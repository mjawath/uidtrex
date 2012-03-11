/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * InvoiceMasterUi.java
 *
 * Created on Dec 2, 2011, 10:27:21 AM
 */
package org.biz.invoicesystem.ui.transactions;

import com.components.custom.DropDownPopUpPanel;
import com.components.custom.PagedPopUpPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import org.biz.app.ui.util.TableUtil;
import org.biz.app.ui.util.uiEty;
import org.biz.dao.util.EntityService;
import org.biz.invoicesystem.entity.master.Item;
import org.biz.invoicesystem.entity.master.Supplier;
import org.biz.invoicesystem.entity.transactions.PurchaseInvoice;
import org.biz.invoicesystem.entity.transactions.PurchaseInvoiceLineItem;
import org.biz.invoicesystem.service.master.ItemService;
import org.biz.invoicesystem.service.master.SupplierService;
import org.biz.invoicesystem.service.transactions.PurchaseInvoiceService;
import org.components.parent.controls.editors.ComboBoxCellEditor;
import org.components.parent.controls.editors.DoubleCellEditor;
import org.components.parent.controls.editors.StringCellEditor;
import org.components.parent.controls.editors.TableColumnAction;
import org.components.parent.controls.editors.TablePopUpCellEditor;
import org.components.parent.controls.editors.TableActions;
import org.components.windows.TabPanelUI;

/**
 *
 * @author Administrator
 */
public class PurchaseMasterUi extends TabPanelUI {

    PurchaseInvoice invoice;
    List<PurchaseInvoiceLineItem> lineItems;
    PurchaseInvoiceService purService;
    List<Supplier> supplierlst;
    List<Item> itemlst;
    PagedPopUpPanel popUpComponent;
    SupplierService supplierService;
    ItemService itemService;
    PagedPopUpPanel suplierPopUpPanel;
    PurchaseInvoiceLineItem seil;
    ComboBoxCellEditor ce;

    /** Creates new form InvoiceMasterUi */
    public PurchaseMasterUi() {
        initComponents();
        init();
    }

