package com.asa.report_service.service;

import com.asa.report_service.entity.enums.DatabaseType;
import com.asa.report_service.entity.enums.ReportFormat;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.*;
import net.sf.jasperreports.engine.export.ooxml.*;
import net.sf.jasperreports.export.*;

import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Map;

@Service
public class ReportGenerateService {

    public byte[] generateReport(String jrxmlPath,
                                 Map<String, Object> parameters,
                                 DatabaseType dbType,
                                 String host,
                                 String dbName,
                                 String username,
                                 String password,
                                 ReportFormat format) throws Exception {

        InputStream reportStream = getClass().getResourceAsStream(jrxmlPath);
        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

        Class.forName(dbType.getDriverClass());
        String jdbcUrl = dbType.getJdbcPrefix() + host + "/" + dbName;

        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password)) {

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            switch (format) {
                case PDF -> {
                    return JasperExportManager.exportReportToPdf(jasperPrint);
                }
                case XLSX -> {
                    JRXlsxExporter exporter = new JRXlsxExporter();
                    exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                    exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
                    exporter.exportReport();
                }
                case CSV -> {
                    JRCsvExporter exporter = new JRCsvExporter();
                    exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                    exporter.setExporterOutput(new SimpleWriterExporterOutput(baos));
                    exporter.exportReport();
                }
                case DOCX -> {
                    JRDocxExporter exporter = new JRDocxExporter();
                    exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                    exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
                    exporter.exportReport();
                }
                case PPTX -> {
                    JRPptxExporter exporter = new JRPptxExporter();
                    exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                    exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
                    exporter.exportReport();
                }
                default -> throw new IllegalArgumentException("Formato no soportado: " + format);
            }

            return baos.toByteArray();
        }
    }
}
