/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TransferOrderUI.java
 *
 * Created on Apr 14, 2012, 3:25:49 PM
 */
package org.biz.invoicesystem.ui.transactions;

import com.components.custom.PagedPopUpPanel;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.biz.invoicesystem.entity.master.Item;
import org.biz.invoicesystem.entity.master.Shop;
import org.biz.invoicesystem.entity.master.Warehouse;
import org.biz.invoicesystem.service.master.ItemService;
import org.biz.invoicesystem.service.master.ShopService;
import org.biz.invoicesystem.service.master.WareHouseService;
import org.components.windows.TabPanelUI;

/**
 *
 * @author nnjj
 */
public class TransferOrderUI extends TabPanelUI {

    
    ShopService shopService;
    ItemService itemService;
    WareHouseService wareHouseService;
    List<Item> items;
    List<Shop> shops;
    List<Warehouse> warehouses; 
    
    PagedPopUpPanel shopPopUpPanel;
    PagedPopUpPanel shoptoPopUpPanel;
    PagedPopUpPanel itemPopUpPanel;
    PagedPopUpPanel itemtoPopUpPanel;
    PagedPopUpPanel wareHousePopUpPanel;
    PagedPopUpPanel wareHousetoPopUpPanel;
    
    /** Creates new form TransferOrderUI */
    public TransferOrderUI() {
        initComponents();
        init();
    }

