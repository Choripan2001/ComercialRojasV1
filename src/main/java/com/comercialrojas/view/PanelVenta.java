package com.comercialrojas.view;

import javax.swing.*;
import java.awt.*;

public class PanelVenta extends JPanel {

    public JTextField txtDniCliente, txtCodigoProducto, txtFecha, txtTotal, txtCuotas;
    public JButton btnBuscarCliente, btnBuscarProducto, btnGenerarVenta;
    public JLabel lblNombreCliente, lblNombreProducto;

    public PanelVenta() {
        setLayout(new BorderLayout(10, 10));
        inicializarUI();
    }

    private void inicializarUI() {
        JPanel panelFormulario = new JPanel(new GridLayout(7, 3, 5, 5));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Registro de Nueva Venta"));

        panelFormulario.add(new JLabel("DNI Cliente:"));
        txtDniCliente = new JTextField();
        panelFormulario.add(txtDniCliente);
        btnBuscarCliente = new JButton("Buscar Cliente");
        panelFormulario.add(btnBuscarCliente);

        panelFormulario.add(new JLabel("Nombre Cliente:"));
        lblNombreCliente = new JLabel("---");
        panelFormulario.add(lblNombreCliente);
        panelFormulario.add(new JLabel(""));

        panelFormulario.add(new JLabel("Código Producto:"));
        txtCodigoProducto = new JTextField();
        panelFormulario.add(txtCodigoProducto);
        btnBuscarProducto = new JButton("Buscar Producto");
        panelFormulario.add(btnBuscarProducto);

        panelFormulario.add(new JLabel("Nombre Producto:"));
        lblNombreProducto = new JLabel("---");
        panelFormulario.add(lblNombreProducto);
        panelFormulario.add(new JLabel(""));

        panelFormulario.add(new JLabel("Fecha Compra (YYYY-MM-DD):"));
        txtFecha = new JTextField();
        panelFormulario.add(txtFecha);
        panelFormulario.add(new JLabel(""));

        panelFormulario.add(new JLabel("Total a Pagar:"));
        txtTotal = new JTextField();
        panelFormulario.add(txtTotal);
        panelFormulario.add(new JLabel(""));

        panelFormulario.add(new JLabel("Cantidad de Cuotas (Letras):"));
        txtCuotas = new JTextField();
        panelFormulario.add(txtCuotas);
        panelFormulario.add(new JLabel(""));

        JPanel panelBotones = new JPanel();
        btnGenerarVenta = new JButton("Registrar Venta y Generar Letras");
        panelBotones.add(btnGenerarVenta);

        add(panelFormulario, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.CENTER);
    }
}