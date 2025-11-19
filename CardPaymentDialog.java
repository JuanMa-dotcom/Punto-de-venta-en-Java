/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

import javax.swing.*;
import java.awt.*;

public class CardPaymentDialog extends JDialog {
    private boolean succeeded = false;
    private PagoTarjeta pago;
    private JTextField cardField = new JTextField(16);
    private JTextField titularField = new JTextField(20);
    private JTextField cvvField = new JTextField(4);

    public CardPaymentDialog(Dialog parent, double monto) {
        super(parent, "Pago con Tarjeta", true);
        setLayout(new BorderLayout());
        JPanel p = new JPanel(new GridLayout(4,2,8,8));
        p.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        p.add(new JLabel("Titular:")); p.add(titularField);
        p.add(new JLabel("Número de tarjeta:")); p.add(cardField);
        p.add(new JLabel("CVV:")); p.add(cvvField);
        p.add(new JLabel("Monto:")); p.add(new JLabel("$" + String.format("%.2f", monto)));
        add(p, BorderLayout.CENTER);
        JPanel b = new JPanel();
        JButton ok = new JButton("Pagar"); JButton cancel = new JButton("Cancelar");
        b.add(ok); b.add(cancel); add(b, BorderLayout.SOUTH);
        pack(); setLocationRelativeTo(parent);

        ok.addActionListener(e -> {
            pago = new PagoTarjeta(monto, cardField.getText().trim(), titularField.getText().trim(), cvvField.getText().trim());
            succeeded = true;
            setVisible(false);
        });
        cancel.addActionListener(e -> { succeeded = false; setVisible(false); });
    }

    public boolean isSucceeded() { return succeeded; }
    public PagoTarjeta getPago() { return pago; }
}