package hateoas.service;

import hateoas.model.Customer;

import java.util.List;

public interface CustomerService {
    public List<Customer> allCustomers();
    public Customer getCustomerDetail(final String customerId);
}
