package ro.nexttech.internship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.nexttech.internship.domain.Firm;
import ro.nexttech.internship.domain.Income;
import ro.nexttech.internship.dto.FirmDto;
import ro.nexttech.internship.filters.firms.FirmSpecificationBuilder;
import ro.nexttech.internship.service.FirmService;

import java.util.List;

@RestController
@RequestMapping("/rest/firms")
public class FirmController {


    private FirmService firmService;

    @Autowired
    public void setFirmService(FirmService firmService) {
        this.firmService = firmService;
    }

    @GetMapping
    public List<FirmDto> searchFirms(@RequestParam(value = "search") String search) {
        Specification<Firm> spec = FirmSpecificationBuilder.getFirmSpec(search);
        return FirmDto.getDtoFromFirmList(firmService.findAll(spec));
    }

    @GetMapping("/{id}")
    public Income getFirm(@PathVariable("id") int id) {
        return null;
    }

    @PostMapping
    public ResponseEntity<Firm> createFirm(@RequestBody Firm firm) {
        if (firm == null) {
            return ResponseEntity.notFound().build();
        } else {
            return null;
        }
    }

    @DeleteMapping
    public String deleteFirm() {
        return null;
    }

    @PutMapping("/{id}")
    public String updateFirm() {
        return null;
    }

}
