package com.sars.learn.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.sars.learn.util.Designation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "employeeSequence")
	@SequenceGenerator(name = "employeeSequence",allocationSize = 5,sequenceName = "seq_employee")
	private Long employeeId;

	@Column(nullable = false)
	private String firstName;
	
	private String lastName;
	
	@Column(unique = true,nullable = false)
	private String emailId;
	
	@Embedded
	private PhoneNumber contactNumber;
	
	@Enumerated(EnumType.ORDINAL)
	private Designation designation;
	
	@CreationTimestamp
	private LocalDateTime createdDate;
	
	@UpdateTimestamp
	private LocalDateTime updatedDate;
	
	private LocalDate joinedDate;
	

}
