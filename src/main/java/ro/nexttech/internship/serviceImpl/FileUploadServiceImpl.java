package ro.nexttech.internship.serviceImpl;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ro.nexttech.internship.domain.Invoice;
import ro.nexttech.internship.repository.InvoiceRepository;
import ro.nexttech.internship.service.FileUploadService;
import ro.nexttech.internship.service.InvoiceService;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import javax.validation.constraints.Null;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Optional;


@Service
public class FileUploadServiceImpl implements FileUploadService {

    private InvoiceService invoiceService;

    public FileUploadServiceImpl(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }


    @Override
    public boolean uploadToDb(MultipartFile file, Integer id) {
        Invoice invoice = new Invoice();
        Blob blob = null;
        try {
            blob = new SerialBlob(file.getBytes());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        invoice = invoiceService.findById(id);
        if (invoice != null) {
            invoice.setFileData(blob);
            invoiceService.saveInvoice(invoice);
            return true;
        }

        return false;
    }

    @Override
    public Blob downloadFile(Integer id) {
        Invoice invoice;
        if (Invoice.getInvoiceMap().get(id) != null) {
            invoice = Invoice.getInvoiceMap().get(id);
        } else
            invoice = invoiceService.findById(id);
        if (invoice != null)
            return invoice.getFileData();
        else
            return null;
    }
}
