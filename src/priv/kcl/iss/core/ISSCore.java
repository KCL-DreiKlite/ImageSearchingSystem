package priv.kcl.iss.core;

import java.io.File;

/**
 * The core of Image Searching System(ISS). Provides some basic method and common attributes.
 */
public class ISSCore {

    /** The default Jsons home. */
    public static final String INFO_FOLDER_PATHNAME = ".\\info";
    /** The default Json file of storing Basic Information. */
    public static final String BASIC_INFO_FILENAME = INFO_FOLDER_PATHNAME + "\\BasicInfo.json";
    /** The default Json file of storing Tags Tree. */
    public static final String TAG_SYSTEM_FILENAME = INFO_FOLDER_PATHNAME + "\\Tags.json";
    /** The default Json file of storing Image Details. */
    public static final String IMAGE_DETAIL_FILENAME = INFO_FOLDER_PATHNAME + "\\ImageDetails.json";

    /**
     * Initialize working folder. If this is your first time to start ISS or the target folder
     * does not contain some necessary JSON file in a <code>infos/</code> directory, please
     * call this method.
     * 
     * @param path the pathname of target working folder
     */
    public static void initialize(String path) {
        File fileBasicInfo = new File(BASIC_INFO_FILENAME);
        File fileTagSystem = new File(TAG_SYSTEM_FILENAME);
        File fileImageDetail = new File(IMAGE_DETAIL_FILENAME);

        File fileWorkingFolder = new File(path);

        
    }
}
