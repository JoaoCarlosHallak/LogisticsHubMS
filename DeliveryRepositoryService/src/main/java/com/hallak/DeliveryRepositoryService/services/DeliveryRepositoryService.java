package com.hallak.DeliveryRepositoryService.services;


import com.hallak.shared_libraries.dtos.DeliveryDTO;
import com.hallak.shared_libraries.dtos.DeliveryToASyncDTO;

import java.util.List;

public interface DeliveryRepositoryService {
    DeliveryToASyncDTO consumeAndPersistDelivery(DeliveryToASyncDTO deliveryToASyncDTO);
    List<DeliveryDTO> findAll();






}
