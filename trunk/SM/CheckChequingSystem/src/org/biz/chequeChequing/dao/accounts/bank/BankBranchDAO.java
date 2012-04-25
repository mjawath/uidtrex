/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.chequeChequing.dao.accounts.bank;

import org.biz.chequeChequing.entity.accounts.bank.BankBranch;
import org.biz.dao.service.GenericDAO;

/**
 *
 * @author mjawath
 */
public class BankBranchDAO extends GenericDAO<BankBranch>{

    public BankBranchDAO() {
        setCls(BankBranch.class);
    }


}
