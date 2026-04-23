package com.comercialrojas.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelConsultas extends JPanel {

    public JTable tablaVentas, tablaMoras;
    public DefaultTableModel modeloVentas, modeloMoras;
    public JButton btnActualizar;

    public PanelConsultas() {
        setLayout(new BorderLayout(10, 10));
        inicializarUI();
    }

    private void inicializarUI() {
        modeloVentas = new DefaultTableModel(new String[]{"ID Venta", "Cliente", "Producto", "Fecha", "Total"}, 0);
        tablaVentas = new JTable(modeloVentas);
        JPanel panelVentas = new JPanel(new BorderLayout());
        panelVentas.setBorder(BorderFactory.createTitledBorder("Ventas de los últimos 7 días"));
        panelVentas.add(new JScrollPane(tablaVentas), BorderLayout.CENTER);

        modeloMoras = new DefaultTableModel(new String[]{"ID Letra", "Cliente", "N° Letra", "Vencimiento", "Monto"}, 0);
        tablaMoras = new JTable(modeloMoras);
        JPanel panelMoras = new JPanel(new BorderLayout());
        panelMoras.setBorder(BorderFactory.createTitledBorder("Letras por vencer (Próximos 7 días)"));
        panelMoras.add(new JScrollPane(tablaMoras), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        btnActualizar = new JButton("Actualizar Datos");
        panelBotones.add(btnActualizar);

        JPanel panelTablas = new JPanel(new GridLayout(2, 1, 10, 10));
        panelTablas.add(panelVentas);
        panelTablas.add(panelMoras);

        add(panelTablas, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }
}