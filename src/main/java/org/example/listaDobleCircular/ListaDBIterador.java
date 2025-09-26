package org.example.listaDobleCircular;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaDBIterador<T> implements Iterator<T> {
    private final int tamanio;
    private Nodo<T> actual;
    private int visitados;

    public ListaDBIterador(Nodo<T> cabeza, int tamanio) {
        this.tamanio = tamanio;
        this.actual = cabeza;
        this.visitados = 0;
    }

    @Override
    public boolean hasNext() {
        return visitados < tamanio;
    }

    @Override
    public T next() {
        if (!hasNext()) throw new NoSuchElementException();
        T v = actual.getValor();
        actual = actual.getSiguiente();
        visitados++;
        return v;
    }
}