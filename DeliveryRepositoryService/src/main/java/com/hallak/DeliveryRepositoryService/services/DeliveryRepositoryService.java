package com.hallak.DeliveryRepositoryService.services;


import com.hallak.shared_libraries.dtos.DeliveryDTO;
import com.hallak.shared_libraries.dtos.DeliveryToSyncDTO;

public interface DeliveryRepositoryService {
    void consumeAndPersistDelivery(DeliveryToSyncDTO deliveryToSyncDTO);
    DeliveryToSyncDTO





}
