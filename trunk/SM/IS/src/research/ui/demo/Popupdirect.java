/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Popupdirect.java
 *
 * Created on Mar 26, 2012, 9:58:31 PM
 */
package research.ui.demo;

/**
 *
 * @author NUZAIR
 */
public class Popupdirect extends javax.swing.JFrame {

    /** Creates new form Popupdirect */
    public Popupdirect() {
        p1 = new javax.swing.JPanel();
        p2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        p1.setBackground(new java.awt.Color(255, 153, 204));
        p1.setLayout(null);
        getContentPane().add(p1,0);
        p1.setBounds(40, 20, 380, 120);


        p2.setBackground(new java.awt.Color(255, 153, 51));
        p2.setLayout(null);
//jTable1.set
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        p2.add(jScrollPane1);
        jScrollPane1.setBounds(30, 20, 380, 275);
        getContentPane().add(p2,1);
        p2.setBounds(140, 70, 440, 220);

        pack();
        
                this.getContentPane().setComponentZOrder(p1, 0);
                        this.getContentPane().setComponentZOrder(p2, 1);
                        this.repaint();

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        p1 = new javax.swing.JPanel();
        p2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        p1.setBackground(new java.awt.Color(255, 153, 204));
        p1.setLayout(null);
        getContentPane().add(p1);
        p1.setBounds(40, 20, 380, 120);

        p2.setBackground(new java.awt.Color(255, 153, 51));
        p2.setLayout(null);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        p2.add(jScrollPane1);
        jScrollPane1.setBounds(30, 20, 380, 275);

        getContentPane().add(p2);
        p2.setBounds(140, 50, 440, 220);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Popupdirect().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel p1;
    private javax.swing.JPanel p2;
    // End of variables declaration//GEN-END:variables
}
