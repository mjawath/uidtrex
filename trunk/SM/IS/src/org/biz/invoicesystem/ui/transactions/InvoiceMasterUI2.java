
/*
 * InvoiceMasterUi.java
 *
 * Created on Dec 2, 2011, 10:27:21 AM
 */
package org.biz.invoicesystem.ui.transactions;

import com.components.custom.ActionTask;
import com.components.custom.PagedPopUpPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.biz.app.ui.util.TableUtil;
import org.biz.app.ui.util.uiEty;
import org.biz.dao.service.GenericDAO;
import org.biz.dao.util.EntityService;
import org.biz.invoicesystem.entity.master.Customer;
import org.biz.invoicesystem.entity.master.Item;
import org.biz.invoicesystem.entity.master.Shop;
import org.biz.invoicesystem.entity.master.Staff;
import org.biz.invoicesystem.entity.master.Supplier;
import org.biz.invoicesystem.entity.transactions.SalesInvoice;
import org.biz.invoicesystem.entity.transactions.SalesInvoiceLineItem;
import org.biz.invoicesystem.service.master.CustomerService;
import org.biz.invoicesystem.service.master.ItemService;
import org.biz.invoicesystem.service.master.StaffService;
import org.biz.invoicesystem.service.transactions.SalesInvoiceService;
import org.biz.invoicesystem.system.SystemUtil;
import org.components.util.Sessions;
import org.components.windows.TabPanelUI;

/*
 * @2011/12/08 jawth -  GUIcreation  of invoice
 * @2011/12/09 jawath-  creation  of invoice logic 
 * @2011/12/12 jawath-  table editing popup poanel  
 * @2011/12/15 jawath-  table editing cell ediotr
 * @2011/12/18 jawath-  table editing cell ediotr document listner
 */
public class InvoiceMasterUI2 extends TabPanelUI {

    SalesInvoice invoice;
    List<SalesInvoiceLineItem> lineItems;
    SalesInvoiceService servicedao;
    PagedPopUpPanel popUpComponent;    
    SalesLineItemPanel lineItemPanel;
//    DoubleCellEditor de;
    PagedPopUpPanel cuspop;
    PagedPopUpPanel salesPopup;
    CustomerService custService;
    ItemService itemService;
    List<Customer> listCust;
    List<Item> listItem;
    List<Staff> listStaff;
    StaffService staffService;
    Item currentItem;
    SalesInvoiceLineItem seil;
    PagedPopUpPanel itemSelectionPopup;

    /** Creates new form InvoiceMasterUi */
    public InvoiceMasterUI2() {
        initComponents();

        init();
    }

    public void init() {

        initPopups();
        
        invoice = new SalesInvoice();
        lineItems = new ArrayList<SalesInvoiceLineItem>();
        invoice.setLineItems(lineItems);

//                //ADD this list to popup

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

        super.init();

    }


