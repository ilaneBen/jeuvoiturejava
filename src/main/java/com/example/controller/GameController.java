package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.model.Ferrari;
import com.example.model.PlayerCar;
import com.example.model.Tesla;
import com.example.model.Vehicle;
import com.example.repository.VehicleRepository;
import com.example.service.CollisionService;
import com.example.service.ScoreService;

@Controller
public class GameController {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private CollisionService collisionService;
    @Autowired
    private ScoreService scoreService;

    @GetMapping("/")
    public String home(Model model) {
        List<Vehicle> vehicles = vehicleRepository.findAll();

        // Ajoutez le joueur s'il n'existe pas
        if (vehicles.stream().noneMatch(v -> v.getType().equals("PlayerCar"))) {
            PlayerCar playerCar = new PlayerCar(100, 50);
            vehicleRepository.save(playerCar);
            vehicles.add(playerCar);
        }

        collisionService.updateVehiclePositions(vehicles); // Déplace les véhicules
        collisionService.detectAndHandleCollisions(vehicles); // Vérifie les collisions
        scoreService.updateScores(vehicles); // Met à jour les scores

        model.addAttribute("vehicles", vehicles);
        return "home"; // Page HTML à afficher
    }

    @PostMapping("/addTesla")
    public String addTesla() {
        Tesla tesla = new Tesla(50, 100); // Position différente de Ferrari
        vehicleRepository.save(tesla);
        return "redirect:/";
    }

    @PostMapping("/addFerrari")
    public String addFerrari() {
        Ferrari ferrari = new Ferrari(100, 200); // Position différente de Tesla
        vehicleRepository.save(ferrari);
        return "redirect:/";
    }

}
