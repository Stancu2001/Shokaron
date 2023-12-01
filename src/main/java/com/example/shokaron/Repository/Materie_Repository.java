package com.example.shokaron.Repository;

import com.example.shokaron.Entity.Materie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface Materie_Repository extends JpaRepository<Materie, Long> {
}
