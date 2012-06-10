
/*
 * TransferOrderUI.java
 *
 * Created on Apr 14, 2012, 3:25:49 PM
 */
package org.biz.invoicesystem.ui.transactions;

import com.components.custom.ActionTask;
import com.components.custom.PagedPopUpPanel;
import java.util.ArrayList;
import java.util.List;
import org.biz.app.ui.util.uiEty;
import org.biz.dao.util.EntityService;
import org.biz.invoicesystem.entity.inventory.TransferOrder;
import org.biz.invoicesystem.entity.inventory.TransferOrderLineItem;
import org.biz.invoicesystem.entity.master.Item;
import org.biz.invoicesystem.entity.master.Shop;
import org.biz.invoicesystem.entity.master.UOM;
import org.biz.invoicesystem.entity.master.Warehouse;
import org.biz.invoicesystem.service.inventory.TransferOrderService;
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
    TransferOrderService orderService;
    ItemService itemService;
    WareHouseService wareHouseService;
    List<Item> items;
    List<Shop> shops;
    List<Warehouse> warehouses;
    TransferOrder transferOrder;
    TransferOrderLineItem transferOrderLineItem;
    List<TransferOrder> transferOrders;
    PagedPopUpPanel<Shop> shopFromPopUpPanel;
    PagedPopUpPanel<Shop> shoptoPopUpPanel;
    PagedPopUpPanel<Item> itemPopUpPanel;
    PagedPopUpPanel<Warehouse> wareHouseFromPopUpPanel;
    PagedPopUpPanel<Warehouse> wareHousetoPopUpPanel;

    /** Creates new form TransferOrderUI */
    public TransferOrderUI() {
        initComponents();
        init();
    }

    @Override
    public void init() {

        tblTransfer.setPropertiesEL(new String[]{"id","item.code","shopFrom.code","shopTo.code","wareHouseFrom.code","wareHouseTo.code","qty","itemMark"});
        tblTransfer.setColumnHeader(new String[]{"id","Item code","description","Shop To","Shop From","WareHouse From","WareHouse To","Qty","Item Mark"});
        transferOrder = new TransferOrder();
        orderService=new TransferOrderService();
        controlPanel1.setCrudController(this);
        shopService = new ShopService();
        shops = new ArrayList<Shop>();
        itemService = new ItemService();
        wareHouseService = new WareHouseService();

        items = new ArrayList<Item>();

        warehouses = new ArrayList<Warehouse>();

        shopFromPopUpPanel = new PagedPopUpPanel(tfromshop, shops, new String[]{"id", "code"},
                new String[]{"id", "Code"}) {

            @Override
            public void action() {
            }

            @Override
            public void search(String qry) {
                try {
                    shopFromPopUpPanel.setObjectToTable(shopService.getDao().getItemByCode(qry));

                } catch (Exception e) {
                    e.printStackTrace();
                }
//                super.search(qry);
            }
        };
        shopFromPopUpPanel.setSelectedProperty("code");

        itemPopUpPanel = new PagedPopUpPanel<Item>(titem, items, new String[]{"id", "code"}, new String[]{"id", "code"}) {

            @Override
            public void action() {
                super.action();
            }

            @Override
            public void search(String qry) {
                try {
                    itemPopUpPanel.setObjectToTable(itemService.getDao().findItemListByCode(qry));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        itemPopUpPanel.setSelectedProperty("code");

        shoptoPopUpPanel = new PagedPopUpPanel(ttoshop, shops, new String[]{"id", "code"}, new String[]{"id", "code"}) {

            @Override
            public void action() {
            }

            @Override
            public void search(String qry) {

                try {

                    shoptoPopUpPanel.setObjectToTable(shopService.getDao().getItemByCode(qry));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        shoptoPopUpPanel.setSelectedProperty("code");

        wareHousetoPopUpPanel = new PagedPopUpPanel(ttowarehouse, warehouses, new String[]{"id", "code"}, new String[]{"id", "code"}) {

            @Override
            public void action() {
            }

            @Override
            public void search(String qry) {
                wareHousetoPopUpPanel.setObjectToTable(wareHouseService.getDao().byCode(qry));
            }
        };
        wareHousetoPopUpPanel.setSelectedProperty("code");

        wareHouseFromPopUpPanel = new PagedPopUpPanel(tfromware, warehouses, new String[]{"id", "code"}, new String[]{"id", "code"}) {

            @Override
            public void action() {
                super.action();
            }

            @Override
            public void search(String qry) {
                wareHouseFromPopUpPanel.setObjectToTable(wareHouseService.getDao().byCode(qry));
            }
        };
        wareHouseFromPopUpPanel.setSelectedProperty("code");

        super.init();

        events();
    }

    @Override
    public void events() {
        titemmark.addaction(0, new ActionTask() {

            @Override
            public boolean action() {
                transferOrder.initialiseList();
//                transferOrder.getLastItem();
                //to test the generic behaviour
                // TODO---make use of generic method implemnetation
                TransferOrderLineItem item = transferOrderLineItem = uiety();
                //validate the transfer order line item 
                //add to the list
                item.setId(EntityService.getKeyStr());
                transferOrder.addToList(transferOrderLineItem);
                addToTable(transferOrder.getItemList());

                return true;
            }
        });
    }

    TransferOrderLineItem uiety() {
        TransferOrderLineItem to = new TransferOrderLineItem();
        to.setItem(itemPopUpPanel.getSelectedObject());
        to.setItemMark(uiEty.tcToStr(titemmark));
        to.setQty(uiEty.tcToDouble(tqty));
        to.setShopFrom(shopFromPopUpPanel.getSelectedObject());
        to.setShopTo(shoptoPopUpPanel.getSelectedObject());
        to.setWareHouseFrom(wareHouseFromPopUpPanel.getSelectedObject());
        to.setWareHouseTo(wareHousetoPopUpPanel.getSelectedObject());

        return to;
    }

    @Override
    public void save() {
        //must not be empty or null
        if (transferOrder.getLastItem()!=null || !transferOrder.getItemList().isEmpty()) {

                orderService.getDao().save(transferOrder);

        }
        clear();

    }

    public void addToTable(List<TransferOrderLineItem> items) {
        tblTransfer.modelToTable(items);
        tblTransfer.addrow(new Object[]{});
    }
    

    @Override
    public void clear() {

        tfromshop.setText("");
        tfromware.setText("");
        titem.setText("");
        titemmark.setText("");
        tqty.setText("");
        ttoshop.setText("");
        ttowarehouse.setText("");
        ttowarehouse.setText("");
        super.clear();
    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tfromware = new org.components.controls.CTextField();
        cLabel1 = new org.components.controls.CLabel();
        ttoshop = new org.components.controls.CTextField();
        cLabel2 = new org.components.controls.CLabel();
        titem = new org.components.controls.CTextField();
        cLabel3 = new org.components.controls.CLabel();
        tqty = new org.components.controls.CTextField();
        cLabel4 = new org.components.controls.CLabel();
        cLabel5 = new org.components.controls.CLabel();
        titemmark = new org.components.controls.CTextField();
        cLabel6 = new org.components.controls.CLabel();
        tfromshop = new org.components.controls.CTextField();
        cLabel7 = new org.components.controls.CLabel();
        ttowarehouse = new org.components.controls.CTextField();
        cLabel8 = new org.components.controls.CLabel();
        tunit = new com.components.custom.DropDownWithButton<UOM>();
        controlPanel1 = new com.components.custom.ControlPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTransfer = new org.components.controls.CTableMaster();

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
        add(titem);
        titem.setBounds(360, 60, 95, 25);

        cLabel3.setText("Item");
        add(cLabel3);
        cLabel3.setBounds(320, 30, 104, 25);
        add(tqty);
        tqty.setBounds(80, 200, 140, 30);

        cLabel4.setText("Qty");
        add(cLabel4);
        cLabel4.setBounds(80, 160, 104, 30);

        cLabel5.setText("Unit");
        add(cLabel5);
        cLabel5.setBounds(250, 150, 80, 30);
        add(titemmark);
        titemmark.setBounds(400, 200, 136, 30);

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
        add(tunit);
        tunit.setBounds(230, 200, 160, 30);
        add(controlPanel1);
        controlPanel1.setBounds(430, 10, 340, 30);

        tblTransfer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Item Code", "Item Mark", "Qty", "From Shop", "To Shop", "From Warehouse", "To Warehouse"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblTransfer);

        add(jScrollPane1);
        jScrollPane1.setBounds(30, 242, 600, 270);
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
    private com.components.custom.ControlPanel controlPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private org.components.controls.CTableMaster tblTransfer;
    private org.components.controls.CTextField tfromshop;
    private org.components.controls.CTextField tfromware;
    private org.components.controls.CTextField titem;
    private org.components.controls.CTextField titemmark;
    private org.components.controls.CTextField tqty;
    private org.components.controls.CTextField ttoshop;
    private org.components.controls.CTextField ttowarehouse;
    private com.components.custom.DropDownWithButton<UOM> tunit;
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
//added support for generic method behaviour
//sub classed the documents to be used as generic implementation
