/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.biz.dao.service;

import java.util.List;
import java.util.Map;

/**
 *
 * @author nnjj
 */
public class Cache {
String key;
String specialKey;
int CurrentPage ;
List list;
Map<String,Cache> map;

    public int getCurrentPage() {
        return CurrentPage;
    }

    public void setCurrentPage(int CurrentPage) {
        this.CurrentPage = CurrentPage;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public Map<String, Cache> getMap() {
        return map;
    }

    public void setMap(Map<String, Cache> map) {
        this.map = map;
    }

    public String getSpecialKey() {
        return specialKey;
    }

    public void setSpecialKey(String specialKey) {
        this.specialKey = specialKey;
    }
    
    public List getbySpecialKey(String key,String spclkey ,int pageno){
//         list=getMap().get(key).0;
         return list; 
    }
    
}
