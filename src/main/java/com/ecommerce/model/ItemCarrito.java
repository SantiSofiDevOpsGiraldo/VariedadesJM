// ItemCarrito.java
package com.ecommerce.model;

public class ItemCarrito {
    private Product product;
    private int cantidad;

    public ItemCarrito(Product product, int cantidad) {
        this.product = product;
        this.cantidad = cantidad;
    }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
}