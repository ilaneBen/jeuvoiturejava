package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Score;
import com.example.model.Vehicle;
import com.example.repository.ScoreRepository;

@Service
public class ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    public void updateScores(List<Vehicle> vehicles) {
        for (Vehicle vehicle : vehicles) {
            Score score = scoreRepository.findById(vehicle.getId()).orElse(null);
            if (score == null) {
                // Crée un nouveau score si le véhicule n'en a pas encore
                score = new Score(vehicle.getType(), 0, 0);
            }

            // Augmente la distance et les points en fonction de la vitesse
            score.setDistance(score.getDistance() + vehicle.getSpeed());
            score.setPoints(score.getPoints() + (vehicle.getSpeed() / 10));
            scoreRepository.save(score);
        }
    }

    public List<Score> getAllScores() {
        return scoreRepository.findAll();
    }

    public Score saveScore(Score score) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
