package com.example.college.controller;

import java.util.List;

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

import com.example.college.entity.LeaveRequest;
import com.example.college.entity.LeaveStatus;
import com.example.college.service.EmployeeService;
import com.example.college.service.LeaveService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/leaves")
public class LeaveController {

    @Autowired
    LeaveService leaveService;

    @Autowired
    EmployeeService employeeService;

    @GetMapping
    public String listLeaves(Model model) {
        List<LeaveRequest> leaves = leaveService.getAllLeaveRequests();

        // Calculate number of days for each leave
        leaves.forEach(leave -> {
            if (leave.getStartDate() != null && leave.getEndDate() != null) {
                long days = java.time.temporal.ChronoUnit.DAYS.between(
                        leave.getStartDate(), leave.getEndDate()) + 1; // inclusive
                leave.setDays(days);
            } else {
                leave.setDays(0);
            }
        });

        model.addAttribute("leaves", leaves);
        model.addAttribute("pendingCount", leaveService.countPendingLeaves());
        return "leave_requests";
    }

    @GetMapping("/apply")
    public String showApplyForm(Model model) {
        model.addAttribute("leaveRequest", new LeaveRequest());
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "apply_leave";
    }

    @PostMapping("/apply")
    public String applyLeave(@Valid @ModelAttribute LeaveRequest leaveRequest,
                             BindingResult result,
                             Model model,
                             RedirectAttributes redirectAttrs) {
        if (result.hasErrors()) {
            model.addAttribute("employees", employeeService.getAllEmployees());
            return "apply_leave";
        }
        try {
            leaveService.applyLeave(leaveRequest);
            redirectAttrs.addFlashAttribute("successMsg", "Leave application submitted successfully!");
        } catch (RuntimeException e) {
            redirectAttrs.addFlashAttribute("errorMsg", e.getMessage());
        }
        return "redirect:/leaves";
    }

    @GetMapping("/approve/{id}")
    public String approveLeave(@PathVariable Long id,
                               @RequestParam(value = "remarks", required = false) String remarks,
                               RedirectAttributes redirectAttrs) {
        try {
            leaveService.updateLeaveStatus(id, LeaveStatus.APPROVED, remarks);
            redirectAttrs.addFlashAttribute("successMsg", "Leave request approved.");
        } catch (RuntimeException e) {
            redirectAttrs.addFlashAttribute("errorMsg", e.getMessage());
        }
        return "redirect:/leaves";
    }

    @GetMapping("/reject/{id}")
    public String rejectLeave(@PathVariable Long id,
                              @RequestParam(value = "remarks", required = false) String remarks,
                              RedirectAttributes redirectAttrs) {
        try {
            leaveService.updateLeaveStatus(id, LeaveStatus.REJECTED, remarks);
            redirectAttrs.addFlashAttribute("successMsg", "Leave request rejected.");
        } catch (RuntimeException e) {
            redirectAttrs.addFlashAttribute("errorMsg", e.getMessage());
        }
        return "redirect:/leaves";
    }
}