package com.example.shokaron.controller;

import com.example.shokaron.DTO.NoteDetailDto;
import com.example.shokaron.Entity.Catalog;
import com.example.shokaron.Entity.Materie;
import com.example.shokaron.Entity.Nota;
import com.example.shokaron.Entity.Student;
import com.example.shokaron.Repository.Catalog_Repository;
import com.example.shokaron.Repository.Materie_Repository;
import com.example.shokaron.Repository.Nota_Repository;
import com.example.shokaron.Repository.Student_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/nota")

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

    @GetMapping("/all")
    public ResponseEntity<List<NoteDetailDto>> getAllNotesWithDetails() {
        List<Nota> noteList = notaRepository.findAll();
        if (noteList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<NoteDetailDto> notaDetailsList = new ArrayList<>();

        for (Nota nota : noteList) {
            NoteDetailDto dto = new NoteDetailDto();
            dto.setId(nota.getId());
            dto.setNumeStudent(nota.getStudent().getNume()+" "+nota.getStudent().getPrenume());
            dto.setMaterie(nota.getMaterie().getName());
            dto.setClasa(nota.getCatalog().getClasa().getClassName());
            dto.setNota(nota.getNota());
            notaDetailsList.add(dto);
        }

        return ResponseEntity.ok(notaDetailsList);
    }

    @PostMapping("/{studentId}/{materieId}/{catalogId}")
    public ResponseEntity<String> adaugaNota(
            @PathVariable Long studentId,
            @PathVariable Long materieId,
            @PathVariable Long catalogId,
            @RequestBody int val_nota
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

    @PutMapping("/{notaId}")
    public ResponseEntity<String> updateNota(@PathVariable Long notaId, @RequestBody int val_nota) {
        try {
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
    @DeleteMapping("/{notaId}")
    public ResponseEntity<String> deleteNota(@PathVariable Long notaId){
        Nota nota=notaRepository.findById(notaId).orElse(null);
        if(nota!=null){
            notaRepository.deleteById(notaId);
            return ResponseEntity.ok("Nota a fost stearsa cu succes");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nota nu a fost gasita");
    }
}