    public void initPopups() {
       JFrame jf = (JFrame) Sessions.getObj("mainui");
//       JDialog jd = new JDialog(jf,false);
////       jd.setModal(false);
//       jd.setSize(300,300);
//       jd.setVisible(true);
        //
        lineItemPanel = new SalesLineItemPanel(jf) {
            
            public SalesInvoiceLineItem panelToEty() {
                SalesInvoiceLineItem sl = super.panelToEty();
                //validated line
                //update table row
                //add to list
                addsales(sl);
                //replace selected row
                //                addToTable(lineItems);

                return sl;
            }

            public void selectEty() {
                //get selected row 
                //get line item from list 
                //set seleceted object to lineitempanel
                SalesInvoiceLineItem sl = InvoiceMasterUI2.this.getSelectedLine();
                if (sl == null) {
                    return;
                }
                salesline = sl;
                loadUnit(sl.getItem());
                itemSelectionPopup.setPopDesable(true);
                etyToPanel();
                itemSelectionPopup.setPopDesable(false);

            }

           
            public void action() {
                SalesInvoiceLineItem sl = super.panelToEty();
                Object id = sl.getId();
                if (id == null) {
                    sl.setId(System.currentTimeMillis() + "tt");
                }
                etyToRow(sl);

                //check lineittem id is null then if its need a new row insert a new row  
                //or move to next row

                if (id == null) {
                    setnewrow();

                }
            }
        };

        lineItemPanel.setTable(tblInvoice);
        lineItemPanel.setcTextField1(lineItemPanel.getItemFiled());
        
        itemSelectionPopup = new PagedPopUpPanel(lineItemPanel.getItemFiled()) {

            public void search(String qry) {
                try {

                    itemSelectionPopup.setList(itemService.getDao().byCode(qry));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void action() {
                super.action();
                String ob = itemSelectionPopup.getSelectedID();
                Item item = null;
                //find Item
                for (Item it : listItem) {
                    if (ob.equals(it.getId())) {
                        itemSelectionPopup.setSelectedObject(it);
                        item = it;
                        break;
                    }
                }
                int sr = tblInvoice.getSelectedRow();
                loadUnit(item);
                SalesInvoiceLineItem lineItem = lineItemPanel.panelToEty();
                //                if current row valid 
                lineItem.setItem(item);
                //                replace row
                addsales(lineItem);
//        lineItemPanel.moveNextFocus();
                //                 lineItemPanel.getItemdescFiled().setInputVerifier(new InputVerifier() {
                //
                //                    @Override
                //                    public boolean verify(JComponent input) {
                //                        throw new UnsupportedOperationException("Not supported yet.");
                //                    }
                //                });

        lineItemPanel.lineItemLogic();
            }

            public Object[] data(Object item) {
                Item it = (Item) item;
                return new Object[]{it.getId(), it.getCode(), it.getDescription()};
            }
        };
          
        cuspop = new PagedPopUpPanel(tcus) {

            @Override
            public Object[] data(Object item) {
                Customer customer = (Customer) item;
                return new Object[]{customer.getId(), customer.getCode(), customer.getCustomerName()};
            }

            @Override
            public void action() {

                String ob = cuspop.getSelectedID();
                Customer item = null;
                //find Item
                for (Customer it : listCust) {
                    if (ob.equals(it.getId())) {
                        cuspop.setSelectedObject(it);
                        item = it;
                        invoice.setCustomer(item);
                        uiEty.objToUi(cTextArea2, item.getAddress());
                        tblInvoice.requestFocus();
                        break;
                    }
                }

            }

            @Override
            public void search(String qry) {
                try {
                    listCust = custService.getDao().byCode(qry);
                    cuspop.setObjectToTable(listCust);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        cuspop.setObjectToTable(listCust);
        cuspop.setSelectedColumn(1);
        cuspop.setNextFocusableComponent(tblInvoice);        
       
        
        cuspop.addaction(new ActionTask(){

            @Override
            public boolean action() {
                System.out.println("action task form out side 222222222");
//                cuspop.mo
                return super.action();
            }
        
        });    
       
        
        salesPopup = new PagedPopUpPanel(tsalesman) {

            @Override
            public Object[] data(Object item) {
                Staff st = (Staff) item;
                return new Object[]{st.getId(), st.getCode(), st.getName()};
            }

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
 
    }
    
    public void events() {
        
        tsubtotal.addActionListener(new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("sub action "+System.currentTimeMillis());
                //should valildate 
                // should call action
                //if both return true 
                //this should leave focus
//                ttax.requestFocus();
                throw new RuntimeException();
            }
        });
        
        tsubtotal.setInputVerifier(new InputVerifier() {

         
            public boolean verify(JComponent input) {
//                throw new RuntimeException("Not supported yet.");
                System.out.println("input verified "+System.currentTimeMillis());
                return true;
            }
        });
        
        

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
        
        
        tcus.addKeyListener(new KeyAdapter() {        
            public void keyPressed(KeyEvent e) {             
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    System.out.println("|--|");
                    //if table not selected pls select a row 
                    if(tblInvoice.getSelectedRow()<0){
                    tblInvoice.getSelectionModel().setSelectionInterval(0,0);
                    }
                    lineItemPanel.showLineItemPanel();
                    lineItemPanel.getItemFiled().requestFocus();
                }
            }
        });      
      
    }

    private void etyToRow(SalesInvoiceLineItem line) {

        String it = line.getItem() == null ? null : line.getItem().getCode();
        String itdes = line.getItem() == null ? null : line.getItem().getDescription();
        TableUtil.replacerowValues(tblInvoice, new Object[]{line.getId(), it, itdes, line.getQty(), line.getUnit(), line.getPrice(), line.getLineAmount()}, tblInvoice.getSelectedRow());

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
        if(it!=null){
        String st = it.getUnitOne();
        String st2 = it.getUnitTwo();
        String[] stx = st2 == null ? new String[]{"",st} : new String[]{"", st, st2};
        uiEty.setcombomodel(stx, lineItemPanel.getUnitCombo());
        }else{
            String[] stx =new String[]{"no","nop","ppp"};
        uiEty.setcombomodel(stx, lineItemPanel.getUnitCombo());
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


            String it = line.getItem() == null ? null : line.getItem().getCode();
            String itdes = line.getItem() == null ? null : line.getItem().getDescription();

            TableUtil.addrow(tblInvoice, new Object[]{new Object[]{line.getId(), it, itdes, line.getQty(), line.getUnit(), line.getPrice(), line.getLineAmount()
                        }});
        }
        TableUtil.addrow(tblInvoice, new Object[]{});


    }

    public void uiety() {
        
        invoice.setDocdate(tdate.getDate());
        invoice.setInvNo(uiEty.tcToStr(tinvoiceManualNo));
        invoice.setShop(Shop.getDefaultShop());
        invoice.setDocRefNo(uiEty.tcToStr(tdocref));
        invoice.setSubTotal(uiEty.tcToDouble(tsubtotal));
        invoice.setSubTotal(uiEty.tcToDouble(tsubtotal));
        invoice.setRemarks(uiEty.tcToStr(tremark));
        invoice.setEditeddate(SystemUtil.getSystemDate());
        invoice.setSaveddate(SystemUtil.getSystemDate());
        invoice.setLineItems(lineItems);
        
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
        tcus = new org.components.controls.CTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        cTextArea2 = new org.components.controls.CTextArea();
        cCheckBox2 = new org.components.controls.CCheckBox();
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
        cTextField13 = new org.components.controls.CTextField();
        cTextField14 = new org.components.controls.CTextField();
        cTextField15 = new org.components.controls.CTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblInvoice = new org.components.controls.TableEditable();
        cButton1 = new org.components.controls.CButton();
        cdelete = new org.components.controls.CButton();
        cclear = new org.components.controls.CButton();
        tsalesman = new org.components.controls.CTextField();
        tdocref = new org.components.controls.CTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tremark = new org.components.controls.CTextArea();
        cLabel1 = new org.components.controls.CLabel();

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
        cLabel2.setBounds(700, 90, 60, 25);

        ttype.setEditable(true);
        ttype.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Invoice", "Quotation", "Credit Note", "Consignment Out" }));
        ttype.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ttypeActionPerformed(evt);
            }
        });
        add(ttype);
        ttype.setBounds(680, 10, 150, 23);

        tinvoiceManualNo.setText("Inv No manually");
        add(tinvoiceManualNo);
        tinvoiceManualNo.setBounds(830, 10, 110, 25);

        cPanel2.setLayout(null);

        cLabel3.setText("Customer");
        cLabel3.setFont(new java.awt.Font("Tahoma", 1, 10));
        cPanel2.add(cLabel3);
        cLabel3.setBounds(10, 0, 60, 20);
        cPanel2.add(tcus);
        tcus.setBounds(60, 0, 150, 20);

        cTextArea2.setColumns(20);
        cTextArea2.setRows(10);
        jScrollPane3.setViewportView(cTextArea2);

        cPanel2.add(jScrollPane3);
        jScrollPane3.setBounds(10, 30, 230, 60);

        cCheckBox2.setText("Type");
        cPanel2.add(cCheckBox2);
        cCheckBox2.setBounds(10, 93, 60, 20);

        add(cPanel2);
        cPanel2.setBounds(0, 11, 240, 120);
        add(tdate);
        tdate.setBounds(830, 40, 110, 22);

        cLabel4.setText("Date");
        cLabel4.setFont(new java.awt.Font("Tahoma", 0, 12));
        add(cLabel4);
        cLabel4.setBounds(780, 40, 60, 25);

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
        cPanel3.setBounds(510, 10, 170, 110);

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

        cTextField13.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel5.add(cTextField13);
        cTextField13.setBounds(90, 10, 150, 20);

        cTextField14.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel5.add(cTextField14);
        cTextField14.setBounds(90, 40, 150, 20);

        cTextField15.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel5.add(cTextField15);
        cTextField15.setBounds(90, 60, 150, 20);

        add(cPanel5);
        cPanel5.setBounds(250, 10, 250, 110);

        tblInvoice.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Item Code", "Desc", "Qty", "Unit", "Price", "Line Amount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblInvoice);
        tblInvoice.getColumnModel().getColumn(0).setResizable(false);
        tblInvoice.getColumnModel().getColumn(1).setResizable(false);
        tblInvoice.getColumnModel().getColumn(2).setResizable(false);
        tblInvoice.getColumnModel().getColumn(3).setResizable(false);
        tblInvoice.getColumnModel().getColumn(4).setResizable(false);
        tblInvoice.getColumnModel().getColumn(5).setResizable(false);
        tblInvoice.getColumnModel().getColumn(6).setResizable(false);

