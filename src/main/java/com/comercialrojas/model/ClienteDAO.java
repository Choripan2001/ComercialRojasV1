package com.comercialrojas.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public boolean registrar(Cliente cliente) {
        String sql = "INSERT INTO Cliente (nombre, dni, domicilio, distrito, telefono) VALUES (?, ?, ?, ?, ?)";
        try {
            con = Conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getDni());
            ps.setString(3, cliente.getDomicilio());
            ps.setString(4, cliente.getDistrito());
            ps.setString(5, cliente.getTelefono());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        } finally {
            cerrarConexiones();
        }
    }

    public List<Cliente> listar() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM Cliente";
        try {
            con = Conexion.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setId(rs.getInt("id"));
                c.setNombre(rs.getString("nombre"));
                c.setDni(rs.getString("dni"));
                c.setDomicilio(rs.getString("domicilio"));
                c.setDistrito(rs.getString("distrito"));
                c.setTelefono(rs.getString("telefono"));
                lista.add(c);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return lista;
    }

    public boolean actualizar(Cliente cliente) {
        String sql = "UPDATE Cliente SET nombre=?, dni=?, domicilio=?, distrito=?, telefono=? WHERE id=?";
        try {
            con = Conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getDni());
            ps.setString(3, cliente.getDomicilio());
            ps.setString(4, cliente.getDistrito());
            ps.setString(5, cliente.getTelefono());
            ps.setInt(6, cliente.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        } finally {
            cerrarConexiones();
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM Cliente WHERE id=?";
        try {
            con = Conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
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