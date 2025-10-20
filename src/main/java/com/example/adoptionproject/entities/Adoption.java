package com.example.adoptionproject.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Adoption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAdoption;

    private Date dateAdoption;
    private float frais;

    @ManyToOne
    @JoinColumn(name = "adoptant_id")
    private Adoptant adoptant;

    @OneToOne
    @JoinColumn(name = "animal_id", unique = true)
    private Animal animal;

}
