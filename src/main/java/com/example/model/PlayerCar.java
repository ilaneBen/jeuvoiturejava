package com.example.model;

import jakarta.persistence.Entity;

@Entity
public class PlayerCar extends Vehicle {

    public PlayerCar(int x, int y) {
        super(x, y, 50, "PlayerCar", "/images/gtr.png");
    }

    public PlayerCar() {
        super(); 
    }

    @Override
    public void move() {
  
    }

    @Override
    public void brake() {
        setSpeed(Math.max(0, getSpeed() - 10));
    }

    @Override
    public void accelerate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
