package com.example.college.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.college.entity.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Search employees by name (case-insensitive)
    List<Employee> findByNameContainingIgnoreCase(String name);

    // Find employees by department ID
    List<Employee> findByDepartmentId(Long departmentId);

    // Search employees by name or department name
    @Query("SELECT e FROM Employee e WHERE " +
           "LOWER(e.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(e.department.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Employee> searchByNameOrDepartment(@Param("keyword") String keyword);

    // Count employees by department
    long countByDepartmentId(Long departmentId);

    // Check if email already exists
    boolean existsByEmail(String email);

    // Check if email exists and belongs to a different employee (for updates)
    boolean existsByEmailAndIdNot(String email, Long id);
}
