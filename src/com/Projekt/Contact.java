package com.Projekt;

public class Contact {
    private String firstName;

    private String lastName;
    private String address;
    private String phoneNumber;

    /**
     * Sets each field in an Contact object
     *
     * @param firstName
     * @param lastName
     * @param address
     * @param phoneNumber
     */
    Contact(String firstName, String lastName, String address, String phoneNumber) {
        setFirstName(firstName);
        setLastName(lastName);
        setAddress(address);
        setPhoneNumber(phoneNumber);
    }

    @Override
    public String toString() {
        return firstName + "," + lastName + "," + address + "," + phoneNumber + '\n';
    }

    void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    void setLastName(String lastName) {
        this.lastName = lastName;
    }
    void setAddress(String address) {
        this.address = address;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getAddress() {
        return address;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
}
