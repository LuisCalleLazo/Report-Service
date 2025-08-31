package com.asa.report_service.entity;

import com.asa.report_service.entity.enums.DatabaseType;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "config_server_connect")
public class ConfigServerConnect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "server_name", nullable = false, length = 100)
    private String serverName;

    @Column(name = "server_description", length = 255)
    private String serverDescription;

    @Column(name = "server_name_or_ip", nullable = false, length = 100)
    private String serverNameOrIp;

    @Column(name = "server_port", nullable = false)
    private Integer serverPort;

    @Column(name = "database_name", nullable = false, length = 50)
    private String databaseName;

    @Enumerated(EnumType.STRING)
    @Column(name = "bd_type", nullable = false, length = 20)
    private DatabaseType bdType;

    @Column(name = "server_user", nullable = false, length = 50)
    private String serverUser;

    @Column(name = "server_password", nullable = false, length = 100)
    private String serverPassword;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;

    // Constructor por defecto (OBLIGATORIO para JPA)
    public ConfigServerConnect() {
        this.createdAt = LocalDateTime.now();
        this.isDeleted = false;
    }

    // Constructor con parámetros
    public ConfigServerConnect(String serverName, String serverDescription,
                               String serverNameOrIp, Integer serverPort,
                               String databaseName, DatabaseType bdType,
                               String serverUser, String serverPassword,
                               Integer userId) {
        this();
        this.serverName = serverName;
        this.serverDescription = serverDescription;
        this.serverNameOrIp = serverNameOrIp;
        this.serverPort = serverPort;
        this.databaseName = databaseName;
        this.bdType = bdType;
        this.serverUser = serverUser;
        this.serverPassword = serverPassword;
        this.userId = userId;
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

    // === MÉTODOS DE CONVENIENCIA ===

    // Método para obtener la conexión JDBC completa
    public String getJdbcUrl() {
        return bdType.getJdbcPrefix() +
                serverNameOrIp + ":" +
                serverPort + "/" +
                databaseName;
    }

    // Método para obtener el driver class
    public String getDriverClassName() {
        return bdType.getDriverClass();
    }

    // Método para clonar la configuración
    public ConfigServerConnect cloneConfig() {
        return new ConfigServerConnect(
                this.serverName + " (Copia)",
                this.serverDescription,
                this.serverNameOrIp,
                this.serverPort,
                this.databaseName,
                this.bdType,
                this.serverUser,
                this.serverPassword,
                this.userId
        );
    }

    // Método para validar la configuración
    public boolean isValid() {
        return serverName != null && !serverName.trim().isEmpty() &&
                serverNameOrIp != null && !serverNameOrIp.trim().isEmpty() &&
                serverPort != null && serverPort > 0 &&
                databaseName != null && !databaseName.trim().isEmpty() &&
                bdType != null &&
                serverUser != null && !serverUser.trim().isEmpty() &&
                serverPassword != null && !serverPassword.trim().isEmpty() &&
                userId != null;
    }

    // === GETTERS Y SETTERS ===

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getServerName() { return serverName; }
    public void setServerName(String serverName) { this.serverName = serverName; }

    public String getServerDescription() { return serverDescription; }
    public void setServerDescription(String serverDescription) { this.serverDescription = serverDescription; }

    public String getServerNameOrIp() { return serverNameOrIp; }
    public void setServerNameOrIp(String serverNameOrIp) { this.serverNameOrIp = serverNameOrIp; }

    public Integer getServerPort() { return serverPort; }
    public void setServerPort(Integer serverPort) { this.serverPort = serverPort; }

    public String getDatabaseName() { return databaseName; }
    public void setDatabaseName(String databaseName) { this.databaseName = databaseName; }

    public DatabaseType getBdType() { return bdType; }
    public void setBdType(DatabaseType bdType) { this.bdType = bdType; }

    public String getServerUser() { return serverUser; }
    public void setServerUser(String serverUser) { this.serverUser = serverUser; }

    public String getServerPassword() { return serverPassword; }
    public void setServerPassword(String serverPassword) { this.serverPassword = serverPassword; }

    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public LocalDateTime getDeletedAt() { return deletedAt; }
    public void setDeletedAt(LocalDateTime deletedAt) { this.deletedAt = deletedAt; }

    public Boolean getIsDeleted() { return isDeleted; }
    public void setIsDeleted(Boolean isDeleted) { this.isDeleted = isDeleted; }

    // === EQUALS Y HASHCODE ===

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConfigServerConnect that = (ConfigServerConnect) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    // === TO STRING ===

    @Override
    public String toString() {
        return "ConfigServerConnect{" +
                "id=" + id +
                ", serverName='" + serverName + '\'' +
                ", serverNameOrIp='" + serverNameOrIp + '\'' +
                ", serverPort=" + serverPort +
                ", databaseName='" + databaseName + '\'' +
                ", bdType=" + bdType +
                ", serverUser='" + serverUser + '\'' +
                ", userId=" + userId +
                ", createdAt=" + createdAt +
                ", isDeleted=" + isDeleted +
                '}';
    }
}