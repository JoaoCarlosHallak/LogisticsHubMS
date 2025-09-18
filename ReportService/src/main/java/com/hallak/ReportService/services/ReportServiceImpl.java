package com.hallak.ReportService.services;

import com.hallak.ReportService.OPF.CustomerInteractionClient;
import com.hallak.ReportService.OPF.FleetManagementClient;
import com.hallak.shared_libraries.dtos.*;
import org.openpdf.text.Document;
import org.openpdf.text.Paragraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;

@Service
public class ReportServiceImpl implements ReportService {

    private final FleetManagementClient fleetManagementClient;
    private final CustomerInteractionClient customerInteractionClient;

    @Autowired
    public ReportServiceImpl(FleetManagementClient fleetManagementClient, CustomerInteractionClient customerInteractionClient) {
        this.fleetManagementClient = fleetManagementClient;
        this.customerInteractionClient = customerInteractionClient;
    }


    @Override
    public byte[] makeReportAndReturnDelivery(DeliveryDTO deliveryDTO) {


        DriverToSyncCCDTO driver = fleetManagementClient.findByCpf(deliveryDTO.getDriverCpf());
        VehicleToSyncCCDTO vehicle = fleetManagementClient.findByPlate(deliveryDTO.getVehiclePlate());
        OrderDTO order = customerInteractionClient.findOrderById(deliveryDTO.getOrderId());


        DeliveryToCommunicationDTO delivery = new DeliveryToCommunicationDTO(
                "Delivery: " + driver.getName() + " | " + vehicle.getModel() + " | " + order.getName(),
                vehicle,
                order,
                driver,
                deliveryDTO.getTrip());



        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document();
        document.add(new Paragraph("Delivery Report"));
        document.add(new Paragraph(" "));
        document.add(new Paragraph("Name: " + delivery.getName()));
        document.add(new Paragraph("ID: " + deliveryDTO.getId()));
        document.add(new Paragraph("Order: " + delivery.getOrder()));
        document.add(new Paragraph("Trip: " + delivery.getTrip()));
        document.add(new Paragraph("Vehicle: " + delivery.getVehicle()));
        document.close();

        return baos.toByteArray();



    }
}

