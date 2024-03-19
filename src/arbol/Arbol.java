
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbol;

import DataManagement.Data;
import static java.awt.image.ImageObserver.HEIGHT;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author andre
 */
public class Arbol<T extends Comparable<T>> implements Iterable<T> {

    private Nodo root = null;
    private int num_nodos;
    private int alt;

    public Arbol() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public Nodo getRoot() {
        return root;
    }

    public int getNum_nodos() {
        return num_nodos;
    }

    public int getAlt() {
        return alt;
    }

    public void setRoot(Nodo root) {
        this.root = root;
    }

    public void setNum_nodos(int num_nodos) {
        this.num_nodos = num_nodos;
    }

    public void setAlt(int alt) {
        this.alt = alt;
    }

    public Icon deleted = new ImageIcon(getClass().getResource("/img\\deleted.png"));
    public Icon error = new ImageIcon(getClass().getResource("/img\\not_found.png"));

    //Inorden Recursivo
    public void inordenRE(Nodo node, ArrayList<String> in) {
        if (node != null) {
            inordenRE(node.getLeft(), in);
            String aux = node.getElement().toString();
            in.add(aux);
            inordenRE(node.getRight(), in);
        }
    }

    //Postorden Recursivo
    public void postordenRE(Nodo node, ArrayList<String> post) {
        if (node != null) {
            postordenRE(node.getLeft(), post);
            postordenRE(node.getRight(), post);
            String aux = node.getElement().toString();
            post.add(aux);
        }
    }

    // Preorden Recursivo
    public void preordenRE(Nodo node, ArrayList<String> pre) {
        if (node != null) {
            String aux = node.getElement().toString();
            pre.add(aux);
            preordenRE(node.getLeft(), pre);
            preordenRE(node.getRight(), pre);
        }
    }

    //Recorrido por nieveles 
    public void recorridoPorNiveles(Nodo raiz, ArrayList<String> niv) {
        if (raiz == null) {
            return;
        }

        Queue<Nodo> cola = new LinkedList<>();
        cola.add(raiz);

        while (!cola.isEmpty()) {
            Nodo actual = cola.poll();
            System.out.print(actual.getElement() + " ");
            niv.add(actual.getElement().toString());

            if (actual.getLeft() != null) {
                cola.add(actual.getLeft());
            }

            if (actual.getRight() != null) {
                cola.add(actual.getRight());
            }
        }
    }

    //Método para hallar la altura de un nodo
    private int altura(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }

        Queue<Nodo> queue = new LinkedList<>();
        queue.offer(nodo);
        int altura = 0;

        while (!queue.isEmpty()) {
            int nivelSize = queue.size();
            altura++;

            for (int i = 0; i < nivelSize; i++) {
                Nodo actual = queue.poll();

                if (actual.getLeft() != null) {
                    queue.offer(actual.getLeft());
                }

                if (actual.getRight() != null) {
                    queue.offer(actual.getRight());
                }
            }
        }

