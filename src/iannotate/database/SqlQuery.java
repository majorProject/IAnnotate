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
public class SqlQuery implements DbInterface {
     Connection connection = null;
    ResultSet resultSet = null;
    
    /**
     * constructor with parameter DbName = name of the database
     * @param DbName 
     */
    public SqlQuery( String DbName ){
        
    //Connection connection = null;
    //ResultSet resultSet = null;
    String DATABASE_URL = "jdbc:mysql://localhost/" + DbName;
    
    try{ 
           connection = DriverManager.getConnection(DATABASE_URL, "root" , null );
       }//end try
       catch(SQLException sqlE)
        {
            errorMessage(sqlE);
        } //end catch
    
    }//end constructor
    
    private static void errorMessage(SQLException sqlE) {
        
        System.err.println("SQLException :" + sqlE.getMessage());
        System.err.println("SQLState :" + sqlE.getSQLState());
        System.err.println("VendorError :"+ sqlE.getErrorCode());
    
    }//end errorMessage
    
    
    
     //method insert
    @Override
    public boolean insertInto( String userName, String passWord, int age, String sex, String contact ) throws SQLException{
       PreparedStatement insert = connection.prepareStatement(
            "INSERT INTO user( username, password, age , sex , contact)" +
            "VALUES(?, ?, ? , ? , ? )");
       
       insert.setString( 1, userName );
       insert.setString( 2, passWord );
       insert.setInt( 3, age );
       insert.setString( 4, sex );
       insert.setString( 5, contact );
       
     boolean r = insert.execute();
     return r;
    
    }// end insertInto
    
    //method update
    @Override
    public boolean update(String newUserName, String contact, String oldUserName  ) throws SQLException{
        PreparedStatement updatus = connection.prepareStatement(
            "UPDATE user SET username = ? , contact = ? WHERE username = ? ");
        
        updatus.setString( 1, newUserName );
        updatus.setString( 2, contact );
        updatus.setString( 3, oldUserName );
        
    boolean r = updatus.execute();
    return r; 
    }//end update
    
   //method delete
    @Override
    public boolean delete( String userName ) throws SQLException{
            PreparedStatement deletus = connection.prepareStatement(
            "DELETE FROM user "+
            "WHERE username = ?");
    deletus.setString( 1, userName );    
    boolean r = deletus.execute();
    return r; 
    }//end delete
    
    
    //method fetch
    @Override
    public void fetch( String userName ) throws SQLException{
        
        PreparedStatement fetchus = connection.prepareStatement(
            "SELECT * FROM user WHERE username = ? ");
        fetchus.setString( 1, userName);
         // boolean r = fetch.execute();
     resultSet = fetchus.executeQuery();
     while(resultSet.next()){
     System.out.println(resultSet.getString(1));
     System.out.println(resultSet.getString(2));
     System.out.println(resultSet.getString(3));
     System.out.println(resultSet.getString(4));
     System.out.println(resultSet.getString(5));
     }
     fetchus.close();
    }//end fetch
    
    
    
     
    
}//end SqlQuery
