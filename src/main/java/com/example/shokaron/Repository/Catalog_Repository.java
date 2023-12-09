package com.example.shokaron.Repository;

import com.example.shokaron.Entity.Catalog;
import com.example.shokaron.Entity.Clasa;
import com.example.shokaron.Entity.Materie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface Catalog_Repository extends JpaRepository<Catalog, Long> {
    Catalog findByClasaId(Long Id);
}
