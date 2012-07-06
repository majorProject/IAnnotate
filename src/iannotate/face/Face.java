/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iannotate.face;

import de.offis.faint.controller.MainController;
import de.offis.faint.model.ImageModel;
import de.offis.faint.model.Region;

/**
 *
 * @author susan
 */
public class Face {
    private static String source;
    private static String destination;
    private Region[] regions;
    
    public Face(String src, String dest) {
        source = src;
        destination = dest;
    }
    
    public Region[] detectFace() {
        MainController controller = MainController.getInstance();
        if(controller != null) {
            controller.setScanWindowSize(1);
            ImageModel imageModel = new ImageModel(source);
            imageModel.initThumbnail();
            regions = controller.detectFaces(imageModel, false);                        
        }
        return regions;
    }
}
