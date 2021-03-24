package priv.kcl.iss.core;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * The core tag system of Image Searching System(ISS). Provides taging to each image in
 * working folder and transform between JSON and TagTreeUnit(TTU).
 */
public class ISSTagSystem {

    /** The json key of {@code TYPE}. */
    public static final String KEY_TYPE = "TYPE";
    /** The json key of {@code TAGNAME}. */
    public static final String KEY_TAGNAME = "TAGNAME";
    /** The json key of {@code CONTAIN}. */
    public static final String KEY_CONTAIN = "CONTAIN";

    /** JSONObject type. */
    private static final String OBJECT_TYPE = "OBJECT";
    /** JSONArray type. */
    private static final String ARRAY_TYPE = "ARRAY";
    

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
        String jsonType = json.getString(KEY_TYPE);
        String jsonTagname = json.getString(KEY_TAGNAME);

        ISSTagTreeUnit ttu = new ISSTagTreeUnit(rootTree, jsonTagname);

        if (jsonType.equals(OBJECT_TYPE)) {
            JSONObject jsonContain = json.getJSONObject(KEY_CONTAIN);
            for (Iterator<String> iterator = jsonContain.keys(); iterator.hasNext(); ) {
                String key = iterator.next();
                ttu.append(transformToTTU(jsonContain.getJSONObject(key), ttu));
            }
        }
        else if (jsonType.equals(ARRAY_TYPE)) {
            JSONArray jsonContain = json.getJSONArray(KEY_CONTAIN);
            for (Iterator<?> iterator = jsonContain.iterator(); iterator.hasNext(); ) {
                ttu.append(new ISSTagTreeUnit(ttu, iterator.next().toString()));
            }
        }

        return ttu;
        
    }

    /**
     * Transform {@code ISSTagTreeUnit} to {@code JSONObject} format.
     * 
     * @param ttu the ISSTagTreeUnit need to be transformed
     * @return a standard tags format {@code JSONObject}
     */
    public static JSONObject transformToJSON(ISSTagTreeUnit ttu) {
        // return new JSONObject().put(ttu.getTagName(), tftjson(ttu));
        // return tftjson(ttu);
        if (ttu.isLeaf())
            return null;
        JSONObject jsonResult = new JSONObject();
        jsonResult.put(KEY_TAGNAME, ttu.getTagName());
        

        if (ttu.hasChild() ? ttu.getChild(0).isLeaf() : false) {
            jsonResult.put(KEY_TYPE, ARRAY_TYPE);
            JSONArray jsonContain = new JSONArray();

            for (Iterator<ISSTagTreeUnit> iterator = ttu.getChildrenList().iterator(); iterator.hasNext(); ) {
                jsonContain.put(iterator.next().getTagName());
            }
            jsonResult.put(KEY_CONTAIN, jsonContain);
        }
        else {
            jsonResult.put(KEY_TYPE, OBJECT_TYPE);
            JSONObject jsonContain = new JSONObject();

            for (Iterator<ISSTagTreeUnit> iterator = ttu.getChildrenList().iterator(); iterator.hasNext(); ) {
                ISSTagTreeUnit childttu = iterator.next();
                jsonContain.put(childttu.getTagName(), transformToJSON(childttu));
            }
            jsonResult.put(KEY_CONTAIN, jsonContain);
        }

        return jsonResult;
    }

    /** This method can only called by transformToJSON(ISSTagTreeUnit) */
    /* private static JSONObject tftjson(ISSTagTreeUnit ttu) {
        if (ttu.isLeaf())
            return null;
        JSONObject jsonResult = new JSONObject();
        jsonResult.put("TAGNAME", ttu.getTagName());
        

        if (ttu.hasChild() ? ttu.getChild(0).isLeaf() : false) {
            jsonResult.put("TYPE", ARRAY_TYPE);
            JSONArray jsonContain = new JSONArray();

            for (Iterator<ISSTagTreeUnit> iterator = ttu.getChildrenList().iterator(); iterator.hasNext(); ) {
                jsonContain.put(iterator.next().getTagName());
            }
            jsonResult.put("CONTAIN", jsonContain);
        }
        else {
            jsonResult.put("TYPE", OBJECT_TYPE);
            JSONObject jsonContain = new JSONObject();

            for (Iterator<ISSTagTreeUnit> iterator = ttu.getChildrenList().iterator(); iterator.hasNext(); ) {
                ISSTagTreeUnit childttu = iterator.next();
                jsonContain.put(childttu.getTagName(), tftjson(childttu));
            }
            jsonResult.put("CONTAIN", jsonContain);
        }

        return jsonResult;
    }*/


    /** All standard tags under TagTree. */
    private ISSTagTreeUnit standardTagTree;
    /** All extensions need to be ignored. */
    private ISSTagTreeUnit ignoredExtension;


    public ISSTagSystem(File tags) {
        
    }
    public ISSTagSystem(JSONObject standardTagTree, JSONObject ignoredExtension) {
        this.standardTagTree = transformToTTU(standardTagTree);
        this.ignoredExtension = transformToTTU(ignoredExtension);


    }

    /**
     * List every leaf
     * @return
     */
    public ArrayList<ISSTagTreeUnit> listAllStandardTags() {
        return listAllTags(standardTagTree.getChildrenList());
    }
    private ArrayList<ISSTagTreeUnit> listAllTags(ArrayList<ISSTagTreeUnit> childrenTags) {
        ArrayList<ISSTagTreeUnit> result = new ArrayList<ISSTagTreeUnit>();
        childrenTags.stream().forEach(ttu -> {
            if (ttu.isLeaf())
                result.add(ttu);
            else
                result.addAll(listAllTags(ttu.getChildrenList()));
        });
        return result;
    }

    public ArrayList<ISSTagTreeUnit> listAllIgnoredExtension() {
        ArrayList<ISSTagTreeUnit> result = new ArrayList<ISSTagTreeUnit>();
        ignoredExtension.getChildrenList().stream().forEach(ttu -> result.add(ttu));
        return result;
    }
    
}



// class ImageUnit {
    
//     File fileImagePath;

//     ArrayList<ISSTagTreeUnit> myTags;

//     ImageUnit(File imagePath, ArrayList<ISSTagTreeUnit> tags) {
        
//     }
//     ImageUnit(File imagePath) {

//     }



//     boolean addTags(ISSTagTreeUnit ttu) {
//         return myTags.add(ttu);
//     }

//     ISSTagTreeUnit getTag(String tagname) {
//         for (Iterator<ISSTagTreeUnit> iterator = myTags.iterator(); iterator.hasNext(); ) {
//             ISSTagTreeUnit ttu = iterator.next();
//             if (ttu.getTagName().equals(tagname))
//                 return ttu;
//         }
//         return null;
//     }
// }
