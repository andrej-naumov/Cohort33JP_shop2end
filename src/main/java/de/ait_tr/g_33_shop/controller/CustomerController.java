package de.ait_tr.g_33_shop.controller;

import de.ait_tr.g_33_shop.domain.dto.CustomerDto;
import de.ait_tr.g_33_shop.service.interfaces.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping
    public CustomerDto save(@RequestBody CustomerDto customer) {
        return service.save(customer);
    }

    @GetMapping
    public List<CustomerDto> get(@RequestParam(required = false) Long id) {
        if (id == null) {
            return service.getAllActiveCustomers();
        } else {
            return List.of(service.getById(id));
        }
    }

    @PutMapping
    public CustomerDto update(@RequestBody CustomerDto customer) {
        return service.update(customer);
    }

    @DeleteMapping
    public void delete(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String name
    ) {
        if (id != null) {
            service.deleteById(id);
        } else if (name != null) {
            service.deleteByName(name);
        }
    }

    @PutMapping("/restore")
    public void restore(@RequestParam Long id) {
        service.restoreById(id);
    }
}