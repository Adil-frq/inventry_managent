package com.invertory.mapper;

import com.invertory.dto.ProductRequest;
import com.invertory.entity.Product;

public interface ProductMapper {

    public  static <T> Product toEntity(T t){
        Product product = new Product();
        if(t instanceof ProductRequest productRequest){

            product.setProductName(productRequest.productName());
            product.setBrand(productRequest.brand());
            product.setSkuCode(productRequest.skuCode());
        }

        return product;
    }
}
