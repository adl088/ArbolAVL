/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaz;

import DataManagement.Data;
import arbol.Arbol;
import arbol.Nodo;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.UIManager;
import com.formdev.flatlaf.FlatLightLaf;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author andre
 */
public class Vista extends javax.swing.JFrame {

    private Path last; // este es el ultimo archivo selecionado por el usuario
    private Arbol arbolAVL = new Arbol();

    ArrayList<String> pre = new ArrayList<String>();
    ArrayList<String> in = new ArrayList<String>();
    ArrayList<String> post = new ArrayList<String>();
    ArrayList<String> niv = new ArrayList<String>();

    /**
     * Creates new form Vista
     */
    public Vista() {
        initComponents();
        this.setLocationRelativeTo(null);
        setImage(arbol, "src/img/arbol.png");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        categorias = new javax.swing.ButtonGroup();
        fondoPanel = new javax.swing.JPanel();
        busquedaAD = new javax.swing.JButton();
        recorridoNiveles = new javax.swing.JButton();
        buscarB = new javax.swing.JButton();
        postOrden = new javax.swing.JButton();
        preOrden = new javax.swing.JButton();
        inOrden = new javax.swing.JButton();
        insertarB = new javax.swing.JButton();
        EliminarB = new javax.swing.JButton();
        arbol = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        recorridos = new javax.swing.JTextArea();
        deco = new javax.swing.JLabel();
        backVista = new javax.swing.JLabel();
        backVista1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(2147483647, 590));
        setResizable(false);

