package com.example.college.service;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.college.entity.LeaveRequest;
import com.example.college.entity.LeaveStatus;
import com.example.college.repository.LeaveRepository;


@Service
public class LeaveService {

    @Autowired
    private LeaveRepository leaveRepository;

    public LeaveRequest applyLeave(LeaveRequest leaveRequest) {

        leaveRequest.setStatus(LeaveStatus.PENDING);
        leaveRequest.setAppliedDate(LocalDate.now());

        return leaveRepository.save(leaveRequest);
    }

    public List<LeaveRequest> getAllLeaveRequests() {
        return leaveRepository.findAllByOrderByAppliedDateDesc();
    }

    public Optional<LeaveRequest> getLeaveById(Long id) {
        return leaveRepository.findById(id);
    }

    public LeaveRequest updateLeaveStatus(Long id, LeaveStatus status, String remarks) {

        LeaveRequest leave = leaveRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave request not found"));

        leave.setStatus(status);
        leave.setAdminRemarks(remarks);

        return leaveRepository.save(leave);
    }

    public List<LeaveRequest> getLeavesByEmployee(Long employeeId) {
        return leaveRepository.findByEmployeeId(employeeId);
    }

    public long countPendingLeaves() {
        return leaveRepository.countByStatus(LeaveStatus.PENDING);
    }
}
