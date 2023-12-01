package com.example.shokaron.controller;

import com.example.shokaron.DTO.NoteDetail;
import com.example.shokaron.Entity.Catalog;
import com.example.shokaron.Entity.Materie;
import com.example.shokaron.Entity.Nota;
import com.example.shokaron.Entity.Student;
import com.example.shokaron.Repository.Catalog_Repository;
import com.example.shokaron.Repository.Materie_Repository;
import com.example.shokaron.Repository.Nota_Repository;
import com.example.shokaron.Repository.Student_Repository;
import com.example.shokaron.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class Controller_Nota {

    private final Nota_Repository notaRepository;
    private final Student_Repository studentRepository;
    private final Catalog_Repository catalogRepository;
    private final Materie_Repository materieRepository;

    @Autowired
    public Controller_Nota(Nota_Repository notaRepository, Student_Repository studentRepository, Catalog_Repository catalogRepository, Materie_Repository materieRepository) {
        this.notaRepository = notaRepository;
        this.studentRepository = studentRepository;
        this.catalogRepository = catalogRepository;
        this.materieRepository = materieRepository;
    }

    @GetMapping("/nota/all")
    public ResponseEntity<List<NoteDetail>> getAllNotesWithDetails() {
        List<Nota> noteList = notaRepository.findAll();
        if (noteList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<NoteDetail> notaDetailsList = new ArrayList<>();

        for (Nota nota : noteList) {
            NoteDetail dto = new NoteDetail();
            dto.setId(nota.getId());
            dto.setNumeStudent(nota.getStudent().getName());
            dto.setMaterie(nota.getMaterie().getName());
            dto.setClasa(nota.getCatalog().getClasa().getClassName());
            dto.setNota(nota.getNota());

            notaDetailsList.add(dto);
        }

        return ResponseEntity.ok(notaDetailsList);
    }

    @PostMapping("/student/{studentId}/{materieId}/{catalogId}/{val_nota}")
    public ResponseEntity<String> adaugaNota(
            @PathVariable Long studentId,
            @PathVariable Long materieId,
            @PathVariable Long catalogId,
            @PathVariable int val_nota
    ) {
        try {
            Optional<Student> studentOptional = studentRepository.findById(studentId);
            Optional<Materie> materieOptional = materieRepository.findById(materieId);
            Optional<Catalog> catalogOptional = catalogRepository.findById(catalogId);
            if (studentOptional.isPresent() && materieOptional.isPresent() && catalogOptional.isPresent()) {
                Student student = studentOptional.get();
                Materie materie = materieOptional.get();
                Catalog catalog = catalogOptional.get();

                Nota nota = new Nota();
                nota.setStudent(student);
                nota.setMaterie(materie);
                nota.setCatalog(catalog);
                nota.setNota(val_nota);

                notaRepository.save(nota);

                return ResponseEntity.ok("Nota a fost adăugată cu succes!");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Studentul, materia sau catalogul nu au fost găsite.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Eroare la adăugarea notei.");
        }
    }

    @PutMapping("/nota/{notaId}")
    public ResponseEntity<String> updateNota(@PathVariable Long notaId, @RequestBody int val_nota) {
        try {
            String message="test";
            Optional<Nota> existingNotaOptional = notaRepository.findById(notaId);
            if (existingNotaOptional.isPresent()) {
                Nota existingNota = existingNotaOptional.get();

                // Actualizează valoarea notei existente cu noua valoare
                existingNota.setNota(val_nota);

                // Salvează modificările în baza de date
                notaRepository.save(existingNota);

                return ResponseEntity.ok("Nota a fost actualizată cu succes!");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nota nu a fost găsită.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Eroare la actualizarea notei.");
        }
    }
    @DeleteMapping("/nota/{notaid}")
    public ResponseEntity<String> deleteNota(@PathVariable Long notaid){
        Nota nota=notaRepository.findById(notaid).orElse(null);
        if(nota!=null){
            notaRepository.deleteById(notaid);
            return ResponseEntity.ok("Nota a fost stearsa cu succes");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nota nu a fost gasita");
    }
}
