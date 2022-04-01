package com.sars.learn.form;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.sars.learn.entity.PhoneNumber;
import com.sars.learn.util.Designation;

import lombok.Data;

@Data
public class EmployeeOnBoardForm {
	

	@NotNull(message = "Please Fill First Name")
	@NotBlank(message = "First Name is Empty")
	private String firstName;
	
	@NotNull(message = "Please Fill First Name")
	@NotBlank(message = "First Name is Empty")
	private String lastName;
	
	@Email
	@NotNull
	@NotBlank
	private String emailId;
	
	@NotNull
	private PhoneNumber contactNumber;
	
	@NotNull
	private Designation designation;
	
	@NotNull
	LocalDate joinedDate;
}
