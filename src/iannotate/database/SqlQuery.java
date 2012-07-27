/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iannotate.database;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public boolean insertInto( String userName, String passWord, String sex, double contact, String address, String dob ) throws SQLException{
       PreparedStatement insertUser = connection.prepareStatement(
            "INSERT INTO user( username, password )" +
            "VALUES( ?, ? )");
       
       insertUser.setString( 1, userName );
     //  MessageEncryption encrypt = new MessageEncryption();
       String passWordEncrypt = null;
        try {
            passWordEncrypt = MessageEncryption.encryptMD5(passWord);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SqlQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
       insertUser.setString( 2, passWordEncrypt );
       
       boolean r = insertUser.execute();

       int userId = fetch(userName).getInt(1);
       PreparedStatement insertPerson =  connection.prepareStatement(
               "INSERT INTO person( sex , contact, address, dob, user_id) " +
               "VALUES ( ?, ?, ?, ?, ? ) ");
       insertPerson.setString( 1, sex );
       insertPerson.setDouble( 2, contact );
       insertPerson.setString( 3, address );
       insertPerson.setString( 4, dob );
       insertPerson.setInt(5, userId);
     
     boolean r2 = insertPerson.execute();
     return r2;
    
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
    public ResultSet fetch( String userName ) throws SQLException{
        
        PreparedStatement fetchus = connection.prepareStatement(
            "SELECT * FROM user WHERE username = ? ");
        fetchus.setString( 1, userName);
        resultSet = fetchus.executeQuery();
          
     while(resultSet.next()){
      return resultSet;
     }
       
     // resultSet.close();
   //  fetchus.close();
    
     return resultSet;
    }//end fetch
    
   @Override
    public ResultSet fetch() throws SQLException{
        
        PreparedStatement fetchus = connection.prepareStatement(
            "SELECT * FROM user ");
         resultSet = fetchus.executeQuery();
          
     while(resultSet.next()){
      return resultSet;
     }
       
      resultSet.close();
    fetchus.close();
    
     return resultSet;
    }//end fetch  
    
    
    
    @Override
    public boolean authentication(String username, String password) {
        try {
            ResultSet pass = fetch(username);
            if(pass.getString(3).contentEquals(password)){
              return true;}
            else{
              return false;}
        } catch (SQLException ex) {
            Logger.getLogger(SqlQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    
    @Override
    public boolean executeQuery(String sql){
//        ResultSet result = null ;
        boolean execute = false;
        try{
            //result = connection.createStatement().executeQuery(sql);
            execute = connection.createStatement().execute(sql);
        }catch(SQLException slqe){
            System.out.println(slqe);
        }
        return execute;
    }//end queryAny
    
    
//    public static void main(String[] args) {
//     SqlQuery s = new SqlQuery("major_project");   
//     String q = "INSERT INTO test VALUES (macha, sale)";
//     s.executeQuery(q);
//    }
}//end SqlQuery
