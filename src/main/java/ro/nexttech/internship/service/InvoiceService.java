package ro.nexttech.internship.service;

import java.util.List;

import ro.nexttech.internship.dto.InvoiceDto;

public interface InvoiceService {
    List<InvoiceDto> searchInvoices(String search,String sortField, String sortDirection, Integer pageSize, Integer pageIndex);
}