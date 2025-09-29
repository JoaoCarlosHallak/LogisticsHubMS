package com.hallak.DeliveryRepositoryService.services;

import com.hallak.DeliveryRepositoryService.SyncConfig.OPF.ReportClient;
import com.hallak.DeliveryRepositoryService.entities.Delivery;
import com.hallak.DeliveryRepositoryService.entities.Trip;
import com.hallak.DeliveryRepositoryService.repositories.DeliveryRepository;
import com.hallak.shared_libraries.dtos.DeliveryDTO;
import com.hallak.shared_libraries.dtos.DeliveryToCommunicationDTO;
import jakarta.persistence.EntityExistsException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeliveryRepositoryServiceImpl implements DeliveryRepositoryService{

    private final DeliveryRepository deliveryRepository;
    private final ModelMapper modelMapper;
    private static final Logger log = LoggerFactory.getLogger(DeliveryRepositoryServiceImpl.class);
    private final ReportClient reportClient;



    public DeliveryRepositoryServiceImpl(DeliveryRepository deliveryRepository, ModelMapper modelMapper, ReportClient reportClient) {
        this.deliveryRepository = deliveryRepository;
        this.modelMapper = modelMapper;
        this.reportClient = reportClient;
    }


    @Override
    @RabbitListener(queues = "${rabbitmq.queue.delivery}")
    public DeliveryToCommunicationDTO consumeAndPersistDelivery(@Payload DeliveryToCommunicationDTO deliveryDTO) {
        log.info("Received {}", deliveryDTO);
        Delivery delivery = new Delivery(
                null,
                deliveryDTO.getName(),
                deliveryDTO.getVehicle().getPlate(),
                deliveryDTO.getOrder().getId(),
                deliveryDTO.getDriver().getCpf(),
                deliveryDTO.getOrder().getUserResponseDTO().getUsername(),
                modelMapper.map(deliveryDTO.getTrip(), Trip.class));
        deliveryRepository.save(delivery);
        return deliveryDTO;

    }

    @Override
    public List<DeliveryDTO> findAll() {
        return deliveryRepository.findAll().stream().map(x -> modelMapper.map(x, DeliveryDTO.class)).toList();
    }

    @Override
    public byte[] deliveryOrder(Long deliveryId) {
        Delivery delivery = deliveryRepository.findById(deliveryId).orElseThrow(() -> new EntityExistsException("There isn't delivery with this id"));
        delivery.getTrip().setArrivalDate(LocalDateTime.now());
        return reportClient.makeReportAndReturnDeliveryPDF(modelMapper.map(delivery, DeliveryDTO.class));

    }


}
