/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.components.custom;

import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JComponent;

/**
 *
 * @author nnjj
 */
public class ActionTask extends AbstractAction{
    
    
    JComponent nextToFocus;

    public ActionTask(JComponent nextToFocus) {
        this.nextToFocus = nextToFocus;
    }

    public ActionTask() {
    }
    
    
    public boolean validate(){
    return true;
    }
    public boolean action(){
        System.out.println(" actiiion  perfforooree");
    return true;
    }
    public void actionPerformed(ActionEvent e) {
        if(! validate()){
        return;
        };
        
        if(! action()){
            
        return;
        };
        System.out.println("sdfasfasfas");
        //goto next focus
        if(nextToFocus!=null){
        nextToFocus.requestFocus();
        }
        
    }
    
}
