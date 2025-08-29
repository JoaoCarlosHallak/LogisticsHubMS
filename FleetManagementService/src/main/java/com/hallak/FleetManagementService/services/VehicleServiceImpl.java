package com.hallak.FleetManagementService.services;

import com.hallak.FleetManagementService.dtos.VehicleDTO;
import com.hallak.FleetManagementService.entities.Vehicle;
import com.hallak.FleetManagementService.repositories.VehicleRepository;
import com.hallak.shared_libraries.entities.Availability;
import com.hallak.shared_libraries.entities.Maintenance;
import com.hallak.shared_libraries.entities.Specifications;
import jakarta.persistence.EntityExistsException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class VehicleServiceImpl implements VehicleService{

    private final VehicleRepository vehicleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository, ModelMapper modelMapper) {
        this.vehicleRepository = vehicleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public VehicleDTO newVehicle(VehicleDTO vehicleDTO) {
        return modelMapper.map(vehicleRepository.save(modelMapper.map(vehicleDTO, Vehicle.class)), VehicleDTO.class);


    }

    @Override
    public VehicleDTO findById(Long id) {
        return modelMapper.map(vehicleRepository.findById(id), VehicleDTO.class);
    }

    @Override
    public List<VehicleDTO> findAll() {
        return vehicleRepository.findAll().stream()
                .map(x -> modelMapper.map(x, VehicleDTO.class)).toList();
    }

    @Override
    @Transactional
    public String deleteById(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(() -> new EntityExistsException("Vehicle not founded"));
        vehicleRepository.delete(vehicle);
        return "Vehicle of model: " + vehicle.getModel() + " and plate: " + vehicle.getPlate() + " deleted with success";

    }

    @Override
    @Transactional
    public VehicleDTO updateVehicle(VehicleDTO vehicleDTO) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleDTO.getId());

        if (vehicle.isEmpty()){
            throw new EntityExistsException();
        }

        return modelMapper.map(vehicleRepository.save(modelMapper.map(vehicleDTO, Vehicle.class)), VehicleDTO.class);



    }

    @Override
    public List<VehicleDTO> findByParams(String availability, String specification, String maintenance) {
        return vehicleRepository.findByMaintenanceAndSpecificationsAndAvailability(Maintenance.valueOf(maintenance.toUpperCase()),
                        Specifications.valueOf(specification.toUpperCase()),
                        Availability.valueOf(availability.toUpperCase()))
                .stream().map(x -> modelMapper.map(x, VehicleDTO.class)).toList();
    }

}
