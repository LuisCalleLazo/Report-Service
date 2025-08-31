package com.asa.report_service.entity.enums;

public enum DatabaseType {
    POSTGRESQL("PostgreSQL", "org.postgresql.Driver", "jdbc:postgresql://"),
    MYSQL("MySQL", "com.mysql.cj.jdbc.Driver", "jdbc:mysql://"),
    SQLSERVER("SQL Server", "com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:sqlserver://"),
    ORACLE("Oracle", "oracle.jdbc.OracleDriver", "jdbc:oracle:thin:@"),
    MARIADB("MariaDB", "org.mariadb.jdbc.Driver", "jdbc:mariadb://"),
    H2("H2", "org.h2.Driver", "jdbc:h2:mem:"),
    SQLITE("SQLite", "org.sqlite.JDBC", "jdbc:sqlite:");

    private final String displayName;
    private final String driverClass;
    private final String jdbcPrefix;

    DatabaseType(String displayName, String driverClass, String jdbcPrefix) {
        this.displayName = displayName;
        this.driverClass = driverClass;
        this.jdbcPrefix = jdbcPrefix;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public String getJdbcPrefix() {
        return jdbcPrefix;
    }

    // Método para obtener el enum desde un string (case insensitive)
    public static DatabaseType fromString(String value) {
        if (value != null) {
            for (DatabaseType type : DatabaseType.values()) {
                if (value.equalsIgnoreCase(type.name()) ||
                        value.equalsIgnoreCase(type.displayName)) {
                    return type;
                }
            }
        }
        throw new IllegalArgumentException("Tipo de base de datos no válido: " + value);
    }
}