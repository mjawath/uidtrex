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
import invoicingsystem.LineItemPanel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JTextField;
import org.biz.app.ui.util.uiEty;
import org.biz.invoicesystem.entity.master.Item;
import org.biz.invoicesystem.entity.master.UOM;
import org.biz.invoicesystem.entity.transactions.SalesInvoiceLineItem;
import org.components.controls.CTextField;

/**
 *
 * @author nnjj
 */
public class SalesLineItemPanel extends LineItemPanel {

    
    protected SalesInvoiceLineItem salesline;

    public SalesLineItemPanel(JFrame jf) {
        super(jf);
        initComponents();

   
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



//        titemcode.addActionListener(new ActionTask(tdescription));
//        tdescription.addActionListener(new ActionTask(tdescription));
        //order of tabbing

//        Object [][] xx=new Object[][]{{titemcode,new ActionTask()},{tdescription},{tqty}};

//        titemcode.addKeyListener(new KeyAdapter() {
//
//            @Override
//            public void keyPressed(KeyEvent e) {
//                super.keyPressed(e);
//            }
//        
//        });        



      /*  tdescription.setInputVerifier(new CInputVerifier(tqty){
            public boolean action() {
                lineItemLogic();
                return super.action();
            }
        });
        tqty.setInputVerifier(new CInputVerifier(tunit){
            public boolean action() {
                lineItemLogic();
                return super.action();
            }
        });
        tprice.setInputVerifier(new CInputVerifier(tprice){
            public boolean action() {
                lineItemLogic();
                return super.action();
            }
        });
*/
        
        titemcode.nextFocusableComponent(tdescription);
        tdescription.nextFocusableComponent(tqty);
        tqty.nextFocusableComponent(tunit);
//        tunit.nextFocusableComponent(tprice);
        
        tprice.nextFocusableComponent(titemcode);
        titemcode.addaction(2, new ActionTask(){           
            public boolean action() {
                    lineItemLogic();
                return super.action();
            }        
        });
        tqty.addaction(0, new ActionTask(){

            @Override
            public boolean action() {
                lineItemLogic();
                return super.action();
            }
        
        });
        tprice.addaction(0, new ActionTask(){
            public boolean action() {
                lineItemLogic();
                return super.action();
            }        
        });
        
    }

    /** Creates new form LineItemPanel */
    public SalesLineItemPanel() {
        super();
        initComponents();

      
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

//               Object[][] xx = {{titemcode, tblInvoice}, {tsalesman, ttax}, {ttax, tsubtotal}
//        };
//        ComponentFactory.addKeyAction(xx);
        titemcode.addaction(4, new ActionTask(){

                    @Override
                    public boolean action() {
                        System.out.println("ddddddddddffffddddddddddd");
                    return true;
                    }
                
                } );
        
        titemcode.addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
               
            }
        });

//        tunit.setActionTask(new ActionTask(tprice));



    }

    public void lineItemLogic() {
        salesline= panelToEty();
        if (salesline.getItem() != null) {
            if (tunit.hasFocus()) {
                salesline.getSalesPrice();
            }
            salesline.setLineAmount(MathUtil.multiply(salesline.getQty(), salesline.getPrice()));
        }
        etyToPanel();
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

    protected void dialogInit() {
        super.dialogInit();
//        initComponents();
//        this.setModalityType(ModalityType.MODELESS);
//        setFocusableWindowState(false);
//        setUndecorated(true);
////        setLocationRelativeTo(fr);
//        getRootPane().setOpaque(false);
////        AWTUtilities.setWindowOpacity(this, 0.8f);
//        setFocusableWindowState(true);
//        setPreferredSize(new Dimension(900, 100));
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titemcode = new org.components.controls.CTextField();
        tdescription = new org.components.controls.CTextField();
        tqty = new org.components.controls.CTextField();
        tprice = new org.components.controls.CTextField();
        tlinetotal = new org.components.controls.CTextField();
        tunit = new com.components.custom.DropDownWithButton<UOM>();

        setBackground(new java.awt.Color(247, 230, 130));
        getContentPane().add(titemcode);
        titemcode.setBounds(20, 30, 122, 25);
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

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public CTextField getItemFiled() {
        return titemcode;
    }

    public JTextField getItemdescFiled() {

        return tdescription;
    }

public DropDownWithButton getUnit(){
return tunit;
}
    
public SalesInvoiceLineItem panelToEty() {


        salesline.setQty(uiEty.tcToDouble(tqty));
        salesline.setUnit(uiEty.tcToStr(tunit.getTextField()));
        salesline.setDescription(uiEty.tcToStr(tdescription));
        salesline.setPrice(uiEty.tcToDouble(tprice));
        salesline.setLineAmount(uiEty.tcToDouble(tlinetotal));        
        //get the uom//         salesline.getItem()
        UOM uom=tunit.getSelectedModel();
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
            uiEty.objToUi(titemcode, it.getCode());

        } else {
            uiEty.objToUi(titemcode, "");
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
    private org.components.controls.CTextField titemcode;
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