/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Dialog para agregar producto
public class AddProductDialog extends JDialog {
    private boolean succeeded = false;
    private JTextField nameField = new JTextField(20);
    private JTextField priceField = new JTextField(10);
    private JTextField stockField = new JTextField(5);

    public AddProductDialog(Frame parent) {
        super(parent, "Agregar Producto", true);
        setLayout(new BorderLayout());
        JPanel p = new JPanel(new GridLayout(3,2,8,8));
        p.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        p.add(new JLabel("Nombre:")); p.add(nameField);
        p.add(new JLabel("Precio:")); p.add(priceField);
        p.add(new JLabel("Stock:")); p.add(stockField);
        add(p, BorderLayout.CENTER);
        JPanel buttons = new JPanel();
        JButton ok = new JButton("Agregar");
        JButton cancel = new JButton("Cancelar");
        buttons.add(ok); buttons.add(cancel);
        add(buttons, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(parent);

        ok.addActionListener(e -> {
            try {
                Double.parseDouble(priceField.getText());
                Integer.parseInt(stockField.getText());
                succeeded = true;
                setVisible(false);
            } catch(Exception ex) {
                JOptionPane.showMessageDialog(this, "Datos inválidos");
            }
        });
        cancel.addActionListener(e -> { succeeded = false; setVisible(false); });
    }

    public boolean isSucceeded() { return succeeded; }
    public String getNameValue() { return nameField.getText(); }
    public double getPriceValue() { return Double.parseDouble(priceField.getText()); }
    public int getStockValue() { return Integer.parseInt(stockField.getText()); }
}