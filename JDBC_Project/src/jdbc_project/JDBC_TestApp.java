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
        System.out.println("Running Test App");
        JDBC_DatabaseTools.LIST_RESULTS(conn, "books", new String[]{"groupName","bookTitle" });
    }
    
}
