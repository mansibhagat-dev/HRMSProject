package com.example.college.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.college.entity.Employee;
import com.example.college.service.DepartmentService;
import com.example.college.service.EmployeeService;

import jakarta.validation.Valid;



@Controller
@RequestMapping("/employees")
public class EmployeeController {
@Autowired
     EmployeeService employeeService;
@Autowired
    DepartmentService departmentService;

    // List all employees with optional search
    @GetMapping
    public String listEmployees(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        if (keyword != null && !keyword.isEmpty()) {
            model.addAttribute("employees", employeeService.searchEmployees(keyword));
            model.addAttribute("keyword", keyword);
        } else {
            model.addAttribute("employees", employeeService.getAllEmployees());
        }
        return "employee_list";
    }

    // Show add employee form
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("departments", departmentService.getAllDepartments());
        model.addAttribute("pageTitle", "Add New Employee");
        return "add_employee";
    }

    // Save new employee
    @PostMapping("/add")
    public String saveEmployee(@Valid @ModelAttribute Employee employee,
                               BindingResult result,
                               Model model,
                               RedirectAttributes redirectAttrs) {
        if (result.hasErrors()) {
            model.addAttribute("departments", departmentService.getAllDepartments());
            model.addAttribute("pageTitle", "Add New Employee");
            return "add_employee";
        }
        try {
            employeeService.saveEmployee(employee);
            redirectAttrs.addFlashAttribute("successMsg", "Employee added successfully!");
        } catch (RuntimeException e) {
            redirectAttrs.addFlashAttribute("errorMsg", e.getMessage());
        }
        return "redirect:/employees";
    }

    // Show edit form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttrs) {
        return employeeService.getEmployeeById(id).map(employee -> {
            model.addAttribute("employee", employee);
            model.addAttribute("departments", departmentService.getAllDepartments());
            model.addAttribute("pageTitle", "Edit Employee");
            return "edit_employee";
        }).orElseGet(() -> {
            redirectAttrs.addFlashAttribute("errorMsg", "Employee not found.");
            return "redirect:/employees";
        });
    }

    // Update employee
    @PostMapping("/edit/{id}")
    public String updateEmployee(@PathVariable Long id,
                                 @Valid @ModelAttribute Employee employee,
                                 BindingResult result,
                                 Model model,
                                 RedirectAttributes redirectAttrs) {
        if (result.hasErrors()) {
            model.addAttribute("departments", departmentService.getAllDepartments());
            model.addAttribute("pageTitle", "Edit Employee");
            return "edit_employee";
        }
        try {
            employeeService.updateEmployee(id, employee);
            redirectAttrs.addFlashAttribute("successMsg", "Employee updated successfully!");
        } catch (RuntimeException e) {
            redirectAttrs.addFlashAttribute("errorMsg", e.getMessage());
        }
        return "redirect:/employees";
    }

    // Delete employee
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id, RedirectAttributes redirectAttrs) {
        try {
            employeeService.deleteEmployee(id);
            redirectAttrs.addFlashAttribute("successMsg", "Employee deleted successfully!");
        } catch (RuntimeException e) {
            redirectAttrs.addFlashAttribute("errorMsg", e.getMessage());
        }
        return "redirect:/employees";
    }
}

