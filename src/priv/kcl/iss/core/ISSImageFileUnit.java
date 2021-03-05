package priv.kcl.iss.core;

import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class ISSImageFileUnit {

    
    private final File fileImage;
    
    private ArrayList<ISSTagTreeUnit> myTags;

    public ISSImageFileUnit(File filePath) throws Exception {
        this.fileImage = filePath;
        this.myTags = new ArrayList<ISSTagTreeUnit>();

        if (!this.fileImage.exists())
            throw new Exception("File does not exist");
        else if (this.fileImage.isDirectory())
            throw new Exception("Folder-Type file is not acceptable");
    }

    public ISSImageFileUnit(String filePath) throws Exception {
        this.fileImage = new File(filePath);
        this.myTags = new ArrayList<ISSTagTreeUnit>();

        if (!this.fileImage.exists())
            throw new Exception("File does not exist");
        else if (this.fileImage.isDirectory())
            throw new Exception("Folder-Type file is not acceptable");
    }

    public ISSImageFileUnit(File filePath, ArrayList<ISSTagTreeUnit> tags) throws Exception {
        this.fileImage = filePath;
        this.myTags = tags;

        if (!this.fileImage.exists())
            throw new Exception("File does not exist");
        else if (this.fileImage.isDirectory())
            throw new Exception("Folder-Type file is not acceptable");
    }

    public ISSImageFileUnit(String filePath, ArrayList<ISSTagTreeUnit> tags) throws Exception {
        this.fileImage = new File(filePath);
        this.myTags = tags;

        if (!this.fileImage.exists())
            throw new Exception("File does not exist");
        else if (this.fileImage.isDirectory())
            throw new Exception("Folder-Type file is not acceptable");
    }

    public ImageIcon getImage() {
        return new ImageIcon(fileImage.getAbsolutePath());
    }
    public Dimension getImageSize() {
        ImageIcon ii = getImage();
        return new Dimension(ii.getIconWidth(), ii.getIconHeight());
    }
    public long getFileLength() {
        return fileImage.length();
    }

    public String getImagePath() {
        try {
            return fileImage.getCanonicalPath();
        }
        catch (Exception e) {
            return fileImage.getAbsolutePath();
        }
    }
    
    public ArrayList<ISSTagTreeUnit> getTags() {
        return myTags;
    }

}
