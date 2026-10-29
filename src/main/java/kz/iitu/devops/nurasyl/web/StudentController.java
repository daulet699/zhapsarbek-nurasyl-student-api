package kz.iitu.devops.nurasyl.web;

import java.util.List;
import jakarta.validation.Valid;
import kz.iitu.devops.nurasyl.model.Student;
import kz.iitu.devops.nurasyl.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student create(@Valid @RequestBody Student input) {
        return service.create(input);
    }

    @GetMapping
    public List<Student> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Student findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public Student update(@PathVariable Long id, @Valid @RequestBody Student input) {
        return service.update(id, input);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
