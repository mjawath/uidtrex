
package com.components.custom;

import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JTextField;

/**
 *
 * @author nnjj
 */
public class DropDownWithButton extends javax.swing.JPanel {

    
    ArrayList model; 
    /** Creates new form DropDownWithButton */
    public DropDownWithButton() {
        initComponents();
        model=new ArrayList();
    }

    public String getValue(){
    return cTextField1.getText();
    }
  
    public JTextField getTextField(){
    return cTextField1;
    }
    
    public void addToModel(String obj){
    model.add(obj);
    }
    
    public void setModel(String... list){
    Collections.addAll(model,list);
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
