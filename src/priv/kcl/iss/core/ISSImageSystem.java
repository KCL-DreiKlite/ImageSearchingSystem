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

    
    
    /** All image files in working folder. Generated from ImageDteails.json. */
    private ArrayList<ISSImageFileUnit> myImages;

    ISSImageSystem(File workingFolder) {
        myImages = new ArrayList<ISSImageFileUnit>();

    }
    ISSImageSystem(JSONObject sourceJson) {
        myImages = new ArrayList<ISSImageFileUnit>();

        for (Iterator<String> iterator = sourceJson.keys(); iterator.hasNext(); ) {
            String key = iterator.next();
            JSONObject imageJson = sourceJson.getJSONObject(key);
            

        }
    }

}
