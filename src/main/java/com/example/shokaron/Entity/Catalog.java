package com.example.shokaron.Entity;

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
public class Catalog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "class_id")
    private Clasa clasa;

    @ManyToMany
    @JoinTable(
            name = "catalog_materie",
            joinColumns = @JoinColumn(name = "catalog_id"),
            inverseJoinColumns = @JoinColumn(name = "materie_id"))
    private List<Materie> materii;

    // Alte atribute legate de catalog

    // Getters and setters
}
