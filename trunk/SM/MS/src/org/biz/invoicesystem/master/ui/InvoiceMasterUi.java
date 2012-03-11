/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * InvoiceMasterUi.java
 *
 * Created on Dec 2, 2011, 10:27:21 AM
 */
package org.biz.invoicesystem.master.ui;

import javax.swing.JPanel;
import org.components.windows.TabPanelUI;

/*
 * @2011/12/08 zumri -  GUI creation  of invoice
 * @2011/12/09 jawath-  creation  of invoice logic
 * 
 */

public class InvoiceMasterUi extends TabPanelUI  {

//    SalesInvoice    
    
    /** Creates new form InvoiceMasterUi */
    public InvoiceMasterUi() {
        initComponents();
        
     init();    
    }

    @Override
    public void init() {
        
        System.out.println("yyyyyy");
        
        
    }
    public void lineItem(){
        
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblInvoice = new org.components.controls.CTable();
        cPanel1 = new org.components.containers.CPanel();
        cLabel5 = new org.components.controls.CLabel();
        cLabel7 = new org.components.controls.CLabel();
        cLabel8 = new org.components.controls.CLabel();
        cLabel9 = new org.components.controls.CLabel();
        cLabel10 = new org.components.controls.CLabel();
        cTextField1 = new org.components.controls.CTextField();
        cTextField4 = new org.components.controls.CTextField();
        cTextField5 = new org.components.controls.CTextField();
        cTextField6 = new org.components.controls.CTextField();
        cTextField11 = new org.components.controls.CTextField();
        cLabel15 = new org.components.controls.CLabel();
        cComboBox1 = new org.components.controls.CComboBox();
        cLabel2 = new org.components.controls.CLabel();
        cComboBox2 = new org.components.controls.CComboBox();
        cTextField2 = new org.components.controls.CTextField();
        cPanel2 = new org.components.containers.CPanel();
        cLabel3 = new org.components.controls.CLabel();
        tCustomerSearchField = new org.components.controls.CTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tCusDetail = new org.components.controls.CTextArea();
        tInvType = new org.components.controls.CCheckBox();
        cDatePicker1 = new org.components.controls.CDatePicker();
        cPanel4 = new org.components.containers.CPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        cxTable2 = new org.components.controls.CxTable();
        cLabel4 = new org.components.controls.CLabel();
        cPanel3 = new org.components.containers.CPanel();
        cLabel6 = new org.components.controls.CLabel();
        cLabel11 = new org.components.controls.CLabel();
        cLabel12 = new org.components.controls.CLabel();
        cLabel13 = new org.components.controls.CLabel();
        tLastInvDate = new org.components.controls.CTextField();
        tInvTotal = new org.components.controls.CTextField();
        cPanel5 = new org.components.containers.CPanel();
        cLabel16 = new org.components.controls.CLabel();
        cLabel17 = new org.components.controls.CLabel();
        cLabel18 = new org.components.controls.CLabel();
        cLabel19 = new org.components.controls.CLabel();
        cLabel20 = new org.components.controls.CLabel();
        tBouncedChq = new org.components.controls.CTextField();
        tCreditLimit = new org.components.controls.CTextField();
        tTotalDues = new org.components.controls.CTextField();
        tPdChq = new org.components.controls.CTextField();

        setLayout(null);

        tblInvoice.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblInvoice);

        add(jScrollPane1);
        jScrollPane1.setBounds(10, 130, 930, 210);

        cPanel1.setLayout(null);

        cLabel5.setText("Salesman");
        cLabel5.setFont(new java.awt.Font("Tahoma", 0, 12));
        cPanel1.add(cLabel5);
        cLabel5.setBounds(800, 70, 60, 25);

        cLabel7.setText("Total");
        cLabel7.setFont(new java.awt.Font("Tahoma", 0, 12));
        cPanel1.add(cLabel7);
        cLabel7.setBounds(10, 10, 70, 20);

        cLabel8.setText("Tax");
        cLabel8.setFont(new java.awt.Font("Tahoma", 0, 12));
        cPanel1.add(cLabel8);
        cLabel8.setBounds(10, 40, 70, 20);

