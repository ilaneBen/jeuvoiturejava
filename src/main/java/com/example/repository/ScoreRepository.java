package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Score;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {
}
