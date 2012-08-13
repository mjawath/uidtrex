/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CustomerStatementUI.java
 *
 * Created on Aug 28, 2011, 8:13:50 PM
 */
package invoicingsystem;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import org.biz.invoicesystem.entity.master.Customer;
import org.biz.invoicesystem.entity.transactions.CustomerStatement;
import org.biz.invoicesystem.service.transactions.CustomerStatementService;
import org.components.windows.TabPanelUI;

/**
 *
 * @author nnjj
 */
public class CustomerPayemntsUI extends TabPanelUI {

    int x;
    Customer cust;
    CustomerStatementService service;
    List<CustomerStatement> listStatements;

    /** Creates new form CustomerStatementUI */
    public CustomerPayemntsUI() {
        initComponents();
        init();
    }

    @Override
    public void init() {

        cust=new Customer();
        listStatements=new ArrayList<CustomerStatement>();
        service =new CustomerStatementService();
        controlPanel1.setCrudController(this);
        
        super.init();
    }

    @Override
    public void save() {

        service.getDao().save(null);
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        controlPanel1 = new com.components.custom.ControlPanel();
        cTextField1 = new org.components.controls.CTextField();
        Cheque = new org.components.controls.CTabPane();
        cPanel1 = new org.components.containers.CPanel();
        chequeRecievedUI1 = new invoicingsystem.ChequeRecievedUI();
        cPanel2 = new org.components.containers.CPanel();
        cPanel3 = new org.components.containers.CPanel();
        cPanel4 = new org.components.containers.CPanel();

        cTextField1.setText("cTextField1");

        javax.swing.GroupLayout cPanel1Layout = new javax.swing.GroupLayout(cPanel1);
        cPanel1.setLayout(cPanel1Layout);
        cPanel1Layout.setHorizontalGroup(
            cPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(chequeRecievedUI1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        cPanel1Layout.setVerticalGroup(
            cPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chequeRecievedUI1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Cheque.addTab("Cheque", cPanel1);

        javax.swing.GroupLayout cPanel2Layout = new javax.swing.GroupLayout(cPanel2);
        cPanel2.setLayout(cPanel2Layout);
        cPanel2Layout.setHorizontalGroup(
            cPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 246, Short.MAX_VALUE)
        );
        cPanel2Layout.setVerticalGroup(
            cPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 72, Short.MAX_VALUE)
        );

        Cheque.addTab("Credit Card", cPanel2);

        javax.swing.GroupLayout cPanel3Layout = new javax.swing.GroupLayout(cPanel3);
        cPanel3.setLayout(cPanel3Layout);
        cPanel3Layout.setHorizontalGroup(
            cPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 246, Short.MAX_VALUE)
        );
        cPanel3Layout.setVerticalGroup(
            cPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 72, Short.MAX_VALUE)
        );

        Cheque.addTab("Cash", cPanel3);

        javax.swing.GroupLayout cPanel4Layout = new javax.swing.GroupLayout(cPanel4);
        cPanel4.setLayout(cPanel4Layout);
        cPanel4Layout.setHorizontalGroup(
            cPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 246, Short.MAX_VALUE)
        );
        cPanel4Layout.setVerticalGroup(
            cPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 72, Short.MAX_VALUE)
        );

        Cheque.addTab("Bank / Other", cPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(cTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                .addComponent(controlPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(Cheque, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(157, 157, 157))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(controlPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addComponent(Cheque, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(304, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CTabPane Cheque;
    private org.components.containers.CPanel cPanel1;
    private org.components.containers.CPanel cPanel2;
    private org.components.containers.CPanel cPanel3;
    private org.components.containers.CPanel cPanel4;
    private org.components.controls.CTextField cTextField1;
    private invoicingsystem.ChequeRecievedUI chequeRecievedUI1;
    private com.components.custom.ControlPanel controlPanel1;
    // End of variables declaration//GEN-END:variables

    @Override
    public String getTabName() {
        return "customer statement";
    }

    @Override
    public JPanel getJPanel() {
        return this;
    }
}
/*

 applying cash cheque bank and credit card functianlity
 * 
 */