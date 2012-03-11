/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ItemsDetailUI.java
 *
 * Created on May 26, 2011, 2:37:59 PM
 */

package invoicingsystem.master.list;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import org.biz.app.ui.util.TableUtil;
import org.biz.invoicesystem.entity.master.Item;
import org.biz.invoicesystem.service.master.ItemService;
import org.components.windows.TabPanelUI;

/**
 *
 * @author Administrator
 */
public class ItemsDetailUI extends TabPanelUI {

    Item selectedItem;
    List<Item> items;
    ItemService service;
    /** Creates new form ItemsDetailUI */
    public ItemsDetailUI() {
        initComponents();
    }

    @Override
    public void init() {
    service = new ItemService();
    items= new ArrayList<Item>();
    }

    public void addToTable(List<Item> items) {
        TableUtil.cleardata(cxTable1);
        for (Item item : items) {
            addToTable(item);
        }
    }

    public void addToTable(Item item) {
//        TableUtil.addrow(cxTable1, new Object[]{item.getId(), item.getCode(), item.getName(), item.getDescription()});
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        cxTable1 = new org.components.controls.CxTable();

        cxTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Code", "Name", "Description"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(cxTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                .addGap(21, 21, 21))
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public String getTabName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public JPanel getJPanel() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CxTable cxTable1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

}
