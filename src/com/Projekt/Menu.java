package com.Projekt;

import java.io.*;
import java.util.Vector;

import static com.Projekt.MenuAction.*;

class Menu {

    /**
     * Handles main menu operations
     */

    static void appRunner() {
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

            // Action is taken from the user and verified with regular expression
            switch (inputInt(input(), "[0-3]")) {
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

    private static void settingsMenu(Vector<Contact> contactList) {
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
     * @param contactList - Vector class collection
     * @return - integer - depending on a vector size - sorting method
     */

    private static int sortMenu(Vector<Contact> contactList) {
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

    private void searchContact(Vector<Contact> contactList, String criteria) {
        // TODO
    }
}