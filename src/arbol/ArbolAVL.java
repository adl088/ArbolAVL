
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package arbol;


import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
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
        ArrayList<String> niv = new ArrayList<String>();

        arbol.insertar(27);
        arbol.insertar(28);
        arbol.insertar(29);
        arbol.insertar(31);
        arbol.insertar(25);
        arbol.insertar(32);
        arbol.insertar(33);
        arbol.insertar(43);
        arbol.insertar(44);
        arbol.insertar(45);
        

        System.out.println("Preorden:");
        arbol.preordenRE(arbol.getRoot(), pre);
        System.out.println(pre);

        System.out.println("Inorden:");
        arbol.inordenRE(arbol.getRoot(), in);
        System.out.println(in);

        System.out.println("Postorden:");
        arbol.postordenRE(arbol.getRoot(), post);
        System.out.println(post);
        
        System.out.println("Recorrido por niveles: ");
        arbol.recorridoPorNiveles(arbol.getRoot(), niv);

        arbol.dibujarGraphiz();
        
        
    }

}
