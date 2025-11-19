/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

import javax.swing.*;
import java.awt.*;

public class PayPalDialog extends JDialog {
    private boolean succeeded = false;
    private PagoPayPal pago;
    private JTextField emailField = new JTextField(25);

    public PayPalDialog(Dialog parent, double monto) {
        super(parent, "Pago PayPal", true);
        setLayout(new BorderLayout());
        JPanel p = new JPanel(new GridLayout(2,2,8,8));
        p.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        p.add(new JLabel("Email PayPal:")); p.add(emailField);
        p.add(new JLabel("Monto:")); p.add(new JLabel("$" + String.format("%.2f", monto)));
        add(p, BorderLayout.CENTER);
        JPanel b = new JPanel(); JButton ok = new JButton("Pagar"); JButton cancel = new JButton("Cancelar"); b.add(ok); b.add(cancel); add(b, BorderLayout.SOUTH);
        pack(); setLocationRelativeTo(parent);

        ok.addActionListener(e -> {
            pago = new PagoPayPal(monto, emailField.getText().trim());
            succeeded = true; setVisible(false);
        });
        cancel.addActionListener(e -> { succeeded = false; setVisible(false); });
    }

    public boolean isSucceeded() { return succeeded; }
    public PagoPayPal getPago() { return pago; }
}