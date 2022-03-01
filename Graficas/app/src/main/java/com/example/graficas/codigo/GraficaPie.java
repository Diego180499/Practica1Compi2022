
package com.example.graficas.codigo;

import java.util.ArrayList;

public class GraficaPie {
    
    private String titulo;
    private String tipo;
    private String [] etiquetas;
    private Double [] valores;
    private double total;
    private ArrayList<Double []> uniones;
    private String extra;

    /*
    GRAFICA PIE DE TIPO PORCENTAJE
    */
    public GraficaPie(String titulo, String tipo, String[] etiquetas, Double[] valores, ArrayList<Double []> uniones, String extra) {
        this.titulo = titulo;
        this.tipo = tipo;
        this.etiquetas = etiquetas;
        this.valores = valores;
        this.uniones = uniones;
        this.extra = extra;
    }

    public GraficaPie(String titulo, String tipo, String[] etiquetas, Double[] valores, double total, ArrayList<Double[]> uniones, String extra) {
        this.titulo = titulo;
        this.tipo = tipo;
        this.etiquetas = etiquetas;
        this.valores = valores;
        this.total = total;
        this.uniones = uniones;
        this.extra = extra;
    }
    
    
    
    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String[] getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(String[] etiquetas) {
        this.etiquetas = etiquetas;
    }

    public Double[] getValores() {
        return valores;
    }

    public void setValores(Double[] valores) {
        this.valores = valores;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public ArrayList<Double[]> getUniones() {
        return uniones;
    }

    public void setUniones(ArrayList<Double[]> uniones) {
        this.uniones = uniones;
    }

    

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
    
    
    
    
    
}
