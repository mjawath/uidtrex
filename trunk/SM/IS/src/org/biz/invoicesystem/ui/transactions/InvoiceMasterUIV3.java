
/*
 * InvoiceMasterUi.java
 *
 * Created on Dec 2, 2011, 10:27:21 AM
 */
package org.biz.invoicesystem.ui.transactions;

import com.components.custom.ActionTask;
import com.components.custom.PagedPopUpPanel;
import com.components.custom.TextFieldWithPopUP;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.biz.app.ui.util.TableUtil;
import org.biz.app.ui.util.UIEty;
import org.biz.invoicesystem.entity.master.Customer;
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
import org.biz.invoicesystem.ui.transactions.components.SalesLineItemPanelV3;
import org.components.util.Sessions;
import org.components.windows.TabPanelUI;

/*
 * @2011/12/08 jawth - GUIcreation of invoice @2011/12/09 jawath- creation of
 * invoice logic
 */
public class InvoiceMasterUIV3 extends TabPanelUI {

    SalesInvoice invoice;
    List<SalesInvoiceLineItem> lineItems;
    SalesInvoiceService servicedao;
    PagedPopUpPanel popUpComponent;
    SalesLineItemPanelV3 lineItemPanel;
//    DoubleCellEditor de;
//    PagedPopUpPanel cuspop;
    PagedPopUpPanel salesPopup;
    CustomerService custService;
    ItemService itemService;
    List<Customer> listCust;
    List<Item> listItem;
    List<Staff> listStaff;
    StaffService staffService;
    Item currentItem;
    TextFieldWithPopUP itemFiled;

    /**
     * Creates new form InvoiceMasterUi
     */
    public InvoiceMasterUIV3() {
        initComponents();

        init();
        tblInvoice.setPropertiesEL(new String[]{"id", "item.code", "item.description", "qty", "unit", "price", "lineAmount"});
    }

    public void mm() {
    }

    public void init() {
        initPopups();
        invoice = new SalesInvoice();
        lineItems = new ArrayList<SalesInvoiceLineItem>();
        invoice.setLineItems(lineItems);
//                //ADD this list to popupd
        listStaff = new ArrayList<Staff>();

        tblInvoice.setModelCollection(lineItems);

        setnewrow();

//         new Thread(){

//            @Override
//            public void run() {
        itemService = new ItemService();
        listItem = itemService.getDao().getAll();

        servicedao = new SalesInvoiceService();
        itemService = new ItemService();
        //press esc to close 

        custService = new CustomerService();
        listCust = custService.getDao().getAll();
        staffService = new StaffService();

//            }

//         }.start();

        controlPanel1.setCrudController(this);

        addToFocus(cuspop);
        addToFocus(tx);
        addToFocus(td);

        super.init();

    }

