package com.comercialrojas.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelCliente extends JPanel {

    public JTextField txtNombre, txtDni, txtDomicilio, txtDistrito, txtTelefono;
    public JButton btnGuardar, btnEditar, btnEliminar, btnLimpiar;
    public JTable tablaClientes;
    public DefaultTableModel modeloTabla;

    public PanelCliente() {
        setLayout(new BorderLayout(10, 10));
        inicializarUI();
    }

    private void inicializarUI() {
        // Panel Superior: Formulario
        JPanel panelFormulario = new JPanel(new GridLayout(5, 2, 5, 5));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Registro de Cliente"));

        panelFormulario.add(new JLabel("Nombre Completo:"));
        txtNombre = new JTextField();
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("DNI:"));
        txtDni = new JTextField();
        panelFormulario.add(txtDni);

        panelFormulario.add(new JLabel("Domicilio:"));
        txtDomicilio = new JTextField();
        panelFormulario.add(txtDomicilio);

        panelFormulario.add(new JLabel("Distrito:"));
        txtDistrito = new JTextField();
        panelFormulario.add(txtDistrito);

        panelFormulario.add(new JLabel("Teléfono:"));
        txtTelefono = new JTextField();
        panelFormulario.add(txtTelefono);

        // Panel Central: Tabla
        modeloTabla = new DefaultTableModel(new String[]{"ID", "Nombre", "DNI", "Domicilio", "Distrito", "Teléfono"}, 0);
        tablaClientes = new JTable(modeloTabla);
        JScrollPane scrollTabla = new JScrollPane(tablaClientes);

        // Panel Inferior: Botones
        JPanel panelBotones = new JPanel();
        btnGuardar = new JButton("Guardar");
        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Eliminar");
        btnLimpiar = new JButton("Limpiar");

        panelBotones.add(btnGuardar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);

        // Ensamblaje
        add(panelFormulario, BorderLayout.NORTH);
        add(scrollTabla, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }
}