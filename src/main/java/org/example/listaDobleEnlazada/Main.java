package org.example.listaDobleEnlazada;

//javac -d . .\src\main\java\org\example\listaDobleEnlazada\*.java
//Comando para ejecutar: java org.example.listaDobleEnlazada.Main
public class Main {
    public static void main(String[] args) {
        ListaDobleEnlazada<Integer> lista = new ListaDobleEnlazada<>();

        lista.agregarInicio(5);
        lista.agregarFinal(10);
        lista.agregarFinal(3);
        lista.agregar(8, 1);

        System.out.println("Lista inicial:");
        lista.imprimirLista(); // [5, 8, 10, 3]

        lista.ordenarLista();
        System.out.println("Lista ordenada:");
        lista.imprimirLista(); // [3, 5, 8, 10]

        lista.eliminar(8);
        System.out.println("Después de eliminar 8:");
        lista.imprimirLista(); // [3, 5, 10]

        lista.modificarNodo(1, 99);
        System.out.println("Después de modificar índice 1 -> 99:");
        lista.imprimirLista(); // [3, 99, 10]

        System.out.println("Recorriendo con Iterator:");
        for (int val : lista) {
            System.out.println(val);
        }

        lista.borrarLista();
        System.out.println("Después de borrar lista:");
        lista.imprimirLista(); // []
    }
}

