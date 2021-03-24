package priv.kcl.iss.core;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import org.json.JSONObject;

/**
 * The core of Image Searching System(ISS). Provides some basic method and common attributes.
 */
public class ISSCore {
    /** Store the JSON files in working folder. */
    public static final int STORE_IN_WORKING_FOLDER = 0;
    /** Store the JSON files in program folder. */
    public static final int STORE_IN_PROGRAM_FOLDER = 1;
    
    /** The Date format for any string need date. */
    public static final String PATTERN_FOR_STRING = "yyyy/MM/dd HH:mm:ss";
    
    /** The Date format for any file name need date. */
    public static final String PATTERN_FOR_FILE = "yyyyMMdd_HHmmss";
    
    /** The universal Date. */
    public static final Date systemDate = new Date();

    /** The default Jsons home. */
    public static final String INFO_FOLDER_PATHNAME = ".\\.issinfos";
    /** The default Json file of storing Basic Information. */
    public static final String BASIC_INFO_FILENAME = INFO_FOLDER_PATHNAME + "\\BasicInfo.json";
    /** The default Json file of storing Tags Tree. */
    public static final String TAGS_FILENAME = INFO_FOLDER_PATHNAME + "\\Tags.json";
    /** The default Json file of storing Image Details. */
    public static final String IMAGE_DETAILS_FILENAME = INFO_FOLDER_PATHNAME + "\\ImageDetails.json";

    /** The default Logs home. */
    public static final String LOG_FOLDER_PATHNAME = ".\\.log";
    /** The default Log file. */
    public static final String LOG_FILE = LOG_FOLDER_PATHNAME + "\\ISS_" + getDateInFormat(PATTERN_FOR_FILE) + ".log";
    
    /** The name of the logger. */
    public static final String LOGGER_NAME = "ISS_Logger";
    /** The logger of ISS. *Need to be initialized before logging any message.* */
    public static final Logger logger = Logger.getLogger(LOGGER_NAME);
    /** The default level of ISS_Logger. */
    public static final Level defaultLoggerLevel = Level.ALL;
    
    /** Used for first startup checking. */
    private static boolean hasStartUp = false;

    /**
     * Initialize every static attributes in ISS, like Logger.<p>
     * <b>IMPORTANT!</b> This method should be called in the frist line of {@code Main.class} and can only
     * be called once.
     */
    public static void startUpISS() {
        // Exit the program if this is not the first time to call this method.
        if (hasStartUp) {
            logger.warning("core.ISSCore.startUpISS() has been called for multiple times! Please check the code immediatly!");
            return;
        }
        
        // Finish the Logger setting.
        logger.setLevel(defaultLoggerLevel);
        try {
            FileHandler fileHandler = new FileHandler(LOG_FILE);
            fileHandler.setLevel(defaultLoggerLevel);
            fileHandler.setFormatter(new Formatter() {
                @Override
                public String format(LogRecord record) {
                    String levelString;
                    switch (record.getLevel().getName().toCharArray()[0]) {
                        case 'S':
                            levelString = "ERROR";
                            break;
                        case 'C':
                            levelString = "CONF";
                            break;
                        case 'W':
                            levelString = "WARN";
                            break;
                        case 'I':
                            levelString = "INFO";
                            break;
                        default:
                            levelString = "VERB";
                            break;
                    }
                    return "[" + getDateInFormat(PATTERN_FOR_STRING) + "] " +
                           "[" + levelString + "]\t" +
                           "@" + record.getSourceClassName() + "." + record.getSourceMethodName() + "\t| " +
                           record.getMessage() +
                           "\n";
                }
            });
            logger.addHandler(fileHandler);
            
        }
        catch (IOException e) {
            System.err.println("Failed to initialize ISS_Logger\nAt core.ISSCore.startUpISS()\n"+e.getMessage());
        }

        // TODO: Maybe there will be more feature in the future?
    }
    
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
    // XXX: Rewrite this method. Maybe seperate some features to a single method is way more better?
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

    /**
     * Get all files in working folder from file system. Contain all extensions (include RAR, ZIP, etc.).
     * 
     * @param files
     * @return
     */
    public static ArrayList<File> makeFileCollection(File[] files) {
        ArrayList<File> result = new ArrayList<File>();

        for (File file: files) {
            if (file.isDirectory())
                result.addAll(makeFileCollection(file.listFiles()));
            else
                result.add(file);
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
    
    /**
     * Get the system date in format.
     * 
     * @param pattern the specific pattern which the date string should formatted
     * @return the frmatted date string
     */
    public static String getDateInFormat(String pattern) {
        return new SimpleDateFormat(pattern).format(systemDate);
    }
    
}
