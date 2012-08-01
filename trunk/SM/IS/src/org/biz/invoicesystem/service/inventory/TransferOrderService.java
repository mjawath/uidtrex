
package org.biz.invoicesystem.service.inventory;

import org.biz.dao.service.Service;
import org.biz.dao.util.EntityService;
import org.biz.erp.inventory.dao.TransferOrderDAO;
import org.biz.invoicesystem.dao.transactions.SalesInvoiceDAO;
import org.biz.invoicesystem.entity.inventory.InventoryJournal;
import org.biz.invoicesystem.entity.inventory.InventoryJournalLine;
import org.biz.invoicesystem.entity.inventory.TransferOrder;
import org.biz.invoicesystem.entity.inventory.TransferOrderLineItem;
import org.biz.invoicesystem.entity.transactions.SalesInvoice;
import org.biz.invoicesystem.entity.transactions.SalesInvoiceLineItem;

/**
 *
 * @author d
 */
public class TransferOrderService extends Service{

    TransferOrderDAO dao;

    public TransferOrderService() {
    dao=new TransferOrderDAO();
    }


    public TransferOrderDAO getDao() {
        return dao;
    }


    public void createInventoryJournal(TransferOrder invoice){
       invoice.setId(EntityService.getKeys());
        //inventory journal
        InventoryJournal ij=new InventoryJournal();
        ij.setId(   EntityService.getKeys()) ;
        for (TransferOrderLineItem sl : invoice.getLineItemList()) {
            InventoryJournalLine ijl=new InventoryJournalLine();
            ijl.setId(sl.getId());
            ijl.setDescription(sl.getId());
            ijl.setItem(sl.getItem());
            ijl.setQty(sl.getQty());
//            ijl.setShop(sl.getShop());
            ijl.setItemMark(sl.getItemMark());
//            ijl.setWarehouse(sl.getWarehouse());

            ij.addIJLine(ijl);
//            ijl.setShop(invoice.gets);
//            jt
                    //shop ware houses
//            ijl.setUom(sl.getQty());
        }
        dao.save(invoice,ij);
    }
}
