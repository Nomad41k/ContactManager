package com.ContactManager;

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
        return firstName + "," + lastName + "," + address + "," + phoneNumber + "\n";
    }

    String printOut() {
        return firstName + " " + lastName + "\nAddress: " + address +
                "\nPhone number: " + String.valueOf(phoneNumber).replaceFirst("(\\d{3})(\\d{3})(\\d+)", "$1 $2 $3") + "\n";
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}