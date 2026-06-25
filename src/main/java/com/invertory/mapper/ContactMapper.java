package com.invertory.mapper;

import com.invertory.dto.ContactRequest;
import com.invertory.dto.ContactResponse;
import com.invertory.dto.SupplierRequest;
import com.invertory.entity.Contact;

public interface ContactMapper {
    public static <T> Contact toEntity(T t){
          Contact contact = new Contact();
         if(t instanceof SupplierRequest request){
             //SupplierRequest request = (SupplierRequest) t;
             ContactRequest contactRequest = request.contactRequest();
             contact.setCity(contactRequest.city());
             contact.setCountry(contactRequest.country());
             contact.setStreet(contactRequest.street());
             contact.setPin(contactRequest.pin());
             contact.setEmail(contactRequest.email());
             contact.setContactNo(contactRequest.contactNo());

         }
         return contact;
    }

    static ContactResponse toDto(Contact contact){
        return new ContactResponse(
                contact.getStreet(),
                contact.getCity(),
                contact.getCountry(),
                contact.getPin(),
                contact.getEmail(),
                contact.getContactNo()

                );
    }
}
