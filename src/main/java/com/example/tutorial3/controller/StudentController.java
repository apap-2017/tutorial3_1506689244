package com.example.tutorial3.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.tutorial3.service.InMemoryStudentService;
import com.example.tutorial3.service.StudentService;
import com.example.tutorial3.model.StudentModel;

@Controller
public class StudentController {
	private final StudentService studentService;
	
	public StudentController() {
		studentService = new InMemoryStudentService();
	}
	
	@RequestMapping("/student/add")
	public String add(@RequestParam(value = "npm", required = true) String npm,
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "gpa", required = true) double gpa) {
		
		StudentModel student = new StudentModel(npm, name, gpa);
		studentService.addStudent(student);
		return "add";
	}	
	
	@RequestMapping({"/student/delete", "/student/delete/{npm}"})
	public String delete(@PathVariable Optional<String> npm, Model model) {
		
		if(npm.isPresent()) {
			StudentModel student = studentService.selectStudent(npm.get());
			model.addAttribute("npm", npm.get());
			
			if(student == null) {
				return "notfound";
			} else {
				studentService.deleteStudent(student);
				return "delete";
			}
		} else {
			return "blankinput";
		}
	}	
	
	@RequestMapping({"/student/view", "/student/view/{npm}"})
	public String viewPath(@PathVariable Optional<String> npm, Model model) {
		
		if(npm.isPresent()) {
			StudentModel student = studentService.selectStudent(npm.get());
			
			if(student == null) {
				model.addAttribute("npm", npm.get());
				return "notfound";
			} else {
				model.addAttribute("student", student);
				return "view";
			}
		} else {
			return "blankinput";
		}
	}	
	
//	@RequestMapping("/student/view")
//	public String view(@RequestParam(value = "npm", required = true) String npm, Model model) {
//		StudentModel student = studentService.selectStudent(npm);
//		model.addAttribute("student", student);
//		return "view";
//	}
	
	@RequestMapping("/student/viewall")
	public String view(Model model) {
		List<StudentModel> students = studentService.selectAllStudents();
		model.addAttribute("students", students);
		return "viewall";
	}
}
