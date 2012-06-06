/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Table.java
 *
 * Created on May 6, 2010, 10:48:54 AM
 */
package org.components.parent.controls;

import java.util.List;
import javax.swing.JTable;
import org.biz.app.ui.util.TableUtil;
import org.jdesktop.swingx.JXTable;

/**
 *
 * @author nano
 */
public class PxTable extends JXTable {

    String[] propertiesEL;

    public String[] getPropertiesEL() {
        return propertiesEL;
    }

    public void setPropertiesEL(String[] propertiesEL) {
        this.propertiesEL = propertiesEL;
//     TableUtil.createTableModel(this, propertiesEL);   
    }

    public void setColumnHeader(String[] title) {

        TableUtil.createTableModel(this, title);
    }
    Class modelClass;

    public Class getModelClass() {
        return modelClass;
    }

    public void setModelClass(Class modelClass) {
        this.modelClass = modelClass;
    }

    /** Creates new form BeanForm */
    public PxTable() {
        initComponents();
    }

    public void clear() {
        TableUtil.cleardata(this);
    }

    public void addModelToTable(Object obj) {
        TableUtil.addModelToTable(obj, this);
    }

    public void modelToTable(List list) {
        clear();
        if(list!=null && list.isEmpty() )return;
        for (Object row : list) {
        TableUtil.addModelToTable(row, this);
        }


    }

    public void addrow(Object[] row){
    TableUtil.addrow(this, row);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        setRowHeight(25);
        setRowHeight(12);
        setTerminateEditOnFocusLost(false);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
