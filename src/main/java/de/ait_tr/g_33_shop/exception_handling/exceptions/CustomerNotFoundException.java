package de.ait_tr.g_33_shop.exception_handling.exceptions;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(Long customerId) {
        super(String.format("Customer with id %d not found", customerId));
    }

    public CustomerNotFoundException(String customerName) {
        super(String.format("Customer with name %s not found", customerName));
    }
}