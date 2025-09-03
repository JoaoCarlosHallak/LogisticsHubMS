package com.hallak.AssignmentLogicService.services;

import com.hallak.AssignmentLogicService.SyncConfig.OPF.FleetManagementClient;
import com.hallak.shared_libraries.dtos.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AssignmentServiceImpl implements AssignmentService {



    private static final Logger log = LoggerFactory.getLogger(AssignmentServiceImpl.class);
    private final DistanceMatrixService distanceMatrixService;
    private final FleetManagementClient fleetManagementClient;
    private final Queue queueToSaveDelivery;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public AssignmentServiceImpl(DistanceMatrixService distanceMatrixService, FleetManagementClient fleetManagementClient, Queue queueToSaveDelivery, RabbitTemplate rabbitTemplate){
        this.distanceMatrixService = distanceMatrixService;
        this.fleetManagementClient = fleetManagementClient;
        this.queueToSaveDelivery = queueToSaveDelivery;
        this.rabbitTemplate = rabbitTemplate;
    }



    @Override
    @RabbitListener(queues = "{rabbitmq.queue.order}")
    public DeliveryToSyncDTO assignmentOrderAndVehicleAndDriver(@Payload OrderDTO orderDTO) {
        Specification specification = orderDTO.getSpecification();
        Double weight = orderDTO.getWeight();
        String originCep = orderDTO.getRoute().getOrigin().getCep();
        String destinyCep = orderDTO.getRoute().getDestiny().getCep();

        try {
            List<DriverToSyncCCDTO> drivers = fleetManagementClient.findByParamsDrive(specification.toString(), Situation.AVAILABLE);
            List<VehicleToSyncCCDTO> vehicles = fleetManagementClient.findByParamsVehicle(Availability.AVAILABLE.toString(),
                    ((distanceMatrixService.getTravelTimeInHours(originCep, destinyCep) > 15) ? Maintenance.LOW : Maintenance.MEDIUM).toString(),
                    specification.toString(),
                    weight);

            if (drivers.isEmpty() && vehicles.isEmpty()) {
                throw new ResourceAccessException("No drivers and vehicles available for assignment");
            } else if (drivers.isEmpty()) {
                throw new ResourceAccessException("No drivers available for assignment");
            } else if (vehicles.isEmpty()) {
                throw new ResourceAccessException("No vehicles available for assignment");
            }

            DriverToSyncCCDTO driver = drivers.getFirst();
            VehicleToSyncCCDTO vehicle = vehicles.getFirst();


            DeliveryToSyncDTO deliveryToSyncDTO = new DeliveryToSyncDTO(
                    "Delivery: " + driver.getName() + " " + vehicle.getModel() + " " + orderDTO.getName(),
                    vehicle,
                    orderDTO,
                    driver,
                    new TripDTO(LocalDateTime.now()));

            rabbitTemplate.convertSendAndReceive(queueToSaveDelivery.getName(), deliveryToSyncDTO);

            return deliveryToSyncDTO;



        } catch (Exception e){
            throw new RuntimeException("Error : " + e.getMessage());
        }











    }
}
