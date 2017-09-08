package com.ContactManager;

import java.io.*;
import java.util.Scanner;

import static com.ContactManager.Contact.contactList;

class MenuAction {

    /**
     * Basic user input String validation. Will not accept empty input.
     *
     * @return String - retval
     */

    static String input() {
        Scanner scanner = new Scanner(System.in);
        String retval;
        retval = scanner.nextLine();

        while (retval.equals("")) {
            System.out.println("Input cannot be empty!");
            retval = scanner.nextLine();
        }
        return retval.trim();
    }

    /**
     * Parses user input into an Integer, can be validated with regular expression
     *
     * @param input - returned value from input()
     * @param regEx - String - specified regular expression
     * @return - parsed Integer
     */

    static int inputInt(String input, String regEx) {
        while (!(input.matches(regEx))) {
            System.out.println("Input is not valid!");
            input = input();
        }
        return Integer.parseInt(input);
    }

    /**
     * Prints the list of current held contact objects
     */

    static void displayContacts() {
        boolean runtime = true;

        while (runtime) {
            for (int index = 0; index < contactList.size();) {
                System.out.println(contactList.get(index).printOut() + "\n\n" +
                        "[1] - previous contact\t[2] - remove contact\t[3] - modify contact\t[4] - next contact\n" +
                        "\t\t\t\t\t[0] - back to main menu" +
                        "\t\tContact database size: " + contactList.size());

                switch (inputInt(input(), "[0-4]")) {
                    case 1:
                        if (index == 0) {
                            index = contactList.size() - 1;
                        } else {
                            index--;
                        }
                        break;
                    case 2:
                        removeContact(index);
                        if (contactList.size() == 0) {
                            System.out.println("You've emptied the cache!");
                            runtime = false;
                        }
                        break;
                    case 3:
                        modifyContact(index);
                        break;
                    case 4:
                        index++;
                        break;
                    case 0:
                        runtime = false;
                        index = contactList.size();
                        break;
                    default:
                        break;
                }
            }
        }
    }

    /**
     * Adds new contact to a Contact ArrayList
     */

    static void addNewContact() {
        boolean runtime = true;
        String [] param = new String[4];
        int paramInt;

        while (runtime) {
            boolean runtimeMore = true;
            System.out.print("First name: ");
            param[0] = input();
            System.out.print("Last name: ");
            param[1] = input();
            System.out.print("Address: ");
            param[2] = input();
            System.out.print("Phone Number: ");
            param[3] = input().replaceAll("[- ()]", "");
            System.out.print("Age: ");
            paramInt = inputInt(input(), "[0-9]+");

            Contact contact = new Contact(param[0], param[1], param[2], param[3], paramInt);

            if (isObjectAlreadyInArray(contact.toString())) {
                System.out.println("Contact already exists!");
            } else {
                contactList.add(contact);
            }
            while (runtimeMore) {
                System.out.println("Add more? [y/n]");
                switch (input().toLowerCase()) {
                    case "n":
                        runtime = false;
                        runtimeMore = false;
                        break;
                    case "y":
                        runtimeMore = false;
                        break;
                    default:
                        System.out.println("Invalid input!");
                        runtime = false;
                        break;
                }
            }
        }
    }

    /**
     * Removes currently browsed contact object from contact ArrayList
     *
     * @param index - integer - index of currently browsed contact
     */

    private static void removeContact(int index) {
        System.out.println("Are you sure you want to remove? [y/n]");
        if (input().toLowerCase().equals("y")) {
            contactList.remove(index);
        }
    }

    /**
     * Modifies existing contact at passed index
     * @param contactIndex - index of array for
     */
    private static void modifyContact(int contactIndex) {

        System.out.println("Select field to edit\n" +
                "1. First name\n" +
                "2. Last name\n" +
                "3. Address\n" +
                "4. Phone number\n" +
                "5. Age\n" +
                "0. None - back to menu");
        System.out.print("== Action: ");
        switch(inputInt(input(), "[0-5]")) {
            case 1:
                System.out.print("Enter new value: ");
                contactList.get(contactIndex).setFirstName(input());
                break;
            case 2:
                System.out.print("Enter new value: ");
                contactList.get(contactIndex).setLastName(input());
                break;
            case 3:
                System.out.print("Enter new value: ");
                contactList.get(contactIndex).setAddress(input());
                break;
            case 4:
                System.out.print("Enter new value: ");
                contactList.get(contactIndex).setPhoneNumber(input());
                break;
            case 5:
                System.out.print("Enter new value: ");
                contactList.get(contactIndex).setAge(inputInt(input(), "[0-9]+"));
                break;
            case 0:
                break;
            default:
                break;
        }
    }

