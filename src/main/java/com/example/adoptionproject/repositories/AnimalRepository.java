package com.example.adoptionproject.repositories;

import com.example.adoptionproject.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Integer> {
}
