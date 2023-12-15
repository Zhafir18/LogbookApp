package com.LogbookApp.controller;

import com.LogbookApp.dto.DropdownDTO;
import com.LogbookApp.dto.account.ChangePasswordDTO;
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
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/registerForm")
    public String registerForm(Model model) {
        var dto = new RegisterDTO();
        dto.setRole("Employee");
        model.addAttribute("dto", dto);
        return "account/register-form";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("dto") RegisterDTO dto, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            accountService.register(dto);
            return "redirect:/account/loginForm";
        }
        dto.setRole("Employee");
        model.addAttribute("dto", dto);
        return "account/register-form";
    }

    @GetMapping("/clientRegisterForm")
    public String clientRegisterForm(Model model) {
        var dto = new RegisterDTO();
        dto.setRole("Client");
        dto.setPassword("indocyber");
        dto.setConfirmPassword("indocyber");
        model.addAttribute("dto", dto);
        return "account/client-register-form";
    }

    @PostMapping("/clientRegister")
    public String registerClient(@Valid @ModelAttribute("dto") RegisterDTO dto, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            accountService.register(dto);
            return "redirect:/hrd/clientList";
        }
        dto.setRole("Client");
        dto.setPassword("indocyber");
        dto.setConfirmPassword("indocyber");
        model.addAttribute("dto", dto);
        return "account/client-register-form";
    }

    @GetMapping("/hrdRegisterForm")
    public String hrdRegisterForm(Model model) {
        var dto = new RegisterDTO();
        dto.setRole("HRD");
        dto.setPassword("indocyber");
        dto.setConfirmPassword("indocyber");
        model.addAttribute("dto", dto);
        return "account/hrd-register-form";
    }

    @PostMapping("/hrdRegister")
    public String registerHRD(@Valid @ModelAttribute("dto") RegisterDTO dto, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            accountService.register(dto);
            return "redirect:/hrd/hrdList";
        }
        dto.setRole("HRD");
        dto.setPassword("indocyber");
        dto.setConfirmPassword("indocyber");
        model.addAttribute("dto", dto);
        return "account/hrd-register-form";
    }

    @GetMapping("/delete")
    public String deleteClient(@RequestParam String username) {
        accountService.delete(username);
        return "redirect:/hrd/clientList";
    }

    @GetMapping("/deleteHRD")
    public String deleteHRD(@RequestParam String username) {
        accountService.delete(username);
        return "redirect:/hrd/hrdList";
    }

    @GetMapping("/loginForm")
    public String loginForm(Model model) {
        return "account/login-form";
    }

    @RequestMapping(value = "/accessDenied", method = {RequestMethod.GET, RequestMethod.POST})
    public String accessDenied(Model model) {
        return "/account/access-denied";
    }

    @GetMapping("/loginFailed")
    public String loginFailed(Model model) {
        return "/account/login-failed";
    }

    @GetMapping("/changePasswordForm")
    public String changePasswordForm(Model model) {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        var dto = new ChangePasswordDTO();
        dto.setUsername(username);
        model.addAttribute("dto", dto);
        return "account/change-password";
    }

    @PostMapping("/changePassword")
    public String changePassword(@Valid @ModelAttribute("dto")ChangePasswordDTO dto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "account/change-password";
        }
        accountService.changePassword(dto);
        return "redirect:/";
    }
}
