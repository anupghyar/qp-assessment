package com.test.qpassessment.service.impl;

import com.test.qpassessment.model.GroceryItem;
import com.test.qpassessment.model.Order;
import com.test.qpassessment.repository.GroceryItemRepo;
import com.test.qpassessment.repository.OrderRepository;
import com.test.qpassessment.service.OrderService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private GroceryItemRepo groceryItemRepo;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, GroceryItemRepo groceryItemRepo) {
        this.orderRepository = orderRepository;
        this.groceryItemRepo = groceryItemRepo;
    }

    @Override
    public Order createOrder(Order order) {

        Set<GroceryItem> groceries = new HashSet<>();
        order.getGroceryItems().forEach(groceryItem -> {
            Optional<GroceryItem> groceryItemRepoById = groceryItemRepo.findById(groceryItem.getId());
            try {
                groceryItemRepoById.map(item -> {
                    if (groceryItem.getQuantity() > item.getQuantity()) {
                        try {
                            throw new BadRequestException(item.getName() + " is out of stock");
                        } catch (BadRequestException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    item.setQuantity(item.getQuantity() - groceryItem.getQuantity());
                    groceries.add(item);
                    return item;
                }).orElseThrow(() -> new BadRequestException("Grocery Item not present"));
            } catch (BadRequestException e) {
                throw new RuntimeException(e);
            }
        });
        order.setGroceryItems(groceries);
        order.setStatus("PLACED");
        Order savedOrder = orderRepository.save(order);
        return savedOrder;
    }
}
