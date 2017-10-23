/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc_project;

import java.sql.*;
import java.util.Scanner;

/**
 *
 * @author Mimi Opkins with some tweaking from Dave Brown
 */
public class CECS323JavaTermProject 
{
    //  Database credentials
    static String USER = DATABASE_INFO.USER;
    static String PASS = DATABASE_INFO.PASS;
    static String DBNAME = DATABASE_INFO.DBNAME;
    
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.apache.derby.jdbc.ClientDriver";
    static String DB_URL = "jdbc:derby://localhost:1527/";
//            + "testdb;user=";

    public static void GetDataBaseInfoFromInput()
    {
        //Prompt the user for the database name, and the credentials.
        //If your database has no credentials, you can update this code to 
        //remove that from the connection string.
        Scanner in = new Scanner(System.in);
        System.out.print("Name of the database (not the user account): ");
        DBNAME = in.nextLine();
        System.out.print("Database user name: ");
//<<<<<<< Updated upstream
        USER = in.nextLine();
        System.out.print("Database password: ");
        PASS = in.nextLine();
    }
    
    public static void main(String[] args) 
    {
        //GetDataBaseInfoFromInput();
//=======
//        //USER = in.nextLine();
//        USER = "zzzx";
//        System.out.print("Database password: ");
//        //PASS = in.nextLine();
//        PASS = "zzzx";
//>>>>>>> Stashed changes
        
        //Constructing the database URL connection string
        DB_URL = DB_URL + DBNAME + ";user="+ USER + ";password=" + PASS;
        Connection conn = null; //initialize the connection
        try 
        {
            //STEP 2: Register JDBC driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL);
            
            //JDBC_DatabaseTools.TEST_METHOD(conn);
            JDBC_DatabaseTools.LIST_RESULTS(conn, "books", new String[]{"groupName","bookTitle" });

            conn.close();
        } 
        catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }//end main
}
