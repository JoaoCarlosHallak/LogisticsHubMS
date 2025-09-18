package com.hallak.ReportService.services;

import com.hallak.shared_libraries.dtos.DeliveryDTO;

public interface ReportService {
    byte[] makeReportAndReturnDelivery(DeliveryDTO deliveryDTO);
}
