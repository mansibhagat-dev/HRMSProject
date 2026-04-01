package com.example.college.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.college.entity.Employee;
import com.example.college.repository.EmployeeRepository;

@Service 
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee saveEmployee(Employee employee) {
        if (employeeRepository.existsByEmail(employee.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public Employee updateEmployee(Long id, Employee employee) {

        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        if (employeeRepository.existsByEmailAndIdNot(employee.getEmail(), id)) {
            throw new RuntimeException("Email already in use");
        }

        existing.setName(employee.getName());
        existing.setEmail(employee.getEmail());
        existing.setPhone(employee.getPhone());
        existing.setDesignation(employee.getDesignation());
        existing.setSalary(employee.getSalary());
        existing.setJoiningDate(employee.getJoiningDate());
        existing.setDepartment(employee.getDepartment());
        existing.setStatus(employee.getStatus());

        return employeeRepository.save(existing);
    }

    public void deleteEmployee(Long id) {

        if (!employeeRepository.existsById(id)) {
            throw new RuntimeException("Employee not found");
        }

        employeeRepository.deleteById(id);
    }

    public List<Employee> searchEmployees(String keyword) {

        if (keyword == null || keyword.trim().isEmpty()) {
            return employeeRepository.findAll();
        }

        return employeeRepository.searchByNameOrDepartment(keyword);
    }

    public long countEmployees() {
        return employeeRepository.count();
    }
}
