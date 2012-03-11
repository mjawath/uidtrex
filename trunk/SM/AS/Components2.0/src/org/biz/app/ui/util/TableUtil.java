/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.app.ui.util;

import java.awt.event.KeyEvent;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.text.JTextComponent;
import org.components.parent.controls.editors.CellEditor;

/**
 *
 * @author nano
 */
public class TableUtil {

    public final static String newRowID = "cxMxCy%%76";

    public static String getNewRowId() {
        return System.currentTimeMillis() + "-" + Math.ceil(Math.random()*10);
    }

    public static void createTableModel(JTable jTable, String[] columns, final Class[] columntypes) {

        DefaultTableModel dtm = new DefaultTableModel(
                new Object[][]{}, columns) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            Class[] types = columntypes;

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };

        jTable.setModel(dtm);

    }

    public static void createTableModel(JTable jTable, String[] columns, final Class[] columntypes, final boolean editable) {

        DefaultTableModel dtm = new DefaultTableModel(
                new Object[][]{}, columns) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return editable;
            }
            Class[] types = columntypes;

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        jTable.setModel(dtm);

    }

    public static DefaultTableModel getdtm(JTable jTable) {
        return (DefaultTableModel) jTable.getModel();
    }
    /*
     * add new row to o index for table
     */

    public static void addnewrow(JTable jTable, Vector row) {

        getdtm(jTable).insertRow(0, row);

    }
    
    public static void addnewrow(JTable jTable) {

        getdtm(jTable).addRow( new Object[]{});

    }

    public static void hideColumn(JTable jt, String st) {

        jt.getColumn(st).setWidth(0);
        jt.getColumn(st).setMinWidth(0);
        jt.getColumn(st).setMaxWidth(0);
    }
    /*
     * add new row for table
     */

    public static void addrow(JTable jTable, Vector row) {
        jTable.scrollRectToVisible(jTable.getCellRect(jTable.getRowCount(), 0, true));
        getdtm(jTable).addRow(row);

    }



    
    public static void addrow(JTable jTable, Object[] row) {
        getdtm(jTable).addRow(row);
        jTable.scrollRectToVisible(jTable.getCellRect(jTable.getRowCount(), 0, true));
        jTable.scrollRectToVisible(jTable.getCellRect(jTable.getRowCount(), 0, true));
    }
//First row will be visble
    public static void addrowSR(JTable jTable, Object[] row) {
//    jTable.scrollRectToVisible(jTable.getCellRect(jTable.getRowCount() - 1, 0, true));
        getdtm(jTable).addRow(row);

    }

    /*
     * insert a new row into specific index
     * 
     */
    public static void insertrow(JTable jTable, Vector row, int point) {
        point = jTable.convertRowIndexToModel(point);
        getdtm(jTable).insertRow(point, row);

    }

    public static void insertrow(JTable jTable, Object[] row, int point) {
        point = jTable.convertRowIndexToModel(point);
        getdtm(jTable).insertRow(point, row);

    }
    /*
     * remove row
     */

    public static void replacerow(JTable jTable, Vector row, int point) {
        point = jTable.convertRowIndexToModel(point);
        getdtm(jTable).removeRow(point);
        getdtm(jTable).insertRow(point, row);

    }

    public static void replacerow(JTable jTable, Object[] row, int point) {
        point = jTable.convertRowIndexToModel(point);
        getdtm(jTable).removeRow(point);
        getdtm(jTable).insertRow(point, row);

    }

    
    public static void replacerowValues(JTable jTable, Object[] row, int point) {
        point = jTable.convertRowIndexToModel(point);
        for (int i = 0; i < row.length; i++) {
            Object va = row[i];
            getdtm(jTable).setValueAt(va, point, i);
        }
        
        
    }
    
    public static void removerow(JTable jTable, int point) {
        point = jTable.convertRowIndexToModel(point);
        getdtm(jTable).removeRow(point);

    }

    public static void findRemoverow(JTable jTable, String code, int point) {
        Vector data = TableUtil.getDataVector(jTable);
        point = jTable.convertRowIndexToModel(point);
        for (int i = 0; i < data.size(); i++) {
            Vector r = (Vector) data.elementAt(i);
            Object ob = r.get(0);
            if (ob.equals(code)) {
                TableUtil.getdtm(jTable).removeRow(i);
                break;
            }
        }

    }

    /*
     * crates new table model
     */
    public static void createTableModel(JTable jTable, Vector data, Vector columns, final Class[] columntypes) {


        DefaultTableModel dtm = new DefaultTableModel(
                data, columns) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            Class[] types = columntypes;

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };

        jTable.setModel(dtm);
    }
    /*
     * fill data by specifying data vector
     */

    public static void filldata(JTable jTable, Vector data) {
        cleardata(jTable);
        for (Object object : data) {
            getdtm(jTable).addRow((Vector) object);
        }

    }

    public static void filldataObj(JTable jTable, Object[][] data) {
        cleardata(jTable);
        for (Object[] object : data) {
            getdtm(jTable).addRow(object);
        }

    }

    /*
     * get tables datavector..
     */
    public static Vector getDataVector(JTable jTable) {

        return getdtm(jTable).getDataVector();
    }

    public static Object[][] getDataObject(JTable jTable) {

        Vector rows = getdtm(jTable).getDataVector();
        Object data[][] = new Object[rows.size()][];

        int x = 0;
        for (Object row : rows) {
            data[x] = ((Vector) row).toArray();
        }
        return data;
//           int x=0;
//
//        for (Object[] row : data) {
//            row=getdtm(jTable).getDataVector().toArray();
//            x++;
//        }
//            return data;
    }

