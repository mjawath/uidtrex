/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.invoicesystem.dao.master;

import org.biz.dao.service.GenericDAO;
import org.biz.invoicesystem.entity.master.Shop;

/**
 *
 * @author d
 */
public class ShopDao extends GenericDAO<Shop> {

    public ShopDao() {
    setCls(Shop.class);
    }
    
}
