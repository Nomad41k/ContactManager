package com.Projekt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

class Menu {

    /**
     * Handles main menu operations
     */
    void appRunner() {
        Vector<Contact> contactList = new Vector<>();

        boolean runtime = true;
        while (runtime) {
            System.out.println("=== Contact manager ===\n\n" +
                    "== Main Menu ==\n" +
                    "1. Show contact list\n" +
                    "2. Add new contact\n" +
                    "3. Settings\n" +
                    "0. Exit\n");
            System.out.print("== Action: ");

            switch (inputInt(input(), "[0-3]")) {
                case 1:
                    if (contactList.size() > 0) {
                        displayContacts(contactList);
                    } else {
                        System.out.println("No contacts.");
                    }
                    break;
                case 2:
                    addNewContact(contactList);
                    break;
                case 3:
                    settingsMenu(contactList);
                    break;
                case 0:
                    System.out.println("Are you sure you want to exit? [y/n]");
                    if (input().toLowerCase().equals("y")) {
                        runtime = false;
                        break;
                    } else {
                        break;
                    }
                default:
                    System.out.println("Invalid action!");
                    break;
            }
        }
    }

    /**
     * Basic user input String validation
     *
     * @return retval
     *         input
     */
    private String input() {
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
     * Parses user input into an Integer
     *
     * @param input - returned value from input()
     * @param regEx - specified regular expression
     * @return - parsed Integer
     */
    private int inputInt(String input, String regEx) {
        while (!(input.matches(regEx))) {
            System.out.println("Input is not valid!");
            input = input();
        }
        return Integer.parseInt(input);
    }

    /**
     * Prints the list of current held contact objects
     *
     * @param contactList - currently held contact objects vector
     */
    private void displayContacts(Vector<Contact> contactList) {
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
                    if (index++ > contactList.size()) {
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
     * Adds new contact to a Contact Vector
     *
     * @param contactList - currently held contact objects vector
     */
    private void addNewContact(Vector<Contact> contactList) {
        Contact contact = new Contact();
        boolean runtime = true;

        while (runtime) {

            System.out.println("First name:");
            contact.setFirstName(input());
            System.out.println("Last name:");
            contact.setLastName(input());
            System.out.println("Address:");
            contact.setAddress(input());
            System.out.println("Phone Number:");
            contact.setPhoneNumber(input());

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
     * @param contactList - currently held contact objects vector
     * @param index - index of currently browsed contact
     */
    private void removeContact(Vector<Contact> contactList, int index) {
        System.out.println("Are you sure you want to remove? [y/n]");
        if (input().toLowerCase().equals("y")) {
            contactList.remove(index);
        }
    }

    /**
     * Displays settings menu
     * @param contactList - currently held contact objects vector
     */
    private void settingsMenu(Vector<Contact> contactList) {
        boolean runtime = true;
        while (runtime) {
            System.out.println("=== Settings ===\n" +
                    "What would you like to do?\n" +
                    "1. Edit sorting\n" +
                    "2. Import from file\n" +
                    "3. Export to file\n" +
                    "0. Back to menu\n" +
                    "== Action: ");

            switch (inputInt(input(), "[0-3]")) {
                case 1:
                    switch (sortMenu(contactList)) {
                        case 1:
//                            quickSort(contactList, 1);
                            break;
                        case 2:
//                            sortBubble(contactList, 1);
                            break;
                        case 3:
//                            quickSort(contactList, 2);
                            break;
                        case 4:
//                            sortBubble(contactList, 2);
                            break;
                        case 0:
                            break;
                        default:
                            System.out.println("Invalid action!");
                            break;
                    }
                    break;
                case 2:
                    try {
//                        System.out.println("Enter filename: ");
                        importFromFile(contactList, "D:/file.txt");
                    } catch (IOException e) {
                        System.out.println("File not found!");
                    }
                    break;
                case 3:
                    // TODO
                    break;
                case 0:
                    runtime = false;
                    break;
                default:
                    System.out.println("Invalid action!");
                    break;
            }
        }
    }

    /**
     * Displays sorting choice menu
     * @param contactList - currently held contact objects vector
     * @return
     */
    private int sortMenu(Vector<Contact> contactList) {
        boolean isContactListBig = false;

        System.out.println("=== Sort Menu ===\n" +
                "Choose sorting method?\n" +
                "1. Sort by first name\n" +
                "2. Sort by last name\n" +
                "0. Back to menu\n" +
                "== Action: ");

        if (contactList.size() > 20) {
            isContactListBig = true;
        }

        switch (inputInt(input(), "[0-2]")) {
                case 1:
                    if (isContactListBig) {
                        return 1;
                    } else {
                        return 2;
                    }
                case 2:
                    if (isContactListBig) {
                        return 3;
                    } else {
                        return 4;
                    }
                case 0:
                    return 0;
                default:
                    System.out.println("Invalid action!");
                    break;
            }
        return 0;
    }


//    private void quickSort(Vector<Contact> contactList, int method) {
//    private static void quicksort(int tablica[], int x, int y) {
//        int pivot = contactList.size();
//
//            switch (method) {
//            case 1:
//                do {
//
//                } while
//            case 2:
//
//        }
//        int i,j,v,temp;
//
//        i=x;
//        j=y;
//        v=tablica[(x+y) / 2];
//        do {
//            while (tablica[i]<v)
//                i++;
//            while (v<tablica[j])
//                j--;
//            if (i<=j) {
//                temp=tablica[i];
//                tablica[i]=tablica[j];
//                tablica[j]=temp;
//                i++;
//                j--;
//            }
//        }
//        while (i<=j);
//        if (x<j)
//            quicksort(tablica,x,j);
//        if (i<y)
//            quicksort(tablica,i,y);
//    }
//}
//
//    private void sortBubble(Vector<Contact> contactList, int method) {
//        switch (method) {
//            case 1:
//                int size = contactList.size();
//                break;
//            case 2:
//                break;
//        }
//    }

    /**
     * Opens file stream for import, parses each line for a new contact object and added to a vector
     *
     * @param contactList currently held contact objects vector
     * @param fileName - user input for a file to import
     * @throws IOException
     * @throws StringIndexOutOfBoundsException
     */
    private void importFromFile(Vector<Contact> contactList, String fileName) throws IOException, StringIndexOutOfBoundsException {
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        Contact contact = new Contact();

        try {
        String textLine = bufferedReader.readLine();
        int breakIndex;

            while(textLine != null) {
                breakIndex = textLine.indexOf(",");
                contact.setFirstName(textLine.substring(0, breakIndex));
                textLine = textLine.substring(breakIndex + 1);

                breakIndex = textLine.indexOf(",");
                contact.setLastName(textLine.substring(0, breakIndex));
                textLine = textLine.substring(breakIndex + 1);

                breakIndex = textLine.indexOf(",");
                contact.setAddress(textLine.substring(0, breakIndex));
                textLine = textLine.substring(breakIndex + 1);

                breakIndex = textLine.indexOf(",");
                contact.setAddress(textLine.substring(0, breakIndex));

                contact.setPhoneNumber(textLine.substring(breakIndex + 1));

                contactList.add(contact);
            }
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("File import failed!");
        } catch (IOException e) {
            System.out.println("Wrong input!");
        }

        bufferedReader.close();
    }

    private void exportToFile(Vector<Contact> contactList, String fileName) {
        // TODO
    }

    private void searchContact(Vector<Contact> contactList, String criteria) {
        // TODO
    }
}