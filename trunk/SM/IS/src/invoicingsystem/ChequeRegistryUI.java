/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ChequeRegistryUI.java
 *
 * Created on Jul 26, 2012, 7:55:34 AM
 */
package invoicingsystem;

/**
 *
 * @author d
 */
public class ChequeRegistryUI extends javax.swing.JPanel {

    /** Creates new form ChequeRegistryUI */
    public ChequeRegistryUI() {
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tcheckno = new org.components.controls.CTextField();
        tbank = new org.components.controls.CTextField();
        tcustomer = new org.components.controls.CTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        cTableMaster1 = new org.components.controls.CTableMaster();
        tcheckno1 = new org.components.controls.CTextField();
        tcustomer1 = new org.components.controls.CTextField();

        tcheckno.setText("Bank Accounts");

        tbank.setText("Cheque");

        tcustomer.setText("customer Accounts");

        cTableMaster1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Customer Name", "Bank", "Cheque No"
            }
        ));
        jScrollPane1.setViewportView(cTableMaster1);

        tcheckno1.setText("Bank");

        tcustomer1.setText("customer");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(tbank, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tcustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tcustomer1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tcheckno, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tcheckno1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tcheckno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tcheckno1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(tbank, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(tcustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(tcustomer1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(42, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CTableMaster cTableMaster1;
    private javax.swing.JScrollPane jScrollPane1;
    private org.components.controls.CTextField tbank;
    private org.components.controls.CTextField tcheckno;
    private org.components.controls.CTextField tcheckno1;
    private org.components.controls.CTextField tcustomer;
    private org.components.controls.CTextField tcustomer1;
    // End of variables declaration//GEN-END:variables
}
/*
user can regiser  cheques for a perticular bank

*
* when customer gives a cheque
*     cheque AC DR
*           customer AC CR
*
* when we deposit
*  bank AC DR
*       cheque  AC CR
* 
*
* when return
*   bank AC DR
*       cheque AC CR
*
* when cashed
*   custoemr AC DR
*       Cheque AC CR
*
*
*
 */