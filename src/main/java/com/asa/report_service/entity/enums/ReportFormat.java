package com.asa.report_service.entity.enums;

public enum ReportFormat {
    PDF("pdf"),
    XLSX("xlsx"),
    ODS("ods"),
    CSV("csv"),
    PPTX("pptx"),
    DOCX("docx");

    private final String extension;

    ReportFormat(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }

    public static ReportFormat fromString(String value) {
        if (value != null) {
            for (ReportFormat format : ReportFormat.values()) {
                if (value.equalsIgnoreCase(format.name()) || value.equalsIgnoreCase(format.extension)) {
                    return format;
                }
            }
        }
        throw new IllegalArgumentException("Formato de reporte no válido: " + value);
    }
}
