package com.comercialrojas.controller;

import com.comercialrojas.model.Producto;
import com.comercialrojas.model.ProductoDAO;
import com.comercialrojas.view.PanelProducto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ProductoControlador implements ActionListener {
    private Producto producto;
    private ProductoDAO productoDAO;
    private PanelProducto vista;
    private int idSeleccionado = -1;

    public ProductoControlador(Producto producto, ProductoDAO productoDAO, PanelProducto vista) {
        this.producto = producto;
        this.productoDAO = productoDAO;
        this.vista = vista;

        this.vista.btnGuardar.addActionListener(this);
        this.vista.btnEditar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this); // En una BD real es mejor cambiar el estado en lugar de eliminar
        this.vista.btnLimpiar.addActionListener(this);

        this.vista.tablaProductos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                seleccionarFila();
            }
        });

        listar();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnGuardar) {
            asignarDatos();
            if (productoDAO.registrar(producto)) exito("Producto registrado");
        } else if (e.getSource() == vista.btnEditar && idSeleccionado != -1) {
            asignarDatos();
            producto.setId(idSeleccionado);
            // Asumiendo que agregue el método actualizar() en ProductoDAO posteriormente
            // if (productoDAO.actualizar(producto)) exito("Producto actualizado");
        } else if (e.getSource() == vista.btnLimpiar) {
            limpiar();
        }
    }

    private void asignarDatos() {
        producto.setCodigoSerie(vista.txtCodigo.getText());
        producto.setNombre(vista.txtNombre.getText());
        producto.setMarca(vista.txtMarca.getText());
        producto.setModelo(vista.txtModelo.getText());
        try {
            producto.setPrecio(Double.parseDouble(vista.txtPrecio.getText()));
        } catch (NumberFormatException ex) {
            producto.setPrecio(0.0);
        }
        producto.setEstado(vista.cbEstado.getSelectedItem().toString());
    }

    private void seleccionarFila() {
        int fila = vista.tablaProductos.getSelectedRow();
        if (fila >= 0) {
            idSeleccionado = Integer.parseInt(vista.tablaProductos.getValueAt(fila, 0).toString());
            vista.txtCodigo.setText(vista.tablaProductos.getValueAt(fila, 1).toString());
            vista.txtNombre.setText(vista.tablaProductos.getValueAt(fila, 2).toString());
            vista.txtMarca.setText(vista.tablaProductos.getValueAt(fila, 3).toString());
            vista.txtModelo.setText(vista.tablaProductos.getValueAt(fila, 4).toString());
            vista.txtPrecio.setText(vista.tablaProductos.getValueAt(fila, 5).toString());
            vista.cbEstado.setSelectedItem(vista.tablaProductos.getValueAt(fila, 6).toString());
        }
    }

    private void listar() {
        DefaultTableModel modelo = (DefaultTableModel) vista.tablaProductos.getModel();
        modelo.setRowCount(0);
        List<Producto> lista = productoDAO.listar();
        for (Producto p : lista) {
            modelo.addRow(new Object[]{p.getId(), p.getCodigoSerie(), p.getNombre(), p.getMarca(), p.getModelo(), p.getPrecio(), p.getEstado()});
        }
    }

    private void limpiar() {
        vista.txtCodigo.setText("");
        vista.txtNombre.setText("");
        vista.txtMarca.setText("");
        vista.txtModelo.setText("");
        vista.txtPrecio.setText("");
        vista.cbEstado.setSelectedIndex(0);
        idSeleccionado = -1;
    }

    private void exito(String mensaje) {
        JOptionPane.showMessageDialog(vista, mensaje);
        limpiar();
        listar();
    }
}