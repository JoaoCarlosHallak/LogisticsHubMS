package com.hallak.ReportService.services;

import com.hallak.ReportService.OPF.CustomerInteractionClient;
import com.hallak.ReportService.OPF.FleetManagementClient;
import com.hallak.shared_libraries.dtos.*;
import org.openpdf.text.*;
import org.openpdf.text.pdf.PdfPTable;
import org.openpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.time.Duration;
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
        UserResponseDTO userResponseDTO = customerInteractionClient.getClientByUsername(deliveryDTO.getClientUsername());
        customerInteractionClient.changeState(deliveryDTO.getOrderId(), State.DELIVERED.name());


        DeliveryToCommunicationDTO delivery = new DeliveryToCommunicationDTO(
                "Delivery: " + driver.getName() + " | " + vehicle.getModel() + " | " + order.getName(),
                vehicle,
                order,
                driver,
                deliveryDTO.getTrip()
        );

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try (Document document = new Document(PageSize.A4, 40, 40, 40, 40)) {
            PdfWriter.getInstance(document, baos);
            document.open();


            Font titleFont = new Font(Font.HELVETICA, 18, Font.BOLD);
            Font sectionFont = new Font(Font.HELVETICA, 14, Font.BOLD);


            Paragraph title = new Paragraph("Delivery Report", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(Chunk.NEWLINE);


            document.add(new Paragraph("Delivery Data", sectionFont));
            PdfPTable deliveryTable = new PdfPTable(2);
            deliveryTable.setWidthPercentage(100);
            deliveryTable.setSpacingBefore(5f);
            deliveryTable.setSpacingAfter(15f);
            deliveryTable.addCell("Name");
            deliveryTable.addCell(delivery.getName());
            deliveryTable.addCell("ID");
            deliveryTable.addCell(String.valueOf(deliveryDTO.getId()));
            document.add(deliveryTable);


            document.add(new Paragraph("Order", sectionFont));
            PdfPTable orderTable = new PdfPTable(2);
            orderTable.setWidthPercentage(100);
            orderTable.setSpacingBefore(5f);
            orderTable.setSpacingAfter(15f);
            orderTable.addCell("Name");
            orderTable.addCell(order.getName());
            orderTable.addCell("Specification");
            orderTable.addCell(order.getSpecification().toString());
            orderTable.addCell("Weight");
            orderTable.addCell(String.valueOf(order.getWeight()) + " kg");
            orderTable.addCell("Origin");
            orderTable.addCell(order.getRoute().getOrigin().getName());
            orderTable.addCell("Destination");
            orderTable.addCell(order.getRoute().getDestiny().getName());
            orderTable.addCell("Status");
            orderTable.addCell(order.getState().toString());
            document.add(orderTable);


            document.add(new Paragraph("Vehicle", sectionFont));
            PdfPTable vehicleTable = new PdfPTable(2);
            vehicleTable.setWidthPercentage(100);
            vehicleTable.setSpacingBefore(5f);
            vehicleTable.setSpacingAfter(15f);
            vehicleTable.addCell("Model");
            vehicleTable.addCell(vehicle.getModel());
            vehicleTable.addCell("Plate");
            vehicleTable.addCell(vehicle.getPlate());
            vehicleTable.addCell("Capacity");
            vehicleTable.addCell(vehicle.getCapacity() + " kg");
            document.add(vehicleTable);

            document.add(new Paragraph("Driver", sectionFont));
            PdfPTable driverTable = new PdfPTable(2);
            driverTable.setWidthPercentage(100);
            driverTable.setSpacingBefore(5f);
            driverTable.setSpacingAfter(15f);
            driverTable.addCell("Name");
            driverTable.addCell(driver.getName());
            driverTable.addCell("CPF");
            driverTable.addCell(driver.getCpf());
            driverTable.addCell("Birth Date");
            driverTable.addCell(driver.getBirth_date().toString());
            document.add(driverTable);

            document.add(new Paragraph("Trip", sectionFont));
            PdfPTable tripTable = new PdfPTable(2);
            tripTable.setWidthPercentage(100);
            tripTable.setSpacingBefore(5f);
            tripTable.setSpacingAfter(15f);
            tripTable.addCell("Departure Date");
            tripTable.addCell(delivery.getTrip().getDepartureDate().toString());
            tripTable.addCell("Arrival Date");
            tripTable.addCell(delivery.getTrip().getArrivalDate().toString());
            tripTable.addCell("Total Duration");

            LocalDateTime departureDate = delivery.getTrip().getDepartureDate();
            LocalDateTime arrivalDate = delivery.getTrip().getArrivalDate();


            tripTable.addCell(
                    "In Days: " + Duration.between(departureDate, arrivalDate).toDays() +
                            " | In Hours: " + Duration.between(departureDate, arrivalDate).toHours() +
                            " | In Minutes: " + Duration.between(departureDate, arrivalDate).toMinutes() +
                            " | In Seconds: " + Duration.between(departureDate, arrivalDate).toSeconds()
            );
            document.add(tripTable);

            document.add(new Paragraph("Client", sectionFont));
            PdfPTable clientTable = new PdfPTable(2);
            clientTable.setWidthPercentage(100);
            clientTable.setSpacingBefore(5f);
            clientTable.setSpacingAfter(15f);
            clientTable.addCell("CPF");
            clientTable.addCell(userResponseDTO.getCpf());
            clientTable.addCell("Username");
            clientTable.addCell(userResponseDTO.getUsername());
            clientTable.addCell("Email");
            clientTable.addCell(userResponseDTO.getEmail());
            document.add(clientTable);



        } catch (Exception e) {
            throw new RuntimeException("Fail in generation of PDF", e);
        }

    return baos.toByteArray();
    }
}

