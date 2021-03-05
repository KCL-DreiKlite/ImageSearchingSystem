package priv.kcl.iss.core;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.json.JSONObject;

/**
 * The core of Image Searching System(ISS). Provides some basic method and common attributes.
 */
public class ISSCore {

    /** The default Jsons home. */
    public static final String INFO_FOLDER_PATHNAME = ".\\infos";
    /** The default Json file of storing Basic Information. */
    public static final String BASIC_INFO_FILENAME = INFO_FOLDER_PATHNAME + "\\BasicInfo.json";
    /** The default Json file of storing Tags Tree. */
    public static final String TAGS_FILENAME = INFO_FOLDER_PATHNAME + "\\Tags.json";
    /** The default Json file of storing Image Details. */
    public static final String IMAGE_DETAILS_FILENAME = INFO_FOLDER_PATHNAME + "\\ImageDetails.json";

    /**
     * Initialize working folder. This method will access every directories and files in
     * the give path to create basic tag system.
     * If this is your first time to start ISS or the target folder
     * does not contain necessary JSON files in a <code>infos/</code> directory, please
     * call this method.
     * 
     * @param path the pathname of target working folder

     */
    public static void initialize(String path) {
        File fileBasicInfo = new File(BASIC_INFO_FILENAME);
        File fileTags = new File(TAGS_FILENAME);
        File fileImageDetails = new File(IMAGE_DETAILS_FILENAME);

        File fileWorkingFolder = new File(path);

        JSONObject jsonBasicInfo = new JSONObject();
        JSONObject jsonTags = new JSONObject();
        JSONObject jsonImageDetails = new JSONObject();

        jsonBasicInfo.put("workingFolder", path);

        try {
            
        }
        catch (Exception e) {

        }
    }

    /**
     * Get the file's formatted length. For example:<p>
     * <b>123</b> will return <b>123 B</b><p>
     * <b>1024</b> will return <b>1 KB</b><p>
     * <b>1234567</b> will return <b>1.17 MB</b><p>
     * <b>12345678</b> will return <b>11.7 MB</b><p>
     * 
     * @param length the length of the given file
     * @return the string of formatted file length
     */
    public static String formatFileLength(long length) {
        double tmpLength = length;
        int sizeLevel = 0;
        while (tmpLength >= 1024) {
            tmpLength /= 1024;
            sizeLevel++;
        }
        String resultSizeLevel;
        switch (sizeLevel) {
            case 0:
                resultSizeLevel = "B";
                break;
            case 1:
                resultSizeLevel = "KB";
                break;
            case 2:
                resultSizeLevel = "MB";
                break;
            case 3:
                resultSizeLevel = "GB";
                break;
            default:
                resultSizeLevel = "TB";
                break;
        }
        int resultLengthIntPart = (int) tmpLength;
        String resultLengthFloatPart = "";
        if (tmpLength == 1 || tmpLength == 0)
            resultLengthFloatPart = "";
        else if (tmpLength < 10)
            resultLengthFloatPart = "."+String.valueOf(tmpLength-resultLengthIntPart).substring(2, 4);
        else if (tmpLength < 100)
            resultLengthFloatPart = "."+String.valueOf(tmpLength-resultLengthIntPart).substring(2, 3);
        
        return resultLengthIntPart + resultLengthFloatPart + " " + resultSizeLevel;
    }
    /**
     * Get the file's formatted length. For example:<p>
     * <b>123</b> will return <b>123 B</b><p>
     * <b>1024</b> will return <b>1 KB</b><p>
     * <b>1234567</b> will return <b>1.17 MB</b><p>
     * <b>12345678</b> will return <b>11.7 MB</b><p>
     * 
     * @param file the file which it's length need to be formatted
     * @return the string of formatted file length
     */
    public static String formatFileLength(File file) {
        return formatFileLength(file.length());
    }
    
}
