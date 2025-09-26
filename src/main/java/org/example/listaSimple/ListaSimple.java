package org.example.listaSimple;
import java.util.Iterator;
public class ListaSimple<T> implements Iterable<T>{

    private int tamanio;
    private Nodo<T> nodoPrimero;


    public ListaSimple() {
        super();
        this.tamanio = 0;
        this.nodoPrimero = null;
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
        Nodo<T> nuevo = new Nodo<T>(valor);
        Nodo<T> actual = nodoPrimero;
        for (int i = 0; i < posicion - 1; i++) {
            actual = actual.getSiguiente();
        }
        nuevo.setSiguiente(actual.getSiguiente());
        actual.setSiguiente(nuevo);
        tamanio++;
    }

    public T obtenerValorNodo(int posicion) {
        Nodo<T> n = obtenerNodo(posicion);
        return n == null ? null : n.getValor();
    }

    public Nodo<T> obtenerNodo(int posicion) {
        if (!indiceValido(posicion)) return null;
        Nodo<T> actual = nodoPrimero;
        for (int i = 0; i < posicion; i++) {
            actual = actual.getSiguiente();
        }
        return actual;
    }

    public int obtenerPosicionNodo(T valor) {
        int i = 0;
        for (Nodo<T> actual = nodoPrimero; actual != null; actual = actual.getSiguiente()) {
            if ((valor == null && actual.getValor() == null) || (valor != null && valor.equals(actual.getValor()))) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public boolean indiceValido(int posicion) {
        return posicion >= 0 && posicion < tamanio;
    }

    public void eliminarPrimero() {
        if (nodoPrimero == null) return;
        nodoPrimero = nodoPrimero.getSiguiente();
        tamanio--;
    }

    public void eliminarUltimo() {
        if (nodoPrimero == null) return;
        if (nodoPrimero.getSiguiente() == null) {
            nodoPrimero = null;
            tamanio = 0;
            return;
        }
        Nodo<T> actual = nodoPrimero;
        while (actual.getSiguiente().getSiguiente() != null) {
            actual = actual.getSiguiente();
        }
        actual.setSiguiente(null);
        tamanio--;
    }

    public void modificarNodo(int posicion, T nuevoValor) {
        Nodo<T> n = obtenerNodo(posicion);
        if (n != null) n.setValor(nuevoValor);
    }

    @SuppressWarnings("unchecked")
    public void ordenarLista() {
        if (nodoPrimero == null || nodoPrimero.getSiguiente() == null) return;
        boolean cambiado;
        do {
            cambiado = false;
            Nodo<T> actual = nodoPrimero;
            while (actual.getSiguiente() != null) {
                T a = actual.getValor();
                T b = actual.getSiguiente().getValor();
                if (((Comparable<? super T>) a).compareTo(b) > 0) {
                    actual.setValor(b);
                    actual.getSiguiente().setValor(a);
                    cambiado = true;
                }
                actual = actual.getSiguiente();
            }
        } while (cambiado);
    }

    public void borrarLista() {
        nodoPrimero = null;
        tamanio = 0;
    }

    public int getTamanio() {
        return tamanio;
    }


    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }


    public Nodo<T> getNodoPrimero() {
        return nodoPrimero;
    }


    public void setNodoPrimero(Nodo<T> nodoPrimero) {
        this.nodoPrimero = nodoPrimero;
    }


    public void agregarInicio(T valor) {

        Nodo<T> nuevoNodo = new Nodo<T>(valor);

        if(estaVacia()) {
            nodoPrimero = nuevoNodo;
        }else {
            nuevoNodo.setSiguiente(nodoPrimero);
            nodoPrimero = nuevoNodo;
        }

        tamanio++;
    }

    public void eliminar(T valor) {
        if (nodoPrimero==null) {
            return;
        }
        if(nodoPrimero.getValor() == valor) {
            nodoPrimero=nodoPrimero.getSiguiente();
            return;
        }
        Nodo<T> actual = nodoPrimero;
        while(actual.getSiguiente()!= null && actual.getSiguiente().getValor()!=valor) {
            actual= actual.getSiguiente();
        }
        if(actual.getSiguiente()!=null) {
            actual.setSiguiente(actual.getSiguiente().getSiguiente());
        }
    }

    public String print() {

        String lista = "";

        // i = 0;  i < arreglo.length;                            i++;
        for(Nodo<T> aux = nodoPrimero; aux != null;aux = aux.getSiguiente() ) {
            lista += aux.getValor() + " ->";
        }

        return lista;


    }

    public void imprimirArreglo1(T arreglo[]) {

        for (int i = 0; i < arreglo.length; i++) {
            System.out.println(arreglo[i]);
        }
    }

    public void imprimirArreglo(T arreglo[]) {
        imprimirArreglo(arreglo, 0);
    }

    public void imprimirArreglo(T arreglo[], int i) {

        if(i < arreglo.length) {
            System.out.println(arreglo[i]);
            imprimirArreglo(arreglo, i + 1);
        }else {
            System.out.println("Termine");
        }

    }

    public void imprimirArreglo3(T arreglo[], int i) {

        if(i == arreglo.length) {//
            System.out.println("Termino");
        }else {
            System.out.println(arreglo[i]);
            imprimirArreglo(arreglo, i + 1);
        }

    }


    public void agregarFinal(T valor) {

        Nodo<T> nuevoNodo = new Nodo(valor);

        if(estaVacia()) {
            nodoPrimero = nuevoNodo;
        }else {

            Nodo<T> aux = nodoPrimero;
            while(aux.getSiguiente() != null) {
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(nuevoNodo);
        }

        tamanio++;
    }

    private boolean estaVacia() {
        // TODO Auto-generated method stub
        return tamanio == 0;
    }


    @Override
    public Iterator<T> iterator() {

        return new ListaSimpleIterador<T>(nodoPrimero);
    }



}
