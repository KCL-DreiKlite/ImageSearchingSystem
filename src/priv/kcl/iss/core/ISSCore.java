package priv.kcl.iss.core;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONObject;

/**
 * The core of Image Searching System(ISS). Provides some basic method and common attributes.
 */
public class ISSCore {

    /** The default Jsons home. */
    public static final String INFO_FOLDER_PATHNAME = ".\\.issinfos";
    /** The default Json file of storing Basic Information. */
    public static final String BASIC_INFO_FILENAME = INFO_FOLDER_PATHNAME + "\\BasicInfo.json";
    /** The default Json file of storing Tags Tree. */
    public static final String TAGS_FILENAME = INFO_FOLDER_PATHNAME + "\\Tags.json";
    /** The default Json file of storing Image Details. */
    public static final String IMAGE_DETAILS_FILENAME = INFO_FOLDER_PATHNAME + "\\ImageDetails.json";

    /** Store the JSON files in working folder. */
    public static final int STORE_IN_WORKING_FOLDER = 0;
    /** Store the JSON files in program folder. */
    public static final int STORE_IN_PROGRAM_FOLDER = 1;

    /** The Date format used for {@code SimpleDateFormat}. */
    public static final String SYSTEM_DATE_FORMAT = "yyyy/MM/dd hh:mm:ss";

    /** The universal Date. */
    public static final Date systemDate = new Date();

