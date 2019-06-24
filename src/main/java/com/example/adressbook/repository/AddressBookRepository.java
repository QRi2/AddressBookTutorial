package com.example.adressbook.repository;

import com.example.adressbook.model.Personendaten;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressBookRepository extends JpaRepository<Personendaten, Long> {

    List<Personendaten> findByName(String name);
}
