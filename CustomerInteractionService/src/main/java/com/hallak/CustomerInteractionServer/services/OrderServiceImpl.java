package com.hallak.CustomerInteractionServer.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hallak.CustomerInteractionServer.dtos.OrderListResponseDTO;
import com.hallak.CustomerInteractionServer.dtos.OrderResponseDTO;
import com.hallak.shared_libraries.dtos.OrderDTO;
import com.hallak.shared_libraries.dtos.UserResponseDTO;
import com.hallak.CustomerInteractionServer.entities.Order;
import com.hallak.shared_libraries.dtos.State;
import com.hallak.CustomerInteractionServer.entities.User;
import com.hallak.CustomerInteractionServer.repositories.OrderRepository;
import com.hallak.shared_libraries.dtos.DeliveryDTO;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final String orderQueueName;
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public OrderServiceImpl(@Value("${rabbitmq.queue.order}") String orderQueueName,
                            RabbitTemplate rabbitTemplate,
                            ObjectMapper objectMapper,
                            OrderRepository orderRepository,
                            UserService userService,
                            ModelMapper modelMapper) {
        this.objectMapper = objectMapper;
        this.orderQueueName = orderQueueName;
        this.rabbitTemplate = rabbitTemplate;
        this.orderRepository = orderRepository;
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
    public DeliveryDTO dispachOrderById(Long id) {
        Order order = orderRepository.findById(id).
                orElseThrow(() -> new UsernameNotFoundException("Order with this id doesn't exists"));
        Object response = rabbitTemplate.convertSendAndReceive(orderQueueName, order);
        if (response instanceof LinkedHashMap<?, ?> map) {
            if (map.containsKey("name")) {
                return objectMapper.convertValue(map, DeliveryDTO.class);
            }

        }
         return new DeliveryDTO();
    }
}
