package org.example.listaSimple;

public class Prueba {
        public static void main(String[] args) {
            ListaSimple<Integer> lista = new ListaSimple<>();

            System.out.println("== estaVacia ==");
            System.out.println(vacia(lista));

            System.out.println("\n== agregarInicio ==");
            lista.agregarInicio(20);
            lista.agregarInicio(10);
            dump(lista);

            System.out.println("\n== agregarFinal ==");
            lista.agregarFinal(30);
            lista.agregarFinal(40);
            dump(lista);

            System.out.println("\n== Agregar (posición específica) ==");
            lista.Agregar(0, 5);
            lista.Agregar(3, 25);
            lista.Agregar(999, 50);
            dump(lista);

            System.out.println("\n== obtenerValorNodo ==");
            System.out.println("pos 0 -> " + lista.obtenerValorNodo(0));
            System.out.println("pos 3 -> " + lista.obtenerValorNodo(3));
            System.out.println("pos ultimo -> " + lista.obtenerValorNodo(lista.getTamanio()-1));

            System.out.println("\n== obtenerNodo (mostrar valor) ==");
            System.out.println("pos 2 -> " + val(lista.obtenerNodo(2)));
            System.out.println("pos -1 -> " + val(lista.obtenerNodo(-1)));
            System.out.println("pos grande -> " + val(lista.obtenerNodo(10)));

            System.out.println("\n== obtenerPosicionNodo ==");
            System.out.println("valor 25 -> " + lista.obtenerPosicionNodo(25));
            System.out.println("valor 999 -> " + lista.obtenerPosicionNodo(999));

            System.out.println("\n== indiceValido ==");
            System.out.println("0 válido? " + lista.indiceValido(0));
            System.out.println("3 válido? " + lista.indiceValido(3));
            System.out.println("999 válido? " + lista.indiceValido(999));

            System.out.println("\n== eliminarPrimero ==");
            lista.eliminarPrimero();
            dump(lista);

            System.out.println("\n== eliminarUltimo ==");
            lista.eliminarUltimo();
            dump(lista);

            System.out.println("\n== eliminar (por valor) ==");
            lista.eliminar(25);
            dump(lista);

            System.out.println("\n== modificarNodo ==");
            int pos = Math.max(0, lista.getTamanio() - 2);
            System.out.println("antes pos " + pos + " -> " + lista.obtenerValorNodo(pos));
            lista.modificarNodo(pos, 99);
            System.out.println("después pos " + pos + " -> " + lista.obtenerValorNodo(pos));
            dump(lista);

            System.out.println("\n== ordenarLista ==");
            lista.Agregar(0, 42);
            lista.Agregar(0, 1);
            lista.Agregar(0, 17);
            lista.Agregar(0, 3);
            dump(lista);
            lista.ordenarLista();
            dump(lista);

            System.out.println("\n== iterator (for-each) ==");
            for (Integer x : lista) {
                System.out.print("[" + x + "] ");
            }
            System.out.println();

            System.out.println("\n== borrarLista ==");
            lista.borrarLista();
            System.out.println("tamaño -> " + lista.getTamanio());
            System.out.println("vacía? -> " + vacia(lista));
            dump(lista);
        }

        private static <T> void dump(ListaSimple<T> lista) {
            System.out.println("tamaño: " + lista.getTamanio());
            System.out.println("lista:  " + lista.print());
        }

        private static <T> String val(Nodo<T> n) {
            return n == null ? "null" : String.valueOf(n.getValor());
        }

        private static <T> boolean vacia(ListaSimple<T> lista) {
            return lista.getTamanio() == 0;
        }
}
