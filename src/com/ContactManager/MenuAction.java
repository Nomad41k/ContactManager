package com.ContactManager;

import java.io.*;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.ArrayList;

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
     *
     * @param contactList - ArrayList class collection
     */

    static void displayContacts(ArrayList<Contact> contactList) {
        boolean runtime = true;

        while (runtime) {
            for (int index = 0; index < contactList.size();) {
                System.out.println(contactList.get(index).printOut() + "\n\n" +
                        "[1] - previous contact\t[2] - remove contact\t[3] - next contact\n" +
                        "\t\t\t\t[0] - back to main menu");

                switch (inputInt(input(), "[0-3]")) {
                    case 1:
                        if (index == 0) {
                            index = contactList.size() - 1;
                        } else {
                            index--;
                        }
                        break;
                    case 2:
                        removeContact(contactList, index);
                        if (contactList.size() == 0) {
                            System.out.println("You've emptied the cache!");
                            runtime = false;
                        }
                        break;
                    case 3:
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
     *
     * @param contactList - ArrayList class collection
     */

    static void addNewContact(ArrayList<Contact> contactList) {
        boolean runtime = true, runtimeMore = true;
        String [] param = new String[4];

        while (runtime) {
            System.out.println("First name:");
            param[0] = input();
            System.out.println("Last name:");
            param[1] = input();
            System.out.println("Address:");
            param[2] = input();
            System.out.println("Phone Number:");
            param[3] = input().replaceAll("[- ()]", "");

            Contact contact = new Contact(param[0], param[1], param[2], param[3]);
            contactList.add(contact);

            while (runtimeMore) {
                System.out.println("Add more? [y/n]");
                switch (input().toLowerCase()) {
                    case "n":
                        runtime = false;
                        runtimeMore = false;
                        break;
                    case "y":
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
     * @param contactList - ArrayList class collection
     * @param index - integer - index of currently browsed contact
     */

    private static void removeContact(ArrayList<Contact> contactList, int index) {
        System.out.println("Are you sure you want to remove? [y/n]");
        if (input().toLowerCase().equals("y")) {
            contactList.remove(index);
        }
    }

    /**
     * Opens file stream for import, parses each line for a new contact object and added to a ArrayList
     *
     * @param contactList - ArrayList class collection
     * @param fileName - user input for a file to import
     * @throws IOException
     * @throws StringIndexOutOfBoundsException
     */

    static void importFromFile(ArrayList<Contact> contactList, String fileName) throws IOException, StringIndexOutOfBoundsException {
        FileInputStream fstream = new FileInputStream(fileName);
        DataInputStream input = new DataInputStream(fstream);
        BufferedReader bufferReader = new BufferedReader(new InputStreamReader(input));
        String stringLine;

        try {
            while ((stringLine = bufferReader.readLine()) != null) {
                String[] param = stringLine.split(",", 4);
                Contact contact = new Contact(param[0], param[1], param[2], param[3]);
                contactList.add(contact);
            }
        } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException | IOException e) {
            System.out.println("File import failed!\n" +
                    "Error: " + e.getMessage());
        }
        input.close();
    }

    /**
     * Opens file stream to export all contact ArrayList elements to a file with specified name
     *
     * @param contactList - ArrayList class collection
     * @param fileName - user input for a file to import
     */

    static void exportToFile(ArrayList<Contact> contactList, String fileName) {
        try(PrintWriter output = new PrintWriter(fileName)) {
            for (Contact aContactList : contactList) {
                output.print(aContactList.toString());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File export failed!\n" +
                    "Error: " + e.getMessage());
        }
    }

    static void search(ArrayList<Contact> contactList, String input) {
        for (Contact aContactList : contactList) {
            if (aContactList.printOut().toLowerCase().contains(input.toLowerCase())) {
                System.out.println("\n" + aContactList.printOut() + "\n");
            }
        }
    }

//    static void sort(ArrayList<Contact> contactList, int type) {
//        if (type == 1) {
//            contactList.sort();
//        }
//    }
}
