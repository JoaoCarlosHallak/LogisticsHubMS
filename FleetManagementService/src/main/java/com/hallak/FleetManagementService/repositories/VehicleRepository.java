package com.hallak.FleetManagementService.repositories;

import com.hallak.FleetManagementService.entities.Vehicle;
import com.hallak.shared_libraries.dtos.Availability;
import com.hallak.shared_libraries.dtos.Maintenance;
import com.hallak.shared_libraries.dtos.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByMaintenanceAndSpecificationAndAvailabilityAndCapacityGreaterThanEqual(
            Maintenance maintenance,
            Specification specification,
            Availability availability,
            Double capacity
    );
}
