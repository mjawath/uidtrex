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

import javax.swing.JPanel;
import org.biz.chequeChequing.entity.accounts.bank.Cheques;
import org.biz.chequeChequing.entity.service.bank.ChequeService;
import org.components.windows.TabPanelUI;

/**
 *
 * @author Administrator
 */
public class ChequeDetailUI extends TabPanelUI {

    Cheques cheques;
    ChequeService service;


    /** Creates new form BankDetailUI */
    public ChequeDetailUI() {
        initComponents();
        
    }

    @Override
    public void init() {
        service=new ChequeService();

        
    }


   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public String getTabName() {
        return "Bank UI";
    }

    @Override
    public JPanel getJPanel() {
        return this;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
