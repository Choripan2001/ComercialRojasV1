package com.comercialrojas.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VentaDAO {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public boolean registrar(Venta venta) {
        String sql = "INSERT INTO Venta (id_cliente, id_producto, fecha_compra, total, observacion) VALUES (?, ?, ?, ?, ?)";
        try {
            con = Conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, venta.getIdCliente());
            ps.setInt(2, venta.getIdProducto());
            ps.setDate(3, venta.getFechaCompra());
            ps.setDouble(4, venta.getTotal());
            ps.setString(5, venta.getObservacion());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        } finally {
            cerrarConexiones();
        }
    }

    public int obtenerUltimoId() {
        int id = 0;
        String sql = "SELECT MAX(id) AS id FROM Venta";
        try {
            con = Conexion.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return id;
    }

    private void cerrarConexiones() {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}