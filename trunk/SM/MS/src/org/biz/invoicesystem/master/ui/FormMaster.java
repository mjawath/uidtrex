 
package org.biz.invoicesystem.master.ui;

 
public interface FormMaster {
    
    String ITEM_IMAGE_PATH="images//item//";

    int GRID_LIST_SIZE=50;
 public void keyListeners();
 
  public Object uiToEntity(Object i);
  
 public void entity2Ui(Object i);
 
 
}