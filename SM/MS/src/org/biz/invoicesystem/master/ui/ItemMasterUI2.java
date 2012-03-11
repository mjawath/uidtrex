package org.biz.invoicesystem.master.ui;

import java.awt.AWTKeyStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileFilter;
import org.biz.app.ui.util.MessageBoxes;
import org.biz.app.ui.util.TableUtil;
import org.biz.app.ui.util.uiEty;
import org.biz.dao.util.EntityService;
import org.biz.invoicesystem.dao.master.SupplierDAO;
import org.biz.invoicesystem.entity.master.ExtraSalesPrice;
import org.biz.invoicesystem.entity.master.Item;
import org.biz.invoicesystem.entity.master.ItemBarcode;
import org.biz.invoicesystem.entity.master.ItemVariation;
import org.biz.invoicesystem.entity.master.Supplier;
import org.biz.invoicesystem.service.master.ItemService;
import org.components.util.Sessions;
import org.components.windows.TabPanelUI;

public class ItemMasterUI2 extends TabPanelUI {

    List<Item> items;
    ItemService itemService;
    EntityService es;
    ItemPopUp ipu;
    private Item selectedItem;
    private ItemMasterTab mastertab;
    private ItemListUi listUi;
    private String copiedItemId;  //this is not item code...keep in mind purpose of updating copied item
    JFileChooser chooser;
    List<File> images=new ArrayList<File>();
    
    public ItemMasterUI2() {
        initComponents();
        keyListeners();
        init();
    }

    
    /////////////////////////////////////
    public void loadComboData(){
        try {
       //c.category , c.unitOne , c.unitTwo c.location      
     List<Object[]> lst=itemService.getDao().loadComboItems();
    
     Set<String> catz=new TreeSet<String>();
     Set<String> un1z=new TreeSet<String>();
     Set<String> un2z=new TreeSet<String>();
     Set<String> locz=new TreeSet<String>();
     
            for (Object[] ss : lst) {
            String category= (String) ss[0];
            catz.add(category);
            String un1= (String) ss[1];
            un1z.add(un1);
            String un2= (String) ss[2];
            un2z.add(un2);
            String location= (String) ss[3];
          locz.add(location);  
          
            }
     uiEty.loadcombo(tItemCategory, catz);               
     
        
        } catch (Exception e) {
       e.printStackTrace(); }
    }
    
    ////////////////////////////
     
    public void keyListeners() {

        try {
            //item code listener
            tItemcode.addKeyListener(new KeyAdapter() {

                @Override
                public void keyTyped(KeyEvent e) {
                    if (e.getKeyChar() == KeyEvent.VK_ENTER) {

                        try {
        Item item=itemService.getDao().findItemByCode(uiEty.tcToStr(tItemcode));
            if(item!=null){ 
        entity2Ui(item);
           
            }
                 tItemDescription.requestFocus();
         
                        } catch (Exception exx) {
                            exx.printStackTrace();
                        tItemcode.requestFocus();    
                        }
              
                    }

                }

                @Override
                public void keyPressed(KeyEvent e) {
                }
            });//finished item code listener

            //item description listener
            tItemDescription.addKeyListener(new KeyAdapter() {

                @Override
                public void keyTyped(KeyEvent e) {
                    if (e.getKeyChar() == KeyEvent.VK_ENTER) {

                        tSupplierItem.getEditor().getEditorComponent().requestFocus();

                    }

                }

                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_UP) {

                        tItemcode.requestFocus();

                    }
                }
            });//finished item description listener                      

