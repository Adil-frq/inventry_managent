package com.invertory.repository;

import com.invertory.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    @Query(nativeQuery = true, value = "select product_id from product order by product_id desc limit 1")
    public String findLastInsertedProductId();

    @Query(nativeQuery = true, value="select product_id from product_supplier where supplier_id = :supplier_id")
    public List<String> findProductIdBySupplierId(@Param("supplier_id") String supplier_id);


    boolean existsByProductIdAndSkuCode(String s, String sku_code);
}

