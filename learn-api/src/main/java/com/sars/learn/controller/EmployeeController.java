package com.sars.learn.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sars.learn.entity.Employee;
import com.sars.learn.form.EmployeeOnBoardForm;
import com.sars.learn.form.EmployeeSearchForm;
import com.sars.learn.service.EmployeeService;


@RestController
@RequestMapping("/employees")
@CrossOrigin(origins = "*")
public class EmployeeController {
	
	private EmployeeService employeeService;
	public  EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Page<Employee>> getAllEmployees(@RequestParam(name = "limit",defaultValue = "10") Integer limit,
			@RequestParam(defaultValue = "0" , name = "page")Integer pageNumber){
		return ResponseEntity.ok(employeeService.getAllEmployees(PageRequest.of(pageNumber, limit)));
	}
	
	@GetMapping("/{employeeId}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Employee> getEmployee(@PathVariable(value = "employeeId") Long employeeId){
		return ResponseEntity.ok(employeeService.getEmployee(employeeId));
	}
	
	@PostMapping("/search")
	@ResponseStatus(HttpStatus.OK)
	public Page<Employee> getAllEmployeesForCriteria(@RequestBody EmployeeSearchForm employeeSearchForm){
		return employeeService.getAllEmployeeForSearchCriteria(employeeSearchForm);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Employee createEmployee( @Validated @RequestBody EmployeeOnBoardForm employeeOnBoardForm) {
		return employeeService.createEmployee(employeeOnBoardForm);
	}
	
	@PutMapping("/{employeeId}")
	@ResponseStatus(HttpStatus.OK)
	public void updateEmployee(@Validated @RequestBody EmployeeOnBoardForm employeeOnBoardForm,@PathVariable("employeeId") Long employeeId) {
		 employeeService.updateEmployee(employeeId,employeeOnBoardForm);
	}
	
	@DeleteMapping("/{employeeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteEmployee(@PathVariable("employeeId") Long employeeId) {
		employeeService.deleteEmployee(employeeId);
	}
}
