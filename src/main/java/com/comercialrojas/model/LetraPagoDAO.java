package com.comercialrojas.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LetraPagoDAO {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public boolean registrar(LetraPago letra) {
        String sql = "INSERT INTO LetraPago (id_venta, numero_letra, fecha_vencimiento, monto, estado, mora) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            con = Conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, letra.getIdVenta());
            ps.setInt(2, letra.getNumeroLetra());
            ps.setDate(3, letra.getFechaVencimiento());
            ps.setDouble(4, letra.getMonto());
            ps.setString(5, letra.getEstado());
            ps.setDouble(6, letra.getMora());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        } finally {
            cerrarConexiones();
        }
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