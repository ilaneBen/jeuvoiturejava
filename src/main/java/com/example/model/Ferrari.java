package com.example.model;

import jakarta.persistence.Entity;

@Entity
public class Ferrari extends Vehicle {
    public Ferrari(int x, int y) {
        super(x, y, 7, "Ferrari", "/images/ferrari.png");
    }

    public Ferrari() {
        super(); // Constructeur vide requis par JPA
    }

    @Override
    public void move() {
        this.y -= this.speed; // Déplacement vers le bas
        if (this.y < -50) {   // Réinitialise lorsque hors écran
            this.y = 400;     // Réapparaît en haut
            this.x = (int) (Math.random() * 740); // Position aléatoire sur l'axe X
        }
    }

    @Override
    public void accelerate() {
        this.speed += 1; 
    }

    @Override
    public void brake() {
        this.speed = Math.max(0, this.speed - 1);
    }
}

