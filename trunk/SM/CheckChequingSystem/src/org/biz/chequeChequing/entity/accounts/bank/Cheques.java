/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.chequeChequing.entity.accounts.bank;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import org.biz.entity.BusObj;

/**
 *
 * @author mjawath
 */
@Entity
public class Cheques extends BusObj {
    private static final long serialVersionUID = 1L;
    
    @OneToOne
    private BankBranch bankBranch;
    private String chequeNumber;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOfBank;
    
    

    public BankBranch getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(BankBranch bankBranch) {
        this.bankBranch = bankBranch;
    }
    

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cheques)) {
            return false;
        }
        Cheques other = (Cheques) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.business.accounts.NewEntity[id=" + id + "]";
    }
}
