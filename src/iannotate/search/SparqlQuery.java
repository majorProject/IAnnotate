/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iannotate.search;

import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import iannotate.database.SearchPersonClass;
import iannotate.utility.FileExtensionFilter;
import java.io.*;
import java.util.LinkedList;
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

        //LinkedList for the search Result
        LinkedList<SearchPersonClass> searchList = new LinkedList<>();
        // Create a new query
        String queryString =
                "PREFIX foaf: <http://xmlns.com/foaf/0.1/> "
                + "PREFIX EXIF: <http://www.w3.org/2003/12/exif/ns#> "
                + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
                + "SELECT ?node ?w ?h ?iw ?il "
                + "WHERE {"
                + //" ?n foaf:thumbnail ?node . " +
                " ?node foaf:name \"" + name + "\" . "
                + " ?node foaf:thumbnail ?t . "
                + " ?t EXIF:imageWidth ?iw ."
                + " ?t EXIF:imageLength ?il ."
                + " ?t EXIF:height ?h ."
                + " ?t EXIF:width ?w ."
                + " }";


        Query query = QueryFactory.create(queryString);
        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet results = qe.execSelect();

        try {

            while (results.hasNext()) {
                QuerySolution soln = results.nextSolution();
                System.out.println(soln.get("h").toString());
                System.out.println(soln.get("w").toString());
                System.out.println(soln.get("il").toString());
                System.out.println(soln.get("iw").toString());

                String imageLength = soln.get("il").toString();
                String imageWidth = soln.get("iw").toString();
                String height = soln.get("h").toString();
                String width = soln.get("w").toString();
                String filename = new File(path).getName();
                searchList.add(new SearchPersonClass(filename, imageWidth, imageLength, height, width));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        // Important - free up resources used running the query
        qe.close();
    }

    public static LinkedList searchPersonThroughDirectory(String pathToDirectory, String personName) {
        LinkedList<SearchPersonClass> searchList = new LinkedList<>();
        File f = new File(pathToDirectory);
        FileExtensionFilter fef = new FileExtensionFilter();
        fef.setExtension("rdf");
        String rootPath = f.getAbsolutePath();
        try {
            rootPath = f.getCanonicalPath() + "\\";
        } catch (IOException ioe) {
            System.out.println("IOException found in line 86 on serachPersonThroughDirectory function");
        }
        //System.out.println(rootPath);
        String[] contents = f.list(fef);
        int count = 0;
        for (String content : contents) {

            String path = rootPath + content;
            File rdf = new File(path);
            if (!rdf.exists()) {
                System.out.println(path);
            }
            Model model;
            try {
                InputStream in = new FileInputStream(rdf);

                model = ModelFactory.createMemModelMaker().createModel(null);
                model.read(in, null);

                String queryString =
                        "PREFIX foaf: <http://xmlns.com/foaf/0.1/> "
                        + "PREFIX EXIF: <http://www.w3.org/2003/12/exif/ns#> "
                        + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
                        + "SELECT ?node ?w ?h ?iw ?il "
                        + "WHERE {"
                        + //" ?n foaf:thumbnail ?node . " +
                        " ?node foaf:name \"" + personName + "\" . "
                        + " ?node foaf:thumbnail ?t . "
                        + " ?t EXIF:imageWidth ?iw ."
                        + " ?t EXIF:imageLength ?il ."
                        + " ?t EXIF:height ?h ."
                        + " ?t EXIF:width ?w ."
                        + " }";


                Query query = QueryFactory.create(queryString);
                // Execute the query and obtain results
                QueryExecution qe = QueryExecutionFactory.create(query, model);
                ResultSet results = qe.execSelect();

                while (results.hasNext()) {
                    QuerySolution soln = results.nextSolution();
                    System.out.println(soln.get("h").toString());
                    System.out.println(soln.get("w").toString());
                    System.out.println(soln.get("il").toString());
                    System.out.println(soln.get("iw").toString());
                    System.out.println(rootPath + new File(path).getName());

                    String imageLength = soln.get("il").toString();
                    String imageWidth = soln.get("iw").toString();
                    String height = soln.get("h").toString();
                    String width = soln.get("w").toString();
                    String filename = rootPath + changeFileExt(new File(path).getName());
                    if (!imageLength.isEmpty()) {
                        searchList.add(new SearchPersonClass(filename, imageWidth, imageLength, height, width));
                    }
                    qe.close();
                }
                System.out.println("--------");

            } catch (FileNotFoundException fnfe) {
                System.out.println("file not found Exception");
            }
        } // ---for(String content: contents) ends here---
        return searchList;
    }

    private static String changeFileExt(String str) {
        String string[];
        string = str.split(".rdf");
        System.out.println(string.length);
        string[0] = string[0] + ".jpg";
        return string[0];
    }

    public static void main(String[] args) throws IOException {
        //searchPerson(".\\rdf\\test2.rdf","rajan");
        String pathToDirectory = "imagedb/rdf";
        searchPersonThroughDirectory(pathToDirectory, "Chinese2");
    }
}
