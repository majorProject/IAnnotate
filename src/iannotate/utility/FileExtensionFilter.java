/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iannotate.utility;

import java.io.File;
import java.io.FilenameFilter;

/**
 *
 * @author Susan
 */
public class FileExtensionFilter implements FilenameFilter{

    private String extension="*";

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }
    
    @Override
    public boolean accept(File dir, String name) {
        if(name.endsWith(extension))
            return true;
        return false;
    }
    
//    public static void main(String[] args) {
//        File f = new File("C:\\new");
//        FileExtensionFilter fef = new FileExtensionFilter();
//        fef.setExtension("txt");
//        String[] contents = f.list(fef);
//        for(String content: contents){
//            System.out.println(content);
//        }
//    }
    
}

