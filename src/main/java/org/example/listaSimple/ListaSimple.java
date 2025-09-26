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
