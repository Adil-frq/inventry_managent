package com.invertory.service;

import com.invertory.dto.SupplierRequest;
import com.invertory.dto.SupplierResponse;
import com.invertory.entity.Contact;
import com.invertory.entity.Supplier;
import com.invertory.exception.SupplierContactMissingException;
import com.invertory.exception.SupplierAlreadyExistsException;
import com.invertory.mapper.ContactMapper;
import com.invertory.mapper.SupplierMapper;
import com.invertory.repository.ContactRepository;
import com.invertory.repository.SupplierRepository;
import com.invertory.util.IdGenerator;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Transactional
public class SupplierService {

    private static final Logger logger = LoggerFactory.getLogger(SupplierService.class);

    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    ContactRepository contactRepository;

    public SupplierResponse saveSupplier(SupplierRequest request){
        logger.info("saveSupplier -> service");

        boolean isSupplierTrnExists = supplierRepository.existsByTrn(request.trn());
        boolean isSupplierExists = supplierRepository.existsByName(request.name());
        var error = isSupplierExists? request.name() : isSupplierTrnExists ? request.trn() : null;

        if(error != null) {
            throw new SupplierAlreadyExistsException("Supplier already exists for this TRN or Name :: " + error);
        }

        String lastCreatedId = supplierRepository.findLastCreatedId();
        //check the supplier already exists with trn number then throw exception.

        Supplier supplier = SupplierMapper.toEntity(request);

        if(lastCreatedId == null){
            supplier.setSupplierId("SUP-00001");
        } else{

            String format = IdGenerator.generateId(lastCreatedId);
            supplier.setSupplierId("SUP-" + format);

        }
        logger.info("supplier id {} ", supplier.getSupplierId());

        Supplier savedSupplier = supplierRepository.save(supplier);

        //after saving the supplier save the contact in contact table
        //get last saved contact

        if(request.contactRequest() == null){
            throw new SupplierContactMissingException("Contact not available for this supplier");
        }
        String lastContactId = contactRepository.findLastCreatedContactId();

        Contact contact = ContactMapper.toEntity(request);

        //contact.setSupplier(savedSupplier);
        if(lastContactId == null){
            contact.setContactId("CON-00001");
        } else {
            lastContactId = IdGenerator.generateId(lastContactId);
            contact.setContactId("CON-" + lastContactId);

        }
        Contact savedContact = contactRepository.save(contact);

        //UPDATE THE SUPPLIER FOR CONTACT ID

        savedSupplier.setContactId(savedContact);
        savedSupplier = supplierRepository.save(savedSupplier);

        //convert this saved Supplier into SupplierResponse
        return SupplierMapper.toDTO(savedSupplier);

    }

}
