package com.upc.modelhouse.SubscriptionAndPayment.resource.Cart;

import com.upc.modelhouse.SubscriptionAndPayment.domain.model.entity.Product;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CartItemDto {
    private @NotNull Integer quantity;
    private @NotNull Product product;
    public CartItemDto() {
    }
    public CartItemDto(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
    @Override
    public String toString() {
        return "CartDto{" +
                ", quantity=" + quantity +
                ", productName=" + product.getName() +
                '}';
    }
}
