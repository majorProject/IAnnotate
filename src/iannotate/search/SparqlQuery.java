/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iannotate.search;

import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sameer
 */
public class SparqlQuery {

    public static void searchPerson(String path, String name) throws IOException {
        //open the rdf
        // FilePermission prem = new java.io.FilePermission(".\\rdfs\\", "read");
        File rdf = new File(path);
        Model model;
        InputStream in = new FileInputStream(rdf);
        model = ModelFactory.createMemModelMaker().createModel(null);
        model.read(in, null);

        // Create a new query
        String queryString =
        "PREFIX foaf: <http://xmlns.com/foaf/0.1/> " +
        "PREFIX EXIF: <http://www.w3.org/2003/12/exif/ns#> " +
        "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
        "SELECT ?node ?w ?h " +
        "WHERE {" +
           
        //" ?n foaf:thumbnail ?node . " +
        " ?node foaf:name \""+name+ "\" . " +   
        " ?node foaf:thumbnail ?t . "
                + " ?t EXIF:imageWidth ?iw ."
                + " ?t EXIF:imageLength ?il ."
                + " ?t EXIF:height ?h ."
                + " ?t EXIF:width ?w ." +      
        " }";


        Query query = QueryFactory.create(queryString);
        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet results = qe.execSelect();

        try {

            while (results.hasNext()) {
                QuerySolution soln = results.nextSolution();
                System.out.println(soln.get("h"));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        // Important - free up resources used running the query
        qe.close();
    }
    
    public static void main(String[] args) throws IOException {
        searchPerson(".\\rdfs\\test2.rdf","Sameer");
    }
}
