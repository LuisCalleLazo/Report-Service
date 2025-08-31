package com.asa.report_service.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "report")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "server_db_id", nullable = false)
    private ConfigServerConnect serverDb;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_report_id")
    private Report subReport;

    @Column(name = "file_report", nullable = false, length = 255)
    private String fileReport;

    @Column(name = "logo", length = 255)
    private String logo;

    @Column(name = "title", nullable = false, length = 200)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "report_name", nullable = false, unique = true, length = 100)
    private String reportName;

    @Column(name = "report_category", length = 50)
    private String reportCategory;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Column(name = "version", nullable = false)
    private Integer version = 1;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;

    // Constructor por defecto
    public Report() {
        this.createdAt = LocalDateTime.now();
        this.isDeleted = false;
        this.isActive = true;
    }

    // Constructor con parámetros principales
    public Report(ConfigServerConnect serverDb, String fileReport,
                  String title, String reportName) {
        this();
        this.serverDb = serverDb;
        this.fileReport = fileReport;
        this.title = title;
        this.reportName = reportName;
    }

    // === MÉTODOS DE AUDITORÍA ===
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.isDeleted = false;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // Método para soft delete
    public void softDelete() {
        this.deletedAt = LocalDateTime.now();
        this.isDeleted = true;
        this.isActive = false;
    }

    // Método para restaurar
    public void restore() {
        this.deletedAt = null;
        this.isDeleted = false;
        this.isActive = true;
    }

    // Método para incrementar versión
    public void incrementVersion() {
        this.version++;
    }

    // === GETTERS Y SETTERS ===
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public ConfigServerConnect getServerDb() { return serverDb; }
    public void setServerDb(ConfigServerConnect serverDb) { this.serverDb = serverDb; }

    public Report getSubReport() { return subReport; }
    public void setSubReport(Report subReport) { this.subReport = subReport; }

    public String getFileReport() { return fileReport; }
    public void setFileReport(String fileReport) { this.fileReport = fileReport; }

    public String getLogo() { return logo; }
    public void setLogo(String logo) { this.logo = logo; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getReportName() { return reportName; }
    public void setReportName(String reportName) { this.reportName = reportName; }

    public String getReportCategory() { return reportCategory; }
    public void setReportCategory(String reportCategory) { this.reportCategory = reportCategory; }

    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }

    public Integer getVersion() { return version; }
    public void setVersion(Integer version) { this.version = version; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public LocalDateTime getDeletedAt() { return deletedAt; }
    public void setDeletedAt(LocalDateTime deletedAt) { this.deletedAt = deletedAt; }

    public Boolean getIsDeleted() { return isDeleted; }
    public void setIsDeleted(Boolean isDeleted) { this.isDeleted = isDeleted; }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", reportName='" + reportName + '\'' +
                ", isActive=" + isActive +
                ", version=" + version +
                '}';
    }
}