    @Override
    public void init() {

        supplierService = new SupplierService();
        itemService = new ItemService();
        purService = new PurchaseInvoiceService();

        supplierlst = new ArrayList<Supplier>();
        itemlst = new ArrayList<Item>();

        invoice = new PurchaseInvoice();
        lineItems = new ArrayList<PurchaseInvoiceLineItem>();
        invoice.setLineItems(lineItems);

        suplierPopUpPanel = new PagedPopUpPanel(tsup) {

            @Override
            public void action() {
                String ob = suplierPopUpPanel.getSelectedID();
                Supplier item = null;
                //find Item
                for (Supplier it : supplierlst) {
                    if (ob.equals(it.getId())) {
                        suplierPopUpPanel.setSelectedObject(it);
                        item = it;
                        invoice.setSupplier(item);
                        uiEty.objToUi(cTextArea2, item.getAddress1());
                        break;
                    }
                }
            }

            @Override
            public void search(String qry) {
                try {
                    supplierlst = supplierService.getDao().byCode(qry);
                    suplierPopUpPanel.setObjectToTable(supplierlst);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public Object[] data(Object item) {
                Supplier it = (Supplier) item;
                return new Object[]{it.getId(), it.getCode(), it.getName()};
            }
        };

        ce=new ComboBoxCellEditor(tblInvoice);
        
        uiEty.setKeyAction(tblInvoice, new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                System.out.println("delete action .......");
                int sr = tblInvoice.getSelectedRow();
                String id = (String) TableUtil.getSelectedValue(tblInvoice, 0);
                if (id == null || id.isEmpty()) {
                    return;
                }
                TableUtil.removerow(tblInvoice, sr);
                for (Iterator<PurchaseInvoiceLineItem> it = lineItems.iterator(); it.hasNext();) {
                    PurchaseInvoiceLineItem salesInvoiceLineItem = it.next();
                    if (id.equals( salesInvoiceLineItem.getId())) {
                        it.remove();
                    }
                }
            }
        }, KeyEvent.VK_DELETE);

        TablePopUpCellEditor tb = new TablePopUpCellEditor(tblInvoice);

        popUpComponent = new PagedPopUpPanel(tblInvoice, tb) {

            @Override
            public void search(String qry) {
                try {

                    itemlst=itemService.getDao().byCode(qry);
                    popUpComponent.setList(itemlst);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            
            
            @Override
            public void action() {

                String ob = popUpComponent.getSelectedID();
                Item item = null;
                //find Item
                for (Item it : itemlst) {
                    if (ob.equals(it.getId())) {
                        popUpComponent.setSelectedObject(it);
                        item = it;
                        break;
                    }
                }
                int sr = tblInvoice.getSelectedRow();
                PurchaseInvoiceLineItem lineItem = rowToEty();
                //if current row valid 
                lineItem.setItem(item);
                loadUnit(item);
                lineItem.setDescription(item.getDescription()) ;
                //set row values
                tblInvoice.setValueAt(item.getDescription(), sr, 2) ;
                
                //replace row
                addRowToLst(lineItem);
                

            }

            @Override
            public Object[] data(Object item) {
                Item it = (Item) item;
                return new Object[]{it.getId(), it.getCode(), it.getDescription()};
            }
        };        
        
      
           popUpComponent.setModel();

        

        TableUtil.setColumnEditor(tblInvoice, 1, tb);
        
        TableUtil.setColumnEditor(tblInvoice, 2, new StringCellEditor(tblInvoice) {

            @Override
            public boolean isCellValid() {
                return super.isCellValid();
            }
        
        });        
        TableUtil.setColumnEditor(tblInvoice, 3, new DoubleCellEditor(tblInvoice));
        
        
        JComboBox jc= new JComboBox(new Object[]{150,250,302});
        jc.setEditable(true);
        TablePopUpCellEditor tp=new TablePopUpCellEditor(tblInvoice);
        DropDownPopUpPanel pp=new DropDownPopUpPanel(tblInvoice, tp) {

            
            
            @Override
            public void action() {
                super.action();
            }

            @Override
            public Object[] data(Object item) {
                
                return new Object[]{item};
            }
            
        
        };
        ArrayList al=new ArrayList(3);
        al.add(1500);
        al.add(1300);
        al.add(6553);
        
       pp.setList(al);
        TableUtil.setColumnEditor(tblInvoice, 4,tp);
        
        TableUtil.setColumnEditor(tblInvoice, 5, new DoubleCellEditor(tblInvoice));
        
        TableUtil.setColumnEditor(tblInvoice, 6, new DoubleCellEditor(tblInvoice));
        
        setnewrow();


        TableActions taacti = new TableActions() {

            @Override
            public boolean rowValid() {
                return validateRow();
            }

            @Override
            public void newRowAdded() {
                System.out.println("new row added");
                PurchaseInvoiceLineItem si = new PurchaseInvoiceLineItem();
                lineItems.add(si);
            }
        };
        tblInvoice.setTableAction(taacti); taacti.setUnSelectableColumns(new Integer[]{3,4});
        taacti.setUnSelectableColumns(new Integer[]{2,4});
        TableColumnAction tca = new TableColumnAction() {

            @Override
            public int actionPerformed() {
                validateRow();
                return TableActions.nextcol;
            }
        };
        taacti.addColumnAction(3, tca);
        TableColumnAction tca5 = new TableColumnAction() {

            @Override
            public int actionPerformed() {
                validateAddRow();
                return TableActions.newrow;
            }
        };
        taacti.addColumnAction(5, tca5);

    }

    public void setnewrow() {
        TableUtil.addrow(tblInvoice, new Object[]{});
        PurchaseInvoiceLineItem si = new PurchaseInvoiceLineItem();

        lineItems.add(si);
    }

    private void loadUnit(Item it){        
         String st=it.getUnitOne();
         String st2=it.getUnitTwo();
         String [] stx= new String[]{st,st2,"d","Y"};         
         uiEty.cmbModelWithoutNull(stx, (JComboBox)ce.getComponent());
        
    }
    
    private void rowLogic() {
        int sr = tblInvoice.getSelectedRow();
        seil = rowToEty();
        //validate the lineitem
        addRowToLst(seil);

        if (seil != null && seil.getId() == null) {
            String va = TableUtil.getNewRowId();
            tblInvoice.setValueAt(va, sr, 0);
            seil.setId(va);
        }
        Double qty = seil.getQty() == null ? 0 : seil.getQty();
        Double price = seil.getPrice() == null ? 0 : seil.getPrice();
        seil.setLineAmount(qty * price);
        tblInvoice.setValueAt(uiEty.getPlainDouble(seil.getLineAmount()), sr, 6);
        tblInvoice.setValueAt(seil.getPrice(), sr, 5);
    }
    
    private boolean validateRow() {


        int sr = tblInvoice.getSelectedRow();

      PurchaseInvoiceLineItem  lit = rowToEty();
        //validate the lineitem
        addRowToLst(lit);

        Double qty = lit.getQty() == null ? 0 : lit.getQty();
        Double price = lit.getPrice() == null ? 0 : lit.getPrice();
        lit.setLineAmount(qty * price);
        
        //validate current row before row selection changes
        tblInvoice.setValueAt(uiEty.getPlainDouble(lit.getLineAmount()), sr, 6);
        tblInvoice.setValueAt(lit.getPrice(), sr, 5);
        for (PurchaseInvoiceLineItem Item : lineItems) {
            Item it = Item.getItem();
            System.out.println("line  **** " + Item.getId() + " item ** " + (it == null ? " - print null" : Item.getItem().getId()));
        }
        return true;
    }

    private boolean validateAddRow() {


        int sr = tblInvoice.getSelectedRow();

        seil = rowToEty();
        //validate the lineitem
        addRowToLst(seil);
        if (seil != null && seil.getId() == null) {
            String va = TableUtil.getNewRowId();
            tblInvoice.setValueAt(va, sr, 0);
            seil.setId(va);
        }
        Double qty = seil.getQty() == null ? 0 : seil.getQty();
        Double price = seil.getPrice() == null ? 0 : seil.getPrice();
        seil.setLineAmount(qty * price);
        tblInvoice.setValueAt(uiEty.getPlainDouble(seil.getLineAmount()), sr, 6);
        tblInvoice.setValueAt(seil.getPrice(), sr, 5);
       
        return true;
    }

    private PurchaseInvoiceLineItem addRowToLst(PurchaseInvoiceLineItem lineItem) {
        PurchaseInvoiceLineItem sl = null;
        int sx = -1;
        int x = -1;
        for (PurchaseInvoiceLineItem sil : lineItems) {
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

    public PurchaseInvoiceLineItem rowToEty() {

        String bt = uiEty.colToStrE(tblInvoice, 0);
        PurchaseInvoiceLineItem lineItem = new PurchaseInvoiceLineItem();
        lineItem.setId(uiEty.colToStrE(tblInvoice, 0));
//        lineItem.setItem(currentItem);
        lineItem.setDescription(uiEty.colToStrE(tblInvoice, 2));
        lineItem.setUnit(uiEty.colToStr(tblInvoice, 4));
        lineItem.setQty(uiEty.colToDbl(tblInvoice, 3));
        lineItem.setPrice(uiEty.colToDbl(tblInvoice, 5));
        lineItem.setLineAmount(uiEty.colToDbl(tblInvoice, 6));
        for (PurchaseInvoiceLineItem sli : lineItems) {
            if ((bt == null && sli.getId() == null) || (bt != null && sli.getId() != null && bt.equals(sli.getId()))) {
                lineItem.setItem(sli.getItem());
                break;
            }
        }
        return lineItem;

    }

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
        cComboBox1 = new org.components.controls.CComboBox();
        cLabel2 = new org.components.controls.CLabel();
        cComboBox2 = new org.components.controls.CComboBox();
        cTextField2 = new org.components.controls.CTextField();
        cPanel2 = new org.components.containers.CPanel();
        cLabel3 = new org.components.controls.CLabel();
        tsup = new org.components.controls.CTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        cTextArea2 = new org.components.controls.CTextArea();
        cCheckBox2 = new org.components.controls.CCheckBox();
        cDatePicker1 = new org.components.controls.CDatePicker();
        cLabel4 = new org.components.controls.CLabel();
        cPanel3 = new org.components.containers.CPanel();
        cLabel6 = new org.components.controls.CLabel();
        cLabel12 = new org.components.controls.CLabel();
        cLabel13 = new org.components.controls.CLabel();
        cLabel14 = new org.components.controls.CLabel();
        cTextField8 = new org.components.controls.CTextField();
        cTextField9 = new org.components.controls.CTextField();
        cTextField10 = new org.components.controls.CTextField();
        cLabel11 = new org.components.controls.CLabel();
        cLabel16 = new org.components.controls.CLabel();
        cTextField12 = new org.components.controls.CTextField();
        cTextField7 = new org.components.controls.CTextField();
        cLabel17 = new org.components.controls.CLabel();
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
        cPanel1.setBounds(630, 360, 330, 160);
        add(cComboBox1);
        cComboBox1.setBounds(830, 130, 130, 23);

        cLabel2.setText("Salesman");
        cLabel2.setFont(new java.awt.Font("Tahoma", 0, 12));
        add(cLabel2);
        cLabel2.setBounds(780, 130, 60, 25);

        cComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Purchase", "Purchase Order", "Cinsignment In" }));
        cComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cComboBox2ActionPerformed(evt);
            }
        });
        add(cComboBox2);
        cComboBox2.setBounds(670, 10, 160, 23);

        cTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cTextField2ActionPerformed(evt);
            }
        });
        add(cTextField2);
        cTextField2.setBounds(830, 40, 130, 25);

        cPanel2.setLayout(null);

        cLabel3.setText("Supplier Details");
        cLabel3.setFont(new java.awt.Font("Tahoma", 1, 10));
        cPanel2.add(cLabel3);
        cLabel3.setBounds(10, 0, 130, 20);
        cPanel2.add(tsup);
        tsup.setBounds(10, 20, 210, 20);

        cTextArea2.setColumns(20);
        cTextArea2.setRows(10);
        jScrollPane3.setViewportView(cTextArea2);

        cPanel2.add(jScrollPane3);
        jScrollPane3.setBounds(10, 40, 210, 70);

        cCheckBox2.setText("Type");
        cPanel2.add(cCheckBox2);
        cCheckBox2.setBounds(10, 110, 140, 20);

        add(cPanel2);
        cPanel2.setBounds(0, 10, 230, 140);
        add(cDatePicker1);
        cDatePicker1.setBounds(830, 100, 130, 22);

        cLabel4.setText("Ref No");
        cLabel4.setFont(new java.awt.Font("Tahoma", 0, 12));
        add(cLabel4);
        cLabel4.setBounds(780, 40, 60, 25);

        cPanel3.setLayout(null);

        cLabel6.setText("Salesman");
        cLabel6.setFont(new java.awt.Font("Tahoma", 0, 12));
        cPanel3.add(cLabel6);
        cLabel6.setBounds(800, 70, 60, 25);

        cLabel12.setText("First Month");
        cLabel12.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel3.add(cLabel12);
        cLabel12.setBounds(10, 20, 90, 20);

        cLabel13.setText("Third Mo th");
        cLabel13.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel3.add(cLabel13);
        cLabel13.setBounds(220, 20, 80, 20);

        cLabel14.setText("Second month ");
        cLabel14.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel3.add(cLabel14);
        cLabel14.setBounds(110, 20, 100, 20);

        cTextField8.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel3.add(cTextField8);
        cTextField8.setBounds(10, 40, 90, 20);

        cTextField9.setFont(new java.awt.Font("Tahoma", 0, 10));
        cTextField9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cTextField9ActionPerformed(evt);
            }
        });
        cPanel3.add(cTextField9);
        cTextField9.setBounds(110, 40, 100, 20);

        cTextField10.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel3.add(cTextField10);
        cTextField10.setBounds(220, 40, 80, 20);

        cLabel11.setText("Sales Last 3 month of  selected supplier's product");
        cLabel11.setFont(new java.awt.Font("Tahoma", 0, 10));
        cPanel3.add(cLabel11);
        cLabel11.setBounds(0, 0, 310, 20);

        add(cPanel3);
        cPanel3.setBounds(20, 390, 320, 70);

        cLabel16.setText("Date");
        cLabel16.setFont(new java.awt.Font("Tahoma", 0, 12));
        add(cLabel16);
        cLabel16.setBounds(780, 100, 60, 25);

        cTextField12.setText("Purch no manually");
        cTextField12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cTextField12ActionPerformed(evt);
            }
        });
        add(cTextField12);
        cTextField12.setBounds(830, 10, 130, 25);

        cTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cTextField7ActionPerformed(evt);
            }
        });
        add(cTextField7);
        cTextField7.setBounds(830, 70, 130, 25);

        cLabel17.setText("GRN No");
        cLabel17.setFont(new java.awt.Font("Tahoma", 0, 12));
        add(cLabel17);
        cLabel17.setBounds(780, 70, 50, 25);

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
        jScrollPane2.setBounds(20, 170, 940, 180);

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

    private void cTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cTextField2ActionPerformed
    }//GEN-LAST:event_cTextField2ActionPerformed

    private void cTextField12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cTextField12ActionPerformed
    }//GEN-LAST:event_cTextField12ActionPerformed

    private void cTextField9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cTextField9ActionPerformed
    }//GEN-LAST:event_cTextField9ActionPerformed

    private void cTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cTextField7ActionPerformed
    }//GEN-LAST:event_cTextField7ActionPerformed

    public void uiety(PurchaseInvoice pi){

        
    }
    
    private void cButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cButton1ActionPerformed

        invoice.setId(EntityService.getKeys());
        for (Iterator<PurchaseInvoiceLineItem> it = lineItems.iterator(); it.hasNext();) {
            PurchaseInvoiceLineItem si = it.next();
            if (si.getId() == null || si.getId().isEmpty()) {
                it.remove();
            }
        }
        purService.getDao().save(invoice);
        invoice = PurchaseInvoice.createNewInvoice();
        lineItems = invoice.getLineItems();
        addToTable(lineItems);
        setnewrow();
        System.out.println("saved.....");
        
        
        
    }//GEN-LAST:event_cButton1ActionPerformed

    private void cdeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cdeleteActionPerformed
}//GEN-LAST:event_cdeleteActionPerformed

    private void cclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cclearActionPerformed
        tblInvoice.editCellAt(0, 4);

    }//GEN-LAST:event_cclearActionPerformed

    private void cComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cComboBox2ActionPerformed

    public void addToTable(List<PurchaseInvoiceLineItem> items) {
        TableUtil.cleardata(tblInvoice);
        if (items == null || items.isEmpty()) {
            return;
        }
        for (PurchaseInvoiceLineItem item : items) {
            TableUtil.addrowSR(tblInvoice, new Object[]{item.getId(), item.getItem().getCode(), item.getQty()
                    });
        }

        TableUtil.addrowSR(tblInvoice, new Object[]{});
    }
    Object xx;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CButton cButton1;
    private org.components.controls.CCheckBox cCheckBox2;
    private org.components.controls.CComboBox cComboBox1;
    private org.components.controls.CComboBox cComboBox2;
    private org.components.controls.CDatePicker cDatePicker1;
    private org.components.controls.CLabel cLabel10;
    private org.components.controls.CLabel cLabel11;
    private org.components.controls.CLabel cLabel12;
    private org.components.controls.CLabel cLabel13;
    private org.components.controls.CLabel cLabel14;
    private org.components.controls.CLabel cLabel15;
    private org.components.controls.CLabel cLabel16;
    private org.components.controls.CLabel cLabel17;
    private org.components.controls.CLabel cLabel2;
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
    private org.components.controls.CTextArea cTextArea2;
    private org.components.controls.CTextField cTextField1;
    private org.components.controls.CTextField cTextField10;
    private org.components.controls.CTextField cTextField11;
    private org.components.controls.CTextField cTextField12;
    private org.components.controls.CTextField cTextField2;
    private org.components.controls.CTextField cTextField4;
    private org.components.controls.CTextField cTextField5;
    private org.components.controls.CTextField cTextField6;
    private org.components.controls.CTextField cTextField7;
    private org.components.controls.CTextField cTextField8;
    private org.components.controls.CTextField cTextField9;
    private org.components.controls.CButton cclear;
    private org.components.controls.CButton cdelete;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private org.components.controls.TableEditable tblInvoice;
    private org.components.controls.CTextField tsup;
    // End of variables declaration//GEN-END:variables

    @Override
    public String getTabName() {
        return "Invoice Master";
    }

    @Override
    public JPanel getJPanel() {
        return this;
    }
}
