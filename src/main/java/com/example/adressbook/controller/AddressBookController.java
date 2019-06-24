package com.example.adressbook.controller;

import com.example.adressbook.model.Adressdaten;
import com.example.adressbook.model.Personendaten;
import com.example.adressbook.service.AddressBookService;
import com.example.adressbook.service.AdressBookLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/addressbook", produces = MediaType.APPLICATION_JSON_VALUE)
public class AddressBookController {

    //    String[] hellos = {"GutenTag", "Hello", "Hellas", "Bonjour"};


//    @GetMapping("/getHelloWorld")
//    public ResponseEntity getHelloWorld() {
//        return ResponseEntity.ok("HelloWorld");
//    }
//
//    @GetMapping("/getHelloWorld/{id}")
//    public ResponseEntity getHelloWorldByIndex(@PathVariable int id) {
//        if (id >= 0 && id < 4) {
//            return ResponseEntity.ok(hellos[id]);
//        }
//        return ResponseEntity.notFound().build();
//    }
//
//    @PatchMapping("/patchArray/{id}/{begruessung}")
//    public ResponseEntity patchArray(@PathVariable int id, @PathVariable String begruessung) {
//        if (id >= 0 && id < 4) {
//            String vorher = hellos[id];
//            hellos[id] = begruessung;
//            return ResponseEntity
//                    .ok("Vorherige Begrüßung war " + vorher + ". Wurde jetzt aktualisiert auf " + hellos[id]);
//        }
//        return ResponseEntity.badRequest().build();
//    }

    private static long generatedID;

    @Autowired
    AddressBookService addressBookService;

//    @Autowired
//    public AddressBookController() {
////        this.generatedID = 0;
//
////        this.addressBookService = new AddressBookService();
//        this.addressBookService.addPersonendaten(new Personendaten("Glania", "Christopher", "meineMAil@mail.de", "0231-1245478", new Adressdaten("Ahornweg 1", 12345, "Potsdam", "Germany")));
//        this.addressBookService.addPersonendaten(new Personendaten("Blub", "Jens", "deineMAil@mail.de", "1253-124578", new Adressdaten("B-hornweg 1", 12265, "Stadtname", "Germany")));
//        this.addressBookService.addPersonendaten(new Personendaten("Müller", "Fabian", "fabi.mueller@gmx.de", "0152-5365444", null));
//    }


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
    public ResponseEntity<Personendaten> getAddresses(@PathVariable long id) {
        return ResponseEntity.ok(this.addressBookService.findById(id));
    }

    //4.	Ich möchte alle Addressdaten einsehen.
    @GetMapping(value = "/addresses")
    public ResponseEntity<List<Personendaten>> getAddresses() {
        return ResponseEntity.ok(this.addressBookService.findAll());
    }

//    5.	Ich möchte alle Datensätze einsehen, die für einen von mir angegebenen Wert, für eine der unten angegebenen Datenspalten haben. (Filtern)
//    Hinweis: Das Abholen eines gefilterten Datensatzes wird in REST-Vorzugsweise über Query-Parameter gesteuert. Beispiel: "GET /cars?color=turquoise&brand=vw".
//    @GetMapping("/address")
//    public ResponseEntity<Personendaten> getAddressByQueryParameter(@Nullable @RequestParam long id,
//                                                                   @Nullable @RequestParam String name,
//                                                                   @Nullable @RequestParam String vorname,
//                                                                   @Nullable @RequestParam String mail,
//                                                                   @Nullable @RequestParam String telefonnummer,
//                                                                   @Nullable @RequestParam String strasse,
//                                                                   @Nullable @RequestParam String stadt,
//                                                                   @Nullable @RequestParam String postleitzahl,
//                                                                   @Nullable @RequestParam String land){
//
//    }

    //  6.	Ich möchte einen bestehenden Datensatz aktualisieren.
    @PutMapping(value = "/addresses/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Personendaten> updateAddress(@PathVariable long id,
                                                       @Valid @RequestBody Personendaten aktualisiertePersonenDaten) {
        Personendaten neuerDatensatz = this.addressBookService.updatePersonendaten(id, aktualisiertePersonenDaten);
        if (neuerDatensatz != null) {
            return ResponseEntity.ok(neuerDatensatz);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    //  7.	Ich möchte einen bestehenden Datensatz löschen.
    @DeleteMapping(value = "/addresses/{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable long id) {
        this.addressBookService.deletePersonendaten(id);
        return ResponseEntity.ok("Einrag mit der ID " + id + " wurde gelöscht");
    }

    //  8.	Ich möchte alle bestehenden Datensätze löschen.
    @DeleteMapping(value = "/addresses")
    public ResponseEntity<String> deleteAllAddresses() {
        this.addressBookService.deleteAll();
        return ResponseEntity.ok("Alle Personendaten wurden gelöscht");
    }
}