    /**
     * Initialize working folder. This method will access every directories and files in
     * the give path to create basic tag system.
     * If this is your first time to start ISS or the target folder
     * does not contain necessary JSON files in a <code>infos/</code> directory, please
     * call this method.
     * 
     * @param workingFolder the pathname of target working folder
     * @param forceOverwrite if there exist the same filename in info_folder for each json
     * files, and {@code forceOverwrite} = true, then I'll re-write these files as
     * initialization status. For example, if {@code BasicInfo.json} is exist, and
     * {@code forceOverwrite} = false, then I'll skip to intialize {@code BasicInfo.json}.
     * But if true, then it's content will be deleted and I'll overwrite it as an
     * initialzed file.
     */
    public static void initialize(String workingFolder, boolean forceOverwrite) {

        File fileWorkingFolder = new File(workingFolder);
        
        File fileInfoFolder = new File(INFO_FOLDER_PATHNAME);

        File fileBasicInfo = new File(BASIC_INFO_FILENAME);
        File fileTags = new File(TAGS_FILENAME);
        File fileImageDetails = new File(IMAGE_DETAILS_FILENAME);
        
        //////////////////  Create InfoFoler  //////////////////
        System.out.println("Creating InfoFolder... "+(fileInfoFolder.mkdir()? "done": "folder already exist"));

        //////////////////  Create BasicInfo.json  //////////////////
        System.out.print("Creating BasicInfo.json... ");
        if (!fileBasicInfo.exists() || forceOverwrite) {
            if (forceOverwrite)
                System.out.print("there's a same filename exist. Overwriting... ");

            try {
                fileBasicInfo.createNewFile();
                JSONObject jsonBasicInfo = new JSONObject();
                jsonBasicInfo.put("WORKINGFOLDER", fileWorkingFolder.getAbsolutePath());

                BufferedWriter br = new BufferedWriter(new FileWriter(fileBasicInfo));
                br.write(jsonBasicInfo.toString());
                br.flush();
                br.close();

                System.out.println("done");
            }
            catch (Exception e) {
                System.err.println("Initialization failed.\nAt core.ISSCore.initialize() when creating BasicInfo.json\n"+e.getMessage());
            }
        }
        else
            System.out.println("there's a same filename exist. Skipped.");

        //////////////////  Create Tags.json  //////////////////
        System.out.print("Creating Tags.json... ");
        if (!fileTags.exists() || forceOverwrite) {
            if (forceOverwrite)
                System.out.print("there's a same filename exist. Overwriting... ");
            
            try {
                fileTags.createNewFile();
                
                ISSTagTreeUnit ttuTagTree = new ISSTagTreeUnit(null, "TagTree");
                ISSTagTreeUnit ttuExtension = new ISSTagTreeUnit(ttuTagTree, "Extension");
                ttuTagTree.append(ttuExtension);
                ttuExtension.append(new ISSTagTreeUnit(ttuExtension, "JPG"));
                ttuExtension.append(new ISSTagTreeUnit(ttuExtension, "JPEG"));
                ttuExtension.append(new ISSTagTreeUnit(ttuExtension, "PNG"));
                ttuExtension.append(new ISSTagTreeUnit(ttuExtension, "GIF"));
                ttuExtension.append(new ISSTagTreeUnit(ttuExtension, "BMP"));

                ISSTagTreeUnit ttuIgnoredExtension = new ISSTagTreeUnit(null, "IgnoredExtension");
                ttuIgnoredExtension.append(new ISSTagTreeUnit(ttuIgnoredExtension, "EXE"));
                ttuIgnoredExtension.append(new ISSTagTreeUnit(ttuIgnoredExtension, "RAR"));
                ttuIgnoredExtension.append(new ISSTagTreeUnit(ttuIgnoredExtension, "ZIP"));
                ttuIgnoredExtension.append(new ISSTagTreeUnit(ttuIgnoredExtension, "JSON"));
                ttuIgnoredExtension.append(new ISSTagTreeUnit(ttuIgnoredExtension, "JAR"));
                ttuIgnoredExtension.append(new ISSTagTreeUnit(ttuIgnoredExtension, ""));

                JSONObject jsonTags = new JSONObject();
                // jsonTags.put("TagTree", ISSTagSystem.transformToJSON(ttuTagTree));
                // jsonTags.put("IgnoredExtension", ISSTagSystem.transformToJSON(ttuIgnoredExtension));
                jsonTags.put("TagTree", ISSTagSystem.transformToJSON(ttuTagTree));
                jsonTags.put("IgnoredExtension", ISSTagSystem.transformToJSON(ttuIgnoredExtension));
                
                BufferedWriter br = new BufferedWriter(new FileWriter(fileTags));
                br.write(jsonTags.toString());
                br.flush();
                br.close();

                System.out.println("done");
            }
            catch (Exception e) {
                System.err.println("Initialization failed.\nAt core.ISSCore.initialize() when creating Tags.json\n"+e.getMessage());
            }
        }
        else
            System.out.println("there's a same filename exist. Skipped.");

        //////////////////  Create ImageDetails.json  //////////////////
        System.out.print("Creating ImageDetails.json... ");
        if (!fileImageDetails.exists() || forceOverwrite) {
            if (forceOverwrite)
                System.out.print("there's a same filename exist. Overwriting... ");
            
            try {
                fileImageDetails.createNewFile();

                // ArrayList<ISSImageFileUnit> fileList = makeFileCollection(fileWorkingFolder.listFiles());
                JSONObject jsonFileList = new JSONObject();
                // fileList.stream()
                //         .forEach(file -> jsonFileList.put(file.getFileName(), file.toJSONObject()));
                
                BufferedWriter br = new BufferedWriter(new FileWriter(fileImageDetails));
                br.write(jsonFileList.toString());
                br.flush();
                br.close();

                System.out.println("done");
            }
            catch (Exception e) {
                System.err.println("Initialization failed.\nAt core.ISSCore.initialize() when creating ImageDetails.json\n"+e.getMessage());
                
            }
        }
        else
            System.out.println("there's a same filename exist. Skipped.");

        System.out.println("Initialization complete.");
    }

    public static ArrayList<ISSImageFileUnit> makeFileCollection(File[] files) throws Exception {
        ArrayList<ISSImageFileUnit> result = new ArrayList<ISSImageFileUnit>();

        for (File file: files) {
            if (file.isDirectory())
                result.addAll(makeFileCollection(file.listFiles()));
            else
                result.add(new ISSImageFileUnit(file));
        }
        
        
        return result;
    }

    public static void deleteInfoFolder() {
        new File(BASIC_INFO_FILENAME).delete();
        new File(TAGS_FILENAME).delete();
        new File(IMAGE_DETAILS_FILENAME).delete();
        new File(INFO_FOLDER_PATHNAME).delete();
    }

    public static boolean checkInfoFolderExist() {
        if (!new File(BASIC_INFO_FILENAME).exists() ||
            !new File(TAGS_FILENAME).exists() ||
            !new File(IMAGE_DETAILS_FILENAME).exists())
            return false;
        return true;
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