        cLabel9.setText("Discount");
        cLabel9.setFont(new java.awt.Font("Tahoma", 0, 12));
        cPanel1.add(cLabel9);
        cLabel9.setBounds(10, 60, 70, 20);

        cLabel10.setText("Final Total");
        cLabel10.setFont(new java.awt.Font("Tahoma", 0, 12));
        cPanel1.add(cLabel10);
        cLabel10.setBounds(10, 90, 70, 20);

        cTextField1.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel1.add(cTextField1);
        cTextField1.setBounds(90, 90, 150, 20);

        cTextField4.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel1.add(cTextField4);
        cTextField4.setBounds(90, 10, 150, 20);

        cTextField5.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel1.add(cTextField5);
        cTextField5.setBounds(90, 40, 150, 20);

        cTextField6.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel1.add(cTextField6);
        cTextField6.setBounds(90, 60, 150, 20);

        cTextField11.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel1.add(cTextField11);
        cTextField11.setBounds(90, 120, 150, 20);

        cLabel15.setText("Recieved");
        cLabel15.setFont(new java.awt.Font("Tahoma", 0, 12));
        cPanel1.add(cLabel15);
        cLabel15.setBounds(10, 120, 70, 20);

        add(cPanel1);
        cPanel1.setBounds(680, 360, 260, 160);
        add(cComboBox1);
        cComboBox1.setBounds(830, 70, 110, 23);

        cLabel2.setText("Salesman");
        cLabel2.setFont(new java.awt.Font("Tahoma", 0, 12));
        add(cLabel2);
        cLabel2.setBounds(780, 70, 60, 25);

        cComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Invoice", "Quotation", "Credit Note", "Consignment Out" }));
        add(cComboBox2);
        cComboBox2.setBounds(690, 10, 140, 23);

