package jdbc_project;

import java.sql.*;
import java.util.Scanner;

/**
 *
 * @author Michael Wolf
 */
public class JDBC_Main 
{
    //  Database credentials
    static String USER = DATABASE_INFO.USER;
    static String PASS = DATABASE_INFO.PASS;
    static String DBNAME = DATABASE_INFO.DBNAME;
    
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.apache.derby.jdbc.ClientDriver";
    static String DB_URL = "jdbc:derby://localhost:1527/";

    
    public static void main(String[] args) 
    {
        //GetDataBaseInfoFromInput();
        
        //Constructing the database URL connection string
        DB_URL = DB_URL + DBNAME + ";user="+ USER + ";password=" + PASS;
        Connection conn = null; //initialize the connection
        try 
        {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL);
            
            
            JDBC_TestApp.RunDataBaseApplication(conn);
            
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
        System.out.println("\nGoodbye!");
    }//end main
    
    
    public static void GetDataBaseInfoFromInput()
    {
        //Prompt the user for the database name, and the credentials.
        //If your database has no credentials, you can update this code to 
        //remove that from the connection string.
        Scanner in = new Scanner(System.in);
        System.out.print("Name of the database (not the user account): ");
        DBNAME = in.nextLine();
        System.out.print("Database user name: ");
        USER = in.nextLine();
        System.out.print("Database password: ");
        PASS = in.nextLine();
    }
}
