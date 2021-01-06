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

    @PutMapping("/create")
    public ResponseEntity<Income> createIncome(@RequestBody Income income) {
        if (income == null) {
            return ResponseEntity.notFound().build();
        } else {
            return null;
        }
    }

    @DeleteMapping("/delete")
    public String deleteIncome() {
        return null;
    }

    @PostMapping("/update")
    public String updateIncome() {
        return null;
    }
}
