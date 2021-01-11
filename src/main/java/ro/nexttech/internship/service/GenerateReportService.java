package ro.nexttech.internship.service;

import com.itextpdf.text.Document;

import java.io.ByteArrayOutputStream;


public interface GenerateReportService {
    double calculateIncomes(int firmId, int luna);
    double calculateCosts(int firmId, int luna);
    Document generateReport(int firmId, int luna, ByteArrayOutputStream baos);
}
