package com.comercialrojas.model;
import java.sql.Date;

public class Venta {
    private int id;
    private int idCliente;
    private int idProducto;
    private Date fechaCompra;
    private double total;
    private String observacion;

    public Venta() {}

    public Venta(int id, int idCliente, int idProducto, Date fechaCompra, double total, String observacion) {
        this.id = id;
        this.idCliente = idCliente;
        this.idProducto = idProducto;
        this.fechaCompra = fechaCompra;
        this.total = total;
        this.observacion = observacion;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }
    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }
    public Date getFechaCompra() { return fechaCompra; }
    public void setFechaCompra(Date fechaCompra) { this.fechaCompra = fechaCompra; }
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
    public String getObservacion() { return observacion; }
    public void setObservacion(String observacion) { this.observacion = observacion; }
}