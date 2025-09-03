package com.hallak.CustomerInteractionServer.services;

import com.hallak.CustomerInteractionServer.dtos.OrderListResponseDTO;
import com.hallak.CustomerInteractionServer.dtos.OrderResponseDTO;
import com.hallak.shared_libraries.dtos.DeliveryToSyncDTO;
import com.hallak.shared_libraries.dtos.OrderDTO;

import java.util.List;

public interface OrderService {
    OrderDTO newOrder(OrderDTO orderDTO);
    List<OrderListResponseDTO> findMyOrders();
    OrderResponseDTO findMyOrder(String name);
    DeliveryToSyncDTO dispachOrderById(Long id);
}
