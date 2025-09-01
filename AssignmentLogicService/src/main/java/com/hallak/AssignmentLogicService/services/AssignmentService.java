package com.hallak.AssignmentLogicService.services;

import com.hallak.shared_libraries.dtos.OrderDTO;


public interface AssignmentService {
    Object assignmentOrderAndVehicle(OrderDTO orderDTO);
}
