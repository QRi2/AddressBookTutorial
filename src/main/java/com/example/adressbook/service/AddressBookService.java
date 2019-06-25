package com.example.adressbook.service;

import com.example.adressbook.controller.QueryFilterRequestModel;
import com.example.adressbook.model.Adressdaten;
import com.example.adressbook.model.Personendaten;
import com.example.adressbook.repository.PersonendatenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressBookService {

    private AdressBookLogger log = new AdressBookLogger();

    private final PersonendatenRepository repository;

    @Autowired
    public AddressBookService(PersonendatenRepository repository) {
        this.repository = repository;
    }


    public List<Personendaten> findAll() {
        log.logShowInfo("Suche nach allen Personendaten");
        return repository.findAll();
    }

    public List<Personendaten> filter(QueryFilterRequestModel filter) {
        List<Personendaten> gefiltertePersonenDaten = findAll();

        if (filter.getId() != 0) {//
            gefiltertePersonenDaten.stream().filter(person -> filter.getId() == person.getId()).findFirst().ifPresent(person -> log.logShowInfo("person rausgenommen: " + person.getVorname() + " +person.getNAchname"));
        }
        if (filter.getName() != null && gefiltertePersonenDaten.size() > 0) {
            gefiltertePersonenDaten = gefiltertePersonenDaten.stream().filter(person -> filter.getName().equals(person.getName())).collect(Collectors.toList());
        }
        if (filter.getVorname() != null && gefiltertePersonenDaten.size() > 0) {
            gefiltertePersonenDaten = gefiltertePersonenDaten.stream().filter(person -> filter.getVorname().equals(person.getVorname())).collect(Collectors.toList());
        }
        if (filter.getMail() != null && gefiltertePersonenDaten.size() > 0) {
            gefiltertePersonenDaten = gefiltertePersonenDaten.stream().filter(person -> filter.getMail().equals(person.getMail())).collect(Collectors.toList());
        }
        if (filter.getTelefonnummer() != null && gefiltertePersonenDaten.size() > 0) {
            gefiltertePersonenDaten = gefiltertePersonenDaten.stream().filter(person -> person.getTelefonnummer() != null &&filter.getTelefonnummer().equals(person.getTelefonnummer())).collect(Collectors.toList());
        }
        if (filter.getStrasse() != null && gefiltertePersonenDaten.size() > 0) {
            gefiltertePersonenDaten = gefiltertePersonenDaten.stream().filter(person -> person.getAdressdaten() != null && person.getAdressdaten().getStrasse() != null && filter.getStrasse().equals(person.getAdressdaten().getStrasse())).collect(Collectors.toList());
        }
        if (filter.getPostleitzahl() != 0 && gefiltertePersonenDaten.size() > 0) {
            gefiltertePersonenDaten = gefiltertePersonenDaten.stream().filter(person -> person.getAdressdaten() != null && filter.getPostleitzahl() == person.getAdressdaten().getPostleitzahl()).collect(Collectors.toList());
        }
        if (filter.getStadt() != null && gefiltertePersonenDaten.size() > 0) {
            gefiltertePersonenDaten = gefiltertePersonenDaten.stream().filter(person -> person.getAdressdaten() != null && person.getAdressdaten().getStadt() != null && filter.getStadt().equals(person.getAdressdaten().getStadt())).collect(Collectors.toList());
        }
        if (filter.getLand() != null && gefiltertePersonenDaten.size() > 0) {
            gefiltertePersonenDaten = gefiltertePersonenDaten.stream().filter(person -> person.getAdressdaten() != null && person.getAdressdaten().getLand() != null && filter.getLand().equals(person.getAdressdaten().getLand())).collect(Collectors.toList());
        }
        log.logShowInfo(gefiltertePersonenDaten.size() + " Ergebnisse entsprechen den Filterkriterien");
        gefiltertePersonenDaten.forEach(person -> log.logShowInfo(person.getVorname() + " "+ person.getName()));
        return gefiltertePersonenDaten;
    }

    public Personendaten findById(long id) {
        log.logShowInfo("Suche nach Personendaten anhand der ID: " + id);
        return repository.findById(id).get();
    }

    public List<Personendaten> findByName(String name) {
        log.logShowInfo("Suche nach Personendaten anhand des Namen: " + name);
        return repository.findByName(name);
    }

    public List<Personendaten> findByVorname(String vorname) {
        log.logShowInfo("Suche nach Personendaten anhand des Vornamen: " + vorname);
        return repository.findByVorname(vorname);
    }

    public List<Personendaten> findByTelefonnummer(String telefonnummer) {
        log.logShowInfo("Suche nach Personendaten anhand der Telefonnummer: " + telefonnummer);
        return repository.findByTelefonnummer(telefonnummer);
    }

    public List<Personendaten> findByMail(String mail) {
        log.logShowInfo("Suche nach Personendaten anhand der E-Mail: " + mail);
        return repository.findByMail(mail);
    }

    public List<Personendaten> findByStrasse(String strasse) {
        log.logShowInfo("Suche nach Personendaten anhand der Straße: " + strasse);
        return repository.findByAdressdatenStrasse(strasse);
    }

    public List<Personendaten> findByPostleitzahl(long postleitzahl) {
        return repository.findByAdressdatenPostleitzahl(postleitzahl);
    }

    public List<Personendaten> findByStadt(String stadt) {
        log.logShowInfo("Suche nach Personendaten anhand der Stadt: " + stadt);
        return repository.findByAdressdatenStadt(stadt);
    }

    public List<Personendaten> findByLand(String land) {
        log.logShowInfo("Suche nach Personendaten anhand des Landes: " + land);
        return repository.findByAdressdatenLand(land);
    }

    public void addPersonendaten(Personendaten personendaten) {
        log.logShowInfo("Neuer Eintrag für " + personendaten.getVorname() + " " + personendaten.getName() + " " + "erstellt");
        repository.save(personendaten);
    }

    public boolean deletePersonendaten(long id) {
        if (repository.findById(id).isPresent()) {
            log.logShowInfo("Eintrag mit der ID: " + id + " wird gelöscht");
            repository.deleteById(id);
            return true;
        }
        log.logShowInfo("Eintrag mit der ID: " + id + " ist nicht vorhanden");
        return false;
    }


    public void deleteAll() {
        log.logShowInfo("Alle Informationen werden gelöscht");
        repository.deleteAll();
    }


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
                        log.logChangeInfo("Straße", zuAktualisierenderDatensatz.getAdressdaten().getStrasse(), aktualisiertePersonenDaten.getAdressdaten().getStrasse());
                        zuAktualisierenderDatensatz.getAdressdaten().setStrasse(aktualisiertePersonenDaten.getAdressdaten().getStrasse());
                    }
                    if (zuAktualisierenderDatensatz.getAdressdaten().getPostleitzahl() != aktualisiertePersonenDaten.getAdressdaten().getPostleitzahl()) {
                        log.logChangeInfo("Postleitzahl", zuAktualisierenderDatensatz.getAdressdaten().getPostleitzahl() + "", aktualisiertePersonenDaten.getAdressdaten().getPostleitzahl() + "");
                        zuAktualisierenderDatensatz.getAdressdaten().setPostleitzahl(aktualisiertePersonenDaten.getAdressdaten().getPostleitzahl());
                    }
                    if (!zuAktualisierenderDatensatz.getAdressdaten().getStadt().equals(aktualisiertePersonenDaten.getAdressdaten().getStadt())) {
                        log.logChangeInfo("Stadt", zuAktualisierenderDatensatz.getAdressdaten().getStadt(), aktualisiertePersonenDaten.getAdressdaten().getStadt());
                        zuAktualisierenderDatensatz.getAdressdaten().setStadt(aktualisiertePersonenDaten.getAdressdaten().getStadt());
                    }
                    if (!zuAktualisierenderDatensatz.getAdressdaten().getLand().equals(aktualisiertePersonenDaten.getAdressdaten().getLand())) {
                        log.logChangeInfo("Land", zuAktualisierenderDatensatz.getAdressdaten().getLand(), aktualisiertePersonenDaten.getAdressdaten().getLand());
                        zuAktualisierenderDatensatz.getAdressdaten().setLand(aktualisiertePersonenDaten.getAdressdaten().getLand());
                    }
                }
            } else {
                zuAktualisierenderDatensatz.setAdressdaten(null);
            }
            repository.save(zuAktualisierenderDatensatz);
            return zuAktualisierenderDatensatz;
        }
        return null;
    }
}
