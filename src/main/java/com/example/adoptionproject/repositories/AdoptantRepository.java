package com.example.adoptionproject.repositories;

import com.example.adoptionproject.entities.Adoptant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdoptantRepository extends JpaRepository<Adoptant, Integer> {
}
