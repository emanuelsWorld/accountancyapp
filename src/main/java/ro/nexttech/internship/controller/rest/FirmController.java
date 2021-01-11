package ro.nexttech.internship.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.nexttech.internship.domain.Firm;
import ro.nexttech.internship.domain.Income;
import ro.nexttech.internship.dto.FirmDto;
import ro.nexttech.internship.filters.firms.FirmRepository;
import ro.nexttech.internship.filters.firms.FirmSpecificationBuilder;

import java.util.List;

@RestController
@RequestMapping("/rest/firms")
public class FirmController {

    @Autowired
    private FirmRepository firmRepository;

    @GetMapping("")
    public List<FirmDto> searchFirms(@RequestParam(value = "search") String search) {
        Specification<Firm> spec = FirmSpecificationBuilder.getFirmSpec(search);
        return FirmDto.getDtoFromFirmList(firmRepository.findAll(spec));
    }

    @GetMapping("/{id}")
    public Income getFirm(@PathVariable("id") int id) {
        return null;
    }

    @PostMapping("/create")
    public ResponseEntity<Firm> createFirm(@RequestBody Firm firm) {
        if (firm == null) {
            return ResponseEntity.notFound().build();
        } else {
            return null;
        }
    }

    @DeleteMapping("/delete")
    public String deleteFirm() {
        return null;
    }

    @PutMapping("/update")
    public String updateFirm() {
        return null;
    }

}
