/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.invoicesystem.entity;
//bussiness entity object 

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author nnjj
 */
@MappedSuperclass
public class BusObj implements Serializable{

    public BusObj() {
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
}
