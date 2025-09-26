package org.example.listaSimple;

import java.util.Iterator;

public class ListaSimpleIterador<T> implements Iterator<T> {
    Nodo<T> aux;

    public ListaSimpleIterador(Nodo<T> primero) {
        aux = primero;
    }

    @Override
    public boolean hasNext() {

        return aux.getSiguiente() != null;
    }

    @Override
    public T next() {

        T valor = aux.getValor();
        aux = aux.getSiguiente();
        return valor;
    }
}
