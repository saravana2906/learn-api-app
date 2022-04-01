package com.sars.learn.form;

import java.util.List;

import com.sars.learn.util.Designation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeSearchForm {
	private String firstName;
	private String lastName;
	private String countryCode;
	private String phoneNumber;
	private String emailId;
	private Designation designation;
	private List<SortBy> sortByFields;
	private Integer limit;
	private Integer page;
	}
