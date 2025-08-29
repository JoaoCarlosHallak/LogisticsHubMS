package com.hallak.FleetManagementService.repositories;

import com.hallak.FleetManagementService.entities.Vehicle;
import com.hallak.shared_libraries.entities.Availability;
import com.hallak.shared_libraries.entities.Maintenance;
import com.hallak.shared_libraries.entities.Specifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByMaintenanceAndSpecificationsAndAvailability(Maintenance maintenance, Specifications specifications, Availability availability);
}
