/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

import javax.swing.*;
import java.awt.*;

public class CashDialog extends JDialog {
    private boolean succeeded = false;
    private PagoEfectivo pago;
    private JTextField recibidoField = new JTextField(10);

    public CashDialog(Dialog parent, double monto) {
        super(parent, "Pago en Efectivo", true);
        setLayout(new BorderLayout());
        JPanel p = new JPanel(new GridLayout(2,2,8,8));
        p.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        p.add(new JLabel("Efectivo recibido:")); p.add(recibidoField);
        p.add(new JLabel("Monto:")); p.add(new JLabel("$" + String.format("%.2f", monto)));
        add(p, BorderLayout.CENTER);
        JPanel b = new JPanel(); JButton ok = new JButton("Pagar"); JButton cancel = new JButton("Cancelar"); b.add(ok); b.add(cancel); add(b, BorderLayout.SOUTH);
        pack(); setLocationRelativeTo(parent);

        ok.addActionListener(e -> {
            try {
                double recibido = Double.parseDouble(recibidoField.getText().trim());
                pago = new PagoEfectivo(monto, recibido);
                succeeded = true; setVisible(false);
            } catch(Exception ex) {
                JOptionPane.showMessageDialog(this, "Ingrese un monto válido");
            }
        });
        cancel.addActionListener(e -> { succeeded = false; setVisible(false); });
    }

    public boolean isSucceeded() { return succeeded; }
    public PagoEfectivo getPago() { return pago; }
}

