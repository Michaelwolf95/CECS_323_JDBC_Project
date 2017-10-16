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
 * @author Michael
 */
public class JDBC_DatabaseTools 
{
    static final String displayFormat="%-30s%-30s%-30s%-30s\n";
    public static String dispNull (String input) 
    {
        if (input == null || input.length() == 0)
            return "N/A";
        else
            return input;
    }
    public static void TEST_METHOD(Connection conn)
    {
        Statement stmt = null;
        try 
        {       
            stmt = conn.createStatement();
            String sql;
            
            // USE PREPARED STATEMENTS

            sql = "SELECT groupName, bookTitle, publisherName, yearPublished FROM books";
            ResultSet rs = null;
            rs = stmt.executeQuery(sql);
            System.out.printf(displayFormat, "groupName", "bookTitle", "publisherName", "yearPublished");

            while (rs.next()) {
                //Retrieve by column name
                String groupName = rs.getString("groupName");
                String bookTitle = rs.getString("bookTitle");
                String publisherName = rs.getString("publisherName");
                String yearPublished = rs.getString("yearPublished");
                System.out.printf(displayFormat, dispNull(groupName), dispNull(bookTitle), dispNull(publisherName), dispNull(yearPublished));
            }
            rs.close();
            stmt.close();
        }
        catch (SQLException se) {se.printStackTrace();} 
        catch (Exception e) {e.printStackTrace();} 
        finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) { }
        }
    }
    public static void LIST_RESULTS(Connection conn, String tableName, String[] attributeNames)
    {
        Statement stmt = null;
        try 
        {       
            stmt = conn.createStatement();
            String sql;
            
            // USE PREPARED STATEMENTS

            sql = "SELECT ";
            for (int i = 0; i < attributeNames.length - 1; i++) 
            {
                sql += attributeNames[i] + ", ";
            }
            sql += attributeNames[attributeNames.length - 1];
            sql += " FROM " + tableName;
            ResultSet rs = null;
            System.out.printf(sql);
            rs = stmt.executeQuery(sql);
            //System.out.printf(displayFormat, "groupName", "bookTitle", "publisherName", "yearPublished");

            while (rs.next()) 
            {
                String output = "";
                for (int i = 0; i < attributeNames.length; i++) 
                {
                    output += rs.getString(attributeNames[i]) + ", ";
                }
                System.out.printf(output+"\n");
                //Retrieve by column name
//                String groupName = rs.getString("groupName");
//                String bookTitle = rs.getString("bookTitle");
//                String publisherName = rs.getString("publisherName");
//                String yearPublished = rs.getString("yearPublished");
//                System.out.printf(displayFormat, dispNull(groupName), dispNull(bookTitle), dispNull(publisherName), dispNull(yearPublished));
            }
            rs.close();
            stmt.close();
        }
        catch (SQLException se) {se.printStackTrace();} 
        catch (Exception e) {e.printStackTrace();} 
        finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) { }
        }
    }
    public static void INSERT_ROW(Connection conn, String tableName, String[] attributeNames, String[] valueNames)
    {
        Statement stmt = null;
        try 
        {       
            stmt = conn.createStatement();
            String sql;
            
            // USE PREPARED STATEMENTS

            sql = "INSERT INTO " + tableName + " (";
            for (int i = 0; i < attributeNames.length - 1; i++) 
            {
                sql += attributeNames[i] + ", ";
            }
            sql += attributeNames[attributeNames.length - 1] + ")";
            sql += " VALUES (";
            for (int i = 0; i < valueNames.length - 1; i++) 
            {
                sql += "'" + valueNames[i] + "'" + ", ";
            }
            sql += "'" +valueNames[valueNames.length - 1] + "')";
            
            //ResultSet rs = null;
            System.out.printf(sql);
            //rs = 
            //stmt.
            stmt.execute(sql);
            //System.out.printf(displayFormat, "groupName", "bookTitle", "publisherName", "yearPublished");

//            while (rs.next()) 
//            {
//                String output = "";
//                for (int i = 0; i < attributeNames.length; i++) 
//                {
//                    output += rs.getString(attributeNames[i]) + ", ";
//                }
//                System.out.printf(output+"\n");
//            }
            //rs.close();
            stmt.close();
        }
        catch (SQLException se) {se.printStackTrace();} 
        catch (Exception e) {e.printStackTrace();} 
        finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) { }
        }
    }
}
