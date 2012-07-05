/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * LineItemPanel.java
 *
 * Created on Feb 23, 2012, 10:21:43 PM
 */
package org.biz.invoicesystem.ui.transactions.components;

import app.utils.MathUtil;
import com.components.custom.ActionTask;
import com.components.custom.DropDownWithButton;
import com.components.custom.TextFieldWithPopUP;
import invoicingsystem.LineItemPanel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JTextField;
import org.biz.app.ui.util.uiEty;
import org.biz.invoicesystem.entity.master.Customer;
import org.biz.invoicesystem.entity.master.Item;
import org.biz.invoicesystem.entity.master.UOM;
import org.biz.invoicesystem.entity.transactions.SalesInvoiceLineItem;

/**
 *
 * @author nnjj
 */
public class SalesLineItemPanelV3 extends LineItemPanel {

    protected SalesInvoiceLineItem salesline;

    public SalesLineItemPanelV3(JFrame jf) {
        super(jf);
        initComponents();
        init();

    }

    public void init(){
            tprice.addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    action();
                    titemcode.requestFocus();
//                rowToEty();
                    e.consume();
                }
            }
        });


        titemcode.addaction(2, new ActionTask() {

            public boolean action() {
                lineItemLogic();
                return super.action();
            }
        });
        tqty.addaction(0, new ActionTask() {

            @Override
            public boolean action() {
                lineItemLogic();
                return super.action();
            }
        });
        tprice.addaction(0, new ActionTask() {

            public boolean action() {
                lineItemLogic();
                return super.action();
            }
        });

        tunit.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                    action();
                    titemcode.requestFocus();
//                rowToEty();
                    e.consume();
                }

            }
        });
        
        addToFocus(titemcode);
        addToFocus(tdescription);
        addToFocus(tqty);
        addToFocus(tprice);



    }

    /**
     * Creates new form LineItemPanel
     */
    public SalesLineItemPanelV3() {
        super();
        initComponents();


        
    }

    public void lineItemLogic() {
        salesline = panelToEty();
        if (salesline.getItem() != null) {
            if (tunit.hasFocus()) {
                salesline.getSalesPrice();
            }
            salesline.setLineAmount(MathUtil.multiply(salesline.getQty(), salesline.getPrice()));
        }
        etyToPanel();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tdescription = new org.components.controls.CTextField();
        tqty = new org.components.controls.CTextField();
        tprice = new org.components.controls.CTextField();
        tlinetotal = new org.components.controls.CTextField();
        tunit = new com.components.custom.DropDownWithButton<UOM>();
        titemcode = new com.components.custom.TextFieldWithPopUP<Customer>();

        setBackground(new java.awt.Color(247, 230, 130));
        getContentPane().add(tdescription);
        tdescription.setBounds(160, 30, 140, 25);
        getContentPane().add(tqty);
        tqty.setBounds(320, 30, 122, 25);
        getContentPane().add(tprice);
        tprice.setBounds(610, 30, 120, 25);
        getContentPane().add(tlinetotal);
        tlinetotal.setBounds(740, 30, 140, 25);
        getContentPane().add(tunit);
        tunit.setBounds(450, 30, 150, 30);
        getContentPane().add(titemcode);
        titemcode.setBounds(20, 30, 130, 25);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public TextFieldWithPopUP getItemFiled() {
        return titemcode;
    }

    public JTextField getItemdescFiled() {

        return tdescription;
    }

    public DropDownWithButton getUnit() {
        return tunit;
    }

    public SalesInvoiceLineItem panelToEty() {


        salesline.setQty(uiEty.tcToDouble(tqty));
        salesline.setUnit(uiEty.tcToStr(tunit.getTextField()));
        salesline.setDescription(uiEty.tcToStr(tdescription));
        salesline.setPrice(uiEty.tcToDouble(tprice));
        salesline.setLineAmount(uiEty.tcToDouble(tlinetotal));
        //get the uom//         salesline.getItem()
        UOM uom = tunit.getSelectedModel();
        salesline.setUom(uom);
        //ui to ety ..
        return salesline;
    }

    public void getLineItem() {

        salesline = (SalesInvoiceLineItem) lineitem;

    }

    public void etyToPanel() {
        Item it = salesline.getItem();
        if (it != null) {
           titemcode.setTextItem(it.getCode());

        } else {
           titemcode.setTextItem("");

        }
        uiEty.objToUi(tqty, salesline.getQty());
        uiEty.objToUi(tdescription, salesline.getDescription());
        uiEty.objToUi(tlinetotal, salesline.getLineAmount());
        uiEty.objToUi(tunit.getTextField(), salesline.getUnit());
        uiEty.objToUi(tprice, salesline.getPrice());

    }

    public void action() {
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CTextField tdescription;
    private com.components.custom.TextFieldWithPopUP<Customer> titemcode;
    private org.components.controls.CTextField tlinetotal;
    private org.components.controls.CTextField tprice;
    private org.components.controls.CTextField tqty;
    private com.components.custom.DropDownWithButton<UOM> tunit;
    // End of variables declaration//GEN-END:variables
}
//
/**
 
 * 
 * 
 * 
 * 
 * 
 */