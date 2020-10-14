package spring.jpademo.student.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "students")
public class Student implements Serializable {
    private static final long serialVersionUID = -297553281792804396L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Mapping thông tin biến với tên cột trong Database
    @Column(name = "name")
    private String name;

    // Nếu không đánh dấu @Column thì sẽ mapping tự động theo tên biến
    private int age;

    private String address;

    private float gpa;

}
