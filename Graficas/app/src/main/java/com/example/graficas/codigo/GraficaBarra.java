package com.example.graficas.codigo;

import java.util.ArrayList;

public class GraficaBarra {

    private String titulo;
    private String[] ejeX;
    private Double[] ejeY;
    private ArrayList<Double[]> uniones;

    public GraficaBarra(String titulo, String[] ejeX, Double[] ejeY, ArrayList<Double[]> uniones) {
        this.titulo = titulo;
        this.ejeX = ejeX;
        this.ejeY = ejeY;
        this.uniones = uniones;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String[] getEjeX() {
        return ejeX;
    }

    public void setEjeX(String[] ejeX) {
        this.ejeX = ejeX;
    }

    public Double[] getEjeY() {
        return ejeY;
    }

    public void setEjeY(Double[] ejeY) {
        this.ejeY = ejeY;
    }

    public ArrayList<Double[]> getUniones() {
        return uniones;
    }

    public void setUniones(ArrayList<Double[]> uniones) {
        this.uniones = uniones;
    }
    
    
  
}
