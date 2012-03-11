
package org.biz.invoicesystem.master.ui;

import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingWorker;
import org.biz.app.ui.util.MessageBoxes;
import org.biz.app.ui.util.TableUtil;
import org.biz.app.ui.util.uiEty;
import org.biz.dao.util.EntityService;
import org.biz.invoicesystem.entity.master.Item;
import org.biz.invoicesystem.service.master.ItemService;
import org.components.windows.TabPanelUI;

 
public class ItemListUi extends TabPanelUI   {

    private ItemService itemService;
    private ItemMasterTab mastertab;
    private ItemMasterUI2 formUi;
    
     int currentPageNo=0;
    public ItemListUi() {
        initComponents();
    init();
    }

    @Override
    public void init() {
     itemService=new ItemService();
     
     callVeryFirstPage();
     
    }

    public void callVeryFirstPage(){
    
        try {
           currentPageNo=0; 
       loadItemList2Tbl(currentPageNo,getDynamicQuery());  
        cPageCount.setText(""+currentPageNo+" OF "+pageCount());
        } catch (Exception e) {
        e.printStackTrace();
        }
    }
    
    public int pageCount(){
    int x=0;
        try {
      x=itemService.getDao().getListSize()%FormMaster.GRID_LIST_SIZE;      
        } catch (Exception e) {
        e.printStackTrace();}
        return x;
    }
    
    public String getDynamicQuery(){
    String qq="";
    
    if(uiEty.tcToStr(cItmDescription)==null ||uiEty.tcToStr(cItmDescription).trim().equals("")){
    
        qq="SELECT e FROM Item e  order by e.id asc";
    }else{
qq="SELECT e FROM Item e Where e.description LIKE '%"+uiEty.tcToStr(cItmDescription)+"%'  order by e.id asc";
    }
    
    
    return qq;
    }
    
    public void loadItemList2Tbl(final int currentPageNo,final String dynamicQuery){
     
        SwingWorker<List<Item>,Object> sw=new SwingWorker<List<Item>,Object>() {

            @Override
            protected List<Item> doInBackground() throws Exception {
        //     List<Item> items=getItemService().getDao().selectAll();
                
             List<Item> items=getItemService().getDao().getPaginatedData(currentPageNo,dynamicQuery);
                return items;
            }

            @Override
            protected void done() {
                try {
                    TableUtil.cleardata(tblItemList);
                   if(get()==null){
                   return;
                   } 
            List<Item> items=get();   
                    for (Item i : items) {
                    TableUtil.addrowSR(tblItemList,new Object[]{i.getCode(),i.getDescription(),i.getCost(),i.getUnit1SalesPrice(),i.getWholesalePrice()});                            
         
                    }
                           
                } catch (Exception e) {
            e.printStackTrace();
                }
            }
            
            
        };
        
        sw.execute();
                
    
    } 
    
    public void callFormUi(){
        try {
  
   getMastertab().getItemTabPane().setSelectedIndex(getMastertab().getItemTabPane().indexOfTab(ItemMasterTab.FormUiTabName));
           
        } catch (Exception e) {
        e.printStackTrace();}
    }
  ////////////////////////////////////////////////
  //when user double click on item row in table...bring him to form view the particular item... 
  public void callItemByCode(String itemCode){
  
      try {
   Item item=itemService.getDao().findItemByCode(itemCode);
  formUi.clearMaster();
   formUi.entity2Ui(item);
   callFormUi();
      } catch (Exception e) {
      e.printStackTrace();
      }
  
  }
      
 ///////////////////////////////////////////////    
    
    
    ///////////////////////////////////////////
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cPanel1 = new org.components.containers.CPanel();
        jLabel1 = new javax.swing.JLabel();
        cItmDescription = new org.components.controls.CTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblItemList = new org.components.controls.CxTable();
        cRefreshItem = new org.components.controls.CButton();
        cDeleteItemBtn = new org.components.controls.CButton();
        cClose = new org.components.controls.CButton();
        cNewItemBtn = new org.components.controls.CButton();
        cButton1 = new org.components.controls.CButton();
        cButton2 = new org.components.controls.CButton();
        cButton3 = new org.components.controls.CButton();
        cButton4 = new org.components.controls.CButton();
        cPageCount = new org.components.controls.CTextFieldPopUp();
        cCopyItem = new org.components.controls.CButton();

