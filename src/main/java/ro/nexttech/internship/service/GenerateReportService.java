package ro.nexttech.internship.service;

import ro.nexttech.internship.domain.ReportDetails;

import java.io.ByteArrayInputStream;
import java.util.List;


public interface GenerateReportService {
    List<ReportDetails> getIncomes(int firmId, int luna);
    List<ReportDetails> getInvoices(int firmId, int luna);
    ByteArrayInputStream generateReport(int firmId, int luna);
}
