package com.LogbookApp.controller;

import com.LogbookApp.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/notification")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @GetMapping("/list")
    public String notificationMessage(Model model) {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        var data = notificationService.getNotificationMessage(username);
        var total = data.isEmpty();
        model.addAttribute("grid", data);
        model.addAttribute("total", total);
        return "notification/message";
    }

    @GetMapping("/readOne")
    public String readOne(@RequestParam Long id, RedirectAttributes redirectAttributes) {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        notificationService.readOne(id);
        redirectAttributes.addAttribute("username", username);
        return "redirect:/notification/list";
    }

    @GetMapping("/readAll")
    public String readAll(RedirectAttributes redirectAttributes) {
        notificationService.readAll();
        return "redirect:/notification/list";
    }
}
