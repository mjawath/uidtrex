
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
import org.biz.app.ui.util.uiEty;
import org.biz.invoicesystem.entity.master.Customer;
import org.biz.invoicesystem.entity.master.Item;
import org.biz.invoicesystem.entity.master.Shop;
import org.biz.invoicesystem.entity.master.Staff;
import org.biz.invoicesystem.entity.transactions.SalesInvoice;
import org.biz.invoicesystem.entity.transactions.SalesInvoiceLineItem;
import org.biz.invoicesystem.service.master.CustomerService;
import org.biz.invoicesystem.service.master.ItemService;
import org.biz.invoicesystem.service.master.StaffService;
import org.biz.invoicesystem.service.transactions.SalesInvoiceService;
import org.biz.invoicesystem.system.SystemEntityUtil;
import org.biz.invoicesystem.ui.transactions.components.SalesLineItemPanelV3;
import org.components.util.Sessions;
import org.components.windows.TabPanelUI;

/*
 * @2011/12/08 jawth -  GUIcreation  of invoice
 * @2011/12/09 jawath-  creation  of invoice logic 
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
    SalesInvoiceLineItem seil;
    TextFieldWithPopUP itemFiled;

    /** Creates new form InvoiceMasterUi */
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
        JFrame jf = (JFrame) Sessions.getObj("mainui");
