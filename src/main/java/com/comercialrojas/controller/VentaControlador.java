package com.comercialrojas.controller;

import com.comercialrojas.model.*;
import com.comercialrojas.view.PanelVenta;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class VentaControlador implements ActionListener {
    private PanelVenta vista;
    private VentaDAO ventaDAO;
    private LetraPagoDAO letraDAO;
    private ClienteDAO clienteDAO;
    private ProductoDAO productoDAO;

    private int idClienteSeleccionado = -1;
    private int idProductoSeleccionado = -1;

    public VentaControlador(PanelVenta vista) {
        this.vista = vista;
        this.ventaDAO = new VentaDAO();
        this.letraDAO = new LetraPagoDAO();
        this.clienteDAO = new ClienteDAO();
        this.productoDAO = new ProductoDAO();

        this.vista.btnBuscarCliente.addActionListener(this);
        this.vista.btnBuscarProducto.addActionListener(this);
        this.vista.btnGenerarVenta.addActionListener(this);

        this.vista.txtFecha.setText(LocalDate.now().toString());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnBuscarCliente) {
            buscarCliente();
        } else if (e.getSource() == vista.btnBuscarProducto) {
            buscarProducto();
        } else if (e.getSource() == vista.btnGenerarVenta) {
            generarVenta();
        }
    }

    private void buscarCliente() {
        String dni = vista.txtDniCliente.getText();
        List<Cliente> clientes = clienteDAO.listar();
        for (Cliente c : clientes) {
            if (c.getDni().equals(dni)) {
                idClienteSeleccionado = c.getId();
                vista.lblNombreCliente.setText(c.getNombre());
                return;
            }
        }
        JOptionPane.showMessageDialog(vista, "Cliente no encontrado.");
    }

    private void buscarProducto() {
        String codigo = vista.txtCodigoProducto.getText();
        List<Producto> productos = productoDAO.listar();
        for (Producto p : productos) {
            if (p.getCodigoSerie().equals(codigo)) {
                idProductoSeleccionado = p.getId();
                vista.lblNombreProducto.setText(p.getNombre());
                vista.txtTotal.setText(String.valueOf(p.getPrecio()));
                return;
            }
        }
        JOptionPane.showMessageDialog(vista, "Producto no encontrado.");
    }

    private void generarVenta() {
        if (idClienteSeleccionado == -1 || idProductoSeleccionado == -1) {
            JOptionPane.showMessageDialog(vista, "Debe seleccionar un cliente y un producto.");
            return;
        }

        try {
            Date fecha = Date.valueOf(vista.txtFecha.getText());
            double total = Double.parseDouble(vista.txtTotal.getText());
            int cuotas = Integer.parseInt(vista.txtCuotas.getText());

            Venta venta = new Venta(0, idClienteSeleccionado, idProductoSeleccionado, fecha, total, "Venta en " + cuotas + " cuotas");

            if (ventaDAO.registrar(venta)) {
                int idVenta = ventaDAO.obtenerUltimoId();
                double montoPorCuota = total / cuotas;
                LocalDate fechaInicial = fecha.toLocalDate();

                for (int i = 1; i <= cuotas; i++) {
                    LocalDate fechaVencimiento = fechaInicial.plusMonths(i);
                    LetraPago letra = new LetraPago(0, idVenta, i, Date.valueOf(fechaVencimiento), montoPorCuota, "Pendiente", 0.0);
                    letraDAO.registrar(letra);
                }

                JOptionPane.showMessageDialog(vista, "Venta y cronograma registrados correctamente.");
                limpiar();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error en los datos ingresados.");
        }
    }

    private void limpiar() {
        vista.txtDniCliente.setText("");
        vista.lblNombreCliente.setText("---");
        vista.txtCodigoProducto.setText("");
        vista.lblNombreProducto.setText("---");
        vista.txtTotal.setText("");
        vista.txtCuotas.setText("");
        idClienteSeleccionado = -1;
        idProductoSeleccionado = -1;
    }
}