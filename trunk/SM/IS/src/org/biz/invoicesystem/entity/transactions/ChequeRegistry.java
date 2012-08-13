/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.invoicesystem.entity.transactions;


import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import org.biz.chequeChequing.entity.accounts.bank.Cheques;
import org.biz.entity.BusObj;
import org.biz.invoicesystem.entity.master.Persons;

/**
 *
 * @author jawath
 */
@Entity
public class ChequeRegistry extends BusObj {

    @OneToOne
    private Cheques cheques;
    @OneToOne
//    private Customer customer;//may be use persons
    private Persons persons;
    private String remarks;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date processedDate;
//    private account
//can we bring the bank account detail to heare
    ///accounts id ??
    //bank
    //customer
    // get above from

}

/*
 i think it unnessasary
 *
 *

 */