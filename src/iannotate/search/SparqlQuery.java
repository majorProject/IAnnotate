/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iannotate.search;

import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Statement;
import iannotate.database.PersonWithRelation;
import iannotate.database.SearchPersonClass;
import iannotate.utility.FileExtensionFilter;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author sameer
 */
public class SparqlQuery {

    public static void searchPerson(String path, String name) throws IOException {
        //open the rdf
       
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
                    String filename = "imagedb" + File.separator + changeFileExt(new File(path).getName());
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

    public static LinkedList searchPersonWithPhone(String pathToDirectory, String personName) {
        LinkedList<PersonWithRelation> searchList = new LinkedList<>();
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
                        + "SELECT ?node ?s ?p "
                        + "WHERE {"
                        + " ?node foaf:name \"" + personName + "\" . "
                        + " ?node foaf:surname ?s ."
                        + " ?node foaf:phone ?p ."
                        + " }";


                Query query = QueryFactory.create(queryString);
                // Execute the query and obtain results
                QueryExecution qe = QueryExecutionFactory.create(query, model);
                ResultSet results = qe.execSelect();

                while (results.hasNext()) {
                    QuerySolution soln = results.nextSolution();

                    System.out.println(soln.get("s").toString());
                    System.out.println(soln.get("p").toString());
                    String surname = soln.get("s").toString();
                    String phone = soln.get("p").toString();
                    String filename = "imagedb" + File.separator + changeFileExt(new File(path).getName());
                    if (!surname.isEmpty() && !phone.isEmpty()) {
                        searchList.add(new PersonWithRelation(personName, surname, "", phone, ""));
                        return searchList;
                    }
                    qe.close();
                }
                System.out.println("--------");

            } catch (FileNotFoundException e) {
                System.out.println("file not found Exception");
            }
        } // ---for(String content: contents) ends here---
        return searchList;
    }

    public static String findRelation(String rdf, String person, String user) throws FileNotFoundException, IOException {

        if(person.toLowerCase().equals(user.toLowerCase())) {
            return "me";
        }
        
        // Open the bloggers RDF graph from the filesystem
        InputStream in = new FileInputStream(new File(rdf));
        // Create an empty in-memory model and populate it from the graph
        Model model = ModelFactory.createMemModelMaker().createModel(null);
        model.read(in, null); // null base URI, since model URIs are absolute
        in.close();

        // Create a new query
        String queryString =
                "PREFIX foaf: <http://xmlns.com/foaf/0.1/> "
                + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
                + "PREFIX EXIF: <http://www.w3.org/2003/12/exif/ns#> "
                + "PREFIX relation: <http://purl.org/vocab/relationship/> "
                + "DESCRIBE ?person "
                + "WHERE {"
                + " ?person foaf:name \"" + user + "\" . "
                + " }";
        Query query = QueryFactory.create(queryString);
        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        Model result = qe.execDescribe();

        //            System.out.println(result);
        // System.out.println(result.listStatements().next().getPredicate().getLocalName());//gives the relation name
        //System.out.println(result.listStatements().next().getObject() + " yeah" );       
        List<Statement> toList = result.listStatements().toList();
        int i = 0;
        String relation = null;
        while (i != toList.size()) {
            relation = toList.get(i).getPredicate().getLocalName();
            String rPerson = toList.get(i).getObject().toString();
            if (rPerson.contentEquals(person)) {
                System.out.println(user + " is " + relation + " " + person);
            }
            i++;

        }
        // Important - free up resources used running the query
        qe.close();

        return relation;
    }
    /**
     * 
     * @param rdfPath
     * @param user
     * @param relation
     * @return LinkedList
     * @throws FileNotFoundException
     * @throws IOException 
     */

    public static LinkedList relateToPerson(String rdfPath,String user,String relation) throws FileNotFoundException, IOException {

        LinkedList<SearchPersonClass> list = new LinkedList<>();
        // Open the bloggers RDF graph from the filesystem
        InputStream in = new FileInputStream(new File(rdfPath));
        // Create an empty in-memory model and populate it from the graph
        Model model = ModelFactory.createMemModelMaker().createModel(null);
        model.read(in, null); // null base URI, since model URIs are absolute
        in.close();

        // Create a new query
        String queryString =
                "PREFIX foaf: <http://xmlns.com/foaf/0.1/> "
                + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
                + "PREFIX EXIF: <http://www.w3.org/2003/12/exif/ns#> "
                + "PREFIX relation: <http://purl.org/vocab/relationship/> "
                + "DESCRIBE ?person "
                + "WHERE {"
                + " ?person foaf:name \"" + user + "\" . "
                + " }";
        Query query = QueryFactory.create(queryString);
// Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        Model result = qe.execDescribe();

//            System.out.println(result);
        // System.out.println(result.listStatements().next().getPredicate().getLocalName());//gives the relation name
        //System.out.println(result.listStatements().next().getObject() + " yeah" );       
        List<Statement> toList = result.listStatements().toList();
        int i = 0;
        String personName = null;
        while (i != toList.size()) {
            String qRelation = toList.get(i).getPredicate().getLocalName();
            personName = toList.get(i).getObject().toString();
            
            
            if (qRelation.contentEquals(relation)) {
                list.add(new SearchPersonClass(personName,"","","",""));
                System.out.println(user + " is " + relation + " " + personName);
            }
            i++;

        }
        // Important - free up resources used running the query
        qe.close();

        return list;

    }

    public static String DateExtractor( String path) throws FileNotFoundException{
        
        InputStream in = new FileInputStream( new File( path ));
        
        Model model = ModelFactory.createMemModelMaker().createModel(null);
        
        model.read( in, null );
        
        String queryString = 
                "PREFIX foaf: <http://xmlns.com/foaf/0.1/> "
                + "PREFIX EXIF: <http://www.w3.org/2003/12/exif/ns#> "
                + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
                + "PREFIX relation: <http://purl.org/vocab/relationship/> "
                + "SELECT ?date "
                + "WHERE { "
                + "?node EXIF:date ?date . }";
        Query query = QueryFactory.create(queryString);
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet result = qe.execSelect();
       
        
        String date = null;
        
        while(result.hasNext()){
            QuerySolution qs = result.nextSolution();
            date = qs.get("date").toString();
           }
        
        if (date == null)//if no date define in metadata
            {
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                //get current date time with Date()
                Date nDate = new Date();
                date = dateFormat.format(nDate);
                System.out.println("there is no date data, so here is the system date:");

                //get current date time with Calendar()
                //Calendar cal = Calendar.getInstance();
                //System.out.println(dateFormat.format(cal.getTime()));
            }
        qe.close();
        return date;
    }
    
    public static String DescriptionExtractor( String path) throws FileNotFoundException{
         //open the rdf
       
        File rdf = new File(path);
        Model model;
        InputStream in = new FileInputStream(rdf);
        model = ModelFactory.createMemModelMaker().createModel(null);
        model.read(in, null);
        
        String queryString =
                "PREFIX foaf: <http://xmlns.com/foaf/0.1/> "
                + "PREFIX EXIF: <http://www.w3.org/2003/12/exif/ns#> "
                + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
                + "PREFIX relation: <http://purl.org/vocab/relationship/> "
                + "SELECT ?desc "
                + "WHERE { "
                + "?node EXIF:imageDescription ?desc . }";
        Query query = QueryFactory.create(queryString);
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet result = qe.execSelect();
        
        String description = null;
        while(result.hasNext()){
            QuerySolution qs = result.nextSolution();
            description = qs.get("desc").toString();
        }
        
        qe.close();
        return description;
    
    }
    
    private static String changeFileExt(String str) {
        String string[];
        string = str.split(".rdf");
        string[0] = string[0] + ".jpg";
        return string[0];
    }

    public static void main(String[] args) throws IOException {
        //searchPerson(".\\rdf\\test2.rdf","rajan");
//        String pathToDirectory = "imagedb/rdf/";
        //searchPersonThroughDirectory(pathToDirectory, "Chinese2");
//        searchPersonWithPhone(pathToDirectory, "prajjwol");
//        relateToPerson("imagedb/rdf/susan/susan.rdf", "susan", "closeFriendOf");
//        String relation = findRelation(pathToDirectory,"saru","susan");
//        System.out.println(relation);
    }
}
