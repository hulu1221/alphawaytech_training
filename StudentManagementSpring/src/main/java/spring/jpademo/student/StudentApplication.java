package spring.jpademo.student;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import spring.jpademo.student.repository.StudentRepository;
import spring.jpademo.student.services.StudentController;

@SpringBootApplication
@RequiredArgsConstructor
public class StudentApplication {

    public static void main(String[] args) {
//        SpringApplication.run(StudentApplication.class, args);
        ApplicationContext context = SpringApplication.run(StudentApplication.class, args);
        StudentRepository studentRepository = context.getBean(StudentRepository.class);
        StudentController studentController = new StudentController(studentRepository);
    }

}
