/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

public class PagoPayPal extends Pago {
    private String email;

    public PagoPayPal(double monto, String email) {
        super(monto);
        this.email = email;
    }

    @Override
    public boolean realizarPago() {
        if (email == null || !email.contains("@")) return false;
        System.out.println("Procesando pago con PayPal para $" + monto);
        return true;
    }
}