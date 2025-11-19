/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;
public class PagoTarjeta extends Pago {
    private String cardNumber;
    private String titular;
    private String cvv;

    public PagoTarjeta(double monto, String cardNumber, String titular, String cvv) {
        super(monto);
        this.cardNumber = cardNumber;
        this.titular = titular;
        this.cvv = cvv;
    }

    @Override
    public boolean realizarPago() {
        // Simulación: validar longitud mínima
        if (cardNumber == null || cardNumber.length() < 12) return false;
        if (cvv == null || cvv.length() < 3) return false;
        // aquí iría la integración real con pasarela
        System.out.println("Procesando pago con tarjeta para $" + monto);
        return true;
    }
}

