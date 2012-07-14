/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iannotate.database;

import java.sql.*;

/**
 *
 * @author Sameer
 */
public class DatabaseConnector {
    
    
        
      public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        DbInterface[] db = new DbInterface[4];
        db[0] = new SqlQuery( "major_project" );
        
        
        
        //establish connection with the database
        // db[0].insertInto( "sammmmeee","dsadasd" , 23, "M", "32423432");
         //db[0].delete("rajan");
        // db[0].update( "sameer", "9841377741", "sammmmeee" );
          System.out.println(
          db[0].fetch("sameer").getString(1));
       System.out.println(
          db[0].fetch("sameer").getString(2));
  
       

        
    }//end main
    
  
   
 

}//end DatabaseConnector
