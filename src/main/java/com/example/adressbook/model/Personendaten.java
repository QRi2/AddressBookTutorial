package com.example.adressbook.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "personendaten")
public class Personendaten {

    public Personendaten(@NotNull(message = "Name muss gegeben sein") @NotBlank(message = "Name muss gegeben sein") String name, @NotNull(message = "Vorname muss gegeben sein") @NotBlank(message = "Vorname muss gegeben sein") String vorname, @NotNull(message = "E-Mail muss gegeben sein") @NotBlank(message = "E-Mail muss gegeben sein") String mail, String telefonnummer, Adressdaten adressdaten) {
        this.name = name;
        this.vorname = vorname;
        this.mail = mail;
        this.telefonnummer = telefonnummer;
        this.adressdaten = adressdaten;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @NotNull(message = "Name muss gegeben sein")
    @NotBlank(message = "Name muss gegeben sein")
    @Column(name = "name")
    private String name;

    @NotNull(message = "Vorname muss gegeben sein")
    @NotBlank(message = "Vorname muss gegeben sein")
    @Column(name = "vorname")
    private String vorname;

    @NotNull(message = "E-Mail muss gegeben sein")
    @NotBlank(message = "E-Mail muss gegeben sein")
    @Column(name="mail")
    private String mail;

    @Column(name="telefonnummer")
    private String telefonnummer;

    @OneToOne(cascade = CascadeType.ALL)
    private Adressdaten adressdaten;
}
