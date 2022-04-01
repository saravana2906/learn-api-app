package com.sars.learn;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sars.learn.entity.Employee;
import com.sars.learn.entity.PhoneNumber;
import com.sars.learn.repository.EmployeeRepository;
import com.sars.learn.util.Designation;

@SpringBootApplication
public class LearnSpringBootApplication implements CommandLineRunner {

	private EmployeeRepository employeeRepository;
	public LearnSpringBootApplication(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	public static void main(String[] args) {
		SpringApplication.run(LearnSpringBootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Employee employee1 =Employee.builder()
				.firstName("Saravana Perumal")
				.lastName("D")
				.contactNumber(PhoneNumber.builder().countryCode("+91").phoneNumber("9789372811").build())
				.designation(Designation.Intern)
				.emailId("saravana@gmail.com")
				.build();
		Employee employee2 = Employee.builder()
				.firstName("Siva Sankar")
				.lastName("D")
				.contactNumber(PhoneNumber.builder().countryCode("+91").phoneNumber("9486392811").build())
				.designation(Designation.Intern)
				.emailId("siva@gmail.com")
				.build();
		Employee employee3 = Employee.builder()
				.firstName("Dharaninathan")
				.lastName("S")
				.contactNumber(PhoneNumber.builder().countryCode("+91").phoneNumber("9442408683").build())
				.designation(Designation.Intern)
				.emailId("dharani@gmail.com")
				.build();
		
		employeeRepository.save(employee1);
		employeeRepository.save(employee2);
		employeeRepository.save(employee3);
		
		
	}

}
