package ro.nexttech.internship.controller.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.nexttech.internship.domain.Firm;
import ro.nexttech.internship.domain.Income;

@RestController
@RequestMapping("/rest/firms")
public class FirmController {

    @GetMapping("/{id}")
    public Income getFirm(@PathVariable("id") int id) {
        return null;
    }

    @PutMapping("/create")
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

    @PostMapping("/update")
    public String updateFirm() {
        return null;
    }

}
