package ro.nexttech.internship.serviceImpl;

import org.apache.tomcat.jni.File;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ro.nexttech.internship.domain.Invoice;
import ro.nexttech.internship.repository.InvoiceRepository;
import ro.nexttech.internship.service.FileUploadService;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;
import java.util.Set;

@Service
public class FileUploadServiceImpl implements FileUploadService {
    private String uploadFolderPath="/Users/nexttech/Desktop/";

    private InvoiceRepository invoiceRepository;

    public FileUploadServiceImpl(InvoiceRepository invoiceRepository) {
        this.invoiceRepository=invoiceRepository;
    }

    @Override
    public void uploadToLocal(MultipartFile file) {
        try {
            byte[] data=file.getBytes();
            Path path= Paths.get(uploadFolderPath+file.getOriginalFilename());
            Files.write(path,data);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void uploadToDb(MultipartFile file) {
        Invoice invoice=new Invoice();
        try
        {
            Blob blob=new SerialBlob(file.getBytes());
            invoice.setInvoiceTotal(444);
            invoice.setDueDate(new Date());
            invoice.setIssueDate(new Date());
            invoice.setPaymentTotal(555);
            invoice.setNumber(555);
            invoice.setFileData(blob);
            invoiceRepository.save(invoice);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SerialException throwables) {
            throwables.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Blob downloadFile(Integer id) {
        Invoice invoice=invoiceRepository.getOne(id);
        return invoice.getFileData();
    }
}
