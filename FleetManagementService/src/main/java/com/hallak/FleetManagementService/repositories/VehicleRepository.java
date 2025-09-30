package com.hallak.FleetManagementService.repositories;

import com.hallak.FleetManagementService.entities.Vehicle;
import com.hallak.shared_libraries.enums.Availability;
import com.hallak.shared_libraries.enums.Maintenance;
import com.hallak.shared_libraries.enums.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByMaintenanceAndSpecificationAndAvailabilityAndCapacityGreaterThanEqual(
            Maintenance maintenance,
            Specification specification,
            Availability availability,
            Double capacity
    );
    Optional<Vehicle> findByPlate(String plate);
}
