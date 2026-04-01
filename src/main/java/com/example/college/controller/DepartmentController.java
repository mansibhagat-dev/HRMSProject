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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.college.entity.Department;
import com.example.college.service.DepartmentService;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/departments")
public class DepartmentController {
@Autowired
      DepartmentService departmentService;

    @GetMapping
    public String listDepartments(Model model) {
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "department_list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("department", new Department());
        model.addAttribute("pageTitle", "Add Department");
        return "add_department";
    }

    @PostMapping("/add")
    public String saveDepartment(@Valid @ModelAttribute Department department,
                                 BindingResult result,
                                 Model model,
                                 RedirectAttributes redirectAttrs) {
        if (result.hasErrors()) {
            model.addAttribute("pageTitle", "Add Department");
            return "add_department";
        }
        try {
            departmentService.saveDepartment(department);
            redirectAttrs.addFlashAttribute("successMsg", "Department added successfully!");
        } catch (RuntimeException e) {
            redirectAttrs.addFlashAttribute("errorMsg", e.getMessage());
        }
        return "redirect:/departments";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttrs) {
        return departmentService.getDepartmentById(id).map(dept -> {
            model.addAttribute("department", dept);
            model.addAttribute("pageTitle", "Edit Department");
            return "edit_department";
        }).orElseGet(() -> {
            redirectAttrs.addFlashAttribute("errorMsg", "Department not found.");
            return "redirect:/departments";
        });
    }

    @PostMapping("/edit/{id}")
    public String updateDepartment(@PathVariable Long id,
                                   @Valid @ModelAttribute Department department,
                                   BindingResult result,
                                   Model model,
                                   RedirectAttributes redirectAttrs) {
        if (result.hasErrors()) {
            model.addAttribute("pageTitle", "Edit Department");
            return "edit_department";
        }
        try {
            departmentService.updateDepartment(id, department);
            redirectAttrs.addFlashAttribute("successMsg", "Department updated successfully!");
        } catch (RuntimeException e) {
            redirectAttrs.addFlashAttribute("errorMsg", e.getMessage());
        }
        return "redirect:/departments";
    }

    @GetMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable Long id, RedirectAttributes redirectAttrs) {
        try {
            departmentService.deleteDepartment(id);
            redirectAttrs.addFlashAttribute("successMsg", "Department deleted successfully!");
        } catch (RuntimeException e) {
            redirectAttrs.addFlashAttribute("errorMsg", e.getMessage());
        }
        return "redirect:/departments";
    }
}

