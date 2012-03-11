/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DetailPanel.java
 *
 * Created on Dec 1, 2011, 9:47:29 PM
 */
package invoicingsystem;

import com.sun.awt.AWTUtilities;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JComponent;
import javax.swing.JTable;
import org.biz.app.ui.util.TableUtil;

/**
 *
 * @author nnjj
 */
public class DetailPanel extends javax.swing.JDialog implements KeyListener {

    JTable jt;

    public void setTable(JTable jtb) {
        this.jt = jtb;

        cTextField1.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
//                System.out.println(" sdfsdfsdf  fsadfsdfsdf sf sdfsd"+e);
                if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode()==KeyEvent.VK_UP) {
                    TableUtil.selectNextRow(jt, e);
//                    KeyboardFocusManager.getCurrentKeyboardFocusManager().redispatchEvent(DetailPanel.this.jt, e);
                }
//                if (e.getKeyCode() == ) {

//                    KeyboardFocusManager.getCurrentKeyboardFocusManager().redispatchEvent(DetailPanel.this.jt, e);
//                }

            }
        });



    }

    /** Creates new form DetailPanel */
    public DetailPanel() {
//       super(null, JDialog.ModalityType.MODELESS);
        setModal(false);
        this.setModalityType(ModalityType.MODELESS);
        setFocusableWindowState(false);
        setUndecorated(true);
        initComponents();
        getRootPane().setOpaque(false);
        AWTUtilities.setWindowOpacity(this, 0.8f);

        setSize(700, 100);
    }

    public void showWithoutFocus() {
        setFocusableWindowState(false);
        this.setVisible(true);
        setFocusableWindowState(true);
        setFocus();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cButton1 = new org.components.controls.CButton();
        cButton2 = new org.components.controls.CButton();
        cLabel1 = new org.components.controls.CLabel();
        cTextField1 = new org.components.controls.CTextField();
        cTextField2 = new org.components.controls.CTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setFocusableWindowState(false);
        getContentPane().setLayout(null);

        cButton1.setText("OK");
        getContentPane().add(cButton1);
        cButton1.setBounds(450, 92, 47, 23);

        cButton2.setText("Cancel");
        getContentPane().add(cButton2);
        cButton2.setBounds(503, 92, 65, 23);

        cLabel1.setText("Item ID");
        getContentPane().add(cLabel1);
        cLabel1.setBounds(10, 11, 69, 25);
        getContentPane().add(cTextField1);
        cTextField1.setBounds(10, 42, 117, 25);
        getContentPane().add(cTextField2);
        cTextField2.setBounds(130, 40, 150, 25);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public void setFocus() {
        cTextField1.requestFocus();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CButton cButton1;
    private org.components.controls.CButton cButton2;
    private org.components.controls.CLabel cLabel1;
    private org.components.controls.CTextField cTextField1;
    private org.components.controls.CTextField cTextField2;
    // End of variables declaration//GEN-END:variables

    public void dispatchEventx(KeyEvent event) {
        System.out.println("qffffff" + event + event.getComponent());
        KeyboardFocusManager.getCurrentKeyboardFocusManager().redispatchEvent(cTextField1, event);
        cTextField1.requestFocus();
        event.consume();
    }

    public void dispatchEvent(JComponent jc, KeyEvent event) {
        System.out.println("qffffff" + event + event.getComponent());
        cTextField1.requestFocus();
        KeyboardFocusManager.getCurrentKeyboardFocusManager().redispatchEvent(jc, event);
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        System.out.println("jpanel  " + e.getKeyCode());
    }

    public void keyReleased(KeyEvent e) {
    }
}
