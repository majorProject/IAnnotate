/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iannotate.utility;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;
import com.hp.hpl.jena.vocabulary.RDF;
import java.io.*;
import myclass.EXIF;
import myclass.Relationship;

/**
 *
 * @author RAJAN
 */
public class WriteRdf {

    public static void writerdf(String filename, String fname,
            String lname, String x, String y, String phone, String info) {

        // create an empty Model
        Model model = ModelFactory.createDefaultModel();

        // find the input file
        InputStream in = null;
        try {
            in = new FileInputStream(filename);
        } catch (IOException e) {
        }

        if (in == null) {
            // set a namespace prefix
            model.setNsPrefix("foaf", FOAF.NS);
            model.setNsPrefix("EXIF", EXIF.NS);
            model.setNsPrefix("relation", Relationship.NS);

            // create a contributor
            Resource root = model.createResource("http://something.com");

            root.addProperty(RDF.type, FOAF.Image);
            root.addProperty(FOAF.thumbnail, model.createResource().addProperty(FOAF.thumbnail,
                    model.createResource().addProperty(EXIF.height, x).addProperty(EXIF.width, y).addProperty(EXIF.imageLength, "30").addProperty(EXIF.imageWidth, "20")).addProperty(FOAF.phone, phone).addProperty(FOAF.surname, lname).addProperty(FOAF.name, fname));




            // write the RDF model to the console as RDF/XML
            model.write(System.out, "RDF/XML-ABBREV");
            try {
                FileOutputStream fout = new FileOutputStream(filename);
                model.write(fout);
            } catch (FileNotFoundException e) {
                System.out.println("error:" + e);
            }
        } else {

            // read the RDF/XML file
            model.read(new InputStreamReader(in), "");

            // retrieve resource from the model
            Resource root = model.getResource("http://something.com");

            root.addProperty(RDF.type, FOAF.Image);
            root.addProperty(FOAF.thumbnail, model.createResource().addProperty(FOAF.thumbnail,
                    model.createResource().addProperty(EXIF.height, x).addProperty(EXIF.width, y).addProperty(EXIF.imageLength, "30").addProperty(EXIF.imageWidth, "20")).addProperty(FOAF.surname, lname).addProperty(FOAF.name, fname));



            // write the RDF model to the console as RDF/XML
            model.write(System.out, "RDF/XML-ABBREV");
            try {
                FileOutputStream fout = new FileOutputStream(filename);
                model.write(fout);
            } catch (FileNotFoundException e) {
                System.out.println("error:" + e);
            }
        }
    }

