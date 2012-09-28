/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iannotate.database;

/**
 *
 * @author Susan
 * @version 1.0
 */
//This is a class developed for user-defined class for linked list and other Collection purposes
public class PersonWithRelation {
    private String personFaintName;
    private String personFullName;
    private String personRelation;
    private String personPhoneNumber;
    private String imageFileName;
    
    PersonWithRelation(String faintName, String fullName, String relation, String phoneNumber, String imageFName ){
        personFaintName = faintName;
        personFullName = fullName;
        personRelation = relation;
        personPhoneNumber = phoneNumber;
        imageFileName = imageFName;
    }
    
    public String getPersonFaintName(){
        return personFaintName;
    }
    public String getPersonFullName(){
        return personFullName;
    }
    public String getPersonRelation(){
        return personRelation;
    }
    public String getPersonPhoneNumber(){
        return personPhoneNumber;
    }
    public String getImageFileName(){
        return imageFileName;
    }
}
