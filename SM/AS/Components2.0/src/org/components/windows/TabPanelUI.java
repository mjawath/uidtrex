package org.components.windows;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TabPanelUI.java
 *
 * Created on Nov 22, 2010, 1:58:08 PM
 */
import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;

/**
 *
 * @author mjawath
 */
public abstract class TabPanelUI extends javax.swing.JPanel implements TabChildUI {

    /** Creates new form TabPanelUI */
    public TabPanelUI() {
        initComponents();

    }

    public void init() {

             Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {

            public void eventDispatched(AWTEvent event) {
                if (((KeyEvent) event).getKeyCode() == KeyEvent.VK_ESCAPE && TabPanelUI.this.isShowing()) {
                    System.out.println("closing");
                };
            }
        }, AWTEvent.KEY_EVENT_MASK);
        events();
    }

    public void events() {

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(null);
    }// </editor-fold>//GEN-END:initComponents

    public abstract String getTabName();

    public abstract JPanel getJPanel();

    public void updateEntityUI() {
    }

    public void release(){

        
    }

   

    public void setobj(Object obj) {
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