        return altura;
    }

    //Retorna el Factor de equilibrio del Nodo
    public int obtenerFE(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }

        int alturaIzquierda = altura(nodo.getLeft());
        int alturaDerecha = altura(nodo.getRight());

        return alturaDerecha - alturaIzquierda;
    }

    //Rotacion Simple Derecha
    public Nodo rotacionDerecha(Nodo c) {
        Nodo aux = c.getLeft();
        c.setLeft(aux.getRight());
        aux.setRight(c);
        c.setFe(obtenerFE(c));
        aux.setFe(obtenerFE(aux));
        return aux;
    }

    //Rotacion Simple Izquierda
    public Nodo rotacionIzquierda(Nodo c) {
        Nodo aux = c.getRight();
        c.setRight(aux.getLeft());
        aux.setLeft(c);
        c.setFe(obtenerFE(c));
        aux.setFe(obtenerFE(aux));
        return aux;
    }

    //Rotacion doble izquierda-derecha
    public Nodo rotacionDobleIzquierda(Nodo c) {
        Nodo temp;
        c.setLeft(rotacionIzquierda(c.getLeft()));
        temp = rotacionDerecha(c);
        return temp;
    }

    //Rotacion doble derecha-izquierda
    public Nodo rotacionDobleDerecha(Nodo c) {
        Nodo temp;
        c.setRight(rotacionDerecha(c.getRight()));
        temp = rotacionIzquierda(c);
        return temp;
    }

    // Método para insertar
    public void insertar(T elemento) {
        Nodo nuevo = new Nodo(elemento);

        if (root == null) {
            root = nuevo;
            return;
        }

        Stack<Nodo> stack = new Stack<>();

        Nodo actual = root;
        Nodo padre = null;
        boolean ladoIzquierdo = false;

        while (actual != null) {
            stack.push(actual);
            padre = actual;

            if (nuevo.getElement().compareTo(actual.getElement()) < 0) {
                actual = actual.getLeft();
                ladoIzquierdo = true;
            } else if (nuevo.getElement().compareTo(actual.getElement()) > 0) {
                actual = actual.getRight();
                ladoIzquierdo = false;
            } else {
                JOptionPane.showMessageDialog(null, "Nodo duplicado", "Error", HEIGHT, error);
                return; //No se permiten duplicados
            }
        }

        if (ladoIzquierdo) {
            padre.setLeft(nuevo);
        } else {
            padre.setRight(nuevo);
        }

        while (!stack.isEmpty()) {
            actual = stack.pop();
            balancear(actual);
        }

    }

    // Función para balancear un nodo desbalanceado
    private void balancear(Nodo nodo) {
        int fe = obtenerFE(nodo);

        if (fe > 1) {
            //Subárbol izquierdo está desbalanceado
            if (obtenerFE(nodo.getRight()) < 0) {
                //Necesitamos una rotación Derecha-Izquierda
                nodo.setRight(rotacionDerecha(nodo.getRight()));
            }
            //Rotación simple izquierda
            nodo = rotacionIzquierda(nodo);

        } else if (fe < -1) {
            //Subárbol derecho está desbalanceado
            if (obtenerFE(nodo.getLeft()) > 0) {
                nodo.setLeft(rotacionIzquierda(nodo.getLeft()));
            }
            //Rotación simple derecha
            nodo = rotacionDerecha(nodo);
        }

        //Actualizar la raíz si es necesario
        if (nodo.getParent() == null) {
            root = nodo;
        }

    }