        add(jScrollPane2);
        jScrollPane2.setBounds(10, 170, 930, 180);

        cButton1.setText("Save");
        cButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cButton1ActionPerformed(evt);
            }
        });
        add(cButton1);
        cButton1.setBounds(410, 400, 57, 30);

        cdelete.setText("Delete");
        cdelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cdeleteActionPerformed(evt);
            }
        });
        add(cdelete);
        cdelete.setBounds(540, 400, 70, 30);

        cclear.setText("Clear");
        cclear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cclearActionPerformed(evt);
            }
        });
        add(cclear);
        cclear.setBounds(470, 400, 70, 30);
        add(tsalesman);
        tsalesman.setBounds(770, 90, 130, 25);
        add(tdocref);
        tdocref.setBounds(710, 60, 70, 25);

        tremark.setColumns(20);
        tremark.setRows(5);
        jScrollPane1.setViewportView(tremark);

        add(jScrollPane1);
        jScrollPane1.setBounds(90, 380, 190, 60);

        cLabel1.setText("Remarks");
        add(cLabel1);
        cLabel1.setBounds(110, 360, 104, 20);
    }// </editor-fold>//GEN-END:initComponents

    private void cButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cButton1ActionPerformed




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

    }//GEN-LAST:event_cButton1ActionPerformed

    private void cdeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cdeleteActionPerformed
    }//GEN-LAST:event_cdeleteActionPerformed

    private void cclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cclearActionPerformed
        clear();
    }//GEN-LAST:event_cclearActionPerformed

    private void ttypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ttypeActionPerformed

        System.out.println("evt "+evt);
        
        
    }//GEN-LAST:event_ttypeActionPerformed

    public void clear() {

        invoice = SalesInvoice.createNewInvoice();
        lineItems = invoice.getLineItems();
        addToTable(lineItems);


        tcus.setText("");
        tsalesman.setText("");
        ttax.setText("");
        tsubtotal.setText("");
        tcashrecieved.setText("");
        tfinaltotle.setText("");
        tinvoiceManualNo.setText("");
        tdis.setText("");
        tsalesman.setText("");
        setnewrow();
        uiEty.setcombomodel(new String[]{}, lineItemPanel.getUnitCombo());
        

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CButton cButton1;
    private org.components.controls.CCheckBox cCheckBox2;
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
    private org.components.controls.CTextArea cTextArea2;
    private org.components.controls.CTextField cTextField10;
    private org.components.controls.CTextField cTextField12;
    private org.components.controls.CTextField cTextField13;
    private org.components.controls.CTextField cTextField14;
    private org.components.controls.CTextField cTextField15;
    private org.components.controls.CTextField cTextField9;
    private org.components.controls.CButton cclear;
    private org.components.controls.CButton cdelete;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private org.components.controls.TableEditable tblInvoice;
    private org.components.controls.CTextField tcashrecieved;
    private org.components.controls.CTextField tcus;
    private org.components.controls.CDatePicker tdate;
    private org.components.controls.CTextField tdis;
    private org.components.controls.CTextField tdocref;
    private org.components.controls.CTextField tfinaltotle;
    private org.components.controls.CTextField tinvoiceManualNo;
    private org.components.controls.CTextArea tremark;
    private org.components.controls.CTextField tsalesman;
    private org.components.controls.CTextField tsubtotal;
    private org.components.controls.CTextField ttax;
    private org.components.controls.CComboBox ttype;
    // End of variables declaration//GEN-END:variables

    public void updateEntityUI() {
    }

    public String getTabName() {
        return "Invoice Master";
    }

    public JPanel getJPanel() {
        return this;
    }

    public static void main(String[] args) {
        Object c = null;
        Object c2 = null;
        System.out.println("r " + c + 25 + " ff " + c2);
        List lst = new ArrayList();
        for (int i = 0; i < 1500; i++) {
            Supplier cus = new Supplier();
            cus.setId( EntityService.getKeyStr());
            cus.setCode( EntityService.getKeyStr());
            cus.setName(EntityService.getKeyStr());
            lst.add(cus);
        }
        new GenericDAO<Supplier>().saveList(lst);
//        List lst = new ArrayList();
//        for (int i = 0; i < 1500; i++) {
//            Customer cus = new Customer();
//            cus.setId("" + i + "" + System.currentTimeMillis());
//            cus.setCode("" + i + "" + System.currentTimeMillis() + "" + i);
//            cus.setCustomerName("" + i + "" + System.currentTimeMillis() + "" + i);
//            lst.add(cus);
//        }
//        new GenericDAO<Customer>().saveList(lst);
    }
}
//        cxTable2.getColumnModel().getColumn(2).setCellEditor(new editor(popUpComponent, tblInvoice));
//        dialog = new ItemPopUp(cTextField1, lineItems)
//        /*     KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
//KeyStroke ob = KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0);
////         tblInvoice.getInputMap().put(KeyStroke.getKeyStroke("ENTER"),ob);
//ActionListener ac = tblInvoice.getActionForKeyStroke(ob);
//tblInvoice.registerKeyboardAction(ac,enter,JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);*/
