package com.ecommerce.model;

public class Cliente {
    private Long id;
    private String nombre;
    private String email;
    private String password;
    private String direccion;
    private String telefono;

    public Cliente(Long id, String nombre, String email, String password, String direccion, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
}