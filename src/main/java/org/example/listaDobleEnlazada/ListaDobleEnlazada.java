package org.example.listaDobleEnlazada;

import java.util.Iterator;

public class ListaDobleEnlazada<T extends Comparable<T>> implements Iterable<T>{

    private NodoDoble<T> cabeza;
    private NodoDoble<T> cola;
    private int tamaño;

    public ListaDobleEnlazada() {
        this.cabeza = cabeza;
        this.cola = this.cola;
        this.tamaño = 0;
    }

    public int getTamaño() {
        return tamaño;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

    public boolean indiceValido(int indice) {
        return indice >= 0 && indice < tamaño;
    }

    public void agregarInicio(T valor) {
        NodoDoble<T> nuevo = new NodoDoble<>(valor);
        if (estaVacia()) {
            cabeza = cola = nuevo;
        } else {
            nuevo.siguiente = cabeza;
            cabeza.anterior = nuevo;
            cabeza = nuevo;
        }
        tamaño++;
    }

    public void agregarFinal(T valor) {
        NodoDoble<T> nuevo = new NodoDoble<>(valor);
        if (estaVacia()) {
            cabeza = cola = nuevo;
        } else {
            cola.siguiente = nuevo;
            nuevo.anterior = cola;
            cola = nuevo;
        }
        tamaño++;
    }

     public void agregar(T valor, int indice) {
        if (indice == 0) {
            agregarInicio(valor);
        } else if (indice == tamaño) {
            agregarFinal(valor);
        } else if (indiceValido(indice)) {
            NodoDoble<T> nuevo = new NodoDoble<>(valor);
            NodoDoble<T> actual = obtenerNodo(indice);
            NodoDoble<T> previo = actual.anterior;

            previo.siguiente = nuevo;
            nuevo.anterior = previo;
            nuevo.siguiente = actual;
            actual.anterior = nuevo;
            tamaño++;
        } else {
            throw new IndexOutOfBoundsException("Índice inválido");
        }
    }

    public NodoDoble<T> obtenerNodo(int indice) {
        if (!indiceValido(indice)) {
            throw new IndexOutOfBoundsException("Índice inválido");
        }
        NodoDoble<T> temp = cabeza;
        for (int i = 0; i < indice; i++) {
            temp = temp.siguiente;
        }
        return temp;
    }

    public T obtenerValorNodo(int indice) {
        return obtenerNodo(indice).valor;
    }

    public int obtenerPosicionNodo(T valor) {
        NodoDoble<T> temp = cabeza;
        int indice = 0;
        while (temp != null) {
            if (temp.valor.equals(valor)) {
                return indice;
            }
            temp = temp.siguiente;
            indice++;
        }
        return -1;
    }

    public void eliminarPrimero() {
        if (estaVacia()) return;
        if (tamaño == 1) {
            cabeza = cola = null;
        } else {
            cabeza = cabeza.siguiente;
            cabeza.anterior = null;
        }
        tamaño--;
    }

    public void eliminarUltimo() {
        if (estaVacia()) return;
        if (tamaño == 1) {
            cabeza = cola = null;
        } else {
            cola = cola.anterior;
            cola.siguiente = null;
        }
        tamaño--;
    }

    public void eliminar(T valor) {
        if (estaVacia()) return;

        if (cabeza.valor.equals(valor)) {
            eliminarPrimero();
            return;
        }
        if (cola.valor.equals(valor)) {
            eliminarUltimo();
            return;
        }

        NodoDoble<T> temp = cabeza;
        while (temp != null) {
            if (temp.valor.equals(valor)) {
                temp.anterior.siguiente = temp.siguiente;
                temp.siguiente.anterior = temp.anterior;
                tamaño--;
                return;
            }
            temp = temp.siguiente;
        }
    }

    public void modificarNodo(int indice, T nuevoValor) {
        NodoDoble<T> nodo = obtenerNodo(indice);
        nodo.valor = nuevoValor;
    }

    public void ordenarLista() {
        if (tamaño <= 1) return;
        boolean cambiado;
        do {
            cambiado = false;
            NodoDoble<T> temp = cabeza;
            while (temp != null && temp.siguiente != null) {
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
        NodoDoble<T> temp = cabeza;
        System.out.print("[");
        while (temp != null) {
            System.out.print(temp.valor);
            temp = temp.siguiente;
            if (temp != null) System.out.print(", ");
        }
        System.out.println("]");
    }

    public void borrarLista() {
        cabeza = cola = null;
        tamaño = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private NodoDoble<T> actual = cabeza;

            @Override
            public boolean hasNext() {
                return actual != null;
            }

            @Override
            public T next() {
                T valor = actual.valor;
                actual = actual.siguiente;
                return valor;
            }
        };
    }
}



