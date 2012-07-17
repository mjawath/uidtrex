/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.invoicesystem.ui.transactions;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import org.biz.app.ui.util.TableUtil;
import org.biz.app.ui.util.uiEty;
import org.biz.invoicesystem.entity.master.Item;
import org.biz.invoicesystem.entity.master.Shop;
import org.biz.invoicesystem.entity.master.Staff;
import org.biz.invoicesystem.entity.transactions.SalesInvoice;
import org.biz.invoicesystem.entity.transactions.SalesInvoiceLineItem;
import org.biz.invoicesystem.master.ui.SystemStatic;
import org.biz.invoicesystem.service.master.CustomerService;
import org.biz.invoicesystem.service.master.ItemService;
import org.biz.invoicesystem.service.master.StaffService;
import org.biz.invoicesystem.service.transactions.SalesInvoiceService;
import org.biz.invoicesystem.system.SystemEntityUtil;
import org.biz.invoicesystem.ui.transactions.components.PosSalesLineItemPanelV3;
import org.components.windows.TabPanelUI;

/**
 *
 * @author d
 */
public class PosInvoiceV3 extends TabPanelUI {

    SalesInvoice invoice;
    List<SalesInvoiceLineItem> lineItems;
    SalesInvoiceService servicedao;
    PosSalesLineItemPanelV3 lineItemPanel;
    private CustomerService custService;
    private ItemService itemService;
    List<Staff> listStaff;
    StaffService staffService;
    Item currentItem;
    SalesInvoiceLineItem invoiceLine;

    /**
     * Creates new form PosInvoiceV3
     */
    public PosInvoiceV3() {
        initComponents();
        init();
    }

    @Override
    public void init() {
        JFrame jf = SystemStatic.getMainWindow();//get the main window from the statics

        lineItemPanel = new PosSalesLineItemPanelV3(jf) {

//            public SalesInvoiceLineItem panelToEty() {
//                super.panelToEty(seil);
//                addsales(seil);
//                return seil;
//            }
////
//            public void selectEty() {
//                SalesInvoiceLineItem sl = PosInvoiceV3.this.getSelectedLine();
//
//                if (sl == null)  return;
//                salesline = sl;
//                clear();
//                selectedEtyToPanel();
//            }

            @Override
            public List itemSearch(String qry) {

                try {
                    //how about searching the pos invnetory for the items
                return    itemService.getDao().byCode(qry);
                } catch (Exception e) {

                    e.printStackTrace();
                }
            return null;
            }

            @Override
//            public void itemAction() {

//                SalesInvoiceLineItem sili = lineItemPanel.getSalesline();
//                Item item = lineItemPanel.getItemcode().getSelectedObject();
//                sili.setItem(item);
//                sili.setDescription(item.getDescription());
//                //get sales price for pos
//                sili.setPrice(item.getSalesPrice());
////                lineItemPanel.panelToEty(seil);
//                lineItemPanel.etyToPanel(sili);
//                addsales(sili);
//                lineItemPanel.setSalesline(sili);
//                lineItemPanel.lineItemLogic();
//            }

            public void lineAddAction() {
                panelToEty(salesline);
                Object id = salesline.getId();
                if (id == null) {
                    salesline.setId(System.currentTimeMillis() + "tt");
                }

                etyToRow(salesline);
                uiety();//to set invoice properties
                invoice.setTotal();
                sTotalToUI();

                //check lineittem id is null then if its need a new row insert a new row
                //or move to next row
            lineItemPanel.clear();
            if (id == null) {
                    setnewrow();
                    int row = tblInvoice.getRowCount();
                    tblInvoice.getSelectionModel().setSelectionInterval(row - 1, row - 1);

                }

            }
        };

        lineItemPanel.setTable(tblInvoice);

        tblInvoice.setPropertiesEL(new String[]{"id", "item.code", "description", "qty", "price", "lineAmount"});
////////////seeking 
        //1 apply u -fullly into it
        //2 primary activity(desire) wanted otherthen anything else
        //3 patient
        //4 see things throu dont give up,dont be a quiter

        //5 direction of teacher
        // 6 sacrifise

        invoice = new SalesInvoice();
        lineItems = new ArrayList<SalesInvoiceLineItem>();
        invoice.setLineItems(lineItems);
        invoiceLine = new SalesInvoiceLineItem();
        tblInvoice.setModelCollection(lineItems);
        itemService = new ItemService();

        servicedao = new SalesInvoiceService();
        itemService = new ItemService();

        custService = new CustomerService();

        staffService = new StaffService();
        listStaff = new ArrayList<Staff>();


        setnewrow();

    }

    public SalesInvoiceLineItem getSelectedLine() {

        String bt = uiEty.colToStrE(tblInvoice, 0);

        for (SalesInvoiceLineItem sli : lineItems) {
            //
            if ((bt == null && sli.getId() == null) || (bt != null && sli.getId() != null) && bt.equals(sli.getId())) {
                return sli;

            }
        }
        return null;
    }

    private SalesInvoiceLineItem addsales(SalesInvoiceLineItem lineItem) {
        SalesInvoiceLineItem sl = null;
        int sx = -1;
        int x = -1;
        for (SalesInvoiceLineItem sil : lineItems) {
            x++;
            if ((lineItem.getId() == null && sil.getId() == null) || lineItem.getId() != null
                    && sil.getId() != null && lineItem.getId().equals(sil.getId())) {
                sx = x;
                sl = sil;
                break;
            }
        }
        if (sx > -1) {
            lineItems.remove(sx);
            lineItems.add(sx, lineItem);
        }
        return sl;
    }

