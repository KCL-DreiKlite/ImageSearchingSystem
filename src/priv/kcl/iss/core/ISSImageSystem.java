package priv.kcl.iss.core;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONObject;

/**
 * This class provides you to manage your {@code ISSImageFileUnit} like searching, sorting,
 * initializing, etc.
 */
public class ISSImageSystem {

    /** The json key of {@code FILENAME}. */
    public static final String KEY_FILENAME = "FILENAME";
    /** The json key of {@code FILEPATH}. */
    public static final String KEY_FILEPATH = "FILEPATH";
    /** The json key of {@code ADDEDTIME}. */
    public static final String KEY_ADDEDTIME = "ADDEDTIME";
    /** The json key of {@code TAGS}. */
    public static final String KEY_TAGS = "TAGS";
    /** The json key of {@code IDENTITY}. */
    public static final String KEY_IDENTITY = "IDENTITY";

    
    private final File workingFolder;
    
    /** All image files in working folder. Generated from ImageDteails.json. */
    private ArrayList<ISSImageFileUnit> myImages;

    private final ISSTagTreeUnit standardTagTree;
    private final ISSTagTreeUnit ignoredExtension;

    private final JSONObject sourceJson;


    ISSImageSystem(File workingFolder, ISSTagTreeUnit standardTagTree, ISSTagTreeUnit ignoredExtension) {
        this.workingFolder = workingFolder;
        this.myImages = new ArrayList<ISSImageFileUnit>();
        this.sourceJson = new JSONObject();
        this.standardTagTree = standardTagTree;
        this.ignoredExtension = ignoredExtension;

    }
    /**
     * Create an Image System.
     * 
     * 
     * @param sourceJson the json stored every ISSImageFileUnit
     */
    ISSImageSystem(File workingFolder, JSONObject sourceJson, ISSTagTreeUnit standardTagTree, ISSTagTreeUnit ignoredExtension) {
        this.workingFolder = workingFolder;
        this.myImages = new ArrayList<ISSImageFileUnit>();
        this.sourceJson = sourceJson;
        this.standardTagTree = standardTagTree;
        this.ignoredExtension = ignoredExtension;

        // for (Iterator<String> iterator = sourceJson.keys(); iterator.hasNext(); ) {
        //     String key = iterator.next();
        //     JSONObject imageJson = sourceJson.getJSONObject(key);
            

        // }
    }

    /**
     * Convert all files in working folder to ISSImageFileUnit. The variable to store these units is
     * {@code myImages}. If there contain a new file which {@code myImages} doesn't contain, then it'll
     * create a new one with basic tags. If there exist a ISSImageFileUnit where the actual file has
     * deleted or moved, then the system will start finding from file system based on it's identity
     * (which you could get it by {@code ISSImageFileUnit.getIdentity()}). If the search result is null,
     * then this unit will be deleted automatically.
     */
    private void setupWorkingFolder() {
        ArrayList<ISSImageFileUnit> tmpImages = new ArrayList<ISSImageFileUnit>();
        // ArrayList<File> imagesInFileSystem = ISSCore.makeFileCollection(workingFolder.listFiles());
        
        // Read all ImageFileUnit from sourceJson to myImages.
        for (Iterator<String> iterator = sourceJson.keys(); iterator.hasNext(); ) {
            String key = iterator.next();
            JSONObject imageFileUnit = sourceJson.getJSONObject(key);
            ISSImageFileUnit ifu = new ISSImageFileUnit(imageFileUnit, standardTagTree);
            
        }
    }
}
