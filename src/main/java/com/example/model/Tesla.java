package com.example.model;

import jakarta.persistence.Entity;

@Entity
public class Tesla extends Vehicle {
    public Tesla() {
        super();
    }

    public Tesla(int x, int y) {
        super(x, y, 5, "Tesla", "/images/tesla.png");
    }
  @Override
    public void accelerate() {
        this.speed += 2; 
    }

    @Override
    public void brake() {
        this.speed = Math.max(0, this.speed - 2); 
    }

    @Override
    public void move() {
        this.y -= this.speed; // Déplacement vertical vers le bas
        if (this.y < -50) {   // Réinitialise lorsque hors écran
            this.y = 400;
            this.x = (int) (Math.random() * 740); // Nouvelle position aléatoire
        }
    }
}