package org.example.invertirContenido;

import org.example.listaSimple.ListaSimple;
import org.example.listaSimple.Nodo;

public class ListasInvertirContenido {
    public static <T> void invertirContenido(ListaSimple<T> lista) {
        if (lista == null) return;
        Nodo<T> cabeza = lista.getNodoPrimero();
        if (cabeza == null || cabeza.getSiguiente() == null) return;
        lista.setNodoPrimero(invertirRec(cabeza, null));
    }

    private static <T> Nodo<T> invertirRec(Nodo<T> actual, Nodo<T> anterior) {
        if (actual == null) return anterior;
        Nodo<T> sig = actual.getSiguiente();
        Nodo<T> nuevaCabeza = invertirRec(sig, actual);
        actual.setSiguiente(anterior);
        return nuevaCabeza;
    }
}
