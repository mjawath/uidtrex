/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.invoicesystem.service.inventory;

import org.biz.dao.service.Service;
import org.biz.invoicesystem.dao.inventory.InventoryJournalDAO;

/**
 *
 * @author mjawath
 */
public class InventoryJournalService extends Service {
    InventoryJournalDAO dao;

    public InventoryJournalService() {
        dao = new InventoryJournalDAO();
    }

    @Override
    public InventoryJournalDAO getDao() {
        return dao;
    }

}
