
package com.components.custom;

import javax.swing.JTextField;

/**
 *
 * @author nnjj
 */
public class DropDownWithButton extends javax.swing.JPanel {

    /** Creates new form DropDownWithButton */
    public DropDownWithButton() {
        initComponents();
    }

    public String getValue(){
    return cTextField1.getText();
    }
  
    public JTextField getTextField(){
    return cTextField1;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cTextField1 = new org.components.controls.CTextField();
        cButton1 = new org.components.controls.CButton();

        setLayout(null);
        add(cTextField1);
        cTextField1.setBounds(0, 0, 124, 25);

        cButton1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        cButton1.setPreferredSize(new java.awt.Dimension(37, 212));
        add(cButton1);
        cButton1.setBounds(130, 0, 19, 20);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CButton cButton1;
    private org.components.controls.CTextField cTextField1;
    // End of variables declaration//GEN-END:variables
}
