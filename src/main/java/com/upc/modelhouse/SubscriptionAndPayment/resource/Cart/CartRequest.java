package com.upc.modelhouse.SubscriptionAndPayment.resource.Cart;

public class CartRequest {
    private int productId;
    private Long accountId;

    public Long getAccountId(){
        return accountId;
    }
    public void setAccountId(Long accountId){
        this.accountId = accountId;
    }

    public int getProductId(){
        return productId;
    }
    public void setProductId(int productId){
        this.productId = productId;
    }
}
