package com.invertory.mapper;

import com.invertory.dto.SupplierRequest;
import com.invertory.dto.SupplierResponse;
import com.invertory.entity.Supplier;

public interface SupplierMapper{
    static <T> Supplier toEntity(T t){
        Supplier supplier = new Supplier();
        if(t instanceof SupplierRequest request){
           //SupplierRequest request = (SupplierRequest) t;

           supplier.setName(request.name());
           supplier.setTrn(request.trn());


        }
        return supplier;
    }

    static SupplierResponse toDTO(Supplier supplier){
        return new SupplierResponse(
                supplier.getSupplierId(),
                supplier.getTrn(),
                supplier.getName(),
                ContactMapper.toDto(supplier.getContactId())
               );
    }
}
