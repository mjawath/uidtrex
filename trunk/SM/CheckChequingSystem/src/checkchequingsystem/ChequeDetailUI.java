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
import org.biz.app.ui.util.uiEty;
import org.biz.chequeChequing.entity.accounts.bank.Bank;
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
     init();   
    }

    @Override
    public void init() {
     controlPanel1.setCrudController(this);        
     
        cheques =new Cheques();
       
        service=new ChequeService();

        
    }

    @Override
    public void save() {

        
        super.save();
    }

    public void uiToEty(Cheques item) {
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cLabel1 = new org.components.controls.CLabel();
        tcode = new org.components.controls.CTextField();
        controlPanel1 = new com.components.custom.ControlPanel();

        setLayout(null);

        cLabel1.setText("Code :");
        add(cLabel1);
        cLabel1.setBounds(10, 132, 71, 25);
        add(tcode);
        tcode.setBounds(118, 132, 100, 25);
        add(controlPanel1);
        controlPanel1.setBounds(123, 182, 310, 53);
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
    private org.components.controls.CLabel cLabel1;
    private com.components.custom.ControlPanel controlPanel1;
    private org.components.controls.CTextField tcode;
    // End of variables declaration//GEN-END:variables

}