            //item category listener
            tItemCategory.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {

                @Override
                public void keyTyped(KeyEvent e) {
                    if (e.getKeyChar() == KeyEvent.VK_ENTER) {

                        tSupplierItem.getEditor().getEditorComponent().requestFocus();
                    }

                }

                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_UP) {

                        tItemDescription.requestFocus();

                    }
                }
            }); //item category listener finished

            //supplier listener
            tSupplierItem.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {

                @Override
                public void keyTyped(KeyEvent e) {
                    if (e.getKeyChar() == KeyEvent.VK_ENTER) {

                        tCartonItem.requestFocus();

                    }

                }

                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_UP) {

                        tItemCategory.getEditor().getEditorComponent().requestFocus();

                    }
                }
            }); //suplier listener finished
            //item carton keylistener started
            tCartonItem.addKeyListener(new KeyAdapter() {

                @Override
                public void keyTyped(KeyEvent e) {
                    if (e.getKeyChar() == KeyEvent.VK_ENTER) {

                        tUnitItem1.getEditor().getEditorComponent().requestFocus();

                    }

                }

                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_UP) {

                        tSupplierItem.getEditor().getEditorComponent().requestFocus();

                    }
                }
            });//carton listener finished.

            //unit 1 listener strtd
            tUnitItem1.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {

                @Override
                public void keyTyped(KeyEvent e) {
                    if (e.getKeyChar() == KeyEvent.VK_ENTER) {

                        tDifferentPerUnit.requestFocus();

                    }

                }

                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_UP) {

                        tCartonItem.requestFocus();

                    }
                }
            });//unit1 listener finished   

            //unit different field listener strtd
            tDifferentPerUnit.addKeyListener(new KeyAdapter() {

                @Override
                public void keyTyped(KeyEvent e) {
                    if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                        if (!uiEty.tcToStr(tDifferentPerUnit).equals("")) {
                            tUnitItem2.getEditor().getEditorComponent().requestFocus();

                        } else {
                            tItemSalesPriceUnit1.requestFocus();
                        }

                    }

                }

                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_UP) {

                        tUnitItem1.getEditor().getEditorComponent().requestFocus();

                    }
                }
            });//unit different field listener finished.

            // unit 2 comb listener strdtd.
            tUnitItem2.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {

                @Override
                public void keyTyped(KeyEvent e) {
                    if (e.getKeyChar() == KeyEvent.VK_ENTER) {

                        tItemSalesPriceUnit1.requestFocus();

                    }

                }

                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_UP) {

                        tDifferentPerUnit.requestFocus();

                    }
                }
            });
            ///unit 2 combo listener finished
            tItemSalesPriceUnit1.addKeyListener(new KeyAdapter() {

                @Override
                public void keyTyped(KeyEvent e) {


                    try {
                        double dif = uiEty.tcToDble0(tDifferentPerUnit);

                        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                            tItemCostPrice.selectAll();
                            tItemCostPrice.requestFocus();

                        }

                    } catch (Exception exx) {
                        exx.printStackTrace();
                        return;
                    }


                }

                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_UP) {


                        if (!uiEty.tcToStr(tDifferentPerUnit).equals("")) {
                            tUnitItem2.getEditor().selectAll();
                            tUnitItem2.getEditor().getEditorComponent().requestFocus();

                        } else {
                            tDifferentPerUnit.selectAll();
                            tDifferentPerUnit.requestFocus();
                        }
                    }
                }
            });

            //cost price listener strtd
            tItemCostPrice.addKeyListener(new KeyAdapter() {

                @Override
                public void keyTyped(KeyEvent e) {
                    if (e.getKeyChar() == KeyEvent.VK_ENTER) {

                        tItemLandingCost.requestFocus();

                    }

                }

                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_UP) {
                        tItemSalesPriceUnit1.selectAll();
                        tItemSalesPriceUnit1.requestFocus();

                    }
                }
            });//cost price listener finished.
            ////////////////////////////////////////////////////////////////
            //landing cost listener field strtd...

            tItemLandingCost.addKeyListener(new KeyAdapter() {

                @Override
                public void keyTyped(KeyEvent e) {
                    if (e.getKeyChar() == KeyEvent.VK_ENTER) {

                        tItemMinimumPrice.requestFocus();

                    }

                }

                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_UP) {

                        tItemCostPrice.requestFocus();

                    }
                }
            });
            ////////////////////////////////////////////////////////////
            //landing cost listener field finished...

            //tItemMinimumPrice listener started..
            tItemMinimumPrice.addKeyListener(new KeyAdapter() {

                @Override
                public void keyTyped(KeyEvent e) {
                    if (e.getKeyChar() == KeyEvent.VK_ENTER) {

                        tItemdiscount.requestFocus();

                    }

                }

                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_UP) {

                        tItemLandingCost.requestFocus();

                    }
                }
            });
            ///////////////////////////////////////////////////////////////////////////       
            tItemdiscount.addKeyListener(new KeyAdapter() {

                @Override
                public void keyTyped(KeyEvent e) {
                    if (e.getKeyChar() == KeyEvent.VK_ENTER) {

                        tItemCommission.requestFocus();

                    }

                }

                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_UP) {

                        tItemMinimumPrice.requestFocus();

                    }
                }
            }); //discount listener finished...... 


            ///////////////////////////////////////////////////////////////////////////       
            tItemCommission.addKeyListener(new KeyAdapter() {

                @Override
                public void keyTyped(KeyEvent e) {
                    if (e.getKeyChar() == KeyEvent.VK_ENTER) {

                        tItemLocation.getEditor().getEditorComponent().requestFocus();

                    }

                }

                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_UP) {

                        tItemdiscount.requestFocus();

                    }
                }
            }); //discount listener finished...... 
            /////////////////////////////////////////////
            tItemLocation.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {

                @Override
                public void keyTyped(KeyEvent e) {
                    if (e.getKeyChar() == KeyEvent.VK_ENTER) {

                        tItemMinimumStock.requestFocus();

                    }

                }

                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_UP) {

                        tItemCommission.requestFocus();

                    }
                }
            });
            ////////////////////////////////////////////////////////////////////////////
            tItemMinimumStock.addKeyListener(new KeyAdapter() {

                @Override
                public void keyTyped(KeyEvent e) {
                    if (e.getKeyChar() == KeyEvent.VK_ENTER) {

                        tItemReOrder.requestFocus();

                    }

                }

                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_UP) {

                        tItemLocation.getEditor().getEditorComponent().requestFocus();

                    }
                }
            });
            ////////////////////////////////////////////////////////////////////////////
         ////////////////////////////////////////////////////////////////////////////
            tItemReOrder.addKeyListener(new KeyAdapter() {

                @Override
                public void keyTyped(KeyEvent e) {
                    if (e.getKeyChar() == KeyEvent.VK_ENTER) {

                        tItemTrakSerial.requestFocus();

                    }

                }

                @Override
                public void keyPressed(KeyEvent e) {
           if (e.getKeyCode() == KeyEvent.VK_UP) {

                   tItemMinimumStock.requestFocus();

                    }
                }
            });
            ////////////////////////////////////////////////////////////////////////////
     
            tItemTrakSerial.addKeyListener(new KeyAdapter() {

                @Override
                public void keyPressed(KeyEvent e) {
                    try {

                        if (e.getKeyCode() == KeyEvent.VK_UP) {
                            tItemReOrder.requestFocus();
                        }
                    } catch (Exception exx) {
                        exx.printStackTrace();
                    }

                }

                @Override
                public void keyTyped(KeyEvent e) {
                    try {

                        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                            tItemTrakSerial.setSelected(tItemTrakSerial.isSelected() ? true : false);
                        }
                        if (e.getKeyChar() == KeyEvent.VK_ENTER) {

                            tItemTrakExpiry.requestFocus();

                        }
                    } catch (Exception exx) {
                        exx.printStackTrace();
                    }

                }
            });
            ///////////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////////
            tItemTrakExpiry.addKeyListener(new KeyAdapter() {

                @Override
                public void keyPressed(KeyEvent e) {
                    try {

                        if (e.getKeyCode() == KeyEvent.VK_UP) {
                            tItemTrakSerial.requestFocus();
                        }
                    } catch (Exception exx) {
                        exx.printStackTrace();
                    }

                }

                @Override
                public void keyTyped(KeyEvent e) {
                    try {

                        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                            tItemTrakExpiry.setSelected(tItemTrakExpiry.isSelected() ? true : false);
                        }
                        if (e.getKeyChar() == KeyEvent.VK_ENTER) {

                            tItemTrakNonStockItem.requestFocus();

                        }
                    } catch (Exception exx) {
                        exx.printStackTrace();
                    }

                }
            });
            ///////////////////////////////////////////////////////////////////////////
            //tItemTrakNonStockItem request fct need to write....

            tItemTrakNonStockItem.addKeyListener(new KeyAdapter() {

                @Override
                public void keyPressed(KeyEvent e) {
                    try {

                        if (e.getKeyCode() == KeyEvent.VK_UP) {
                            tItemTrakExpiry.requestFocus();
                        }
                    } catch (Exception exx) {
                        exx.printStackTrace();
                    }

                }

                @Override
                public void keyTyped(KeyEvent e) {
                    try {

                        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                            tItemTrakNonStockItem.setSelected(tItemTrakNonStockItem.isSelected() ? true : false);
                        }
                        if (e.getKeyChar() == KeyEvent.VK_ENTER) {

                            tItemTrakInactive.requestFocus();

                        }
                    } catch (Exception exx) {
                        exx.printStackTrace();
                    }

                }
            });

            ////////////////////////////////////////////////////////////////////////////

            ////////////////////////////////////////////////////////////////////////////

            tItemTrakInactive.addKeyListener(new KeyAdapter() {

                @Override
                public void keyPressed(KeyEvent e) {
                    try {

                        if (e.getKeyCode() == KeyEvent.VK_UP) {
                            tItemTrakNonStockItem.requestFocus();
                        }
                    } catch (Exception exx) {
                        exx.printStackTrace();
                    }

                }

                @Override
                public void keyTyped(KeyEvent e) {
                    try {

                        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                            tItemTrakInactive.setSelected(tItemTrakInactive.isSelected() ? true : false);
                        }
                        if (e.getKeyChar() == KeyEvent.VK_ENTER) {

                            tItemTrakManfctringItem.requestFocus();

                        }
                    } catch (Exception exx) {
                        exx.printStackTrace();
                    }

                }
            });

            ///////////////////////////////////////////////////////////////////////////

            ////////////////////////////////////////////////////////////////////////////

            tItemTrakManfctringItem.addKeyListener(new KeyAdapter() {

                @Override
                public void keyPressed(KeyEvent e) {
                    try {

                        if (e.getKeyCode() == KeyEvent.VK_UP) {
                            tItemTrakInactive.requestFocus();
                        }
                    } catch (Exception exx) {
                        exx.printStackTrace();
                    }

                }

                @Override
                public void keyTyped(KeyEvent e) {
                    try {

                        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                            tItemTrakManfctringItem.setSelected(tItemTrakManfctringItem.isSelected() ? true : false);
                        }
                        if (e.getKeyChar() == KeyEvent.VK_ENTER) {

                            cSaveBtn.requestFocus();

                        }
                    } catch (Exception exx) {
                        exx.printStackTrace();
                    }

                }
            });

            ////////////////////////////////////////////////////////////////////////////
            tVariationName.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {

                @Override
                public void keyTyped(KeyEvent e) {
                    try {
                        if (e.getKeyChar() == KeyEvent.VK_ENTER) {

                            tVariationPrice1.requestFocus();

                        }
                    } catch (Exception exx) {
                    }
                }
            });
            /////////////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////////

            /////////////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////////

            /////////////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////////

            /////////////////////////////////////////////////////////////////////////////

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init() {

        try {
            es = EntityService.getEntityService();
            itemService = new ItemService();
            items = itemService.getDao().getAll();

            ///init filechooser and set filter
            ///////////////////////  
            chooser = new JFileChooser(new File("."));
            chooser.setMultiSelectionEnabled(true);
            chooser.setFileFilter(new FileFilter() {

                @Override
                public boolean accept(File f) {

                    if (f.isDirectory()) {
                        return true;
                    }
                    String s = f.getName();
                    int i = s.lastIndexOf('.');

                    if (i > 0 && i < s.length() - 1) {
                        if (s.substring(i + 1).toLowerCase().equals("jpg") || s.substring(i + 1).toLowerCase().equals("png") || s.substring(i + 1).toLowerCase().equals("gif") || s.substring(i + 1).toLowerCase().equals("png")) {
                            return true;
                        }
                    }

                    return false;
                }

                @Override
                public String getDescription() {
                    return "Images Only";
                }
            });
            chooser.setCurrentDirectory(null);

            ////////////////////////////////////////
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void clearMaster() {
        try {

            tVariationName.setSelectedItem("");
            tVariationPrice1.setText("");
            tVariationPrice2.setText("");

            tPriceRange.setSelectedItem("");
            tRngeValue.setText("");
            TableUtil.cleardata(tblVariation);
            TableUtil.cleardata(tblPriceRanges);
            TableUtil.cleardata(tblBarcode);
             
            tType.setText("");
            tItemBarcode.setText("");
            entity2Ui(new Item());
            
            cPanel4.removeAll();
            cPanel4.revalidate();
            
            images.clear();
        } catch (Exception e) {
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        componentFactory1 = new org.components.util.ComponentFactory();
        cPanel5 = new org.components.containers.CPanel();
        tItemCostPrice = new org.components.controls.CTextField();
        tItemMinimumStock = new org.components.controls.CTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        tItemCommissionValue = new org.components.controls.CTextField();
        tDifferentPerUnit = new org.components.controls.CTextField();
        tSupplierItem = new org.components.controls.CComboBox();
        cClose = new org.components.controls.CButton();
        tItemDescription = new org.components.controls.CTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cDeleteBtn = new org.components.controls.CButton();
        jLabel23 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        cButton1 = new org.components.controls.CButton();
        cClear = new org.components.controls.CButton();
        tItemdiscount = new org.components.controls.CTextField();
        tItemSalesPriceUnit1 = new org.components.controls.CTextField();
        jLabel2 = new javax.swing.JLabel();
        tItemLandingCost = new org.components.controls.CTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        cScrollPane1 = new org.components.controls.CScrollPane();
        cPanel4 = new org.components.containers.CPanel();
        cLabel7 = new org.components.controls.CLabel();
        tItemSalesPriceUnit2 = new org.components.controls.CTextField();
        tUnitItem2 = new org.components.controls.CComboBox();
        jLabel15 = new javax.swing.JLabel();
        cSaveBtn = new org.components.controls.CButton();
        jLabel21 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        cPanel1 = new org.components.containers.CPanel();
        tVariationPrice2 = new org.components.controls.CTextField();
        tVariationName = new org.components.controls.CComboBox();
        cLabel2 = new org.components.controls.CLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblVariation = new javax.swing.JTable();
        tVariationPrice1 = new org.components.controls.CTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPriceRanges = new javax.swing.JTable();
        tRngeValue = new org.components.controls.CTextField();
        tPriceRange = new org.components.controls.CComboBox();
        cLabel1 = new org.components.controls.CLabel();
        tWholesalePrice = new org.components.controls.CTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblBarcode = new javax.swing.JTable();
        tType = new org.components.controls.CTextField();
        cLabel4 = new org.components.controls.CLabel();
        cLabel5 = new org.components.controls.CLabel();
        cLabel6 = new org.components.controls.CLabel();
        tItemBarcode = new org.components.controls.CTextField();
        cPanel3 = new org.components.containers.CPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tMetaInfo = new org.components.controls.CTextArea();
        cLabel3 = new org.components.controls.CLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        tItemMinimumPrice = new org.components.controls.CTextField();
        tItemLocation = new org.components.controls.CComboBox();
        tItemdiscValue = new org.components.controls.CTextField();
        tItemCommission = new org.components.controls.CTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        tItemReOrder = new org.components.controls.CTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cPanel2 = new org.components.containers.CPanel();
        tItemTrakSerial = new org.components.controls.CCheckBox();
        tItemTrakExpiry = new org.components.controls.CCheckBox();
        tItemTrakNonStockItem = new org.components.controls.CCheckBox();
        tItemTrakInactive = new org.components.controls.CCheckBox();
        tItemTrakManfctringItem = new org.components.controls.CCheckBox();
        tCartonItem = new org.components.controls.CTextField();
        tUnitItem1 = new org.components.controls.CComboBox();
        jLabel19 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        tItemcode = new org.components.controls.CTextField();
        jLabel6 = new javax.swing.JLabel();
        tItemCategory = new org.components.controls.CComboBox();
        jLabel1 = new javax.swing.JLabel();

        setLayout(null);

        cPanel5.setLayout(null);
        cPanel5.add(tItemCostPrice);
        tItemCostPrice.setBounds(60, 270, 90, 25);
        cPanel5.add(tItemMinimumStock);
        tItemMinimumStock.setBounds(60, 420, 210, 25);

        jLabel9.setText("Min.Price");
        cPanel5.add(jLabel9);
        jLabel9.setBounds(10, 300, 60, 20);

        jLabel3.setText("Carton ");
        cPanel5.add(jLabel3);
        jLabel3.setBounds(10, 140, 60, 20);

        jLabel8.setText("Unit 2");
        cPanel5.add(jLabel8);
        jLabel8.setBounds(190, 170, 60, 20);

        jLabel22.setText("Cost Price");
        cPanel5.add(jLabel22);
        jLabel22.setBounds(10, 270, 60, 20);

        tItemCommissionValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tItemCommissionValueActionPerformed(evt);
            }
        });
        cPanel5.add(tItemCommissionValue);
        tItemCommissionValue.setBounds(180, 360, 90, 25);
        cPanel5.add(tDifferentPerUnit);
        tDifferentPerUnit.setBounds(140, 190, 50, 20);

        tSupplierItem.setEditable(true);
        cPanel5.add(tSupplierItem);
        tSupplierItem.setBounds(60, 110, 210, 20);

        cClose.setText("Goto List ");
        cClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cCloseActionPerformed(evt);
            }
        });
        cPanel5.add(cClose);
        cClose.setBounds(620, 430, 90, 50);
        cPanel5.add(tItemDescription);
        tItemDescription.setBounds(60, 50, 210, 25);

        jLabel17.setText("Min.Stock");
        cPanel5.add(jLabel17);
        jLabel17.setBounds(10, 420, 60, 20);

        jLabel10.setText("%");
        cPanel5.add(jLabel10);
        jLabel10.setBounds(50, 330, 20, 20);

        cDeleteBtn.setText("Delete");
        cDeleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cDeleteBtnActionPerformed(evt);
            }
        });
        cPanel5.add(cDeleteBtn);
        cDeleteBtn.setBounds(530, 430, 80, 50);

        jLabel23.setText("Val");
        cPanel5.add(jLabel23);
        jLabel23.setBounds(160, 350, 20, 40);

        jLabel16.setText("Commission");
        cPanel5.add(jLabel16);
        jLabel16.setBounds(10, 360, 60, 20);

        cButton1.setText("Browse");
        cButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cButton1ActionPerformed(evt);
            }
        });
        cPanel5.add(cButton1);
        cButton1.setBounds(290, 390, 80, 20);

        cClear.setText("Clear");
        cClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cClearActionPerformed(evt);
            }
        });
        cPanel5.add(cClear);
        cClear.setBounds(440, 430, 80, 50);

        tItemdiscount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tItemdiscountActionPerformed(evt);
            }
        });
        cPanel5.add(tItemdiscount);
        tItemdiscount.setBounds(60, 330, 90, 25);

        tItemSalesPriceUnit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tItemSalesPriceUnit1ActionPerformed(evt);
            }
        });
        cPanel5.add(tItemSalesPriceUnit1);
        tItemSalesPriceUnit1.setBounds(60, 210, 80, 20);

        jLabel2.setText("Description ");
        cPanel5.add(jLabel2);
        jLabel2.setBounds(10, 50, 60, 20);
        cPanel5.add(tItemLandingCost);
        tItemLandingCost.setBounds(180, 270, 90, 25);

        jLabel18.setText("Diff");
        cPanel5.add(jLabel18);
        jLabel18.setBounds(140, 170, 50, 20);

        jLabel20.setText("Unit 1");
        cPanel5.add(jLabel20);
        jLabel20.setBounds(60, 170, 50, 20);

        cScrollPane1.setAutoscrolls(true);
        cScrollPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cScrollPane1.setViewportView(cPanel4);

        cPanel5.add(cScrollPane1);
        cScrollPane1.setBounds(290, 300, 460, 90);

        cLabel7.setText("You Can Select More than one Product Image");
        cPanel5.add(cLabel7);
        cLabel7.setBounds(380, 390, 370, 25);

        tItemSalesPriceUnit2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tItemSalesPriceUnit2ActionPerformed(evt);
            }
        });
        cPanel5.add(tItemSalesPriceUnit2);
        tItemSalesPriceUnit2.setBounds(190, 210, 80, 20);

        tUnitItem2.setEditable(true);
        cPanel5.add(tUnitItem2);
        tUnitItem2.setBounds(190, 190, 80, 23);

        jLabel15.setText("Location");
        cPanel5.add(jLabel15);
        jLabel15.setBounds(10, 390, 60, 20);

        cSaveBtn.setText("Save");
        cSaveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cSaveBtnActionPerformed(evt);
            }
        });
        cPanel5.add(cSaveBtn);
        cSaveBtn.setBounds(350, 430, 80, 50);

        jLabel21.setText("Supplier");
        cPanel5.add(jLabel21);
        jLabel21.setBounds(10, 110, 60, 20);

        cPanel1.setLayout(null);

        tVariationPrice2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tVariationPrice2ActionPerformed(evt);
            }
        });
        tVariationPrice2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tVariationPrice2KeyTyped(evt);
            }
        });
        cPanel1.add(tVariationPrice2);
        tVariationPrice2.setBounds(310, 30, 140, 25);

        tVariationName.setEditable(true);
        cPanel1.add(tVariationName);
        tVariationName.setBounds(10, 30, 130, 23);

        cLabel2.setText("Item Variation");
        cPanel1.add(cLabel2);
        cLabel2.setBounds(10, 0, 130, 25);

        tblVariation.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Description", "S Price 1", "S Price 2"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblVariation);

        cPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(10, 60, 440, 120);

        tVariationPrice1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tVariationPrice1KeyTyped(evt);
            }
        });
        cPanel1.add(tVariationPrice1);
        tVariationPrice1.setBounds(150, 30, 140, 25);

        jTabbedPane1.addTab("Variation", cPanel1);

        jPanel1.setLayout(null);

        jLabel7.setText("Whole Sale  Price");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(20, 10, 130, 20);

        tblPriceRanges.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Range", "Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblPriceRanges);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(50, 110, 340, 70);

        tRngeValue.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tRngeValueKeyTyped(evt);
            }
        });
        jPanel1.add(tRngeValue);
        tRngeValue.setBounds(270, 70, 120, 25);

        tPriceRange.setEditable(true);
        tPriceRange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tPriceRangeActionPerformed(evt);
            }
        });
        jPanel1.add(tPriceRange);
        tPriceRange.setBounds(50, 70, 130, 23);

        cLabel1.setText("Feed Price Ranges For Wholesale Needs");
        jPanel1.add(cLabel1);
        cLabel1.setBounds(60, 40, 310, 25);
        jPanel1.add(tWholesalePrice);
        tWholesalePrice.setBounds(260, 10, 180, 25);

        jTabbedPane1.addTab("Price Range", jPanel1);

        jPanel4.setLayout(null);

        tblBarcode.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Type", "Barcode"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tblBarcode);

        jPanel4.add(jScrollPane5);
        jScrollPane5.setBounds(50, 70, 340, 110);

        tType.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tTypeKeyTyped(evt);
            }
        });
        jPanel4.add(tType);
        tType.setBounds(50, 30, 160, 25);

        cLabel4.setText("Barcode Number");
        jPanel4.add(cLabel4);
        cLabel4.setBounds(230, 0, 160, 25);

        cLabel5.setText("Type");
        jPanel4.add(cLabel5);
        cLabel5.setBounds(50, 0, 160, 25);

        cLabel6.setText("Enter");
        jPanel4.add(cLabel6);
        cLabel6.setBounds(404, 30, 40, 25);

        tItemBarcode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tItemBarcodeKeyTyped(evt);
            }
        });
        jPanel4.add(tItemBarcode);
        tItemBarcode.setBounds(230, 30, 160, 25);

        jTabbedPane1.addTab("Barcode", jPanel4);

        cPanel3.setLayout(null);

        tMetaInfo.setColumns(20);
        tMetaInfo.setRows(5);
        jScrollPane4.setViewportView(tMetaInfo);

        cPanel3.add(jScrollPane4);
        jScrollPane4.setBounds(10, 80, 440, 96);

        cLabel3.setText("Meta Information ");
        cPanel3.add(cLabel3);
        cLabel3.setBounds(10, 50, 300, 25);
        cPanel3.add(jPanel3);
        jPanel3.setBounds(100, -30, 10, 10);

        jTabbedPane1.addTab("Meta Details ", cPanel3);

        cPanel5.add(jTabbedPane1);
        jTabbedPane1.setBounds(280, 20, 470, 220);

        jLabel13.setText("$");
        cPanel5.add(jLabel13);
        jLabel13.setBounds(170, 270, 10, 20);
        cPanel5.add(tItemMinimumPrice);
        tItemMinimumPrice.setBounds(60, 300, 90, 25);

        tItemLocation.setEditable(true);
        cPanel5.add(tItemLocation);
        tItemLocation.setBounds(60, 390, 210, 23);

        tItemdiscValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tItemdiscValueActionPerformed(evt);
            }
        });
        cPanel5.add(tItemdiscValue);
        tItemdiscValue.setBounds(180, 330, 90, 25);

        tItemCommission.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tItemCommissionActionPerformed(evt);
            }
        });
        cPanel5.add(tItemCommission);
        tItemCommission.setBounds(60, 360, 90, 20);
        cPanel5.add(jPanel2);
        jPanel2.setBounds(290, 210, 10, 10);

        jLabel4.setText("Category");
        cPanel5.add(jLabel4);
        jLabel4.setBounds(10, 80, 60, 14);
        cPanel5.add(tItemReOrder);
        tItemReOrder.setBounds(60, 450, 210, 25);

        jLabel14.setText("Val");
        cPanel5.add(jLabel14);
        jLabel14.setBounds(160, 330, 20, 30);

        jLabel11.setText("Sales Price");
        cPanel5.add(jLabel11);
        jLabel11.setBounds(10, 210, 60, 30);

        cPanel2.setLayout(null);

        tItemTrakSerial.setText("Track Serial Number");
        tItemTrakSerial.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tItemTrakSerial.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tItemTrakSerial.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tItemTrakSerial.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        tItemTrakSerial.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tItemTrakSerial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tItemTrakSerialActionPerformed(evt);
            }
        });
        cPanel2.add(tItemTrakSerial);
        tItemTrakSerial.setBounds(10, 10, 110, 50);

        tItemTrakExpiry.setText("Track Expiry ");
        tItemTrakExpiry.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tItemTrakExpiry.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tItemTrakExpiry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tItemTrakExpiryActionPerformed(evt);
            }
        });
        cPanel2.add(tItemTrakExpiry);
        tItemTrakExpiry.setBounds(110, 0, 71, 60);

        tItemTrakNonStockItem.setText("Non Stock Item");
        tItemTrakNonStockItem.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tItemTrakNonStockItem.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tItemTrakNonStockItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tItemTrakNonStockItemActionPerformed(evt);
            }
        });
        cPanel2.add(tItemTrakNonStockItem);
        tItemTrakNonStockItem.setBounds(180, 0, 90, 60);

        tItemTrakInactive.setText("Inactive");
        tItemTrakInactive.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tItemTrakInactive.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cPanel2.add(tItemTrakInactive);
        tItemTrakInactive.setBounds(270, 0, 60, 60);

        tItemTrakManfctringItem.setText("Manufacturing Item");
        tItemTrakManfctringItem.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tItemTrakManfctringItem.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cPanel2.add(tItemTrakManfctringItem);
        tItemTrakManfctringItem.setBounds(330, 0, 110, 60);

        cPanel5.add(cPanel2);
        cPanel2.setBounds(290, 240, 440, 60);
        cPanel5.add(tCartonItem);
        tCartonItem.setBounds(60, 140, 210, 25);

        tUnitItem1.setEditable(true);
        tUnitItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tUnitItem1ActionPerformed(evt);
            }
        });
        cPanel5.add(tUnitItem1);
        tUnitItem1.setBounds(60, 190, 80, 23);

        jLabel19.setText("Re Order");
        cPanel5.add(jLabel19);
        jLabel19.setBounds(10, 450, 60, 20);

        jLabel12.setText("Discount ");
        cPanel5.add(jLabel12);
        jLabel12.setBounds(10, 330, 60, 20);
        cPanel5.add(tItemcode);
        tItemcode.setBounds(60, 20, 210, 25);

        jLabel6.setText("Landing Cost");
        cPanel5.add(jLabel6);
        jLabel6.setBounds(180, 250, 80, 20);

        tItemCategory.setEditable(true);
        cPanel5.add(tItemCategory);
        tItemCategory.setBounds(60, 80, 210, 23);

        jLabel1.setText("Item Code");
        cPanel5.add(jLabel1);
        jLabel1.setBounds(10, 20, 50, 20);

        add(cPanel5);
        cPanel5.setBounds(20, 40, 780, 510);
    }// </editor-fold>//GEN-END:initComponents
