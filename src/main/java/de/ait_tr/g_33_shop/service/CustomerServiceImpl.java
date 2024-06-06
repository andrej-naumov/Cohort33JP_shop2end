package de.ait_tr.g_33_shop.service;

import de.ait_tr.g_33_shop.domain.entity.Customer;
import de.ait_tr.g_33_shop.service.interfaces.CustomerService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Override
    public Customer save(Customer customer) {
        return null;
    }

    @Override
    public List<Customer> getAllActiveCustomers() {
        return null;
    }

    @Override
    public Customer getById(Long id) {
        return null;
    }

    @Override
    public Customer update(Customer customer) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void deleteByName(String name) {

    }

    @Override
    public void restoreById(Long id) {

    }

    @Override
    public long getActiveCustomersNumber() {
        return 0;
    }

    @Override
    public BigDecimal getTotalCostOfCustomersProducts(Long customerId) {
        return null;
    }

    @Override
    public BigDecimal getAverageCostOfCustomersProducts(Long customerId) {
        return null;
    }

    @Override
    public void addProductToCustomersCart(Long customerId, Long productId) {

    }

    @Override
    public void removeProductFromCustomersCart(Long customerId, Long productId) {

    }

    @Override
    public void clearCustomersCart(Long customerId) {

    }
}