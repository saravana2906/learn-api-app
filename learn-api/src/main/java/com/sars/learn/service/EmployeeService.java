package com.sars.learn.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.NullHandling;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.sars.learn.entity.Employee;
import com.sars.learn.entity.PhoneNumber;
import com.sars.learn.form.EmployeeOnBoardForm;
import com.sars.learn.form.EmployeeSearchForm;
import com.sars.learn.form.SortBy.SortOrder;
import com.sars.learn.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	private EmployeeRepository employeeRepository;

	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public Page<Employee> getAllEmployees(PageRequest pageRequest){
		
		return employeeRepository.findAll(pageRequest);	
	}

	public Employee getEmployee(Long employeeId) {
		
		return employeeRepository.findById(employeeId).get();
	}
	
	public Page<Employee> getAllEmployeeForSearchCriteria(EmployeeSearchForm employeeSearchForm){
		PhoneNumber phoneNumber = PhoneNumber.builder().countryCode(employeeSearchForm.getCountryCode())
									.phoneNumber(employeeSearchForm.getPhoneNumber()).build();
		Employee employee = Employee.builder()
									.firstName(employeeSearchForm.getFirstName())
									.lastName(employeeSearchForm.getLastName())
									.contactNumber(phoneNumber)
									.designation(employeeSearchForm.getDesignation())
									.emailId(employeeSearchForm.getEmailId()).build();
	ExampleMatcher matcherCondition = ExampleMatcher.matching().withIgnorePaths("employeeId").withIgnoreCase()
	.withMatcher("firstName", GenericPropertyMatcher.of(StringMatcher.CONTAINING))
	.withMatcher("lastName", GenericPropertyMatcher.of(StringMatcher.CONTAINING))
	.withMatcher("contactNumber.phoneNumber", GenericPropertyMatcher.of(StringMatcher.CONTAINING))
	.withMatcher("contactNumber.countryCode", GenericPropertyMatcher.of(StringMatcher.CONTAINING))
	.withMatcher("emailId", GenericPropertyMatcher.of(StringMatcher.CONTAINING));
	Example<Employee> probe =  Example.of(employee,matcherCondition);
	List<Order> orders = employeeSearchForm.getSortByFields().stream().map(sortByField -> {
		return new Order(sortByField.getOrder()==SortOrder.ASCENDING ? Direction.ASC : Direction.DESC,
				                sortByField.getFieldName(),NullHandling.NULLS_LAST);
	}).collect(Collectors.toList());
	PageRequest pageRequest = null;
	if(!orders.isEmpty())
		pageRequest=PageRequest.of(employeeSearchForm.getPage(), employeeSearchForm.getLimit(), Sort.by(orders));
	else 
		pageRequest = PageRequest.of(employeeSearchForm.getPage(), employeeSearchForm.getLimit());
	return employeeRepository.findAll(probe, pageRequest);	
	}

	public Employee createEmployee(EmployeeOnBoardForm employeeOnBoardForm) {
		Employee employee = Employee.builder()
				            .firstName(employeeOnBoardForm.getFirstName())
				            .lastName(employeeOnBoardForm.getLastName())
				            .emailId(employeeOnBoardForm.getEmailId())
				            .contactNumber(employeeOnBoardForm.getContactNumber())
				            .designation(employeeOnBoardForm.getDesignation())
				            .joinedDate(employeeOnBoardForm.getJoinedDate()).build();
		
		return employeeRepository.save(employee);
	}

	public void updateEmployee(Long employeeId, EmployeeOnBoardForm employeeOnBoardForm) {
		Employee employee = Employee.builder()
				.employeeId(employeeId)
	            .firstName(employeeOnBoardForm.getFirstName())
	            .lastName(employeeOnBoardForm.getLastName())
	            .emailId(employeeOnBoardForm.getEmailId())
	            .contactNumber(employeeOnBoardForm.getContactNumber())
	            .designation(employeeOnBoardForm.getDesignation())
	            .joinedDate(employeeOnBoardForm.getJoinedDate()).build();
		 employeeRepository.save(employee);
	}

	public void deleteEmployee(Long employeeId) {
		employeeRepository.deleteById(employeeId);	
	}


}
