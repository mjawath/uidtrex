/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ContactsUI.java
 *
 * Created on May 2, 2012, 12:42:57 PM
 */
package org.biz.invoicesystem.master.ui;

import org.components.windows.TabPanelUI;

/**
 *
 * @author d
 */
public class ContactsUI extends TabPanelUI {

    /** Creates new form ContactsUI */
    public ContactsUI() {
        initComponents();
    }

    @Override
    public void init() {
   
        controlPanel1.setCrudController(this);
        super.init();
    }

   
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cTextField1 = new org.components.controls.CTextField();
        cLabel1 = new org.components.controls.CLabel();
        cTextField2 = new org.components.controls.CTextField();
        cLabel2 = new org.components.controls.CLabel();
        cTextField3 = new org.components.controls.CTextField();
        cLabel3 = new org.components.controls.CLabel();
        cTextField4 = new org.components.controls.CTextField();
        cLabel4 = new org.components.controls.CLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        cTable1 = new org.components.controls.CTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        cTable2 = new org.components.controls.CTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        cTable3 = new org.components.controls.CTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        cTable4 = new org.components.controls.CTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        cTable5 = new org.components.controls.CTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        cTable6 = new org.components.controls.CTable();
        cLabel5 = new org.components.controls.CLabel();
        cTextField5 = new org.components.controls.CTextField();
        cLabel6 = new org.components.controls.CLabel();
        cTextField6 = new org.components.controls.CTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        cTable7 = new org.components.controls.CTable();
        cTextField7 = new org.components.controls.CTextField();
        cLabel7 = new org.components.controls.CLabel();
        cTextField8 = new org.components.controls.CTextField();
        cTextField9 = new org.components.controls.CTextField();
        controlPanel1 = new com.components.custom.ControlPanel();

        setLayout(null);

        cTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cTextField1ActionPerformed(evt);
            }
        });
        add(cTextField1);
        cTextField1.setBounds(80, 30, 140, 25);

        cLabel1.setText("Tel");
        add(cLabel1);
        cLabel1.setBounds(10, 28, 68, 25);
        add(cTextField2);
        cTextField2.setBounds(80, 160, 140, 25);

        cLabel2.setText("Fax");
        add(cLabel2);
        cLabel2.setBounds(30, 160, 68, 25);
        add(cTextField3);
        cTextField3.setBounds(330, 30, 140, 25);

        cLabel3.setText("Email");
        add(cLabel3);
        cLabel3.setBounds(270, 30, 50, 25);
        add(cTextField4);
        cTextField4.setBounds(590, 170, 140, 25);

        cLabel4.setText("WebSite");
        add(cLabel4);
        cLabel4.setBounds(520, 170, 68, 25);

        cTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Title 1"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(cTable1);
        cTable1.getColumnModel().getColumn(0).setResizable(false);

        add(jScrollPane1);
        jScrollPane1.setBounds(80, 200, 142, 95);

        cTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Title 1"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(cTable2);
        cTable2.getColumnModel().getColumn(0).setResizable(false);

        add(jScrollPane2);
        jScrollPane2.setBounds(330, 60, 142, 95);

        cTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Title 1"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(cTable3);
        cTable3.getColumnModel().getColumn(0).setResizable(false);

        add(jScrollPane3);
        jScrollPane3.setBounds(82, 59, 142, 95);

        cTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Title 1"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(cTable4);
        cTable4.getColumnModel().getColumn(0).setResizable(false);

        add(jScrollPane4);
        jScrollPane4.setBounds(330, 190, 142, 95);

        cTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Title 1"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(cTable5);
        cTable5.getColumnModel().getColumn(0).setResizable(false);

        add(jScrollPane5);
        jScrollPane5.setBounds(600, 60, 142, 95);

        cTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Title 1"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(cTable6);
        cTable6.getColumnModel().getColumn(0).setResizable(false);

        add(jScrollPane6);
        jScrollPane6.setBounds(590, 200, 142, 95);

        cLabel5.setText("WebSite");
        add(cLabel5);
        cLabel5.setBounds(260, 170, 68, 20);
        add(cTextField5);
        cTextField5.setBounds(330, 160, 140, 25);

        cLabel6.setText("WebSite");
        add(cLabel6);
        cLabel6.setBounds(510, 30, 68, 20);
        add(cTextField6);
        cTextField6.setBounds(600, 30, 140, 25);

        cTable7.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane7.setViewportView(cTable7);

        add(jScrollPane7);
        jScrollPane7.setBounds(50, 460, 452, 100);
        add(cTextField7);
        cTextField7.setBounds(100, 420, 110, 25);

        cLabel7.setText("Address");
        add(cLabel7);
        cLabel7.setBounds(20, 420, 70, 25);
        add(cTextField8);
        cTextField8.setBounds(220, 420, 90, 25);
        add(cTextField9);
        cTextField9.setBounds(320, 420, 100, 25);
        add(controlPanel1);
        controlPanel1.setBounds(570, 390, 340, 30);
    }// </editor-fold>//GEN-END:initComponents

    private void cTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cTextField1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CLabel cLabel1;
    private org.components.controls.CLabel cLabel2;
    private org.components.controls.CLabel cLabel3;
    private org.components.controls.CLabel cLabel4;
    private org.components.controls.CLabel cLabel5;
    private org.components.controls.CLabel cLabel6;
    private org.components.controls.CLabel cLabel7;
    private org.components.controls.CTable cTable1;
    private org.components.controls.CTable cTable2;
    private org.components.controls.CTable cTable3;
    private org.components.controls.CTable cTable4;
    private org.components.controls.CTable cTable5;
    private org.components.controls.CTable cTable6;
    private org.components.controls.CTable cTable7;
    private org.components.controls.CTextField cTextField1;
    private org.components.controls.CTextField cTextField2;
    private org.components.controls.CTextField cTextField3;
    private org.components.controls.CTextField cTextField4;
    private org.components.controls.CTextField cTextField5;
    private org.components.controls.CTextField cTextField6;
    private org.components.controls.CTextField cTextField7;
    private org.components.controls.CTextField cTextField8;
    private org.components.controls.CTextField cTextField9;
    private com.components.custom.ControlPanel controlPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    // End of variables declaration//GEN-END:variables

    @Override
    public String getTabName() {
        return "Contacts ";
    }

   
}
