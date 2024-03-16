
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

        arbol.insertar(3);
        arbol.insertar(8);
        arbol.insertar(1);
        arbol.insertar(5);
        arbol.insertar(15);
        arbol.insertar(17);
        arbol.insertar(18);
        arbol.insertar(21);
        arbol.insertar(25);
        arbol.insertar(26);
        arbol.insertar(27);
        arbol.insertar(28);
        arbol.insertar(29);
        arbol.eliminar(8);
        arbol.eliminar(3);
        arbol.eliminar(25);
        arbol.eliminar(5);
        
//        
//        for (var a: arbol) {
//            System.out.println(a);
//        }
//        
        

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
        arbol.recorridoPorNivelesRE(arbol.getRoot(), niv);

        arbol.dibujarGraphiz();
        
        
    }

}
