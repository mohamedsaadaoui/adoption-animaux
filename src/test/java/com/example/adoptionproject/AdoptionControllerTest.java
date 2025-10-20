package com.example.adoptionproject;

import com.example.adoptionproject.controllers.AdoptionRestController;
import com.example.adoptionproject.entities.*;
import com.example.adoptionproject.services.IAdoptionServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AdoptionRestController.class)
class AdoptionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IAdoptionServices adoptionServices;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void addAdoptant_ShouldReturnCreatedAdoptant() throws Exception {
        // Given
        Adoptant adoptant = new Adoptant();
        adoptant.setIdAdoptant(1);
        adoptant.setNom("Dupont");
        adoptant.setAdresse("123 Rue Test");
        adoptant.setTelephone("0123456789");

        when(adoptionServices.addAdoptant(any(Adoptant.class))).thenReturn(adoptant);

        // When & Then - Utilisez la bonne URL : "/addAdoptant"
        mockMvc.perform(post("/addAdoptant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(adoptant)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value("Dupont"));
    }

    @Test
    void addAnimal_ShouldReturnCreatedAnimal() throws Exception {
        // Given
        Animal animal = new Animal();
        animal.setIdAnimal(1);
        animal.setNom("Médor");
        animal.setAge(3);
        animal.setSterilise(true);
        animal.setEspece(Espece.CHIEN);

        when(adoptionServices.addAnimal(any(Animal.class))).thenReturn(animal);

        // When & Then
        mockMvc.perform(post("/addAnimal")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(animal)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value("Médor"));
    }

    @Test
    void addAdoption_ShouldReturnCreatedAdoption() throws Exception {
        // Given
        Adoption adoption = new Adoption();
        adoption.setIdAdoption(1);
        adoption.setFrais(150.0f);

        when(adoptionServices.addAdoption(any(Adoption.class), anyInt(), anyInt()))
                .thenReturn(adoption);

        // When & Then - Utilisez la bonne URL : "/addAdoption/{idAdoptant}/{idAnimal}"
        mockMvc.perform(post("/addAdoption/1/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(adoption)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.frais").value(150.0));
    }

    @Test
    void getAdoptionsByAdoptant_ShouldReturnAdoptionsList() throws Exception {
        // Given
        Adoptant adoptant = new Adoptant();
        adoptant.setNom("Dupont");

        Adoption adoption = new Adoption();
        adoption.setIdAdoption(1);
        adoption.setFrais(100.0f);
        adoption.setAdoptant(adoptant);

        List<Adoption> adoptions = Arrays.asList(adoption);

        when(adoptionServices.getAdoptionsByAdoptant(anyString())).thenReturn(adoptions);

        // When & Then
        mockMvc.perform(get("/byAdoptant/Dupont"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].frais").value(100.0))
                .andExpect(jsonPath("$[0].adoptant.nom").value("Dupont"));
    }

    @Test
    void calculTotalFrais_ShouldReturnCorrectSum() throws Exception {
        // Given
        when(adoptionServices.calculFraisTotalAdoptions(anyInt())).thenReturn(350.0f);

        // When & Then
        mockMvc.perform(get("/totalFrais/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("350.0"));
    }
}