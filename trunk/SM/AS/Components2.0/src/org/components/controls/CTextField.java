/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * jtextfield.java
 *
 * Created on May 4, 2010, 7:39:59 PM
 */
package org.components.controls;

import com.components.custom.ActionTask;
import com.components.custom.CInputVerifier;
import com.components.custom.IContainer;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.InputVerifier;
import javax.swing.SwingUtilities;
import org.components.parent.controls.PTextField;

/**
 *
 * @author nano
 */
public class CTextField extends PTextField {

    IContainer container;
    @Override
    protected void processFocusEvent(FocusEvent e) {
        super.processFocusEvent(e);
        if (e.isTemporary()) {
            return;
        }

        int id = e.getID();
        switch (id) {
            case FocusEvent.FOCUS_GAINED:
                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        selectAll();
                    }
                });
                break;
            case FocusEvent.FOCUS_LOST:
                final CTextField org = (CTextField) e.getComponent();

                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        org.setCaretPosition(org.getText().length());
                    }
                });
                break;
        }




    }

 
    /** Creates new form BeanForm */
    public CTextField() {
        initComponents();
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    if(container !=null){
                    container.callBackAction();
                    }
                    postActionEvent();
                    // just change the focus 
                }
            }        
        }); 
        
//        setInputVerifier(new CInputVerifier());
    }
    

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        setPreferredSize(new java.awt.Dimension(6, 25));
    }// </editor-fold>//GEN-END:initComponents

    public void setActionTask(ActionTask actionTask) {
        addActionListener(actionTask);
    }
   
    
    @Override
    protected void processKeyEvent(KeyEvent e) {
//        if(e.getKeyCode()==KeyEvent.VK_ENTER){
//            postActionEvent();
//            return;
//        }
        super.processKeyEvent(e);        
    }
    
    public void setInputVerifier(CInputVerifier inputVerifier) {
//        addActionListener(inputVerifier);
        super.setInputVerifier(inputVerifier);
    }
    
    
    
    private String formater;

    /**
     * Get the value of formater
     *
     * @return the value of formater
     */
    public String getFormater() {
        return formater;
    }

    /**
     * Set the value of formater
     *
     * @param formater new value of formater
     */
    public void setFormater(String formater) {
        this.formater = formater;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
