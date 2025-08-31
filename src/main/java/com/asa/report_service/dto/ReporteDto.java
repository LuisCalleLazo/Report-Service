package com.asa.report_service.dto;

import com.asa.report_service.entity.enums.DatabaseType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReporteDto {
    private Long id;
    private String nombre;
    private String descripcion;
    private Double size;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private DatabaseType databaseType;
    private List<ReporteDto> subreportes = new ArrayList<>();

    // Constructor vacío
    public ReporteDto() {
    }

    // Constructor con parámetros básicos
    public ReporteDto(Long id, String nombre, DatabaseType databaseType) {
        this.id = id;
        this.nombre = nombre;
        this.databaseType = databaseType;
        this.createdAt = LocalDateTime.now();
    }

    // Getters y Setters para todos los campos
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<ReporteDto> getSubreportes() {
        return subreportes;
    }

    public void setSubreportes(List<ReporteDto> subreportes) {
        this.subreportes = subreportes;
    }

    public void addSubreporte(ReporteDto subreporte) {
        this.subreportes.add(subreporte);
    }
}