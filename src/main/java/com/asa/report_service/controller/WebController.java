package com.asa.report_service.controller;

import com.asa.report_service.dto.LoginRequestDto;
import com.asa.report_service.dto.ReporteDto;
import com.asa.report_service.entity.enums.DatabaseType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class WebController {

    @GetMapping("/")
    public String Home(Model model)
    {
        model.addAttribute("message", "Report Service esta funcionando");
        return "home";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginRequestDto request, Model model) {
        if("admin@gmail.com".equals(request.getEmail())
                && "1234".equals(request.getPassword())) {
            return "redirect:/dashboard";
        }
        model.addAttribute("error", "Credenciales inválidas");
        return "home";
    }

    @GetMapping("/documentation")
    public String Documentation(Model model)
    {
        return "documentation";
    }

    @GetMapping("/dashboard")
    public String Dashboard(Model model) {
        List<ReporteDto> reportes = new ArrayList<>();

        for (long i = 1; i <= 10; i++) {
            ReporteDto r = new ReporteDto(i, "Reporte " + i, DatabaseType.POSTGRESQL);
            r.setDescripcion("Descripción del reporte " + i);
            r.setSize(2.0 + i);
            r.setCreatedAt(LocalDateTime.now().minusDays(i));
            r.setUpdatedAt(LocalDateTime.now().minusDays(i/2));

            // Agregar subreportes
            for (long j = 1; j <= 2; j++) {
                ReporteDto sub = new ReporteDto(i*10 + j, "Subreporte " + i + "." + j, DatabaseType.MYSQL);
                sub.setDescripcion("Descripción del subreporte " + i + "." + j);
                sub.setSize(1.0 + j);
                sub.setCreatedAt(LocalDateTime.now().minusDays(j));
                sub.setUpdatedAt(LocalDateTime.now().minusDays(j/2));
                r.addSubreporte(sub);
            }

            reportes.add(r);
        }

        model.addAttribute("reportes", reportes);
        return "dashboard";
    }


    @GetMapping("/reports")
    public String Reports(Model model)
    {
        model.addAttribute("message", "Report Service esta funcionando");
        return "reports";
    }

    @GetMapping("/new-report")
    public String NewReports(Model model)
    {
        model.addAttribute("message", "Report Service esta funcionando");
        return "new-report";
    }
}
