package com.example.adoptionproject.services;

import com.example.adoptionproject.entities.*;
import com.example.adoptionproject.repositories.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class AdoptionServicesImpl implements IAdoptionServices{
    private final AdoptantRepository adoptantRepository;
    private final AnimalRepository animalRepository;
    private final AdoptionRepository adoptionRepository;

    @Override
    public Adoptant addAdoptant(Adoptant adoptant) {
        return adoptantRepository.save(adoptant);
    }

    @Override
    public Animal addAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

    @Override
    public Adoption addAdoption(Adoption adoption, int idAdoptant, int idAnimal) {
        Adoptant adoptant = adoptantRepository.findById(idAdoptant).orElse(null);
        Animal animal = animalRepository.findById(idAnimal).orElse(null);
        if (adoptant != null && animal != null) {
            adoption.setAdoptant(adoptant);
            adoption.setAnimal(animal);
            return adoptionRepository.save(adoption);
        }
        return null;
    }

    @Override
    public List<Adoption> getAdoptionsByAdoptant(String nom) {
        return adoptionRepository.findByAdoptant_Nom(nom);
    }

    @Override
    public float calculFraisTotalAdoptions(int idAdoptant) {
        List<Adoption> adoptions = adoptionRepository.findByAdoptant_IdAdoptant(idAdoptant);
        float total = 0f;
        for (Adoption adoption : adoptions) {
            total += adoption.getFrais();
        }
        return total;
    }
}
