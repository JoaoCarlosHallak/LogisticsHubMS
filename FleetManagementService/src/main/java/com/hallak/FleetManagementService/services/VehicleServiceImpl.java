package com.hallak.FleetManagementService.services;

import com.hallak.FleetManagementService.dtos.VehicleDTO;
import com.hallak.FleetManagementService.entities.Vehicle;
import com.hallak.FleetManagementService.repositories.VehicleRepository;
import com.hallak.shared_libraries.dtos.Availability;
import com.hallak.shared_libraries.dtos.Maintenance;
import com.hallak.shared_libraries.dtos.Specification;
import com.hallak.shared_libraries.dtos.VehicleToSyncCCDTO;
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
    public List<VehicleToSyncCCDTO> findByParams(String availability, String specification, String maintenance, Double capacity) {
        return vehicleRepository.findByMaintenanceAndSpecificationAndAvailabilityAndCapacityGreaterThanEqual(Maintenance.valueOf(maintenance.toUpperCase()),
                        Specification.valueOf(specification.toUpperCase()),
                        Availability.valueOf(availability.toUpperCase()),
                        capacity)
                .stream().map(x -> modelMapper.map(x, VehicleToSyncCCDTO.class)).toList();
    }


    @Override
    public Void changeMaintenance(Long vehicleId, String maintenance) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new EntityExistsException("This vehicle doesn't exist"));
        vehicle.setMaintenance(Maintenance.valueOf(maintenance.toUpperCase()));
        vehicleRepository.save(vehicle);
        return null;
    }

    @Override
    public Void changeAvailability(Long vehicleId, String availability) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new EntityExistsException("This vehicle doesn't exist"));
        vehicle.setAvailability(Availability.valueOf(availability.toUpperCase()));
        vehicleRepository.save(vehicle);
        return null;
    }

}
