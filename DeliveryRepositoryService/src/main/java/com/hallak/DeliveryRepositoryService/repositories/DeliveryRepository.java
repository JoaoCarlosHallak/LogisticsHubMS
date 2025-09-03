package com.hallak.DeliveryRepositoryService.repositories;

import com.hallak.DeliveryRepositoryService.entities.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}
