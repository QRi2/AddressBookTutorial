package com.example.adressbook.test;


import com.example.adressbook.controller.AddressBookController;
import com.example.adressbook.controller.QueryFilterRequestModel;
import com.example.adressbook.model.Adressdaten;
import com.example.adressbook.model.Personendaten;
import com.example.adressbook.service.AddressBookService;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AddressbookUnitTest {


    AddressBookService service;
    AddressBookController controller;
    List<Personendaten> personenlisteKomplett;
    List<Personendaten> personenlisteFilter;
    QueryFilterRequestModel filter;

    @Before
    public void init() {

        this.service = mock(AddressBookService.class);
        this.filter = new QueryFilterRequestModel();

        personenlisteKomplett = new ArrayList<>();
        personenlisteFilter = new ArrayList<>();
        Personendaten person1 = new Personendaten("Atherton", "Rachel", "rachel@gmx.co.uk", "0152-355521", null);
        Personendaten person2 = new Personendaten("Atherton", "Gee", "gee@web.co.uk", "0152-352341", new Adressdaten("Mountainview. 44", 12345, "Manchester", "England"));
        Personendaten person3 = new Personendaten("Hill", "Sam", "sam.hill@oulook.au", "0152-365521", new Adressdaten("Forrestway 42", 66624, "Perth", "Australia"));
        Personendaten personUpdated = new Personendaten("Btherton", "Gee", "gee.Btherton@web.co.uk", "0152-352341", new Adressdaten("Mountainview. 44", 12345, "Manchester", "England"));

        personenlisteKomplett.add(person1);
        personenlisteKomplett.add(person2);
        personenlisteKomplett.add(person3);
        personenlisteKomplett.add(personUpdated);

        personenlisteFilter.add(person1);
        personenlisteFilter.add(person2);

        filter.setName("Atherton");

        when(service.filter(filter)).thenReturn(personenlisteFilter);
        when(service.findById(1)).thenReturn((person2));
//        when(service.updatePersonendaten(1, personUpdated)).thenReturn(personUpdated);
        when(service.findAll()).thenReturn(personenlisteKomplett);


        this.controller = new AddressBookController(this.service);
    }

    @Test
    public void einePersondatenAnlegen() {
        assertEquals(200, controller.createAddressData(personenlisteKomplett.get(0)).getStatusCodeValue());
        assertEquals("Gee", controller.createAddressData(personenlisteKomplett.get(1)).getBody().getVorname());
    }

    @Test
    public void mehrerePersonendatenAnlegen() {
        assertEquals(200, controller.createAddressData(personenlisteKomplett.get(0)).getStatusCodeValue());
        assertEquals(4, controller.createAddressData(personenlisteKomplett).getBody().size());
        assertEquals("Rachel", controller.createAddressData(personenlisteKomplett).getBody().get(0).getVorname());
        assertEquals(null, controller.createAddressData(personenlisteKomplett).getBody().get(0).getAdressdaten());
        assertEquals("Gee", controller.createAddressData(personenlisteKomplett).getBody().get(1).getVorname());
        assertEquals("England", controller.createAddressData(personenlisteKomplett).getBody().get(1).getAdressdaten().getLand());
        assertEquals("Sam", controller.createAddressData(personenlisteKomplett).getBody().get(2).getVorname());
        assertEquals("Australia", controller.createAddressData(personenlisteKomplett).getBody().get(2).getAdressdaten().getLand());
    }

    @Test
    public void findePersonendatenAnhandDerID() {
        assertEquals("Gee", controller.getAddress(1).getBody().getVorname());
    }

    @Test
    public void findeAllePersonendaten(){
        assertEquals(4, controller.getAddress().getBody().size());
        assertEquals(200, controller.getAddress().getStatusCodeValue());
    }

    @Test
    public void findePersonendatenAnhandDesNamens() {
        assertEquals(200, controller.filterByQueryParameter(filter).getStatusCodeValue());
        assertEquals(2, controller.filterByQueryParameter(filter).getBody().size());
        assertEquals("Rachel", controller.filterByQueryParameter((filter)).getBody().get(0).getVorname());
    }

//    @Test
//    public void personendatenAktualisieren() {
//        assertEquals("Btherton", controller.updateAddress(1, personenlisteKomplett.get(3)).getBody().getName());
//        assertEquals("gee.Btherton@web.co.uk", controller.updateAddress(1, personenlisteKomplett.get(3)).getBody().getMail());
//
//    }

//    @Test
//    public void x_loeschePersonendatenByID() {
//        when(service.deletePersonendaten(1)).thenReturn(personenlisteKomplett.remove(personenlisteKomplett.get(0)));
//        controller.deleteAddress(1);
//        assertEquals(3, personenlisteKomplett.size());
//    }

    @Test
    public void xx_loeschePersonendatenKomplett() {
        assertEquals(200, controller.deleteAllAddresses().getStatusCodeValue());
    }


}
