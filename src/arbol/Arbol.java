
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbol;

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

    //Retorna el Factor de equilibrio del Nodo
    public int obtenerFE(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        return altura(nodo.getRight()) - altura(nodo.getLeft());
    }

    private Nodo<T> rotacionDerecha(Nodo<T> y) {
        if (y == null || y.getLeft() == null) {
            return y;
        }
        Nodo<T> x = y.getLeft();
        Nodo<T> T2 = x.getRight();

        // Realiza la rotación
        x.setRight(y);
        y.setLeft(T2);

        // Actualiza padres
        if (T2 != null) {
            T2.setParent(y);
        }
        x.setParent(y.getParent());
        y.setParent(x);

        // Si y era la raíz, actualiza la raíz del árbol
        if (x.getParent() == null) {
            root = x;
        } else if (x.getParent().getRight() == y) {
            x.getParent().setRight(x);
        } else {
            x.getParent().setLeft(x);
        }

        // Actualiza alturas
        actualizarAltura(y);
        actualizarAltura(x);

        return x; // Nuevo padre
    }

    //Rotacion simple izquierda
    private Nodo<T> rotacionIzquierda(Nodo<T> x) {
        if (x == null || x.getRight() == null) {
            return x;
        }
        Nodo<T> y = x.getRight();
        Nodo<T> T2 = y.getLeft();

        // Realiza la rotación
        y.setLeft(x);
        x.setRight(T2);

        // Actualiza padres
        if (T2 != null) {
            T2.setParent(x);
        }
        y.setParent(x.getParent());
        x.setParent(y);

        // Si x era la raíz, actualiza la raíz del árbol
        if (y.getParent() == null) {
            root = y;
        } else if (y.getParent().getRight() == x) {
            y.getParent().setRight(y);
        } else {
            y.getParent().setLeft(y);
        }

        // Actualiza alturas
        actualizarAltura(x);
        actualizarAltura(y);

        return y; // Nuevo padre
    }

// Rotación Doble Izquierda-Derecha
    private Nodo<T> rotacionDobleIzquierda(Nodo<T> x) {
        x.setLeft(rotacionIzquierda(x.getLeft()));
        return rotacionDerecha(x);
    }

// Rotación Doble Derecha-Izquierda
    private Nodo<T> rotacionDobleDerecha(Nodo<T> y) {
        y.setRight(rotacionDerecha(y.getRight()));
        return rotacionIzquierda(y);
    }

    private int altura(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        return nodo.getAlt();
    }

    private void actualizarAltura(Nodo<T> n) {
        n.setAlt(1 + Math.max(altura(n.getLeft()), altura(n.getRight())));
    }

    public void insertar(T elemento) {
        if (root == null) {
            root = new Nodo<>(elemento);
            return;
        }

        Stack<Nodo<T>> stack = new Stack<>();
        Nodo<T> actual = root;
        Nodo<T> padre = null;

        // Buscar la posición para el nuevo nodo y mantener el camino
        while (actual != null) {
            stack.push(actual);
            padre = actual;
            if (elemento.compareTo(actual.getElement()) < 0) {
                actual = actual.getLeft();
            } else if (elemento.compareTo(actual.getElement()) > 0) {
                actual = actual.getRight();
            } else {
                // Elemento ya existe, retornar sin insertar
                return;
            }
        }

        // Insertar el nuevo nodo
        Nodo<T> nuevoNodo = new Nodo<>(elemento);
        if (elemento.compareTo(padre.getElement()) < 0) {
            padre.setLeft(nuevoNodo);
        } else {
            padre.setRight(nuevoNodo);
        }
        nuevoNodo.setParent(padre);

        // Balancear el árbol desde el nodo insertado hacia arriba
        while (!stack.isEmpty()) {
            Nodo<T> nodo = stack.pop();
            actualizarAltura(nodo);
            balancear(nodo);
        }
    }

    // Función para balancear un nodo desbalanceado
    private void balancear(Nodo<T> nodo) {
        if (nodo == null) {
            return;
        }

        actualizarAltura(nodo);
        int balance = obtenerFE(nodo);

        // Caso LL
        if (balance > 1 && obtenerFE(nodo.getRight()) > 0) {
            rotacionIzquierda(nodo);
        } // Caso RR
        else if (balance < -1 && obtenerFE(nodo.getLeft()) < 0) {
            rotacionDerecha(nodo);
        } // Caso LR
        else if (balance < -1 && obtenerFE(nodo.getLeft()) > 0) {
            nodo.setLeft(rotacionIzquierda(nodo.getLeft()));
            rotacionDerecha(nodo);
        } // Caso RL
        else if (balance > 1 && obtenerFE(nodo.getRight()) < 0) {
            nodo.setRight(rotacionDerecha(nodo.getRight()));
            rotacionIzquierda(nodo);
        }
    }

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

        actualizarAltura(subAr);
        int balance = obtenerFE(subAr);

        // Caso LL
        if (balance > 1 && obtenerFE(subAr.getRight()) > 0) {
            rotacionIzquierda(subAr);
        } // Caso RR
        else if (balance < -1 && obtenerFE(subAr.getLeft()) < 0) {
            rotacionDerecha(subAr);
        } // Caso LR
        else if (balance < -1 && obtenerFE(subAr.getLeft()) > 0) {
            subAr.setLeft(rotacionIzquierda(subAr.getLeft()));
            rotacionDerecha(subAr);
        } // Caso RL
        else if (balance > 1 && obtenerFE(subAr.getRight()) < 0) {
            subAr.setRight(rotacionDerecha(subAr.getRight()));
            rotacionIzquierda(subAr);
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
