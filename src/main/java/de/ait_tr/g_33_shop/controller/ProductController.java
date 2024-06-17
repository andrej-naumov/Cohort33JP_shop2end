package de.ait_tr.g_33_shop.controller;

import de.ait_tr.g_33_shop.domain.dto.ProductDto;
import de.ait_tr.g_33_shop.service.interfaces.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/products")
@Tag(name = "Product controller", description = "Controller for various operations with Products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

// CRUD - Create (POST), Read (GET), Update (PUT), Delete (DELETE)

    // Create: POST -> localhost:8080/products

    // Три уровня доступа к приложению:
    // 1. Получить все продукты могут все пользователи, в том числе анонимные
    // 2. Получить продукт по идентификатору могут только аутентифицированные пользователи с любой ролью
    // 3. Сохранить продукт в базу данных может только администратор

    @PostMapping
    public ProductDto save(
            @RequestBody
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Instance of a Product")
            ProductDto product
    ) {
        return service.save(product);
    }

    // Read: GET -> localhost:8080/products?id=3

    @Operation(
            summary = "Get product by id",
            description = "Getting one product that exists in the database by its id"
    )
    @GetMapping
    public ProductDto getById(
            @RequestParam
            @Parameter(description = "Product unique identifier")
            Long id
    ) {
        return service.getById(id);
    }

    @GetMapping("/all")
    public List<ProductDto> getAll() {
        return service.getAllActiveProducts();
    }

    // Update: PUT -> localhost:8080/products

    @PutMapping
    public ProductDto update(@RequestBody ProductDto product) {
        return service.update(product);
    }

    // Delete: DELETE -> localhost:8080/products?id=3

    @DeleteMapping
    public void delete(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String title
    ) {
        if (id != null) {
            service.deleteById(id);
        } else if (title != null) {
            service.deleteByTitle(title);
        }
    }

    @PutMapping("/restore")
    public void restore(@RequestParam Long id) {
        service.restoreById(id);
    }

    @GetMapping("/quantity")
    public long getProductsQuantity() {
        return service.getAllActiveProductsQuantity();
    }

    // GET -> http://localhost:8080/products/total-price

    @GetMapping("/total-price")
    public BigDecimal getTotalPrice() {
        return service.getAllActiveProductsTotalPrice();
    }

    @GetMapping("/average-price")
    public BigDecimal getAveragePrice() {
        return service.getAllActiveProductsAveragePrice();
    }
}