    @Override
    public void init() {
    shopService =new ShopService();
    shops=Collections.emptyList();
    shopService.setList(shops);
    itemService = new ItemService();
    wareHouseService = new WareHouseService();
    
    items=Collections.emptyList();
  
    warehouses=Collections.emptyList();
    
    shopPopUpPanel = new PagedPopUpPanel(ttoshop) {

            @Override
            public void action() {
                super.action();
            }

            @Override
            public void search(String qry) {
                try {
                                    
                    shopService.getDao().getItemByCode(qry);

                } catch (Exception e) {
                e.printStackTrace();
                }
                  
                
//                super.search(qry);
            }

            @Override
            public Object[] data(Object item) {
                return super.data(item);
            }
    
    };
    shopPopUpPanel.setPropertiesEL(new String[]{"id","code"});
//        shopPopUpPanel.setTitle(new String[]{"id","Code"});
    wareHousePopUpPanel= new PagedPopUpPanel(tfromware) {

            @Override
            public void action() {
                super.action();
            }

            @Override
            public void search(String qry) {
                super.search(qry);
            }

            @Override
            public Object[] data(Object item) {
                return super.data(item);
            }
    
    };

    itemPopUpPanel= new PagedPopUpPanel(ttoshop) {

            @Override
            public void action() {
                super.action();
            }

            @Override
            public void search(String qry) {
                super.search(qry);
            }

            @Override
            public Object[] data(Object item) {
                return super.data(item);
            }
    
    };
    
    shoptoPopUpPanel = new PagedPopUpPanel(ttoshop) {

            @Override
            public void action() {
                super.action();
            }

            @Override
            public void search(String qry) {
                super.search(qry);
            }

            @Override
            public Object[] data(Object item) {
                return super.data(item);
            }
    
    };
    
    wareHousetoPopUpPanel =new PagedPopUpPanel(ttowarehouse) {

            @Override
            public void action() {
                super.action();
            }

            @Override
            public void search(String qry) {
                super.search(qry);
            }

            @Override
            public Object[] data(Object item) {
                return super.data(item);
            }
    
    };

    itemPopUpPanel =new PagedPopUpPanel(ttoshop) {

            @Override
            public void action() {
                super.action();
            }

            @Override
            public void search(String qry) {
                
                super.search(qry);
            }

            @Override
            public Object[] data(Object item) {
                return super.data(item);
            }
    
    };

    
    
    
    
    super.init();
        
        
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tfromware = new org.components.controls.CTextField();
        cLabel1 = new org.components.controls.CLabel();
        ttoshop = new org.components.controls.CTextField();
        cLabel2 = new org.components.controls.CLabel();
        cTextField3 = new org.components.controls.CTextField();
        cLabel3 = new org.components.controls.CLabel();
        cTextField4 = new org.components.controls.CTextField();
        cLabel4 = new org.components.controls.CLabel();
        cTextField5 = new org.components.controls.CTextField();
        cLabel5 = new org.components.controls.CLabel();
        cTextField6 = new org.components.controls.CTextField();
        cLabel6 = new org.components.controls.CLabel();
        tfromshop = new org.components.controls.CTextField();
        cLabel7 = new org.components.controls.CLabel();
        ttowarehouse = new org.components.controls.CTextField();
        cLabel8 = new org.components.controls.CLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        cTable1 = new org.components.controls.CTable();

        setLayout(null);

        tfromware.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfromwareActionPerformed(evt);
            }
        });
        add(tfromware);
        tfromware.setBounds(60, 120, 95, 25);

        cLabel1.setText("From warehouse");
        add(cLabel1);
        cLabel1.setBounds(20, 90, 130, 25);
        add(ttoshop);
        ttoshop.setBounds(221, 63, 95, 20);

        cLabel2.setText("To shop");
        add(cLabel2);
        cLabel2.setBounds(180, 37, 104, 25);
        add(cTextField3);
        cTextField3.setBounds(360, 60, 95, 25);

        cLabel3.setText("Item");
        add(cLabel3);
        cLabel3.setBounds(320, 30, 104, 25);
        add(cTextField4);
        cTextField4.setBounds(80, 200, 140, 30);

        cLabel4.setText("Qty");
        add(cLabel4);
        cLabel4.setBounds(80, 160, 104, 30);
        add(cTextField5);
        cTextField5.setBounds(250, 200, 80, 30);

        cLabel5.setText("Unit");
        add(cLabel5);
        cLabel5.setBounds(250, 150, 80, 30);
        add(cTextField6);
        cTextField6.setBounds(350, 200, 136, 30);

        cLabel6.setText("Item Mark");
        add(cLabel6);
        cLabel6.setBounds(360, 150, 104, 30);
        add(tfromshop);
        tfromshop.setBounds(50, 60, 95, 25);

        cLabel7.setText("From Shop");
        add(cLabel7);
        cLabel7.setBounds(10, 37, 104, 25);
        add(ttowarehouse);
        ttowarehouse.setBounds(220, 120, 95, 25);

        cLabel8.setText("To warehouse");
        add(cLabel8);
        cLabel8.setBounds(170, 90, 104, 25);

        cTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Item Code", "Item Name", "Qty", "UOM", "Shop From", "Shop To", "Ware House From", "Ware House To"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Short.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(cTable1);
        cTable1.getColumnModel().getColumn(0).setResizable(false);
        cTable1.getColumnModel().getColumn(2).setResizable(false);
        cTable1.getColumnModel().getColumn(3).setResizable(false);

        add(jScrollPane1);
        jScrollPane1.setBounds(10, 260, 770, 250);
    }// </editor-fold>//GEN-END:initComponents

    private void tfromwareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfromwareActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfromwareActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CLabel cLabel1;
    private org.components.controls.CLabel cLabel2;
    private org.components.controls.CLabel cLabel3;
    private org.components.controls.CLabel cLabel4;
    private org.components.controls.CLabel cLabel5;
    private org.components.controls.CLabel cLabel6;
    private org.components.controls.CLabel cLabel7;
    private org.components.controls.CLabel cLabel8;
    private org.components.controls.CTable cTable1;
    private org.components.controls.CTextField cTextField3;
    private org.components.controls.CTextField cTextField4;
    private org.components.controls.CTextField cTextField5;
    private org.components.controls.CTextField cTextField6;
    private javax.swing.JScrollPane jScrollPane1;
    private org.components.controls.CTextField tfromshop;
    private org.components.controls.CTextField tfromware;
    private org.components.controls.CTextField ttoshop;
    private org.components.controls.CTextField ttowarehouse;
    // End of variables declaration//GEN-END:variables

    @Override
    public String getTabName() {
        return "Transfer Order UI ";
    }
    
    /**
     * title
     * 
     * Spring MVC 3.1, jqGrid, and Spring Data JPA Integration Guide (Part 1) 
     * http://krams915.blogspot.com/2012/01/spring-mvc-31-jqgrid-and-spring-data_1887.html
     * 
     * keys
     */
}
