package de.ait_tr.g_33_shop.service.interfaces;

import de.ait_tr.g_33_shop.domain.dto.CustomerDto;

import java.math.BigDecimal;
import java.util.List;

public interface CustomerService {

    CustomerDto save(CustomerDto customer);
    List<CustomerDto> getAllActiveCustomers();
    CustomerDto getById(Long id);
    CustomerDto update(CustomerDto customer);
    void deleteById(Long id);
    void deleteByName(String name);
    void restoreById(Long id);
    long getActiveCustomersNumber();
    BigDecimal getTotalCostOfCustomersProducts(Long customerId);
    BigDecimal getAverageCostOfCustomersProducts(Long customerId);
    void addProductToCustomersCart(Long customerId, Long productId);
    void removeProductFromCustomersCart(Long customerId, Long productId);
    void clearCustomersCart(Long customerId);
}