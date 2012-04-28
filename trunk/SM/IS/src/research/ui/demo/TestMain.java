/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package research.ui.demo;

import javax.swing.JFrame;
import org.biz.erp.inventory.ui.BarCodeCreatorUI;

/**
 *
 * @author d
 */
public class TestMain {
    public static void main(String[] args) {
        JFrame  jf=new JFrame();
        jf.setSize(600,600);
     
        jf.add(new BarCodeCreatorUI());
        
        jf.setVisible(true);
    }
  
}
