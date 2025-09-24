package com.hallak.ReportService.OPF;

import com.hallak.shared_libraries.dtos.OrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "customerInteraction-service")
public interface CustomerInteractionClient {

    @GetMapping(value = "/admin/order/{id}")
    OrderDTO findOrderById(@PathVariable Long id);

    @PutMapping(value = "/admin/order/{id}/state")
    OrderDTO changeState(@PathVariable Long id, @RequestParam String state);
}
