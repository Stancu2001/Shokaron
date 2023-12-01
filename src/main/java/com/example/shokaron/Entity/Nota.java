package com.example.shokaron.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int nota;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonIgnore
    private Student student;

    @ManyToOne
    @JoinColumn(name = "materie_id")
    @JsonIgnore
    private Materie materie;

    @ManyToOne
    @JoinColumn(name = "catalog_id")
    @JsonIgnore
    private Catalog catalog;

    public Long getId() {
        return id;
    }

    public int getNota() {
        return nota;
    }

    public Student getStudent() {
        return student;
    }

    public Materie getMaterie() {
        return materie;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setMaterie(Materie materie) {
        this.materie = materie;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }
// Getters and setters
}
