package com.hallak.ReportService.OPF;

import com.hallak.shared_libraries.dtos.OrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customerInteraction-service")
public interface CustomerInteractionClient {

    @GetMapping(value = "/admin/order/{id}")
    OrderDTO findOrderById(@PathVariable Long id);
}
