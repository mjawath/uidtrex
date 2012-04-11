/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.biz.books.entity.accounts.ledger;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author nano
 */
@Entity
public class TransactionEvent implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Accounts accounts;   
    private BigDecimal cr;
    private BigDecimal dr;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateTrx;
    private String description;
    private String referceDoc;

    @OneToOne
    JounelEntry transactionEvent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof TransactionEvent)) {
            return false;
        }
        TransactionEvent other = (TransactionEvent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Accounts.TransactionEntry[id=" + id + "]";
    }
  
}
