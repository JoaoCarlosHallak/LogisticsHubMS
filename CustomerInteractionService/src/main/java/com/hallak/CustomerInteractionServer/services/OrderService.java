package com.hallak.CustomerInteractionServer.services;

import com.hallak.CustomerInteractionServer.dtos.OrderDTO;
import com.hallak.CustomerInteractionServer.dtos.OrderListResponseDTO;
import com.hallak.CustomerInteractionServer.dtos.OrderResponseDTO;

import java.util.List;

public interface OrderService {
    OrderDTO newOrder(OrderDTO orderDTO);
    List<OrderListResponseDTO> findMyOrders();
    OrderResponseDTO findMyOrder(String name);
}
