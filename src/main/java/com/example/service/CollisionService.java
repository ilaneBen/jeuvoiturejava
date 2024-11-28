package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Vehicle;
import com.example.repository.VehicleRepository;

@Service
public class CollisionService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public boolean checkCollision(Vehicle v1, Vehicle v2) {
        // Détection simple basée sur les positions
        return Math.abs(v1.getX() - v2.getX()) < 10 && Math.abs(v1.getY() - v2.getY()) < 10;
    }

    public void handleCollision(Vehicle v1, Vehicle v2) {
        // Réduit la vitesse des véhicules
        v1.setSpeed(Math.max(0, v1.getSpeed() - 10));
        v2.setSpeed(Math.max(0, v2.getSpeed() - 10));

        // Affiche une alerte dans les logs
        System.out.println("Collision entre " + v1.getType() + " et " + v2.getType());
    }

public void detectAndHandleCollisions(List<Vehicle> vehicles) {
    for (int i = 0; i < vehicles.size(); i++) {
        Vehicle v1 = vehicles.get(i);
        for (int j = i + 1; j < vehicles.size(); j++) {
            Vehicle v2 = vehicles.get(j);
            if (isOverlapping(v1, v2)) {
                System.out.println("Collision entre " + v1.getType() + " et " + v2.getType());
                repositionVehicle(v2); // Déplace le véhicule pour éviter des collisions répétées
            }
        }
    }
}

private void repositionVehicle(Vehicle vehicle) {
    vehicle.setX((int) (Math.random() * 740)); // Nouvelle position aléatoire
    vehicle.setY(400); // Remonte en haut de l'écran
}


    public void updateVehiclePositions(List<Vehicle> vehicles) {
        for (Vehicle vehicle : vehicles) {
            vehicle.setX(vehicle.getX() + vehicle.getSpeed());
            if (vehicle.getX() > 800) { 
                vehicle.setX(0);
            }
            vehicleRepository.save(vehicle);
        }
    }
public boolean isOverlapping(Vehicle vehicle1, Vehicle vehicle2) {
    return Math.abs(vehicle1.getX() - vehicle2.getX()) < 50 && // Largeur d'un véhicule
           Math.abs(vehicle1.getY() - vehicle2.getY()) < 30;  // Hauteur d'un véhicule
}


    public boolean isOverlappingWithAny(Vehicle newVehicle, List<Vehicle> existingVehicles) {
        for (Vehicle existing : existingVehicles) {
            if (isOverlapping(newVehicle, existing)) {
                return true;
            }
        }
        return false;
    }
}