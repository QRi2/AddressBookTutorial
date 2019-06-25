package com.example.adressbook.controller;

import lombok.Getter;
import lombok.Setter;
import org.wolfe.query.QueryParamOperator;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class QueryFilterRequestModel {

    @NotNull
    private long id;

    @QueryParamOperator(allowed = "=")
    private String name;

    @QueryParamOperator(allowed = "=")
    private String vorname;

    @QueryParamOperator(allowed = "=")
    private String mail;

    @QueryParamOperator(allowed = "=")
    private String strasse;

    @QueryParamOperator(allowed = "=")
    private String land;

    @QueryParamOperator(allowed = "=")
    private String stadt;

    @QueryParamOperator(allowed = "=")
    private String telefonnummer;

    @QueryParamOperator(allowed = "=")
    private long postleitzahl;


}
