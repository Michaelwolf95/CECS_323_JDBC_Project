/*
 * @Author - Adam Flores
 * @Author - Michael Wolfe
 */
package jdbc_project;

import java.util.Scanner;
import java.sql.*;

public class dbMenu {
    public static void displayMain(Connection conn){
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        boolean quit = false;
        while(quit!=true){
            System.out.println("Main Menu\n");
            System.out.print("1. Display Data\n"
                + "2. Manipulate Data\n"
                + "3. Quit\n");
            
            System.out.print("Enter Choice: ");
            int n = reader.nextInt();
            switch(n){
                case 1: displayData(conn, reader);
                        break;
                case 2: insertData(conn, reader);
                        break;
                case 3: quit=true;
                        break;
            }
        }     
        reader.close(); 
    }
    
    public static void displayData(Connection conn, Scanner reader){
        System.out.println("List Information\n");
         System.out.print("1.List ALL writing groups\n"
            + "2. Data for a writing group\n"
            + "3. List ALL publishers\n"
            + "4. Data for a publisher\n"
            + "5. List ALL books\n"
            + "6. Data for a book\n");
            
        System.out.print("Enter Choice: ");
        int n = reader.nextInt();
        switch(n){
            case 1: displayWritingGroups(conn);
                    break;
            case 2: displayWritingGroup(conn, reader);
                    break;
            case 3: displayPublishers(conn);
                    break;
            case 4:displayPublisher(conn, reader);
                    break;
            case 5: displayBooks(conn);
                    break;
            case 6: displayBook(conn, reader);
                    break;
            default: break;
        }
    }
     public static void insertData(Connection conn, Scanner reader){
         System.out.println("Manipulate Data\n");
         System.out.print("1. Add a book\n"
            + "2. Change publisher\n"
            + "3. Remove a book\n"
            + "Enter any other key to go back\n");
            
        System.out.print("Enter Choice: ");
        int n = reader.nextInt();
        switch(n){
            case 1: addBook(conn, reader);
                    break;
            case 2: changePub(conn, reader);
                    break;
            case 3: rmvBook(conn, reader);
                    break;
            default: break;
        }
     }
     
     public static void displayWritingGroups(Connection conn){
        JDBC_DatabaseTools.LIST_RESULTS(conn, "writingGroups", new String[]{"groupName", "headWriter"});
     }
     
     public static void displayWritingGroup(Connection conn, Scanner reader){
        reader.nextLine();      //Clear \n from buffer
        System.out.print("Enter Writing Group: ");
        String wg = reader.nextLine();
        JDBC_DatabaseTools.LIST_RESULTS(conn,"WritingGroups", new String[]{"groupName", "headWriter", "yearFormed", "subject"}, "groupName", wg);
     }

    public static void displayPublishers(Connection conn) {
        JDBC_DatabaseTools.LIST_RESULTS(conn, "publishers", new String[]{"publisherName"});
    }

    public static void displayPublisher(Connection conn, Scanner reader) {
        reader.nextLine();      //Clear \n from buffer
        System.out.print("Enter Publisher: ");
        String pub = reader.nextLine();
        JDBC_DatabaseTools.LIST_RESULTS(conn,"publishers", new String[]{"publisherName","publisherAddress","publisherPhone", "publisherEmail"}, "publisherName", pub);
                    
    }

    public static void displayBooks(Connection conn) {
        JDBC_DatabaseTools.LIST_RESULTS(conn, "books", new String[]{"bookTitle", "numberPages"}); 
    }

    public static void displayBook(Connection conn, Scanner reader) {
        reader.nextLine();      //Clear \n from buffer
        System.out.print("Enter Book Title: ");
        String book = reader.nextLine();
        JDBC_DatabaseTools.LIST_RESULTS(conn,"books", new String[]{"groupName", "bookTitle", "publisherName", "yearPublished", "numberPages"}, "bookTitle", book);
    }

    public static void addBook(Connection conn, Scanner reader) {
        reader.nextLine();      //Clear \n from buffer
        String rowInfo[]= new String[5];
        System.out.print("Enter Writing Group Name: ");
        rowInfo[0] = reader.nextLine();
        System.out.print("Enter book name: ");
        rowInfo[1] = reader.nextLine();
        System.out.print("Enter the publisher name: ");
        rowInfo[2] = reader.nextLine();
        System.out.print("Publisher Year: ");
        rowInfo[3] = reader.nextLine();
        System.out.print("Number of Pages");
        rowInfo[4] = reader.nextLine();
        JDBC_DatabaseTools.INSERT_BOOK(conn, "books", new String[]{"groupName", "bookTitle", "publisherName", "yearPublished", "numberPages"}, rowInfo);
    }

    public static void changePub(Connection conn, Scanner reader) {
        reader.nextLine();
        String newPub[] = new String[4];
        System.out.print("Old publisher name: ");
        String oldPub = reader.nextLine();
        System.out.print("New publisher name: ");
        newPub[0] = reader.nextLine();
        System.out.print("New publisher address: ");
        newPub[1] = reader.nextLine();
        System.out.print("New publisher email address: ");
        newPub[2] = reader.nextLine();
        System.out.print("New publisher phone number: ");
        newPub[3] = reader.nextLine();
        JDBC_DatabaseTools.CHANGE_PUBLISHER(conn, oldPub, newPub);
    }

    public static void rmvBook(Connection conn, Scanner reader) {
        reader.nextLine();
        System.out.print("Enter book to be removed: ");
        String bookTitle = reader.nextLine();
        JDBC_DatabaseTools.REMOVE_BOOK(conn, bookTitle);
    }
     
     
}
