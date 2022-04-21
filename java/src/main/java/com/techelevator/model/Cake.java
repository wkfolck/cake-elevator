package com.techelevator.model;

import java.math.BigDecimal;

public class Cake {

    private Long cakeId;
    private String flavor;
    private String icing;
    private String size;
    private int numOfTiers;
    private String shape;
    private String filling;
    private boolean isStandard;
    private String imageURL;
    private BigDecimal price;
    private boolean available;

    public Cake() {
    }

    public Cake(Long cakeId, String flavor, String icing, String size, int numOfTiers, String shape, String filling, boolean isStandard, String imageURL, BigDecimal price) {
        this.cakeId = cakeId;
        this.flavor = flavor;
        this.icing = icing;
        this.size = size;
        this.numOfTiers = numOfTiers;
        this.shape = shape;
        this.filling = filling;
        this.isStandard = isStandard;
        this.imageURL = imageURL;
        this.price = price;

    }

    public Long getCakeId() {
        return cakeId;
    }

    public void setCakeId(Long cakeId) {
        this.cakeId = cakeId;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }


    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getNumOfTiers() {
        return numOfTiers;
    }

    public void setNumOfTiers(int numOfTiers) {
        this.numOfTiers = numOfTiers;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public String getFilling() {
        return filling;
    }

    public void setFilling(String filling) {
        this.filling = filling;
    }

    public boolean isStandard() {
        return isStandard;
    }

    public void setStandard(boolean standard) {
        isStandard = standard;
    }

    public String getIcing() {
        return icing;
    }

    public void setIcing(String icing) {
        this.icing = icing;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
