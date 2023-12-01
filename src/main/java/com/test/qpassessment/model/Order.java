package com.test.qpassessment.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;


@SuppressWarnings("serial")
@Entity
@Table(name = "ORDER_TABLE")
public class Order {

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String address;

    @Column(name = "order_date")
    private String orderDate;

    private String status;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="order_id")
    private Set<GroceryItem> groceryItems = new HashSet<>();

    public Order() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<GroceryItem> getGroceryItems() {
        return groceryItems;
    }

    public void setGroceryItems(Set<GroceryItem> groceryItems) {
        this.groceryItems = groceryItems;
    }
}
