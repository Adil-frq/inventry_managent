package com.invertory.service;

import com.invertory.dto.ContactRequest;
import com.invertory.dto.SupplierRequest;
import com.invertory.dto.SupplierResponse;
import com.invertory.entity.Contact;
import com.invertory.entity.Supplier;
import com.invertory.exception.SupplierAlreadyExistsException;
import com.invertory.exception.SupplierContactMissingException;
import com.invertory.mapper.ContactMapper;
import com.invertory.mapper.SupplierMapper;
import com.invertory.repository.ContactRepository;
import com.invertory.repository.SupplierRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("SupplierServiceTest")
public class SupplierServiceTest {
    @Mock
    private SupplierRepository supplierRepository;
    @Mock
    private ContactRepository contactRepository;

    @InjectMocks
    SupplierService supplierService;

    private SupplierRequest supplierRequest;

    @BeforeEach
    void setup(){
        this.supplierRequest = new SupplierRequest("Global Trading Co", null, null);
    }

    @Test
    @DisplayName("Check Supplier name already exists then throw SupplierAlreadyExistsException")
    public void testSupplierAlreadyExistsException(){
        when(supplierRepository.existsByName(this.supplierRequest.name())).thenReturn(true);

        SupplierAlreadyExistsException exception = assertThrows(SupplierAlreadyExistsException.class, ()->supplierService.saveSupplier(this.supplierRequest));

        String expectedMessage = "Supplier already exists for this TRN or Name :: "+this.supplierRequest.name();

        assertEquals(expectedMessage, exception.getMessage());
    }
    //@Test

    /*@DisplayName("Check if the contact is null the throw the ContactRequiredException()")
    public void testContactRequiredException(){

        SupplierContactMissingException exception = assertThrows(
                SupplierContactMissingException.class,
                () -> supplierService.saveSupplier(this.supplierRequest)

        );

        String expectedMessage = "Contact not available for this supplier";

        assertEquals(expectedMessage, exception.getMessage());

    }
    @Test
    public void saveSupplier(){
        ContactRequest contactRequest = new ContactRequest("Daira","Dubai", "UAE",
                "123456","alex.doe@example.com", "+971544707044");
        SupplierRequest supplier = new SupplierRequest("ABC LLC", "1001234567",contactRequest);
        Supplier supplierEntity = SupplierMapper.toEntity(supplier);
        Contact contactEntity = ContactMapper.toEntity(contactRequest);
        supplierEntity.setSupplierId("CON-00001");
        //given
        when(supplierRepository.existsByTrn(supplier.trn())).thenReturn(false);
        when(supplierRepository.existsByName(supplier.name())).thenReturn(false);
        when(supplierRepository.findLastCreatedId()).thenReturn(null);
        when(SupplierMapper.toEntity(supplier)).thenReturn(supplierEntity);
        //when(contactRepository.save(ContactMapper.toEntity(contactRequest))).thenReturn(ContactMapper.toEntity(contactRequest));
         when(supplierRepository.save(any(Supplier.class))).thenReturn(supplierEntity);
        //when(SupplierMapper.toDTO(supplierEntity)).thenReturn(SupplierMapper.toDTO(supplierEntity));
        when(contactRepository.save(any(Contact.class))).thenReturn(contactEntity);
       // when(ContactMapper.toEntity(ContactMapper.toEntity(contactRequest))).thenReturn(contactEntity);
        SupplierResponse response = supplierService.saveSupplier(supplier);

        assertNotNull(response);

        // Check that our mock database invocations happened exactly as intended
        verify(supplierRepository, times(2)).save(any(Supplier.class)); // Saved initially, then updated with contact
        verify(contactRepository, times(1)).save(any(Contact.class));

    }*/

    @Test
    public void saveSupplier_Success() {
        // 1. Arrange
        ContactRequest contactRequest = new ContactRequest("Daira","Dubai", "UAE", "123456","alex.doe@example.com", "+971544707044");
        SupplierRequest supplier = new SupplierRequest("ABC LLC", "1001234567", contactRequest);

        Supplier supplierEntity = SupplierMapper.toEntity(supplier);
        Contact contactEntity = ContactMapper.toEntity(contactRequest);

        // Fix prefixes to match what your service actually expects
        supplierEntity.setSupplierId("SUP-00001");
        contactEntity.setContactId("CON-00001");

        // 2. Given (Stubs)
        when(supplierRepository.existsByTrn(supplier.trn())).thenReturn(false);
        when(supplierRepository.existsByName(supplier.name())).thenReturn(false);
        when(supplierRepository.findLastCreatedId()).thenReturn(null);

        // CRITICAL: You must stub this call because your service uses it right after saving the supplier!
        when(contactRepository.findLastCreatedContactId()).thenReturn(null);

        // Use generic matchers to return our fake DB entities
        when(supplierRepository.save(any(Supplier.class))).thenReturn(supplierEntity);
        when(contactRepository.save(any(Contact.class))).thenReturn(contactEntity);

        // 3. Act
        SupplierResponse response = supplierService.saveSupplier(supplier);

        // 4. Assert
        assertNotNull(response);
        verify(supplierRepository, times(2)).save(any(Supplier.class));
        verify(contactRepository, times(1)).save(any(Contact.class));
    }
}
