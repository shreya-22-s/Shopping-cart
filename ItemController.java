package com.ecommerce.controller;

import com.ecommerce.model.Item;
import com.ecommerce.storage.DataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/items")
@CrossOrigin(origins = "*")
public class ItemController {
    
    @Autowired
    private DataStore dataStore;
    @PostMapping
    public ResponseEntity<?> createItem(@RequestBody Item item) {
        // Check karo name aur price hai ya nahi
        if (item.getName() == null || item.getPrice() == null) {
            return ResponseEntity.badRequest()
                .body(Map.of("error", "Name aur price chahiye"));
        }
        
        item.setId(dataStore.getNextItemId());
        dataStore.getItems().add(item);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(item);
    }
    
    @GetMapping
    public ResponseEntity<List<Item>> listItems() {
        return ResponseEntity.ok(dataStore.getItems());
    }
}
