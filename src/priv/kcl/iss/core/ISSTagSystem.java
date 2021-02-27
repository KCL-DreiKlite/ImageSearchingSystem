package priv.kcl.iss.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONWriter;

/**
 * The core tag system of Image Searching System(ISS). Provides taging to each image in
 * working folder and transform between JSON and TagTreeUnit(TTU).
 */
public class ISSTagSystem {

    /** */
    public static final String OBJECT_TYPE = "object";

    public static final String ARRAY_TYPE = "array";
    
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
    /**
     * Transform {@code JSONObject} to {@code ISSTagTreeUnit} format.
     * 
     * @param json the JSONObject need to be transformed
     * @return a full tree constructed in {@code ISSTagTreeUnit} format which the root
     * will be {@code null}
     */
    public static ISSTagTreeUnit transformToTTU(JSONObject json) {
        return transformToTTU(json, null);
    }
    /**
     * Transform {@code JSONObject} to {@code ISSTagTreeUnit} format.
     * 
     * @param json the JSONObject need to be transformed
     * @param rootTree the transformed tree will be attached under this root
     * @return a full tree constructed in {@code ISSTagTreeUnit} format
     */
    public static ISSTagTreeUnit transformToTTU(JSONObject json, ISSTagTreeUnit rootTree) {
        String jsonType = json.getString("type");
        String jsonTagname = json.getString("tagname");

        ISSTagTreeUnit ttu = new ISSTagTreeUnit(rootTree, jsonTagname);

        if (jsonType.equals(OBJECT_TYPE)) {
            JSONObject jsonContain = json.getJSONObject("contain");
            for (Iterator<String> iterator = jsonContain.keys(); iterator.hasNext(); ) {
                String key = iterator.next();
                ttu.append(transformToTTU(jsonContain.getJSONObject(key), ttu));
            }
        }
        else if (jsonType.equals(ARRAY_TYPE)) {
            JSONArray jsonContain = json.getJSONArray("contain");
            for (Iterator<?> iterator = jsonContain.iterator(); iterator.hasNext(); ) {
                ttu.append(new ISSTagTreeUnit(ttu, iterator.next().toString()));
            }
        }

        // System.err.println(ttu.toString());
        

        return ttu;
        
    }

    /**
     * 
     * @param ttu
     * @return
     */
    public static JSONObject transformToJSON(ISSTagTreeUnit ttu) {
        return new JSONObject().put(ttu.getTagName(), tftjson(ttu));
    }
    /** This method can only called by transformToJSON(ISSTagTreeUnit) */
    private static JSONObject tftjson(ISSTagTreeUnit ttu) {
        if (ttu.isLeaf())
            return null;
        JSONObject jsonResult = new JSONObject();
        jsonResult.put("tagname", ttu.getTagName());
        

        if (ttu.hasChild() ? ttu.getChild(0).isLeaf() : false) {
            jsonResult.put("type", ARRAY_TYPE);
            JSONArray jsonContain = new JSONArray();

            for (Iterator<ISSTagTreeUnit> iterator = ttu.getChildrenList().iterator(); iterator.hasNext(); ) {
                jsonContain.put(iterator.next().getTagName());
            }
            jsonResult.put("contain", jsonContain);
        }
        else {
            jsonResult.put("type", OBJECT_TYPE);
            JSONObject jsonContain = new JSONObject();

            for (Iterator<ISSTagTreeUnit> iterator = ttu.getChildrenList().iterator(); iterator.hasNext(); ) {
                ISSTagTreeUnit childttu = iterator.next();
                jsonContain.put(childttu.getTagName(), transformToJSON(childttu));
            }
            jsonResult.put("contain", jsonContain);

        }

        JSONObject result = new JSONObject();
        // result.put(ttu.getTagName(), jsonResult);
        // System.err.println(result.toString());
        return jsonResult;
        
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
