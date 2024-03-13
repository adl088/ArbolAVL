/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbolavl;

/**
 *
 * @author andre
 */
public class Nodo<T extends Comparable<T>> {

    private T element;
    private int fe;
    private Nodo parent;
    private Nodo left;
    private Nodo right;

    public Nodo(T element) {
        this.element = element;
        this.fe = 0;
        this.left = null;
        this.right = null;
    }

    public void setFe(int fe) {
        this.fe = fe;
    }

    public int getFe() {
        return fe;
    }

    public Nodo(T element, Nodo parent) {
        this.element = element;
        this.parent = parent;
    }

    public T getElement() {
        return element;
    }

    public Nodo getParent() {
        return parent;
    }

    public Nodo getLeft() {
        return left;
    }

    public Nodo getRight() {
        return right;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public void setParent(Nodo parent) {
        this.parent = parent;
    }

    public void setLeft(Nodo left) {
        this.left = left;
    }

    public void setRight(Nodo right) {
        this.right = right;
    }

    public Nodo(T element, Nodo parent, Nodo left, Nodo right) {
        this.element = element;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }

    public void insertChild(Nodo child) {
        if (child.getElement().compareTo(element) < 0) {
            if (left != null) {
                left.insertChild(child);
            } else {
                left = child;
            }
        }else{
            if(right != null){
                right.insertChild(child);
            }else{
                right = child;
            }
        }
    }

    public String textoGraphviz() {
        if (left == null && right == null) {
            return String.valueOf(element);
        } else {
            String texto = "";
            if (left != null) {
                texto = element + "->" + left.textoGraphviz() + "\n";
            }
            if (right != null) {
                texto = element + "->" + right.textoGraphviz() + "\n";
            }
            return texto;          
        }
    }

}
