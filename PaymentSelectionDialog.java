/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Selección de método de pago y recolección de datos
public class PaymentSelectionDialog extends JDialog {
    private boolean succeeded = false;
    private Pago pago = null;

    public PaymentSelectionDialog(Dialog parent, double monto) {
        super(parent, "Seleccionar Pago", true);
        setSize(400,300);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        JPanel top = new JPanel();
        top.add(new JLabel("Total: $" + String.format("%.2f", monto)));
        add(top, BorderLayout.NORTH);

        JPanel center = new JPanel(new GridLayout(3,1,8,8));
        JButton card = new JButton("Pagar con Tarjeta");
        JButton paypal = new JButton("Pagar con PayPal");
        JButton cash = new JButton("Pagar en Efectivo");
        center.add(card); center.add(paypal); center.add(cash);
        add(center, BorderLayout.CENTER);

        card.addActionListener(e -> {
            CardPaymentDialog cpd = new CardPaymentDialog(this, monto);
            cpd.setVisible(true);
            if (cpd.isSucceeded()) { pago = cpd.getPago(); succeeded = true; setVisible(false); }
        });

        paypal.addActionListener(e -> {
            PayPalDialog ppd = new PayPalDialog(this, monto);
            ppd.setVisible(true);
            if (ppd.isSucceeded()) { pago = ppd.getPago(); succeeded = true; setVisible(false); }
        });

        cash.addActionListener(e -> {
            CashDialog cd = new CashDialog(this, monto);
            cd.setVisible(true);
            if (cd.isSucceeded()) { pago = cd.getPago(); succeeded = true; setVisible(false); }
        });
    }

    public boolean isSucceeded() { return succeeded; }
    public Pago getPago() { return pago; }
}
