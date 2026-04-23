package com.comercialrojas.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelProducto extends JPanel {

    public JTextField txtCodigo, txtNombre, txtMarca, txtModelo, txtPrecio;
    public JComboBox<String> cbEstado;
    public JButton btnGuardar, btnEditar, btnEliminar, btnLimpiar;
    public JTable tablaProductos;
    public DefaultTableModel modeloTabla;

    public PanelProducto() {
        setLayout(new BorderLayout(10, 10));
        inicializarUI();
    }

    private void inicializarUI() {
        // Formulario
        JPanel panelFormulario = new JPanel(new GridLayout(6, 2, 5, 5));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Registro de Producto"));

        panelFormulario.add(new JLabel("Código/Serie:"));
        txtCodigo = new JTextField();
        panelFormulario.add(txtCodigo);

        panelFormulario.add(new JLabel("Nombre (Ej. Licuadora):"));
        txtNombre = new JTextField();
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Marca:"));
        txtMarca = new JTextField();
        panelFormulario.add(txtMarca);

        panelFormulario.add(new JLabel("Modelo:"));
        txtModelo = new JTextField();
        panelFormulario.add(txtModelo);

        panelFormulario.add(new JLabel("Precio:"));
        txtPrecio = new JTextField();
        panelFormulario.add(txtPrecio);

        panelFormulario.add(new JLabel("Estado:"));
        cbEstado = new JComboBox<>(new String[]{"Disponible", "Agotado", "Descontinuado"});
        panelFormulario.add(cbEstado);

        // Tabla
        modeloTabla = new DefaultTableModel(new String[]{"ID", "Código", "Nombre", "Marca", "Modelo", "Precio", "Estado"}, 0);
        tablaProductos = new JTable(modeloTabla);
        JScrollPane scrollTabla = new JScrollPane(tablaProductos);

        // Botones
        JPanel panelBotones = new JPanel();
        btnGuardar = new JButton("Guardar");
        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Eliminar");
        btnLimpiar = new JButton("Limpiar");

        panelBotones.add(btnGuardar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);

        add(panelFormulario, BorderLayout.NORTH);
        add(scrollTabla, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }
}