package com.invertory.repository;

import com.invertory.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ContactRepository extends JpaRepository<Contact,String> {
    @Query(nativeQuery = true, value = "select contact_id from contact order by contact_id desc limit 1")
    public String findLastCreatedContactId();
}
