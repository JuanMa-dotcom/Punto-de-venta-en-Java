/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

// Dialog que muestra el carrito y permite pagar
public class CartDialog extends JDialog {
    private Customer customer;
    private DefaultTableModel model;
    public CartDialog(Frame parent, Customer customer) {
        super(parent, "Carrito", true);
        this.customer = customer;
        setSize(600,400);
        setLocationRelativeTo(parent);

        setLayout(new BorderLayout());
        model = new DefaultTableModel(new Object[]{"Producto","Precio","Cantidad","Subtotal"},0) {
            public boolean isCellEditable(int r,int c){return false;}
        };
        JTable table = new JTable(model);
        refresh();
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        JLabel totalLbl = new JLabel("Total: $" + String.format("%.2f", customer.getCart().getTotal()));
        JButton payBtn = new JButton("Pagar");
        JButton clearBtn = new JButton("Vaciar carrito");
        bottom.add(totalLbl);
        bottom.add(payBtn);
        bottom.add(clearBtn);
        add(bottom, BorderLayout.SOUTH);

        payBtn.addActionListener(e -> {
            double total = customer.getCart().getTotal();
            if (total <= 0) { JOptionPane.showMessageDialog(this, "Carrito vacío"); return; }
            PaymentSelectionDialog ps = new PaymentSelectionDialog(this, total);
            ps.setVisible(true);
            if (ps.isSucceeded()) {
                // procesar pago polimórfico
                Pago pago = ps.getPago();
                boolean ok = pago.realizarPago();
                if (ok) {
                    JOptionPane.showMessageDialog(this, "Pago procesado. Gracias por su compra!");
                    customer.getCart().clear();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Error en el pago. Intente nuevamente.");
                }
            }
        });

        clearBtn.addActionListener(e -> { customer.getCart().clear(); refresh(); });
    }

    private void refresh() {
        model.setRowCount(0);
        customer.getCart().getItems().forEach((prod,qty) -> {
            model.addRow(new Object[]{prod.getName(), prod.getPrice(), qty, prod.getPrice()*qty});
        });
    }
}