    /**
     * Opens file stream for import, parses each line for a new contact object and added to a ArrayList
     *
     * @param fileName - user input for a file to import
     */

    static void importFromFile(String fileName) throws IOException, StringIndexOutOfBoundsException {
        FileInputStream fstream = new FileInputStream(fileName);
        DataInputStream input = new DataInputStream(fstream);
        BufferedReader bufferReader = new BufferedReader(new InputStreamReader(input));
        String stringLine;

        int existingContactsNumber = 0;

        try {
            while ((stringLine = bufferReader.readLine()) != null) {
                String[] param = stringLine.split(",", 5);
                Contact contact = new Contact(param[0], param[1], param[2], param[3], Integer.parseInt(param[4]));
                if (isObjectAlreadyInArray(contact.toString())) {
                    existingContactsNumber++;
                } else {
                    contactList.add(contact);
                }
            }
            System.out.println("File import complete!\nContacts already existing in array: " + existingContactsNumber);
        } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException | IOException e) {
            System.out.println("File import failed!\n" +
                    "Error: " + e.getMessage());
        }
        input.close();
    }

    /**
     * Opens file stream to export all contact ArrayList elements to a file with specified name
     *
     * @param fileName - user input for a file to import
     */

    static void exportToFile(String fileName) {
        try(PrintWriter output = new PrintWriter(fileName)) {
            for (Contact aContactList : contactList) {
                output.print(aContactList.toString());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File export failed!\n" +
                    "Error: " + e.getMessage());
        }
    }

    /**
     * Searches for the inputed phrase in each objects field
     * @param input - user input of searched phrase
     */

    static void search(String input) {
        for (Contact aContactList : contactList) {
            if (aContactList.printOut().toLowerCase().contains(input.toLowerCase())) {
                System.out.println("\n" + aContactList.printOut() + "\n");
            }
        }
    }

    /**
     * Sorts the contacts array according to user input - either alphabetically by first or last name. Uses bubble sort.
     * @param type - user input for a type of sorting
     */

    static void sort(int type) {

        if (type == 1) {
            for (int i = 0; i < contactList.size(); i++) {
                for (int j = 0; j < contactList.size() - 1; j++) {
                    if (contactList.get(j).getFirstName().compareTo(contactList.get(j + 1).getFirstName()) > 0) swapObject(j);
                }
            }
        }
        if (type == 2) {
            for (int i = 0; i < contactList.size(); i++) {
                for (int j = 0; j < contactList.size() - 1; j++) {
                    if (contactList.get(j).getLastName().compareTo(contactList.get(j + 1).getLastName()) > 0) swapObject(j);
                }
            }
        }
        if (type == 3) {
            for (int i = 0; i < contactList.size(); i++) {
                for (int j = 0; j < contactList.size() - 1; j++) {
                    if (contactList.get(j).getAge() > contactList.get(j + 1).getAge()) swapObject(j);
                }
            }
        }
    }

    /**
     * Swaps ArrayList object with a following one
     * @param index - object index to swapObject
     */

    private static void swapObject(int index) {
        // Swaps places of objects
        // Copy object at index j to the end of an ArrayList
        contactList.add(contactList.get(index));
        // Move object at index j + 1 into index 1
        contactList.set(index, contactList.get(index + 1));
        // Move object at index j int j + 1
        contactList.set(index + 1, contactList.get((contactList.size() - 1)));

        // Remove spare swapped object
        contactList.remove(contactList.size() - 1);
    }

    /**
     * Verifies if the newly added Contact object is already present in ArrayList contactList
     * @param comparedObject - newly added object for verification
     * @return boolean - object is or is not present
     */
    private static boolean isObjectAlreadyInArray (String comparedObject) {
        for (Contact aContactList : contactList) {
            if (aContactList.toString().equals(comparedObject))
                return true;
        }
        return false;
    }
}
