/*
 * @Author - Adam Flores
 * @Author - Michael Wolfe
 */
package jdbc_project;

import java.util.Scanner;
import java.sql.*;

public class dbMenu {
    protected static Scanner reader = new Scanner(System.in);  // Reading from System.in
    public static void displayMain(Connection conn){
        boolean quit = false;
        while(quit!=true){
            System.out.println("Main Menu\n");
            System.out.print("1. Display Data\n"
                + "2. Insert Data\n"
                + "3. Quit\n");
            
            System.out.println("Enter Choice: ");
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
            
        System.out.println("Enter Choice: ");
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
            
        System.out.println("Enter Choice: ");
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
         JDBC_DatabaseTools.LIST_RESULTS(conn, "writingGroups", new String[]{"groupName"});
     }
     
     public static void displayWritingGroup(Connection conn, Scanner reader){
        System.out.print("Enter Writing Group: ");
        String wg = reader.next();
        
     }

    private static void displayPublishers(Connection conn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void displayPublisher(Connection conn, Scanner reader) {
        System.out.print("Enter Publisher: ");
        String pub = reader.next();
                    
    }

    private static void displayBooks(Connection conn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void displayBook(Connection conn, Scanner reader) {
        System.out.print("Enter Book Title:");
        String book = reader.next();
                    
    }

    private static void addBook(Connection conn, Scanner reader) {
        System.out.print("Enter Writing Group Name: ");
        String grpName = reader.nextLine();
        System.out.print("Enter book name: ");
        String bkName = reader.nextLine();
        System.out.print("Enter the publisher name: ");
        String pubName = reader.nextLine();
        System.out.print("Publisher Year: ");
        String pubYr = reader.nextLine();
        System.out.print("Number of Pages");
        String pgNum = 
        JDBD_DatabaseTools.INSERT_ROW(conn, "books", new String[]("groupName", "bookTitle", "publisherName", "yearPublished","numberPages"), 
            new String[]())
    }

    private static void changePub(Connection conn, Scanner reader) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void rmvBook(Connection conn, Scanner reader) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     
     
}