        cTextField2.setText("inv no");
        cTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cTextField2ActionPerformed(evt);
            }
        });
        add(cTextField2);
        cTextField2.setBounds(830, 10, 110, 25);

        cPanel2.setLayout(null);

        cLabel3.setText("Customer");
        cLabel3.setFont(new java.awt.Font("Tahoma", 1, 10));
        cPanel2.add(cLabel3);
        cLabel3.setBounds(10, 0, 60, 20);
        cPanel2.add(tCustomerSearchField);
        tCustomerSearchField.setBounds(60, 0, 150, 20);

        tCusDetail.setColumns(20);
        tCusDetail.setRows(10);
        jScrollPane3.setViewportView(tCusDetail);

        cPanel2.add(jScrollPane3);
        jScrollPane3.setBounds(10, 20, 230, 70);

        tInvType.setText("Type");
        cPanel2.add(tInvType);
        tInvType.setBounds(10, 90, 140, 23);

        add(cPanel2);
        cPanel2.setBounds(0, 11, 240, 110);
        add(cDatePicker1);
        cDatePicker1.setBounds(830, 40, 110, 22);

        cPanel4.setLayout(null);

        cxTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Description", "Price Range"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(cxTable2);

        cPanel4.add(jScrollPane4);
        jScrollPane4.setBounds(10, 10, 210, 100);

        add(cPanel4);
        cPanel4.setBounds(10, 380, 230, 120);

        cLabel4.setText("Date");
        cLabel4.setFont(new java.awt.Font("Tahoma", 0, 12));
        add(cLabel4);
        cLabel4.setBounds(780, 40, 60, 25);

        cPanel3.setLayout(null);

        cLabel6.setText("Salesman");
        cLabel6.setFont(new java.awt.Font("Tahoma", 0, 12));
        cPanel3.add(cLabel6);
        cLabel6.setBounds(800, 70, 60, 25);

        cLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cLabel11.setText("Last Invoice");
        cLabel11.setFont(new java.awt.Font("Tahoma", 1, 12));
        cPanel3.add(cLabel11);
        cLabel11.setBounds(0, 0, 180, 20);

        cLabel12.setText("Invoice Date");
        cLabel12.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel3.add(cLabel12);
        cLabel12.setBounds(10, 20, 70, 20);

        cLabel13.setText("Invoice Total");
        cLabel13.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel3.add(cLabel13);
        cLabel13.setBounds(10, 60, 70, 20);

        tLastInvDate.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel3.add(tLastInvDate);
        tLastInvDate.setBounds(10, 40, 150, 20);

        tInvTotal.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel3.add(tInvTotal);
        tInvTotal.setBounds(10, 80, 150, 20);

        add(cPanel3);
        cPanel3.setBounds(490, 10, 180, 110);

        cPanel5.setLayout(null);

        cLabel16.setText("Salesman");
        cLabel16.setFont(new java.awt.Font("Tahoma", 0, 12));
        cPanel5.add(cLabel16);
        cLabel16.setBounds(800, 70, 60, 25);

        cLabel17.setText("Credit Limit");
        cLabel17.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel5.add(cLabel17);
        cLabel17.setBounds(10, 10, 70, 20);

        cLabel18.setText("Total Dues");
        cLabel18.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel5.add(cLabel18);
        cLabel18.setBounds(10, 40, 70, 20);

        cLabel19.setText("PD Chqs");
        cLabel19.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel5.add(cLabel19);
        cLabel19.setBounds(10, 60, 70, 20);

        cLabel20.setText("Bounced Chqs");
        cLabel20.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel5.add(cLabel20);
        cLabel20.setBounds(10, 80, 70, 20);

        tBouncedChq.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel5.add(tBouncedChq);
        tBouncedChq.setBounds(90, 80, 150, 20);

        tCreditLimit.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel5.add(tCreditLimit);
        tCreditLimit.setBounds(90, 10, 150, 20);

        tTotalDues.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel5.add(tTotalDues);
        tTotalDues.setBounds(90, 40, 150, 20);

        tPdChq.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel5.add(tPdChq);
        tPdChq.setBounds(90, 60, 150, 20);

        add(cPanel5);
        cPanel5.setBounds(240, 10, 250, 110);
    }// </editor-fold>//GEN-END:initComponents

    private void cTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cTextField2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CComboBox cComboBox1;
    private org.components.controls.CComboBox cComboBox2;
    private org.components.controls.CDatePicker cDatePicker1;
    private org.components.controls.CLabel cLabel10;
    private org.components.controls.CLabel cLabel11;
    private org.components.controls.CLabel cLabel12;
    private org.components.controls.CLabel cLabel13;
    private org.components.controls.CLabel cLabel15;
    private org.components.controls.CLabel cLabel16;
    private org.components.controls.CLabel cLabel17;
    private org.components.controls.CLabel cLabel18;
    private org.components.controls.CLabel cLabel19;
    private org.components.controls.CLabel cLabel2;
    private org.components.controls.CLabel cLabel20;
    private org.components.controls.CLabel cLabel3;
    private org.components.controls.CLabel cLabel4;
    private org.components.controls.CLabel cLabel5;
    private org.components.controls.CLabel cLabel6;
    private org.components.controls.CLabel cLabel7;
    private org.components.controls.CLabel cLabel8;
    private org.components.controls.CLabel cLabel9;
    private org.components.containers.CPanel cPanel1;
    private org.components.containers.CPanel cPanel2;
    private org.components.containers.CPanel cPanel3;
    private org.components.containers.CPanel cPanel4;
    private org.components.containers.CPanel cPanel5;
    private org.components.controls.CTextField cTextField1;
    private org.components.controls.CTextField cTextField11;
    private org.components.controls.CTextField cTextField2;
    private org.components.controls.CTextField cTextField4;
    private org.components.controls.CTextField cTextField5;
    private org.components.controls.CTextField cTextField6;
    private org.components.controls.CxTable cxTable2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private org.components.controls.CTextField tBouncedChq;
    private org.components.controls.CTextField tCreditLimit;
    private org.components.controls.CTextArea tCusDetail;
    private org.components.controls.CTextField tCustomerSearchField;
    private org.components.controls.CTextField tInvTotal;
    private org.components.controls.CCheckBox tInvType;
    private org.components.controls.CTextField tLastInvDate;
    private org.components.controls.CTextField tPdChq;
    private org.components.controls.CTextField tTotalDues;
    private org.components.controls.CTable tblInvoice;
    // End of variables declaration//GEN-END:variables

    @Override
    public String getTabName() {
        return  "Invoice Master";
    }

    @Override
    public JPanel getJPanel() {
        return this;
    }
}
