package com.example.adressbook.repository;

import com.example.adressbook.model.Adressdaten;
import com.example.adressbook.model.Personendaten;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonendatenRepository extends JpaRepository<Personendaten, Long> {

    List<Personendaten> findByName(String name);
    List<Personendaten> findByVorname(String vorname);
    List<Personendaten> findByTelefonnummer(String telefonnummer);
    List<Personendaten> findByMail(String mail);
    List<Personendaten> findByAdressdatenStrasse(String strasse);
    List<Personendaten> findByAdressdatenPostleitzahl(long postleitzahl);
    List<Personendaten> findByAdressdatenStadt(String stadt);
    List<Personendaten> findByAdressdatenLand(String land);
}
