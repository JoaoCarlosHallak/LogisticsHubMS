package com.hallak.DeliveryRepositoryService.services;


import com.hallak.shared_libraries.dtos.DeliveryDTO;
import com.hallak.shared_libraries.dtos.DeliveryToSyncDTO;

import java.util.List;

public interface DeliveryRepositoryService {
    void consumeAndPersistDelivery(DeliveryToSyncDTO deliveryToSyncDTO);
    List<DeliveryDTO> findAll();






}
