package com.ecommerce.storage;

import com.ecommerce.model.*;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class DataStore {
    
    private List<User> users = new ArrayList<>();
    private List<Item> items = new ArrayList<>();
    private List<Cart> carts = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();
    
    private AtomicLong userId = new AtomicLong(1);
    private AtomicLong itemId = new AtomicLong(1);
    private AtomicLong cartId = new AtomicLong(1);
    private AtomicLong orderId = new AtomicLong(1);
    private AtomicLong cartItemId = new AtomicLong(1);
    private AtomicLong orderItemId = new AtomicLong(1);
    
    // Getters
    public List<User> getUsers() { return users; }
    public List<Item> getItems() { return items; }
    public List<Cart> getCarts() { return carts; }
    public List<Order> getOrders() { return orders; }
    
    // ID generators
    public Long getNextUserId() { return userId.getAndIncrement(); }
    public Long getNextItemId() { return itemId.getAndIncrement(); }
    public Long getNextCartId() { return cartId.getAndIncrement(); }
    public Long getNextOrderId() { return orderId.getAndIncrement(); }
    public Long getNextCartItemId() { return cartItemId.getAndIncrement(); }
    public Long getNextOrderItemId() { return orderItemId.getAndIncrement(); }
}
