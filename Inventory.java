/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

import java.util.*;

// Inventario simple en memoria
public class Inventory {
    private List<Product> products = new ArrayList<>();
    private int nextId = 1;

    public Inventory() {
        // productos iniciales
        addProduct(new Product(0, "Pizza Margherita", 120.0, 10));
        addProduct(new Product(0, "Hamburguesa Doble", 90.0, 15));
        addProduct(new Product(0, "Refresco 600ml", 30.0, 50));
    }

    public synchronized Product addProduct(Product p) {
        if (p.getId() == 0) {
            Product np = new Product(nextId++, p.getName(), p.getPrice(), p.getStock());
            products.add(np);
            return np;
        } else {
            products.add(p);
            return p;
        }
    }

    public List<Product> getProducts() { return Collections.unmodifiableList(products); }

    public Product findById(int id) {
        return products.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
    }
}