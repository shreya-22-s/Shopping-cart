package com.ecommerce.model;

public class CartItem {
    private Long id;          
    private Long cartId;     
    private Long itemId;     
    private Item item;       
    
    public CartItem() {}
    
    public CartItem(Long id, Long cartId, Long itemId, Item item) {
        this.id = id;
        this.cartId = cartId;
        this.itemId = itemId;
        this.item = item;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getCartId() { return cartId; }
    public void setCartId(Long cartId) { this.cartId = cartId; }
    
    public Long getItemId() { return itemId; }
    public void setItemId(Long itemId) { this.itemId = itemId; }
    
    public Item getItem() { return item; }
    public void setItem(Item item) { this.item = item; }
}
