package com.example.shokaron.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Clasa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String className;

    @OneToMany(mappedBy = "clasa")
    @JsonIgnore
    private List<Student> students;

    @OneToOne(mappedBy = "clasa")
    @JsonIgnore
    private Catalog catalog;

    @OneToMany(mappedBy = "clasa")
    @JsonIgnore
    private List<ProfesorMaterieClasa> profesoriMateriiClase;

    // Alte atribute legate de clasă

    // Getters and setters
}
