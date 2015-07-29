package com.example.poo_code.onlinemobilestore.Entities;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Poo_Code on 19/07/2015.
 */
public class Product {

    @SerializedName("idproduct")
    private int idProduct;

    @SerializedName("nombre")
    private String nombre;

    @SerializedName("precio")
    private Double precio;

    @SerializedName("descripcion")
    private String descripcion;

    @SerializedName("idcategory")
    private int idCategoria;

    @SerializedName("nombreCategoria")
    private String categoria;

    @SerializedName("imagen")
    private String imagemes;


    @SerializedName("tallas")
    private ArrayList<MasterItem> talla;

    @SerializedName("color")
    private ArrayList<MasterItem> color;

    @SerializedName("compocision")
    private ArrayList<MasterItem> composicion;

    @SerializedName("cuidado")
    private ArrayList<MasterItem> cuidado;

    public int getIdProduct() { return idProduct; }

    public void setIdProduct(int idProduct) { this.idProduct = idProduct; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public Double getPrecio() { return precio; }

    public void setPrecio(Double precio) { this.precio = precio; }

    public String getDescripcion() { return descripcion; }

    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public int getIdCategoria() { return idCategoria; }

    public void setIdCategoria(int idCategoria) { this.idCategoria = idCategoria; }

    public String getCategoria() { return categoria; }

    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getImagemes() { return imagemes; }

    public void setImagemes(String imagemes) { this.imagemes = imagemes; }

    public ArrayList<MasterItem> getTalla() { return talla; }

    public void setTalla(ArrayList<MasterItem> talla) { this.talla = talla; }

    public ArrayList<MasterItem> getColor() {
        return color;
    }

    public void setColor(ArrayList<MasterItem> color) {
        this.color = color;
    }

    public ArrayList<MasterItem> getComposicion() {
        return composicion;
    }

    public void setComposicion(ArrayList<MasterItem> composicion) {
        this.composicion = composicion;
    }

    public ArrayList<MasterItem> getCuidado() {
        return cuidado;
    }

    public void setCuidado(ArrayList<MasterItem> cuidado) {
        this.cuidado = cuidado;
    }

    @Override
    public String  toString(){
        return getNombre();
    }
}
