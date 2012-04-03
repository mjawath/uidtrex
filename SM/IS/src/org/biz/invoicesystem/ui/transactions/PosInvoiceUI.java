
/*
 * InvoiceMasterUi.java
 *
 * Created on Dec 2, 2011, 10:27:21 AM
 */
package org.biz.invoicesystem.ui.transactions;

import com.components.custom.PagedPopUpPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.biz.app.ui.util.TableUtil;
import org.biz.app.ui.util.uiEty;
import org.biz.invoicesystem.entity.master.Customer;
import org.biz.invoicesystem.entity.master.Item;
import org.biz.invoicesystem.entity.master.Staff;
import org.biz.invoicesystem.entity.transactions.SalesInvoice;
import org.biz.invoicesystem.entity.transactions.SalesInvoiceLineItem;
import org.biz.invoicesystem.service.master.CustomerService;
import org.biz.invoicesystem.service.master.ItemService;
import org.biz.invoicesystem.service.master.StaffService;
import org.biz.invoicesystem.service.transactions.SalesInvoiceService;
import org.components.util.Sessions;
import org.components.windows.TabPanelUI;

/*
 * @2011/12/08 zumri -  GUI creation  of invoice
 * @2011/12/09 jawath-  creation  of invoice logic 
 * @2011/12/12 jawath-  table editing popup poanel  
 * @2011/12/15 jawath-  table editing cell ediotr
 * @2011/12/18 jawath-  table editing cell ediotr document listner
 */
public class PosInvoiceUI extends TabPanelUI {

    SalesInvoice invoice;
    List<SalesInvoiceLineItem> lineItems;
    SalesInvoiceService servicedao;
    PagedPopUpPanel itemSelectionPopup;
    SalesLineItemPanel lineItemPanel;