/////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void addToVariationTbl() {
        try {
            // TableUtil.cleardata(tblVariation);
            String variationDesc = uiEty.cmbtostr(tVariationName);
            double variaiotnPrice1 = uiEty.tcToDble0(tVariationPrice1);
            double variaiotnPrice2 = uiEty.tcToDble0(tVariationPrice2);
            TableUtil.addrow(tblVariation, new Object[]{variationDesc, variaiotnPrice1, variaiotnPrice2});

        } catch (Exception e) {

            e.printStackTrace();

        }
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //this method ADD VALUES TO THE TABLE OF EXTRA SALES PRICE...TABLE.

    public void addToExtraPriceTbl() {
        try {
            // TableUtil.cleardata(tblVariation);
            String tPriceDes = uiEty.cmbtostr(tPriceRange);
            double ExtraPrice1 = uiEty.tcToDble0(tRngeValue);
            TableUtil.addrow(tblPriceRanges, new Object[]{tPriceDes, ExtraPrice1});

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //this method ADD barcode TO THE TABLE OF barcodeTbl...TABLE.
    public void addToBarcode() {
        try {
            // TableUtil.cleardata(tblVariation);

            String type = uiEty.tcToStr(tType);
            String barcode = uiEty.tcToStr(tItemBarcode);

            TableUtil.addrow(tblBarcode, new Object[]{type, barcode});

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Item uiToEntity(Item i) throws Exception {
        try {
            i.setId(EntityService.getEntityService().getKey(""));
            i.setCode(uiEty.tcToStr(tItemcode));
            i.setDescription(uiEty.tcToStr(tItemDescription));
            i.setCategory(uiEty.cmbtostr(tItemCategory)); //    combo 
            i.setSupplierId(uiEty.cmbtostr(tSupplierItem)); //    combo 
            i.setCarton(uiEty.tcToDble0(tCartonItem)); //
            System.out.println("uiEty.cmbtostr(tUnitItem1) "+uiEty.cmbtostr(tUnitItem1));
            i.setUnitOne(uiEty.cmbtostr(tUnitItem1));//      combo
            i.setDifferent(uiEty.tcToInt(tDifferentPerUnit));//tDifferentPerUnit
            i.setUnitTwo(uiEty.cmbtostr(tUnitItem2));//tUnitItem2       combo
            i.setUnit1SalesPrice(uiEty.tcToDble0(tItemSalesPriceUnit1)); //tItemSalesPriceUnit1      
            i.setUnit2SalesPrice(uiEty.tcToDble0(tItemSalesPriceUnit2));//tItemSalesPriceUnit2
            i.setCost(uiEty.tcToDble0(tItemCostPrice));//tItemCostPrice
            i.setLandCost(uiEty.tcToDble0(tItemLandingCost)); //tItemLandingCost     
            i.setMinSalesPrice(uiEty.tcToDble0(tItemMinimumPrice)); //tItemMinimumPrice
            i.setDiscount(uiEty.tcToDble0(tItemdiscount));//tItemdiscount        
            i.setDiscountValue(uiEty.tcToDble0(tItemdiscValue));
            i.setCommission(uiEty.tcToDble0(tItemCommission));//tItemCommission
            i.setCommissionValue(uiEty.tcToDble0(tItemCommissionValue));//tItemCommission
            i.setLocation(uiEty.cmbtostr(tItemLocation));//tItemLocation   combo   
            i.setMinStock(uiEty.tcToDble0(tItemMinimumStock));//tItemMinimumStock   
            i.setReOrder(uiEty.tcToDble0(tItemReOrder)); //tItemReOrder
            i.setTrackSerial(tItemTrakSerial.isSelected());  //tItemTrakSerial chk
            i.setTrackExpiry(tItemTrakExpiry.isSelected());  //tItemTrakExpiry chk
            i.setNonStockItems(tItemTrakNonStockItem.isSelected());//tItemTrakNonStockItem chk
            i.setManufactItem(tItemTrakManfctringItem.isSelected());//tItemTrakManfctringItem chk      
            i.setInactive(tItemTrakInactive.isSelected());//tItemTrakInactive chk      
            i.setWholesalePrice(uiEty.tcToDble0(tWholesalePrice));//tWholesalePrice      
            i.setMetaInfo(tMetaInfo.getText());  //tMetaInfo
            i.setVariations(ui2ItemVariation(tblVariation, i.getId()));
            i.setExtrasalespriceCollection(ui2ExtraSalesPrice(tblPriceRanges, i.getId()));

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return i;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void callListTab() {
        try {
            getMastertab().getItemTabPane().setSelectedIndex(getMastertab().getItemTabPane().indexOfTab(ItemMasterTab.ListUiTabName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////
//    public String idForDb(String shopName){
//    String id="";
//    
//        try {
//           
//            id+=uiEty.nowTimesStamp()+shopName;
//    //id must be first autonumber ,second timestamp (8 digits),third shopname;  
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    
//    return id;
//    }
    //this method will get all the itemvariation details from 
    //itemvariation table /grid.
    public List<ItemVariation> ui2ItemVariation(JTable tbl, String itemid) {
        List<ItemVariation> lstOfVariation = new ArrayList<ItemVariation>();
        try {
            Vector<Vector> vecOfVec = TableUtil.getDataVector(tbl);
            for (Iterator<Vector> it = vecOfVec.iterator(); it.hasNext();) {

                Vector row = it.next();
                ItemVariation var = new ItemVariation();
                var.setId(EntityService.getEntityService().getKey(""));
                var.setDescription(row.get(0) == null ? "" : row.get(0).toString());
                var.setsPrice1(row.get(1) == null ? 0.0 : Double.parseDouble(row.get(1).toString()));
                var.setsPrice2(row.get(2) == null ? 0.0 : Double.parseDouble(row.get(2).toString()));
                lstOfVariation.add(var);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lstOfVariation;
    }

    public List<ExtraSalesPrice> ui2ExtraSalesPrice(JTable tbl, String itemid) {
        List<ExtraSalesPrice> lstOfExSalePrice = new ArrayList<ExtraSalesPrice>();
        try {
            Vector<Vector> vecOfVec = TableUtil.getDataVector(tbl);
            for (Iterator<Vector> it = vecOfVec.iterator(); it.hasNext();) {
                Vector row = it.next();
                ExtraSalesPrice extraSprice = new ExtraSalesPrice();
                extraSprice.setId(EntityService.getEntityService().getKey(""));
                extraSprice.setDescription(row.get(0) == null ? "" : row.get(0).toString());
                extraSprice.setPrice(row.get(1) == null ? 0.0 : Double.parseDouble(row.get(1).toString()));
                lstOfExSalePrice.add(extraSprice);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lstOfExSalePrice;
    }

    ////////////////////////////
    public List<ItemBarcode> ui2Barcodes(JTable tbl, String itemid) {
        List<ItemBarcode> lstFBarcodes = new ArrayList<ItemBarcode>();
        try {
            Vector<Vector> vecOfVec = TableUtil.getDataVector(tbl);
            for (Iterator<Vector> it = vecOfVec.iterator(); it.hasNext();) {
                Vector row = it.next();
                ItemBarcode bCode = new ItemBarcode();
                bCode.setId(EntityService.getEntityService().getKey(""));
                bCode.setType(row.get(0) == null ? "" : row.get(0).toString());
                bCode.setBarcode(row.get(1) == null ? "" : row.get(1).toString());
                lstFBarcodes.add(bCode);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lstFBarcodes;
    }

    ////////////////////////////
    public void entity2Ui(Item i) {

        try {

            setCopiedItemId(i.getId());
            uiEty.objToUi(tItemcode, i.getCode());
            uiEty.objToUi(tItemDescription, i.getDescription());// tItemDescription
            uiEty.objToUi(tItemCategory, i.getCategory());//   i.setCategory(uiEty.cmbtostr(tItemCategory)); //    combo 
            uiEty.objToUi(tSupplierItem, i.getSupplierId());//   i.setSupplierId(uiEty.cmbtostr(tSupplierItem)); //    combo 
            uiEty.objToUi(tCartonItem, i.getCarton());//   i.setCarton(uiEty.tcToDble0(tCartonItem)); //     
            uiEty.objToUi(tUnitItem1, i.getUnitOne());//   i.setUnitOne(uiEty.cmbtostr(tUnitItem1));//      combo
            uiEty.objToUi(tDifferentPerUnit, i.getDifferent());//  i.setDifferent(uiEty.tcToInt(tDifferentPerUnit));//tDifferentPerUnit
            uiEty.objToUi(tUnitItem2, i.getUnitTwo());// i.setUnitTwo(uiEty.cmbtostr(tUnitItem2));//tUnitItem2       combo
            uiEty.objToUi(tItemSalesPriceUnit1, i.getUnit1SalesPrice());//  i.setUnit1SalesPrice(uiEty.tcToDble0(tItemSalesPriceUnit1)); //tItemSalesPriceUnit1      
            uiEty.objToUi(tItemSalesPriceUnit2, i.getUnit2SalesPrice());// i.setUnit2SalesPrice(uiEty.tcToDble0(tItemSalesPriceUnit2));//tItemSalesPriceUnit2
            uiEty.objToUi(tItemCostPrice, i.getCost());//  i.setCost(uiEty.tcToDble0(tItemCostPrice));//tItemCostPrice
            uiEty.objToUi(tItemLandingCost, i.getLandCost());//  i.setLandCost(uiEty.tcToDble0(tItemLandingCost)); //tItemLandingCost     
            uiEty.objToUi(tItemMinimumPrice, i.getMinSalesPrice());//  i.setMinSalesPrice(uiEty.tcToDble0(tItemMinimumPrice)); //tItemMinimumPrice
            uiEty.objToUi(tItemdiscount, i.getDiscount());//  i.setDicount(uiEty.tcToDble0(tItemdiscount));//tItemdiscount        


            uiEty.objToUi(tItemdiscValue, i.getDiscountValue());//   //tItemdiscValue       

            uiEty.objToUi(tItemCommission, i.getCommission());//   i.setCommission(uiEty.tcToDble0(tItemCommission));//tItemCommission
            uiEty.objToUi(tItemCommissionValue, i.getCommission());//  i.setCommissionValue(uiEty.tcToDble0(tItemCommissionValue));//tItemCommission
            uiEty.objToUi(tItemLocation, i.getLocation());//   i.setLocation(uiEty.cmbtostr(tItemLocation));//tItemLocation   combo   
            uiEty.objToUi(tItemMinimumStock, i.getMinStock());//   i.setMinStock(uiEty.tcToDble0(tItemMinimumStock));//tItemMinimumStock   
            uiEty.objToUi(tItemReOrder, i.getReOrder());//   i.setReOrder(uiEty.tcToDble0(tItemReOrder)); //tItemReOrder
            uiEty.objToUi(tItemTrakSerial, i.getTrackSerial());//   i.setTrackSerial(tItemTrakSerial.isSelected());  //tItemTrakSerial chk
            uiEty.objToUi(tItemTrakExpiry, i.getTrackExpiry());//   i.setTrackExpiry(tItemTrakExpiry.isSelected());  //tItemTrakExpiry chk
            uiEty.objToUi(tItemTrakNonStockItem, i.getNonStockItems());//   i.setNonStockItems(tItemTrakNonStockItem.isSelected());//tItemTrakNonStockItem chk
            uiEty.objToUi(tItemTrakManfctringItem, i.getManufactItem());//   i.setManufactItem(tItemTrakManfctringItem.isSelected());//tItemTrakManfctringItem chk      
            uiEty.objToUi(tItemTrakInactive, i.getInactive());//   i.setInactive(tItemTrakInactive.isSelected());//tItemTrakInactive chk      
            uiEty.objToUi(tWholesalePrice, i.getWholesalePrice());//   i.setWholesalePrice(uiEty.tcToDble0(tWholesalePrice));//tWholesalePrice      
            uiEty.objToUi(tMetaInfo, i.getMetaInfo());//   i.setMetaInfo(tMetaInfo.getText());  //tMetaInfo
            itemVariation2Ui(i.getVariations());//uiEty.objToUi(//   i.setVariations(ui2ItemVariation(tblVariation));            
            extraSalesPrice2Ui(i.getExtrasalespriceCollection());//uiEty.objToUi(//   i.setExtrasalespriceCollection(ui2ExtraSalesPrice(tblPriceRanges));
            //        
loadImagesToPanel(i.getCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void itemVariation2Ui(List<ItemVariation> lstOfVariation) {

        try {

            if (lstOfVariation == null) {
                return;
            }
            for (Iterator<ItemVariation> it = lstOfVariation.iterator(); it.hasNext();) {
                ItemVariation i = it.next();

                TableUtil.addrow(tblVariation, new Object[]{i.getDescription(), i.getsPrice1(), i.getsPrice2()});

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////
    public void extraSalesPrice2Ui(List<ExtraSalesPrice> lstOfExtraPrice) {


        try {

            if (lstOfExtraPrice == null) {
                return;
            }
            for (Iterator<ExtraSalesPrice> it = lstOfExtraPrice.iterator(); it.hasNext();) {
                ExtraSalesPrice i = it.next();

                TableUtil.addrow(tblVariation, new Object[]{i.getDescription(), i.getPrice()});

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////
    public void barcode2Ui(List<ItemBarcode> lstOfBarcode) {


        try {
            if (lstOfBarcode == null) {
                return;
            }

            for (Iterator<ItemBarcode> it = lstOfBarcode.iterator(); it.hasNext();) {
                ItemBarcode i = it.next();

                TableUtil.addrow(tblVariation, new Object[]{i.getType(), i.getBarcode()});

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////
    private void cSaveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cSaveBtnActionPerformed


        try {
            //validate vendor..
            //code
            //description
            //salesprice

            if (uiEty.tcToStr(tItemcode) == null || uiEty.tcToStr(tItemcode).equals("")) {
                MessageBoxes.wrnmsg(null, "Please Type Item Code", "Empty Item Code");
                return;
            }
            
            if(getSupplier(uiEty.cmbtostr(tSupplierItem))==null){
          
        MessageBoxes.wrnmsg(null, "No Supplier Found For This Item.", "Empty");
      //  Sessions.getObj("");     
                return;
            }
            
            //if customer copy item and edit item here..
            //copieditemId will nt be empty it carries copied item id.


            Item item = uiToEntity(new Item());
            //if item coming from list view only this if condition works
            if (getCopiedItemId() != null) {
                if (pasteItem(item, getCopiedItemId())) {
                      saveImages(item.getCode(),images);
                    clearMaster();
                    listUi.callVeryFirstPage();
                   // MessageBoxes.okmsg(null, "Updated.", "Item");
                  
                    return; //return is very important..to exit the method ...
                    

                }

            }


            Item exist = itemService.getDao().findItemByCode(item.getCode());
            if (exist == null) {
                itemService.getDao().save(item);

                saveImages(item.getCode(),images);
            } else {
                //item exist so ask user to update ...
                String[] ObjButtons = {"Yes", "No"};
                int PromptResult = JOptionPane.showOptionDialog(null, "Item Already Exist Do You Want to Update it?", "Item Form", -1, 2, null, ObjButtons, ObjButtons[1]);

                if (PromptResult == 0) {
                    
                    
                    item.setId(exist.getId());
                    itemService.getDao().update(item);
                    
                  deleteImages(item.getCode());
                  saveImages(item.getCode(),images);
                  
                    
                }else{
          return;
      }

            }
            // addToTable(items);
//        ipu.populateTable(items);
            clearMaster();
        } catch (Exception e) {
            e.printStackTrace();
            MessageBoxes.errormsg(null, e.getMessage(), "Error");
        }


    }//GEN-LAST:event_cSaveBtnActionPerformed

    public void saveImages(String itemid,List<File> images){
        try {int x=1;
            for (File img : images) {
                System.out.println("img is "+img.getAbsolutePath());                          
     new File(FormMaster.ITEM_IMAGE_PATH).mkdirs();
     
       String newImagePath = FormMaster.ITEM_IMAGE_PATH+x+"-"+itemid +"-"+ img.getName().substring(img.getName().lastIndexOf("."),img.getName().length());       
        x++;
        
    File imgout1 = new File(newImagePath);
 
      imgout1.mkdirs();         
    boolean d = ImageIO.write(ImageIO.read(img), getExtension(img), imgout1);         
            
            }
     // this.images.clear();            
        } catch (Exception e) {
        e.printStackTrace();}
        
    }
    //////////////////////////////////////
    public void deleteImages(String itemid){
        try {
    File f=new File(FormMaster.ITEM_IMAGE_PATH);
    File[] ff=f.listFiles(); 
   
    if(ff!=null){
        for (File file : ff) {
 boolean b=Pattern.compile(Pattern.quote("-"+itemid +"-"), Pattern.CASE_INSENSITIVE).matcher(file.getName()).find();      
     if(b){
     file.delete();
  
     }
        
        }
         
    }
        } catch (Exception e) {
       e.printStackTrace();
        }
    }
    ///////////////////////////////////

      //////////////////////////////////////
    public void loadImagesToPanel(String itemid){
        
        try {
    File f=new File(FormMaster.ITEM_IMAGE_PATH);
    File[] ff=f.listFiles(); 
   List<File> itemImages=new ArrayList<File>();  
    
    if(ff!=null){
        for (final File image : ff) {
 boolean b=Pattern.compile(Pattern.quote("-"+itemid +"-"), Pattern.CASE_INSENSITIVE).matcher(image.getName()).find();      
     if(b){
     //load images to the panel 
    final JLabel jl = new JLabel();

                jl.addMouseListener(new MouseAdapter() {

                    JPopupMenu p = null;

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        p = viewLargeImg(jl,image);
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        if (p != null) {
                            p.setVisible(false);
                        }
                    }
                });

                cPanel4.add(imagesloadresize(image.getAbsolutePath(), jl));

     cPanel4.add(imagesloadresize(image.getAbsolutePath(),jl));
  cPanel4.revalidate();
     
     
     }
        
        }
         
    }
        } catch (Exception e) {
       e.printStackTrace();
        }
    }
    
///////////////////////////////////////////////////////////////////////

    
    public boolean pasteItem(Item i, String itemid) throws Exception {
        boolean b = false;
        try {
            i.setId(itemid);
            itemService.getDao().update(i);
            b = true;
        } catch (Exception e) {
            e.printStackTrace();

            b = false;
            throw e;
        }
        return b;
    }
    
    ////////////////////////////////////////////
public String getExtension(File f)
      throws Exception
    {
   String extension = f.getName();
      int i = extension.lastIndexOf('.');

      if ((i > 0) && (i < extension.length() - 1)) {
    extension = extension.substring(i + 1).toLowerCase();
     }
     return extension;
   }

//////////////////////////////////

    private void tItemTrakExpiryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tItemTrakExpiryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tItemTrakExpiryActionPerformed

    private void tItemTrakNonStockItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tItemTrakNonStockItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tItemTrakNonStockItemActionPerformed

    private void tItemSalesPriceUnit2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tItemSalesPriceUnit2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tItemSalesPriceUnit2ActionPerformed

    private void tUnitItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tUnitItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tUnitItem1ActionPerformed

    private void tItemTrakSerialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tItemTrakSerialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tItemTrakSerialActionPerformed

    private void tPriceRangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tPriceRangeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tPriceRangeActionPerformed

    private void tItemdiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tItemdiscountActionPerformed
        try {
            double dis = uiEty.tcToDble0(tItemdiscount);
            if (dis != 0) {

                dis = dis / 100 * uiEty.tcToDble0(tItemSalesPriceUnit1);
                uiEty.objToUi(tItemdiscValue, dis);

            } else {
                uiEty.objToUi(tItemdiscValue, "");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tItemdiscountActionPerformed

    private void tItemCommissionValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tItemCommissionValueActionPerformed
        try {
            double comAmount = uiEty.tcToDble0(tItemCommissionValue);
            if (comAmount != 0) {

                comAmount = comAmount / uiEty.tcToDble0(tItemSalesPriceUnit1) * 100;
                uiEty.objToUi(tItemCommission, comAmount);
            } else {
                uiEty.objToUi(tItemCommission, "");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tItemCommissionValueActionPerformed

    private void tItemdiscValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tItemdiscValueActionPerformed
        try {
            double disAmount = uiEty.tcToDble0(tItemdiscValue);
            if (disAmount != 0) {

                disAmount = disAmount / uiEty.tcToDble0(tItemSalesPriceUnit1) * 100;
                uiEty.objToUi(tItemdiscount, disAmount);
            } else {
                uiEty.objToUi(tItemdiscount, "");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tItemdiscValueActionPerformed

    private void tItemCommissionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tItemCommissionActionPerformed
        try {
            double com = uiEty.tcToDble0(tItemCommission);
            if (com != 0) {

                com = com / 100 * uiEty.tcToDble0(tItemSalesPriceUnit1);
                uiEty.objToUi(tItemCommissionValue, com);

            } else {
                uiEty.objToUi(tItemCommissionValue, "");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_tItemCommissionActionPerformed

    private void tItemSalesPriceUnit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tItemSalesPriceUnit1ActionPerformed
        try {
            if (!uiEty.cmbtostr(tUnitItem2).trim().equals("") && uiEty.tcToDble0(tDifferentPerUnit) > 0) {


                tItemSalesPriceUnit2.setText("" + uiEty.tcToDble0(tItemSalesPriceUnit1) / uiEty.tcToDble0(tDifferentPerUnit));


            } else {
                tItemSalesPriceUnit2.setText("");
            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_tItemSalesPriceUnit1ActionPerformed

    private void tRngeValueKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tRngeValueKeyTyped
        try {
            if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
                //need to clear table before adding rows....
                addToExtraPriceTbl();
                tPriceRange.setSelectedItem("");
                tRngeValue.setText("");
                tPriceRange.getEditor().getEditorComponent().requestFocus();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tRngeValueKeyTyped

    private void tVariationPrice2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tVariationPrice2KeyTyped
        try {

            if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
                //need to clear table before adding rows....
                addToVariationTbl();

                tVariationName.setSelectedItem("");
                tVariationPrice1.setText("");
                tVariationPrice2.setText("");

                tVariationName.getEditor().getEditorComponent().requestFocus();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tVariationPrice2KeyTyped

    private void cClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cClearActionPerformed
        clearMaster();
    }//GEN-LAST:event_cClearActionPerformed

    private void cDeleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cDeleteBtnActionPerformed
        try {

            if (uiEty.tcToStr(tItemcode) == null || uiEty.tcToStr(tItemcode).equals("")) {
                MessageBoxes.wrnmsg(null, "Please Type Item Code", "Empty Item Code");
                return;
            }

            Item item = uiToEntity(new Item());

            Item exist = itemService.getDao().findItemByCode(item.getCode());
            if (exist != null) {

                String[] ObjButtons = {"Yes", "No"};
                int PromptResult = JOptionPane.showOptionDialog(null, "Are you want to delete ?", "Item Form", -1, 2, null, ObjButtons, ObjButtons[1]);


                if (PromptResult == 0) {
                    itemService.getDao().delete(exist);
 deleteImages(exist.getCode());   
                }

            } else {
                MessageBoxes.errormsg(null, "No Item Found.", getTabName());
                return;

            }
            clearMaster();
        } catch (Exception e) {

            MessageBoxes.errormsg(null, e.getMessage(), getTabName());
        }

    }//GEN-LAST:event_cDeleteBtnActionPerformed

    private void cCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cCloseActionPerformed
        //cal item list form....
        //hide this form
        callListTab();

    }//GEN-LAST:event_cCloseActionPerformed

    private void tTypeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tTypeKeyTyped
        try {
            if (evt.getKeyChar() == KeyEvent.VK_ENTER) {

                tItemBarcode.requestFocus();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tTypeKeyTyped

    private void tItemBarcodeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tItemBarcodeKeyTyped
        try {
            if (evt.getKeyChar() == KeyEvent.VK_ENTER) {

                addToBarcode();
                tType.setText("");
                tItemBarcode.setText("");
                tType.requestFocus();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tItemBarcodeKeyTyped

    private void tVariationPrice1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tVariationPrice1KeyTyped

        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {

            tVariationPrice2.requestFocus();

        }

    }//GEN-LAST:event_tVariationPrice1KeyTyped

    private void tVariationPrice2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tVariationPrice2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tVariationPrice2ActionPerformed

    private void cButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cButton1ActionPerformed
        try {
        //    loadImagesToPanel("1000");
   //  deleteImages("1000");                            
       //     jPanel4.removeAll();
            chooser.showOpenDialog(null);
            File[] files = chooser.getSelectedFiles();
//      JPanel panel = new JPanel(new FlowLayout());
//        
//        panel.setLayout(new FlowLayout());
//   JPopupMenu p=new JPopupMenu("imagepanel");
            //  cScrollPane1.add(panel);     
            for (final File image : files) {
                
       images.add(image);
                 System.out.println("addedd image");
//    ImagePanel ii=new ImagePanel(new ImageIcon(image.getCanonicalPath()).getImage(),1);
//ImagePanel ii2=new ImagePanel(new ImageIcon("C:/Documents and Settings/Administrator/Desktop/mazari.jpg").getImage(),1);

                //panel.setPreferredSize(new Dimension(300, 200));
                //ii2.setVisible(true);

//      System.out.println("canonical o]path "+image.getCanonicalPath());
//      System.out.println("absolute o]path "+image.getAbsolutePath());
                //     panel.add(new JLabel(new ImageIcon(image.getAbsolutePath())));                             
                // panel.add(ii2);                             
                // panel.setPreferredSize(new Dimension(200, 200));  
                ////    panel.add(new JLabel(new ImageIcon("C:/Documents and Settings/Administrator/Desktop/mazari.jpg")));
                //ImagePanel ii=new ImagePanel(new ImageIcon("C:/Documents and Settings/Administrator/Desktop/mazari.jpg").getImage(),1);
//   ii.setPreferredSize(new Dimension(200,150));
//   ii.setVisible(true);

                //jScrollPane3.add(new JLabel(new ImageIcon("C:/Documents and Settings/Administrator/Desktop/mazari.jpg")));                   
// jScrollPane3.add(panel);                   


                final JLabel jl = new JLabel();

                jl.addMouseListener(new MouseAdapter() {

                    JPopupMenu p = null;

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        p = viewLargeImg(jl,image);
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        if (p != null) {
                            p.setVisible(false);
                        }
                    }
                });

                cPanel4.add(jl);


             cPanel4.add(imagesloadresize(image.getAbsolutePath(), jl));

            }
            cPanel4.revalidate();
  
MessageBoxes.okmsg(null,"ok","title");
//    panel.setVisible(true);  
//   p.add(panel);
//   p.show(this,10, 100);
//   p.setVisible(true);
//          
        } catch (Exception e) {

            e.printStackTrace();

        }

    }//GEN-LAST:event_cButton1ActionPerformed
    public JPopupMenu viewLargeImg(JLabel lbl,File image) {
      //  System.out.println("viewlargeImg Methd image name is "+image.getAbsolutePath());
        JPopupMenu p = new JPopupMenu("imagepanel");
        try {
            JPanel panel = new JPanel(new FlowLayout());
         //      panel.add(new JLabel(new ImageIcon("C:/Documents and Settings/Administrator/Desktop/mazari.jpg")));           
            Icon i = lbl.getIcon();
        //    ImagePanel ii = new ImagePanel(iconToImage(i), 50);
        //    ImagePanel ii = new ImagePanel(image, 1);
         //   panel.add(imagesloadresize( , lbl));
          JLabel jj=new JLabel();   
          jj.setIcon(new ImageIcon( iconToImage(i).getScaledInstance(250, 250,0)));
            panel.add(jj);
            panel.setVisible(true);
            panel.revalidate();
            p.add(panel);
           
            p.show(this, Toolkit.getDefaultToolkit().getScreenSize().width/2, 0);
            p.setVisible(true);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }

    static Image iconToImage(Icon icon) {
        if (icon instanceof ImageIcon) {
            return ((ImageIcon) icon).getImage();
        } else {
            int w = icon.getIconWidth();
            int h = icon.getIconHeight();
            GraphicsEnvironment ge =
                    GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice gd = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gd.getDefaultConfiguration();
      //      BufferedImage image = gc.createCompatibleImage(w, h);
           BufferedImage image = gc.createCompatibleImage(500,500);
            Graphics2D g = image.createGraphics();
            icon.paintIcon(null, g, 0, 0);
            g.dispose();
            return image;
        }
    }

    private JLabel imagesloadresize(String ss, JLabel jl) {
        ImageIcon i = new ImageIcon(ss);
        Image i1 = i.getImage().getScaledInstance(100, 100, 4);

        Icon i12 = new ImageIcon(i1);
        jl.setIcon(i12);
        return jl;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.components.controls.CButton cButton1;
    private org.components.controls.CButton cClear;
    private org.components.controls.CButton cClose;
    private org.components.controls.CButton cDeleteBtn;
    private org.components.controls.CLabel cLabel1;
    private org.components.controls.CLabel cLabel2;
    private org.components.controls.CLabel cLabel3;
    private org.components.controls.CLabel cLabel4;
    private org.components.controls.CLabel cLabel5;
    private org.components.controls.CLabel cLabel6;
    private org.components.controls.CLabel cLabel7;
    private org.components.containers.CPanel cPanel1;
    private org.components.containers.CPanel cPanel2;
    private org.components.containers.CPanel cPanel3;
    private org.components.containers.CPanel cPanel4;
    private org.components.containers.CPanel cPanel5;
    private org.components.controls.CButton cSaveBtn;
    private org.components.controls.CScrollPane cScrollPane1;
    private org.components.util.ComponentFactory componentFactory1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private org.components.controls.CTextField tCartonItem;
    private org.components.controls.CTextField tDifferentPerUnit;
    private org.components.controls.CTextField tItemBarcode;
    private org.components.controls.CComboBox tItemCategory;
    private org.components.controls.CTextField tItemCommission;
    private org.components.controls.CTextField tItemCommissionValue;
    private org.components.controls.CTextField tItemCostPrice;
    private org.components.controls.CTextField tItemDescription;
    private org.components.controls.CTextField tItemLandingCost;
    private org.components.controls.CComboBox tItemLocation;
    private org.components.controls.CTextField tItemMinimumPrice;
    private org.components.controls.CTextField tItemMinimumStock;
    private org.components.controls.CTextField tItemReOrder;
    private org.components.controls.CTextField tItemSalesPriceUnit1;
    private org.components.controls.CTextField tItemSalesPriceUnit2;
    private org.components.controls.CCheckBox tItemTrakExpiry;
    private org.components.controls.CCheckBox tItemTrakInactive;
    private org.components.controls.CCheckBox tItemTrakManfctringItem;
    private org.components.controls.CCheckBox tItemTrakNonStockItem;
    private org.components.controls.CCheckBox tItemTrakSerial;
    private org.components.controls.CTextField tItemcode;
    private org.components.controls.CTextField tItemdiscValue;
    private org.components.controls.CTextField tItemdiscount;
    private org.components.controls.CTextArea tMetaInfo;
    private org.components.controls.CComboBox tPriceRange;
    private org.components.controls.CTextField tRngeValue;
    private org.components.controls.CComboBox tSupplierItem;
    private org.components.controls.CTextField tType;
    private org.components.controls.CComboBox tUnitItem1;
    private org.components.controls.CComboBox tUnitItem2;
    private org.components.controls.CComboBox tVariationName;
    private org.components.controls.CTextField tVariationPrice1;
    private org.components.controls.CTextField tVariationPrice2;
    private org.components.controls.CTextField tWholesalePrice;
    private javax.swing.JTable tblBarcode;
    private javax.swing.JTable tblPriceRanges;
    private javax.swing.JTable tblVariation;
    // End of variables declaration//GEN-END:variables

    @Override
    public String getTabName() {
        return "Item Master";
    }

    @Override
    public JPanel getJPanel() {
        return this;
    }
    //////////////////////////////////////////////

    public Supplier getSupplier(String typedName) throws Exception {
        Supplier s = null;
        try {
         
        s=new SupplierDAO().findSupplierByCode(typedName);
        
        
            
        } catch (Exception e) {

            e.printStackTrace();
        }

        return s;
    }
    //////////////////////////////////////////////////////

    public static void main(String[] args) {
        final JTabbedPane tp = new JTabbedPane();

        // Remove Tab as the focus traversal key - Could always add another key stroke here instead.
        tp.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.<AWTKeyStroke>emptySet());

        KeyStroke ks = KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0);

        Action nextTab = new AbstractAction("NextTab") {

            public void actionPerformed(ActionEvent evt) {
                int i = tp.getSelectedIndex();
                tp.setSelectedIndex(i == tp.getTabCount() - 1 ? 0 : i + 1);
            }
        };

        // Register action.
        tp.getActionMap().put("NextTab", nextTab);
        tp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(ks, "NextTab");

        tp.addTab("Foo", new JPanel());
        tp.addTab("Bar", new JPanel());
        tp.addTab("Baz", new JPanel());
        tp.addTab("Qux", new JPanel());

        JFrame frm = new JFrame();

        frm.setLayout(new BorderLayout());
        frm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frm.add(new JButton(nextTab), BorderLayout.NORTH);
        frm.add(tp, BorderLayout.CENTER);
        frm.setBounds(50, 50, 400, 300);
        frm.setVisible(true);
    }

    /**
     * @return the selectedItem
     */
    public Item getSelectedItem() {
        return selectedItem;
    }

    /**
     * @param selectedItem the selectedItem to set
     */
    public void setSelectedItem(Item selectedItem) {
        this.selectedItem = selectedItem;
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
     * @return the listUi
     */
    public ItemListUi getListUi() {
        return listUi;
    }

    /**
     * @param listUi the listUi to set
     */
    public void setListUi(ItemListUi listUi) {
        this.listUi = listUi;
    }

    /**
     * @return the copiedItemId
     */
    public String getCopiedItemId() {
        return copiedItemId;
    }

    /**
     * @param copiedItemId the copiedItemId to set
     */
    public void setCopiedItemId(String copiedItemId) {
        this.copiedItemId = copiedItemId;
    }

    public class ImagePanel extends JPanel {

        private double m_zoom = 1.0;
        private double m_zoomPercentage;
        private Image m_image;

        /** 
         * Constructor 
         *  
         * @param image 
         * @param zoomPercentage 
         */
        public ImagePanel(Image image, double zoomPercentage) {
            m_image = image;
            m_zoomPercentage = zoomPercentage / 100;
        }

        /** 
         * This method is overriden to draw the image 
         * and scale the graphics accordingly 
         */
        public void paintComponent(Graphics grp) {
            Graphics2D g2D = (Graphics2D) grp;

            //set the background color to white 
            g2D.setColor(Color.WHITE);
            //fill the rect 
            g2D.fillRect(0, 0, getWidth(), getHeight());

            //scale the graphics to get the zoom effect 
            g2D.scale(m_zoom, m_zoom);

            //draw the image 
            g2D.drawImage(m_image, 0, 0, this);
        }

        /** 
         * This method is overriden to return the preferred size 
         * which will be the width and height of the image plus 
         * the zoomed width width and height.  
         * while zooming out the zoomed width and height is negative 
         */
//        public Dimension getPreferredSize() 
//        { 
//            return new Dimension((int)(m_image.getWidth(this) +  
//                                      (m_image.getWidth(this) * (m_zoom - 1))), 
//                                 (int)(m_image.getHeight(this) +  
//                                      (m_image.getHeight(this) * (m_zoom -1 )))); 
//        } 
        public Dimension getPreferredSize() {
            return new Dimension(250, 250);
        }

        /** 
         * Sets the new zoomed percentage 
         * @param zoomPercentage 
         */
        public void setZoomPercentage(int zoomPercentage) {
            m_zoomPercentage = ((double) zoomPercentage) / 100;
        }

        /** 
         * This method set the image to the original size 
         * by setting the zoom factor to 1. i.e. 100% 
         */
        public void originalSize() {
            m_zoom = 1;
        }

        /** 
         * This method increments the zoom factor with 
         * the zoom percentage, to create the zoom in effect  
         */
        public void zoomIn() {
            m_zoom += m_zoomPercentage;
        }

        /** 
         * This method decrements the zoom factor with the  
         * zoom percentage, to create the zoom out effect  
         */
        public void zoomOut() {
            m_zoom -= m_zoomPercentage;

            if (m_zoom < m_zoomPercentage) {
                if (m_zoomPercentage > 1.0) {
                    m_zoom = 1.0;
                } else {
                    zoomIn();
                }
            }
        }

        /** 
         * This method returns the currently 
         * zoomed percentage 
         *  
         * @return 
         */
        public double getZoomedTo() {
            return m_zoom * 100;
        }
    }
}
