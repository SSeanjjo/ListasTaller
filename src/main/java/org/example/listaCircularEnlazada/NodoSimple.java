package org.example.listaCircularEnlazada;

// NodoCircular.java
public class NodoSimple<T> {
    T valor;
    NodoSimple<T> siguiente;

    public NodoSimple(T valor) {
        this.valor = valor;
        this.siguiente = this; // apunta a sí mismo al inicio
    }
}