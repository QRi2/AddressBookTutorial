package com.example.adressbook.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "address")
public class Adressdaten {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    long id;

    @Column(name = "strasse")
    String strasse;
    @Column(name = "postleitzahl")
    int postleitzahl;
    @Column(name = "stadt")
    String stadt;
    @Column(name = "land")
    String land;

    public Adressdaten(String strasse, int postleitzahl, String stadt, String land) {
        this.strasse = strasse;
        this.postleitzahl = postleitzahl;
        this.stadt = stadt;
        this.land = land;
    }
}
