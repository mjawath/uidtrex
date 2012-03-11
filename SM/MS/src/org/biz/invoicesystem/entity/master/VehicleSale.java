/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.invoicesystem.entity.master;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author Administrator
 */
@Entity
 
public class VehicleSale extends  Shop implements Serializable {
  
    
    long Code;//this must be autonumber...for tracking purpose...
     
    
}
