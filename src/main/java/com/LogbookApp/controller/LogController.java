package com.LogbookApp.controller;

import com.LogbookApp.dto.logbook.EditLogDTO;
import com.LogbookApp.dto.logbook.InsertNewLogDTO;
import com.LogbookApp.service.LogService;
import com.LogbookApp.service.NotificationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/log")
public class LogController {
    @Autowired
    private LogService logService;

    @GetMapping("/list")
    public String index(@RequestParam(defaultValue = "1") Integer page,
                        @RequestParam(required = false) Integer submissionMonth,
                        @RequestParam(required = false) Integer submissionYear,
                        @RequestParam(required = false, defaultValue = "true") boolean clientApproval,
                        @RequestParam(required = false, defaultValue = "true") boolean hrdApproval,
                        @RequestParam(required = false) Integer period,
                        Model model){
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        var data = logService.getDataForEmployee(username, submissionMonth, submissionYear, clientApproval, hrdApproval, period, page);
        var header = logService.getHeader(username);
        model.addAttribute("grid", data);
        model.addAttribute("header", header);
        model.addAttribute("submissionMonth", submissionMonth);
        model.addAttribute("submissionYear", submissionYear);
        model.addAttribute("clientApproval", clientApproval);
        model.addAttribute("hrdApproval", hrdApproval);
        model.addAttribute("period", period);
        model.addAttribute("monthDropdown", logService.getMonthDropdown());
        model.addAttribute("yearDropdown", logService.getYearDropdown());
        model.addAttribute("clientApprovalDropdown", logService.getStatus());
        model.addAttribute("hrdApprovalDropdown", logService.getStatus());
        model.addAttribute("periodDropdown", logService.getPeriodDropdown());
        model.addAttribute("totalPages", data.getTotalPages());
        model.addAttribute("currentPage", page);
        return "log/list";
    }

    @GetMapping("/editForm")
    public String editLogForm(@RequestParam Long id, Model model) {
        var dto = new EditLogDTO();
        if (id != null) {
            dto = logService.findOne(id);
        }
        model.addAttribute("dto", dto);
        return "log/edit";
    }

    @PostMapping("edit")
    public String edit(@Valid @ModelAttribute("dto") EditLogDTO dto, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            logService.saveLog(dto);
            return "redirect:/log/list";
        }
        model.addAttribute("dto", dto);
        return "log/edit";
    }
}
