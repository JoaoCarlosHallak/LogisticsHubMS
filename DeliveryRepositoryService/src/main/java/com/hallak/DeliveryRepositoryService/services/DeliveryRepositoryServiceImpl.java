package com.hallak.DeliveryRepositoryService.services;

import com.hallak.DeliveryRepositoryService.entities.Delivery;
import com.hallak.DeliveryRepositoryService.entities.Trip;
import com.hallak.DeliveryRepositoryService.repositories.DeliveryRepository;
import com.hallak.shared_libraries.dtos.DeliveryDTO;
import com.hallak.shared_libraries.dtos.DeliveryToSyncDTO;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryRepositoryServiceImpl implements DeliveryRepositoryService{

    private final DeliveryRepository deliveryRepository;
    private final ModelMapper modelMapper;

    public DeliveryRepositoryServiceImpl(DeliveryRepository deliveryRepository, ModelMapper modelMapper) {
        this.deliveryRepository = deliveryRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    @RabbitListener(queues = "${rabbitmq.queue.delivery}")
    public void consumeAndPersistDelivery(@Payload DeliveryToSyncDTO deliveryDTO) {
        Delivery delivery = new Delivery(
                null,
                deliveryDTO.getName(),
                deliveryDTO.getVehicle().getPlate(),
                deliveryDTO.getOrderDTO().getId(),
                deliveryDTO.getDriverDTO().getCpf(),
                modelMapper.map(deliveryDTO.getTripDTO(),
                        Trip.class));
        deliveryRepository.save(delivery);

    }

    @Override
    public List<DeliveryDTO> findAll() {
        return deliveryRepository.findAll().stream().map(x -> modelMapper.map(x, DeliveryDTO.class)).toList();
    }
}
