package de.ait_tr.g_33_shop.controller;

import de.ait_tr.g_33_shop.domain.dto.CustomerDto;
import de.ait_tr.g_33_shop.service.interfaces.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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

    // GET -> localhost:8080/customers/number

    @GetMapping("/number")
    public long getActiveCustomersNumber() {
        return service.getActiveCustomersNumber();
    }

    // GET -> localhost:8080/customers/5/products_cost

    @GetMapping("/{customerId}/products_cost")
    public BigDecimal getTotalCostOfCustomersProducts(@PathVariable Long customerId) {
        return service.getTotalCostOfCustomersProducts(customerId);
    }

    // GET -> localhost:8080/customers/5/avg_product_cost

    @GetMapping("/{customerId}/avg_product_cost")
    public BigDecimal getAverageCostOfCustomersProducts(@PathVariable Long customerId) {
        return service.getAverageCostOfCustomersProducts(customerId);
    }

    // PUT -> localhost:8080/customers/5/add_product/7

    @PutMapping("/{customerId}/add_product/{productId}")
    public void addProductToCustomersCart(
            @PathVariable Long customerId,
            @PathVariable Long productId
    ) {
        service.addProductToCustomersCart(customerId, productId);
    }

    // DELETE -> localhost:8080/customers/5/remove_product/7

    @DeleteMapping("/{customerId}/remove_product/{productId}")
    public void removeProductFromCustomersCart(
            @PathVariable Long customerId,
            @PathVariable Long productId
    ) {
        service.removeProductFromCustomersCart(customerId, productId);
    }

    // DELETE -> localhost:8080/customers/5/clear_cart

    @DeleteMapping("/{customerId}/clear_cart")
    public void clearCustomersCart(@PathVariable Long customerId) {
        service.clearCustomersCart(customerId);
    }
}