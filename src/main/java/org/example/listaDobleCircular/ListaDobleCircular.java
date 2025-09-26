package org.example.listaDobleCircular;

import java.util.Iterator;

public class ListaDobleCircular<T extends Comparable<? super T>> implements Iterable<T> {

    private int tamanio;
    private Nodo<T> cabeza;

    public ListaDobleCircular() {
        this.tamanio = 0;
        this.cabeza = null;
    }

    public int getTamanio() {
        return tamanio;
    }

    public boolean estaVacia() {
        return tamanio == 0;
    }

    public void agregarInicio(T valor) {
        Nodo<T> nuevo = new Nodo<>(valor);
        if (estaVacia()) {
            cabeza = nuevo;
        } else {
            Nodo<T> cola = cabeza.getAnterior();
            nuevo.setSiguiente(cabeza);
            nuevo.setAnterior(cola);
            cola.setSiguiente(nuevo);
            cabeza.setAnterior(nuevo);
            cabeza = nuevo;
        }
        tamanio++;
    }

    public void agregarFinal(T valor) {
        if (estaVacia()) {
            agregarInicio(valor);
            return;
        }
        Nodo<T> nuevo = new Nodo<>(valor);
        Nodo<T> cola = cabeza.getAnterior();
        nuevo.setSiguiente(cabeza);
        nuevo.setAnterior(cola);
        cola.setSiguiente(nuevo);
        cabeza.setAnterior(nuevo);
        tamanio++;
    }

    public void Agregar(int posicion, T valor) {
        if (posicion <= 0) {
            agregarInicio(valor);
            return;
        }
        if (posicion >= tamanio) {
            agregarFinal(valor);
            return;
        }
        Nodo<T> actual = obtenerNodo(posicion);
        Nodo<T> ant = actual.getAnterior();
        Nodo<T> nuevo = new Nodo<>(valor);
        nuevo.setAnterior(ant);
        nuevo.setSiguiente(actual);
        ant.setSiguiente(nuevo);
        actual.setAnterior(nuevo);
        tamanio++;
    }

    public T obtenerValorNodo(int posicion) {
        Nodo<T> n = obtenerNodo(posicion);
        return n == null ? null : n.getValor();
    }

    public Nodo<T> obtenerNodo(int posicion) {
        if (!indiceValido(posicion)) return null;
        Nodo<T> actual = cabeza;
        for (int i = 0; i < posicion; i++) actual = actual.getSiguiente();
        return actual;
    }

    public int obtenerPosicionNodo(T valor) {
        if (estaVacia()) return -1;
        Nodo<T> actual = cabeza;
        for (int i = 0; i < tamanio; i++) {
            if ((actual.getValor() == null && valor == null) || (actual.getValor() != null && actual.getValor().equals(valor))) {
                return i;
            }
            actual = actual.getSiguiente();
        }
        return -1;
    }

    public boolean indiceValido(int posicion) {
        return posicion >= 0 && posicion < tamanio;
    }

    public void eliminarPrimero() {
        if (estaVacia()) return;
        if (tamanio == 1) {
            cabeza = null;
        } else {
            Nodo<T> cola = cabeza.getAnterior();
            Nodo<T> sig = cabeza.getSiguiente();
            cola.setSiguiente(sig);
            sig.setAnterior(cola);
            cabeza = sig;
        }
        tamanio--;
    }

    public void eliminarUltimo() {
        if (estaVacia()) return;
        if (tamanio == 1) {
            cabeza = null;
        } else {
            Nodo<T> cola = cabeza.getAnterior();
            Nodo<T> nuevaCola = cola.getAnterior();
            nuevaCola.setSiguiente(cabeza);
            cabeza.setAnterior(nuevaCola);
        }
        tamanio--;
    }

    public void Eliminar(T valor) {
        if (estaVacia()) return;
        Nodo<T> actual = cabeza;
        for (int i = 0; i < tamanio; i++) {
            if ((actual.getValor() == null && valor == null) || (actual.getValor() != null && actual.getValor().equals(valor))) {
                if (tamanio == 1) {
                    cabeza = null;
                } else {
                    Nodo<T> ant = actual.getAnterior();
                    Nodo<T> sig = actual.getSiguiente();
                    ant.setSiguiente(sig);
                    sig.setAnterior(ant);
                    if (actual == cabeza) cabeza = sig;
                }
                tamanio--;
                return;
            }
            actual = actual.getSiguiente();
        }
    }

    public void modificarNodo(int posicion, T nuevoValor) {
        Nodo<T> n = obtenerNodo(posicion);
        if (n != null) n.setValor(nuevoValor);
    }

    public void ordenarLista() {
        if (tamanio < 2) return;
        for (int i = 0; i < tamanio; i++) {
            Nodo<T> actual = cabeza;
            for (int j = 0; j < tamanio - 1; j++) {
                Nodo<T> sig = actual.getSiguiente();
                if (actual.getValor().compareTo(sig.getValor()) > 0) {
                    T tmp = actual.getValor();
                    actual.setValor(sig.getValor());
                    sig.setValor(tmp);
                }
                actual = sig;
            }
        }
    }

    public String imprimirLista() {
        if (estaVacia()) return "";
        StringBuilder sb = new StringBuilder();
        Nodo<T> actual = cabeza;
        for (int i = 0; i < tamanio; i++) {
            sb.append(actual.getValor());
            if (i < tamanio - 1) sb.append(" <-> ");
            actual = actual.getSiguiente();
        }
        sb.append(" (circular)");
        return sb.toString();
    }


    public void borrarLista() {
        cabeza = null;
        tamanio = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListaDBIterador<>(cabeza, tamanio);
    }
}