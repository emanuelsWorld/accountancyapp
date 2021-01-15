package ro.nexttech.internship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ro.nexttech.internship.dto.IncomeDto;
import ro.nexttech.internship.service.IncomeService;

@RestController
@RequestMapping("/rest/incomes")
public class IncomeController {

    @Autowired
    private IncomeService incomeService;

    @GetMapping("/{id}")
    public IncomeDto getIncome(@PathVariable("id") int id) {
        return incomeService.getDtoFromIncome(incomeService.findById(id));
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping
    public IncomeDto createIncome(@RequestBody IncomeDto incomeDto, Authentication auth) {
        if (incomeDto == null) {
            return null;
        } else {
            return incomeService.saveIncomeDto(incomeDto, auth.getName());
        }
    }

    @PreAuthorize("hasRole('MANAGER')")
    @DeleteMapping("{id}")
    public String deleteIncome(@PathVariable("id") int id) {
        if(incomeService.deleteById(id)) {
            return "Income with id " + id + " deleted";
        }
        return "Deletion unsuccessful";
    }

    @PutMapping("{id}")
    public String updateIncome() {
        return null;
    }
}
