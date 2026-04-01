package com.example.college.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.college.service.DepartmentService;
import com.example.college.service.EmployeeService;
import com.example.college.service.LeaveService;

@Controller
public class DashboardController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private LeaveService leaveService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        model.addAttribute("totalEmployees", employeeService.countEmployees());
        model.addAttribute("totalDepartments", departmentService.countDepartments());
        model.addAttribute("pendingLeaves", leaveService.countPendingLeaves());
        model.addAttribute("recentLeaves",
                leaveService.getAllLeaveRequests().stream().limit(5).toList());

        return "dashboard";
    }
}