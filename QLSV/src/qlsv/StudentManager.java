package qlsv;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;
import java.util.Scanner;

public class StudentManager {
	public static Scanner keyboard = new Scanner(System.in);
    public List<Student> studentList;
    
    public StudentManager() {
    	studentList = new ArrayList<>();
    }
    
    public void add() {
    	int id = (studentList.size() > 0) ? (studentList.size() + 1) : 1;
//    	int id = 1;
    	System.out.println("new student id = " + id);
        String name = inputName();
        byte age = inputAge();
        String address = inputAddress();
        float gpa = inputGpa();
        Student student = new Student(id, name, age, address, gpa);
        studentList.add(student);
    }
    
    public void edit(int id) {
        boolean isExisted = false;
        int size = studentList.size();
        for (int i = 0; i < size; i++) {
            if (studentList.get(i).getId() == id) {
                isExisted = true;
                studentList.get(i).setName(inputName());
                studentList.get(i).setAge(inputAge());
                studentList.get(i).setAddress(inputAddress());
                studentList.get(i).setGpa(inputGpa());
                break;
            }
        }
        if (!isExisted) {
            System.out.printf("id = %d not existed.\n", id);
        } else {
            // write code to save data if it had other saving location
        }
    }

    public void delete(int id) {
        boolean isExisted = false;
        int size = studentList.size();
        for (int i = 0; i < size; i++) {
            if (studentList.get(i).getId() == id) {
                isExisted = true;
                studentList.remove(i);
                break;
            }
        }
        if (!isExisted) {
            System.out.printf("id = %d not existed.\n", id);
        } else {
            // write code to save data if it had other saving location
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
                System.out.print("invalid! Input student age again: ");
            }
        }
    }
    
    public void show() {
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
        Collections.sort(studentList, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        show();
    }

    public void SortByGpa() {
        Collections.sort(studentList, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getGpa() > o2.getGpa() ? 1 : -1;
            }
        });
        show();
    }
    
}