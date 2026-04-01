package com.example.college.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.college.entity.LeaveRequest;
import com.example.college.entity.LeaveStatus;


@Repository
public interface LeaveRepository extends JpaRepository<LeaveRequest, Long> {

    List<LeaveRequest> findByEmployeeId(Long employeeId);

    List<LeaveRequest> findByStatus(LeaveStatus status);

    List<LeaveRequest> findAllByOrderByAppliedDateDesc();

    long countByStatus(LeaveStatus status);
}
