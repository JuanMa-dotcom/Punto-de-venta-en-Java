/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

public class PagoEfectivo extends Pago {
    private double recibido;

    public PagoEfectivo(double monto, double recibido) {
        super(monto);
        this.recibido = recibido;
    }

    @Override
    public boolean realizarPago() {
        if (recibido < monto) return false;
        System.out.println("Pago en efectivo aceptado. Cambio: $" + (recibido - monto));
        return true;
    }
}