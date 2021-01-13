package ro.nexttech.internship.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.nexttech.internship.domain.Income;
import ro.nexttech.internship.domain.Provider;

@RestController
@RequestMapping("/rest/providers")
public class ProviderController {

    @GetMapping("/{id}")
    public Income getProvider(@PathVariable("id") int id) {
        return null;
    }

    @PostMapping
    public ResponseEntity<Provider> createProvider(@RequestBody Provider provider) {
        if (provider == null) {
            return ResponseEntity.notFound().build();
        } else {
            return null;
        }
    }

    @DeleteMapping
    public String deleteProvider() {
        return null;
    }

    @PutMapping("/{id}")
    public String updateProvider() {
        return null;
    }
}
