package com.ecommerce.controller;

import com.ecommerce.model.*;
import com.ecommerce.storage.DataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "*")
public class OrderController {
    
    @Autowired
    private DataStore dataStore;
    @PostMapping
    public ResponseEntity<?> createOrder(
            @RequestHeader("Authorization") String token,
            @RequestBody Map<String, Long> request) {
        User user = dataStore.getUsers().stream()
            .filter(u -> token.equals(u.getToken()))
            .findFirst()
            .orElse(null);
        
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("error", "Login karo pehle"));
        }
        
        Long cartId = request.get("cart_id");
        if (cartId == null) {
            return ResponseEntity.badRequest()
                .body(Map.of("error", "cart_id chahiye"));
        }
        Cart cart = dataStore.getCarts().stream()
            .filter(c -> c.getId().equals(cartId) && 
                        c.getUserId().equals(user.getId()))
            .findFirst()
            .orElse(null);
        
        if (cart == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", "Cart nahi mila"));
        }
        Order order = new Order();
        order.setId(dataStore.getNextOrderId());
        order.setUserId(user.getId());
        for (CartItem cartItem : cart.getCartItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setId(dataStore.getNextOrderItemId());
            orderItem.setOrderId(order.getId());
            orderItem.setItemId(cartItem.getItemId());
            orderItem.setItem(cartItem.getItem());
            order.getOrderItems().add(orderItem);
        }
        
        dataStore.getOrders().add(order);
        dataStore.getCarts().remove(cart);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }
    @GetMapping
    public ResponseEntity<List<Order>> listOrders() {
        return ResponseEntity.ok(dataStore.getOrders());
    }
}
