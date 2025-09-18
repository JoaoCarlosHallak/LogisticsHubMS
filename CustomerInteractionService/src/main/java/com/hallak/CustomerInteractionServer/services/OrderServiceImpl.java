package com.hallak.CustomerInteractionServer.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hallak.CustomerInteractionServer.dtos.OrderListResponseDTO;
import com.hallak.CustomerInteractionServer.dtos.OrderResponseDTO;
import com.hallak.CustomerInteractionServer.repositories.UserRepository;
import com.hallak.shared_libraries.dtos.*;
import com.hallak.CustomerInteractionServer.entities.Order;
import com.hallak.CustomerInteractionServer.entities.User;
import com.hallak.CustomerInteractionServer.repositories.OrderRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final Queue queueToDispatchOrder;
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;
    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    public OrderServiceImpl(Queue queueToDispatchOrder,
                            RabbitTemplate rabbitTemplate,
                            ObjectMapper objectMapper,
                            OrderRepository orderRepository, UserRepository userRepository,
                            UserService userService,
                            ModelMapper modelMapper) {
        this.objectMapper = objectMapper;
        this.queueToDispatchOrder = queueToDispatchOrder;
        this.rabbitTemplate = rabbitTemplate;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    @Override
    public OrderDTO newOrder(OrderDTO orderDTO) {
        System.out.println(orderDTO);
        if (orderRepository.findByName(orderDTO.getName()).isPresent()) {
            throw new DataIntegrityViolationException("Order with this name already exists");
        }


        User user = userService.authenticated();

        orderDTO.setState(State.SEPARATION);
        Order order = modelMapper.map(orderDTO, Order.class);
        order.setUser(user);


        orderDTO.setUserResponseDTO(modelMapper.map(user, UserResponseDTO.class));

        orderRepository.save(order);

        return orderDTO;

    }

    @Override
    public List<OrderListResponseDTO> findMyOrders() {
        return orderRepository
                .findAllByUser_Username(userService.authenticated().getUsername())
                .stream().map(x -> modelMapper.map(x, OrderListResponseDTO.class)).toList();
    }

    @Override
    public OrderResponseDTO findMyOrder(String name) {
        return modelMapper.map
                (orderRepository.findByName(name)
                                .orElseThrow(() -> new UsernameNotFoundException("Order with this name doesn't exists"))
                        , OrderResponseDTO.class);

    }

    @Override
    public DeliveryToCommunicationDTO dispatchOrderById(Long id) {
        Order order = orderRepository.findById(id).
                orElseThrow(() -> new UsernameNotFoundException("Order with this id doesn't exists"));
        log.info("Sending order > name: {} / specification: {} / state: {}", order.getName(), order.getSpecification(), order.getState());
        Object response = rabbitTemplate.convertSendAndReceive(queueToDispatchOrder.getName(), modelMapper.map(order, OrderDTO.class));
        log.info("Received {}", response);
        if (response instanceof DeliveryToCommunicationDTO delivery) {
            delivery.getOrderDTO().setUserResponseDTO(modelMapper.map(orderRepository.findUserByOrderId(delivery.getOrderDTO().getId()), UserResponseDTO.class));
            return delivery;
            }
        return new DeliveryToCommunicationDTO();

    }

    @Override
    public OrderDTO findOrderById(Long id) {
        return modelMapper.map(
                orderRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Order with this id doesn't exists"))
                , OrderDTO.class);
    }
}



