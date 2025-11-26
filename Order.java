package com.ecommerce.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private Long id;                      
    private Long userId;                  
    private List<OrderItem> orderItems;   
    
    public Order() {
        this.orderItems = new ArrayList<>();
    }
    
    public Order(Long id, Long userId) {
        this.id = id;
        this.userId = userId;
        this.orderItems = new ArrayList<>();
    }
    
    // Getters aur Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    
    public List<OrderItem> getOrderItems() { return orderItems; }
    public void setOrderItems(List<OrderItem> orderItems) { this.orderItems = orderItems; }
}
