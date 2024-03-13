package arbolavl;

/**
 *
 * @author andre
 */
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.*;
import java.io.PrintWriter;
import java.util.Stack;
import javax.swing.JPanel;
import java.util.ArrayList;

public class Arbol<T extends Comparable<T>> {

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

    //Inorden Recursivo
    public void inordenRE(Nodo node, ArrayList<String> in) {
        if (node != null) {
            inordenRE(node.getLeft(), in);
//            System.out.println(node.getElement());
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
//            System.out.println(node.getElement());
            String aux = node.getElement().toString();
            post.add(aux);
        }
    }

    // Preorden Recursivo
    public void preordenRE(Nodo node, ArrayList<String> pre) {
        if (node != null) {
//            System.out.println(node.getElement());
            String aux = node.getElement().toString();
            pre.add(aux);
            preordenRE(node.getLeft(), pre);
            preordenRE(node.getRight(), pre);
        }
    }

    //Método para hallar altura desde un nodo
    private void altura(Nodo aux, int nivel) {
        if (aux != null) {
            altura(aux.getLeft(), nivel + 1);
            alt = nivel;
            altura(aux.getRight(), nivel + 1);
        }
    }

    //Retorna altura del árbol
    public int getAltura() {
        altura(root, 1);
        return alt;
    }

    public int getFE(Nodo x) {
        if (x == null) {
            return -1;
        } else {
            return x.getFe();
        }
    }

    //Rotacion Simple Izquierda
    public Nodo rotacionIzquierda(Nodo c) {
        Nodo aux = c.getLeft();
        c.setLeft(aux.getRight());
        aux.setRight(c);
        c.setFe(Math.max(getFE(c.getLeft()), getFE(c.getRight())));
        aux.setFe(Math.max(getFE(aux.getLeft()), getFE(aux.getRight())));
        return aux;
    }

    //Rotacion Simple Derecha
    public Nodo rotacionDerecha(Nodo c) {
        Nodo aux = c.getRight();
        c.setRight(aux.getLeft());
        aux.setLeft(c);
        c.setFe(Math.max(getFE(c.getLeft()), getFE(c.getRight())));
        aux.setFe(Math.max(getFE(aux.getLeft()), getFE(aux.getRight())));
        return aux;
    }

    //Rotacion doble izquierda derecha
    public Nodo rotacionDobleIzquierda(Nodo c) {
        Nodo temp;
        c.setLeft(rotacionDerecha(c.getLeft()));
        temp = rotacionIzquierda(c);
        return temp;
    }

    //Rotacion doble derecha izquierda
    public Nodo rotacionDobleDerecha(Nodo c) {
        Nodo temp;
        c.setRight(rotacionIzquierda(c.getRight()));
        temp = rotacionDerecha(c);
        return temp;
    }

    //Metodo para insertar AVL
    public Nodo insertarAVL(Nodo nuevo, Nodo subAr) {
        Nodo nuevoPadre = subAr;
        if (nuevo.getElement().compareTo(subAr.getElement()) < 0) {
            if (subAr.getLeft() == null) {
                subAr.setLeft(nuevo);
            } else {
                subAr.setLeft(insertarAVL(nuevo, subAr.getLeft()));
                if (getFE(subAr.getLeft()) - getFE(subAr.getRight()) == 2) {
                    if (nuevo.getElement().compareTo(subAr.getLeft().getElement()) < 0) {
                        nuevoPadre = rotacionIzquierda(subAr);
                    } else {
                        nuevoPadre = rotacionDobleIzquierda(subAr);
                    }
                }
            }

        } else if (nuevo.getElement().compareTo(subAr.getElement()) > 0) {
            if (subAr.getRight() == null) {
                subAr.setRight(nuevo);
            } else {
                subAr.setRight(insertarAVL(nuevo, subAr.getRight()));
                if (getFE(subAr.getRight()) - getFE(subAr.getLeft()) == 2) {
                    if (nuevo.getElement().compareTo(subAr.getRight().getElement()) > 0) {
                        nuevoPadre = rotacionDerecha(subAr);
                    } else {
                        nuevoPadre = rotacionDobleDerecha(subAr);
                    }

                }
            }
        } else {
            System.out.println("Nodo duplicado");
        }
        //Actualizamos la altura
        if (subAr.getLeft() == null && subAr.getRight() != null) {
            subAr.setFe(subAr.getRight().getFe() + 1);
        } else if (subAr.getRight() == null && subAr.getLeft() != null) {
            subAr.setFe(subAr.getLeft().getFe() + 1);
        } else {
            subAr.setFe(Math.max(getFE(subAr.getLeft()), getFE(subAr.getRight())) + 1);
        }
        return nuevoPadre;
    }

    //Método para insertar
    public void insertar(T elemento) {
        Nodo nuevo = new Nodo(elemento);
        if (root == null) {
            root = nuevo;
        } else {
            root = insertarAVL(nuevo, root);
        }
        
    }

    public String obtenerCodigoGraphviz() {
        String texto = "digraph G\n"
                + "{\n"
                + "      node[shape = circle]\n"
                + "      node[style = filled]\n"
                + "      node[fillcolor = \"# FFB6C1\"]\n"
                + "      node[color = \"# FFB6C1\"]\n"
                + "      edge[color = \"#31CEF0\"]\n";
        if (root != null) {
            texto += root.textoGraphviz();
        }
        texto += "\n}";
        return texto;
    }

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
            escribirArchivo("arbol.dot", obtenerCodigoGraphviz());
           
            ProcessBuilder proceso= new ProcessBuilder("dot", "-Tpng", "-o", "arbol.png", "arbol.dot");
            //Convertir archivo.dot a imagen
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
}