        fondoPanel.setMaximumSize(new java.awt.Dimension(2147483647, 590));
        fondoPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        busquedaAD.setText("B�squeda Avanzada");
        busquedaAD.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));
        busquedaAD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                busquedaADActionPerformed(evt);
            }
        });
        fondoPanel.add(busquedaAD, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 170, 180, 50));

        recorridoNiveles.setText("Recorrido Por Niveles");
        recorridoNiveles.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));
        recorridoNiveles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recorridoNivelesActionPerformed(evt);
            }
        });
        fondoPanel.add(recorridoNiveles, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 300, 140, 50));

        buscarB.setText("Buscar Nodo");
        buscarB.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));
        buscarB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarBActionPerformed(evt);
            }
        });
        fondoPanel.add(buscarB, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 170, 170, 50));

        postOrden.setText("PostOrden");
        postOrden.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));
        postOrden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                postOrdenActionPerformed(evt);
            }
        });
        fondoPanel.add(postOrden, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 390, 140, 50));

        preOrden.setText("PreOrden");
        preOrden.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));
        preOrden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                preOrdenActionPerformed(evt);
            }
        });
        fondoPanel.add(preOrden, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 390, 140, 50));

        inOrden.setText("InOrden");
        inOrden.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));
        inOrden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inOrdenActionPerformed(evt);
            }
        });
        fondoPanel.add(inOrden, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 390, 140, 50));

        insertarB.setText("Insertar Nodo");
        insertarB.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));
        insertarB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertarBActionPerformed(evt);
            }
        });
        fondoPanel.add(insertarB, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 90, 170, 50));

        EliminarB.setText("Eliminar Nodo");
        EliminarB.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));
        EliminarB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarBActionPerformed(evt);
            }
        });
        fondoPanel.add(EliminarB, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 90, 180, 50));

        arbol.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        arbol.setText("arbol");
        arbol.setPreferredSize(new java.awt.Dimension(550, 350));
        fondoPanel.add(arbol, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 440, 370));

        recorridos.setColumns(20);
        recorridos.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        recorridos.setRows(5);
        recorridos.setText("\n");
        recorridos.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0)));
        jScrollPane2.setViewportView(recorridos);

        fondoPanel.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 450, 450, 100));

        deco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/deco.png"))); // NOI18N
        fondoPanel.add(deco, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -5, -1, -1));

        backVista.setBackground(new java.awt.Color(255, 255, 255));
        backVista.setForeground(new java.awt.Color(255, 255, 255));
        backVista.setText("jLabel4");
        backVista.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "VISTA PREVIA", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 24))); // NOI18N
        backVista.setOpaque(true);
        fondoPanel.add(backVista, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 490, 460));

        backVista1.setBackground(new java.awt.Color(255, 255, 255));
        backVista1.setForeground(new java.awt.Color(255, 255, 255));
        backVista1.setText("jLabel4");
        backVista1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        backVista1.setOpaque(true);
        fondoPanel.add(backVista1, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 85, 500, 470));

        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        fondoPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 10, 1020, 570));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/p.png"))); // NOI18N
        fondoPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/backGROUND (1).png"))); // NOI18N
        fondo.setAlignmentY(0.0F);
        fondoPanel.add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 590));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fondoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1041, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(fondoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void insertarBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertarBActionPerformed
        arbolAVL.dibujarGraphiz();
        // Creaci�n del JFileChooser
        javax.swing.JFileChooser fileChooser = new javax.swing.JFileChooser();

        // Establece el directorio inicial en el directorio del proyecto
        String currentDirectory = System.getProperty("user.dir");
        fileChooser.setCurrentDirectory(new File(currentDirectory));

        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                // Obtiene el archivo seleccionado
                last = fileChooser.getSelectedFile().toPath();
                addToArbol();
                updateArbol();
            } catch (IOException ex) {
                System.out.println("Hubo error al a�adir el arbol");
                Logger.getLogger(Vista.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_insertarBActionPerformed

    private void EliminarBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarBActionPerformed

        String name = (String) JOptionPane.showInputDialog(null, "Ingrese nodo a eliminar", "ELIMINAR NODO", JOptionPane.INFORMATION_MESSAGE, null, null, "");
        
        for (var a : arbolAVL) {
            Data aa = (Data) a;
            if (aa.toString().equals(name)) {
                arbolAVL.eliminar(aa);
                break;
            }
        }
        updateArbol();
    }//GEN-LAST:event_EliminarBActionPerformed

    private void inOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inOrdenActionPerformed
        recorridos.setText("");
        in.clear();
        arbolAVL.inordenRE(arbolAVL.getRoot(), in);
        recorridos.append("InOrden: ");
        recorridos.append("\n");
        recorridos.append("\n");
        recorridos.append(in.toString());
    }//GEN-LAST:event_inOrdenActionPerformed

    private void preOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_preOrdenActionPerformed
        recorridos.setText("");
        pre.clear();
        arbolAVL.preordenRE(arbolAVL.getRoot(), pre);
        recorridos.append("PreOrden: ");
        recorridos.append("\n");
        recorridos.append("\n");
        recorridos.append(pre.toString());
    }//GEN-LAST:event_preOrdenActionPerformed

    private void postOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_postOrdenActionPerformed
        recorridos.setText("");
        post.clear();
        arbolAVL.postordenRE(arbolAVL.getRoot(), post);
        recorridos.append("PostOrden: ");
        recorridos.append("\n");
        recorridos.append("\n");
        recorridos.append(post.toString());
    }//GEN-LAST:event_postOrdenActionPerformed

    private void buscarBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarBActionPerformed

        String name = (String) JOptionPane.showInputDialog(null, "Ingrese nodo a buscar", "BUSCAR NODO", JOptionPane.INFORMATION_MESSAGE, null, null, "");

        if (arbolAVL.buscar(arbolAVL.getRoot(), name) != null) {
            JOptionPane.showMessageDialog(null, "Nodo encontrado: " + name
                    + "\nFactor de equilibrio: "
                    + arbolAVL.obtenerFE(arbolAVL.buscar(arbolAVL.getRoot(), name))
                    + "\nNivel: " + arbolAVL.obtenerNivel(arbolAVL.getRoot(), arbolAVL.buscar(arbolAVL.getRoot(), name).getElement(), 0)
                    + "\nPadre: " + arbolAVL.obtenerPadre(arbolAVL.getRoot(), arbolAVL.buscar(arbolAVL.getRoot(), name).getElement())
                    + "\nT�o: " + arbolAVL.obtenerTio(arbolAVL.getRoot(), arbolAVL.buscar(arbolAVL.getRoot(), name).getElement())
                    + "\nAbuelo: " + arbolAVL.obtenerAbuelo(arbolAVL.getRoot(), arbolAVL.buscar(arbolAVL.getRoot(), name).getElement()));
        } else {
            JOptionPane.showMessageDialog(null, "Nodo no encontrado");
            System.out.println(arbolAVL.buscar(arbolAVL.getRoot(), name));
        }
    }//GEN-LAST:event_buscarBActionPerformed

    private void recorridoNivelesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recorridoNivelesActionPerformed
        recorridos.setText("");
        niv.clear();
        arbolAVL.recorridoPorNivelesRE(arbolAVL.getRoot(), niv);
        recorridos.append("Recorrido por Niveles: ");
        recorridos.append("\n");
        recorridos.append("\n");
        recorridos.append(niv.toString());
    }//GEN-LAST:event_recorridoNivelesActionPerformed

    private void busquedaADActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_busquedaADActionPerformed
        String type = JOptionPane.showInputDialog(null, "Ingrese una categor�a: ");

        String rango = JOptionPane.showInputDialog(null, "Ingrese un peso m�ximo: ");

    }//GEN-LAST:event_busquedaADActionPerformed

    //Actualiza la imagen del �rbol
    private void updateArbol() {
        arbolAVL.dibujarGraphiz();
        setImage(arbol, "src/img/arbol.png");
        arbol.revalidate();
        arbol.repaint();
    }

    //A�ade el nuevo nodo al �rbol
    private void addToArbol() throws IOException {
        Data add = new Data(last);
        arbolAVL.insertar(add);
    }

    //Muestra la imagen del �rbol
    private void setImage(JLabel name, String root) {
        name.setText("");
        Image img = new ImageIcon(root).getImage();
        ImageIcon img2 = new ImageIcon(img.getScaledInstance(200, 300, Image.SCALE_SMOOTH));
        name.setIcon(img2);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            System.err.println("Failed to initialize LaF");
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Vista().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton EliminarB;
    private javax.swing.JLabel arbol;
    private javax.swing.JLabel backVista;
    private javax.swing.JLabel backVista1;
    private javax.swing.JButton buscarB;
    private javax.swing.JButton busquedaAD;
    private javax.swing.ButtonGroup categorias;
    private javax.swing.JLabel deco;
    private javax.swing.JLabel fondo;
    private javax.swing.JPanel fondoPanel;
    private javax.swing.JButton inOrden;
    private javax.swing.JButton insertarB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton postOrden;
    private javax.swing.JButton preOrden;
    private javax.swing.JButton recorridoNiveles;
    private javax.swing.JTextArea recorridos;
    // End of variables declaration//GEN-END:variables
}
