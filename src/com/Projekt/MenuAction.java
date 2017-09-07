package com.Projekt;

import java.io.*;
import java.util.Scanner;
import java.util.Vector;

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
        return retval;
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
     * @param contactList - Vector class collection
     */

    static void displayContacts(Vector<Contact> contactList) {
        for (int index = 0; index < contactList.size(); ) {
            System.out.println("" + contactList.get(index).toString() + "\n\n" +
                    "[1] - previous contact\t[2] - remove contact\t[3] - next contact\n" +
                    "\t\t\t\t[0] - back to main menu");

            switch (inputInt(input(), "[0-3]")) {
                case 1:
                    if (index >= 1) {
                        index--;
                        break;
                    } else {
                        System.out.println("This is the first record.");
                        index = contactList.size();
                        break;
                    }
                case 2:
                    removeContact(contactList, index);
                case 3:
                    if (index == contactList.size()) {
                        System.out.println("No more records!");
                        break;
                    } else {
                        index++;
                        break;
                    }
                case 0:
                    index = contactList.size();
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Opens file stream for import, parses each line for a new contact object and added to a vector
     *
     * @param contactList - Vector class collection
     * @param fileName - user input for a file to import
     * @throws IOException
     * @throws StringIndexOutOfBoundsException
     */

    static void importFromFile(Vector<Contact> contactList, String fileName) throws IOException, StringIndexOutOfBoundsException {
        FileInputStream fstream = new FileInputStream(fileName);
        DataInputStream input = new DataInputStream(fstream);
        BufferedReader bufferReader = new BufferedReader(new InputStreamReader(input));
        try {
            String strLine;
            while ((strLine = bufferReader.readLine()) != null) {
                String[] placeHolder = strLine.split(",");
                Contact contact = new Contact(placeHolder[0],placeHolder[1],placeHolder[2], placeHolder[3]);
                contactList.add(contact);
            }
        } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException | IOException e) {
            System.out.println("File import failed!\n" +
                    "Error: " + e.getMessage());
        }
        input.close();
    }

    /**
     * Opens file stream to export all contact vector elements to a file with specified name
     *
     * @param contactList - Vector class collection
     * @param fileName - user input for a file to import
     */

    static void exportToFile(Vector<Contact> contactList, String fileName) {
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
     * Adds new contact to a Contact Vector
     *
     * @param contactList - Vector class collection
     */

    static void addNewContact(Vector<Contact> contactList) {
        boolean runtime = true;
        String [] placeHolder = new String[4];

        while (runtime) {
            System.out.println("First name:");
            placeHolder[0] = input();
            System.out.println("Last name:");
            placeHolder[1] = input();
            System.out.println("Address:");
            placeHolder[2] = input();
            System.out.println("Phone Number:");
            placeHolder[3] = input();

            Contact contact = new Contact(placeHolder[0],placeHolder[1],placeHolder[2], placeHolder[3]);
            contactList.add(contact);

            System.out.println("Add more? [y/n]");
            if (input().toLowerCase().equals("n")) {
                runtime = false;
            }
        }
    }

    /**
     * Removes currently browsed contact object from contact vector
     *
     * @param contactList - Vector class collection
     * @param index - integer - index of currently browsed contact
     */

    private static void removeContact(Vector<Contact> contactList, int index) {
        System.out.println("Are you sure you want to remove? [y/n]");
        if (input().toLowerCase().equals("y")) {
            contactList.remove(index);
        }
    }
}