//    //Metodo para insertar al AVL
//    public Nodo insertarAVL(Nodo nuevo, Nodo subAr) {
//        Nodo nuevoPadre = subAr;
//        if (nuevo.getElement().compareTo(subAr.getElement()) < 0) {
//            if (subAr.getLeft() == null) {
//                subAr.setLeft(nuevo);
//            } else {
//                subAr.setLeft(insertarAVL(nuevo, subAr.getLeft()));
//                if ((obtenerFE(subAr.getLeft()) - obtenerFE(subAr.getRight()) == 2)) {
//                    if (nuevo.getElement().compareTo(subAr.getLeft().getElement()) < 0) {
//                        nuevoPadre = rotacionIzquierda(subAr);
//                    } else {
//                        nuevoPadre = rotacionDobleIzquierda(subAr);
//                    }
//                }
//            }
//
//        } else if (nuevo.getElement().compareTo(subAr.getElement()) > 0) {
//            if (subAr.getRight() == null) {
//                subAr.setRight(nuevo);
//            } else {
//                subAr.setRight(insertarAVL(nuevo, subAr.getRight()));
//                if ((obtenerFE(subAr.getRight()) - obtenerFE(subAr.getLeft()) == 2)) {
//                    if (nuevo.getElement().compareTo(subAr.getRight().getElement()) > 0) {
//                        nuevoPadre = rotacionDerecha(subAr);
//                    } else {
//                        nuevoPadre = rotacionDobleDerecha(root);
//                    }
//
//                }
//            }
//        } else {
//            System.out.println("Nodo duplicado");
//            JOptionPane.showMessageDialog(null, "Nodo duplicado", "Error", HEIGHT, error);
//        }
//        //Actualizamos la altura
//        if ((subAr.getLeft() == null) && (subAr.getRight() != null)) {
//            subAr.setFe((subAr.getRight().getFe() + 1));
//        } else if ((subAr.getRight() == null) && (subAr.getLeft() != null)) {
//            subAr.setFe((subAr.getLeft().getFe() + 1));
//        } else {
//            subAr.setFe((Math.max(obtenerFE(subAr.getLeft()), obtenerFE(subAr.getRight())) + 1));
//        }
//        return nuevoPadre;
//    }
//
//    //Método para insertar
//    public void insertar(T elemento) {
//        Nodo nuevo = new Nodo(elemento);
//        if (root == null) {
//            root = nuevo;
//        } else {
//            root = insertarAVL(nuevo, root);
//        }
//    }
    
    
    //Halla el predecesor
    public Nodo pred(Nodo r) {
        Nodo p = r.getLeft();
        p.setParent(r);

        while (p.getRight() != null) {
            p.setParent(r);
            p = p.getRight();
        }
        return p;
    }

    //Elimina el nodo y balancea el árbol 
    public Nodo eliminarAVL(T elemento, Nodo subAr) {
        if (subAr == null) {
            return subAr;
        }
        Nodo aux = new Nodo(elemento);

        // Realizar la eliminación similar a un ABB
        if (aux.getElement().compareTo(subAr.getElement()) < 0) {
            subAr.setLeft(eliminarAVL(elemento, subAr.getLeft()));
        } else if (aux.getElement().compareTo(subAr.getElement()) > 0) {
            subAr.setRight(eliminarAVL(elemento, subAr.getRight()));
        } else { // Elemento encontrado, realizar la eliminación
            // Caso 1: Nodo a eliminar tiene 0 o 1 hijo
            if (subAr.getLeft() == null || subAr.getRight() == null) {
                Nodo temp = null;
                if (temp == subAr.getLeft()) {
                    temp = subAr.getRight();
                } else {
                    temp = subAr.getLeft();
                }

                // Si no hay hijos
                if (temp == null) {
                    temp = subAr;
                    subAr = null;
                } else { // Caso 1 hijo
                    subAr = temp;
                }
            } else { // Caso 2: Nodo a eliminar tiene 2 hijos
                // Encontrar el sucesor inmediato en el subárbol derecho
                Nodo temp = encontrarMinimo(subAr.getRight());

                // Copiar el contenido del sucesor inmediato al nodo actual
                subAr.setElement(temp.getElement());

                // Eliminar el sucesor inmediato
                subAr.setRight(eliminarAVL((T) temp.getElement(), subAr.getRight()));
            }
        }

        // Si el árbol quedó vacío después de la eliminación
        if (subAr == null) {
            return subAr;
        }

        // Actualizar la altura (factor de equilibrio) del nodo actual
        subAr.setFe(Math.max(obtenerFE(subAr.getLeft()), obtenerFE(subAr.getRight())) + 1);

        // Rebalancear el árbol
        int balance = obtenerFE(subAr);
        if (balance > 1 && obtenerFE(subAr.getLeft()) >= 0) {
            return rotacionDerecha(subAr);
        }
        if (balance > 1 && obtenerFE(subAr.getLeft()) < 0) {
            subAr.setLeft(rotacionIzquierda(subAr.getLeft()));
            return rotacionDerecha(subAr);
        }
        if (balance < -1 && obtenerFE(subAr.getRight()) <= 0) {
            return rotacionIzquierda(subAr);
        }
        if (balance < -1 && obtenerFE(subAr.getRight()) > 0) {
            subAr.setRight(rotacionDerecha(subAr.getRight()));
            return rotacionIzquierda(subAr);
        }

        return subAr;
    }

    // Método para eliminar un nodo del árbol AVL
    public void eliminar(T elemento) {
        if (root == null) {
            return;
        }
        root = eliminarAVL(elemento, root);
        System.out.println("Eliminado " + elemento);
        JOptionPane.showMessageDialog(null, "Nodo eliminado", "ELIMINAR NODO:", HEIGHT, deleted);

    }

    // Función para encontrar el nodo mínimo (más a la izquierda) en un subárbol
    private Nodo encontrarMinimo(Nodo nodo) {
        Nodo actual = nodo;
        while (actual.getLeft() != null) {
            actual = actual.getLeft();
        }
        return actual;
    }

    //Función para buscar nodo
    public Nodo buscar(Nodo r, String element) {
        if (this.root == null) {
            return null;
        } else if (r.getElement().toString().compareTo(element) == 0) {
            return r;
        } else if (r.getElement().toString().compareTo(element) < 0) {
            return buscar(r.getRight(), element);
        } else {
            return buscar(r.getLeft(), element);
        }
    }

    // Función para obtener el máximo de dos enteros
    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    //Función para obtener el nivel del nodo
    public int obtenerNivel(Nodo r, T elemento, int nivel) {
        Nodo aux = new Nodo(elemento);

        //Si no se encuentra el nodo
        if (r == null) {
            return -1;
        }

        if (r.getElement().compareTo(aux.getElement()) == 0) {
            return nivel; //Retorna el nivel actual si se encuentra el nodo
        }

        //Se obtienen los niveles de los nodos a la izquierda y derecha
        int nivelIzquierdo = obtenerNivel(r.getLeft(), elemento, nivel + 1);
        int nivelDerecho = obtenerNivel(r.getRight(), elemento, nivel + 1);

        //Retorna el nivel donde se encontró del nodo
        return Math.max(nivelIzquierdo, nivelDerecho);
    }

    //Función para obtener el padre del nodo
    public Nodo obtenerPadre(Nodo r, T elemento) {
        Nodo aux = new Nodo(elemento);
        if ((r == null) || (r.getElement().compareTo(aux.getElement()) == 0)) {
            return null; // si la raiz es nula o el elemento que buscamos
        }

        if ((r.getLeft() != null && r.getLeft().getElement().compareTo(aux.getElement()) == 0)
                || (r.getRight() != null && r.getRight().getElement().compareTo(aux.getElement()) == 0)) {
            return r; //si el padre del nodo es la raíz
        }

        //Busca el padre en los subárboles izquierdo y derecho
        Nodo padreIzquierdo = obtenerPadre(r.getLeft(), elemento);
        Nodo padreDerecho = obtenerPadre(r.getRight(), elemento);

        //Retorna el padre encontrado en alguno de los dos subárboles
        return (padreIzquierdo != null) ? padreIzquierdo : padreDerecho;
    }

    //Función para obtener el tio del nodo
    public Nodo obtenerTio(Nodo r, T elemento) {
        //Primero obtenemos el abuelo y el padre del nodo
        Nodo abuelo = obtenerAbuelo(r, elemento);
        Nodo padre = obtenerPadre(r, elemento);

        //Si no hay abuelo no hay tío
        if (abuelo == null) {
            return null;
        }

        //Si no hay padre/es la raíz no hay tío
        if (padre == null) {
            return null;
        }

        //Revisamos los hijos del abuelo para hallar al tío
        if (abuelo.getLeft() != null && abuelo.getRight() == padre) { // El tío es el hijo izquierdo
            return abuelo.getLeft();
        } else if (abuelo.getRight() != null && abuelo.getLeft() == padre) { //El tío es el hijo derecho
            return abuelo.getRight();
        } else if (abuelo.getRight() == null && abuelo.getLeft() == padre) { //El abuelo no tiene hijo derecho
            return null;
        } else if (abuelo.getLeft() == null && abuelo.getRight() == padre) { //El abuelo no tiene hijo izquierdo
            return null;
        } else {
            return null; //No hay tío
        }

    }

