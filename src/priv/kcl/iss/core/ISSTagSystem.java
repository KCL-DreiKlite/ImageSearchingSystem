package priv.kcl.iss.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONObject;

/**
 * The core taging system of Image Searching System(ISS). Provides 
 */
public class ISSTagSystem {
    
    // final File sourceTagsJsonFile = new File(ISSCore.TAG_SYSTEM_FILENAME);
    // final JSONObject tagsJson;
    // final ISSTagTreeUnit tags;

    // public ISSTagSystem() {
    //     try {
    //         BufferedReader br = new BufferedReader(new FileReader(sourceTagsJsonFile));
    //         tagsJson = new JSONObject(br.readLine());
    //         br.close();
    //     }
    //     catch (Exception e) {
    //         System.err.println(e.toString());
    //     }
    // }

    ArrayList<ImageUnit> imageUnits = new ArrayList<ImageUnit>();

    void resolveJsonToTTU() {
        
    }

    void transformToJsonFromTTU() {

    }
}

class ImageUnit {
    
    File fileImagePath;

    ArrayList<ISSTagTreeUnit> myTags;

    ImageUnit(File imagePath, ArrayList<ISSTagTreeUnit> tags) {
        
    }



    boolean addTags(ISSTagTreeUnit ttu) {
        return myTags.add(ttu);
    }

    ISSTagTreeUnit getTag(String tagname) {
        for (Iterator<ISSTagTreeUnit> iterator = myTags.iterator(); iterator.hasNext(); ) {
            ISSTagTreeUnit ttu = iterator.next();
            if (ttu.getTagName().equals(tagname))
                return ttu;
        }
        return null;
    }
}
