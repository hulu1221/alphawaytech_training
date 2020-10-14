package spring.jpademo.student.services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.jpademo.student.domain.Student;
import spring.jpademo.student.repository.StudentRepository;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class StudentController {

    private StudentRepository studentRepository;
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    @GetMapping("/student")
    public List<Student> getStudentList() {
        System.out.println(studentRepository);
        return studentRepository.findAll();
    }

    @PostMapping("/student")
    public ResponseEntity addStudent(@RequestBody Student student) {
        studentRepository.save(student);
        return ResponseEntity.ok().body(student);
    }

    @GetMapping("/student/{studentId}")
    public Student getStudent(@PathVariable(name = "studentId") Integer studentId) {
        return studentRepository.findStudentById(studentId);
    }

    @PutMapping("/student/{studentId}")
    public Student editStudent(@PathVariable(name = "studentId") Integer studentId,
                               @RequestBody Student student) {
        student.setId(studentId);
        studentRepository.save(student);
        return student;
    }

    @DeleteMapping("student/{studentId}")
    public ResponseEntity deleteStudent(@PathVariable(name = "studentId") Integer studentId) {
        studentRepository.deleteById(studentId);
        return ResponseEntity.ok().build();
    }

}
