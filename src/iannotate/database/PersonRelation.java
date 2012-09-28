/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iannotate.database;

import iannotate.utility.FileUtil;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sameer
 */
public class PersonRelation implements PersonRelationInterface{
    Connection connection = null;
    ResultSet resultSet = null;
    
    PersonRelation(String dbName){
        String DATABASE_URL = "jdbc:mysql://localhost/" + dbName;
    
    try{ 
           connection = DriverManager.getConnection(DATABASE_URL, "root" , null );
       }//end try
       catch(SQLException sqlE)
        {
            errorMessage(sqlE);
        } //end catch
    }
    private static void errorMessage(SQLException sqlE) {
        
        System.err.println("SQLException :" + sqlE.getMessage());
        System.err.println("SQLState :" + sqlE.getSQLState());
        System.err.println("VendorError :"+ sqlE.getErrorCode());
    
    }//end errorMessage
    @Override
    public boolean addPerson(String faintName, String fullName, String relation, String phoneNumber) {
        switch(relation){
            case "me":
                break;
            default:
                try {
                    String currentUser = FileUtil.getSession();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(PersonRelation.class.getName()).log(Level.SEVERE, null, ex);
                }catch(IOException ioe){}
                
        }
        
        return true;
        
    }

       
}
