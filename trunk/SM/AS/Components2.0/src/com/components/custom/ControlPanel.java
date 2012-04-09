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
    CrudControl control;

    public void setCrudController(CrudControl control) {
        this.control = control;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btsave = new javax.swing.JButton();
        btdelete = new javax.swing.JButton();
        btclear = new javax.swing.JButton();
        btgrid = new javax.swing.JButton();

        setLayout(null);

        btsave.setText("Save");
        btsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsaveActionPerformed(evt);
            }
        });
        add(btsave);
        btsave.setBounds(0, 0, 104, 39);

        btdelete.setText("Delete");
        btdelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btdeleteActionPerformed(evt);
            }
        });
        add(btdelete);
        btdelete.setBounds(110, 0, 104, 42);

        btclear.setText("Clear");
        btclear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btclearActionPerformed(evt);
            }
        });
        add(btclear);
        btclear.setBounds(220, 0, 104, 42);

        btgrid.setText("Goto Grid View >>");
        btgrid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btgridActionPerformed(evt);
            }
        });
        add(btgrid);
        btgrid.setBounds(330, 0, 130, 42);
    }// </editor-fold>//GEN-END:initComponents

    private void btsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsaveActionPerformed

        control.save();
    }//GEN-LAST:event_btsaveActionPerformed

    private void btdeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btdeleteActionPerformed
        control.delete();        // TODO add your handling code here:
    }//GEN-LAST:event_btdeleteActionPerformed

    private void btclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btclearActionPerformed
        control.clear();
    }//GEN-LAST:event_btclearActionPerformed

    private void btgridActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btgridActionPerformed
        control.gotoList();        // TODO add your handling code here:
    }//GEN-LAST:event_btgridActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btclear;
    private javax.swing.JButton btdelete;
    private javax.swing.JButton btgrid;
    private javax.swing.JButton btsave;
    // End of variables declaration//GEN-END:variables
}
