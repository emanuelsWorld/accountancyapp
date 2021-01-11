package ro.nexttech.internship.service;

import com.itextpdf.text.Document;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Map;


public interface GenerateReportService {
    double calculateIncomes(int firmId, int luna);
    double calculateCosts(int firmId, int luna);
    ByteArrayInputStream generateReport(int firmId, int luna);
    ByteArrayInputStream getReport(Map<String, String> myData);
}
