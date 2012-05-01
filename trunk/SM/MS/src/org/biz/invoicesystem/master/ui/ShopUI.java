/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ShopUI.java
 *
 * Created on Apr 30, 2012, 8:33:09 PM
 */
package org.biz.invoicesystem.master.ui;

import javax.swing.JPanel;
import org.biz.app.ui.util.uiEty;
import org.biz.invoicesystem.entity.master.Shop;
import org.biz.invoicesystem.service.master.ShopService;
import org.biz.invoicesystem.system.SystemUtil;
import org.components.windows.TabPanelUI;

/**
 *
 * @author d
 */
public class ShopUI extends TabPanelUI {

    /** Creates new form ShopUI */
    public ShopUI() {
        initComponents();
        init();
    }
    Shop shop;
    ShopService service;
    

    @Override
    public void init() {
        controlPanel1.setCrudController(this);
        shop = new Shop();
    service =new ShopService();    
        
    }

    @Override
    public void save() {
        uiToety();
        shop.setId(SystemUtil.timeStampKey());
        service.getDao().save(shop);        
        shop = new Shop();
    }

    private void uiToety() {
        shop.setCode(uiEty.tcToStr(cTextField1));

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        controlPanel1 = new com.components.custom.ControlPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        cxTable1 = new org.components.controls.CxTable();
        cTextField1 = new org.components.controls.CTextField();

        jScrollPane1.setViewportView(cxTable1);

        cTextField1.setText("cTextField1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(controlPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(cTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(cTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(controlPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(151, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CTextField cTextField1;
    private com.components.custom.ControlPanel controlPanel1;
    private org.components.controls.CxTable cxTable1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    @Override
    public String getTabName() {
        return "Shop ";
    }

    @Override
    public JPanel getJPanel() {
        return this;
    }
}