    public void initPopups() {
        JFrame jf = SystemStatic.getMainWindow();
//       JDialog jd = new JDialog(jf,false); 
////       jd.setModal(false);
//       jd.setSize(300,300);
//       jd.setVisible(true);
        //

        lineItemPanel = new SalesLineItemPanelV3(jf) {

            @Override
            public List itemSearch(String qry) {

                try {
                    //how about searching the pos invnetory for the items
                return    listItem=itemService.getDao().byCode(qry);
                } catch (Exception e) {

                    e.printStackTrace();
                }
            return null;
            }

            @Override
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
        tblInvoice.setColumnHeader(new String[]{"id", "Item Code", "Description", "Qty", "Unit"      , "Price", "Line Amount"});
        tblInvoice.setPropertiesEL(new String[]{"id", "item.code", "description", "qty", "uom.simbol", "price", "lineAmount"});


        cuspop.setActionActionTask(new ActionTask() {

            @Override
            public void actionCall() {

                String ob = cuspop.getSelectedID();
                Customer cus = null;
                //find Item
                for (Customer it : listCust) {
                    if (ob.equals(it.getId())) {
                        cuspop.setSelectedObject(it);
                        cus = it;
                        invoice.setCustomer(cus);
                        UIEty.objToUi(taddress, cus.getAddress());
                        int row = tblInvoice.getRowCount();
                        tblInvoice.getSelectionModel().setSelectionInterval(row - 1, row - 1);
                        return;
                    }
                }

            }
        });

        cuspop.setSearchActionTask(new ActionTask() {

            @Override
            public void actionCall(Object qry) {
                try {
                    // get the db results
                    //update the ui later 

                    listCust = custService.getDao().byCode(qry.toString());
                    cuspop.setObjectToTable(listCust);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        cuspop.setObjectToTable(listCust);
        cuspop.setPropertiesEL(new String[]{"id", "code", "customerName"});
        cuspop.setTitle(new String[]{"id", "Code", "Customer Name "});
        cuspop.setSelectedProperty("code");
        //        cuspop.setSelectedColumn(1);
//        cuspop.setNextFocusableComponent(tblInvoice);




        tsalesman.setActionTask(new ActionTask() {

            @Override
            public void actionCall() {

                String ob = tsalesman.getSelectedID();
                Staff item = null;
                //find Item
                for (Staff it : listStaff) {
                    if (ob.equals(it.getId())) {
                        salesPopup.setSelectedObject(it);

                        item = it;
                        invoice.setStaff(it);
//                        uiEty.objToUi(cTextArea2, item.getMobile());
                        break;
                    }
                }


            }
        });
        tsalesman.setSearchActionTask(new ActionTask() {

            @Override
            public void actionCall(Object qry) {
                try {
                    listStaff = staffService.getDao().byCode(qry.toString());
                    salesPopup.setObjectToTable(listStaff);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        tsalesman.setObjectToTable(listStaff);
        tsalesman.setPropertiesEL(new String[]{"id", "code", "name"});
        tsalesman.setTitle(new String[]{"id", "Code", "staff Name "});
        tsalesman.setSelectedProperty("code");



    }

    //this method should be refetored
    // move this to the object table level
    public SalesInvoiceLineItem getSelectedLine() {

        String bt = UIEty.colToStrE(tblInvoice, 0);

        for (SalesInvoiceLineItem sli : lineItems) {
            //
            if ((bt == null && sli.getId() == null) || (bt != null && sli.getId() != null) && bt.equals(sli.getId())) {
                return sli;

            }
        }
        return null;
    }

    public void events() {




//        Object[][] xx = {{tcus, tblInvoice}, {tsalesman, ttax}, {ttax, tsubtotal}
//        };
//        ComponentFactory.addKeyAction(xx);




        UIEty.setKeyAction(tblInvoice, new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                System.out.println("delete action .......");
                int sr = tblInvoice.getSelectedRow();
                String id = (String) TableUtil.getSelectedValue(tblInvoice, 0);
                if (id.equals(TableUtil.newRowID)) {
                    return;
                }
                TableUtil.removerow(tblInvoice, sr);
                for (Iterator<SalesInvoiceLineItem> it = lineItems.iterator(); it.hasNext();) {
                    SalesInvoiceLineItem salesInvoiceLineItem = it.next();
                    if (id.equals(salesInvoiceLineItem.getId())) {
                        it.remove();
                    }
                }
            }
        }, KeyEvent.VK_DELETE);


//        tcus.addKeyListener(new KeyAdapter() {
//
//            public void keyPressed(KeyEvent e) {
//                if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_DOWN 
//                         || e.getKeyCode() == KeyEvent.VK_TAB) {
//                    taddress.requestFocus();
//                    System.out.println("|--|");
//                    //if table not selected pls select a row 
//                    
//                }
//            }
//        });

//        taddress.addKeyListener(new KeyAdapter() {
//
//            public void keyPressed(KeyEvent e) {
//                if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_DOWN 
//                         || e.getKeyCode() == KeyEvent.VK_TAB) {
//                    if (tblInvoice.getSelectedRow() < 0) {
//                        tblInvoice.getSelectionModel().setSelectionInterval(0, 0);
//                    }
//                    lineItemPanel.showLineItemPanel();
//                    lineItemPanel.getItemFiled().requestFocus();
//                }
//            }
//        });
//        

    }

    public void save() {
        for (Iterator<SalesInvoiceLineItem> it = lineItems.iterator(); it.hasNext();) {
            SalesInvoiceLineItem si = it.next();
            if (si.getId() == null) {
                it.remove();
            }
        }

//do we have worry about journal posting 
        servicedao.createInventoryJournal(invoice);
        invoice = SalesInvoice.createNewInvoice();
        lineItems = invoice.getLineItems();
        addToTable(lineItems);

        clear();
    }

    private void etyToRow(SalesInvoiceLineItem line) {

        TableUtil.replaceModel(tblInvoice, line, tblInvoice.getSelectedRow());


//this will bemy implementation
        //tableutil.setdatamodle (Customer.class)
        //tableutil .setColumnProperties (new String[]{"name","date#fromated" })//formate is supported 
        //by calling nessasary method calls/invokations throug  EL
        //
        //tableutil.addobject(customer);

    }

    public void getSelectedEty() {
    }

   
    public void setnewrow() {
        TableUtil.addrow(tblInvoice, new Object[]{});
        SalesInvoiceLineItem si = new SalesInvoiceLineItem();
        lineItems.add(si);
    }

    public void addToTable(List<SalesInvoiceLineItem> items) {
        TableUtil.cleardata(tblInvoice);
        if (items == null || items.isEmpty()) {
            return;
        }
        for (SalesInvoiceLineItem line : items) {
            TableUtil.addModelToTable(line, tblInvoice);
        }
        TableUtil.addrow(tblInvoice, new Object[]{});


    }

    public void uiety() {

        invoice.setDocdate(tdate.getDate());
        invoice.setInvNo(UIEty.tcToStr(tinvoiceManualNo));
        invoice.setShop(Shop.getDefaultShop());
        invoice.setDocRefNo(UIEty.tcToStr(tdocref));
        invoice.setSubTotal(UIEty.tcToDouble(tsubtotal));
        invoice.setTexAmount(UIEty.tcToDouble(ttax));
        invoice.setDiscount(UIEty.tcToDouble(tdis));
        invoice.setCashRecieveds(UIEty.tcToDouble(tcashrecieved));
        invoice.setRemarks(UIEty.tcToStr(tremark));
        invoice.setEditeddate(SystemEntityUtil.getSystemDate());
        invoice.setSaveddate(SystemEntityUtil.getSystemDate());
//        invoice.setLineItems(lineItems);

    }

    public void sTotalToUI() {
        UIEty.objToUi(tsubtotal, invoice.getSubTotal());
        UIEty.objToUi(tfinaltotle, invoice.getTotal());
        UIEty.objToUi(tbal, invoice.setTotal());
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        cLabel2 = new org.components.controls.CLabel();
        ttype = new org.components.controls.CComboBox();
        tinvoiceManualNo = new org.components.controls.CTextField();
        cPanel2 = new org.components.containers.CPanel();
        cLabel3 = new org.components.controls.CLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        taddress = new org.components.controls.CTextArea();
        cuspop = new com.components.custom.TextFieldWithPopUP<Customer>();
        tdate = new org.components.controls.CDatePicker();
        cLabel4 = new org.components.controls.CLabel();
        cPanel3 = new org.components.containers.CPanel();
        cLabel6 = new org.components.controls.CLabel();
        cLabel11 = new org.components.controls.CLabel();
        cLabel12 = new org.components.controls.CLabel();
        cLabel13 = new org.components.controls.CLabel();
        cTextField9 = new org.components.controls.CTextField();
        cTextField10 = new org.components.controls.CTextField();
        cPanel5 = new org.components.containers.CPanel();
        cLabel16 = new org.components.controls.CLabel();
        cLabel17 = new org.components.controls.CLabel();
        cLabel18 = new org.components.controls.CLabel();
        cLabel19 = new org.components.controls.CLabel();
        cLabel20 = new org.components.controls.CLabel();
        cTextField12 = new org.components.controls.CTextField();
        tx = new org.components.controls.CTextField();
        td = new org.components.controls.CTextField();
        cTextField15 = new org.components.controls.CTextField();
        tdocref = new org.components.controls.CTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tremark = new org.components.controls.CTextArea();
        cLabel1 = new org.components.controls.CLabel();
        tbal = new org.components.controls.CLabel();
        cLabel21 = new org.components.controls.CLabel();
        controlPanel1 = new com.components.custom.ControlPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblInvoice = new org.components.controls.CTableMaster();
        tsalesman = new com.components.custom.TextFieldWithPopUP<Staff>();

        setLayout(null);

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

        add(cPanel1);
        cPanel1.setBounds(680, 360, 260, 160);

        cLabel2.setText("Salesman");
        cLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        add(cLabel2);
        cLabel2.setBounds(710, 230, 60, 25);

        ttype.setEditable(true);
        ttype.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Invoice", "Quotation", "Credit Note", "Consignment Out" }));
        ttype.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ttypeActionPerformed(evt);
            }
        });
        add(ttype);
        ttype.setBounds(690, 150, 150, 23);

        tinvoiceManualNo.setText("Inv No manually");
        add(tinvoiceManualNo);
        tinvoiceManualNo.setBounds(840, 150, 110, 25);

        cPanel2.setLayout(null);

        cLabel3.setText("Customer");
        cLabel3.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        cPanel2.add(cLabel3);
        cLabel3.setBounds(10, 0, 60, 20);

        taddress.setColumns(20);
        taddress.setRows(10);
        jScrollPane3.setViewportView(taddress);

        cPanel2.add(jScrollPane3);
        jScrollPane3.setBounds(10, 50, 230, 60);
        cPanel2.add(cuspop);
        cuspop.setBounds(60, 10, 130, 25);

        add(cPanel2);
        cPanel2.setBounds(10, 160, 240, 120);
        add(tdate);
        tdate.setBounds(840, 180, 110, 22);

        cLabel4.setText("Date");
        cLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        add(cLabel4);
        cLabel4.setBounds(790, 180, 60, 25);

        cPanel3.setLayout(null);

        cLabel6.setText("Salesman");
        cLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cPanel3.add(cLabel6);
        cLabel6.setBounds(800, 70, 60, 25);

        cLabel11.setText("Last Invoice");
        cLabel11.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        cPanel3.add(cLabel11);
        cLabel11.setBounds(60, 0, 70, 20);

        cLabel12.setText("Invoice Date");
        cLabel12.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        cPanel3.add(cLabel12);
        cLabel12.setBounds(10, 20, 70, 20);

        cLabel13.setText("Invoice Total");
        cLabel13.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        cPanel3.add(cLabel13);
        cLabel13.setBounds(10, 60, 70, 20);

        cTextField9.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        cPanel3.add(cTextField9);
        cTextField9.setBounds(10, 40, 150, 20);

        cTextField10.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        cPanel3.add(cTextField10);
        cTextField10.setBounds(10, 80, 150, 20);

        add(cPanel3);
        cPanel3.setBounds(520, 150, 170, 110);

        cPanel5.setLayout(null);

        cLabel16.setText("Salesman");
        cLabel16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cPanel5.add(cLabel16);
        cLabel16.setBounds(800, 70, 60, 25);

        cLabel17.setText("Credit Limit");
        cLabel17.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        cPanel5.add(cLabel17);
        cLabel17.setBounds(10, 10, 70, 20);

        cLabel18.setText("Total Dues");
        cLabel18.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        cPanel5.add(cLabel18);
        cLabel18.setBounds(10, 40, 70, 20);

        cLabel19.setText("PD Chqs");
        cLabel19.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        cPanel5.add(cLabel19);
        cLabel19.setBounds(10, 60, 70, 20);

        cLabel20.setText("Bounced Chqs");
        cLabel20.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        cPanel5.add(cLabel20);
        cLabel20.setBounds(10, 80, 70, 20);

        cTextField12.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        cPanel5.add(cTextField12);
        cTextField12.setBounds(90, 90, 150, 20);

        tx.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        tx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txActionPerformed(evt);
            }
        });
        cPanel5.add(tx);
        tx.setBounds(90, 10, 150, 20);

        td.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        cPanel5.add(td);
        td.setBounds(90, 40, 150, 20);

        cTextField15.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        cPanel5.add(cTextField15);
        cTextField15.setBounds(90, 60, 150, 20);

        add(cPanel5);
        cPanel5.setBounds(260, 150, 250, 120);
        add(tdocref);
        tdocref.setBounds(720, 200, 70, 25);

        tremark.setColumns(20);
        tremark.setRows(5);
        jScrollPane1.setViewportView(tremark);

        add(jScrollPane1);
        jScrollPane1.setBounds(10, 380, 190, 30);

        cLabel1.setText("Remarks");
        add(cLabel1);
        cLabel1.setBounds(110, 360, 104, 20);

        tbal.setText("");
        add(tbal);
        tbal.setBounds(160, 480, 190, 40);

        cLabel21.setText("Total :");
        add(cLabel21);
        cLabel21.setBounds(110, 480, 104, 25);
        add(controlPanel1);
        controlPanel1.setBounds(110, 420, 480, 40);

        tblInvoice.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Item Code", "Description", "Qty", "Price", "Line Amount"
            }
        ));
        jScrollPane4.setViewportView(tblInvoice);

        add(jScrollPane4);
        jScrollPane4.setBounds(50, 20, 810, 120);

        tsalesman.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tsalesmanActionPerformed(evt);
            }
        });
        add(tsalesman);
        tsalesman.setBounds(770, 240, 130, 25);
    }// </editor-fold>//GEN-END:initComponents

    private void ttypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ttypeActionPerformed

        System.out.println("evt " + evt);


    }//GEN-LAST:event_ttypeActionPerformed

    private void txActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txActionPerformed

    private void tsalesmanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tsalesmanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tsalesmanActionPerformed

    public void clear() {
        invoice = SalesInvoice.createNewInvoice();
        lineItems = invoice.getLineItems();
        addToTable(lineItems);
        cuspop.clear();
        tsalesman.clear();
        ttax.setText("");
        tsubtotal.setText("");
        tcashrecieved.setText("");
        tfinaltotle.setText("");
        tinvoiceManualNo.setText("");
        tdis.setText("");
        tsalesman.setText("");
        setnewrow();
//        lineItemPanel.getUnit().setModel(new String[]{});

    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CLabel cLabel1;
    private org.components.controls.CLabel cLabel10;
    private org.components.controls.CLabel cLabel11;
    private org.components.controls.CLabel cLabel12;
    private org.components.controls.CLabel cLabel13;
    private org.components.controls.CLabel cLabel15;
    private org.components.controls.CLabel cLabel16;
    private org.components.controls.CLabel cLabel17;
    private org.components.controls.CLabel cLabel18;
    private org.components.controls.CLabel cLabel19;
    private org.components.controls.CLabel cLabel2;
    private org.components.controls.CLabel cLabel20;
    private org.components.controls.CLabel cLabel21;
    private org.components.controls.CLabel cLabel3;
    private org.components.controls.CLabel cLabel4;
    private org.components.controls.CLabel cLabel5;
    private org.components.controls.CLabel cLabel6;
    private org.components.controls.CLabel cLabel7;
    private org.components.controls.CLabel cLabel8;
    private org.components.controls.CLabel cLabel9;
    private org.components.containers.CPanel cPanel1;
    private org.components.containers.CPanel cPanel2;
    private org.components.containers.CPanel cPanel3;
    private org.components.containers.CPanel cPanel5;
    private org.components.controls.CTextField cTextField10;
    private org.components.controls.CTextField cTextField12;
    private org.components.controls.CTextField cTextField15;
    private org.components.controls.CTextField cTextField9;
    private com.components.custom.ControlPanel controlPanel1;
    private com.components.custom.TextFieldWithPopUP<Customer> cuspop;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private org.components.controls.CTextArea taddress;
    private org.components.controls.CLabel tbal;
    private org.components.controls.CTableMaster tblInvoice;
    private org.components.controls.CTextField tcashrecieved;
    private org.components.controls.CTextField td;
    private org.components.controls.CDatePicker tdate;
    private org.components.controls.CTextField tdis;
    private org.components.controls.CTextField tdocref;
    private org.components.controls.CTextField tfinaltotle;
    private org.components.controls.CTextField tinvoiceManualNo;
    private org.components.controls.CTextArea tremark;
    private com.components.custom.TextFieldWithPopUP<Staff> tsalesman;
    private org.components.controls.CTextField tsubtotal;
    private org.components.controls.CTextField ttax;
    private org.components.controls.CComboBox ttype;
    private org.components.controls.CTextField tx;
    // End of variables declaration//GEN-END:variables

    public void updateEntityUI() {
    }

    public String getTabName() {
        return "Invoice Master";
    }

    public JPanel getJPanel() {
        return this;
    }
}

/*

 simpliflying the GUI usage--------Keep the model with the component policy 
 * 1) customer selection :-
 *              * user search for specific attribute
 *              * press enter selects the customer ::::: note why do we have to bring the
 *              * selected model to the upper level ..keep it in the componnet level
 *              * if user types any stupid values  clear the selection of the model and textfield
 *
2)line item entry
* if table selection changes
*       model value should be set to floating panel  FP
*           and it should be shown on the top of thet row
*
* if user changes the row while data entry
*       values of the FP should be added to collection of the table
*           and row should be refreshed .
*
* if user complets the data entry then
*       validate the model
*       warn the user
*       update the collection with dirty value -----***should be checked  ****
*
*

 */