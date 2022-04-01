package com.sars.learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sars.learn.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
