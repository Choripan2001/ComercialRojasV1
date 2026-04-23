package com.comercialrojas.controller;

import com.comercialrojas.model.ConsultasDAO;
import com.comercialrojas.view.PanelConsultas;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ConsultasControlador implements ActionListener {
    private PanelConsultas vista;
    private ConsultasDAO consultasDAO;

    public ConsultasControlador(PanelConsultas vista, ConsultasDAO consultasDAO) {
        this.vista = vista;
        this.consultasDAO = consultasDAO;

        this.vista.btnActualizar.addActionListener(this);

        cargarDatos();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnActualizar) {
            cargarDatos();
        }
    }

    private void cargarDatos() {
        listarVentas();
        listarMoras();
    }

    private void listarVentas() {
        DefaultTableModel modelo = (DefaultTableModel) vista.tablaVentas.getModel();
        modelo.setRowCount(0);
        List<Object[]> lista = consultasDAO.listarVentasSemanales();
        for (Object[] fila : lista) {
            modelo.addRow(fila);
        }
    }

    private void listarMoras() {
        DefaultTableModel modelo = (DefaultTableModel) vista.tablaMoras.getModel();
        modelo.setRowCount(0);
        List<Object[]> lista = consultasDAO.listarMorasProximas();
        for (Object[] fila : lista) {
            modelo.addRow(fila);
        }
    }
}