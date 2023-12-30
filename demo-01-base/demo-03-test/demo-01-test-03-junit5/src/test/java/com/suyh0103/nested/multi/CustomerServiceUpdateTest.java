package com.suyh0103.nested.multi;

import com.suyh0103.Customer;
import com.suyh0103.CustomerService;
import com.suyh0103.CustomerServiceJDBC;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CustomerServiceUpdateTest {

    CustomerService customerService;

    @BeforeEach
    void createNewObjectForAll() {
        System.out.println("New CustomerService()");
        customerService = new CustomerServiceJDBC();
    }

    @Test
    void update_with_new() {
        customerService.update(new Customer());
    }

    @Test
    void update_with_existing() {
        customerService.update(new Customer());
    }

}
