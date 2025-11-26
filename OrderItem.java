package com.ecommerce.model;

public class OrderItem {
    private Long id;          
    private Long orderId;    
    private Long itemId;      
    private Item item;       
    
    public OrderItem() {}
    
    public OrderItem(Long id, Long orderId, Long itemId, Item item) {
        this.id = id;
        this.orderId = orderId;
        this.itemId = itemId;
        this.item = item;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }
    
    public Long getItemId() { return itemId; }
    public void setItemId(Long itemId) { this.itemId = itemId; }
    
    public Item getItem() { return item; }
    public void setItem(Item item) { this.item = item; }
}
