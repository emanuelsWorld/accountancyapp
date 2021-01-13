package ro.nexttech.internship.serviceImpl;



import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ro.nexttech.internship.domain.Invoice;
import ro.nexttech.internship.repository.InvoiceRepository;
import ro.nexttech.internship.service.FileUploadService;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import javax.validation.constraints.Null;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Optional;


@Service
public class FileUploadServiceImpl implements FileUploadService {

    private InvoiceRepository invoiceRepository;

    public FileUploadServiceImpl(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }


    @Override
    public boolean uploadToDb(MultipartFile file, Integer id) {
        Invoice invoice = new Invoice();
        Optional<Invoice> value = null;
        try {
            Blob blob = new SerialBlob(file.getBytes());

                value = invoiceRepository.findById(id);

            if (value.isPresent()) {
                invoice = value.get();
                invoice.setFileData(blob);
                invoiceRepository.save(invoice);
                return true;
            }

        } catch (SerialException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }
        return false;
    }

    @Override
    public Blob downloadFile(Integer id) {
        Optional<Invoice> invoice;
        if(Invoice.getInvoiceMap().get(id)!=null) {
            invoice= Optional.ofNullable(Invoice.getInvoiceMap().get(id));
        }
        else
            invoice = invoiceRepository.findById(id);

        if (invoice.isPresent())
            return invoice.get().getFileData();
        else {
            return null;
        }
    }
}
