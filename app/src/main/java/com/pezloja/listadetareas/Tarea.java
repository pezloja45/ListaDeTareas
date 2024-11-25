package com.pezloja.listadetareas;

import java.io.Serializable;
import java.util.Calendar;

public class Tarea implements Serializable {
    private String nomTarea;
    private String fecha;
    private String hora;
    private String descripcion;
    private int categoria;

    public Tarea(String nomTarea, String fecha, String hora, String descripcion, int categoria) {
        this.nomTarea = nomTarea;
        this.fecha = fecha;
        this.hora = hora;
        this.descripcion = descripcion;
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public String getNomTarea() {
        return nomTarea;
    }

    public void setNomTarea(String nomTarea) {
        this.nomTarea = nomTarea;
    }

    public String getFecha() {
        if (fecha.equals("")) {
            Calendar c = Calendar.getInstance();
            int ano = c.get(Calendar.YEAR);
            int mes = c.get(Calendar.MONTH);
            int dia = c.get(Calendar.DAY_OF_MONTH);
            fecha = "" + ano + "/" + (mes + 1) + "/" + dia;
        }
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        if (hora.equals("")) {
            Calendar c = Calendar.getInstance();
            int h = c.get(Calendar.HOUR_OF_DAY);
            int m = c.get(Calendar.MINUTE);
            hora = "" + h + ":" + m;
        }
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
