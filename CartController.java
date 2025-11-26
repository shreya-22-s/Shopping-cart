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
@RequestMapping("/carts")
@CrossOrigin(origins = "*")
public class CartController {
    
    @Autowired
    private DataStore dataStore;
    @PostMapping
    public ResponseEntity<?> createOrUpdateCart(
            @RequestHeader("Authorization") String token,
            @RequestBody Map<String, List<Long>> request) {

        User user = dataStore.getUsers().stream()
            .filter(u -> token.equals(u.getToken()))
            .findFirst()
            .orElse(null);
        
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("error", "Login karo pehle"));
        }
        
        List<Long> itemIds = request.get("item_ids");
        if (itemIds == null || itemIds.isEmpty()) {
            return ResponseEntity.badRequest()
                .body(Map.of("error", "item_ids chahiye"));
        }
        Cart cart = dataStore.getCarts().stream()
            .filter(c -> c.getUserId().equals(user.getId()))
            .findFirst()
            .orElse(null);
        
        if (cart == null) {
            cart = new Cart();
            cart.setId(dataStore.getNextCartId());
            cart.setUserId(user.getId());
            dataStore.getCarts().add(cart);
        }
        for (Long itemId : itemIds) {
            Item item = dataStore.getItems().stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst()
                .orElse(null);
            
            if (item != null) {
                CartItem cartItem = new CartItem();
                cartItem.setId(dataStore.getNextCartItemId());
                cartItem.setCartId(cart.getId());
                cartItem.setItemId(itemId);
                cartItem.setItem(item);
                cart.getCartItems().add(cartItem);
            }
        }
        
        return ResponseEntity.ok(cart);
    }
    @GetMapping
    public ResponseEntity<List<Cart>> listCarts() {
        return ResponseEntity.ok(dataStore.getCarts());
    }
}
