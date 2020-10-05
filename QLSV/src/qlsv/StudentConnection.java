package qlsv;

import com.google.gson.Gson;

import java.util.List;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class StudentConnection {
	private String file_name;
	private static FileWriter file;
	
    public StudentConnection() {
    }
    public StudentConnection(String file_name) {
    	super();
    	this.file_name = file_name;
    }
    
    public String getFilename() {
    	return file_name;
    }
    public void setFilename(String file_name) {
    	this.file_name = file_name;
    }
    
	public void insert(List<Student> studentList) {
        try {
        	 
            // Constructs a FileWriter given a file name, using the platform's default charset
            file = new FileWriter(this.file_name);
            String json = new Gson().toJson(studentList);
            file.write(json); 
        } catch (IOException e) {
            e.printStackTrace();
 
        } finally {
 
            try {
                file.flush();
                file.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
	}
	
	public List<Student> selectAll() throws FileNotFoundException {
        List<Student> studentList = new ArrayList<>();
        String json = "";
        try {
            FileReader reader = new FileReader(this.file_name);
            int character;
            
            while ((character = reader.read()) != -1) {                
            	json+= (char) character;
            }
            reader.close();
//            System.out.println(json);
            Gson gson = new Gson();             
            Student[] arr = gson.fromJson(json, Student[].class);  
            for(Student student : arr) {
            	studentList.add(student);
            }            

        } catch (NullPointerException e) {
//        	e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(studentList);
        return studentList;
	}
}
