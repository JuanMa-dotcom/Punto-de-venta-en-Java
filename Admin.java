/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

// Administrador
public class Admin extends User {
    public Admin(int userId, String name) { super(userId, name, true); }

    public Product addProduct(Inventory inv, String name, double price, int stock) {
        return inv.addProduct(new Product(0, name, price, stock));
    }
}