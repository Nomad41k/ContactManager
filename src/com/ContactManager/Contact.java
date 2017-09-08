package com.ContactManager;

import java.util.ArrayList;

public class Contact {
    static ArrayList<Contact> contactList = new ArrayList<>();
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private int age;


    Contact(String firstName, String lastName, String address, String phoneNumber, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.age = age;
    }

    @Override
    public String toString() {
        return firstName + "," + lastName + "," + address + "," + phoneNumber + "," + age + "\n";
    }

    String printOut() {
        return firstName + " " + lastName + "\nAddress: " + address + "\nAge: " + age +
                "\nPhone number: " + String.valueOf(phoneNumber).replaceFirst("(\\d{3})(\\d{3})(\\d+)", "$1 $2 $3") + "\n";
    }

    String getFirstName() {
        return firstName;
    }
    String getLastName() {
        return lastName;
    }

    public static void setContactList(ArrayList<Contact> contactList) {
        Contact.contactList = contactList;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAge(int age) {
        this.age = age;
    }
}