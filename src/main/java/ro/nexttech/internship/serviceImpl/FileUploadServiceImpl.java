package ro.nexttech.internship.serviceImpl;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ro.nexttech.internship.domain.Invoice;
import ro.nexttech.internship.repository.InvoiceRepository;
import ro.nexttech.internship.service.FileUploadService;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    private InvoiceRepository invoiceRepository;

    public FileUploadServiceImpl(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }


    @Override
    public void uploadToDb(MultipartFile file, Integer id) {
        Invoice invoice = new Invoice();
        try {
            Blob blob = new SerialBlob(file.getBytes());
            invoice = invoiceRepository.getOne(id);
            invoice.setFileData(blob);
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
        Invoice invoice = invoiceRepository.getOne(id);
        return invoice.getFileData();
    }
}
