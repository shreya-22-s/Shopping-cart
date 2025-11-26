package com.ecommerce.controller;

import com.ecommerce.model.User;
import com.ecommerce.storage.DataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")  // Frontend se connect karne ke liye
public class UserController {
    
    @Autowired
    private DataStore dataStore;
    
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");
        
        if (username == null || password == null) {
            return ResponseEntity.badRequest()
                .body(Map.of("error", "Username aur password chahiye"));
        }
        
        boolean exists = dataStore.getUsers().stream()
            .anyMatch(u -> u.getUsername().equals(username));
        
        if (exists) {
            return ResponseEntity.badRequest()
                .body(Map.of("error", "Username pehle se hai"));
        }
        
        User newUser = new User();
        newUser.setId(dataStore.getNextUserId());
        newUser.setUsername(username);
        newUser.setPassword(password);
        
        dataStore.getUsers().add(newUser);
        
        Map<String, Object> response = new HashMap<>();
        response.put("id", newUser.getId());
        response.put("username", newUser.getUsername());
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> listUsers() {
        List<Map<String, Object>> safeUsers = new ArrayList<>();
        
        // Har user ka data bhejo (password ke bina)
        for (User user : dataStore.getUsers()) {
            Map<String, Object> safeUser = new HashMap<>();
            safeUser.put("id", user.getId());
            safeUser.put("username", user.getUsername());
            safeUsers.add(safeUser);
        }
        
        return ResponseEntity.ok(safeUsers);
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");
        
        User user = dataStore.getUsers().stream()
            .filter(u -> u.getUsername().equals(username) && 
                        u.getPassword().equals(password))
            .findFirst()
            .orElse(null);
        
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("error", "Galat username ya password"));
        }

        String token = UUID.randomUUID().toString();
        user.setToken(token);

        Map<String, Object> response = new HashMap<>();
        response.put("id", user.getId());
        response.put("username", user.getUsername());
        response.put("token", token);
        
        return ResponseEntity.ok(response);
    }
}
