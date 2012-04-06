/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.dao.service;

import org.biz.dao.util.EntityService;

/**
 *
 * @author mjawath
 */
public class Service {

    EntityService es;
    Cache cache;
    GenericDAO dao;
    int pageSize;

    public Service() {
        es = EntityService.getEntityService();
    }

    public GenericDAO getDao() {

        if (dao == null) {
            dao = new GenericDAO();
        }
        return dao;
    }

    public Cache getCache() {
        return dao.getCache();
    }

    public void moveToNextPage(String queryname, int currentpage) {
        dao.pagedData(queryname, currentpage);

    }

    public void getNextPage(String qry, int page) {
        dao.pagedData(qry, page);
    }

    public void getPreviousePage(String qry, int page) {
        dao.pagedData(qry, page);
    }

    public void getLastPage(String qry) {
        dao.pagedData(qry);
    }

    public void getFirstPage(String qry) {
        dao.pagedData(qry);
    }
}
