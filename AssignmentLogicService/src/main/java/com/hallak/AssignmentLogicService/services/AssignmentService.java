package com.hallak.AssignmentLogicService.services;

import com.hallak.shared_libraries.dtos.DeliveryToSyncDTO;
import com.hallak.shared_libraries.dtos.OrderDTO;


public interface AssignmentService {
    DeliveryToSyncDTO assignmentOrderAndVehicleAndDriver(OrderDTO orderDTO);
}
