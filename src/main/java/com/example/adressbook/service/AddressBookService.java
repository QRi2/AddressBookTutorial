package com.example.adressbook.service;

import com.example.adressbook.model.Adressdaten;
import com.example.adressbook.model.Personendaten;
import com.example.adressbook.repository.AddressBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressBookService implements IAddressBookService {

    AdressBookLogger log = new AdressBookLogger();

    private final AddressBookRepository repository;

    @Autowired
    public AddressBookService(AddressBookRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Personendaten> findAll() {
        log.logShowInfo("Suche nach allen Personendaten");
        return repository.findAll();
    }

    @Override
    public Personendaten findById(long id) {
        log.logShowInfo("Suche nach Personendaten anhand der ID: " + id);
        return repository.findById(id).get();
    }

    @Override
    public List<Personendaten> findByName(String name) {

//        List<Personendaten> personenDatensatz = repository.findAll();
//        List<Personendaten> datensaetzeMitGesuchtemNamen = new ArrayList<>();
//        log.logShowInfo("Suche nach Personendaten anhand des Namen: " + name);
//        for (Personendaten personendaten : personenDatensatz) {
//            if (personendaten.getName().equals(name)) {
//                datensaetzeMitGesuchtemNamen.add(personendaten);
//                log.logShowInfo("Treffer bei " + personendaten.getName() + " " + personendaten.getVorname());
//            }
//        }
        return repository.findByName(name);
    }

    @Override
    public List<Personendaten> findByVorname(String vorname) {
        List<Personendaten> personenDatensatz = repository.findAll();
        List<Personendaten> datensaetzeMitGesuchtemVornamen = new ArrayList<>();
        log.logShowInfo("Suche nach Personendaten anhand des Vornamen: " + vorname);
        for (Personendaten personendaten : personenDatensatz) {
            if (personendaten.getVorname().equals(vorname)) {
                datensaetzeMitGesuchtemVornamen.add(personendaten);
                log.logShowInfo("Treffer bei " + personendaten.getVorname() + " " + personendaten.getName());
            }
        }
        return datensaetzeMitGesuchtemVornamen;
    }

    @Override
    public List<Personendaten> findByTelefonnummer(String telefonnummer) {
        List<Personendaten> personenDatensatz = repository.findAll();
        List<Personendaten> datensaetzeMitGesuchterTelefonnummer = new ArrayList<>();
        log.logShowInfo("Suche nach Personendaten anhand der Telefonnummer: " + telefonnummer);
        for (Personendaten personendaten : personenDatensatz) {
            if (personendaten.getTelefonnummer() != null &&
                    personendaten.getTelefonnummer().equals(telefonnummer)) {
                datensaetzeMitGesuchterTelefonnummer.add(personendaten);
                log.logShowInfo("Treffer bei " + personendaten.getVorname() + " " + personendaten.getName());
            }
        }
        return datensaetzeMitGesuchterTelefonnummer;
    }

    @Override
    public List<Personendaten> findByMail(String mail) {
        List<Personendaten> personenDatensatz = repository.findAll();
        List<Personendaten> datensaetzeMitGesuchterMail = new ArrayList<>();
        log.logShowInfo("Suche nach Personendaten anhand der E-Mail: " + mail);
        for (Personendaten personendaten : personenDatensatz) {
            if (personendaten.getMail().equals(mail)) {
                datensaetzeMitGesuchterMail.add(personendaten);
                log.logShowInfo("Treffer bei " + personendaten.getVorname() + " " + personendaten.getName());
            }
        }
        return datensaetzeMitGesuchterMail;
    }

    @Override
    public List<Personendaten> findByStrasse(String strasse) {
        List<Personendaten> personenDatensatz = repository.findAll();
        List<Personendaten> datensaetzeMitGesuchterStrasse = new ArrayList<>();
        log.logShowInfo("Suche nach Personendaten anhand der Straße: " + strasse);
        for (Personendaten personendaten : personenDatensatz) {
            if (personendaten.getAdressdaten() != null &&
                    personendaten.getAdressdaten().getStrasse() != null &&
                    personendaten.getAdressdaten().getStrasse().equals(strasse)) {
                datensaetzeMitGesuchterStrasse.add(personendaten);
                log.logShowInfo("Treffer bei " + personendaten.getVorname() + " " + personendaten.getName());
            }
        }
        return datensaetzeMitGesuchterStrasse;
    }

    @Override
    public List<Personendaten> findByPostleitzahl(long postleitzahl) {
        List<Personendaten> personenDatensatz = repository.findAll();
        List<Personendaten> datensaetzeMitGesuchterPostleitzahl = new ArrayList<>();
        log.logShowInfo("Suche nach Personendaten anhand der Postleitzahl: " + postleitzahl);
        for (Personendaten personendaten : personenDatensatz) {
            if (personendaten.getAdressdaten() != null &&
                    personendaten.getAdressdaten().getPostleitzahl() != 0 &&
                    personendaten.getAdressdaten().getPostleitzahl() == postleitzahl) {
                datensaetzeMitGesuchterPostleitzahl.add(personendaten);
                log.logShowInfo("Treffer bei " + personendaten.getVorname() + " " + personendaten.getName());
            }
        }
        return datensaetzeMitGesuchterPostleitzahl;
    }

    @Override
    public List<Personendaten> findByStadt(String stadt) {
        List<Personendaten> personenDatensatz = repository.findAll();
        List<Personendaten> datensaetzeMitGesuchterStadt = new ArrayList<>();
        log.logShowInfo("Suche nach Personendaten anhand der Stadt: " + stadt);
        for (Personendaten personendaten : personenDatensatz) {
            if (personendaten.getAdressdaten() != null &&
                    personendaten.getAdressdaten().getStadt() != null &&
                    personendaten.getAdressdaten().getStadt().equals(stadt)) {
                datensaetzeMitGesuchterStadt.add(personendaten);
                log.logShowInfo("Treffer bei " + personendaten.getVorname() + " " + personendaten.getName());
            }
        }
        return datensaetzeMitGesuchterStadt;
    }

    @Override
    public List<Personendaten> findByLand(String land) {
        List<Personendaten> personenDatensatz = repository.findAll();
        List<Personendaten> datensaetzeMitGesuchtemLand = new ArrayList<>();
        log.logShowInfo("Suche nach Personendaten anhand des Landes: " + land);
        for (Personendaten personendaten : personenDatensatz) {
            if (personendaten.getAdressdaten() != null &&
                    personendaten.getAdressdaten().getLand() != null &&
                    personendaten.getAdressdaten().getLand().equals(land)) {
                datensaetzeMitGesuchtemLand.add(personendaten);
                log.logShowInfo("Treffer bei " + personendaten.getVorname() + " " + personendaten.getName());
            }
        }
        return datensaetzeMitGesuchtemLand;
    }

    @Override
    public void addPersonendaten(Personendaten personendaten) {
        log.logShowInfo("Neuer Eintrag für " + personendaten.getVorname() + " " + personendaten.getName() + " " + "erstellt");
        repository.save(personendaten);
    }

    @Override
    public void deletePersonendaten(long id) {
        log.logShowInfo("Eintrag mit der ID: " + id + " wird gelöscht");
        repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        log.logShowInfo("Alle Informationen werden gelöscht");
        repository.deleteAll();
    }

    @Override
    public Personendaten updatePersonendaten(long id, Personendaten aktualisiertePersonenDaten) {
        Personendaten zuAktualisierenderDatensatz = repository.findById(id).get();
        if (zuAktualisierenderDatensatz != null) {
            if (!zuAktualisierenderDatensatz.getName().equals(aktualisiertePersonenDaten.getName())) {
                log.logChangeInfo("Name", zuAktualisierenderDatensatz.getName(), aktualisiertePersonenDaten.getName());
                zuAktualisierenderDatensatz.setName(aktualisiertePersonenDaten.getName());
            }
            if (!zuAktualisierenderDatensatz.getVorname().equals(aktualisiertePersonenDaten.getVorname())) {
                log.logChangeInfo("Vorname", zuAktualisierenderDatensatz.getVorname(), aktualisiertePersonenDaten.getVorname());
                zuAktualisierenderDatensatz.setVorname(aktualisiertePersonenDaten.getVorname());
            }
            if (!zuAktualisierenderDatensatz.getMail().equals(aktualisiertePersonenDaten.getMail())) {
                log.logChangeInfo("E-Mail", zuAktualisierenderDatensatz.getMail(), aktualisiertePersonenDaten.getMail());
                zuAktualisierenderDatensatz.setMail(aktualisiertePersonenDaten.getMail());
            }
            if (zuAktualisierenderDatensatz.getTelefonnummer() == null && aktualisiertePersonenDaten.getTelefonnummer() != null ||
                    !zuAktualisierenderDatensatz.getTelefonnummer().equals(aktualisiertePersonenDaten.getTelefonnummer())) {
                log.logChangeInfo("Telefonnummer", zuAktualisierenderDatensatz.getTelefonnummer(), aktualisiertePersonenDaten.getTelefonnummer());
                zuAktualisierenderDatensatz.setTelefonnummer(aktualisiertePersonenDaten.getTelefonnummer());
            }
            if (aktualisiertePersonenDaten.getAdressdaten() != null) {
                if (zuAktualisierenderDatensatz.getAdressdaten() == null) {
                    Adressdaten adresse = new Adressdaten();
                    if (aktualisiertePersonenDaten.getAdressdaten().getStrasse() != null) {
                        log.logNewAdressData("Straße", aktualisiertePersonenDaten.getAdressdaten().getStrasse());
                        adresse.setStrasse(aktualisiertePersonenDaten.getAdressdaten().getStrasse());
                    }
                    if (aktualisiertePersonenDaten.getAdressdaten().getPostleitzahl() != 0) {
                        log.logNewAdressData("Postleitzahl", aktualisiertePersonenDaten.getAdressdaten().getPostleitzahl() + "");
                        adresse.setPostleitzahl(aktualisiertePersonenDaten.getAdressdaten().getPostleitzahl());
                    }
                    if (aktualisiertePersonenDaten.getAdressdaten().getStadt() != null) {
                        log.logNewAdressData("Stadt", aktualisiertePersonenDaten.getAdressdaten().getStadt());
                        adresse.setStadt(aktualisiertePersonenDaten.getAdressdaten().getStadt());
                    }
                    if (aktualisiertePersonenDaten.getAdressdaten().getLand() != null) {
                        log.logNewAdressData("Land", aktualisiertePersonenDaten.getAdressdaten().getLand());
                        adresse.setLand(aktualisiertePersonenDaten.getAdressdaten().getLand());
                    }
                    zuAktualisierenderDatensatz.setAdressdaten(adresse);
                } else if (!zuAktualisierenderDatensatz.getAdressdaten().equals(aktualisiertePersonenDaten.getAdressdaten())) {
                    if (!zuAktualisierenderDatensatz.getAdressdaten().getStrasse().equals(aktualisiertePersonenDaten.getAdressdaten().getStrasse())) {
                        zuAktualisierenderDatensatz.getAdressdaten().setStrasse(aktualisiertePersonenDaten.getAdressdaten().getStrasse());
                        log.logChangeInfo("Straße", zuAktualisierenderDatensatz.getAdressdaten().getStrasse(), aktualisiertePersonenDaten.getAdressdaten().getStrasse());
                    }
                    if (zuAktualisierenderDatensatz.getAdressdaten().getPostleitzahl() != aktualisiertePersonenDaten.getAdressdaten().getPostleitzahl()) {
                        zuAktualisierenderDatensatz.getAdressdaten().setPostleitzahl(aktualisiertePersonenDaten.getAdressdaten().getPostleitzahl());
                        log.logChangeInfo("Postleitzahl", zuAktualisierenderDatensatz.getAdressdaten().getPostleitzahl() + "", aktualisiertePersonenDaten.getAdressdaten().getPostleitzahl() + "");
                    }
                    if (!zuAktualisierenderDatensatz.getAdressdaten().getStadt().equals(aktualisiertePersonenDaten.getAdressdaten().getStadt())) {
                        zuAktualisierenderDatensatz.getAdressdaten().setStadt(aktualisiertePersonenDaten.getAdressdaten().getStadt());
                        log.logChangeInfo("Stadt", zuAktualisierenderDatensatz.getAdressdaten().getStadt(), aktualisiertePersonenDaten.getAdressdaten().getStadt());
                    }
                    if (!zuAktualisierenderDatensatz.getAdressdaten().getLand().equals(aktualisiertePersonenDaten.getAdressdaten().getLand())) {
                        zuAktualisierenderDatensatz.getAdressdaten().setLand(aktualisiertePersonenDaten.getAdressdaten().getLand());
                        log.logChangeInfo("Land", zuAktualisierenderDatensatz.getAdressdaten().getLand(), aktualisiertePersonenDaten.getAdressdaten().getLand());
                    }
                }
            }else{
                zuAktualisierenderDatensatz.setAdressdaten(null);
            }
            repository.save(zuAktualisierenderDatensatz);
            return zuAktualisierenderDatensatz;
        }
        return null;
    }
}
