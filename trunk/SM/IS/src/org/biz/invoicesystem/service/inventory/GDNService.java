/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.invoicesystem.service.inventory;

import org.biz.dao.service.Service;
import org.biz.invoicesystem.dao.inventory.GDNDAO;

/**
 *
 * @author mjawath
 */
public class GDNService extends Service {
    GDNDAO dao;

    public GDNService() {
    dao = new GDNDAO();
    }

    @Override
    public GDNDAO getDao() {
        return dao;
    }

}
