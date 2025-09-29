package org.example.invertirContenido;

import org.example.listaSimple.ListaSimple;

public class Demo {
    public static void main(String[] args) {
        ListaSimple<Integer> lista = new ListaSimple<>();

        System.out.println("== Lista vacía ==");
        System.out.println(lista.print());
        ListasInvertirContenido.invertirContenido(lista);
        System.out.println("Invertida: " + lista.print());

        System.out.println("\n== Lista con un solo nodo ==");
        lista.agregarInicio(10);
        System.out.println(lista.print());
        ListasInvertirContenido.invertirContenido(lista);
        System.out.println("Invertida: " + lista.print());

        System.out.println("\n== Lista con varios nodos ==");
        lista.borrarLista();
        lista.agregarFinal(1);
        lista.agregarFinal(2);
        lista.agregarFinal(3);
        lista.agregarFinal(4);
        lista.agregarFinal(5);

        System.out.println("Original: " + lista.print());
        ListasInvertirContenido.invertirContenido(lista);
        System.out.println("Invertida: " + lista.print());

        System.out.println("\n== Otra prueba con cadenas ==");
        ListaSimple<String> listaStr = new ListaSimple<>();
        listaStr.agregarFinal("A");
        listaStr.agregarFinal("B");
        listaStr.agregarFinal("C");
        listaStr.agregarFinal("D");

        System.out.println("Original: " + listaStr.print());
        ListasInvertirContenido.invertirContenido(listaStr);
        System.out.println("Invertida: " + listaStr.print());
    }
}
