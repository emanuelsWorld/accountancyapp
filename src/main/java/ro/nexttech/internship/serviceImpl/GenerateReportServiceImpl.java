package ro.nexttech.internship.serviceImpl;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import org.springframework.stereotype.Service;
import ro.nexttech.internship.domain.Income;
import ro.nexttech.internship.domain.Invoice;
import ro.nexttech.internship.domain.Payment;
import ro.nexttech.internship.repository.FirmRepository;
import ro.nexttech.internship.repository.IncomeRepository;
import ro.nexttech.internship.service.GenerateReportService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class GenerateReportServiceImpl implements GenerateReportService {

    private FirmRepository firmRepository;
    private IncomeRepository incomeRepository;

    public GenerateReportServiceImpl(FirmRepository firmRepository, IncomeRepository incomeRepository) {
        this.firmRepository = firmRepository;
        this.incomeRepository = incomeRepository;
    }

    @Override
    public double calculateIncomes(int firmId, int luna) {
        var incomes = firmRepository.findById(firmId).get().getIncomes();
        var total = incomes.stream().filter(income -> income.getDate().getMonthValue() == luna).mapToDouble(Income::getAmmount).sum();
        return total;
    }

    public double calculateCosts(int firmId, int luna) {
        var invoices = firmRepository.findById(firmId).get().getInvoices();
        double paymentTotal = 0;
        var currentInvoices = invoices.stream().filter(invoice -> invoice.getIssueDate().getMonthValue() == luna).collect(Collectors.toList());
        for (Invoice invoice : currentInvoices)
            paymentTotal += invoice.getPaymentEntities().stream().mapToDouble(Payment::getAmmount).sum();

        return paymentTotal;
    }

    public ByteArrayInputStream generateReport(int firmId, int luna) {
        var incomeTotal = calculateIncomes(firmId, luna);
        var costTotal = calculateCosts(firmId, luna);
        Map<String, String> myData = new HashMap<>();
        myData.put("incomeTotal", Double.toString(incomeTotal));
        myData.put("costTotal", Double.toString(costTotal));
        myData.put("balance", Double.toString((incomeTotal - costTotal)));
        return getReport(myData);


    }

    public ByteArrayInputStream getReport(Map<String, String> myData) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(80);
            table.setWidths(new int[]{4, 4, 4});
            Font headFont = FontFactory.getFont(FontFactory.TIMES_ROMAN);
            PdfPCell hcell;
            hcell = new PdfPCell(new Phrase("Income total: ", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Cost total: ", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Balance: ", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            PdfPCell cell;
            cell = new PdfPCell(new Phrase(myData.get("incomeTotal")));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(myData.get("costTotal")));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(myData.get("balance")));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            PdfWriter.getInstance(document, out);
            document.open();
            document.add(table);

            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return new ByteArrayInputStream(out.toByteArray());
    }
}
