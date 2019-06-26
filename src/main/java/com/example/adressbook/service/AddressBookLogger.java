package com.example.adressbook.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddressBookLogger {
    Logger adressbookLogger = LogManager.getLogger(AddressBookLogger.class.getName());

    public void logChangeInfo(String wasWurdeGeaendert, String vorher, String nachher) {
        adressbookLogger.info(wasWurdeGeaendert + " von " + vorher + " auf " + nachher + " geändert");
    }

    public void logNewAdressData(String wasWurdeGeaendert, String nachher) {
        adressbookLogger.info(wasWurdeGeaendert + " wurde neu hinzugefügt. Neuer Wert: " + nachher);
    }

    public void logDeleteEntry(long id) {
        adressbookLogger.info("Einrag mit der ID " + id + " wurde gelöscht");
    }

    public void logShowInfo(String message) {
        adressbookLogger.info(message);
    }
}
