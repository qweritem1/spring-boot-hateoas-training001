package hateoas.service;

import hateoas.model.Customer;
import hateoas.model.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {
    private Map<String, Customer> customerMap;
    private Map<String, Order>    customerOneOrderMap;
    private Map<String, Order>    customerTwoOrderMap;
    private Map<String, Order>    customerThreeOrderMap;

    public OrderServiceImpl() {
        customerMap = new HashMap<>();
        customerOneOrderMap = new HashMap<>();
        customerTwoOrderMap = new HashMap<>();
        customerThreeOrderMap = new HashMap<>();

        Customer customerOne = new Customer("cust001", "John", "SG");
        Customer customerTwo = new Customer("cust002", "Mike", "LS");
        Customer customerThree = new Customer("cust003", "David", "ST");

        customerOneOrderMap.put("cust001", new Order("order_001", 100, 10));
        customerOneOrderMap.put("cust001", new Order("order_002", 200, 20));
        customerTwoOrderMap.put("cust002", new Order("order_003", 300, 30));
        customerTwoOrderMap.put("cust002", new Order("order_004", 400, 40));

        customerOne.setOrders(customerOneOrderMap);
        customerTwo.setOrders(customerTwoOrderMap);
        customerThree.setOrders(customerThreeOrderMap);

        customerMap.put("cust001", customerOne);
        customerMap.put("cust002", customerTwo);
        customerMap.put("cust003", customerThree);
    }

    @Override
    public List<Order> getAllOrdersForCustomer(String customerId) {
        return new ArrayList<>(customerMap.get(customerId).getOrders().values());
    }

    @Override
    public Order getOrderByIdForCustomer(String customerId, String orderId) {
        return customerMap.get(customerId).getOrders().get(orderId);
    }
}
