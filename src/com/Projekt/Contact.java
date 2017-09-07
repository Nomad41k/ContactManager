package com.Projekt;

public class Contact {
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;

    Contact(String firstName, String lastName, String address, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return firstName + "," + lastName + "," + address + "," + phoneNumber + '\n';
    }
}
