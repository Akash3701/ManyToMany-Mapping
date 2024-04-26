package com.example.springbootproject;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/course")
public class CourseController {

	@Autowired
	private CourseRepo courseRepo;
	
	@PostMapping("/save")
	public Course savecourse(@RequestBody Course course) {
		return courseRepo.save(course);
	}
	
	@PutMapping("/{id}")
	public Course updatecourse(@PathVariable Integer id, @RequestBody Course course) {
		course.setId(id);
		return courseRepo.save(course);
	}
	
	@DeleteMapping("/{id}")
	public void deletecourse(@PathVariable Integer id) {
		courseRepo.deleteById(id);
	}
	
	@GetMapping("/{id}")
	public Optional<Course> getById(@PathVariable Integer id) {
		return courseRepo.findById(id);
	}
	
	@GetMapping("/page")
	public Page<Course> getByPagecourse(@RequestParam int page, @RequestParam int size) {
		Pageable pageable = PageRequest.of(page, size);
		return courseRepo.findAll(pageable);
	}
	
	@GetMapping("/sortby")
	public List<Course> getBySort(@RequestParam String sortBy) {
		return courseRepo.findAll(Sort.by(sortBy));
	}
}
