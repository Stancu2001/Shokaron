package com.example.shokaron.controller;

import com.example.shokaron.Entity.Catalog;
import com.example.shokaron.Entity.Materie;
import com.example.shokaron.Repository.Catalog_Repository;
import com.example.shokaron.Repository.Materie_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/catalog")
public class Controller_Catalog {
    private final Catalog_Repository catalogRepository;
    private final Materie_Repository materieRepository;

    @Autowired
    public Controller_Catalog(Catalog_Repository catalogRepository, Materie_Repository materieRepository) {
        this.catalogRepository = catalogRepository;
        this.materieRepository = materieRepository;
    }

//    @PostMapping("/addmaterie/{catalogId}/{materieId}")
//    public ResponseEntity<String> addMaterieToCatalog(@PathVariable Long catalogId, @PathVariable Long materieId) {
//        Catalog catalog = catalogRepository.findById(catalogId).orElse(null);
//        Materie materie = materieRepository.findById(materieId).orElse(null);
//        if(catalog!=null && catalog.getMaterii().contains(materie)){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Materia exista in catalog");
//        }
//        if (catalog != null && materie != null) {
//            catalog.getMaterii().add(materie);
//            catalogRepository.save(catalog);
//            return ResponseEntity.ok("Materie adăugată cu succes la catalog");
//        }
//
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Catalog sau materie nu a fost găsită");
//    }

    @PostMapping("/addmaterie")
    public ResponseEntity<String> addMaterieToCatalog(@RequestBody Map<String, Long> request) {
        Long catalogId = request.get("catalogId");
        Long materieId = request.get("materieId");

        Catalog catalog = catalogRepository.findById(catalogId).orElse(null);
        Materie materie = materieRepository.findById(materieId).orElse(null);
        if(catalog!=null && catalog.getMaterii().contains(materie)){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Materia exista in catalog");
        }

        if (catalog != null && materie != null) {
            catalog.getMaterii().add(materie);
            catalogRepository.save(catalog);
            return ResponseEntity.ok("Materie adăugată cu succes la catalog");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Catalog sau materie nu a fost găsită");
    }


    @DeleteMapping("/removematerie/{catalogId}/{materieId}")
    public ResponseEntity<String> removeMaterieFromCatalog(@PathVariable Long catalogId, @PathVariable Long materieId) {
        Catalog catalog = catalogRepository.findById(catalogId).orElse(null);
        Materie materie = materieRepository.findById(materieId).orElse(null);

        if (catalog != null && materie != null) {
            if (catalog.getMaterii().contains(materie)) {
                catalog.getMaterii().remove(materie);
                catalogRepository.save(catalog);
                return ResponseEntity.ok("Materie ștearsă cu succes din catalog");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Materia nu este asociată acestui catalog");
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Catalog sau materie nu a fost găsită");
    }
}
