package iannotate.face;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import de.offis.faint.controller.MainController;
import de.offis.faint.model.ImageModel;
import de.offis.faint.model.Region;
import java.util.HashMap;

/**
 *
 * @author susan
 */
public class FaceDetection {
    private static String source;
    private static String destination;
    private Region[] regions;
    
    public FaceDetection(String src, String dest) {
        source = src;
        destination = dest;
    }
    
    public Region[] detectFace(int p) {        
        MainController controller = MainController.getInstance();
        if(controller != null) {
            controller.setScanWindowSize(p);
            ImageModel imageModel = new ImageModel(source);
            imageModel.initThumbnail();
            regions = controller.detectFaces(imageModel, false);
        }
        return regions;
    }
    public HashMap<String, Integer> recogniseFace(Region r) {
        MainController controller = MainController.getInstance();
        HashMap<String, Integer> recognizeFace = controller.recognizeFace(r);
        return recognizeFace;
    }    
}
