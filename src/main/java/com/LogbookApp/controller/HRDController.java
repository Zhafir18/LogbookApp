package com.LogbookApp.controller;

import com.LogbookApp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/hrd")
public class HRDController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/clientList")
    public String clientList(@RequestParam(defaultValue = "") String company,
                             @RequestParam(defaultValue = "1") Integer page,
                             Model model) {
        var data = accountService.getClient(company, page);
        model.addAttribute("grid", data);
        model.addAttribute("company", company);
        model.addAttribute("totalPages", data.getTotalPages());
        model.addAttribute("currentPage", page);
        return "/hrd/client-list";
    }

    @GetMapping("/hrdList")
    public String hrdList(@RequestParam(defaultValue = "") String name,
                             @RequestParam(defaultValue = "1") Integer page,
                             Model model) {
        var data = accountService.getHRD(name, page);
        model.addAttribute("grid", data);
        model.addAttribute("name", name);
        model.addAttribute("totalPages", data.getTotalPages());
        model.addAttribute("currentPage", page);
        return "/hrd/hrd-list";
    }

}
