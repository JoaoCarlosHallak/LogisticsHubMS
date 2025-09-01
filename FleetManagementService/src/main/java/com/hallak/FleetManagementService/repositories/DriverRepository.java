package com.hallak.FleetManagementService.repositories;

import com.hallak.FleetManagementService.entities.Driver;
import com.hallak.FleetManagementService.entities.Vehicle;
import com.hallak.shared_libraries.dtos.Availability;
import com.hallak.shared_libraries.dtos.Maintenance;
import com.hallak.shared_libraries.dtos.Situation;
import com.hallak.shared_libraries.dtos.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    List<Driver> findBySpecificationAndSituation(
            Specification specification,
            Situation situation);
}


