/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package research.ui.demo;

import java.util.ArrayList;
import java.util.List;
import org.biz.dao.service.GenericDAO;
import org.biz.dao.util.EntityService;
import org.biz.invoicesystem.entity.master.Customer;
import org.biz.invoicesystem.entity.master.Item;
import org.biz.invoicesystem.entity.master.Shop;
import org.biz.invoicesystem.entity.master.Staff;
import org.biz.invoicesystem.entity.master.Supplier;

/**
 *
 * @author nnjj
 */
public class dbCreation {
    
    public static void main(String[] args) {
      System.out.println("hop "+Shop.class);
        Shop s=new Shop();
        s.setId("fdf5");
        new GenericDAO().save(s);
        
    }
    public void createmster(){
    List lst = new ArrayList();
        for (int i = 0; i < 1500; i++) {
            Supplier cus = new Supplier();
            cus.setId( EntityService.getKeyStr());
            cus.setCode( EntityService.getKeyStr());
            cus.setName(EntityService.getKeyStr());
            lst.add(cus);
        }
        new GenericDAO<Customer>().saveList(lst);
        List lstx = new ArrayList();
        for (int i = 0; i < 1500; i++) {
            Customer cus = new Customer();
            cus.setId(EntityService.getKeyStr());
            cus.setCode(EntityService.getKeyStr());
            cus.setCustomerName(EntityService.getKeyStr());
            lstx.add(cus);
        }
        new GenericDAO<Customer>().saveList(lstx);
        List lst22 = new ArrayList();
        for (int i = 0; i < 1500; i++) {
            Item cus = new Item();
            cus.setId(EntityService.getKeyStr());
            cus.setCode(EntityService.getKeyStr());
            cus.setDescription(EntityService.getKeyStr());
            lst22.add(cus);
        }
        new GenericDAO<Item>().saveList(lst22);
        List lst2 = new ArrayList();
        for (int i = 0; i < 1500; i++) {
            Staff cus = new Staff();
            cus.setId(EntityService.getKeyStr());
            cus.setCode(EntityService.getKeyStr());
            cus.setName(EntityService.getKeyStr());
            lst2.add(cus);
        }
        new GenericDAO<Staff>().saveList(lst2);
    }
  
}
