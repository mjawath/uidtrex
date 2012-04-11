/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.books.entity.accounts.ledger;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author mjawath
 */
@Entity
public class Accounts implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String assests = "assests";
    private static final String liablity = "liablity";
    private static final String equity = "equity";
    private static final String expence = "expence";
    private static final String income = "income";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    String primaryAccounts;//assest = liability+equity
    String subAccounts;//assest+expence = liability+equity+income
    String accountsName;
    String accountsNo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    
    public String getAccountsName() {
        return accountsName;
    }

    public void setAccountsName(String accountsName) {
        this.accountsName = accountsName;
    }

    public String getAccountsNo() {
        return accountsNo;
    }

    public void setAccountsNo(String accountsNo) {
        this.accountsNo = accountsNo;
    }

    public String getPrimaryAccounts() {
        return primaryAccounts;
    }

    public void setPrimaryAccounts(String primaryAccounts) {
        this.primaryAccounts = primaryAccounts;
    }

    public String getSubAccounts() {
        return subAccounts;
    }

    public void setSubAccounts(String subAccounts) {
        this.subAccounts = subAccounts;
    }

    enum primaryAccounts {

        assests, liablity, equity
    }

    enum subAccounts {

        assests, expence, liablity, equity, income
    }
}