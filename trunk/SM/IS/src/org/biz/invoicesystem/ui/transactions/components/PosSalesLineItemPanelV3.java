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
import com.components.custom.TextFieldWithPopUP;
import invoicingsystem.LineItemPanel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JFrame;
import org.biz.app.ui.util.UIEty;
import org.biz.invoicesystem.entity.master.Item;
import org.biz.invoicesystem.entity.transactions.SalesInvoiceLineItem;

/**
 *
 * @author mjawath
 */
public class PosSalesLineItemPanelV3 extends LineItemPanel {

    protected SalesInvoiceLineItem salesline;

    public PosSalesLineItemPanelV3(JFrame jf) {
        super(jf);
        initComponents();
        init();



    }

    /**
     * Creates new form LineItemPanel
     */
    public PosSalesLineItemPanelV3() {
        super();
        initComponents();



//               Object[][] xx = {{titemcode, tblInvoice}, {tsalesman, ttax}, {ttax, tsubtotal}
//        };
//        ComponentFactory.addKeyAction(xx);
        titemcode.addaction(4, new ActionTask() {

            @Override
            public boolean action() {
                System.out.println("ddddddddddffffddddddddddd");
                return true;
            }
        });

        titemcode.addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
            }
        });

//        tunit.setActionTask(new ActionTask(tprice));

        tprice.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
                lineItemLogic();
            }
        });

    }

    public void init() {

        salesline = new SalesInvoiceLineItem();

        tprice.addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
//                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//                    lineAddAction();
//                    titemcode.requestFocus();
////                rowToEty();
//                    e.consume();
//                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println(tprice.getText());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                lineItemLogic();

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
                PosSalesLineItemPanelV3.this.lineAddAction();
                return super.action();
            }
        });




        titemcode.setActionActionTask(new ActionTask() {

            @Override
            public void actionCall() {
                itemAction();
            }
        });
        titemcode.setSearchActionTask(new ActionTask() {

            @Override
            public void actionCall(Object st) {
                List lst = itemSearch(st.toString());
                titemcode.getPagedPopUpPanel().setList(lst);
            }
        });

        titemcode.setPropertiesEL(new String[]{"id", "code", "description"});
        titemcode.setTitle(new String[]{"id", "Code", "Description"});
        titemcode.setSelectedProperty("code");

        addToFocus(titemcode);
        addToFocus(tqty);
        addToFocus(tprice);
        addToFocus(titemcode);

    }

    public List itemSearch(String qry) {
        return null;
    }

    public void itemAction() {
        SalesInvoiceLineItem sili = this.getSalesline();
        Item item = this.getItemcode().getSelectedObject();
        sili.setItem(item);
        sili.setDescription(item.getDescription());
        //get sales price for pos
        sili.setPrice(item.getSalesPrice());
//                lineItemPanel.panelToEty(seil);
        this.etyToPanel(sili);
//                addsales(sili);
        setSalesline(sili);
        lineItemLogic();


    }

    public void lineItemLogic() {
        panelToEty(salesline);
        if (salesline.getItem() != null) {
            salesline.setLineAmount(MathUtil.multiply(salesline.getQty(), salesline.getPrice()));
        }
        etyToPanel(salesline);// TODO : change this according to the user seleection
        //provide tseparate method for line logic
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tdescription = new org.components.controls.CTextField();
        tqty = new org.components.controls.CTextField();
        tprice = new org.components.controls.CTextField();
        tlinetotal = new org.components.controls.CTextField();
        titemcode = new com.components.custom.TextFieldWithPopUP<Item>();

        setBackground(new java.awt.Color(247, 230, 130));
        getContentPane().add(tdescription);
        tdescription.setBounds(160, 30, 140, 25);
        getContentPane().add(tqty);
        tqty.setBounds(320, 30, 122, 25);
        getContentPane().add(tprice);
        tprice.setBounds(470, 30, 120, 25);
        getContentPane().add(tlinetotal);
        tlinetotal.setBounds(610, 30, 140, 25);
        getContentPane().add(titemcode);
        titemcode.setBounds(20, 30, 130, 25);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public TextFieldWithPopUP<Item> getItemcode() {
        return titemcode;
    }

    
    public SalesInvoiceLineItem panelToEty(SalesInvoiceLineItem salesline) {


        salesline.setQty(UIEty.tcToDouble(tqty));
        salesline.setDescription(UIEty.tcToStr(tdescription));
        salesline.setPrice(UIEty.tcToDouble(tprice));
        salesline.setLineAmount(UIEty.tcToDouble(tlinetotal));

        //get the uom//         salesline.getItem()

        //ui to ety ..
        return salesline;
    }

    public void getLineItem() {

        salesline = (SalesInvoiceLineItem) super.salesline;

    }

    public void etyToPanel(SalesInvoiceLineItem salesline) {
        Item it = salesline.getItem();
        if (it != null) {
            titemcode.setTextItem(it.getCode());
            UIEty.objToUi(tdescription, salesline.getDescription());
//
        } else {
            titemcode.setTextItem("");
            UIEty.objToUi(tdescription, "");
        }

        if (!tqty.hasFocus()) {
            UIEty.objToUi(tqty, salesline.getQty());
        }
        if (!tdescription.hasFocus()) {
            UIEty.objToUi(tdescription, salesline.getDescription());
        }
        if (!tlinetotal.hasFocus()) {
            UIEty.objToUi(tlinetotal, salesline.getLineAmount());
        }
        if (!tprice.hasFocus()) {
            UIEty.objToUi(tprice, salesline.getPrice());
        }

    }

    public SalesInvoiceLineItem getSalesline() {
        return salesline;
    }

    public void setSalesline(SalesInvoiceLineItem salesline) {
        this.salesline = salesline;
    }

    public void selectedEtyToPanel() {
        if (salesline == null) {
            return;
        }
        Item it = salesline.getItem();
        if (it != null) {
            titemcode.setTextItem(it.getCode());

//
        } else {
            titemcode.setTextItem("");
        }
        UIEty.objToUi(tqty, salesline.getQty());
        UIEty.objToUi(tdescription, salesline.getDescription());
        UIEty.objToUi(tlinetotal, salesline.getLineAmount());
        UIEty.objToUi(tprice, salesline.getPrice());

    }

    public void lineAddAction() {
    }

    public void selectEty() {
        salesline = (SalesInvoiceLineItem) jt.getSelectedObject();
        clear();
        selectedEtyToPanel();
    }

    public void clear() {
        tdescription.setText("");
        titemcode.setTextItem("");
        tlinetotal.setText("");
        tprice.setText("");
        tqty.setText("");
        setSalesline(new SalesInvoiceLineItem());
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CTextField tdescription;
    private com.components.custom.TextFieldWithPopUP<Item> titemcode;
    private org.components.controls.CTextField tlinetotal;
    private org.components.controls.CTextField tprice;
    private org.components.controls.CTextField tqty;
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