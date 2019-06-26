package com.example.adressbook.controller;

import com.example.adressbook.model.Personendaten;
import com.example.adressbook.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wolfe.query.QueryParamFilter;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/addressbook", produces = MediaType.APPLICATION_JSON_VALUE)
public class AddressBookController {

    @Autowired
    AddressBookService addressBookService;

    @Autowired
    public AddressBookController(AddressBookService addressBookService) {
        this.addressBookService = addressBookService;
    }

    //1.	Ich möchte einen neuen Addressdatensatz hinzufügen.
    @PostMapping(value = "/address", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Personendaten> createAddressData(@RequestBody Personendaten neuerEintrag) {
        Personendaten neueAddressDaten = new Personendaten(neuerEintrag.getName(), neuerEintrag.getVorname(), neuerEintrag.getMail(), neuerEintrag.getTelefonnummer(), neuerEintrag.getAdressdaten());
        this.addressBookService.addPersonendaten(neueAddressDaten);
        return ResponseEntity.ok(neueAddressDaten);
    }

    //2.	Ich möchte mehrere Datensätze auf einmal hinzufügen. (oder auch nur einen :D)
    @PostMapping(value = "/addresses", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Personendaten>> createAddressData(@RequestBody List<Personendaten> neueEintraege) {
        List<Personendaten> lokaleListeFuerResponse = new ArrayList<>();
        for (Personendaten neuerEintrag : neueEintraege) {
            Personendaten neueAddressDaten = new Personendaten(neuerEintrag.getName(), neuerEintrag.getVorname(), neuerEintrag.getMail(), neuerEintrag.getTelefonnummer(), neuerEintrag.getAdressdaten());
            this.addressBookService.addPersonendaten(neueAddressDaten);
            lokaleListeFuerResponse.add(neueAddressDaten);
        }
        return ResponseEntity.ok(lokaleListeFuerResponse);
    }

    //3.	Ich möchte einen spezifischen Addressdatensatz einsehen.
    @GetMapping(value = "/addresses/{id}")
    public ResponseEntity<Personendaten> getAddress(@PathVariable long id) {
        return ResponseEntity.ok(this.addressBookService.findById(id));
    }

    //4.	Ich möchte alle Addressdaten einsehen.
    @GetMapping(value = "/addresses")
    public ResponseEntity<List<Personendaten>> getAddress() {
        return ResponseEntity.ok(this.addressBookService.findAll());
    }

    //    5.	Ich möchte alle Datensätze einsehen, die für einen von mir angegebenen Wert, für eine der unten angegebenen Datenspalten haben. (Filtern)
    //          Hinweis: Das Abholen eines gefilterten Datensatzes wird in REST-Vorzugsweise über Query-Parameter gesteuert. Beispiel: "GET /cars?color=turquoise&brand=vw".
    @GetMapping("/address")
    public ResponseEntity<List<Personendaten>> filterByQueryParameter(@QueryParamFilter QueryFilterRequestModel filters) {
        return ResponseEntity.ok(this.addressBookService.filter(filters));
    }

    //  6.	Ich möchte einen bestehenden Datensatz aktualisieren.
    @PutMapping(value = "/addresses/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Personendaten> updateAddress(@PathVariable long id,
                                                       @Valid @RequestBody Personendaten aktualisiertePersonenDaten) {
        Personendaten neuerDatensatz = this.addressBookService.updatePersonendaten(id, aktualisiertePersonenDaten);
        if (neuerDatensatz != null) {
            return ResponseEntity.ok(neuerDatensatz);
        }
        return ResponseEntity.badRequest().build();

    }

    //  7.	Ich möchte einen bestehenden Datensatz löschen.
    @DeleteMapping(value = "/addresses/{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable long id) {
        if (this.addressBookService.deletePersonendaten(id)) {
            return ResponseEntity.ok("Einrag mit der ID " + id + " wurde gelöscht");
        }
        return ResponseEntity.badRequest().build();
    }

    //  8.	Ich möchte alle bestehenden Datensätze löschen.
    @DeleteMapping(value = "/addresses")
    public ResponseEntity<String> deleteAllAddresses() {
        this.addressBookService.deleteAll();
        return ResponseEntity.ok("Alle Personendaten wurden gelöscht");
    }
}
