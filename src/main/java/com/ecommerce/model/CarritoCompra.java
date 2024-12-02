package com.ecommerce.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CarritoCompra {
    private Long id;
    private Long clienteId;
    private List<ItemCarrito> items;
    private String estado; // VACIO, CON_PRODUCTOS, EN_PROCESO_PAGO, COMPLETADO
    private BigDecimal total;

    public CarritoCompra(Long id, Long clienteId) {
        this.id = id;
        this.clienteId = clienteId;
        this.items = new ArrayList<>();
        this.estado = "VACIO";
        this.total = BigDecimal.ZERO;
    }

    public void agregarItem(Product producto, int cantidad) {
        ItemCarrito item = new ItemCarrito(producto, cantidad);
        items.add(item);
        calcularTotal();
        this.estado = "CON_PRODUCTOS";
    }

    public void eliminarItem(Long productoId) {
        items.removeIf(item -> item.getProduct().getId().equals(productoId));
        calcularTotal();
        if (items.isEmpty()) {
            this.estado = "VACIO";
        }
    }

    private void calcularTotal() {
        this.total = items.stream()
                .map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getCantidad())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public BigDecimal getTotal() { return total; }
    public List<ItemCarrito> getItems() { return items; }
}

