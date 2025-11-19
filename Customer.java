/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

public class Customer extends User {
    private Cart cart = new Cart();
    public Customer(int userId, String name) { super(userId, name, false); }
    public Cart getCart() { return cart; }
}
