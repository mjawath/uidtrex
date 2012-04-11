/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * BankDetailUI.java
 *
 * Created on May 26, 2011, 4:39:06 PM
 */
package checkchequingsystem;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import org.biz.app.ui.util.TableUtil;
import org.biz.app.ui.util.uiEty;
import org.biz.chequeChequing.entity.accounts.bank.BankBranch;
import org.biz.chequeChequing.entity.service.bank.BankBranchService;
import org.biz.dao.util.EntityService;
import org.components.windows.TabPanelUI;

/**
 *
 * @author Administrator
 */
public class BankBranchDetailUI extends TabPanelUI {

    BankBranch selectedbankbranch;
    List<BankBranch> branches;
    BankBranchService service;
    EntityService es;
    /** Creates new form BankDetailUI */
    public BankBranchDetailUI() {
        initComponents();

    }

    @Override
    public void init() {

        service = new BankBranchService();
        branches = new ArrayList<BankBranch>();
       es = EntityService.getEntityService();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cButton1 = new org.components.controls.CButton();
        cLabel1 = new org.components.controls.CLabel();
        tcode = new org.components.controls.CTextField();
        tbankname = new org.components.controls.CTextField();
        cLabel2 = new org.components.controls.CLabel();
        cButton2 = new org.components.controls.CButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        cxTable1 = new org.components.controls.CxTable();

        cButton1.setText("Save");
        cButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cButton1ActionPerformed(evt);
            }
        });

        cLabel1.setText("Code :");

        cLabel2.setText("Bank Name :");

        cButton2.setText("Save");
        cButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cButton2ActionPerformed(evt);
            }
        });

        cxTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Code", "Name", "Description"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(cxTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(tcode, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tbankname, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(cButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(cButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(176, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbankname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(98, 98, 98)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cButton2ActionPerformed
        if (selectedbankbranch != null) {
            BankBranch item = service.getDao().deatach(selectedbankbranch, selectedbankbranch.getId());
            uiToEty(item);
            service.getDao().update(item);
            branches = service.getDao().getAll();
            addToTable(branches);
            return;
        }

        BankBranch item = new BankBranch();
        item.setId(es.getKey());
        uiToEty(item);
        service.getDao().save(item);
        branches = service.getDao().getAll();
        addToTable(branches);
        //        ipu.populateTable(banks);
}//GEN-LAST:event_cButton2ActionPerformed

    private void cButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cButton1ActionPerformed

       public void addToTable(List<BankBranch> items) {
        TableUtil.cleardata(cxTable1);
        for (BankBranch item : items) {
            addToTable(item);
        }
        TableUtil.addrow(cxTable1, new Object[]{TableUtil.newRowID, ""});
    }

    public void addToTable(BankBranch item) {
        TableUtil.addrow(cxTable1, new Object[]{item.getId(), item.getCode()});
    }
    
    public void etyToUI(BankBranch item) {
        uiEty.objToUi(tcode, item.getCode());

    }

    public void uiToEty(BankBranch item) {
        item.setCode(uiEty.tcToStr(tcode));

    }

    @Override
    public String getTabName() {
        return "Bank UI";
    }

    @Override
    public JPanel getJPanel() {
        return this;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CButton cButton1;
    private org.components.controls.CButton cButton2;
    private org.components.controls.CLabel cLabel1;
    private org.components.controls.CLabel cLabel2;
    private org.components.controls.CxTable cxTable1;
    private javax.swing.JScrollPane jScrollPane1;
    private org.components.controls.CTextField tbankname;
    private org.components.controls.CTextField tcode;
    // End of variables declaration//GEN-END:variables
}
