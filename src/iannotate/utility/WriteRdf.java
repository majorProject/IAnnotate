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

/**
 *
 * @author RAJAN
 */
public class WriteRdf {

    public static void writerdf(String filename, String fname,
            String lname, String x, String y, String info) {

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

            // create a contributor
            Resource root = model.createResource();
            Resource first = model.createResource();

            root.addProperty(RDF.type, FOAF.Image);
            root.addProperty(FOAF.thumbnail, first);

            first.addProperty(FOAF.thumbnail, model.createResource().addProperty(EXIF.height, x).addProperty(EXIF.width, y).addProperty(EXIF.imageLength, "30").addProperty(EXIF.imageWidth, "20"));
            first.addProperty(FOAF.surname, lname).addProperty(FOAF.name, fname);
            
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

            // retrieve the Adam Smith vcard resource from the model
            Resource node = model.getResource("http://something.com");

            Resource first = model.createResource("http://something.com");
            node.addProperty(FOAF.thumbnail, first);

            first.addProperty(FOAF.thumbnail, model.createResource().addProperty(EXIF.height, "20").addProperty(EXIF.width, "10").addProperty(EXIF.imageLength, "30").addProperty(EXIF.imageWidth, "20"));
            first.addProperty(FOAF.surname, "lname").addProperty(FOAF.name, "fname");



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
        writerdf("rdf//test2.rdf", "samer", "Dangol", "60", "100", "info");
    }
}
