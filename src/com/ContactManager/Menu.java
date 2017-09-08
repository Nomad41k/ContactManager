package com.ContactManager;

import java.io.*;
import java.util.ArrayList;

import static com.ContactManager.MenuAction.*;

class Menu {

    /**
     * Handles main menu operations
     */

    static void appRunner() {
        ArrayList<Contact> contactList = new ArrayList<>();

        boolean runtime = true;
        while (runtime) {
            System.out.println("=== Contact manager ===\n\n" +
                    "== Main Menu ==\n" +
                    "1. Show contact list\n" +
                    "2. Add new contact\n" +
                    "3. Search contact\n" +
                    "4. Settings\n" +
                    "0. Exit\n");
            System.out.print("== Action: ");

            // Action is taken from the user and verified with regular expression
            switch (inputInt(input(), "[0-4]")) {
                case 1:
                    if (contactList.size() > 0) {
                        displayContacts(contactList);
                    } else {
                        System.out.println("No contacts. Add them first!");
                    }
                    break;
                case 2:
                    MenuAction.addNewContact(contactList);
                    break;
                case 3:
                    System.out.println("Enter the phrase you're searching for: ");
                    MenuAction.search(contactList, input());
                    break;
                case 4:
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
     * Displays settings menu
     * @param contactList - Vector class collection
     */

    private static void settingsMenu(ArrayList<Contact> contactList) {
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
                    switch (sortMenu()) {
                        case 1:
//                            sort(contactList, 1);
                            break;
                        case 2:
//                            sort(contactList, 2);
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
                        System.out.println("Enter filename: ");
                        importFromFile(contactList, input());
                    } catch (IOException e) {
                        System.out.println("File not found!");
                    }
                    break;
                case 3:
                    System.out.println("Enter filename: ");
                    exportToFile(contactList, input());
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
     * @return - integer - depending on a vector size - sorting method
     */

    private static int sortMenu() {

        System.out.println("=== Sort Menu ===\n" +
                "Choose sorting method?\n" +
                "1. Sort by first name\n" +
                "2. Sort by last name\n" +
                "0. Back to menu\n" +
                "== Action: ");

        switch (inputInt(input(), "[0-2]")) {
                case 1:
                    return 1;
                case 2:
                    return 2;
                case 0:
                    return 0;
                default:
                    System.out.println("Invalid action!");
                    break;
            }
        return 0;
    }
}