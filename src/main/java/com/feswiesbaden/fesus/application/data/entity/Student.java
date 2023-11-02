package com.feswiesbaden.fesus.application.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;

import java.time.LocalDate;

@Entity
@Table(name = "student")
public class Student extends AbstractEntity{
    private String firstName;
    private String lastName;
    private String apprenticeshipGroup;
    private String apprenticeshipFocus;
    private String gender;
    private String streetName;
    private String houseNumber;
    private String postalCode;
    private String city;
    @Email
    private String email;
    private String phone;
    private LocalDate dateOfBirth;
    private String birthPlace;
    private String birthCountry;
    private String firstLanguage;
    private String secondLanguage;
    private String firstNationality;
    private String secondNationality;
    private String previousSchoolName;
    private String previousSchoolAddress;
    private String previousSchoolPostal;
    private String previousSchoolType;
    private String previousHighestGraduation;
    private String companyName;
    private String companyAddress;
    private String companyStreetname;
    private String companyPhoneNumber;
    private String companyFax;
    private LocalDate companyApprenticeshipBeginningDate;
    @Email
    private LocalDate companyEmail;
    private String familyContactLastName;
    private String familyContactFirstName;
    private String familyContactAddress;
    private String familyContactStreetname;
    private String familyContactPhonePrivate;
    private String familyContactPhoneBusiness;




}