//       JDialog jd = new JDialog(jf,false); 
////       jd.setModal(false);
//       jd.setSize(300,300);
//       jd.setVisible(true);
        //
        lineItemPanel = new SalesLineItemPanelV3(jf) {

            public SalesInvoiceLineItem panelToEty() {
                SalesInvoiceLineItem sl = super.panelToEty();
                //validated line
                //update table row
                //add to list
                // TODO get uom ????/*/done using the generic 
                //what should be done to the uom  comparison
                //it also done
                addsales(sl);
                //replace selected row
                //                addToTable(lineItems);

                return sl;
            }

            public void selectEty() {
                //get selected row 
                //get line item from list 
                //set seleceted object to lineitempanel
                SalesInvoiceLineItem sl = InvoiceMasterUIV3.this.getSelectedLine();
                if (sl == null) {
                    return;
                }
                salesline = sl;
                loadUnit(sl.getItem());
//                itemSelectionPopup.setPopDesable(true);
                etyToPanel();
//                itemSelectionPopup.setPopDesable(false);

            }

            public void action() {
                SalesInvoiceLineItem sl = super.panelToEty();
                Object id = sl.getId();
                if (id == null) {
                    sl.setId(System.currentTimeMillis() + "tt");
                }


                etyToRow(sl);
                uiety();
                invoice.setTotal();
                sTotalToUI();

                //check lineittem id is null then if its need a new row insert a new row  
                //or move to next row

                if (id == null) {
                    setnewrow();
                    int row = tblInvoice.getRowCount();
                    tblInvoice.getSelectionModel().setSelectionInterval(row - 1, row - 1);

                }

            }
        };
        itemFiled = lineItemPanel.getItemFiled();

        lineItemPanel.setTable(tblInvoice);
        tblInvoice.setColumnHeader(new String[]{"id", "Item Code", "Description", "Qty", "Unit", "Price", "Line Amount"});
        tblInvoice.setPropertiesEL(new String[]{"id", "item.code", "description", "qty", "Unit", "price", "lineAmount"});

        lineItemPanel.setTextField(itemFiled);


        itemFiled.setTitle(new String[]{"id", "Item Code", "Description", "Qty", "Unit", "Price", "Line Amount"});
        itemFiled.setPropertiesEL(new String[]{"id", "code", "description"});
        itemFiled.setSelectedProperty("code");
        //        itemSelectionPopup.setSelectedColumn(1);
        itemFiled.setActionActionTask(new ActionTask() {

            @Override
            public void actionCall() {
                Item item = (Item) itemFiled.getSelectedObject();
//                Item item = null;
//                //find Item
//                for (Item it : listItem) {
//                    if (ob.equals(it.getId())) {
//                        setSelectedObject(it);
//                        item = it;
//                        break;
//                    }
//                }
                loadUnit(item);
                SalesInvoiceLineItem lineItem = lineItemPanel.panelToEty();
                //                if current row valid
                lineItem.setItem(item);
                //                replace row
                addsales(lineItem);

                lineItemPanel.lineItemLogic();


            }
        });

        itemFiled.setSearchActionTask(new ActionTask() {

            @Override
            public void actionCall(Object st) {
                try {

                    listItem = itemService.getDao().byCode(st.toString());
//                    lineItemPanel.getItemFiled().setList(listItem);
                    itemFiled.setObjectToTable(listItem);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });


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
                        uiEty.objToUi(taddress, cus.getAddress());
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




        salesPopup = new PagedPopUpPanel(tsalesman) {

//            @Override
//            public Object[] data(Object item) {
//                Staff st = (Staff) item;
//                return new Object[]{st.getId(), st.getCode(), st.getName()};
//            }
            @Override
            public void action() {

                String ob = salesPopup.getSelectedID();
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

            @Override
            public void search(String qry) {
                try {
                    listStaff = staffService.getDao().byCode(qry);
                    salesPopup.setObjectToTable(listStaff);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        salesPopup.setPropertiesEL(new String[]{"id", "code", "name"});



    }

    public void events() {




//        Object[][] xx = {{tcus, tblInvoice}, {tsalesman, ttax}, {ttax, tsubtotal}
//        };
//        ComponentFactory.addKeyAction(xx);




        uiEty.setKeyAction(tblInvoice, new AbstractAction() {

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


        servicedao.createInventoryJournal(invoice);
        invoice = SalesInvoice.createNewInvoice();
        lineItems = invoice.getLineItems();
        addToTable(lineItems);

        clear();
    }

    private void etyToRow(SalesInvoiceLineItem line) {

        String it = line.getItem() == null ? null : line.getItem().getCode();
        String itdes = line.getItem() == null ? null : line.getItem().getDescription();
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

    private SalesInvoiceLineItem addsales(SalesInvoiceLineItem lineItem) {
        SalesInvoiceLineItem sl = null;
        int sx = -1;
        int x = -1;
        for (SalesInvoiceLineItem sil : lineItems) {
            x++;
            if ((lineItem.getId() == null && sil.getId() == null) || lineItem.getId() != null && sil.getId() != null && lineItem.getId().equals(sil.getId())) {
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

    public SalesInvoiceLineItem getSelectedLine() {

        String bt = uiEty.colToStrE(tblInvoice, 0);
        SalesInvoiceLineItem lineItem = new SalesInvoiceLineItem();

        for (SalesInvoiceLineItem sli : lineItems) {
            //
            if ((bt == null && sli.getId() == null) || (bt != null && sli.getId() != null) && bt.equals(sli.getId())) {
                return sli;

            }
        }
        return null;
    }

    private void loadUnit(Item it) {
        if (it != null) {
            lineItemPanel.getUnit().setModel(it.getUoms());

        } else {

            lineItemPanel.getUnit().getModel().clear();

        }

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
        invoice.setInvNo(uiEty.tcToStr(tinvoiceManualNo));
        invoice.setShop(Shop.getDefaultShop());
        invoice.setDocRefNo(uiEty.tcToStr(tdocref));
        invoice.setSubTotal(uiEty.tcToDouble(tsubtotal));
        invoice.setTexAmount(uiEty.tcToDouble(ttax));
        invoice.setDiscount(uiEty.tcToDouble(tdis));
        invoice.setCashRecieveds(uiEty.tcToDouble(tcashrecieved));
        invoice.setRemarks(uiEty.tcToStr(tremark));
        invoice.setEditeddate(SystemEntityUtil.getSystemDate());
        invoice.setSaveddate(SystemEntityUtil.getSystemDate());
//        invoice.setLineItems(lineItems);

    }

    public void sTotalToUI() {
        uiEty.objToUi(tsubtotal, invoice.getSubTotal());
        uiEty.objToUi(tfinaltotle, invoice.getTotal());
        uiEty.objToUi(tbal, invoice.setTotal());
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
        tsalesman = new com.components.custom.CTextFieldWpopup();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblInvoice = new org.components.controls.CTableMaster();

        setLayout(null);

        cPanel1.setLayout(null);

        cLabel5.setText("Salesman");
        cLabel5.setFont(new java.awt.Font("Tahoma", 0, 12));
        cPanel1.add(cLabel5);
        cLabel5.setBounds(800, 70, 60, 25);

        cLabel7.setText("Total");
        cLabel7.setFont(new java.awt.Font("Tahoma", 0, 12));
        cPanel1.add(cLabel7);
        cLabel7.setBounds(10, 10, 70, 20);

        cLabel8.setText("Tax");
        cLabel8.setFont(new java.awt.Font("Tahoma", 0, 12));
        cPanel1.add(cLabel8);
        cLabel8.setBounds(10, 40, 70, 20);

        cLabel9.setText("Discount");
        cLabel9.setFont(new java.awt.Font("Tahoma", 0, 12));
        cPanel1.add(cLabel9);
        cLabel9.setBounds(10, 60, 70, 20);

        cLabel10.setText("Final Total");
        cLabel10.setFont(new java.awt.Font("Tahoma", 0, 12));
        cPanel1.add(cLabel10);
        cLabel10.setBounds(10, 90, 70, 20);

        tfinaltotle.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel1.add(tfinaltotle);
        tfinaltotle.setBounds(90, 90, 150, 20);

        tsubtotal.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel1.add(tsubtotal);
        tsubtotal.setBounds(90, 10, 150, 20);

        ttax.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel1.add(ttax);
        ttax.setBounds(90, 40, 150, 20);

        tdis.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel1.add(tdis);
        tdis.setBounds(90, 60, 150, 20);

        tcashrecieved.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel1.add(tcashrecieved);
        tcashrecieved.setBounds(90, 120, 150, 20);

        cLabel15.setText("Recieved");
        cLabel15.setFont(new java.awt.Font("Tahoma", 0, 12));
        cPanel1.add(cLabel15);
        cLabel15.setBounds(10, 120, 70, 20);

        add(cPanel1);
        cPanel1.setBounds(680, 360, 260, 160);

        cLabel2.setText("Salesman");
        cLabel2.setFont(new java.awt.Font("Tahoma", 0, 12));
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
        cLabel3.setFont(new java.awt.Font("Tahoma", 1, 10));
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
        cLabel4.setFont(new java.awt.Font("Tahoma", 0, 12));
        add(cLabel4);
        cLabel4.setBounds(790, 180, 60, 25);

        cPanel3.setLayout(null);

        cLabel6.setText("Salesman");
        cLabel6.setFont(new java.awt.Font("Tahoma", 0, 12));
        cPanel3.add(cLabel6);
        cLabel6.setBounds(800, 70, 60, 25);

        cLabel11.setText("Last Invoice");
        cLabel11.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel3.add(cLabel11);
        cLabel11.setBounds(60, 0, 70, 20);

        cLabel12.setText("Invoice Date");
        cLabel12.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel3.add(cLabel12);
        cLabel12.setBounds(10, 20, 70, 20);

        cLabel13.setText("Invoice Total");
        cLabel13.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel3.add(cLabel13);
        cLabel13.setBounds(10, 60, 70, 20);

        cTextField9.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel3.add(cTextField9);
        cTextField9.setBounds(10, 40, 150, 20);

        cTextField10.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel3.add(cTextField10);
        cTextField10.setBounds(10, 80, 150, 20);

        add(cPanel3);
        cPanel3.setBounds(520, 150, 170, 110);

        cPanel5.setLayout(null);

        cLabel16.setText("Salesman");
        cLabel16.setFont(new java.awt.Font("Tahoma", 0, 12));
        cPanel5.add(cLabel16);
        cLabel16.setBounds(800, 70, 60, 25);

        cLabel17.setText("Credit Limit");
        cLabel17.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel5.add(cLabel17);
        cLabel17.setBounds(10, 10, 70, 20);

        cLabel18.setText("Total Dues");
        cLabel18.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel5.add(cLabel18);
        cLabel18.setBounds(10, 40, 70, 20);

        cLabel19.setText("PD Chqs");
        cLabel19.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel5.add(cLabel19);
        cLabel19.setBounds(10, 60, 70, 20);

        cLabel20.setText("Bounced Chqs");
        cLabel20.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel5.add(cLabel20);
        cLabel20.setBounds(10, 80, 70, 20);

        cTextField12.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel5.add(cTextField12);
        cTextField12.setBounds(90, 80, 150, 20);

        tx.setFont(new java.awt.Font("Tahoma", 0, 10));
        tx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txActionPerformed(evt);
            }
        });
        cPanel5.add(tx);
        tx.setBounds(90, 10, 150, 20);

        td.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel5.add(td);
        td.setBounds(90, 40, 150, 20);

        cTextField15.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel5.add(cTextField15);
        cTextField15.setBounds(90, 60, 150, 20);

        add(cPanel5);
        cPanel5.setBounds(260, 150, 250, 110);
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
        controlPanel1.setBounds(110, 420, 480, 50);
        add(tsalesman);
        tsalesman.setBounds(790, 240, 160, 25);

        tblInvoice.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Item Code", "Description", "Qty", "Price", "Line Amount"
            }
        ));
        jScrollPane4.setViewportView(tblInvoice);

        add(jScrollPane4);
        jScrollPane4.setBounds(50, 10, 810, 110);
    }// </editor-fold>//GEN-END:initComponents

    private void ttypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ttypeActionPerformed

        System.out.println("evt " + evt);


    }//GEN-LAST:event_ttypeActionPerformed

    private void txActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txActionPerformed

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
    private com.components.custom.CTextFieldWpopup tsalesman;
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