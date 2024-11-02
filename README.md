
# Estándares de Codificación - Variedades JM

## EVIDENCIA DE DESEMPEÑO: GA7-220501096-AA1-EV02
**DEFINIR ESTÁNDARES DE CODIFICACIÓN DE ACUERDO A PLATAFORMA DE DESARROLLO ELEGIDA**  
*ENRIQUE GIRALDO PUENTES*

## Título
Informe Técnico: Estándares de Codificación para el Desarrollo del Software "Variedades JM"

## Introducción
En el desarrollo de software, la adopción de estándares de codificación es fundamental para garantizar la calidad del código, facilitar la colaboración entre desarrolladores y asegurar la mantenibilidad del software a largo plazo. Este informe establece los estándares de codificación para la plataforma elegida en el desarrollo del software "Variedades JM", que permitirá al equipo de desarrollo escribir código claro, coherente y conforme a las mejores prácticas de programación orientada a objetos.

El software "Variedades JM" será desarrollado utilizando JavaScript y Node.js en el backend, con MongoDB como base de datos NoSQL, bajo el paradigma de programación orientada a objetos (POO).

## Objetivo
El objetivo de este informe es definir los estándares de codificación que se seguirán en el desarrollo del software "Variedades JM". Estos estándares abarcan las convenciones de nombramiento, la organización de clases y métodos, y las mejores prácticas para escribir código limpio, modular y escalable.

## Estándares de Codificación

### 1. Nombramiento de Variables
El nombramiento adecuado de variables es clave para la legibilidad y el mantenimiento del código.

#### Camel Case
Las variables seguirán el formato camelCase, donde la primera letra es minúscula y cada palabra subsiguiente comienza con mayúscula.

**Ejemplos:**
```javascript
let firstName = "John";
let totalAmount = 100;
let userAddress = "Calle 123";
```

#### Significado Claro
Los nombres de variables deben ser descriptivos y reflejar su propósito.

**Incorrecto:**
```javascript
let x = "John";
let temp = 100;
let addr = "Calle 123";
```

**Correcto:**
```javascript
let userName = "John";
let totalPrice = 100;
let deliveryAddress = "Calle 123";
```

### 2. Declaración de Clases
Las clases son la base de la programación orientada a objetos. Se aplicará un formato estándar para declarar clases y se nombrarán usando PascalCase.

#### UpperCamelCase para Nombres de Clases
Los nombres de las clases deben usar UpperCamelCase, comenzando cada palabra con una letra mayúscula.

**Ejemplos:**
```javascript
class Product {
  constructor(name, price) {
    this.name = name;
    this.price = price;
  }
}

class ShoppingCart {
  constructor() {
    this.items = [];
  }
}
```

### Estándares de JavaScript/Node.js

#### Operaciones Asíncronas
```javascript
async function getProduct(id) {
  try {
    const product = await Product.findById(id);
    return product;
  } catch (error) {
    throw new Error('Error al obtener el producto');
  }
}
```

#### Estructura del proyecto Node.js:
- `/routes`: Rutas de la API
- `/models`: Modelos de la base de datos
- `/controllers`: Lógica de negocio
- `/services`: Funciones auxiliares o servicios

### 3. Declaración de Métodos
Los métodos representan las acciones de una clase. Siguen la convención camelCase.

**Ejemplos:**
```javascript
class Product {
  calculateDiscount() { }
  updateStock() { }
  getDescription() { }
}
```

### 4. Uso de Constantes
Las constantes deben declararse en mayúsculas separadas por guiones bajos (_).

**Ejemplo:**
```javascript
const MAX_PRODUCTS = 100;
const API_BASE_URL = 'https://api.example.com';
const DEFAULT_TAX_RATE = 0.19;
```

### 5. Uso de Comentarios

#### Comentarios Claros y Concisos
```javascript
// Calcula el descuento basado en el volumen de compra
function calculateBulkDiscount(quantity, unitPrice) {
  // Aplicar 10% de descuento para compras mayores a 10 unidades
  if (quantity > 10) {
    return unitPrice * quantity * 0.9;
  }
  return unitPrice * quantity;
}
```

#### Docstrings en Métodos
```javascript
/**
 * Calcula el precio total incluyendo impuestos
 * @param {number} basePrice - Precio base del producto
 * @param {number} taxRate - Tasa de impuesto (decimal)
 * @returns {number} Precio total con impuestos
 */
function calculateTotalPrice(basePrice, taxRate) {
  return basePrice * (1 + taxRate);
}
```

### 6. Estructura del Código

#### Indentación
```javascript
function processOrder(order) {
  if (order.isValid()) {
    try {
      processPayment(order);
      updateInventory(order);
      sendConfirmation(order);
    } catch (error) {
      handleError(error);
    }
  }
}
```

### 7. Manejo de Errores
```javascript
async function createProduct(productData) {
  try {
    const product = new Product(productData);
    await product.save();
    return product;
  } catch (error) {
    throw new Error(`Error al crear producto: ${error.message}`);
  }
}
```

### 8. Patrones de Diseño

#### A) Singleton
```javascript
class Database {
  static instance = null;

  static getInstance() {
    if (!Database.instance) {
      Database.instance = new Database();
    }
    return Database.instance;
  }
}
```

#### B) Patrón Factory
```javascript
class ProductFactory {
  createProduct(type) {
    switch (type) {
      case 'digital':
        return new DigitalProduct();
      case 'physical':
        return new PhysicalProduct();
      default:
        throw new Error('Tipo de producto no válido');
    }
  }
}
```

### 9. Control de Versiones

#### A) Convención para mensajes de commit
- Formato: `tipo(alcance): descripción breve`
- Ejemplo: `feat(product): add new product personalization feature`

#### B) Estrategia de Ramificación
- `main`: Rama principal para producción
- `develop`: Rama de desarrollo
- `feature/*`: Nuevas características
- `hotfix/*`: Soluciones de bugs en producción

### 10. Pruebas de Software

#### A) Pruebas Unitarias
```javascript
describe('Product', () => {
  it('should calculate correct discount', () => {
    const product = new Product('Test', 100);
    expect(product.calculateDiscount(0.1)).toBe(90);
  });
});
```

#### B) Pruebas de Integración
Las pruebas de integración garantizarán que diferentes módulos trabajen juntos de manera correcta, como la integración entre los servicios de productos y el carrito de compras.

## Conclusión
El uso de estos estándares de codificación permitirá desarrollar un software claro, eficiente y mantenible para "Variedades JM". Estos lineamientos aseguran que todos los desarrolladores trabajen bajo un mismo conjunto de reglas, lo que facilita la colaboración, la revisión de código y el mantenimiento a largo plazo del sistema.
