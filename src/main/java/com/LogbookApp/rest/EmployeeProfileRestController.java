package com.LogbookApp.rest;

import com.LogbookApp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/employeeProfile")
public class EmployeeProfileRestController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/{username}")
    public ResponseEntity<Object> getEmployeeProfile(@PathVariable String username) {
        var response = accountService.getProfile(username);
        return ResponseEntity.status(200).body(response);
    }
}
