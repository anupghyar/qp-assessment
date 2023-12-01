package com.test.qpassessment.controller;

import com.test.qpassessment.model.Order;
import com.test.qpassessment.service.OrderService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/orders")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Create new order along with multiple groceryItems
     *
     * @param order : List of grocery items which need to be saved
     * @return the created order and HttpStatus.CREATED if order was successfully created
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> createOrder(@RequestBody Order order) throws BadRequestException {

        order = (Order) orderService.createOrder(order);

        return new ResponseEntity<Order>(order, HttpStatus.CREATED);

    }

}
