package com.comercialrojas.view;

import javax.swing.*;
import java.awt.*;

public class FrmPrincipal extends JFrame {

    // Componentes principales
    private JTabbedPane panelPestanas;
    private JPanel panelOperaciones;
    private JPanel panelConsultas;
    private JPanel panelMantenimiento;

    public FrmPrincipal() {
        // Configuración básica de la ventana
        setTitle("Sistema de Gestión - Comercial Rojas");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar en pantalla

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        panelPestanas = new JTabbedPane();

        // Inicializar cada pestaña
        panelOperaciones = new JPanel();
        panelOperaciones.setLayout(new BorderLayout());

        PanelVenta panelVenta = new PanelVenta();
        panelOperaciones.add(panelVenta, BorderLayout.CENTER);

        panelConsultas = new JPanel();
        panelConsultas.add(new JLabel("Aquí irán las tablas de moras y ventas semanales"));

        panelMantenimiento = new JPanel();
        panelMantenimiento.setLayout(new BorderLayout());

        JTabbedPane pestanasMantenimiento = new JTabbedPane();

        // Instanciación MVC para Cliente
        PanelCliente panelCliente = new PanelCliente();
        com.comercialrojas.model.Cliente modeloCliente = new com.comercialrojas.model.Cliente();
        com.comercialrojas.model.ClienteDAO daoCliente = new com.comercialrojas.model.ClienteDAO();
        new com.comercialrojas.controller.ClienteControlador(modeloCliente, daoCliente, panelCliente);

        pestanasMantenimiento.addTab("Gestión de Clientes", panelCliente);

        panelMantenimiento.add(pestanasMantenimiento, BorderLayout.CENTER);

        // Agregar las pestañas al panel principal
        panelPestanas.addTab("Operaciones", panelOperaciones);
        panelPestanas.addTab("Consultas y Moras", panelConsultas);
        panelPestanas.addTab("Mantenimiento", panelMantenimiento);

        add(panelPestanas, BorderLayout.CENTER);
    }

    // Método principal para ejecutar y visualizar la ventana
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FrmPrincipal().setVisible(true);
        });
    }
}