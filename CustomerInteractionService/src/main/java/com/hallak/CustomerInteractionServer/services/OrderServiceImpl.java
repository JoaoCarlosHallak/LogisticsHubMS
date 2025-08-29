package com.hallak.CustomerInteractionServer.services;

import com.hallak.CustomerInteractionServer.dtos.OrderDTO;
import com.hallak.CustomerInteractionServer.dtos.OrderListResponseDTO;
import com.hallak.CustomerInteractionServer.dtos.OrderResponseDTO;
import com.hallak.CustomerInteractionServer.dtos.UserResponseDTO;
import com.hallak.CustomerInteractionServer.entities.Order;
import com.hallak.CustomerInteractionServer.entities.State;
import com.hallak.CustomerInteractionServer.entities.User;
import com.hallak.CustomerInteractionServer.repositories.OrderRepository;
import org.hibernate.exception.DataException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserService userService, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    @Override
    public OrderDTO newOrder(OrderDTO orderDTO) {
        System.out.println(orderDTO);
        if (orderRepository.findByName(orderDTO.getName()).isPresent()){
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
}
