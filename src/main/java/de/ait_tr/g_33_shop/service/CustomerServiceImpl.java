package de.ait_tr.g_33_shop.service;

import de.ait_tr.g_33_shop.domain.dto.CustomerDto;
import de.ait_tr.g_33_shop.domain.entity.Customer;
import de.ait_tr.g_33_shop.domain.entity.Product;
import de.ait_tr.g_33_shop.exception_handling.exceptions.CustomerNotFoundException;
import de.ait_tr.g_33_shop.repository.CustomerRepository;
import de.ait_tr.g_33_shop.service.interfaces.CustomerService;
import de.ait_tr.g_33_shop.service.interfaces.ProductService;
import de.ait_tr.g_33_shop.service.mapping.CustomerMappingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;
    private final CustomerMappingService mappingService;
    private final ProductService productService;

    public CustomerServiceImpl(CustomerRepository repository, CustomerMappingService mappingService, ProductService productService) {
        this.repository = repository;
        this.mappingService = mappingService;
        this.productService = productService;
    }

    @Override
    public CustomerDto save(CustomerDto customer) {
        return null;
    }

    @Override
    public List<CustomerDto> getAllActiveCustomers() {
        return null;
    }

    @Override
    public CustomerDto getById(Long id) {
        return null;
    }

    @Override
    public CustomerDto update(CustomerDto customer) {
        return null;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Customer customer = repository.findById(id).orElseThrow(
                () -> new CustomerNotFoundException(id)
        );

        customer.setActive(false);
    }

    @Override
    @Transactional
    public void deleteByName(String name) {
        Customer customer = repository.findByName(name).orElseThrow(
                () -> new CustomerNotFoundException(name)
        );

        customer.setActive(false);
    }

    @Override
    @Transactional
    public void restoreById(Long id) {
        Customer customer = repository.findById(id).orElseThrow(
                () -> new CustomerNotFoundException(id)
        );

        customer.setActive(true);
    }

    @Override
    public long getActiveCustomersNumber() {
        return 0;
    }

    @Override
    public BigDecimal getTotalCostOfCustomersProducts(Long customerId) {
        Customer customer = repository.findById(customerId).orElseThrow(
                () -> new CustomerNotFoundException(customerId)
        );

        return customer.getCart().getActiveProductsTotalCost();
    }

    @Override
    public BigDecimal getAverageCostOfCustomersProducts(Long customerId) {
        Customer customer = repository.findById(customerId).orElseThrow(
                () -> new CustomerNotFoundException(customerId)
        );

        return customer.getCart().getActiveProductsAverageCost();
    }

    @Override
    @Transactional
    public void addProductToCustomersCart(Long customerId, Long productId) {
        Customer customer = repository.findById(customerId).orElseThrow(
                () -> new CustomerNotFoundException(customerId)
        );

        Product product = productService.getEntityById(productId);

        customer.getCart().addProduct(product);
    }

    @Override
    public void removeProductFromCustomersCart(Long customerId, Long productId) {

    }

    @Override
    public void clearCustomersCart(Long customerId) {

    }
}