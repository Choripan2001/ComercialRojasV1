package com.comercialrojas.model;
import java.sql.Date;

public class LetraPago {
    private int id;
    private int idVenta;
    private int numeroLetra;
    private Date fechaVencimiento;
    private double monto;
    private String estado;
    private double mora;

    public LetraPago() {}

    public LetraPago(int id, int idVenta, int numeroLetra, Date fechaVencimiento, double monto, String estado, double mora) {
        this.id = id;
        this.idVenta = idVenta;
        this.numeroLetra = numeroLetra;
        this.fechaVencimiento = fechaVencimiento;
        this.monto = monto;
        this.estado = estado;
        this.mora = mora;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdVenta() { return idVenta; }
    public void setIdVenta(int idVenta) { this.idVenta = idVenta; }
    public int getNumeroLetra() { return numeroLetra; }
    public void setNumeroLetra(int numeroLetra) { this.numeroLetra = numeroLetra; }
    public Date getFechaVencimiento() { return fechaVencimiento; }
    public void setFechaVencimiento(Date fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }
    public double getMonto() { return monto; }
    public void setMonto(double monto) { this.monto = monto; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public double getMora() { return mora; }
    public void setMora(double mora) { this.mora = mora; }
}