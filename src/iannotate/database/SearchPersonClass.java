/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iannotate.database;

/**
 *
 * @author Susan
 */
public class SearchPersonClass {
    private String fileName;
    private String imageWidth;
    private String imageLength;
    private String height;
    private String width;
    private String personName;

    public SearchPersonClass(String fN, String iW, String iL, String h, String w, String name) {
        this.fileName = fN;
        this.imageWidth = iW;
        this.imageLength = iL;
        this.height = h;
        this.width = w;
        this.personName = name;
    }

    public String getFileName() {
        return fileName;
    }

    public String getHeight() {
        return height;
    }

    public String getImageLength() {
        return imageLength;
    }

    public String getWidth() {
        return width;
    }

    public String getImageWidth() {
        return imageWidth;
    }
    
    public String getpersonName() {
        return personName;
    }
    
}