//Función para obtener al abuelo del nodo
    public Nodo obtenerAbuelo(Nodo r, T elemento) {
        //Encontramos el padre del nodo
        Nodo padre = obtenerPadre(r, elemento);

        //Si no se encuentra el padre o es la raíz entonces no hay abuelo
        if (padre == null || padre == r) {
            return null;
        }

        //Encontrar al abuelo (padre del padre)
        return obtenerPadre(r, (T) padre.getElement());
    }

    //Obtiene el código para dibujar el árbol
    public String obtenerCodigoGraphviz() {
        String texto = "digraph G\n"
                + "{\n"
                + "     node[shape = circle]\n"
                + "     node[style = filled]\n"
                + "     node[fillcolor = \"#FFB6C1\"]\n"
                + "     node[color = \"#FFB6C1\"]\n"
                + "     edge[color = \"#000000\"]\n";
        if (root != null) {
            texto += root.textoGraphviz();
        }
        texto += "\n}";
        return texto;
    }

    //  Escribe el archivo .dot
    public void escribirArchivo(String ruta, String contenido) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter(ruta);
            pw = new PrintWriter(fichero);
            pw.write(contenido);
            pw.close();
            fichero.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (pw != null) {
                pw.close();
            }
        }

    }

    //Método para dibujar el árbol
    public void dibujarGraphiz() {
        try {
            escribirArchivo("archivo.dot", obtenerCodigoGraphviz());

            //Convertir archivo.dot a imagen
            ProcessBuilder proceso = new ProcessBuilder("dot", "-Tpng", "-o", "src/img/arbol.png", "archivo.dot");
            proceso.redirectErrorStream(true);
            Process p = proceso.start();
            p.waitFor();
            if (p.exitValue() != 0) {
                System.err.println("Error al convertir el archivo dot a png.");
            } else {
                System.out.println("La conversión se ha completado exitosamente.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Esto es el iterador para recorrer el árbol con un For Each
    @Override
    public Iterator<T> iterator() {
        return new ArbolIterator(root);

    }

    private class ArbolIterator implements Iterator<T> {

        private Stack<Nodo<T>> stack = new Stack<>();

        public ArbolIterator(Nodo<T> node) {
            while (node != null) {
                stack.push(node);
                node = node.getLeft();
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public T next() {
            Nodo<T> node = stack.pop();
            T result = node.getElement();
            if (node.getRight() != null) {
                Nodo<T> temp = node.getRight();
                while (temp != null) {
                    stack.push(temp);
                    temp = temp.getLeft();
                }
            }
            return result;
        }
    }
}
