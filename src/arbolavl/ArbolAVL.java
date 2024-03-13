/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package arbolavl;

import java.util.ArrayList;

/**
 *
 * @author andre
 */
public class ArbolAVL {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Arbol arbol = new Arbol();
        ArrayList<String> pre = new ArrayList<String>();
        ArrayList<String> in = new ArrayList<String>();
        ArrayList<String> post = new ArrayList<String>();
        String direccionDot = "arbol.dot";
        String direccionPng = "arbol.png";
        String dotPath= "\"C:\\Program Files\\Graphviz\\bin\\dot.exe\"";

        arbol.insertar(8);
        arbol.insertar(13);
        arbol.insertar(14);
        arbol.insertar(15);
        arbol.insertar(16);
        arbol.insertar(5);
        arbol.insertar(4);
        arbol.insertar(3);
        arbol.insertar(1);
        arbol.insertar(0);
        
        
        
        System.out.println("Preorden:");
        arbol.preordenRE(arbol.getRoot(), pre);
        System.out.println(pre);
        
        System.out.println("Inorden:");
        arbol.inordenRE(arbol.getRoot(), in);
        System.out.println(in);
        
        System.out.println("Postorden:");
        arbol.postordenRE(arbol.getRoot(), post);
        System.out.println(post);

        arbol.dibujarGraphiz();

    }

}
