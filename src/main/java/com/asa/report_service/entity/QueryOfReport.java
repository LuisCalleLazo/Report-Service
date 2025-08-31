package com.asa.report_service.entity;

import com.asa.report_service.entity.enums.ParamType;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "query_of_report")
public class QueryOfReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id", nullable = false)
    private Report report;

    @Column(name = "param", nullable = false, length = 100)
    private String param;

    @Column(name = "value", length = 500)
    private String value;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_value", nullable = false, length = 20)
    private ParamType typeValue;

    @Column(name = "default_value", length = 500)
    private String defaultValue;

    @Column(name = "is_required", nullable = false)
    private Boolean isRequired = false;

    @Column(name = "param_order", nullable = false)
    private Integer paramOrder = 0;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;

    // Constructor por defecto
    public QueryOfReport() {
        this.createdAt = LocalDateTime.now();
        this.isDeleted = false;
    }

    // Constructor con parámetros principales
    public QueryOfReport(Report report, String param, ParamType typeValue) {
        this();
        this.report = report;
        this.param = param;
        this.typeValue = typeValue;
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
    }

    // Método para restaurar
    public void restore() {
        this.deletedAt = null;
        this.isDeleted = false;
    }

    // === GETTERS Y SETTERS ===
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Report getReport() { return report; }
    public void setReport(Report report) { this.report = report; }

    public String getParam() { return param; }
    public void setParam(String param) { this.param = param; }

    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }

    public ParamType getTypeValue() { return typeValue; }
    public void setTypeValue(ParamType typeValue) { this.typeValue = typeValue; }

    public String getDefaultValue() { return defaultValue; }
    public void setDefaultValue(String defaultValue) { this.defaultValue = defaultValue; }

    public Boolean getIsRequired() { return isRequired; }
    public void setIsRequired(Boolean isRequired) { this.isRequired = isRequired; }

    public Integer getParamOrder() { return paramOrder; }
    public void setParamOrder(Integer paramOrder) { this.paramOrder = paramOrder; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

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
        return "QueryOfReport{" +
                "id=" + id +
                ", param='" + param + '\'' +
                ", typeValue=" + typeValue +
                ", isRequired=" + isRequired +
                '}';
    }
}