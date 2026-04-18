package com.comercialrojas.model;

public class Cliente {
    private int id;
    private String nombre;
    private String dni;
    private String domicilio;
    private String distrito;
    private String telefono;

    public Cliente(){

    }

    public Cliente(int id, String nombre, String dni, String domicilio, String distrito, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.dni = dni;
        this.domicilio = domicilio;
        this.distrito = distrito;
        this.telefono = telefono;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public String getDomicilio() { return domicilio; }
    public void setDomicilio(String domicilio) { this.domicilio = domicilio; }

    public String getDistrito() { return distrito; }
    public void setDistrito(String distrito) { this.distrito = distrito; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

}
