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
    public static void LIST_RESULTS_ALL(Connection conn, String tableName)
    {
        Statement stmt = null;
        try 
        {       
            stmt = conn.createStatement();
            String sql;
            
            // ToDo: USE PREPARED STATEMENTS

            sql = "SELECT * FROM " + tableName;

            System.out.printf(sql + "\n");
            
            ResultSet rs = null;
            rs = stmt.executeQuery(sql);
            
            int columnCount = rs.getMetaData().getColumnCount();
            while (rs.next()) 
            {
                String output = "";
                for (int i = 1; i <= columnCount; i++) 
                {
                    output += rs.getString(rs.getMetaData().getColumnName(i)) + ", ";
                }
                System.out.printf(output+"\n");
                
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
            
            // ToDo: USE PREPARED STATEMENTS

            sql = "SELECT ";
            for (int i = 0; i < attributeNames.length - 1; i++) 
            {
                sql += attributeNames[i] + ", ";
            }
            sql += attributeNames[attributeNames.length - 1];
            sql += " FROM " + tableName;
            
            System.out.printf(sql + "\n");
            
            ResultSet rs = null;
            rs = stmt.executeQuery(sql);
            

            while (rs.next()) 
            {
                String output = "";
                for (int i = 0; i < attributeNames.length; i++) 
                {
                    output += rs.getString(attributeNames[i]) + ", ";
                }
                System.out.printf(output+"\n");
                
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
    public static void LIST_RESULTS(Connection conn, String tableName, String[] attributeNames, String compareAttribute, String compareTarget)
    {
        String sql = null;
        PreparedStatement stmt = null;
        if (tableName=="WritingGroups"){
            sql = "SELECT * FROM WritingGroups WHERE groupName = ? ";
        }else if(tableName=="publishers"){
            sql = "SELECT * FROM publishers WHERE publisherName = ? ";
        }else if(tableName=="books"){
            sql = "SELECT * FROM books WHERE bookTitle = ? ";
        }   
        try 
        {       
            stmt = conn.prepareStatement(sql);
            //stmt.setString(1, tableName);
            //stmt.setString(1, compareAttribute);
            stmt.setString(1, compareTarget);

//            sql = "SELECT ";
//            for (int i = 0; i < attributeNames.length - 1; i++) 
//            {
//                sql += attributeNames[i] + ", ";
//            }
//            sql += attributeNames[attributeNames.length - 1];
//            sql += " FROM " + tableName;
//            
//            //ToDo: ADD WHERE CLAUSE
//            sql += " WHERE " + compareAttribute + "='" + compareTarget+"'";
//            
//            System.out.printf(sql + "\n");
//            
            ResultSet rs = stmt.executeQuery();
            

            while (rs.next()) 
            {
                String output = "";
                for (int i = 0; i < attributeNames.length; i++) 
                {
                    output += rs.getString(attributeNames[i]) + ", ";
                }
                System.out.printf(output+"\n");
                
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
            
            // ToDo: USE PREPARED STATEMENTS

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

            System.out.printf(sql + "\n");
            Boolean result;
            try
            {
                result = stmt.execute(sql);
            }
            catch(SQLException se)
            {
                System.out.printf("\n" + se.getMessage()+ "\n");
                //System.out.printf("Exception found at Insert SQL: " + se.getSQLState() + "\n");
//                if(se.getSQLState().equals("23505")) //23505
//                {
//                    System.out.printf("Duplicate Key\n");
//                }
            }

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
    
    public static void INSERT_BOOK(Connection conn, String tableName, String[] attributeNames, String[] valueNames)
    {
        String sql = "INSERT INTO books VALUES(?,?,?,?,?)";
        PreparedStatement stmt = null;
        try 
        {       
            stmt = conn.prepareStatement(sql);
            //stmt.setString(1, tableName);
            //for(int i=2; i-7<attributeNames.length-1; i++){
                //stmt.setString(i, attributeNames[i-2]);}
            for(int i=0; i<valueNames.length; i++){
                if( tableName=="books" && i>2){
                     int strVal = Integer.parseInt(valueNames[i]);
                     stmt.setInt(i+1, strVal);
                 }else{
                    stmt.setString(i+1, valueNames[i]);
                 }
            }
            try
            {
                int i = stmt.executeUpdate();
                System.out.println(i+" record(s) affected");
            }
            catch(SQLException se)
            {
                System.out.printf("\n" + se.getMessage()+ "\n");
                //System.out.printf("Exception found at Insert SQL: " + se.getSQLState() + "\n");
//                if(se.getSQLState().equals("23505")) //23505
//                {
//                    System.out.printf("Duplicate Key\n");
//                }
            }

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
