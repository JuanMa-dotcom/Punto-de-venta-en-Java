/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

import java.util.*;

// Carrito de compras
public class Cart {
    private Map<Product, Integer> items = new LinkedHashMap<>();

    public void add(Product p, int qty) {
        if (qty <= 0) return;
        int available = p.getStock();
        if (qty > available) qty = available; // ajustar a stock
        items.put(p, items.getOrDefault(p,0) + qty);
        p.setStock(p.getStock() - qty);
    }

    public void remove(Product p) {
        Integer q = items.remove(p);
        if (q != null) {
            p.setStock(p.getStock() + q); // devolver existencia
        }
    }

    public double getTotal() {
        return items.entrySet().stream().mapToDouble(e -> e.getKey().getPrice() * e.getValue()).sum();
    }

    public Map<Product,Integer> getItems() { return Collections.unmodifiableMap(items); }

    public void clear() {
        // devolver stock
        for (Map.Entry<Product,Integer> e : items.entrySet()) {
            e.getKey().setStock(e.getKey().getStock() + e.getValue());
        }
        items.clear();
    }
}