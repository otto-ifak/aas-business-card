package com.otto.aas_business_card;

import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;

@Entity
class Contact {

    @Id
    @GeneratedValue(generator = "UUID")
    public UUID id;
    public String academicTitle;
    public String firstName;
    public String lastName;
    public String company;
    public String department;
    public String street;
    public String zipCode;
    public String cityTown;
    public String nationalCode;
    public String mail;
    public String phone;
    public String web;

    Contact() {
    }

    // public String getName() {
    //     return this.name;
    // }

    // public void setName(String name) {
    //     this.name = name;
    // }
}