//     public static  Object getfilteredmodelvalueat(JTable jt, int srow,int scol){
//
////         return jt.getRowSorter().getValueAt(jt.convertRowIndexToModel( srow),jt.convertColumnIndexToModel(scol));
//     }
    public static void cleardata(JTable jTable) {
        jTable.clearSelection();
        DefaultTableModel dtm = getdtm(jTable);
        while (dtm.getRowCount() > 0) {
            dtm.removeRow(dtm.getRowCount() - 1);
        }
    }
//give Views column index and get the models column index

    public static int selectedViewToModelColumn(JTable jt) {
        return jt.convertColumnIndexToModel(jt.getSelectedColumn());
    }
//give models column index and get the views column index

    public static int getViewColumnIndex(JTable jt, int modelcolumnindex) {
        return jt.convertColumnIndexToView(modelcolumnindex);
    }

    public static Object getModelsValueAt(JTable jt, int modelrowindex, int modelcolindex) {
// return jt.getValueAt(jt.convertRowIndexToView(modelrowindex), jt.convertColumnIndexToView(modelcolindex));
//        //above return stracnge values because of orginal model sorterd model
        return jt.getValueAt(modelrowindex, jt.convertColumnIndexToView(modelcolindex));
    }

    public static Object getSelectedModelsValueAt(JTable jt, int modelcolindex) {
        int sr=jt.getSelectedRow();
        if(sr<0){return null;}
        return jt.getValueAt(sr, jt.convertColumnIndexToView(modelcolindex));
    }

    public static Object getSelectedValue(JTable jt) {
        if (jt.getSelectedRow() > -1) {
            return jt.getValueAt(jt.getSelectedRow(), jt.getSelectedColumn());
        }
        return null;
    }
    //get selected column return specified column value of selected row  if  null  retruns null

    public static Object getSelectedValue(JTable jt, int column) {
        if (jt.getSelectedRow() > -1) {
            if (jt.getEditingColumn() == column) {
                TableCellEditor ce = jt.getCellEditor();
                try {

                    JComponent jc = ((CellEditor) ce).getComponent();
                    if (jc instanceof JTextComponent) {
                        return ((JTextComponent) jc).getText();

                    }
                    if (jc instanceof JComboBox) {
                        return ((JComboBox) jc).getSelectedItem();

                    }
                } catch (Exception e) {
                    return null;
                }
                return null;
            }
            return jt.getValueAt(jt.getSelectedRow(), column);
        }
        return null;

    }

   
    //get selected column return specified column value of selected row  if  null  retruns null

    public static Object getValueat(JTable jt,int row, int column) {
        if (row > -1) {
            if (jt.getEditingColumn() == column) {
                TableCellEditor ce = jt.getCellEditor();
                try {

                    JComponent jc = ((CellEditor) ce).getComponent();
                    if (jc instanceof JTextComponent) {
                        return ((JTextComponent) jc).getText();

                    }
                    if (jc instanceof JComboBox) {
                        return ((JComboBox) jc).getSelectedItem();

                    }
                } catch (Exception e) {
                    return null;
                }
                return null;
            }
            return jt.getValueAt(row, column);
        }
        return null;

    }

   
    //get selected column return empty string if null

    public static String getSelectedValueE(JTable jt, int column) {
        if (jt.getSelectedRow() > -1) {
            Object ob = getSelectedValue(jt, column);
            if (ob == null) {
                return "";
            }
            return ob.toString();
        }
        return "";

    }

    public static void selectNextRow(JTable jt, KeyEvent e) {
        int x = jt.getSelectedRow();
        int r = jt.getRowCount();
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (x + 1 < r) {

                jt.getSelectionModel().setSelectionInterval(x + 1, x + 1);
                return;
            }

        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (x - 1 >= 0 && x - 1 < r) {
                jt.getSelectionModel().setSelectionInterval(x - 1, x - 1);
            }
        }



    }

    public static void selectNextRow(JTable jt, int e) {
        int x = jt.getSelectedRow();
        int r = jt.getRowCount();
        if (e == KeyEvent.VK_DOWN) {
            if (x + 1 < r) {
            jt.changeSelection(x + 1, x + 1, false, false);
//                jt.getSelectionModel().setSelectionInterval(x + 1, x + 1);
                return;
            }

        }
        if (e == KeyEvent.VK_UP) {
            if (x - 1 >= 0 && x - 1 < r) {
//                jt.getSelectionModel().setSelectionInterval(x - 1, x - 1);
                jt.changeSelection(x - 1, x - 1,false, false);
            }
        }



    }

    
    public static void setModelValueat(JTable jt, Object value, int modelrowindex, int modelcolindex) {
        jt.setValueAt(value, jt.convertRowIndexToView(modelrowindex), jt.convertColumnIndexToView(modelcolindex));
    }

    public static boolean isSelectedRowNewRow(JTable jt) {
        int x = jt.getSelectedRow();
        if (x == -1) {
            return true;
        } else {
            Object ob = jt.getValueAt(x, 0);
            if (ob == null) {
                return false;
            }
            if (newRowID.equals(ob)) {
                return true;
            } else {
                return false;
            }
        }

    }

    public static Object rowID(JTable jt) {
        int x = jt.getSelectedRow();
        if (x == -1) {
            return null;
        } else {
            Object ob = jt.getValueAt(x, 0);
            if (newRowID.equals(ob)) {
                return null;
            } else {
                return ob;
            }
        }

    }

    public static void setColumnEditor(JTable tb, int col, TableCellEditor ed) {
        tb.getColumnModel().getColumn(col).setCellEditor(ed);

    }
}
