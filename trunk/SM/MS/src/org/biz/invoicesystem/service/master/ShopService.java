/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.invoicesystem.service.master;

import org.biz.dao.service.Service;
import org.biz.invoicesystem.dao.master.ShopDao;

/**
 *
 * @author d
 */
public class ShopService extends Service{

    ShopDao dao;
    public ShopService() {
    dao=new ShopDao();
    }
    
}
