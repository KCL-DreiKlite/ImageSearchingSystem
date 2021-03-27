package priv.kcl.iss.core;

import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A data type to control each image's detail and tags easily.
 */
public class ISSImageFileUnit {

    /** Store the file path of one specific image. */
    private final File fileImage;
    
    /** Store when this image/file has been added into ISS. */
    private final String addedTime;

    /** Store the tags of this image. */
    private ArrayList<ISSTagTreeUnit> myTags;

    /**
     * Create an Image Unit directly from json object.
     * 
     * @param sourceJson the source json object of this unit
     * @param standardTagTree all of my tags are gonna found by this tagtree. If I have a tag
     * that this tagtree doesn't exist, then the tag will be deleted automatically.
     */
    public ISSImageFileUnit(JSONObject sourceJson, ISSTagTreeUnit standardTagTree) throws JSONException {
        fileImage = new File(sourceJson.getString(ISSImageSystem.KEY_FILEPATH));
        addedTime = sourceJson.getString(ISSImageSystem.KEY_ADDEDTIME);
        JSONArray array = sourceJson.getJSONArray(ISSImageSystem.KEY_TAGS);
        array.forEach(tagname -> {
            if (tagname.getClass() != String.class)
                return;
            ISSTagTreeUnit ttu = standardTagTree.find(String.valueOf(tagname));
            if (ttu != null)
                myTags.add(ttu);
            else
                return;
        });

        ISSCore.logger.finest(toString());
    }
    /**
     * Create an Image Unit.
     * 
     * @param file the image file which I should be
     * @throws ISSException thrown if there's no such file or the pathname pointed to a directory
     */
    public ISSImageFileUnit(File file) throws ISSException {
        this.fileImage = file;
        this.myTags = new ArrayList<ISSTagTreeUnit>();
        this.addedTime = ISSCore.getDateInFormat(ISSCore.PATTERN_FOR_STRING);

        if (!this.fileImage.exists())
            throw new ISSException("File not found");
        else if (this.fileImage.isDirectory())
            throw new ISSException("The file path pointed to a directory");

        ISSCore.logger.finest(toString());
    }
    /**
     * Create an Image Unit.
     * 
     * @param filePath the path of the image file which I should be
     * @throws ISSException thrown if there's no such file or the pathname pointed to a directory
     */
    public ISSImageFileUnit(String filePath) throws ISSException {
        this.fileImage = new File(filePath);
        this.myTags = new ArrayList<ISSTagTreeUnit>();
        this.addedTime = ISSCore.getDateInFormat(ISSCore.PATTERN_FOR_STRING);

        if (!this.fileImage.exists())
            throw new ISSException("File not found");
        else if (this.fileImage.isDirectory())
            throw new ISSException("The file path pointed to a directory");

        ISSCore.logger.finest(toString());
    }
    /**
     * Create an Image Unit.
     * 
     * @param file the image file which I should be
     * @param addedTime when this image file added into ISS
     * @throws ISSException thrown if there's no such file or the pathname pointed to a directory
     */
    public ISSImageFileUnit(File file, String addedTime) throws ISSException {
        this.fileImage = file;
        this.myTags = new ArrayList<ISSTagTreeUnit>();
        this.addedTime = addedTime;

        if (!this.fileImage.exists())
            throw new ISSException("File not found");
        else if (this.fileImage.isDirectory())
            throw new ISSException("The file path pointed to a directory");

        ISSCore.logger.finest(toString());
    }
    /**
     * Create an Image Unit.
     * 
     * @param filePath the path of the image file which I should be
     * @param addedTime when this image file added into ISS
     * @throws ISSException thrown if there's no such file or the pathname pointed to a directory
     */
    public ISSImageFileUnit(String filePath, String addedTime) throws ISSException {
        this.fileImage = new File(filePath);
        this.myTags = new ArrayList<ISSTagTreeUnit>();
        this.addedTime = addedTime;

        if (!this.fileImage.exists())
            throw new ISSException("File not found");
        else if (this.fileImage.isDirectory())
            throw new ISSException("The file path pointed to a directory");

        ISSCore.logger.finest(toString());
    }
    /**
     * Create an Image Unit.
     * 
     * @param file the image file which I should be
     * @param tags all of my tags
     * @throws ISSException thrown if there's no such file or the pathname pointed to a directory
     */
    public ISSImageFileUnit(File file, ArrayList<ISSTagTreeUnit> tags) throws ISSException {
        this.fileImage = file;
        this.myTags = tags;
        this.addedTime = ISSCore.getDateInFormat(ISSCore.PATTERN_FOR_STRING);

        if (!this.fileImage.exists())
            throw new ISSException("File not found");
        else if (this.fileImage.isDirectory())
            throw new ISSException("The file path pointed to a directory");

        ISSCore.logger.finest(toString());
    }
    /**
     * Create an Image Unit.
     * 
     * @param filePath the path of the image file which I should be
     * @param tags all of my tags
     * @throws ISSException thrown if there's no such file or the pathname pointed to a directory
     */
    public ISSImageFileUnit(String filePath, ArrayList<ISSTagTreeUnit> tags) throws ISSException {
        this.fileImage = new File(filePath);
        this.myTags = tags;
        this.addedTime = ISSCore.getDateInFormat(ISSCore.PATTERN_FOR_STRING);

        if (!this.fileImage.exists())
            throw new ISSException("File not found");
        else if (this.fileImage.isDirectory())
            throw new ISSException("The file path pointed to a directory");

        ISSCore.logger.finest(toString());
    }
    /**
     * Create an Image Unit.
     * 
     * @param file the image file which I should be
     * @param addedTime when this image file added into ISS
     * @param tags all of my tags
     * @throws ISSException thrown if there's no such file or the pathname pointed to a directory
     */
    public ISSImageFileUnit(File file, String addedTime, ArrayList<ISSTagTreeUnit> tags) throws ISSException {
        this.fileImage = file;
        this.myTags = tags;
        this.addedTime = addedTime;

        if (!this.fileImage.exists())
            throw new ISSException("File not found");
        else if (this.fileImage.isDirectory())
            throw new ISSException("The file path pointed to a directory");

        ISSCore.logger.finest(toString());
    }
    /**
     * Create an Image Unit.
     * 
     * @param filePath the path of the image file which I should be
     * @param addedTime when this image file added into ISS
     * @param tags all of my tags
     * @throws ISSException thrown if there's no such file or the pathname pointed to a directory
     */
    public ISSImageFileUnit(String filePath, String addedTime, ArrayList<ISSTagTreeUnit> tags) throws ISSException {
        this.fileImage = new File(filePath);
        this.myTags = tags;
        this.addedTime = addedTime;

        if (!this.fileImage.exists())
            throw new ISSException("File not found");
        else if (this.fileImage.isDirectory())
            throw new ISSException("The file path pointed to a directory");

        ISSCore.logger.finest(toString());
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
     * Get a unique hash code of an image file.
     * @return
     */
    public int getIdentity() {
        final ImageIcon image = getImage();
        // final int imageWidth = image.getIconWidth();
        // final int imageHeight = image.getIconHeight();
        // final long fileLength = fileImage.length();
        // final String fileName = fileImage.getName();
        final String result = fileImage.getName() + fileImage.length() + image.getIconWidth() + image.getIconHeight();
        return result.hashCode();
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
        ImageIcon image = getImage();
        return new Dimension(image.getIconWidth(), image.getIconHeight());
    }
    /**
     * Get the aspect ratio of image.
     * @return the aspect ratio
     */
    public double getImageAspectRatio() {
        final ImageIcon image = getImage();
        return image.getIconWidth() / image.getIconHeight();
    }

    /**
     * Get the name of file without path.<p>
     * For example, if there's a file with this local path: {@code /home/pic/image.jpg}
     * , then it'll return {@code image.jpg}.
     * @return
     */
    public String getFilename() {
        return fileImage.getName();
    }
    /**
     * Get the name of file without path and extension.
     * For example, if there's a file with this local path: {@code /home/pic/image.jpg}
     * , then it'll return {@code image}.
     * @return
     */
    public String getFilenameWithoutExtension() {
        int extIndex = fileImage.getName().lastIndexOf(".");
        return fileImage.getName().substring(0, (extIndex==-1? fileImage.getName().length(): extIndex));
    }
    /**
     * Get the extension of file. <b>The return string will convert to uppercase.</b>
     * @return
     */
    public String getFileExtension() {

        if (getFilename().equals(getFilenameWithoutExtension()))
            return ISSImageSystem.EXT_EMPTY;
        else
            return getFilename().substring(getFilenameWithoutExtension().length()+1).toUpperCase();
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
    public String getFileAbsolutePath() {
        try {
            return fileImage.getCanonicalPath();
        }
        catch (Exception e) {
            return fileImage.getAbsolutePath();
        }
    }
    /**
     * Get the relative pathname based on the given working folder path.<p>
     * For example, if the IFU's pathname is {@code "C:\hello\hi.jpg"} with the input argument
     * {@code "C:\hello\"}, then it'll return {@code ".\hi.jpg"}. It'll return the same string
     * if the input is {@code "C:\hello"}. But if the input is {@code "D:\bye"}, then the
     * return value will be {@code null}.
     * 
     * @param workingFolderPath the working folder's pathname
     * @return the relative pathname start with ".\"
     */
    // TODO: Rewrite this method by changing the soruce of working folder pathanme to ISSCore.getWorkingFolderPathname()
    public String getFileRelativePath() {
        // String absPath = getFileAbsolutePath();
        // if (absPath.indexOf(ISSCore.getWorkingFolderPathname(), 0) == 0) {
        //     String rltPath = absPath.substring(ISSCore.getWorkingFolderPathname().length());
        //     rltPath = (rltPath.charAt(0)!='\\' ? ".\\" : ".") + rltPath;
        //     return rltPath;
        // }
        // else
        //     return null;
        // String rltPath = "." + absPath.substring(ISSCore.getWorkingFolderPathname().length());
        return "." + getFileAbsolutePath().substring(ISSCore.getWorkingFolderPathname().length());
    }

    /**
     * Get the tags of this image.
     * @return an Arraylist contains all tags
     */
    public ArrayList<ISSTagTreeUnit> getTags() {
        return myTags;
    }

    /**
     * Get the time-stamp when this image file has been added into ISS.
     * @return the time-stamp formatted as {@code ISSCore.SYSTEM_DATE_FORMAT}
     */
    public String getAddedTime() {
        return addedTime;
    }

    public String toString() {
        return "[Filename="+fileImage.getName()+",Location="+getFileAbsolutePath()+",Length="+getFileLengthInFormat()+",AddedTime="+getAddedTime()+"]";
    }
    
    public JSONObject toJSONObject() {
        JSONObject result = new JSONObject();
        result.put(ISSImageSystem.KEY_FILENAME, getFilename());
        result.put(ISSImageSystem.KEY_FILEPATH, getFileAbsolutePath());
        result.put(ISSImageSystem.KEY_ADDEDTIME, addedTime);
        JSONArray tagArray = new JSONArray();
        myTags.stream().forEach(tag -> tagArray.put(tag));
        result.put(ISSImageSystem.KEY_TAGS, tagArray);
        result.put(ISSImageSystem.KEY_IDENTITY, getIdentity());
        return result;
    }
}
