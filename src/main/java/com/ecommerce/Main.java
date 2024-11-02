package com.ecommerce;

import com.ecommerce.model.*;
import com.ecommerce.service.*;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ProductService productService = new ProductService();
    private static Cliente clienteActual = null;
    private static CarritoCompra carritoActual = null;

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            try {
                mostrarMenuPrincipal();
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer

                switch (opcion) {
                    case 1:
                        if (clienteActual == null) {
                            iniciarSesion();
                        } else {
                            System.out.println("Ya has iniciado sesión como: " + clienteActual.getNombre());
                        }
                        break;
                    case 2:
                        if (clienteActual == null) {
                            crearCuenta();
                        } else {
                            System.out.println("Ya has iniciado sesión. Cierra sesión primero.");
                        }
                        break;
                    case 3:
                        mostrarCatalogo();
                        break;
                    case 4:
                        if (clienteActual != null) {
                            gestionarCarrito();
                        } else {
                            System.out.println("Debes iniciar sesión primero.");
                        }
                        break;
                    case 5:
                        if (clienteActual != null) {
                            procesarPago();
                        } else {
                            System.out.println("Debes iniciar sesión primero.");
                        }
                        break;
                    case 0:
                        running = false;
                        System.out.println("¡Gracias por usar nuestro sistema!");
                        break;
                    default:
                        System.out.println("Opción no válida");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                scanner.nextLine(); // Limpiar buffer en caso de error
            }
        }
    }

    private static void mostrarMenuPrincipal() {
        System.out.println("\n=== Sistema de E-commerce ===");
        System.out.println("1. Iniciar Sesión");
        System.out.println("2. Crear Cuenta");
        System.out.println("3. Ver Catálogo");
        System.out.println("4. Gestionar Carrito");
        System.out.println("5. Procesar Pago");
        System.out.println("0. Salir");
        System.out.print("\nSeleccione una opción: ");
    }

    private static void iniciarSesion() {
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Contraseña: ");
        String password = scanner.nextLine();

        // Aquí deberías validar las credenciales contra la base de datos
        // Por ahora, creamos un cliente de prueba
        clienteActual = new Cliente(1L, "Usuario Prueba", email, password, "Dirección Prueba", "123456789");
        carritoActual = new CarritoCompra(1L, clienteActual.getId());
        System.out.println("Sesión iniciada correctamente");
    }

    private static void crearCuenta() {
        System.out.println("\n=== Crear Nueva Cuenta ===");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Contraseña: ");
        String password = scanner.nextLine();
        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();

        // Aquí deberías guardar el cliente en la base de datos
        clienteActual = new Cliente(1L, nombre, email, password, direccion, telefono);
        carritoActual = new CarritoCompra(1L, clienteActual.getId());
        System.out.println("Cuenta creada exitosamente");
    }

    private static void mostrarCatalogo() throws Exception {
        System.out.println("\n=== Catálogo de Productos ===");
        System.out.println("1. Regalos Cumpleaños");
        System.out.println("2. Desayunos Sorpresa");
        System.out.println("3. Ropa Estampada");
        System.out.println("4. Gorras");

        productService.getAllProducts().forEach(p -> {
            System.out.println("\n--------------------------------");
            System.out.println("ID: " + p.getId());
            System.out.println("Nombre: " + p.getName());
            System.out.println("Precio: $" + p.getPrice());
            System.out.println("Stock: " + p.getStock());
            System.out.println("Categoría: " + p.getCategory());
        });
    }

    private static void gestionarCarrito() {
        if (carritoActual == null) {
            carritoActual = new CarritoCompra(1L, clienteActual.getId());
        }

        System.out.println("\n=== Gestión del Carrito ===");
        System.out.println("1. Agregar Producto");
        System.out.println("2. Eliminar Producto");
        System.out.println("3. Ver Carrito");
        System.out.println("4. Vaciar Carrito");
        System.out.print("\nSeleccione una opción: ");

        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        try {
            switch (opcion) {
                case 1:
                    agregarAlCarrito();
                    break;
                case 2:
                    eliminarDelCarrito();
                    break;
                case 3:
                    mostrarCarrito();
                    break;
                case 4:
                    carritoActual = new CarritoCompra(1L, clienteActual.getId());
                    System.out.println("Carrito vaciado");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void agregarAlCarrito() throws Exception {
        System.out.print("Ingrese el ID del producto: ");
        Long productoId = scanner.nextLong();
        System.out.print("Ingrese la cantidad: ");
        int cantidad = scanner.nextInt();

        productService.getProduct(productoId).ifPresentOrElse(
                producto -> {
                    if (producto.getStock() >= cantidad) {
                        carritoActual.agregarItem(producto, cantidad);
                        System.out.println("Producto agregado al carrito");
                    } else {
                        System.out.println("Stock insuficiente");
                    }
                },
                () -> System.out.println("Producto no encontrado")
        );
    }

    private static void eliminarDelCarrito() {
        System.out.print("Ingrese el ID del producto a eliminar: ");
        Long productoId = scanner.nextLong();
        carritoActual.eliminarItem(productoId);
        System.out.println("Producto eliminado del carrito");
    }

    private static void mostrarCarrito() {
        System.out.println("\n=== Tu Carrito ===");
        if (carritoActual.getItems().isEmpty()) {
            System.out.println("El carrito está vacío");
            return;
        }

        carritoActual.getItems().forEach(item -> {
            System.out.println("\n--------------------------------");
            System.out.println("Producto: " + item.getProduct().getName());
            System.out.println("Cantidad: " + item.getCantidad());
            System.out.println("Precio unitario: $" + item.getProduct().getPrice());
            System.out.println("Subtotal: $" + item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getCantidad())));
        });

        System.out.println("\nTotal del carrito: $" + carritoActual.getTotal());
    }

    private static void procesarPago() {
        if (carritoActual == null || carritoActual.getItems().isEmpty()) {
            System.out.println("El carrito está vacío");
            return;
        }

        System.out.println("\n=== Proceso de Pago ===");
        mostrarCarrito();

        System.out.println("\nSeleccione método de pago:");
        System.out.println("1. Efectivo");
        System.out.print("\nOpción: ");

        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        if (opcion == 1) {
            carritoActual.setEstado("COMPLETADO");
            System.out.println("\n=== Factura ===");
            System.out.println("Cliente: " + clienteActual.getNombre());
            System.out.println("Dirección: " + clienteActual.getDireccion());
            System.out.println("Teléfono: " + clienteActual.getTelefono());
            mostrarCarrito();
            System.out.println("\nPago en efectivo procesado correctamente");

            // Crear nuevo carrito vacío
            carritoActual = new CarritoCompra(carritoActual.getId() + 1, clienteActual.getId());
        } else {
            System.out.println("Método de pago no válido");
        }
    }
}