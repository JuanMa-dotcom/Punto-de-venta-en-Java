/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

public class User {
    protected int userId;
    protected String name;
    protected boolean isAdmin;

    public User(int userId, String name, boolean isAdmin) {
        this.userId = userId;
        this.name = name;
        this.isAdmin = isAdmin;
    }

    public boolean isAdmin() { return isAdmin; }
    public String getName() { return name; }
}

