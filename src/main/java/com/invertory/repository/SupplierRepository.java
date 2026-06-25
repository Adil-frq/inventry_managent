package com.invertory.repository;

import com.invertory.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

public interface SupplierRepository extends JpaRepository<Supplier,String> {

    @Query(nativeQuery = true , value = "select supplier_id from supplier order by supplier_id desc limit 1")
    public String findLastCreatedId();

    boolean existsByTrn(String trn);

    boolean existsByName(String supplierName);

    @Query(nativeQuery = true , value = "select * from supplier where name = :supplierName")
    Supplier findByName(@Param("supplierName") String name);

}
