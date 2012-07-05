/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iannotate.mainclass;

import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.MetadataException;
import iannotate.metadata.EXIFMetadataExtractor;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author susan
 */
public class IAnnotate {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File newFile = new File("images/image.jpg");
        EXIFMetadataExtractor eme;
        try{
           eme = new EXIFMetadataExtractor(newFile);
           System.out.println(eme.getEXIFVersion());
        }catch(IOException e){
        }catch(ImageProcessingException e){
        }catch(MetadataException me){}
    }
}
