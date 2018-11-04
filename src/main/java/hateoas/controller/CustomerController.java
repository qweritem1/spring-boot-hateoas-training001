package hateoas.controller;

import hateoas.service.CustomerService;
import hateoas.service.OrderService;
import hateoas.model.Customer;
import hateoas.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/customers")
@EnableHypermediaSupport(type=HypermediaType.HAL)
public class CustomerController {
    @Autowired  CustomerService customerService;
    @Autowired  OrderService orderService;

    @RequestMapping(value = "/{customerId}", method = RequestMethod.GET)
    public Customer getCustomerById(@PathVariable final String customerId) {
        return customerService.getCustomerDetail(customerId);
    }

    @RequestMapping(value = "/{customerId}/{orderId}", method = RequestMethod.GET)
    public Order getOrderId( @PathVariable final String customerId, @PathVariable final String orderId ) {
        return customerService.getCustomerDetail(customerId).getOrders().get(orderId);
    }

    @RequestMapping(value = "/{customerId}/orders", method = RequestMethod.GET, produces = {"application/hal+json"})
    public Resources<Order> getOrderForCustomer(@PathVariable final String customerId) {
        List<Order> orders = orderService.getAllOrdersForCustomer(customerId);
        for (final Order order : orders) {
            Link selfLink = linkTo(methodOn(CustomerController.class).getOrderId(customerId, order.getOrderId())).withSelfRel();
            order.add(selfLink);
        }

        Link link = linkTo(methodOn(CustomerController.class).getOrderForCustomer(customerId)).withSelfRel();
        Resources<Order> result = new Resources<>(orders, link);
        return result;
    }

    @RequestMapping(method = RequestMethod.GET, produces = {"application/hal+json"})
    public Resources<Customer> getAllCustomers() {
        List<Customer> customers = customerService.allCustomers();
        for (final Customer customer : customers) {
            Link link = linkTo(methodOn(CustomerController.class).getCustomerById(customer.getCustomerId())).withSelfRel();
            customer.add(link);
            if ( orderService.getAllOrdersForCustomer(customer.getCustomerId()).size() > 0 ) {
                Link Orderlink = linkTo(methodOn(CustomerController.class).getOrderForCustomer(customer.getCustomerId())).withRel("allOrders");
                customer.add(Orderlink);
            }
        }

        Link link = linkTo(CustomerController.class).withSelfRel();
        Resources<Customer> result = new Resources<>( customers, link);
        return result;
    }

}
