package com.example.shokaron.Repository;

import com.example.shokaron.Entity.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface Profesor_Repository extends JpaRepository<Profesor, Long> {
}
