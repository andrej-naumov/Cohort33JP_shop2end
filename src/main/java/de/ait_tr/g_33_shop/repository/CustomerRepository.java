package de.ait_tr.g_33_shop.repository;

import de.ait_tr.g_33_shop.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByName(String name);
}