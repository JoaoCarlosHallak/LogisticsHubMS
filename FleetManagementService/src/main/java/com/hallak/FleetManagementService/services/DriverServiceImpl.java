package com.hallak.FleetManagementService.services;

import com.hallak.FleetManagementService.dtos.DriverDTO;
import com.hallak.FleetManagementService.entities.Driver;
import com.hallak.FleetManagementService.repositories.DriverRepository;

import com.hallak.shared_libraries.dtos.DriverToSyncCCDTO;
import com.hallak.shared_libraries.enums.Situation;
import com.hallak.shared_libraries.enums.Specification;
import com.hallak.shared_libraries.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;



@Service
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public DriverServiceImpl(DriverRepository driverRepository, ModelMapper modelMapper) {
        this.driverRepository = driverRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public DriverDTO newDriver(DriverDTO driverDTO) {
        return modelMapper.map(
                driverRepository.save(modelMapper.map(driverDTO, Driver.class)),
                DriverDTO.class
        );
    }

    @Override
    public DriverDTO findById(Long id) {
        return modelMapper.map(driverRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Driver not found")),
                DriverDTO.class
        );
    }

    @Override
    public List<DriverDTO> findAll() {
        return driverRepository.findAll().stream()
                .map(x -> modelMapper.map(x, DriverDTO.class))
                .toList();
    }

    @Override
    @Transactional
    public String deleteById(Long id) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Driver not found"));
        driverRepository.delete(driver);
        return "Driver with name: " + driver.getName() + " deleted successfully";
    }

    @Override
    @Transactional
    public DriverDTO updateDriver(DriverDTO driverDTO) {
        Optional<Driver> driver = driverRepository.findById(driverDTO.getId());

        if (driver.isEmpty()) {
            throw new ResourceNotFoundException("Driver not found");
        }

        return modelMapper.map(
                driverRepository.save(modelMapper.map(driverDTO, Driver.class)),
                DriverDTO.class
        );
    }

    @Override
    public List<DriverToSyncCCDTO> findByParams(String specification, String situation) {
        return driverRepository.findBySpecificationAndSituation(
                        Specification.valueOf(specification.toUpperCase()),
                        Situation.valueOf(situation.toUpperCase()))
                .stream().map(x -> modelMapper.map(x, DriverToSyncCCDTO.class)).toList();


    }

    @Override
    public Void changeSituation(String cpf, String situation) {
        Driver driver = driverRepository.findByCpf(cpf).orElseThrow(() -> new ResourceNotFoundException("This driver doesn't exists"));
        driver.setSituation(Situation.valueOf(situation.toUpperCase()));
        driverRepository.save(driver);
        return null;
    }

    @Override
    public DriverToSyncCCDTO findByCpf(String cpf) {
        System.out.println(cpf);
        System.out.println(Objects.equals(cpf, driverRepository.findById(1L).get().getCpf()));
        return modelMapper.map(driverRepository.findByCpf(cpf)
                        .orElseThrow(() -> new ResourceNotFoundException("Driver not found")),
                DriverToSyncCCDTO.class
        );
    }


}
