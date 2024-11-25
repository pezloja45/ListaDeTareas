package com.pezloja.listadetareas;

import java.util.ArrayList;
import java.util.List;

public class ControladorTarea {
    private static List<Tarea> lstTareas = new ArrayList<>();

    public static void initArray() {
        Tarea t;
        t = new Tarea("Jugar al play", "20/11/2024", "22:00", "WUAFBUFGWAUJGBGAWWWWWWWW fabfaw bufawb ufwbuwf abuwf abuwfa", 0);
        lstTareas.add(t);
        t = new Tarea("Salir a run", "22/010/2023", "22:01", "fawfgawg dwa ffw af", 1);
        lstTareas.add(t);
        t = new Tarea("Apagar fogos", "22/010/2023", "22:02", "ejemplo de desc", 2);
        lstTareas.add(t);
        t = new Tarea("Aprender pmdm", "22/010/2023", "22:03", "ejemplo de desc", 3);
        lstTareas.add(t);
        t = new Tarea("Cumple hermana", "22/010/2023", "22:04", "ejemplo de desc", 4);
        lstTareas.add(t);
        t = new Tarea("Clapear a hugo en fortnite", "22/010/2023", "22:05", "ejemplo de desc", 5);
        lstTareas.add(t);
    }

    public static List<Tarea> getTareas() {
        return lstTareas;
    }

    public static Tarea getTarea(int posTarea) {
        return lstTareas.get(posTarea);
    }

    public static void editTarea(int posTarea, Tarea t) {
        lstTareas.set(posTarea, t);
    }

    public static void addTarea(Tarea t) {
        lstTareas.add(t);
    }

    public static void ordenarTareas() {
        lstTareas.sort((t1, t2) -> t1.getNomTarea().compareToIgnoreCase(t2.getNomTarea()));
    }
}