    public static void writerdfR(String filename, String username, String name, String relation) {
        // create an empty Model
        Model model = ModelFactory.createDefaultModel();

        // find the input file
        InputStream in = null;
        try {
            in = new FileInputStream(filename);
        } catch (IOException e) {
        }
        if (in == null) {
            // set a namespace prefix
            model.setNsPrefix("foaf", FOAF.NS);
            model.setNsPrefix("EXIF", EXIF.NS);
            model.setNsPrefix("relation", Relationship.NS);

            // create a contributor
            Resource root = model.createResource("http://something.com");

            root.addProperty(RDF.type, FOAF.Person);

            root.addProperty(FOAF.name, username);
            switch (relation) {
                case "ambivalentOf":
                    root.addProperty(Relationship.ambivalentOf, name);
                    break;
                case "antagonistOf":
                    root.addProperty(Relationship.antagonistOf, name);
                    break;
                case "apprenticeTo":
                    root.addProperty(Relationship.apprenticeTo, name);
                    break;
                case "childOf":
                    root.addProperty(Relationship.childOf, name);
                    break;
                case "employerOf":
                    root.addProperty(Relationship.employerOf, name);
                    break;
                case "enemyOf":
                    root.addProperty(Relationship.enemyOf, name);
                    break;
                case "grandchildOf":
                    root.addProperty(Relationship.grandchildOf, name);
                    break;
                case "grandparentOf":
                    root.addProperty(Relationship.grandparentOf, name);
                    break;
                case "influencedBy":
                    root.addProperty(Relationship.influencedBy, name);
                    break;
                case "knowsByReputation":
                    root.addProperty(Relationship.knowsByReputation, name);
                    break;
                case "knowsInPassing":
                    root.addProperty(Relationship.knowsInPassing, name);
                    break;
                case "knowsOf":
                    root.addProperty(Relationship.knowsOf, name);
                    break;
                case "mentorOf":
                    root.addProperty(Relationship.mentorOf, name);
                    break;
                case "participant":
                    root.addProperty(Relationship.participant, name);
                    break;
                case "participantIn":
                    root.addProperty(Relationship.participantIn, name);
                    break;
                case "wouldLikeToknow":
                    root.addProperty(Relationship.wouldLikeToKnow, name);
                    break;
                case "friendOf":
                    root.addProperty(Relationship.friendOf, name);
                case "colleagueOf":
                    root.addProperty(Relationship.colleagueOf, name);
                case "spouseOf":
                    root.addProperty(Relationship.spouseOf, name);
                case "closeFriendOf":
                    root.addProperty(Relationship.closeFriendOf, name);
                case "livesWith":
                    root.addProperty(Relationship.livesWith, name);
                case "neighborOf":
                    root.addProperty(Relationship.neighborOf, name);
                case "siblingOf":
                    root.addProperty(Relationship.siblingOf, name);


            }

            // write the RDF model to the console as RDF/XML
            model.write(System.out, "RDF/XML-ABBREV");

            try {
                FileOutputStream fout = new FileOutputStream(filename);
                model.write(fout);
            } catch (FileNotFoundException e) {
                System.out.println("error:" + e);
            }
        } else {
            // read the RDF/XML file
            model.read(new InputStreamReader(in), "");

            // retrieve resource from the model
            Resource root = model.getResource("http://something.com");

            switch (relation) {
                case "ambivalentOf":
                    root.addProperty(Relationship.ambivalentOf, name);
                    break;
                case "antagonistOf":
                    root.addProperty(Relationship.antagonistOf, name);
                    break;
                case "apprenticeTo":
                    root.addProperty(Relationship.apprenticeTo, name);
                    break;
                case "childOf":
                    root.addProperty(Relationship.childOf, name);
                    break;
                case "employerOf":
                    root.addProperty(Relationship.employerOf, name);
                    break;
                case "enemyOf":
                    root.addProperty(Relationship.enemyOf, name);
                    break;
                case "grandchildOf":
                    root.addProperty(Relationship.grandchildOf, name);
                    break;
                case "grandparentOf":
                    root.addProperty(Relationship.grandparentOf, name);
                    break;
                case "influencedBy":
                    root.addProperty(Relationship.influencedBy, name);
                    break;
                case "knowsByReputation":
                    root.addProperty(Relationship.knowsByReputation, name);
                    break;
                case "knowsInPassing":
                    root.addProperty(Relationship.knowsInPassing, name);
                    break;
                case "knowsOf":
                    root.addProperty(Relationship.knowsOf, name);
                    break;
                case "mentorOf":
                    root.addProperty(Relationship.mentorOf, name);
                    break;
                case "participant":
                    root.addProperty(Relationship.participant, name);
                    break;
                case "participantIn":
                    root.addProperty(Relationship.participantIn, name);
                    break;
                case "wouldLikeToknow":
                    root.addProperty(Relationship.wouldLikeToKnow, name);
                    break;
                case "friendOf":
                    root.addProperty(Relationship.friendOf, name);
                case "colleagueOf":
                    root.addProperty(Relationship.colleagueOf, name);
                case "spouseOf":
                    root.addProperty(Relationship.spouseOf, name);
                case "closeFriendOf":
                    root.addProperty(Relationship.closeFriendOf, name);
                case "livesWith":
                    root.addProperty(Relationship.livesWith, name);
                case "neighborOf":
                    root.addProperty(Relationship.neighborOf, name);
                case "siblingOf":
                    root.addProperty(Relationship.siblingOf, name);


            }

            // write the RDF model to the console as RDF/XML
            model.write(System.out, "RDF/XML-ABBREV");

            try {
                FileOutputStream fout = new FileOutputStream(filename);
                model.write(fout);
            } catch (FileNotFoundException e) {
                System.out.println("error:" + e);
            }
        }
    }

    public static void writerdfD(String filename, String info, String date) {
        // create an empty Model
        Model model = ModelFactory.createDefaultModel();

        // find the input file
        InputStream in = null;
        try {
            in = new FileInputStream(filename);
        } catch (IOException e) {
        }

        if (in == null) {
            // set a namespace prefix
            model.setNsPrefix("foaf", FOAF.NS);
            model.setNsPrefix("EXIF", EXIF.NS);
            model.setNsPrefix("relation", Relationship.NS);

            // create a contributor
            Resource root = model.createResource("http://something.com");

            root.addProperty(EXIF.imageDescription, info).addProperty(EXIF.date, date);

            // write the RDF model to the console as RDF/XML
            model.write(System.out, "RDF/XML-ABBREV");
            try {
                FileOutputStream fout = new FileOutputStream(filename);
                model.write(fout);
            } catch (FileNotFoundException e) {
                System.out.println("error:" + e);
            }
        } else {

            // read the RDF/XML file
            model.read(new InputStreamReader(in), "");

            // retrieve resource from the model
            Resource root = model.getResource("http://something.com");

            root.addProperty(EXIF.imageDescription, info);


            // write the RDF model to the console as RDF/XML
            model.write(System.out, "RDF/XML-ABBREV");
            try {
                FileOutputStream fout = new FileOutputStream(filename);
                model.write(fout);
            } catch (FileNotFoundException e) {
                System.out.println("error:" + e);
            }
        }
    }

    public static void main(String[] args) {
        //writerdf("rdf/test2.rdf", "rajan", "maharjan", "60", "100", "info");
        //writerdfR("rajan", "susan", "friendOf");
        writerdfD("rdf/date1.rdf","Description about the image and the something about the something","01-02-2011");
    }
}
