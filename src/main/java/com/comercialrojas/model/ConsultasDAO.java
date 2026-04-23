package com.comercialrojas.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsultasDAO {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public List<Object[]> listarVentasSemanales() {
        List<Object[]> lista = new ArrayList<>();
        String sql = "SELECT v.id, c.nombre, p.nombre, v.fecha_compra, v.total " +
                "FROM Venta v " +
                "JOIN Cliente c ON v.id_cliente = c.id " +
                "JOIN Producto p ON v.id_producto = p.id " +
                "WHERE v.fecha_compra >= CAST(DATEADD(day, -7, GETDATE()) AS DATE) " +
                "ORDER BY v.fecha_compra DESC";
        try {
            con = Conexion.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new Object[]{rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getDouble(5)});
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return lista;
    }

    public List<Object[]> listarMorasProximas() {
        List<Object[]> lista = new ArrayList<>();
        String sql = "SELECT l.id, c.nombre, l.numero_letra, l.fecha_vencimiento, l.monto " +
                "FROM LetraPago l " +
                "JOIN Venta v ON l.id_venta = v.id " +
                "JOIN Cliente c ON v.id_cliente = c.id " +
                "WHERE l.estado = 'Pendiente' AND l.fecha_vencimiento BETWEEN CAST(GETDATE() AS DATE) AND CAST(DATEADD(day, 7, GETDATE()) AS DATE) " +
                "ORDER BY l.fecha_vencimiento ASC";
        try {
            con = Conexion.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new Object[]{rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getDate(4), rs.getDouble(5)});
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return lista;
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