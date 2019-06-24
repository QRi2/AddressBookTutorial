package com.example.adressbook.service;

import com.example.adressbook.model.Personendaten;

import java.util.List;

public interface IAddressBookService {
    List<Personendaten> findAll();
    Personendaten findById(long id);
    List<Personendaten> findByName(String name);
    List<Personendaten> findByVorname(String vorname);
    List<Personendaten> findByTelefonnummer(String telefonnummer);
    List<Personendaten> findByMail(String mail);
    List<Personendaten> findByStrasse(String strasse);
    List<Personendaten> findByPostleitzahl(long postleitzahl);
    List<Personendaten> findByStadt(String stadt);
    List<Personendaten> findByLand(String land);
    void addPersonendaten(Personendaten personendaten);
    void deletePersonendaten(long id);
    void deleteAll();
    Personendaten updatePersonendaten(long id, Personendaten personendaten);
}
