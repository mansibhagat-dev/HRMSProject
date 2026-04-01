package com.example.college.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.college.entity.Department;
import com.example.college.repository.DepartmentRepository;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public Department saveDepartment(Department department) {

        if (departmentRepository.existsByNameIgnoreCase(department.getName())) {
            throw new RuntimeException("Department already exists");
        }

        return departmentRepository.save(department);
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Optional<Department> getDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }

    public Department updateDepartment(Long id, Department department) {

        Department existing = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        if (departmentRepository.existsByNameIgnoreCaseAndIdNot(department.getName(), id)) {
            throw new RuntimeException("Department name already exists");
        }

        existing.setName(department.getName());
        existing.setDescription(department.getDescription());
        existing.setLocation(department.getLocation());

        return departmentRepository.save(existing);
    }

    public void deleteDepartment(Long id) {

        Department dept = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        if (dept.getEmployees() != null && !dept.getEmployees().isEmpty()) {
            throw new RuntimeException("Department has employees assigned");
        }

        departmentRepository.deleteById(id);
    }

    public long countDepartments() {
        return departmentRepository.count();
    }
}