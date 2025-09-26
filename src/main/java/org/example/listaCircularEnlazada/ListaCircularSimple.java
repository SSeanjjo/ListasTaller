package org.example.listaCircularEnlazada;

import java.util.Iterator;

public class ListaCircularSimple<T extends Comparable<T>> implements Iterable<T> {
    private NodoSimple<T> cabeza;
    private int tamaño;

    public ListaCircularSimple() {
        this.cabeza = null;
        this.tamaño = 0;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

    public boolean indiceValido(int indice) {
        return indice >= 0 && indice < tamaño;
    }

    public void agregarInicio(T valor) {
        NodoSimple<T> nuevo = new NodoSimple<>(valor);
        if (estaVacia()) {
            cabeza = nuevo;
        } else {
            NodoSimple<T> cola = cabeza;
            while (cola.siguiente != cabeza) {
                cola = cola.siguiente;
            }
            nuevo.siguiente = cabeza;
            cola.siguiente = nuevo;
            cabeza = nuevo;
        }
        tamaño++;
    }

    public void agregarFinal(T valor) {
        if (estaVacia()) {
            agregarInicio(valor);
        } else {
            NodoSimple<T> nuevo = new NodoSimple<>(valor);
            NodoSimple<T> cola = cabeza;
            while (cola.siguiente != cabeza) {
                cola = cola.siguiente;
            }
            cola.siguiente = nuevo;
            nuevo.siguiente = cabeza;
            tamaño++;
        }
    }

    public void agregar(T valor, int indice) {
        if (indice == 0) {
            agregarInicio(valor);
        } else if (indice == tamaño) {
            agregarFinal(valor);
        } else if (indiceValido(indice)) {
            NodoSimple<T> actual = cabeza;
            for (int i = 0; i < indice - 1; i++) {
                actual = actual.siguiente;
            }
            NodoSimple<T> nuevo = new NodoSimple<>(valor);
            nuevo.siguiente = actual.siguiente;
            actual.siguiente = nuevo;
            tamaño++;
        } else {
            throw new IndexOutOfBoundsException("Índice inválido");
        }
    }

    public NodoSimple<T> obtenerNodo(int indice) {
        if (!indiceValido(indice)) throw new IndexOutOfBoundsException("Índice inválido");
        NodoSimple<T> temp = cabeza;
        for (int i = 0; i < indice; i++) {
            temp = temp.siguiente;
        }
        return temp;
    }

    public T obtenerValorNodo(int indice) {
        return obtenerNodo(indice).valor;
    }

    public int obtenerPosicionNodo(T valor) {
        if (estaVacia()) return -1;
        NodoSimple<T> temp = cabeza;
        for (int i = 0; i < tamaño; i++) {
            if (temp.valor.equals(valor)) return i;
            temp = temp.siguiente;
        }
        return -1;
    }

    public void eliminarPrimero() {
        if (estaVacia()) return;
        if (tamaño == 1) {
            cabeza = null;
        } else {
            NodoSimple<T> cola = cabeza;
            while (cola.siguiente != cabeza) {
                cola = cola.siguiente;
            }
            cabeza = cabeza.siguiente;
            cola.siguiente = cabeza;
        }
        tamaño--;
    }

    public void eliminarUltimo() {
        if (estaVacia()) return;
        if (tamaño == 1) {
            cabeza = null;
        } else {
            NodoSimple<T> actual = cabeza;
            while (actual.siguiente.siguiente != cabeza) {
                actual = actual.siguiente;
            }
            actual.siguiente = cabeza;
        }
        tamaño--;
    }

    public void eliminar(T valor) {
        if (estaVacia()) return;

        if (cabeza.valor.equals(valor)) {
            eliminarPrimero();
            return;
        }

        NodoSimple<T> actual = cabeza;
        while (actual.siguiente != cabeza) {
            if (actual.siguiente.valor.equals(valor)) {
                actual.siguiente = actual.siguiente.siguiente;
                tamaño--;
                return;
            }
            actual = actual.siguiente;
        }
    }

    public void modificarNodo(int indice, T nuevoValor) {
        NodoSimple<T> nodo = obtenerNodo(indice);
        nodo.valor = nuevoValor;
    }

    public void ordenarLista() {
        if (tamaño <= 1) return;
        boolean cambiado;
        do {
            cambiado = false;
            NodoSimple<T> temp = cabeza;
            for (int i = 0; i < tamaño - 1; i++) {
                if (temp.valor.compareTo(temp.siguiente.valor) > 0) {
                    T aux = temp.valor;
                    temp.valor = temp.siguiente.valor;
                    temp.siguiente.valor = aux;
                    cambiado = true;
                }
                temp = temp.siguiente;
            }
        } while (cambiado);
    }

    public void imprimirLista() {
        if (estaVacia()) {
            System.out.println("[]");
            return;
        }
        NodoSimple<T> temp = cabeza;
        System.out.print("[");
        for (int i = 0; i < tamaño; i++) {
            System.out.print(temp.valor);
            temp = temp.siguiente;
            if (i < tamaño - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    public void borrarLista() {
        cabeza = null;
        tamaño = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private NodoSimple<T> actual = cabeza;
            private int count = 0;

            @Override
            public boolean hasNext() {
                return count < tamaño;
            }

            @Override
            public T next() {
                T valor = actual.valor;
                actual = actual.siguiente;
                count++;
                return valor;
            }
        };
    }
}
