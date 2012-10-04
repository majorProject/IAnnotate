/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iannotate.utility;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;
import com.hp.hpl.jena.vocabulary.DC_11;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.VCARD;
import java.io.*;
import myclass.EXIF;
import myclass.Ical;
import myclass.Relationship;

/**
 *
 * @author RAJAN
 */
public class WriteRdf {

    public static void writerdf(String filename, String fname,
            String lname, String width, String height, String imageWidth, String imageHeight, String phone, String info) {

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
                    model.createResource().addProperty(EXIF.height, height).addProperty(EXIF.width, width).addProperty(EXIF.imageLength, imageHeight).addProperty(EXIF.imageWidth, imageWidth)).addProperty(FOAF.phone, phone).addProperty(FOAF.surname, lname).addProperty(FOAF.name, fname));




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
                    model.createResource().addProperty(EXIF.height, height).addProperty(EXIF.width, width).addProperty(EXIF.imageLength, imageHeight).addProperty(EXIF.imageWidth, imageWidth)).addProperty(FOAF.phone, phone).addProperty(FOAF.surname, lname).addProperty(FOAF.name, fname));



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
        Model modelSecond = ModelFactory.createDefaultModel();        

        // find the input file
        InputStream in = null;
        InputStream inSecond = null;
        try {
            in = new FileInputStream(filename);
        } catch (IOException e) {
        }
        String secondFilename = "imagedb/rdf/user/" + name + ".rdf";
        try {
            inSecond = new FileInputStream(secondFilename);
        } catch (IOException e) {
        }
        if (in == null) {
            // set a namespace prefix
            model.setNsPrefix("foaf", FOAF.NS);
            model.setNsPrefix("EXIF", EXIF.NS);
            model.setNsPrefix("relation", Relationship.NS);

            // create a contributor
            Resource root = model.createResource("http://something.com");
//            Resource ro3otSecond = model.createResource("http://something.com");

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
//                    rootSecond.addProperty(Relationship.parentOf, username);
                    break;
                case "employerOf":
                    root.addProperty(Relationship.employerOf, name);
                    break;
                case "enemyOf":
                    root.addProperty(Relationship.enemyOf, name);
                    break;
                case "grandchildOf":
                    root.addProperty(Relationship.grandchildOf, name);
//                    rootSecond.addProperty(Relationship.grandparentOf, username);
                    break;
                case "grandparentOf":
                    root.addProperty(Relationship.grandparentOf, name);
//                    rootSecond.addProperty(Relationship.grandchildOf, username);
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
//                    rootSecond.addProperty(Relationship.friendOf, username);
                    break;
                case "colleagueOf":
                    root.addProperty(Relationship.colleagueOf, name);
                    break;
                case "spouseOf":
                    root.addProperty(Relationship.spouseOf, name);
//                    rootSecond.addProperty(Relationship.spouseOf, username);
                    break;
                case "closeFriendOf":
                    root.addProperty(Relationship.closeFriendOf, name);
                    break;
                case "livesWith":
                    root.addProperty(Relationship.livesWith, name);
//                    rootSecond.addProperty(Relationship.livesWith, username);
                    break;
                case "neighborOf":
                    root.addProperty(Relationship.neighborOf, name);
//                    rootSecond.addProperty(Relationship.neighborOf, username);
                    break;
                case "siblingOf":
                    root.addProperty(Relationship.siblingOf, name);
//                    rootSecond.addProperty(Relationship.siblingOf, username);
                    break;
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
                    break;
                case "colleagueOf":
                    root.addProperty(Relationship.colleagueOf, name);
                    break;
                case "spouseOf":
                    root.addProperty(Relationship.spouseOf, name);
                    break;
                case "closeFriendOf":
                    root.addProperty(Relationship.closeFriendOf, name);
                    break;
                case "livesWith":
                    root.addProperty(Relationship.livesWith, name);
                    break;
                case "neighborOf":
                    root.addProperty(Relationship.neighborOf, name);
                    break;
                case "siblingOf":
                    root.addProperty(Relationship.siblingOf, name);
                    break;


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
        
        if (inSecond == null) {
            // set a namespace prefix
            modelSecond.setNsPrefix("foaf", FOAF.NS);
            modelSecond.setNsPrefix("EXIF", EXIF.NS);
            modelSecond.setNsPrefix("relation", Relationship.NS);

            // create a contributor
//            Resource root = model.createResource("http://something.com");
            Resource rootSecond = modelSecond.createResource("http://something.com");

            rootSecond.addProperty(RDF.type, FOAF.Person);

            rootSecond.addProperty(FOAF.name, name);
            switch (relation) {
                case "ambivalentOf":
//                    root.addProperty(Relationship.ambivalentOf, name);
                    break;
                case "antagonistOf":
//                    root.addProperty(Relationship.antagonistOf, name);
                    break;
                case "apprenticeTo":
//                    root.addProperty(Relationship.apprenticeTo, name);
                    break;
                case "childOf":
//                    root.addProperty(Relationship.childOf, name);
                    rootSecond.addProperty(Relationship.parentOf, username);
                    break;
                case "employerOf":
//                    root.addProperty(Relationship.employerOf, name);
                    break;
                case "enemyOf":
//                    root.addProperty(Relationship.enemyOf, name);
                    break;
                case "grandchildOf":
//                    root.addProperty(Relationship.grandchildOf, name);
                    rootSecond.addProperty(Relationship.grandparentOf, username);
                    break;
                case "grandparentOf":
//                    root.addProperty(Relationship.grandparentOf, name);
                    rootSecond.addProperty(Relationship.grandchildOf, username);
                    break;
                case "influencedBy":
//                    root.addProperty(Relationship.influencedBy, name);
                    break;
                case "knowsByReputation":
//                    root.addProperty(Relationship.knowsByReputation, name);
                    break;
                case "knowsInPassing":
//                    root.addProperty(Relationship.knowsInPassing, name);
                    break;
                case "knowsOf":
//                    root.addProperty(Relationship.knowsOf, name);
                    break;
                case "mentorOf":
//                    root.addProperty(Relationship.mentorOf, name);
                    break;
                case "participant":
//                    root.addProperty(Relationship.participant, name);
                    break;
                case "participantIn":
//                    root.addProperty(Relationship.participantIn, name);
                    break;
                case "wouldLikeToknow":
//                    root.addProperty(Relationship.wouldLikeToKnow, name);
                    break;
                case "friendOf":
//                    root.addProperty(Relationship.friendOf, name);
                    rootSecond.addProperty(Relationship.friendOf, username);
                    break;
                case "colleagueOf":
//                    root.addProperty(Relationship.colleagueOf, name);
                    break;
                case "spouseOf":
//                    root.addProperty(Relationship.spouseOf, name);
                    rootSecond.addProperty(Relationship.spouseOf, username);
                    break;
                case "closeFriendOf":
//                    root.addProperty(Relationship.closeFriendOf, name);
                    rootSecond.addProperty(Relationship.closeFriendOf, username);
                    break;
                case "livesWith":
//                    root.addProperty(Relationship.livesWith, name);
                    rootSecond.addProperty(Relationship.livesWith, username);
                    break;
                case "neighborOf":
//                    root.addProperty(Relationship.neighborOf, name);
                    rootSecond.addProperty(Relationship.neighborOf, username);
                    break;
                case "siblingOf":
//                    root.addProperty(Relationship.siblingOf, name);
                    rootSecond.addProperty(Relationship.siblingOf, username);
                    break;
            }

            // write the RDF model to the console as RDF/XML
            modelSecond.write(System.out, "RDF/XML-ABBREV");

            try {
                FileOutputStream fout = new FileOutputStream(secondFilename);
                modelSecond.write(fout);
            } catch (FileNotFoundException e) {
                System.out.println("error:" + e);
            }
        } else {
            // read the RDF/XML file
            modelSecond.read(new InputStreamReader(inSecond), "");

            // retrieve resource from the model
            Resource rootSecond = modelSecond.getResource("http://something.com");

            switch (relation) {
                case "ambivalentOf":
//                    root.addProperty(Relationship.ambivalentOf, name);
                    break;
                case "antagonistOf":
//                    root.addProperty(Relationship.antagonistOf, name);
                    break;
                case "apprenticeTo":
//                    root.addProperty(Relationship.apprenticeTo, name);
                    break;
                case "childOf":
//                    root.addProperty(Relationship.childOf, name);
                    rootSecond.addProperty(Relationship.parentOf, username);
                    break;
                case "employerOf":
//                    root.addProperty(Relationship.employerOf, name);
                    break;
                case "enemyOf":
//                    root.addProperty(Relationship.enemyOf, name);
                    break;
                case "grandchildOf":
//                    root.addProperty(Relationship.grandchildOf, name);
                    rootSecond.addProperty(Relationship.grandparentOf, username);
                    break;
                case "grandparentOf":
//                    root.addProperty(Relationship.grandparentOf, name);
                    rootSecond.addProperty(Relationship.grandchildOf, username);
                    break;
                case "influencedBy":
//                    root.addProperty(Relationship.influencedBy, name);
                    break;
                case "knowsByReputation":
//                    root.addProperty(Relationship.knowsByReputation, name);
                    break;
                case "knowsInPassing":
//                    root.addProperty(Relationship.knowsInPassing, name);
                    break;
                case "knowsOf":
//                    root.addProperty(Relationship.knowsOf, name);
                    break;
                case "mentorOf":
//                    root.addProperty(Relationship.mentorOf, name);
                    break;
                case "participant":
//                    root.addProperty(Relationship.participant, name);
                    break;
                case "participantIn":
//                    root.addProperty(Relationship.participantIn, name);
                    break;
                case "wouldLikeToknow":
//                    root.addProperty(Relationship.wouldLikeToKnow, name);
                    break;
                case "friendOf":
//                    root.addProperty(Relationship.friendOf, name);
                    rootSecond.addProperty(Relationship.friendOf, username);
                    break;
                case "colleagueOf":
//                    root.addProperty(Relationship.colleagueOf, name);
                    break;
                case "spouseOf":
//                    root.addProperty(Relationship.spouseOf, name);
                    rootSecond.addProperty(Relationship.spouseOf, username);
                    break;
                case "closeFriendOf":
//                    root.addProperty(Relationship.closeFriendOf, name);
                    rootSecond.addProperty(Relationship.closeFriendOf, username);
                    break;
                case "livesWith":
//                    root.addProperty(Relationship.livesWith, name);
                    rootSecond.addProperty(Relationship.livesWith, username);
                    break;
                case "neighborOf":
//                    root.addProperty(Relationship.neighborOf, name);
                    rootSecond.addProperty(Relationship.neighborOf, username);
                    break;
                case "siblingOf":
//                    root.addProperty(Relationship.siblingOf, name);
                    rootSecond.addProperty(Relationship.siblingOf, username);
                    break;
            }

            // write the RDF model to the console as RDF/XML
            modelSecond.write(System.out, "RDF/XML-ABBREV");

            try {
                FileOutputStream fout = new FileOutputStream(secondFilename);
                modelSecond.write(fout);
            } catch (FileNotFoundException e) {
                System.out.println("error:" + e);
            }
        }
        
    }

    public static void writerdfD(String filename, String info, String location) {
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

            root.addProperty(EXIF.imageDescription, info).addProperty(VCARD.Locality, location);

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

            //root.addProperty(VCARD.Locality, "aa");
            
            root.addProperty(EXIF.imageDescription, info).addProperty(VCARD.Locality, location);


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
//        writerdfR("imagedb/rdf/user/abc.rdf","abc", "xyz", "friendOf");
        writerdfD("rdf/date1.rdf","Description about the image and the something about the something","kathmandu");
    }
}
