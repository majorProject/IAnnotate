package iannotate.utility;

import de.offis.faint.global.Constants;
import de.offis.faint.model.FaceDatabase;
import java.io.*;

public class FileUtil {

    public static void copyfile(String srFile, String dtFile) {
        try {
            File f1 = new File(srFile);
            File f2 = new File(dtFile);
            InputStream in = new FileInputStream(f1);

            //For Append the file.
//			OutputStream out = new FileOutputStream(f2,true);

            //For Overwrite the file.
            OutputStream out = new FileOutputStream(f2);

            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
            System.out.println("File copied.");
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage() + " in the specified directory.");
            System.exit(0);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //for recovering FaceDb.data from the disk
    public static FaceDatabase recoverFromDisk(String path) throws IOException {
        String filename = path + File.separator + Constants.FACE_DB_FILE;
        FileInputStream fis = new FileInputStream(filename);
        ObjectInputStream in = new ObjectInputStream(fis);
        FaceDatabase db = null;
        try {
            db = (FaceDatabase) in.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        in.close();
        return db;
    }

    public static String getSession() throws FileNotFoundException, IOException, IOException {
        int ch;
        StringBuffer strContent = new StringBuffer("");
        FileInputStream fs = new FileInputStream("session.txt");
        while ((ch = fs.read()) != -1) {
            strContent.append((char) ch);
        }
        fs.close();
        return strContent.toString().trim();
    }
// for testing
    public static void main(String[] args) throws FileNotFoundException, IOException {
        //copyfile("D:\\Temp\\test.html", "D:\\test.html");
        String s = getSession();
        System.out.println(s);
    }
}