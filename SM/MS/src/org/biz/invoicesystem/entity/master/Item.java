package org.biz.invoicesystem.entity.master;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;


@Entity
public class Item implements Serializable {
 //   private static final long serialVersionUID = 1L;
    @Id
     private String id; //default table id...for 
    private String code;//unique item code...
 //   private String name;
//    @OneToOne
//    private Product product;
    private String description; //item description ....
   // private String inventoryType;

    private String category; 
    
    private String unitOne; //like bags...dozens...boxes..
    
    private Integer different; //unit diferents like 50packets=1 bag...
            
    private String unitTwo; //like pcs ..packets...
    //item has many mariation
    //variation has name value --colur = red/colur = yellow , size = 15 ,size = 20 
    
  //  private String itemID;
  //  private String warehouse;   
 //   private String extraCategory;
    private String supplierId;
    private Double cost;
    private Double salesPrice;
    private Double unit1SalesPrice;
    private Double unit2SalesPrice;
    
    private String type;
 //   private String sections;
    private Double minSalesPrice;
    private Double discount;
    private Double discountValue;
 //   private Double difference;
    private Double minStock;
    private String location;
    private Boolean manufactItem;
//    private String itemExtraInfo;
    private String pic1;
//    private String pic2;
//    private String pic3;
   
//    private Double salesPrice2;
    private Double landCost;
 //   private Double avgCost;
    private Double wholesalePrice;
    private Double commission;
    private Double commissionValue;
    private Double reOrder;
    private Double carton;
    private Boolean nonStockItems;
    private Boolean inactive;
    private Boolean itemcol;
    private Boolean trackingItem;
    private Boolean trackSerial;
    private Boolean trackExpiry;

    String metaInfo; //extra description about item ..we need this some time when we upload this 
                     // item to online....so must be good desc..
    
//      @OneToMany(cascade={javax.persistence.CascadeType.ALL}, mappedBy="item")
//    private List<ItemTex> itemtexCollection;

   @JoinColumn(name="Item_id")
/*     */   @OneToMany(cascade={javax.persistence.CascadeType.ALL, javax.persistence.CascadeType.REMOVE},orphanRemoval=true)
/*     */     private List<ExtraSalesPrice> extrasalespriceCollection;

   @JoinColumn(name="Item_id")
/*     */   @OneToMany(cascade={javax.persistence.CascadeType.ALL, javax.persistence.CascadeType.REMOVE},orphanRemoval=true)
/*     */    private List<ItemVariation> variations;
 
    @JoinColumn(name="Item_id")
/*     */   @OneToMany(cascade={javax.persistence.CascadeType.ALL, javax.persistence.CascadeType.REMOVE},orphanRemoval=true)
/*     */    private List<ItemBarcode> barcodes;
 
   
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
            
     @Temporal(javax.persistence.TemporalType.DATE)
    Date crDate;
//    private Boolean isSentToMaster;
    private Boolean isDeleted;
//    private String loggedinStaff;
//    private List itemtexCollection;
  //  private List extrasalespriceCollection;


    
    
    public static Item find(String code, List<Item> lst) {


        Comparator<Item> com = new Comparator<Item>() {

            public int compare(Item o1, Item o2) {
                return o1.getCode().compareTo(o2.getCode());
            }
        };

        Collections.sort(lst, com);

        Item s = new Item();
        s.setCode(code);
        int num = Collections.binarySearch(lst, s, com);

        if (num > -1) {
            return lst.get(num);
        } else {
            return null;
        }
    }
    
   

    
    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    
   
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//   
//    public Boolean getActive() {
//        return active;
//    }
//
//    public void setActive(Boolean active) {
//        this.active = active;
//    }

    public Double getCarton() {
        return carton;
    }

