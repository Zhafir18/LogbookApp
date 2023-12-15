package com.LogbookApp.controller;

import com.LogbookApp.service.AccountService;
import com.LogbookApp.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/client")
public class ClientEmployeeController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private LogService logService;

    @GetMapping("/employee")
    public String clientEmployee(@RequestParam(defaultValue = "1") Integer page,
                                 Model model) {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        var data = accountService.getClientEmployee(username, page);
        model.addAttribute("grid", data);
        model.addAttribute("totalPages", data.getTotalPages());
        model.addAttribute("currentPage", page);
        return "client/employee-list";
    }

    @GetMapping("/log")
    public String clientEmployeeLog(@RequestParam String username,
                                    @RequestParam(defaultValue = "1") Integer page,
                                    @RequestParam(required = false) Integer logMonth,
                                    @RequestParam(required = false) Integer logYear,
                                    @RequestParam(required = false, defaultValue = "true") Boolean clientApproval,
                                    Model model) {
        var clientUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        var data = logService.getClientEmployeeLog(clientUsername, username, logMonth, logYear, clientApproval, page);
        model.addAttribute("grid", data);
        model.addAttribute("logMonth", logMonth);
        model.addAttribute("logYear", logYear);
        model.addAttribute("clientApproval", clientApproval);
        model.addAttribute("monthDropdown", logService.getMonthDropdown());
        model.addAttribute("yearDropdown", logService.getLogDateYearDropdown());
        model.addAttribute("clientApprovalDropdown", logService.getStatus());
        model.addAttribute("totalPages", data.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("username", username);
        return "client/employee-log";
    }

    @GetMapping("/approve")
    public String approve(@RequestParam Long id, RedirectAttributes redirectAttributes) {
        logService.clientApprove(id);
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        redirectAttributes.addAttribute("username", username);
        return "redirect:/client/log";
    }

    @GetMapping("/approveAll")
    public String approveAll(RedirectAttributes redirectAttributes) {
        logService.clientApproveAll();
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        redirectAttributes.addAttribute("username", username);
        return "redirect:/client/log";
    }
}
