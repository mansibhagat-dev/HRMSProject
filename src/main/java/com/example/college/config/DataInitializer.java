package com.example.college.config;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.college.entity.Department;
import com.example.college.entity.Employee;
import com.example.college.entity.User;
import com.example.college.repository.DepartmentRepository;
import com.example.college.repository.EmployeeRepository;
import com.example.college.repository.UserRepository;
import com.example.college.service.UserService;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void run(String... args) {

        if (!userRepository.existsByUsername("admin")) {

            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword("admin123");
            admin.setRole("ROLE_ADMIN");
            admin.setEnabled(true);

            userService.saveUser(admin);

            System.out.println("Default admin user created — username: admin, password: admin123");
        }

        if (departmentRepository.count() == 0) {

        	Department hr = new Department();
        	hr.setName("Human Resources");
        	hr.setDescription("Handles employee relations and recruitment");
        	hr.setLocation("Floor 1");

        	Department it = new Department();
        	it.setName("Information Technology");
        	it.setDescription("Manages all IT infrastructure and software");
        	it.setLocation("Floor 3");

        	Department fin = new Department();
        	fin.setName("Finance");
        	fin.setDescription("Manages company finances and accounting");
        	fin.setLocation("Floor 2");

        	Department mkt = new Department();
        	mkt.setName("Marketing");
        	mkt.setDescription("Handles promotions and brand strategy");
        	mkt.setLocation("Floor 4");

        	departmentRepository.save(hr);
        	departmentRepository.save(it);
        	departmentRepository.save(fin);
        	departmentRepository.save(mkt);

            

            System.out.println("Sample departments created.");

            if (employeeRepository.count() == 0) {

                Employee e1 = new Employee();
                e1.setName("Alice Johnson");
                e1.setEmail("alice@hrms.com");
                e1.setPhone("9876543210");
                e1.setDesignation("HR Manager");
                e1.setSalary(new BigDecimal("75000.00"));
                e1.setJoiningDate(LocalDate.of(2021, 3, 15));
                e1.setDepartment(hr);
                e1.setStatus("ACTIVE");
                employeeRepository.save(e1);

                Employee e2 = new Employee();
                e2.setName("Bob Smith");
                e2.setEmail("bob@hrms.com");
                e2.setPhone("9123456780");
                e2.setDesignation("Software Engineer");
                e2.setSalary(new BigDecimal("90000.00"));
                e2.setJoiningDate(LocalDate.of(2022, 7, 1));
                e2.setDepartment(it);
                e2.setStatus("ACTIVE");
                employeeRepository.save(e2);

                Employee e3 = new Employee();
                e3.setName("Carol White");
                e3.setEmail("carol@hrms.com");
                e3.setPhone("9001234567");
                e3.setDesignation("Finance Analyst");
                e3.setSalary(new BigDecimal("68000.00"));
                e3.setJoiningDate(LocalDate.of(2020, 11, 20));
                e3.setDepartment(fin);
                e3.setStatus("ACTIVE");
                employeeRepository.save(e3);

                System.out.println("Sample employees created.");
            }
        }
    }
}