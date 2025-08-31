package com.asa.report_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @GetMapping("/api/auth/")
    public String home() {
        return "¡Report Service está funcionando correctamente!";
    }

    @GetMapping("/api/status")
    public String status() {
        return "Conectado a la base de datos - Servicio activo";
    }
}
