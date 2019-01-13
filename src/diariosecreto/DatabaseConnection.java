/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diariosecreto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.Statement;

/**
 *
 * @author joaom
 */
public class DatabaseConnection {
    
    private Connection dbConn = null;
    
    public DatabaseConnection(String fileName){
        try
        {
            String url = "jdbc:sqlite:"+fileName;
            dbConn = DriverManager.getConnection(url);
        } catch (SQLException e){
            System.out.println(e);
        }
    }
    
    public String hasEntrance(String date, String name){
        String query = "SELECT Entry FROM Entries WHERE userName = '" + name + "' AND Data = '" + date + "';";
        try{
            
            Statement stmt = dbConn.createStatement();
            
            ResultSet res = stmt.executeQuery(query);
            
            return res.getString(1);
            
            
        }catch(SQLException e){
            System.out.println(e);
        }
        
        return null;
    }
    
    public boolean existsInDB(String userName, String Passwd){
        String query = "SELECT COUNT(*) FROM Users WHERE LoginUser = '" + userName + "' AND Passwd = '" + Passwd + "';";
        System.out.println(query);
        try{
            
            Statement stmt = dbConn.createStatement();
            
            ResultSet res = stmt.executeQuery(query);
            
            return (res.getInt(1) == 1);
            
            
        }catch(SQLException e){
            System.out.println(e);
        }
        return false;
    }
    
    public void closeConnection(){
        try{
            dbConn.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        
    }
    
    public String[] getInfo(String userName){
        String queryInformation = "SELECT * FROM Users WHERE LoginUser = '" + userName + "';"; 
        String[] information = new String[6];
        try{
            Statement stmt = dbConn.createStatement();
            ResultSet res = stmt.executeQuery(queryInformation);
            
            if(res == null) return null;
            
            for (int i = 0; i < information.length; i++) {
                information[i] = res.getString(i+1);
            }
            
            return information;
        }catch(SQLException e){
            System.out.println(e);
        }
        
        return null;
        
    }
    
    public boolean insertUser(String userName, String name, String sexo, String Passwd, String fotografia, String dataNasc){
        String insertQuery = "INSERT INTO Users VALUES ('" + name + "', '" + dataNasc + 
                    "', '" + sexo + "', '" + Passwd + "', '" + userName +
                "', '" + fotografia + "');";
        
        try{
            Statement stmt = dbConn.createStatement();
            return stmt.execute(insertQuery);
            
        }catch(SQLException e){
            System.out.println(e);
        }
        return false;
    }
    
    public boolean insertEntry(String data, String entry, String hour, String user, boolean update){
        String SQLQuery;
        if(update)
            SQLQuery = "UPDATE Entries SET Entry = '" + entry + "', Hour = '" + hour + "' WHERE Data = '" + data + "' AND userName ='" + user + "';";
        else
            SQLQuery = "INSERT INTO Entries VALUES ('" + data + "', '" + entry + "', '" + hour + "', '" + user + "');";
        
        try{
            Statement stmt = dbConn.createStatement();
            boolean res = stmt.execute(SQLQuery);
            return res;
            
        }catch(SQLException e){
            System.out.println(e);
        }
        return false;
    }
    
        /*public String getEntry(String data, String user){
        String SQLQuery;
            SQLQuery = "SELECT Entry FROM Entries WHERE Data = '" + data + "' AND userName ='" + user + "';";

        
        try{
            Statement stmt = dbConn.createStatement();
            ResultSet res = stmt.executeQuery(SQLQuery);
            
            if(res == null) return null;
            
            return res.getString(1);
            
        }catch(SQLException e){
            System.out.println(e);
        }
        return null;
    }*/
}
