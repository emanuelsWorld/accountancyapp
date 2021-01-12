package ro.nexttech.internship.serviceImpl;



import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ro.nexttech.internship.domain.Invoice;
import ro.nexttech.internship.repository.InvoiceRepository;
import ro.nexttech.internship.service.FileUploadService;

import javax.sql.rowset.serial.SerialBlob;

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
    public boolean uploadToDb(MultipartFile file, Integer id) {
        Invoice invoice = new Invoice();
        try {
            Blob blob = new SerialBlob(file.getBytes());

            var value = invoiceRepository.findById(id);
            if (value.isPresent()) {
                invoice = value.get();
                invoice.setFileData(blob);
                invoiceRepository.save(invoice);
                return true;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public Blob downloadFile(Integer id) {
        var val = invoiceRepository.findById(id);

        if (val.isPresent())
            return val.get().getFileData();
        else {
            return null;
        }
    }
}
