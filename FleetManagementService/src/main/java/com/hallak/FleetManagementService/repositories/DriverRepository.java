package com.hallak.FleetManagementService.repositories;

import com.hallak.FleetManagementService.entities.Driver;
import com.hallak.shared_libraries.enums.Situation;
import com.hallak.shared_libraries.enums.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    List<Driver> findBySpecificationAndSituation(
            Specification specification,
            Situation situation);
    Optional<Driver> findByCpf(String cpf);
}


