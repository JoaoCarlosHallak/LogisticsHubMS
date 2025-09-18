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
    @RabbitListener(queues = "${rabbitmq.queue.order}")
    public DeliveryToCommunicationDTO assignmentOrderAndVehicleAndDriver(@Payload OrderDTO orderDTO) {
        log.info("Received {}", orderDTO);
        Specification specification = orderDTO.getSpecification();
        Double weight = orderDTO.getWeight();
        String originCep = orderDTO.getRoute().getOrigin().getCep();
        String destinyCep = orderDTO.getRoute().getDestiny().getCep();


        try {
            List<DriverToSyncCCDTO> drivers = fleetManagementClient.findByParamsDrive(specification.toString(), Situation.AVAILABLE.toString());
            List<VehicleToSyncCCDTO> vehicles = fleetManagementClient.findByParamsVehicle(Availability.AVAILABLE.toString(),
                    ((distanceMatrixService.getTravelTimeInHours(originCep, destinyCep) > 15) ? Maintenance.LOW : Maintenance.MEDIUM).toString(),
                    specification.toString(),
                    weight);
            drivers.forEach(System.out::println);
            vehicles.forEach(System.out::println);

            if (drivers.isEmpty() && vehicles.isEmpty()) {
                throw new ResourceAccessException("No drivers and vehicles available for assignment");
            } else if (drivers.isEmpty()) {
                throw new ResourceAccessException("No drivers available for assignment");
            } else if (vehicles.isEmpty()) {
                throw new ResourceAccessException("No vehicles available for assignment");
            }


            DriverToSyncCCDTO driver = drivers.get(0);
            VehicleToSyncCCDTO vehicle = vehicles.get(0);

            fleetManagementClient.changeSituation(driver.getCpf(), Situation.ON_ROUTE.toString());
            fleetManagementClient.changeAvailability(vehicle.getPlate(), Availability.RUNNING.toString());



            DeliveryToCommunicationDTO deliveryToCommunicationDTO = new DeliveryToCommunicationDTO(
                    "Delivery: " + driver.getName() + " | " + vehicle.getModel() + " | " + orderDTO.getName(),
                    vehicle,
                    orderDTO,
                    driver,
                    new TripDTO(LocalDateTime.now()));

            log.info("Sending {}", deliveryToCommunicationDTO);

            rabbitTemplate.convertSendAndReceive(queueToSaveDelivery.getName(), deliveryToCommunicationDTO);

            log.info("Received {}", deliveryToCommunicationDTO);

            return deliveryToCommunicationDTO;



        } catch (Exception e){
            log.error("Erro no assignmentOrderAndVehicleAndDriver", e);
            throw new RuntimeException("Erro no assignmentOrderAndVehicleAndDriver", e);
        }











    }
}
