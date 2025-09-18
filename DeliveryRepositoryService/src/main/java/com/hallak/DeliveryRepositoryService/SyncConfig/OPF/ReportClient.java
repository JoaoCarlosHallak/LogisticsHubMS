package com.hallak.DeliveryRepositoryService.SyncConfig.OPF;

import com.hallak.shared_libraries.dtos.DeliveryDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "report-service")
public interface ReportClient {

    @PostMapping
    byte[] makeReportAndReturnDeliveryPDF(@RequestBody DeliveryDTO deliveryDTO);



}
