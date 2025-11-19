/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.main;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

// Ventana principal con login simple (admin/cliente)
public class Main {
    private static Inventory inventory = new Inventory();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Punto de Venta - POO");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setLocationRelativeTo(null);

        JPanel p = new JPanel(new BorderLayout());
        JLabel title = new JLabel("PUNTO DE VENTA", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 28));
        p.add(title, BorderLayout.NORTH);

        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setBorder(BorderFactory.createEmptyBorder(20,50,20,50));

        JButton adminBtn = new JButton("Ingresar como Administrador");
        JButton clientBtn = new JButton("Ingresar como Cliente");
        center.add(Box.createVerticalStrut(40));
        center.add(adminBtn);
        center.add(Box.createVerticalStrut(20));
        center.add(clientBtn);

        p.add(center, BorderLayout.CENTER);
        frame.setContentPane(p);
        frame.setVisible(true);

        adminBtn.addActionListener(e -> openAdminFrame());
        clientBtn.addActionListener(e -> openCustomerFrame());
    }

    private static void openAdminFrame() {
        JFrame af = new JFrame("Administrador - Inventario");
        af.setSize(700,500);
        af.setLocationRelativeTo(null);

        JPanel root = new JPanel(new BorderLayout(10,10));
        JLabel h = new JLabel("Panel Administrador", SwingConstants.CENTER);
        h.setFont(new Font("SansSerif", Font.BOLD, 20));
        root.add(h, BorderLayout.NORTH);

        DefaultTableModel model = new DefaultTableModel(new Object[]{"ID","Nombre","Precio","Stock"},0) {
            public boolean isCellEditable(int r,int c) { return false; }
        };
        JTable table = new JTable(model);
        refreshTable(model);

        JScrollPane sp = new JScrollPane(table);
        root.add(sp, BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        JButton addBtn = new JButton("Agregar Producto");
        JButton refreshBtn = new JButton("Refrescar Inventario");
        bottom.add(addBtn);
        bottom.add(refreshBtn);
        root.add(bottom, BorderLayout.SOUTH);

        af.setContentPane(root);
        af.setVisible(true);

        addBtn.addActionListener(ev -> {
            AddProductDialog dialog = new AddProductDialog(af);
            dialog.setVisible(true);
            if (dialog.isSucceeded()) {
                Admin admin = new Admin(1, "Admin");
                admin.addProduct(inventory, dialog.getNameValue(), dialog.getPriceValue(), dialog.getStockValue());
                refreshTable(model);
            }
        });

        refreshBtn.addActionListener(ev -> refreshTable(model));
    }

    private static void refreshTable(DefaultTableModel model) {
        model.setRowCount(0);
        for (Product p : inventory.getProducts()) {
            model.addRow(new Object[]{p.getId(), p.getName(), p.getPrice(), p.getStock()});
        }
    }

    private static void openCustomerFrame() {
        JFrame cf = new JFrame("Cliente - Comprar");
        cf.setSize(900,600);
        cf.setLocationRelativeTo(null);

        JPanel root = new JPanel(new BorderLayout(10,10));
        JLabel h = new JLabel("Panel Cliente", SwingConstants.CENTER);
        h.setFont(new Font("SansSerif", Font.BOLD, 20));
        root.add(h, BorderLayout.NORTH);

        DefaultTableModel model = new DefaultTableModel(new Object[]{"ID","Nombre","Precio","Stock"},0) {
            public boolean isCellEditable(int r,int c) { return false; }
        };
        JTable table = new JTable(model);
        refreshTable(model);

        JScrollPane sp = new JScrollPane(table);
        root.add(sp, BorderLayout.CENTER);

        JPanel right = new JPanel();
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
        right.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JLabel qtyLabel = new JLabel("Cantidad:");
        JTextField qtyField = new JTextField("1",5);
        JButton addCartBtn = new JButton("Agregar al carrito");
        JButton viewCartBtn = new JButton("Ver carrito");

        right.add(qtyLabel);
        right.add(qtyField);
        right.add(Box.createVerticalStrut(10));
        right.add(addCartBtn);
        right.add(Box.createVerticalStrut(10));
        right.add(viewCartBtn);

        root.add(right, BorderLayout.EAST);

        cf.setContentPane(root);
        cf.setVisible(true);

        Customer customer = new Customer(1, "Visitante");

        addCartBtn.addActionListener(ev -> {
            int sel = table.getSelectedRow();
            if (sel < 0) { JOptionPane.showMessageDialog(cf, "Seleccione un producto"); return; }
            int id = (int)table.getValueAt(sel,0);
            Product p = inventory.findById(id);
            if (p == null) return;
            int qty = 1;
            try { qty = Integer.parseInt(qtyField.getText()); } catch(Exception ex) { qty = 1; }
            if (qty <= 0) { JOptionPane.showMessageDialog(cf, "Cantidad inválida"); return; }
            if (p.getStock() <= 0) { JOptionPane.showMessageDialog(cf, "Producto sin stock"); return; }
            customer.getCart().add(p, qty);
            refreshTable(model);
            JOptionPane.showMessageDialog(cf, "Agregado al carrito");
        });

        viewCartBtn.addActionListener(ev -> {
            CartDialog cd = new CartDialog(cf, customer);
            cd.setVisible(true);
        });
    }
}