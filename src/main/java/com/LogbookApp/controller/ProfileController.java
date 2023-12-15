package com.LogbookApp.controller;

import com.LogbookApp.dto.account.EditProfileDTO;
import com.LogbookApp.dto.account.RegisterDTO;
import com.LogbookApp.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/profile")
    private String getEmployeeProfile(Model model) {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        var data =accountService.getProfile(username);
        model.addAttribute("dto", data);
        return "profile/profile";
    }

    @GetMapping("/editForm")
    private String editEmployeeForm(@RequestParam String username, Model model) {
        var dto = new EditProfileDTO();
        if (username != null) {
            dto = accountService.findOne(username);
        }
        model.addAttribute("dto", dto);
        return "profile/edit-form";
    }

    @PostMapping("/edit")
    private String editEmployee(@Valid @ModelAttribute("dto")EditProfileDTO dto, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            accountService.save(dto);
            return "redirect:/profile/profile";
        }
        return "profile/edit-employee-form";
    }
}
