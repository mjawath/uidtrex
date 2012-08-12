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
import invoicingsystem.LineItemPanel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JTextField;
import org.biz.app.ui.util.UIEty;
import org.biz.invoicesystem.entity.master.Item;
import org.biz.invoicesystem.entity.transactions.SalesInvoiceLineItem;
import org.components.controls.CTextField;

/**
 *
 * @author nnjj
 */
public class PosSalesLineItemPanel extends LineItemPanel {

    protected SalesInvoiceLineItem salesline;

    public PosSalesLineItemPanel(JFrame jf) {
        super(jf);
        initComponents();
        init();



    }

    /**
     * Creates new form LineItemPanel
     */
    public PosSalesLineItemPanel() {
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



        tprice.addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
//                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//                    action();
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
                PosSalesLineItemPanel.this.action();
                return super.action();
            }
        });

        addToFocus(titemcode);
        addToFocus(tqty);
        addToFocus(tprice);
        addToFocus(titemcode);

    }

    public void lineItemLogic() {
        panelToEty(salesline );
        if (salesline.getItem() != null) {
            salesline.setLineAmount(MathUtil.multiply(salesline.getQty(), salesline.getPrice()));
        }
        etyToPanel(salesline);// TODO : change this according to the user seleection
        //provide tseparate method for line logic
    }

//    public void moveNextFocus() {
//        Component com = getFocusOwner();
//        if (com != null) {
//            int id = al.indexOf(com);
//            if (id > - 1 && id < al.size() - 1) {
//                Component comp = (Component) al.get(++id);
////                comp.requestFocus();
//            }
//        }
//    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titemcode = new org.components.controls.CTextField();
        tdescription = new org.components.controls.CTextField();
        tqty = new org.components.controls.CTextField();
        tprice = new org.components.controls.CTextField();
        tlinetotal = new org.components.controls.CTextField();

        setBackground(new java.awt.Color(247, 230, 130));
        getContentPane().add(titemcode);
        titemcode.setBounds(20, 30, 122, 25);
        getContentPane().add(tdescription);
        tdescription.setBounds(160, 30, 140, 25);
        getContentPane().add(tqty);
        tqty.setBounds(320, 30, 122, 25);
        getContentPane().add(tprice);
        tprice.setBounds(470, 30, 120, 25);
        getContentPane().add(tlinetotal);
        tlinetotal.setBounds(610, 30, 140, 25);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public CTextField getItemFiled() {
        return titemcode;
    }

    public JTextField getItemdescFiled() {

        return tdescription;
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
            UIEty.objToUi(titemcode, it.getCode());
            UIEty.objToUi(tdescription, salesline.getDescription());
//
        } else {
            UIEty.objToUi(titemcode, "");
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

    public void selectEtyToPanel() {
        Item it = salesline.getItem();
        if (it != null) {
            UIEty.objToUi(titemcode, it.getCode());
//
        } else {
            UIEty.objToUi(titemcode, "");
        }
        UIEty.objToUi(tqty, salesline.getQty());
        UIEty.objToUi(tdescription, salesline.getDescription());
        UIEty.objToUi(tlinetotal, salesline.getLineAmount());
        UIEty.objToUi(tprice, salesline.getPrice());

    }

    public void action() {
    }

    @Override
    public void pack() {
        super.pack();
    }

    public void clear() {
        tdescription.setText("");
        titemcode.setText("");
        tlinetotal.setText("");
        tprice.setText("");
        tqty.setText("");
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CTextField tdescription;
    private org.components.controls.CTextField titemcode;
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