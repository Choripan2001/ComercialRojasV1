package com.comercialrojas.model;

public class Producto {
    private int id;
    private String codigoSerie;
    private String nombre;
    private String marca;
    private String modelo;
    private double precio;
    private String estado;

    public Producto() {}

    public Producto(int id, String codigoSerie, String nombre, String marca, String modelo, double precio, String estado) {
        this.id = id;
        this.codigoSerie = codigoSerie;
        this.nombre = nombre;
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
        this.estado = estado;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getCodigoSerie() { return codigoSerie; }
    public void setCodigoSerie(String codigoSerie) { this.codigoSerie = codigoSerie; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}