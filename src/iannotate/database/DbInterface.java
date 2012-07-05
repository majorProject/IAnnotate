/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iannotate.database;

import java.sql.SQLException;

/**
 *
 * @author Sameer
 */
public interface DbInterface {
  public boolean insertInto( String userName, String passWord, int age, String sex, String contact ) throws SQLException;
  public boolean update(String newUserName, String contact, String oldUserName  ) throws SQLException;
  public boolean delete( String userName ) throws SQLException;
  public void fetch( String userName ) throws SQLException;
}
