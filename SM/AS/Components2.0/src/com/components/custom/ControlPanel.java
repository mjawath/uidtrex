

/*
 * ControlPanel.java
 *
 * Created on Apr 2, 2012, 7:41:26 PM
 */
package com.components.custom;

/**
 *
 * @author NUZAIR
 */
public class ControlPanel extends javax.swing.JPanel {

    /** Creates new form ControlPanel */
    public ControlPanel() {
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setLayout(null);

        jButton1.setText("Save");
        add(jButton1);
        jButton1.setBounds(0, 0, 104, 39);

        jButton2.setText("Delete");
        add(jButton2);
        jButton2.setBounds(110, 0, 104, 42);

        jButton3.setText("Clear");
        add(jButton3);
        jButton3.setBounds(220, 0, 104, 42);

        jButton4.setText("Goto Grid View >>");
        add(jButton4);
        jButton4.setBounds(330, 0, 130, 42);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    // End of variables declaration//GEN-END:variables
}
