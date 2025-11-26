package com.ecommerce.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private Long id;                      
    private Long userId;                  
    private List<CartItem> cartItems;     
    
    public Cart() {
        this.cartItems = new ArrayList<>();
    }
    
    public Cart(Long id, Long userId) {
        this.id = id;
        this.userId = userId;
        this.cartItems = new ArrayList<>();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    
    public List<CartItem> getCartItems() { return cartItems; }
    public void setCartItems(List<CartItem> cartItems) { this.cartItems = cartItems; }
}
