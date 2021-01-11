package ro.nexttech.internship.serviceImpl;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;

import org.springframework.stereotype.Service;
import ro.nexttech.internship.domain.Income;
import ro.nexttech.internship.domain.Invoice;
import ro.nexttech.internship.domain.Payment;
import ro.nexttech.internship.repository.FirmRepository;
import ro.nexttech.internship.repository.IncomeRepository;
import ro.nexttech.internship.service.GenerateReportService;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.Month;
import java.util.stream.Collectors;


@Service
public class GenerateReportServiceImpl implements GenerateReportService {

    private FirmRepository firmRepository;
    private IncomeRepository incomeRepository;

    public GenerateReportServiceImpl(FirmRepository firmRepository, IncomeRepository incomeRepository) {
        this.firmRepository = firmRepository;
        this.incomeRepository=incomeRepository;
    }

    @Override
    public double calculateIncomes(int firmId, int luna) {
        var incomes = firmRepository.findById(firmId).get().getIncomes();
        var total = incomes.stream().filter(income -> income.getDate().getMonthValue() == luna).mapToDouble(Income::getAmmount).sum();
        return total;
    }

    public double calculateCosts(int firmId, int luna) {
        var invoices = firmRepository.findById(firmId).get().getInvoices();
        double paymentTotal=0;
        var currentInvoices = invoices.stream().filter(invoice -> invoice.getIssueDate().getMonthValue() == luna).collect(Collectors.toList());
        for(Invoice invoice:currentInvoices)
            paymentTotal+=invoice.getPaymentEntities().stream().mapToDouble(Payment::getAmmount).sum();

        return paymentTotal;
    }

    public Document generateReport(int firmId, int luna, ByteArrayOutputStream baos) {
        var incomeTotal = calculateIncomes(firmId, luna);
        var costTotal = calculateCosts(firmId, luna);
        String text=new String("Income total: "+incomeTotal+"\n"+"Cost total: "+costTotal+"\n"+"Balance: "+(incomeTotal-costTotal)+"\n"+"Month: "+Month.of(luna).name());
        Document document=new Document();
        try {
            PdfWriter.getInstance(document,baos);
            document.open();
            Chunk chunk=new Chunk(text);
            document.add(chunk);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        document.close();
        return document;


    }
}
