package com.hallak.AssignmentLogicService.services;

import com.hallak.shared_libraries.dtos.DeliveryToCommunicationDTO;
import com.hallak.shared_libraries.dtos.OrderDTO;


public interface AssignmentService {
    DeliveryToCommunicationDTO assignmentOrderAndVehicleAndDriver(OrderDTO orderDTO);
}
