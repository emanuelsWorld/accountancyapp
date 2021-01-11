package ro.nexttech.internship.controller.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.nexttech.internship.domain.Income;

@RestController
@RequestMapping("/rest/incomes")
public class IncomeController {

    @GetMapping("/{id}")
    public Income getIncome(@PathVariable("id") int id) {
        return null;
    }

    @PostMapping
    public ResponseEntity<Income> createIncome(@RequestBody Income income) {
        if (income == null) {
            return ResponseEntity.notFound().build();
        } else {
            return null;
        }
    }

    @DeleteMapping
    public String deleteIncome() {
        return null;
    }

    @PutMapping("{/id}")
    public String updateIncome() {
        return null;
    }
}
