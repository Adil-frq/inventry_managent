package com.invertory;

import com.invertory.repository.SupplierRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
class ImsApplicationTests {

   @Test


    void testDatabaseMethod() throws InterruptedException {
       Thread.sleep(30000);
   }

}
