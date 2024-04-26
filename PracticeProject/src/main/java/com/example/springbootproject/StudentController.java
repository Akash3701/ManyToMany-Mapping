package com.example.springbootproject;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentRepo studentRepo;
	
	@PostMapping("/save")
	public Student saveStudent(@RequestBody Student student) {
		return studentRepo.save(student);
	}
	
	@PutMapping("/{id}")
	public Student updateStudent(@PathVariable Integer id, @RequestBody Student student) {
		student.setId(id);
		return studentRepo.save(student);
	}
	
	@DeleteMapping("/{id}")
	public void deleteStudent(@PathVariable Integer id) {
		studentRepo.deleteById(id);
	}
	
	@GetMapping("/{id}")
	public Optional<Student> getById(@PathVariable Integer id) {
		return studentRepo.findById(id);
	}
	
	@GetMapping("/page")
	public Page<Student> getByPageStudent(@RequestParam int page, @RequestParam int size) {
		Pageable pageable = PageRequest.of(page, size);
		return studentRepo.findAll(pageable);
	}
	
	@GetMapping("/sortby")
	public List<Student> getBySort(@RequestParam String sortBy) {
		return studentRepo.findAll(Sort.by(sortBy));
	}
}
