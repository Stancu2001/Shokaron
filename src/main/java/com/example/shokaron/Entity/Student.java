package com.example.shokaron.Entity;

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
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nume;
    private String prenume;

//    @Column(unique = true,length = 13)
//    private String CNP;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private Clasa clasa;

    @OneToMany(mappedBy = "student")
    private List<Nota> note;

    // Getters and setters
}