package kz.iitu.devops.nurasyl.service;

import java.util.List;
import kz.iitu.devops.nurasyl.model.Student;
import kz.iitu.devops.nurasyl.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class StudentService {
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public Student create(Student input) {
        input.setId(null);
        return repository.save(input);
    }

    public List<Student> findAll() {
        return repository.findAll();
    }

    public Student findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Student " + id + " not found"));
    }

    public Student update(Long id, Student incoming) {
        Student student = findById(id);
        student.setFullName(incoming.getFullName());
        student.setEmail(incoming.getEmail());
        student.setGroupName(incoming.getGroupName());
        student.setStudentNumber(incoming.getStudentNumber());
        return repository.save(student);
    }

    public void delete(Long id) {
        Student student = findById(id);
        repository.delete(student);
    }
}
