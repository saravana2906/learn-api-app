package com.sars.learn.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.sars.learn.entity.Employee;
import com.sars.learn.entity.PhoneNumber;
import com.sars.learn.form.EmployeeOnBoardForm;
import com.sars.learn.util.Designation;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class EmployeeServiceIntegrationTest {
	
	@Autowired
	EmployeeService employeeService;
	
	@Test
	public void given_EmployeeOnBoardForm_Then_Return_SavedEmployeewithEmployeeId() {
		EmployeeOnBoardForm employeeOnBoardForm = new EmployeeOnBoardForm();
		employeeOnBoardForm.setFirstName("Naruto");
		employeeOnBoardForm.setLastName("Uzumaki");
		employeeOnBoardForm.setEmailId("naruto@gmail.com");
		employeeOnBoardForm.setDesignation(Designation.Senior_Developer);
		employeeOnBoardForm.setContactNumber(PhoneNumber.builder()
				.countryCode("+91")
				.phoneNumber("87910345678").build());
		employeeOnBoardForm.setJoinedDate(LocalDate.of(2022, 1, 31));
		//Create Employee
		Employee employee = employeeService.createEmployee(employeeOnBoardForm);
		assert(employee.getEmployeeId()!=0);
		assertEquals("Naruto", employeeOnBoardForm.getFirstName());
		employeeOnBoardForm.setDesignation(Designation.CEO);
		// Update Employee 
		employeeService.updateEmployee(employee.getEmployeeId(), employeeOnBoardForm);
		// Get Employee by Employee Id
		Employee updatedEmployee = employeeService.getEmployee(employee.getEmployeeId());
		assertEquals(Designation.CEO, updatedEmployee.getDesignation());
		// Delete Employee by Employee Id
		employeeService.deleteEmployee(employee.getEmployeeId());
		assertThrows(NoSuchElementException.class, ()->{employeeService.getEmployee(employee.getEmployeeId());});
		
	}
	
	@Test
	public void given_EmployeeListAvailable_When_GetAllEmployee_Then_Return_EmployeeList() {
		PageRequest pageRequest = PageRequest.of(0, 10);
		Page<Employee> allEmployees = employeeService.getAllEmployees(pageRequest);
		assert(allEmployees.getContent().size()>0);
	}
	

}