    public SalesInvoiceLineItem rowToEty() {

        String bt = uiEty.colToStrE(tblInvoice, 0);
        SalesInvoiceLineItem lineItem = new SalesInvoiceLineItem();
        lineItem.setId(uiEty.colToStrE(tblInvoice, 0));
//        lineItem.setItem(currentItem);
        lineItem.setDescription(uiEty.colToStrE(tblInvoice, 2));
        lineItem.setUnit(uiEty.colToStr(tblInvoice, 4));
        lineItem.setQty(uiEty.colToDbl(tblInvoice, 3));
        lineItem.setPrice(uiEty.colToDbl(tblInvoice, 5));
        lineItem.setLineAmount(uiEty.colToDbl(tblInvoice, 6));
        for (SalesInvoiceLineItem sli : lineItems) {
            if (bt.equals(sli.getId())) {
                lineItem.setItem(sli.getItem());
                break;
            }
        }


        return lineItem;

    }

    public void uiety() {

//        invoice.setDocdate(tdate.getDate());
//        invoice.setInvNo(uiEty.tcToStr(tinvoiceManualNo));
        invoice.setShop(Shop.getDefaultShop());
//        invoice.setDocRefNo(uiEty.tcToStr(tdocref));
        invoice.setSubTotal(uiEty.tcToDouble(tsubtotal));
        invoice.setTexAmount(uiEty.tcToDouble(ttax));
        invoice.setDiscount(uiEty.tcToDouble(tdis));
        invoice.setCashRecieveds(uiEty.tcToDouble(tcashrecieved));
//        invoice.setRemarks(uiEty.tcToStr(tremark));
        invoice.setEditeddate(SystemEntityUtil.getSystemDate());
        invoice.setSaveddate(SystemEntityUtil.getSystemDate());
//        invoice.setLineItems(lineItems);

    }

    private void etyToRow(SalesInvoiceLineItem line) {

        tblInvoice.replaceModel(line);
    }

    public void sTotalToUI() {
        uiEty.objToUi(tsubtotal, invoice.getSubTotal());
        uiEty.objToUi(tfinaltotle, invoice.getTotal());
//        uiEty.objToUi(tbal, invoice.setTotal());
    }

    public void setnewrow() {
        TableUtil.addrow(tblInvoice, new Object[]{});
        SalesInvoiceLineItem si = new SalesInvoiceLineItem();
        lineItems.add(si);
    }




    public void addToTable(List<SalesInvoiceLineItem> items) {

        tblInvoice.modelToTable(items);
        tblInvoice.addrow(new Object[]{});
    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblInvoice = new org.components.controls.CTableMaster();
        cPanel1 = new org.components.containers.CPanel();
        cLabel5 = new org.components.controls.CLabel();
        cLabel7 = new org.components.controls.CLabel();
        cLabel8 = new org.components.controls.CLabel();
        cLabel9 = new org.components.controls.CLabel();
        cLabel10 = new org.components.controls.CLabel();
        tfinaltotle = new org.components.controls.CTextField();
        tsubtotal = new org.components.controls.CTextField();
        ttax = new org.components.controls.CTextField();
        tdis = new org.components.controls.CTextField();
        tcashrecieved = new org.components.controls.CTextField();
        cLabel15 = new org.components.controls.CLabel();
        crudcontrolPanel = new com.components.custom.ControlPanel();

        tblInvoice.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Item Code", "Description", "Qty", "Price", "Line Amount"
            }
        ));
        jScrollPane1.setViewportView(tblInvoice);

        cPanel1.setLayout(null);

        cLabel5.setText("Salesman");
        cLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cPanel1.add(cLabel5);
        cLabel5.setBounds(800, 70, 60, 25);

        cLabel7.setText("Total");
        cLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cPanel1.add(cLabel7);
        cLabel7.setBounds(10, 10, 70, 20);

        cLabel8.setText("Tax");
        cLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cPanel1.add(cLabel8);
        cLabel8.setBounds(10, 40, 70, 20);

        cLabel9.setText("Discount");
        cLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cPanel1.add(cLabel9);
        cLabel9.setBounds(10, 60, 70, 20);

        cLabel10.setText("Final Total");
        cLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cPanel1.add(cLabel10);
        cLabel10.setBounds(10, 90, 70, 20);

        tfinaltotle.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        cPanel1.add(tfinaltotle);
        tfinaltotle.setBounds(90, 90, 150, 20);

        tsubtotal.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        cPanel1.add(tsubtotal);
        tsubtotal.setBounds(90, 10, 150, 20);

        ttax.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        cPanel1.add(ttax);
        ttax.setBounds(90, 40, 150, 20);

        tdis.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        cPanel1.add(tdis);
        tdis.setBounds(90, 60, 150, 20);

        tcashrecieved.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        cPanel1.add(tcashrecieved);
        tcashrecieved.setBounds(90, 120, 150, 20);

        cLabel15.setText("Recieved");
        cLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cPanel1.add(cLabel15);
        cLabel15.setBounds(10, 120, 70, 20);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 880, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 266, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(crudcontrolPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(306, 306, 306))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(crudcontrolPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32))))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CLabel cLabel10;
    private org.components.controls.CLabel cLabel15;
    private org.components.controls.CLabel cLabel5;
    private org.components.controls.CLabel cLabel7;
    private org.components.controls.CLabel cLabel8;
    private org.components.controls.CLabel cLabel9;
    private org.components.containers.CPanel cPanel1;
    private com.components.custom.ControlPanel crudcontrolPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private org.components.controls.CTableMaster tblInvoice;
    private org.components.controls.CTextField tcashrecieved;
    private org.components.controls.CTextField tdis;
    private org.components.controls.CTextField tfinaltotle;
    private org.components.controls.CTextField tsubtotal;
    private org.components.controls.CTextField ttax;
    // End of variables declaration//GEN-END:variables

    @Override
    public String getTabName() {
        return "Pos invoice V3";
    }
}
