package hateoas.service;

import hateoas.model.Order;

import java.util.List;

public interface OrderService {
    public List<Order> getAllOrdersForCustomer(final String customerId);
    public Order getOrderByIdForCustomer(final String customerId, final String orderId);
}
