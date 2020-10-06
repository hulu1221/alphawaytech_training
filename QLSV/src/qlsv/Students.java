package qlsv;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Students {
	private Connection conn;
	Statement stmt = null;
	
	public Students() {
    }
	
    public Students(Connection conn) {
        super();
        this.conn = conn;
    }
	public List<Student> selectAll() {
		List<Student> studentList = new ArrayList<>();
		try {
			stmt = conn.createStatement();
			String sql = "SELECT * FROM STUDENTS";			
			ResultSet rs = stmt.executeQuery(sql);
            // add data to list
            while (rs.next()) {
                Student stud = returnStudent(rs);
                studentList.add(stud);
            }   
		}catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }
		return studentList;		
	}
	
	public Student selectById(int id) {
		Student stud = new Student();		
		// check exist student id
		try {			
			PreparedStatement stmtCheck = conn.prepareStatement("SELECT * FROM STUDENTS WHERE id = (?)");  
			stmtCheck.setInt(1, id);
			ResultSet rs = stmtCheck.executeQuery(); 
			// if result have no record then return false
			if (rs.next()) {
				// select student by id
				stud = returnStudent(rs);
			}
		} catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   } 
		return stud;
	}
	
	public Integer updateById(Student stud) {		
		// check exist student id
		int i = 0;
		try {			
			PreparedStatement stmtCheck = conn.prepareStatement("SELECT * FROM STUDENTS WHERE id = (?)");  
			stmtCheck.setInt(1, stud.getId());
			int rs = stmtCheck.executeUpdate(); 
			// if result have no record then return false
			if (rs > 0) {
				// select student by id
				String sql = "UPDATE STUDENTS SET name =(?), age = (?), address = (?), gpa = (?)";
				PreparedStatement stmtUpdate = conn.prepareStatement(sql + "WHERE id = (?)");  
				stmtUpdate.setString(1, stud.getName());
				stmtUpdate.setInt(2, stud.getAge());
				stmtUpdate.setString(3, stud.getAddress());
				stmtUpdate.setFloat(4, stud.getGpa());
				stmtUpdate.setInt(5, stud.getId());
				i = stmtUpdate.executeUpdate();  
			}
		} catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   } 
		return i;
	}
	
	public Integer updateRowNewId(int oldId, int newId) {		
		// check exist student id
		int i = 0;
		try {			
			PreparedStatement stmtCheck = conn.prepareStatement("SELECT * FROM STUDENTS WHERE id = (?)");  
			stmtCheck.setInt(1, oldId);
			ResultSet rs = stmtCheck.executeQuery(); 
			// if result have no record then return false
			if (rs.next()) {
				// select student by id
				String sql = "UPDATE STUDENTS SET id =(?)";
				PreparedStatement stmtUpdate = conn.prepareStatement(sql + "WHERE id = (?)");  
				stmtUpdate.setInt(1, newId);
				stmtUpdate.setInt(2, oldId);
				i = stmtUpdate.executeUpdate();  
			}
		} catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   } 
		return i;
	}
	
	public Integer insert(Student stud) {		
		// check exist student id
		int i = 0;
		try {			
				// set student information
				String sql = "INSERT INTO STUDENTS (id, name, age, address, gpa) VALUES (?,?,?,?,?)";
				PreparedStatement stmtUpdate = conn.prepareStatement(sql);  
				stmtUpdate.setInt(1, stud.getId());
				stmtUpdate.setString(2, stud.getName());
				stmtUpdate.setInt(3, stud.getAge());
				stmtUpdate.setString(4, stud.getAddress());
				stmtUpdate.setFloat(5, stud.getGpa());
				i = stmtUpdate.executeUpdate();  
		} catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   } 
		return i;
	}
	
	public boolean deleteById(int id) {		
		// check exist student id
		boolean deleted = false;
		try {			
			PreparedStatement stmtCheck = conn.prepareStatement("DELETE FROM STUDENTS WHERE id = (?)");  
			stmtCheck.setInt(1, id);
			int rs = stmtCheck.executeUpdate(); 
			if (rs > 0) {
				// if deleted then update all student id
				int i = 1;
		    	for (Student student : selectAll()) {
		    		updateRowNewId(student.getId(), i);
		    		i++;
		        }
		    	return true;
			}
			
		} catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   } 
		return deleted;
	}
	
	private Student returnStudent(ResultSet rs) {
		Student stud = new Student();
		try {
			stud.setId(rs.getInt("id"));
			stud.setName(rs.getString("name"));
			stud.setAge(Byte.parseByte(rs.getString("age")));
			stud.setAddress(rs.getString("address"));
			stud.setGpa(Float.parseFloat(rs.getString("gpa")));
		} catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }
		return stud;        
	}
	
}
