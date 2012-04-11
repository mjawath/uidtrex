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
import org.biz.chequeChequing.entity.accounts.bank.Bank;
import org.biz.chequeChequing.entity.service.bank.BankService;
import org.biz.dao.util.EntityService;
import org.components.windows.TabPanelUI;

/**
 *
 * @author Administrator
 */
public class BankDetailUI extends TabPanelUI {

    Bank selectedbank;
    List<Bank> banks;
    BankService service;
    EntityService es;

    /** Creates new form BankDetailUI */
    public BankDetailUI() {
        initComponents();
        init();
    }

    @Override
    public void init() {

        service = new BankService();
        banks = new ArrayList<Bank>();
        es = EntityService.getEntityService();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cLabel1 = new org.components.controls.CLabel();
        tcode = new org.components.controls.CTextField();
        tbankname = new org.components.controls.CTextField();
        cLabel2 = new org.components.controls.CLabel();
        cButton1 = new org.components.controls.CButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        cxTable1 = new org.components.controls.CxTable();

        cLabel1.setText("Code :");

        cLabel2.setText("Bank Name :");

        cButton1.setText("Save");
        cButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cButton1ActionPerformed(evt);
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
                        .addComponent(cButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbankname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cButton1ActionPerformed
        if (selectedbank != null) {
            Bank item = service.getDao().deatach(selectedbank, selectedbank.getId());
            uiToEty(item);
            service.getDao().update(item);
            banks = service.getDao().getAll();
            addToTable(banks);
            return;
        }

        Bank item = new Bank();
        item.setId(es.getKey());
        uiToEty(item);
        service.getDao().save(item);
        banks = service.getDao().getAll();
        addToTable(banks);
//        ipu.populateTable(banks);
    }//GEN-LAST:event_cButton1ActionPerformed
    public void etyToUI(Bank item) {
        uiEty.objToUi(tcode, item.getCode());

    }

    public void uiToEty(Bank item) {
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

    public void addToTable(List<Bank> items) {
        TableUtil.cleardata(cxTable1);
        for (Bank item : items) {
            addToTable(item);
        }
        TableUtil.addrow(cxTable1, new Object[]{TableUtil.newRowID, ""});
    }

    public void addToTable(Bank item) {
        TableUtil.addrow(cxTable1, new Object[]{item.getId(), item.getCode()});
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CButton cButton1;
    private org.components.controls.CLabel cLabel1;
    private org.components.controls.CLabel cLabel2;
    private org.components.controls.CxTable cxTable1;
    private javax.swing.JScrollPane jScrollPane1;
    private org.components.controls.CTextField tbankname;
    private org.components.controls.CTextField tcode;
    // End of variables declaration//GEN-END:variables
}
