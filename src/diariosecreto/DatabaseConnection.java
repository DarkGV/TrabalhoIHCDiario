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
}
