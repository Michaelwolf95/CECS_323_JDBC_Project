package jdbc_project;

import java.sql.*;
import java.util.Scanner;
/**
 *
 * @author Michael
 */
public class JDBC_TestApp 
{
    public static void RunDataBaseApplication(Connection conn)
    {
        System.out.println("Running Test App...");
        
        
        //JDBC_DatabaseTools.INSERT_ROW(conn, "books", new String[]{"groupName","bookTitle", "publisherName" }, new String[]{"DataBase Testers Inc.", "InsertedBook Issue 2", "Scholastic" });
        
        //JDBC_DatabaseTools.LIST_RESULTS(conn, "books", new String[]{"groupName","bookTitle" });
        //JDBC_DatabaseTools.LIST_RESULTS_ALL(conn, "books");
        
        JDBC_DatabaseTools.LIST_RESULTS(conn, "books", new String[]{"groupName","bookTitle" }, "bookTitle", "TestBook Issue 2");
    }
    
}
