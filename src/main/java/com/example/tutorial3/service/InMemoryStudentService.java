package com.example.tutorial3.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.example.tutorial3.model.StudentModel;

public class InMemoryStudentService implements StudentService{
	private static List<StudentModel> studentList = new ArrayList<StudentModel>();
	
	@Override
	public StudentModel selectStudent(String npm) {
		
		Iterator<StudentModel> studentIterator = studentList.iterator();
		
		while(studentIterator.hasNext()) {
			StudentModel student = studentIterator.next();
			if(npm.equals(student.getNpm())) {
				return student;
			}
		}
		
		return null;
	}
	
	@Override
	public List<StudentModel> selectAllStudents(){
		return studentList;
	}
	
	@Override
	public void addStudent(StudentModel student) {
		studentList.add(student);
	}
	
	@Override
	public void deleteStudent(StudentModel student) {
		studentList.remove(student);
	}
}
