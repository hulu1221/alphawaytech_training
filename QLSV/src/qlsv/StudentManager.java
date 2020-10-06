package qlsv;

import java.util.Collections;
import java.util.List;
import java.util.Comparator;
import java.util.Scanner;

public class StudentManager {
	private static Scanner keyboard = new Scanner(System.in);
	private List<Student> studentList;
//    private StudentConnection connect;
    private Students students;
    public StudentManager(Students students) {
    	this.students = students;
//    	studentList = students.selectAll();
    }
  
    public void add() {
    	studentList = students.selectAll();
    	int id = (studentList.size() > 0) ? (studentList.size() + 1) : 1;
    	System.out.println("new student id = " + id);
        String name = inputName();
        byte age = inputAge();
        String address = inputAddress();
        float gpa = inputGpa();
        Student student = new Student(id, name, age, address, gpa);
//        studentList.add(student);
        int i = students.insert(student);
        if (i > 0) {
        	System.out.println("Success!");
        } else {
        	System.out.println("Fail!");
        }
    }
    
    public void edit(int id) {
        Student stud = new Student();
        stud.setId(id);
        stud.setName(inputName());
        stud.setAge(inputAge());
        stud.setAddress(inputAddress());
        stud.setGpa(inputGpa());
        int i = students.updateById(stud);
        if (i > 0) {
        	System.out.println("Success!");
        } else {
        	System.out.println("Fail!");
        }
    }

    public void delete(int id) {
        boolean deleted = students.deleteById(id);
        if (deleted) {
        	System.out.println("Success!");
        } else {
        	System.out.println("Fail!");
        }
    }
    
    public int inputId() {
        System.out.print("Input student id: ");
        while (true) {
            try {
                int id = Integer.parseInt((keyboard.nextLine()));
                return id;
            } catch (NumberFormatException ex) {
                System.out.print("invalid! Input student id again: ");
            }
        }
    } 
    
    private String inputName() {
        System.out.print("Input student name: ");
        return keyboard.nextLine();
    }
    private byte inputAge() {
        System.out.print("Input student age: ");
        while (true) {
            try {
                byte age = Byte.parseByte((keyboard.nextLine()));
                if (age < 0 && age > 100) {
                    throw new NumberFormatException();
                }
                return age;
            } catch (NumberFormatException ex) {
                System.out.print("invalid! Input student id again: ");
            }
        }
    }
    private String inputAddress() {
        System.out.print("Input student address: ");
        return keyboard.nextLine();
    }
    private float inputGpa() {
        System.out.print("Input student gpa: ");
        while (true) {
            try {
                float gpa = Float.parseFloat((keyboard.nextLine()));
                if (gpa < 0.0 && gpa > 10.0) {
                    throw new NumberFormatException();
                }
                return gpa;
            } catch (NumberFormatException ex) {
                System.out.print("invalid! Input student gpa again: ");
            }
        }
    }
    
    public void show() {
    	studentList = students.selectAll();
        System.out.println("Number of students: " + studentList.size());
        for (Student student : studentList) {
            System.out.format("%5d | ", student.getId());
            System.out.format("%20s | ", student.getName());
            System.out.format("%5d | ", student.getAge());
            System.out.format("%20s | ", student.getAddress());
            System.out.format("%10.1f%n", student.getGpa());
        }
    }
    public void SortByName() {
    	studentList = students.selectAll();
        Collections.sort(studentList, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        show();
    }

    public void SortByGpa() {
    	studentList = students.selectAll();
        Collections.sort(studentList, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getGpa() > o2.getGpa() ? 1 : -1;
            }
        });
        show();
    }
    
}