        setLayout(null);

        cPanel1.setLayout(null);

        jLabel1.setText("Item Description Search");
        cPanel1.add(jLabel1);
        jLabel1.setBounds(40, 30, 130, 20);

        cItmDescription.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cItmDescriptionActionPerformed(evt);
            }
        });
        cItmDescription.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cItmDescriptionKeyTyped(evt);
            }
        });
        cPanel1.add(cItmDescription);
        cItmDescription.setBounds(170, 30, 470, 25);

        tblItemList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Code", "Description", "Cost Price", "Sales Price", "Wh.Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblItemList.setColumnSelectionAllowed(true);
        tblItemList.setFont(new java.awt.Font("Tahoma", 0, 14));
        tblItemList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblItemListMouseClicked(evt);
            }
        });
        tblItemList.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tblItemListKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(tblItemList);
        tblItemList.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblItemList.getColumnModel().getColumn(0).setMinWidth(85);
        tblItemList.getColumnModel().getColumn(0).setPreferredWidth(85);
        tblItemList.getColumnModel().getColumn(0).setMaxWidth(85);
        tblItemList.getColumnModel().getColumn(1).setMinWidth(200);
        tblItemList.getColumnModel().getColumn(1).setPreferredWidth(200);
        tblItemList.getColumnModel().getColumn(1).setMaxWidth(200);

        cPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(20, 90, 760, 278);

        cRefreshItem.setText("Refresh");
        cRefreshItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cRefreshItemActionPerformed(evt);
            }
        });
        cPanel1.add(cRefreshItem);
        cRefreshItem.setBounds(360, 380, 121, 49);

        cDeleteItemBtn.setText("Delete");
        cDeleteItemBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cDeleteItemBtnActionPerformed(evt);
            }
        });
        cPanel1.add(cDeleteItemBtn);
        cDeleteItemBtn.setBounds(220, 380, 121, 49);

        cClose.setText("Close");
        cClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cCloseActionPerformed(evt);
            }
        });
        cPanel1.add(cClose);
        cClose.setBounds(620, 380, 110, 50);

        cNewItemBtn.setText("New ");
        cNewItemBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cNewItemBtnActionPerformed(evt);
            }
        });
        cPanel1.add(cNewItemBtn);
        cNewItemBtn.setBounds(80, 380, 121, 49);

        cButton1.setText("<");
        cButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cButton1ActionPerformed(evt);
            }
        });
        cPanel1.add(cButton1);
        cButton1.setBounds(280, 60, 41, 23);

        cButton2.setText("<<");
        cButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cButton2ActionPerformed(evt);
            }
        });
        cPanel1.add(cButton2);
        cButton2.setBounds(220, 60, 49, 23);

        cButton3.setText(">>");
        cButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cButton3ActionPerformed(evt);
            }
        });
        cPanel1.add(cButton3);
        cButton3.setBounds(480, 60, 49, 23);

        cButton4.setText(">");
        cButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cButton4ActionPerformed(evt);
            }
        });
        cPanel1.add(cButton4);
        cButton4.setBounds(430, 60, 41, 23);
        cPanel1.add(cPageCount);
        cPageCount.setBounds(330, 60, 90, 20);

        cCopyItem.setText("Copy");
        cCopyItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cCopyItemActionPerformed(evt);
            }
        });
        cPanel1.add(cCopyItem);
        cCopyItem.setBounds(500, 380, 110, 50);

        add(cPanel1);
        cPanel1.setBounds(0, 0, 790, 480);
    }// </editor-fold>//GEN-END:initComponents

    private void cRefreshItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cRefreshItemActionPerformed
        //refresh the item list and reinsert item tabl list...
         try {
        loadItemList2Tbl(0,getDynamicQuery());  
        } catch (Exception e) {
        e.printStackTrace();
        }
    }//GEN-LAST:event_cRefreshItemActionPerformed

    private void cDeleteItemBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cDeleteItemBtnActionPerformed
        //call item master form with selected item to delete the item...
         try {
      String itemid=(String) tblItemList.getValueAt(tblItemList.getSelectedRow(),0);
          if(itemid==null || itemid.equals("")){
           MessageBoxes.wrnmsg(null,"Please Select an Item.","Empty Item");                 
                return;
            }  
      Item exist=itemService.getDao().findItemByCode(itemid);
       if(exist==null || exist.getCode().equals("")){
           MessageBoxes.wrnmsg(null,"No Item Found.","Empty Item");                 
                return;
            }         
    String[] ObjButtons = { "Yes", "No" };
  int PromptResult = JOptionPane.showOptionDialog(null, "Are you want to delete ?", "Item Form", -1, 2, null, ObjButtons, ObjButtons[1]);
     
      
     if(PromptResult==0){ 
  itemService.getDao().delete(exist);
 
     }
        } catch (Exception e) {
        e.printStackTrace();
        MessageBoxes.errormsg(null, e.getMessage(), "Error");
        }
    }//GEN-LAST:event_cDeleteItemBtnActionPerformed

    private void cCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cCloseActionPerformed
        // close this form...
            try {
//      String itemid=(String) tblItemList.getValueAt(tblItemList.getSelectedRow(),0);
//          if(itemid==null || itemid.equals("")){
//           MessageBoxes.wrnmsg(null,"Please Select an Item.","Empty Item");                 
//                return;
//            }  
            
   
        } catch (Exception e) {
        e.printStackTrace();
        MessageBoxes.errormsg(null, e.getMessage(), "Error");
        }
    }//GEN-LAST:event_cCloseActionPerformed

    private void cItmDescriptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cItmDescriptionActionPerformed
        // TODO add your handling code here:
         try {
          
        } catch (Exception e) {
        e.printStackTrace();
        }
    }//GEN-LAST:event_cItmDescriptionActionPerformed

    private void cNewItemBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cNewItemBtnActionPerformed
      //calling item master form...
         try {
    callFormUi();      
        } catch (Exception e) {
        e.printStackTrace();
        }
    }//GEN-LAST:event_cNewItemBtnActionPerformed

    private void tblItemListKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblItemListKeyTyped
      if(evt.getKeyChar()==KeyEvent.VK_ENTER){
      
   String itemCode=(String) tblItemList.getValueAt(tblItemList.getSelectedRow(),0);
   callItemByCode(itemCode);
   
      }
    }//GEN-LAST:event_tblItemListKeyTyped

    private void cCopyItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cCopyItemActionPerformed
            try {
                if(tblItemList.getRowCount()==0){
                return;
                }
      String itemid=(String) tblItemList.getValueAt(tblItemList.getSelectedRow(),0);
          if(itemid==null || itemid.equals("")){
           MessageBoxes.wrnmsg(null,"Please Select an Item.","Empty Item");                 
           return;
            }  
      Item exist=itemService.getDao().findItemByCode(itemid);
       if(exist==null || exist.getCode().equals("")){
           MessageBoxes.wrnmsg(null,"No Item Found.","Empty Item");                 
                return;
            }       
  String[] ObjButtons = { "Yes", "No" };
  int PromptResult = JOptionPane.showOptionDialog(null, "Are you want to Copy this Item ?", "Item Form", -1, 2, null, ObjButtons, ObjButtons[1]);
     
      
     if(PromptResult==0){    
      exist.setId(EntityService.getEntityService().getKey(""));                
        String oldCode=exist.getCode();
     exist.setCode("Copy "+oldCode);                 
     
    
     itemService.getDao().save(exist);    
     getFormUi().entity2Ui(exist);
   callFormUi();  
     }
      
        } catch (Exception e) {
        e.printStackTrace();
        MessageBoxes.errormsg(null, e.getMessage(), "Error");
        }
    }//GEN-LAST:event_cCopyItemActionPerformed

    private void cButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cButton4ActionPerformed
     
       TableUtil.cleardata(tblItemList); 
   currentPageNo+=1;  
   
   
   if(currentPageNo>pageCount()){
  currentPageNo=pageCount();
        
   }
        loadItemList2Tbl(currentPageNo,getDynamicQuery());
          cPageCount.setText(""+currentPageNo+" OF "+pageCount());
    }//GEN-LAST:event_cButton4ActionPerformed

    private void cButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cButton1ActionPerformed
      TableUtil.cleardata(tblItemList); 
   currentPageNo-=1; 
   
   if(currentPageNo<0){
  currentPageNo=0;
        
   }
   
        loadItemList2Tbl(currentPageNo,getDynamicQuery());
          cPageCount.setText(""+currentPageNo+" OF "+pageCount());
    }//GEN-LAST:event_cButton1ActionPerformed

    private void cButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cButton3ActionPerformed
      
     TableUtil.cleardata(tblItemList); 
   currentPageNo=pageCount();      
        loadItemList2Tbl(currentPageNo,getDynamicQuery());
          cPageCount.setText(""+currentPageNo+" OF "+pageCount());
    }//GEN-LAST:event_cButton3ActionPerformed

    private void cButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cButton2ActionPerformed
    
     TableUtil.cleardata(tblItemList); 
   currentPageNo=0;      
        loadItemList2Tbl(currentPageNo,getDynamicQuery());
          cPageCount.setText(""+currentPageNo+" OF "+pageCount());
    }//GEN-LAST:event_cButton2ActionPerformed

    private void cItmDescriptionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cItmDescriptionKeyTyped
        
               loadItemList2Tbl(currentPageNo,getDynamicQuery());

    }//GEN-LAST:event_cItmDescriptionKeyTyped

    private void tblItemListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblItemListMouseClicked
        try {
         if(evt.getClickCount()==2){  
             
   String itemCode=(String) tblItemList.getValueAt(tblItemList.getSelectedRow(),0);
   callItemByCode(itemCode);
        
         }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tblItemListMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CButton cButton1;
    private org.components.controls.CButton cButton2;
    private org.components.controls.CButton cButton3;
    private org.components.controls.CButton cButton4;
    private org.components.controls.CButton cClose;
    private org.components.controls.CButton cCopyItem;
    private org.components.controls.CButton cDeleteItemBtn;
    private org.components.controls.CTextField cItmDescription;
    private org.components.controls.CButton cNewItemBtn;
    private org.components.controls.CTextFieldPopUp cPageCount;
    private org.components.containers.CPanel cPanel1;
    private org.components.controls.CButton cRefreshItem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private org.components.controls.CxTable tblItemList;
    // End of variables declaration//GEN-END:variables

    @Override
    public String getTabName() {
       return "Customer List";
    }

    @Override
    public JPanel getJPanel() {
    
        return this;    
    }

    /**
     * @return the itemService
     */
    public ItemService getItemService() {
        return itemService;
    }

    /**
     * @param itemService the itemService to set
     */
    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    /**
     * @return the mastertab
     */
    public ItemMasterTab getMastertab() {
        return mastertab;
    }

    /**
     * @param mastertab the mastertab to set
     */
    public void setMastertab(ItemMasterTab mastertab) {
        this.mastertab = mastertab;
    }

    /**
     * @return the formUi
     */
    public ItemMasterUI2 getFormUi() {
        return formUi;
    }

    /**
     * @param formUi the formUi to set
     */
    public void setFormUi(ItemMasterUI2 formUi) {
        this.formUi = formUi;
    }
}
