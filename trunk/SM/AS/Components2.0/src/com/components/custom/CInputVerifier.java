/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.components.custom;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;

/**
 *
 * @author nnjj
 */
public class CInputVerifier extends javax.swing.InputVerifier implements ActionListener{
 Component nextToFocus;

    public CInputVerifier(Component nextToFocus) {
        this.nextToFocus = nextToFocus;
    }
 
    public boolean validate(){
    return true;
    }
    public boolean action(){
    return true;
    }
    public boolean verify(JComponent input) {
             
        if(! validate()){
        return false;
        };
        
        if(! action()){
        return false;
        };
        //goto next focus
        if(nextToFocus!=null){
        nextToFocus.requestFocus();
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
     action();
    }
    
}
