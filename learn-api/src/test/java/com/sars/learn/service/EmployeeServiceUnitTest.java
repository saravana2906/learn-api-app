package com.sars.learn.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.sars.learn.entity.Employee;
import com.sars.learn.entity.PhoneNumber;
import com.sars.learn.form.EmployeeOnBoardForm;
import com.sars.learn.repository.EmployeeRepository;
import com.sars.learn.util.Designation;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class EmployeeServiceUnitTest {
	
	@Mock
	private EmployeeRepository employeeRepository;
	
	@InjectMocks
	private EmployeeService employeeService;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}
	
	
	@Test
	public void testCreateEmployee() {
		EmployeeOnBoardForm employeeOnBoardForm = new EmployeeOnBoardForm();
		employeeOnBoardForm.setFirstName("Naruto");
		employeeOnBoardForm.setLastName("Uzumaki");
		employeeOnBoardForm.setEmailId("naruto@gmail.com");
		employeeOnBoardForm.setDesignation(Designation.Senior_Developer);
		employeeOnBoardForm.setContactNumber(PhoneNumber.builder()
				.countryCode("+91")
				.phoneNumber("87910345678").build());
		employeeOnBoardForm.setJoinedDate(LocalDate.of(2022, 1, 31));
		
		Employee employee = Employee.builder()
							.employeeId(22L)
							.firstName("Naruto")
							.lastName("Uzumaki")
							.emailId("naruto@gmail.com")
							.designation(Designation.Senior_Developer)
							.contactNumber(
							PhoneNumber.builder()
							.countryCode("+91")
							.phoneNumber("87910345678").build())
							.joinedDate(LocalDate.of(2022, 1, 31)).build();
		when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
		Employee savedEmployee = employeeService.createEmployee(employeeOnBoardForm);
		
		assert(savedEmployee.getEmployeeId()!=0);
		assertEquals("Naruto",employee.getFirstName());
	}

}
