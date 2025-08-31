package com.asa.report_service.entity.enums;

public enum ParamType {
    STRING("String"),
    INTEGER("Integer"),
    LONG("Long"),
    DOUBLE("Double"),
    BOOLEAN("Boolean"),
    DATE("Date"),
    TIMESTAMP("Timestamp"),
    LIST("List"),
    MAP("Map");

    private final String displayName;

    ParamType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    // Método para obtener el enum desde un string
    public static ParamType fromString(String value) {
        if (value != null) {
            for (ParamType type : ParamType.values()) {
                if (value.equalsIgnoreCase(type.name()) ||
                        value.equalsIgnoreCase(type.displayName)) {
                    return type;
                }
            }
        }
        throw new IllegalArgumentException("Tipo de parámetro no válido: " + value);
    }
}