/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.invoicesystem.entity.master;

import javax.persistence.Entity;
import org.biz.invoicesystem.entity.BusObj;

/**
 *
 * @author nnjj
 */
@Entity
public class Shop extends BusObj{
    public static void main(String[] args) {
        System.out.println("hop "+Shop.class);
    }
}
