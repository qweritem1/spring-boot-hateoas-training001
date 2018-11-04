package hateoas.service;

import hateoas.model.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService {
    private Map<String, Customer> customerMap;

    public CustomerServiceImpl() {
        Customer customerOne = new Customer("cust001", "John", "SG");
        Customer customerTwo = new Customer("cust002", "Mike", "LS");
        Customer customerThree = new Customer("cust003", "David", "ST");

        customerMap = new HashMap<>();
        customerMap.put("cust001", customerOne);
        customerMap.put("cust002", customerTwo);
        customerMap.put("cust003", customerThree);
    }

    @Override
    public List<Customer> allCustomers() {
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public Customer getCustomerDetail(String customerId) {
        return customerMap.get(customerId);
    }
}
