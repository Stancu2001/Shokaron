package com.example.shokaron.Repository;

import com.example.shokaron.Entity.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface Nota_Repository extends JpaRepository<Nota,Long> {

}
