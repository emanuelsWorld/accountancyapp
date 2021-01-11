package ro.nexttech.internship.controller.rest;

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

    @PostMapping("/create")
    public ResponseEntity<Provider> createProvider(@RequestBody Provider provider) {
        if (provider == null) {
            return ResponseEntity.notFound().build();
        } else {
            return null;
        }
    }

    @DeleteMapping("/delete")
    public String deleteProvider() {
        return null;
    }

    @PutMapping("/update")
    public String updateProvider() {
        return null;
    }
}
