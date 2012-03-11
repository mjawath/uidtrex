/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.invoicesystem.service.transactions;

import org.biz.dao.service.Service;
import org.biz.invoicesystem.dao.transactions.PurchaseInvoiceDAO;

/**
 *
 * @author mjawath
 */
public class PurchaseInvoiceService extends Service {
    PurchaseInvoiceDAO dao;

    public PurchaseInvoiceService() {
    dao = new PurchaseInvoiceDAO();
    }

    public PurchaseInvoiceDAO getDao() {
        return dao;
    }
   

}
