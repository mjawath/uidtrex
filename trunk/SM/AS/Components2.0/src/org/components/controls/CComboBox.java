/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CComboBox.java
 *
 * Created on May 6, 2010, 6:56:55 PM
 */

package org.components.controls;

import com.components.custom.CInputVerifier;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.JComboBox;

/**
 *
 * @author nano
 */
public class CComboBox extends JComboBox {

    /** Creates new form BeanForm */
    public CComboBox() {
        initComponents();
          addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_ENTER){

                   ActionEvent xx=new ActionEvent(this,ActionEvent.ACTION_FIRST,"");
                    fireActionEvent();
                    CComboBox.this.hidePopup();
                    // just change the focus 
                }
            }        
        }); 
          
//          addActionListener(new AbstractAction() {
//
//            public void actionPerformed(ActionEvent e) {
//              
////                fireActionEvent();
//            }
//        });
          
       getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    fireActionEvent();
                    // just change the focus 
                }
            }        
        });
        
    }

    protected void processFocusEvent(FocusEvent e) {
        
        super.processFocusEvent(e);
        int id = e.getID();
        switch (id) {
            case FocusEvent.FOCUS_GAINED:
                showPopup();
                break;
            case FocusEvent.FOCUS_LOST:
                hidePopup();
                break;
        }
        
    }
     
    public void setInputVerifier(CInputVerifier inputVerifier) {
        addActionListener(inputVerifier);
        super.setInputVerifier(inputVerifier);
    }
   
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
