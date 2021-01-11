package ro.nexttech.internship.serviceImpl;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import ro.nexttech.internship.domain.*;
import ro.nexttech.internship.repository.FirmRepository;

import ro.nexttech.internship.service.GenerateReportService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class GenerateReportServiceImpl implements GenerateReportService {

    private FirmRepository firmRepository;


    public GenerateReportServiceImpl(FirmRepository firmRepository) {
        this.firmRepository = firmRepository;
    }

    @Override
    public List<ReportDetails> getIncomes(int firmId, int luna) {
        List<ReportDetails> incomeList = new ArrayList<>();
        var incomes = firmRepository.findById(firmId).get().getIncomes().stream().filter(income -> income.getIssueDate().getMonthValue() == luna).collect(Collectors.toList());
        for (Income income : incomes)
            incomeList.add(new ReportDetails(income.getIssueDate(), income.getAmmount(), true));
        return incomeList;

    }

    @Override
    public List<ReportDetails> getInvoices(int firmId, int luna) {
        Firm firm=null;
        try {
            firm=firmRepository.findById(firmId).get();

        } catch (NoSuchElementException nsee) {
            nsee.printStackTrace();
        }
        var invoices=firm.getInvoices().stream().filter(invoice -> invoice.getIssueDate().getMonthValue() == luna).collect(Collectors.toList());
        List<ReportDetails> invoiceList = new ArrayList<>();
        for (Invoice invoice : invoices)
            for (Payment payment : invoice.getPaymentEntities())
                invoiceList.add(new ReportDetails(payment.getDate(), payment.getAmmount()));

        return invoiceList;
    }

    @Override
    public ByteArrayInputStream generateReport(int firmId, int luna) {
        List<ReportDetails> incomeList = getIncomes(firmId, luna);
        List<ReportDetails> invoiceList = getInvoices(firmId, luna);
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(80);
            table.setWidths(new int[]{4, 4, 4});
            Font headFont = FontFactory.getFont(FontFactory.TIMES_ROMAN);
            PdfPCell hcell;
            hcell = new PdfPCell(new Phrase("Date: ", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Credit: ", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Debit: ", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            PdfPCell cell;

            List<ReportDetails> orderTransactions = new ArrayList<>();
            for (ReportDetails reportIncome : incomeList)
                orderTransactions.add(reportIncome);
            for (ReportDetails reportInvoice : invoiceList)
                orderTransactions.add(reportInvoice);
            orderTransactions.sort((o1, o2) ->
            {
                return o1.getDate().compareTo(o2.getDate());
            });

            for (ReportDetails reportIncome : orderTransactions)
                if (reportIncome.isCredit()) {
                    cell = new PdfPCell(new Phrase(reportIncome.getDate().toString()));
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    cell = new PdfPCell(new Phrase(reportIncome.getAmmount().toString()));
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    cell = new PdfPCell(new Phrase(""));
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                } else {
                    cell = new PdfPCell(new Phrase(reportIncome.getDate().toString()));
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    cell = new PdfPCell(new Phrase(""));
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                    cell = new PdfPCell(new Phrase(reportIncome.getAmmount().toString()));
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);

                }

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
