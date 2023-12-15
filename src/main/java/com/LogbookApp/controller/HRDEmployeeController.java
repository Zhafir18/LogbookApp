package com.LogbookApp.controller;

import com.LogbookApp.dto.logbook.InsertNewLogDTO;
import com.LogbookApp.service.AccountService;
import com.LogbookApp.service.LogService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@RequestMapping("/employee")
public class HRDEmployeeController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private LogService logService;

    @GetMapping("/list")
    public String employeeList(@RequestParam(defaultValue = "1") Integer page,
                               Model model) {
        var data = accountService.getEmployee(page);
        model.addAttribute("grid", data);
        model.addAttribute("totalPages", data.getTotalPages());
        model.addAttribute("currentPage", page);
        return "employee/list";
    }

    @GetMapping("/logList")
    public String index(@RequestParam(defaultValue = "1") Integer page,
                        @RequestParam String username,
                        @RequestParam(required = false) Integer submissionMonth,
                        @RequestParam(required = false) Integer submissionYear,
                        @RequestParam(required = false, defaultValue = "true") boolean clientApproval,
                        @RequestParam(required = false, defaultValue = "true") boolean hrdApproval,
                        Model model){
        var data = logService.getData(username, submissionMonth, submissionYear, clientApproval, hrdApproval, page);
        model.addAttribute("grid", data);
        model.addAttribute("submissionMonth", submissionMonth);
        model.addAttribute("submissionYear", submissionYear);
        model.addAttribute("clientApproval", clientApproval);
        model.addAttribute("hrdApproval", hrdApproval);
        model.addAttribute("monthDropdown", logService.getMonthDropdown());
        model.addAttribute("yearDropdown", logService.getYearDropdown());
        model.addAttribute("clientApprovalDropdown", logService.getStatus());
        model.addAttribute("hrdApprovalDropdown", logService.getStatus());
        model.addAttribute("totalPages", data.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("username", username);
        return "employee/log-by-employee";
    }

    @GetMapping("/insertLogForm")
    public String newLogForm(String username, Model model) {
        var dto = new InsertNewLogDTO();
        var employeeDropdown = logService.getEmployeeDropdown();
        var clientDropdown = logService.getClientDropdown();
        var hrdDropdown = logService.getHRDDropdown();
        dto.setUsername(username);
        dto.setClientApproval(false);
        dto.setHrdApproval(false);
        dto.setPeriod(LocalDate.now().getYear());

        model.addAttribute("dto", dto);
        model.addAttribute("employeeDropdown", employeeDropdown);
        model.addAttribute("clientDropdown", clientDropdown);
        model.addAttribute("hrdDropdown", hrdDropdown);

        return "employee/hrd-log-form";
    }

    @PostMapping("/insertLog")
    public String insertLog(@Valid @ModelAttribute("dto") InsertNewLogDTO dto,
                            BindingResult bindingResult,
                            Model model,
                            RedirectAttributes redirectAttributes) {
        if (!bindingResult.hasErrors()) {
            logService.insertNew(dto);
            redirectAttributes.addAttribute("username", dto.getUsername());
            return "redirect:/employee/logList";
        }
        var employeeDropdown = logService.getEmployeeDropdown();
        var clientDropdown = logService.getClientDropdown();
        var hrdDropdown = logService.getHRDDropdown();
        model.addAttribute("employeeDropdown", employeeDropdown);
        model.addAttribute("clientDropdown", clientDropdown);
        model.addAttribute("hrdDropdown", hrdDropdown);
        dto.setUsername(dto.getUsername());
        dto.setClientApproval(false);
        dto.setHrdApproval(false);
        dto.setPeriod(LocalDate.now().getYear());
        return "employee/hrd-log-form";
    }

    @GetMapping("/approve")
    public String approve(@RequestParam Long id) {
        logService.hrdApprove(id);
        return "redirect:/employee/list";
    }

    @GetMapping("/approveAll")
    public String approveAll(RedirectAttributes redirectAttributes) {
        logService.hrdApproveAll();
        return "redirect:/employee/list";
    }
}
