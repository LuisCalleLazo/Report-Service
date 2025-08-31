package com.asa.report_service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String Home(Model model)
    {
        model.addAttribute("message", "Report Service esta funcionando");
        return "home";
    }

    @GetMapping("/documentation")
    public String Documentation(Model model)
    {
        return "documentation";
    }

    @GetMapping("/dashboard")
    public String Dashboard(Model model)
    {
        model.addAttribute("message", "Report Service esta funcionando");
        return "home";
    }

    @GetMapping("/reports")
    public String Reports(Model model)
    {
        model.addAttribute("message", "Report Service esta funcionando");
        return "home";
    }

    @GetMapping("/new-report")
    public String NewReports(Model model)
    {
        model.addAttribute("message", "Report Service esta funcionando");
        return "home";
    }

    @GetMapping("/help-dev")
    public String HelpDev(Model model)
    {
        model.addAttribute("message", "Report Service esta funcionando");
        return "home";
    }
}
