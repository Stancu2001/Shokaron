package com.example.shokaron.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Materie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String Name;

    @ManyToMany(mappedBy = "materii")
    @JsonIgnore
    private List<Profesor> profesori;

    @OneToMany(mappedBy = "materie")
    @JsonIgnore
    private List<ProfesorMaterieClasa> profesoriMateriiClase;

    @ManyToMany(mappedBy = "materii")
    @JsonIgnore
    private List<Catalog> cataloage;
}

