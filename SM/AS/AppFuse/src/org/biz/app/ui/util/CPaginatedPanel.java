/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CPaginatedPanel.java
 *
 * Created on Apr 6, 2012, 6:51:44 AM
 */
package org.biz.app.ui.util;

import org.biz.dao.service.Service;

/**
 *
 * @author nnjj
 */
public class CPaginatedPanel extends javax.swing.JPanel {

    /** Creates new form CPaginatedPanel */
    public CPaginatedPanel() {
        initComponents();



    }
    private String qryName = "";
    private Service service;

    public void setService(Service service) {
        this.service = service;
    }

    public Service gerService() {
        return service;
    }

    public void getNextEntity() {
    }

    public void getPreviousEntity() {
    }

    public void getExecuteQuery() {
    }

    public void getNextPage() {
        service.moveToNextPage(qryName, 0);
    }

    public void getPreviousePage() {
        service.getPreviousePage(qryName, 0);
    }

    public void getLastPage() {
        service.getLastPage(qryName);
    }

    public void getFirstPage() {
        service.getFirstPage(qryName);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cButton3 = new org.components.controls.CButton();
        cButton4 = new org.components.controls.CButton();
        cButton5 = new org.components.controls.CButton();
        tnextpage = new org.components.controls.CButton();
        cPageCount = new org.components.controls.CTextFieldPopUp();

        setLayout(null);

        cButton3.setText("<");
        cButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cButton3ActionPerformed(evt);
            }
        });
        add(cButton3);
        cButton3.setBounds(110, 10, 41, 23);

        cButton4.setText("<<");
        cButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cButton4ActionPerformed(evt);
            }
        });
        add(cButton4);
        cButton4.setBounds(50, 10, 49, 23);

        cButton5.setText(">>");
        cButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cButton5ActionPerformed(evt);
            }
        });
        add(cButton5);
        cButton5.setBounds(310, 10, 49, 23);

        tnextpage.setText(">");
        tnextpage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tnextpageActionPerformed(evt);
            }
        });
        add(tnextpage);
        tnextpage.setBounds(260, 10, 41, 23);
        add(cPageCount);
        cPageCount.setBounds(160, 10, 90, 20);
    }// </editor-fold>//GEN-END:initComponents

    private void cButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cButton4ActionPerformed
        getFirstPage();
       }//GEN-LAST:event_cButton4ActionPerformed

    private void cButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cButton6ActionPerformed

    private void cButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cButton3ActionPerformed
        getPreviousePage();
}//GEN-LAST:event_cButton3ActionPerformed

    private void cButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cButton5ActionPerformed
        getLastPage();

}//GEN-LAST:event_cButton5ActionPerformed

    private void tnextpageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tnextpageActionPerformed
        getNextPage();

}//GEN-LAST:event_tnextpageActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CButton cButton3;
    private org.components.controls.CButton cButton4;
    private org.components.controls.CButton cButton5;
    private org.components.controls.CTextFieldPopUp cPageCount;
    private org.components.controls.CButton tnextpage;
    // End of variables declaration//GEN-END:variables
}
