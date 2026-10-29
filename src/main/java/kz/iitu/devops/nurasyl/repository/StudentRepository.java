package kz.iitu.devops.nurasyl.repository;

import kz.iitu.devops.nurasyl.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {}
