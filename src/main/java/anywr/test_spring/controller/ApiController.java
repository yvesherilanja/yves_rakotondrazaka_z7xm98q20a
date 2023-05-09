package anywr.test_spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import anywr.test_spring.model.Student;
import anywr.test_spring.pagination.Paged;
import anywr.test_spring.service.StudentService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
@RestController
public class ApiController {

	@Autowired
	private StudentService ss;
	
	@GetMapping("/students")
	public ResponseEntity<?> all_students(
			@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size,
            @RequestParam(value = "class_name", required = false, defaultValue = "") String class_name,
            @RequestParam(value = "teacher_name", required = false, defaultValue = "") String teacher_name) {
		
			Paged<Student> all_students = ss.getAllStudentFilter(class_name, teacher_name, pageNumber, size);
		    return ResponseEntity.ok(all_students.getPage().getContent());
		
	  }
	
}
