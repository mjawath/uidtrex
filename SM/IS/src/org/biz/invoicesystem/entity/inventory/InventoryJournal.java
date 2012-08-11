/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.invoicesystem.entity.inventory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import org.biz.entity.BusObj;
import org.biz.invoicesystem.entity.master.Warehouse;
import org.biz.invoicesystem.entity.master.Shop;

/**
 *
 * @author Admin
 */
@Entity
public class InventoryJournal  extends BusObj implements Serializable {

    public  static final long serialVersionUID = 1L;
    public  static final Byte pos_Invoice =0;
    public  static final Byte sales_Invoice = 1;
    public  static final Byte Item_In = 1;
    public  static final Byte Item_Out = 0;

    private String code;
    private Byte documentType;//invoice //transferorder//begbalance//adjestments
    private String documentClass;//classs type of document
    private String refCode;//reference document ids
    @JoinColumn(name = "inv_id")    
    @OneToMany(cascade=CascadeType.ALL,orphanRemoval=true)
    List<InventoryJournalLine> lines;
    private Byte  inOrOut; // to represent the state of  inventory entry in a top level 

    public Byte getInOrOut() {
        return inOrOut;
    }

    public void setInOrOut(Byte inOrOut) {
        this.inOrOut = inOrOut;
    }
    @OneToOne
    private Shop shop;
    @OneToOne
    private Warehouse warehouse;
    
    String docType;
    String docRefNo;
    
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date entryDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date createdDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date modifiedDate;
    

    
    public void addIJLine(InventoryJournalLine ij){
    if(lines==null){
    createInvJouLines();
    }
    lines.add(ij);
    }
    
    public Byte getDocumentType() {
        return documentType;
    }

    public void setDocumentType(Byte documentType) {
        this.documentType = documentType;
    }

    public String getDocRefNo() {
        return docRefNo;
    }

    public void setDocRefNo(String docRefNo) {
        this.docRefNo = docRefNo;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getDocumentClass() {
        return documentClass;
    }

    public void setDocumentClass(String documentClass) {
        this.documentClass = documentClass;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }
 

    public List<InventoryJournalLine> getLines() {
        return lines;
    }

    public void setLines(List<InventoryJournalLine> lines) {
        this.lines = lines;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getRefCode() {
        return refCode;
    }

    public void setRefCode(String refCode) {
        this.refCode = refCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        //// TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InventoryJournalLine)) {
            return false;
        }
        InventoryJournal other = (InventoryJournal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    

    @Override
    public String toString() {
        return "org.biz.invoicesystem.entity.master.InventoryJournal[id=" + id + "]";
    }

    private void createInvJouLines() {
        if(lines==null){
        lines = new ArrayList<InventoryJournalLine>();        
        return;
        }
        lines.clear();
        
    }
}
/*

  top level implementation of the inventory .
 * as a journal so that data mining can be eased 


* item mark is used to represents the
* sku for perticular item
 */