    /** Creates new form InvoiceMasterUi */
    public PosInvoiceUI() {
        initComponents();

        init();
    }

    
    @Override
    public void init() {

        JFrame jf = (JFrame) Sessions.getObj("mainui");
        
        lineItemPanel = new SalesLineItemPanel(jf) {

            public SalesInvoiceLineItem panelToEty() {
                SalesInvoiceLineItem sl = super.panelToEty();
                addsales(sl);
                return sl;
            }

            public void selectEty() {
                SalesInvoiceLineItem sl = PosInvoiceUI.this.getSelectedLine();
                if (sl == null) {
                    return;
                }
                salesline = sl;
//                loadUnit(sl.getItem());
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
                lineItem.setItem(item);
                addsales(lineItem);

                lineItemPanel.lineItemLogic();
            }

            public Object[] data(Object item) {
                Item it = (Item) item;
                return new Object[]{it.getId(), it.getCode(), it.getDescription()};
            }
        };


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
                    if (salesInvoiceLineItem.getId().equals(id)) {
                        it.remove();
                    }
                }
            }
        }, KeyEvent.VK_DELETE);

        invoice = new SalesInvoice();
        lineItems = new ArrayList<SalesInvoiceLineItem>();
        invoice.setLineItems(lineItems);
        itemService = new ItemService();
        listItem = itemService.getDao().getAll();

        servicedao = new SalesInvoiceService();
        itemService = new ItemService();

        custService = new CustomerService();
        listCust = custService.getDao().getAll();

        staffService = new StaffService();
        listStaff = new ArrayList<Staff>();



        tblInvoice.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    try {
                        int sr = tblInvoice.getSelectedRow();
                        SalesInvoiceLineItem seil = rowToEty();
                        //if new row create new line item add to 
                        //or selecet the correct model frfom list and select current 
                    } catch (Exception ee) {
                    ee.printStackTrace();
                    }
                }
            }
        });
        setnewrow();


    }

    private void etyToRow(SalesInvoiceLineItem line) {

        String it = line.getItem() == null ? null : line.getItem().getCode();
        String itdes = line.getItem() == null ? null : line.getItem().getDescription();
        TableUtil.replacerowValues(tblInvoice, new Object[]{line.getId(), it, itdes, line.getQty(), line.getUnit(), line.getPrice(), line.getLineAmount()}, tblInvoice.getSelectedRow());

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
//    DoubleCellEditor de;
    PagedPopUpPanel salesPopup;
    CustomerService custService;
    ItemService itemService;
    List<Customer> listCust;
    List<Item> listItem;
    List<Staff> listStaff;
    StaffService staffService;
    Item currentItem;
    SalesInvoiceLineItem seil;

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

    private void loadUnit(Item it) {
        String st = it.getUnitOne();
        String st2 = it.getUnitOne();
        String[] stx = st2 == null ? new String[]{st} : new String[]{st, st2};

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
        for (SalesInvoiceLineItem item : items) {
            TableUtil.addrow(tblInvoice, new Object[]{item.getId(), item.getItem().getCode(), item.getQty()
                    });
        }
        TableUtil.addrow(tblInvoice, new Object[]{TableUtil.newRowID});
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cPanel1 = new org.components.containers.CPanel();
        cLabel5 = new org.components.controls.CLabel();
        cLabel7 = new org.components.controls.CLabel();
        cLabel8 = new org.components.controls.CLabel();
        cLabel9 = new org.components.controls.CLabel();
        cLabel10 = new org.components.controls.CLabel();
        cTextField1 = new org.components.controls.CTextField();
        cTextField4 = new org.components.controls.CTextField();
        cTextField5 = new org.components.controls.CTextField();
        cTextField6 = new org.components.controls.CTextField();
        cTextField11 = new org.components.controls.CTextField();
        cLabel15 = new org.components.controls.CLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblInvoice = new org.components.controls.TableEditable();
        cButton1 = new org.components.controls.CButton();
        cdelete = new org.components.controls.CButton();
        cclear = new org.components.controls.CButton();

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

        cTextField1.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel1.add(cTextField1);
        cTextField1.setBounds(90, 90, 150, 20);

        cTextField4.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel1.add(cTextField4);
        cTextField4.setBounds(90, 10, 150, 20);

        cTextField5.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel1.add(cTextField5);
        cTextField5.setBounds(90, 40, 150, 20);

        cTextField6.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel1.add(cTextField6);
        cTextField6.setBounds(90, 60, 150, 20);

        cTextField11.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel1.add(cTextField11);
        cTextField11.setBounds(90, 120, 150, 20);

        cLabel15.setText("Recieved");
        cLabel15.setFont(new java.awt.Font("Tahoma", 0, 12));
        cPanel1.add(cLabel15);
        cLabel15.setBounds(10, 120, 70, 20);

        add(cPanel1);
        cPanel1.setBounds(640, 360, 270, 160);

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

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblInvoice);

        add(jScrollPane2);
        jScrollPane2.setBounds(20, 20, 900, 270);

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
    }//GEN-LAST:event_cclearActionPerformed

    public void clear() {
        
        invoice = SalesInvoice.createNewInvoice();
        lineItems = invoice.getLineItems();
        addToTable(lineItems);


        setnewrow();
        uiEty.setcombomodel(new String[]{}, lineItemPanel.getUnitCombo());
        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CButton cButton1;
    private org.components.controls.CLabel cLabel10;
    private org.components.controls.CLabel cLabel15;
    private org.components.controls.CLabel cLabel5;
    private org.components.controls.CLabel cLabel7;
    private org.components.controls.CLabel cLabel8;
    private org.components.controls.CLabel cLabel9;
    private org.components.containers.CPanel cPanel1;
    private org.components.controls.CTextField cTextField1;
    private org.components.controls.CTextField cTextField11;
    private org.components.controls.CTextField cTextField4;
    private org.components.controls.CTextField cTextField5;
    private org.components.controls.CTextField cTextField6;
    private org.components.controls.CButton cclear;
    private org.components.controls.CButton cdelete;
    private javax.swing.JScrollPane jScrollPane2;
    private org.components.controls.TableEditable tblInvoice;
    // End of variables declaration//GEN-END:variables

    @Override
    public void updateEntityUI() {
    }

    @Override
    public String getTabName() {
        return "Invoice Master";
    }

    @Override
    public JPanel getJPanel() {
        return this;
    }
}
//        cxTable2.getColumnModel().getColumn(2).setCellEditor(new editor(popUpComponent, tblInvoice));
//        dialog = new ItemPopUp(cTextField1, lineItems)
//        /*     KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
//KeyStroke ob = KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0);
////         tblInvoice.getInputMap().put(KeyStroke.getKeyStroke("ENTER"),ob);
//ActionListener ac = tblInvoice.getActionForKeyStroke(ob);
//tblInvoice.registerKeyboardAction(ac,enter,JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);*/
