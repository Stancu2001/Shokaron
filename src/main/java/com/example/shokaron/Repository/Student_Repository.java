package com.example.shokaron.Repository;

import com.example.shokaron.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface Student_Repository extends JpaRepository<Student, Long> {
}
