package priv.kcl.iss.core;

import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;

/**
 * A data type to control each image's detail and tags easily.
 */
public class ISSImageFileUnit {

    /** Store the file path of one specific image. */
    private final File fileImage;
    
    /** Store the tags of this image. */
    private ArrayList<ISSTagTreeUnit> myTags;

    /**
     * Create an Image Unit.
     * 
     * @param file the image file which I should be
     * @throws FileNotFoundException thrown if the file does not exist in file system
     * @throws FileAlreadyExistsException thrown if the path point to a folder
     */
    public ISSImageFileUnit(File file) throws FileNotFoundException, FileAlreadyExistsException {
        this.fileImage = file;
        this.myTags = new ArrayList<ISSTagTreeUnit>();

        checkFile();
    }
    /**
     * Create an Image Unit.
     * 
     * @param filePath the path of the image file which I should be
     * @throws FileNotFoundException thrown if the file does not exist in file system
     * @throws FileAlreadyExistsException thrown if the path point to a folder
     */
    public ISSImageFileUnit(String filePath) throws FileNotFoundException, FileAlreadyExistsException {
        this.fileImage = new File(filePath);
        this.myTags = new ArrayList<ISSTagTreeUnit>();

        checkFile();
    }
    /**
     * Create an Image Unit.
     * 
     * @param file the image file which I should be
     * @throws FileNotFoundException thrown if the file does not exist in file system
     * @throws FileAlreadyExistsException thrown if the path point to a folder
     */
    public ISSImageFileUnit(File file, ArrayList<ISSTagTreeUnit> tags) throws FileNotFoundException, FileAlreadyExistsException {
        this.fileImage = file;
        this.myTags = tags;

        checkFile();
    }
    /**
     * Create an Image Unit.
     * 
     * @param filePath the path of the image file which I should be
     * @throws FileNotFoundException thrown if the file does not exist in file system
     * @throws FileAlreadyExistsException thrown if the path point to a folder
     */
    public ISSImageFileUnit(String filePath, ArrayList<ISSTagTreeUnit> tags) throws FileNotFoundException, FileAlreadyExistsException {
        this.fileImage = new File(filePath);
        this.myTags = tags;

        checkFile();
    }

    /** Check if the image file is valid. */
    private void checkFile() throws FileNotFoundException, FileAlreadyExistsException {
        if (!this.fileImage.exists())
            throw new FileNotFoundException("File does not exist");
        else if (this.fileImage.isDirectory())
            throw new FileAlreadyExistsException("Folder is not acceptable");
    }

    /**
     * Add new tag into this image unit.
     * @param tag the tag which will be appened into this image unit
     * @return {@code false} if I already have that tag. Otherwise return {@code true}
     */
    public boolean addTag(ISSTagTreeUnit tag) {
        return myTags.add(tag);
    }

    /**
     * Remove tag from this image unit.
     * @param tag the tag which will be removed from this image unit
     * @return {@code false} if I don't have that tag. Otherwise return {@code true}
     */
    public boolean removeTag(ISSTagTreeUnit tag) {
        return myTags.remove(tag);
    }
    /**
     * Remove tag from this image unit.
     * @param tag the tag which will be removed from this image unit
     * @return {@code false} if I don't have that tag. Otherwise return {@code true}
     */
    public boolean removeTag(String tag) {
        boolean isRemoved = false;
        for (Iterator<ISSTagTreeUnit> iterator = myTags.iterator(); iterator.hasNext(); ) {
            ISSTagTreeUnit ttu = iterator.next();
            if (ttu.getTagName().equals(tag)) {
                myTags.remove(ttu);
                isRemoved = true;
                break;
            }
        }
        return isRemoved;
    }

    /**
     * Get the {@code ImageIcon} from the file.
     * @return the image from the file
     */
    public ImageIcon getImage() {
        return new ImageIcon(fileImage.getAbsolutePath());
    }
    /**
     * Get the image size.
     * @return the {@code Dimension} that store image's height and width.
     */
    public Dimension getImageSize() {
        ImageIcon ii = getImage();
        return new Dimension(ii.getIconWidth(), ii.getIconHeight());
    }
    /**
     * Get the file's length.
     * @return the length of the image file
     */
    public long getFileLength() {
        return fileImage.length();
    }
    /**
     * Get the file's length with fixed format.
     * @return the formatted length of the image file
     */
    public String getFileLengthInFormat() {
        return ISSCore.formatFileLength(fileImage);
    }

    /**
     * Get the absolute pathname of the image file.
     * @return the String of the path
     */
    public String getFileImagePath() {
        try {
            return fileImage.getCanonicalPath();
        }
        catch (Exception e) {
            return fileImage.getAbsolutePath();
        }
    }
    
    /**
     * Get the tags of this image.
     * @return an Arraylist contains all tags
     */
    public ArrayList<ISSTagTreeUnit> getTags() {
        return myTags;
    }

}