    public void setCarton(Double carton) {
        this.carton = carton;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Date getCrDate() {
        return crDate;
    }

    public void setCrDate(Date crDate) {
        this.crDate = crDate;
    }

    

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Boolean getItemcol() {
        return itemcol;
    }

    public void setItemcol(Boolean itemcol) {
        this.itemcol = itemcol;
    }

   

    public Double getLandCost() {
        return landCost;
    }

    public void setLandCost(Double landCost) {
        this.landCost = landCost;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getManufactItem() {
        return manufactItem;
    }

    public void setManufactItem(Boolean manufactItem) {
        this.manufactItem = manufactItem;
    }

    public Double getMinSalesPrice() {
        return minSalesPrice;
    }

    public void setMinSalesPrice(Double minSalesPrice) {
        this.minSalesPrice = minSalesPrice;
    }

    public Double getMinStock() {
        return minStock;
    }

    public void setMinStock(Double minStock) {
        this.minStock = minStock;
    }

    public Boolean getNonStockItems() {
        return nonStockItems;
    }

    public void setNonStockItems(Boolean nonStockItems) {
        this.nonStockItems = nonStockItems;
    }

    public String getPic1() {
        return pic1;
    }

    public void setPic1(String pic1) {
        this.pic1 = pic1;
    }

    public Double getReOrder() {
        return reOrder;
    }

    public void setReOrder(Double reOrder) {
        this.reOrder = reOrder;
    }

   

    public Double getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(Double salesPrice) {
        this.salesPrice = salesPrice;
    }

    public Boolean getTrackingItem() {
        return trackingItem;
    }

    public void setTrackingItem(Boolean trackingItem) {
        this.trackingItem = trackingItem;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUnitOne() {
        return unitOne;
    }

    public void setUnitOne(String unitOne) {
        this.unitOne = unitOne;
    }

    public String getUnitTwo() {
        return unitTwo;
    }

    public void setUnitTwo(String unitTwo) {
        this.unitTwo = unitTwo;
    }

     

    public Double getWholesalePrice() {
        return wholesalePrice;
    }

    public void setWholesalePrice(Double wholesalePrice) {
        this.wholesalePrice = wholesalePrice;
    }

  
    public Double getCommission() {
        return commission;
    }

    public void setCommission(Double commission) {
        this.commission = commission;
    }

    public Boolean getInactive() {
        return inactive;
    }

    public void setInactive(Boolean inactive) {
        this.inactive = inactive;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public Boolean getTrackExpiry() {
        return trackExpiry;
    }

    public void setTrackExpiry(Boolean trackExpiry) {
        this.trackExpiry = trackExpiry;
    }

    public Boolean getTrackSerial() {
        return trackSerial;
    }

    public void setTrackSerial(Boolean trackSerial) {
        this.trackSerial = trackSerial;
    }

    public Double getUnit2SalesPrice() {
        return unit2SalesPrice;
    }

    public void setUnit2SalesPrice(Double unit2SalesPrice) {
        this.unit2SalesPrice = unit2SalesPrice;
    }

    public List<ExtraSalesPrice> getExtrasalespriceCollection() {
        return extrasalespriceCollection;
    }

    public void setExtrasalespriceCollection(List<ExtraSalesPrice> extrasalespriceCollection) {
        this.extrasalespriceCollection = extrasalespriceCollection;
    }

    public String getMetaInfo() {
        return metaInfo;
    }

    public void setMetaInfo(String metaInfo) {
        this.metaInfo = metaInfo;
    }

    public Double getUnit1SalesPrice() {
        return unit1SalesPrice;
    }

    public void setUnit1SalesPrice(Double unit1SalesPrice) {
        this.unit1SalesPrice = unit1SalesPrice;
    }

    public List<ItemVariation> getVariations() {
        return variations;
    }

    public void setVariations(List<ItemVariation> variations) {
        this.variations = variations;
    }

    /**
     * @return the discount
     */
    public Double getDiscount() {
        return discount;
    }

    /**
     * @param discount the discount to set
     */
    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getCommissionValue() {
        return commissionValue;
    }

    public void setCommissionValue(Double commissionValue) {
        this.commissionValue = commissionValue;
    }

    public Double getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(Double discountValue) {
        this.discountValue = discountValue;
    }

    public List<ItemBarcode> getBarcodes() {
        return barcodes;
    }

    public void setBarcodes(List<ItemBarcode> barcodes) {
        this.barcodes = barcodes;
    }

    /**
     * @return the different
     */
    public Integer getDifferent() {
        return different;
    }

    /**
     * @param different the different to set
     */
    public void setDifferent(Integer different) {
        this.different = different;
    }
 
    
    
}
