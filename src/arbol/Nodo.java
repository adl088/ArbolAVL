package arbol;
/**s
 *
 * @author andre
 */

public class Nodo<T extends Comparable<T>> {

    @Override
    public String toString() {
        return element.toString();
    }

    private T element;
    
    private int fe;
    private Nodo parent;
    private Nodo left;
    private Nodo right;
    private int alt;
    private String category;
    private int size;

    public int getAlt() {
        return alt;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    

    public void setAlt(int alt) {
        this.alt = alt;
    }
    

    public Nodo(T element) {
        this.element = element;
        this.fe = 0;
        this.left = null;
        this.right = null;
        alt = 1;
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

    public String textoGraphviz() {
        if (this.getLeft() == null && this.getRight() == null) {
            return String.valueOf(this.getElement());
        } else {
            String texto = "";
            if (this.getLeft() != null) {
                texto = this.getElement() + "->" + this.getLeft().textoGraphviz() + "\n";
            }
            if (this.getRight() != null) {
                texto += this.getElement() + "->" + this.getRight().textoGraphviz() + "\n";
            }
            return texto;
        }
    }
}
