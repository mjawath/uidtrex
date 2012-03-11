package org.biz.invoicesystem.service.master;

import org.biz.dao.service.Service;
import org.biz.invoicesystem.dao.master.ItemDAO;

    
public class ItemService extends Service{
    ItemDAO dao;

    public ItemService() {
    dao = new ItemDAO();
    }

    public ItemDAO getDao() {
        return dao;
    }

}
