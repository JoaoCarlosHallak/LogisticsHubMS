package com.hallak.DeliveryRepositoryService.services;


import com.hallak.shared_libraries.dtos.DeliveryDTO;
import com.hallak.shared_libraries.dtos.DeliveryToCommunicationDTO;

import java.util.List;

public interface DeliveryRepositoryService {
    DeliveryToCommunicationDTO consumeAndPersistDelivery(DeliveryToCommunicationDTO deliveryToCommunicationDTO);
    List<DeliveryDTO> findAll();
    byte[] deliveryOrder(Long deliveryId);
}
