package com.invertory.service;

import com.invertory.dto.InventoryMapper;
import com.invertory.dto.ProductRequest;
import com.invertory.entity.Inventory;
import com.invertory.entity.Product;
import com.invertory.entity.Supplier;
import com.invertory.exception.SupplierNotFoundException;
import com.invertory.mapper.ProductMapper;
import com.invertory.repository.InventoryRepository;
import com.invertory.repository.ProductRepository;
import com.invertory.repository.SupplierRepository;
import com.invertory.util.IdGenerator;
import jakarta.transaction.Transactional;
import lombok.extern.flogger.Flogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    InventoryRepository inventoryRepository;
    @Autowired
    InventoryService inventoryService;

    @Transactional
    public void saveProduct(ProductRequest request){
         //check the supplier exists for this product or not
        String trn = null;
        String name = null;
        String prodId = null;

        boolean isSupplierExists = false;
        boolean isProductExist = false;
        Product savedProduct = null;

        if(request.supplierRequest() != null) {
            trn = request.supplierRequest().trn();
            name = request.supplierRequest().name();
        }

        if(trn != null) {
            isSupplierExists = supplierRepository.existsByTrn(trn);
        } else if(name != null) {
           isSupplierExists =  supplierRepository.existsByName(name);
        }
        if(!isSupplierExists) {
            throw new SupplierNotFoundException("Supplier not found of given name "+ name);
        }
        //find supplier id for the given supplier
        Supplier supplier = supplierRepository.findByName(name);

        if(supplier != null){
            String supplierId = supplier.getSupplierId();
            //find product_id of this supplier id
            List<String> productIdBySupplierId = productRepository.findProductIdBySupplierId(supplierId);


             prodId = productIdBySupplierId.stream()
                    .filter(productId ->
                            productRepository.existsByProductIdAndSkuCode(productId, request.skuCode()))
                    .findFirst()
                    .orElse(null);


        }
        Product product = ProductMapper.toEntity(request);
        //save the product if product is not exists

        if(prodId == null) {
            String productId = productRepository.findLastInsertedProductId();

            if(productId == null)
                product.setProductId("PROD-00001");
            else {
                productId =  IdGenerator.generateId(productId);
                product.setProductId("PROD-"+productId);
            }
            product.setSupplier(Arrays.asList(supplier));

            savedProduct = productRepository.save(product);

        }
        //if(savedProduct != null && supplier != null)
            inventoryService.saveInventory(request, savedProduct, supplier);
        
        log.info("product saved successfully {}" , product.getProductId());
    }
}
