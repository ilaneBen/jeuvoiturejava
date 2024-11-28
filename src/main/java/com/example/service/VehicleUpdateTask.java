package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.model.Vehicle;
import com.example.repository.VehicleRepository;

@Service
public class VehicleUpdateTask {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Scheduled(fixedRate = 100)
    public void updateVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        for (Vehicle vehicle : vehicles) {
            if (!vehicle.getType().equals("PlayerCar")) {
                vehicle.move();
            }
        }
        vehicleRepository.saveAll(vehicles);
    }
}
