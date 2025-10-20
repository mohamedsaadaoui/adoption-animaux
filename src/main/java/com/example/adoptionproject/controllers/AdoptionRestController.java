package com.example.adoptionproject.controllers;

import com.example.adoptionproject.entities.*;
import com.example.adoptionproject.services.IAdoptionServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("")
public class AdoptionRestController {

    private final IAdoptionServices adoptionServices;

    @PostMapping("/addAdoptant")
    public Adoptant addAdoptant(@RequestBody Adoptant adoptant) {
        return adoptionServices.addAdoptant(adoptant);
    }

    @PostMapping("/addAnimal")
    public Animal addAnimal(@RequestBody Animal animal) {
        return adoptionServices.addAnimal(animal);
    }

    @PostMapping("/addAdoption/{idAdoptant}/{idAnimal}")
    public Adoption addAdoption(@RequestBody Adoption adoption,
                                @PathVariable int idAdoptant,
                                @PathVariable int idAnimal) {
        return adoptionServices.addAdoption(adoption, idAdoptant, idAnimal);
    }

    @GetMapping("/byAdoptant/{nom}")
    public List<Adoption> getAdoptionsByAdoptant(@PathVariable String nom) {
        return adoptionServices.getAdoptionsByAdoptant(nom);
    }

    @GetMapping("/totalFrais/{idAdoptant}")
    public float calculTotalFrais(@PathVariable int idAdoptant) {
        return adoptionServices.calculFraisTotalAdoptions(idAdoptant);
    }
}
