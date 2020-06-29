package com.example.wkl_android.main.shopping_cart.bean;

public class EditShopCarNumber {
  private long carId;
  private long skuId;
  private long skuCount;

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public long getSkuId() {
        return skuId;
    }

    public void setSkuId(long skuId) {
        this.skuId = skuId;
    }

    public long getSkuCount() {
        return skuCount;
    }

    public void setSkuCount(long skuCount) {
        this.skuCount = skuCount;
    }
}
