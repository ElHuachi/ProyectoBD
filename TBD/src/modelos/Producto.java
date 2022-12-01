/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 * POJOs
 * @author paveg
 */

public class Producto {
    private int id;
    private String nombre;
    private String cantidadXUnidad;
    private int idProveedor;
    private int idCategoria;
    //Atributos extra que se usarán para mostrar los nombre
    //del proveedor y categoría en la lista
    private String proveedor;
    private String categoria;
    private double precio;
    private int existencia;
    private int nivelReorden;
    private int unidadesEnOrden;
    private int descontinuado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCantidadXUnidad() {
        return cantidadXUnidad;
    }

    public void setCantidadXUnidad(String cantidadXUnidad) {
        this.cantidadXUnidad = cantidadXUnidad;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public int getNivelReorden() {
        return nivelReorden;
    }

    public void setNivelReorden(int nivelReorden) {
        this.nivelReorden = nivelReorden;
    }

    public int getUnidadesEnOrden() {
        return unidadesEnOrden;
    }

    public void setUnidadesEnOrden(int unidadesEnOrden) {
        this.unidadesEnOrden = unidadesEnOrden;
    }

    public int getDescontinuado() {
        return descontinuado;
    }

    public void setDescontinuado(int descontinuado) {
        this.descontinuado = descontinuado;
    }

    public Producto(int id, String nombre, int idProveedor, int idCategoria, 
            String cantidadXUnidad, double precio, int existencia, int nivelReorden, 
            int unidadesEnOrden, int descontinuado) {
        this.id = id;
        this.nombre = nombre;
        this.idProveedor = idProveedor;
        this.idCategoria = idCategoria;
        this.cantidadXUnidad = cantidadXUnidad;
        this.precio = precio;
        this.existencia = existencia;
        this.nivelReorden = nivelReorden;
        this.unidadesEnOrden = unidadesEnOrden;
        this.descontinuado = descontinuado;
    }

    public Producto(int id, String nombre, String proveedor, String categoria, double precio, int existencia, int descontinuado) {
        this.id = id;
        this.nombre = nombre;
        this.proveedor = proveedor;
        this.categoria = categoria;
        this.cantidadXUnidad = cantidadXUnidad;
        this.precio = precio;
        this.existencia = existencia;
        this.descontinuado = descontinuado;
    }

}
