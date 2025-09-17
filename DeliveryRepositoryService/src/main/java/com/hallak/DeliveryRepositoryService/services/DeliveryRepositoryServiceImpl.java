package com.hallak.DeliveryRepositoryService.services;

import com.hallak.DeliveryRepositoryService.entities.Delivery;
import com.hallak.DeliveryRepositoryService.entities.Trip;
import com.hallak.DeliveryRepositoryService.repositories.DeliveryRepository;
import com.hallak.shared_libraries.dtos.DeliveryDTO;
import com.hallak.shared_libraries.dtos.DeliveryToASyncDTO;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryRepositoryServiceImpl implements DeliveryRepositoryService{

    private final DeliveryRepository deliveryRepository;
    private final ModelMapper modelMapper;
    private static final Logger log = LoggerFactory.getLogger(DeliveryRepositoryServiceImpl.class);


    public DeliveryRepositoryServiceImpl(DeliveryRepository deliveryRepository, ModelMapper modelMapper) {
        this.deliveryRepository = deliveryRepository;
        this.modelMapper = modelMapper;

    }


    @Override
    @RabbitListener(queues = "${rabbitmq.queue.delivery}")
    public DeliveryToASyncDTO consumeAndPersistDelivery(@Payload DeliveryToASyncDTO deliveryDTO) {
        log.info("Received {}", deliveryDTO);
        Delivery delivery = new Delivery(
                null,
                deliveryDTO.getName(),
                deliveryDTO.getVehicle().getPlate(),
                deliveryDTO.getOrderDTO().getId(),
                deliveryDTO.getDriverDTO().getCpf(),
                modelMapper.map(deliveryDTO.getTripDTO(),
                        Trip.class));
        deliveryRepository.save(delivery);
        return deliveryDTO;

    }

    @Override
    public List<DeliveryDTO> findAll() {
        return deliveryRepository.findAll().stream().map(x -> modelMapper.map(x, DeliveryDTO.class)).toList();
    }
}
