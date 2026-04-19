package com.comercialrojas.controller;

import com.comercialrojas.model.Cliente;
import com.comercialrojas.model.ClienteDAO;
import com.comercialrojas.view.PanelCliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ClienteControlador implements ActionListener {
    private Cliente cliente;
    private ClienteDAO clienteDAO;
    private PanelCliente vista;
    private int idSeleccionado = -1;

    public ClienteControlador(Cliente cliente, ClienteDAO clienteDAO, PanelCliente vista) {
        this.cliente = cliente;
        this.clienteDAO = clienteDAO;
        this.vista = vista;

        this.vista.btnGuardar.addActionListener(this);
        this.vista.btnEditar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);

        this.vista.tablaClientes.addMouseListener(new MouseAdapter() {
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
            if (clienteDAO.registrar(cliente)) exito("Cliente registrado");
        } else if (e.getSource() == vista.btnEditar && idSeleccionado != -1) {
            asignarDatos();
            cliente.setId(idSeleccionado);
            if (clienteDAO.actualizar(cliente)) exito("Cliente actualizado");
        } else if (e.getSource() == vista.btnEliminar && idSeleccionado != -1) {
            if (clienteDAO.eliminar(idSeleccionado)) exito("Cliente eliminado");
        } else if (e.getSource() == vista.btnLimpiar) {
            limpiar();
        }
    }

    private void asignarDatos() {
        cliente.setNombre(vista.txtNombre.getText());
        cliente.setDni(vista.txtDni.getText());
        cliente.setDomicilio(vista.txtDomicilio.getText());
        cliente.setDistrito(vista.txtDistrito.getText());
        cliente.setTelefono(vista.txtTelefono.getText());
    }

    private void seleccionarFila() {
        int fila = vista.tablaClientes.getSelectedRow();
        if (fila >= 0) {
            idSeleccionado = Integer.parseInt(vista.tablaClientes.getValueAt(fila, 0).toString());
            vista.txtNombre.setText(vista.tablaClientes.getValueAt(fila, 1).toString());
            vista.txtDni.setText(vista.tablaClientes.getValueAt(fila, 2).toString());
            vista.txtDomicilio.setText(vista.tablaClientes.getValueAt(fila, 3).toString());
            vista.txtDistrito.setText(vista.tablaClientes.getValueAt(fila, 4).toString());
            vista.txtTelefono.setText(vista.tablaClientes.getValueAt(fila, 5).toString());
        }
    }

    private void listar() {
        DefaultTableModel modelo = (DefaultTableModel) vista.tablaClientes.getModel();
        modelo.setRowCount(0);
        List<Cliente> lista = clienteDAO.listar();
        for (Cliente c : lista) {
            modelo.addRow(new Object[]{c.getId(), c.getNombre(), c.getDni(), c.getDomicilio(), c.getDistrito(), c.getTelefono()});
        }
    }

    private void limpiar() {
        vista.txtNombre.setText("");
        vista.txtDni.setText("");
        vista.txtDomicilio.setText("");
        vista.txtDistrito.setText("");
        vista.txtTelefono.setText("");
        idSeleccionado = -1;
    }

    private void exito(String mensaje) {
        JOptionPane.showMessageDialog(vista, mensaje);
        limpiar();
        listar();
    }
}