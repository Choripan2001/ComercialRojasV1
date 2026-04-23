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
        new com.comercialrojas.controller.VentaControlador(panelVenta); // agregado recien para controlador
        panelOperaciones.add(panelVenta, BorderLayout.CENTER);

        //PANEL CONSULTAS

        panelConsultas = new JPanel();
        panelConsultas.setLayout(new BorderLayout());

        PanelConsultas panelConsultasView = new PanelConsultas();
        new com.comercialrojas.controller.ConsultasControlador(panelConsultasView, new com.comercialrojas.model.ConsultasDAO());

        panelConsultas.add(panelConsultasView, BorderLayout.CENTER);

        // PANEL MANTENIMIENTO

        panelMantenimiento = new JPanel();
        panelMantenimiento.setLayout(new BorderLayout());
        JTabbedPane pestanasMantenimiento = new JTabbedPane();

        // 1. Instanciación MVC para Cliente
        PanelCliente panelCliente = new PanelCliente();
        new com.comercialrojas.controller.ClienteControlador(new com.comercialrojas.model.Cliente(), new com.comercialrojas.model.ClienteDAO(), panelCliente);
        pestanasMantenimiento.addTab("Gestión de Clientes", panelCliente);

        // 2. Instanciación MVC para Producto
        PanelProducto panelProducto = new PanelProducto();
        new com.comercialrojas.controller.ProductoControlador(new com.comercialrojas.model.Producto(), new com.comercialrojas.model.ProductoDAO(), panelProducto);
        pestanasMantenimiento.addTab("Gestión de Productos", panelProducto);

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