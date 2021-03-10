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

    
    public static final String OBJECT_TYPE = "OBJECT";

    public static final String ARRAY_TYPE = "ARRAY";
    

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
        String jsonType = json.getString("TYPE");
        String jsonTagname = json.getString("TAGNAME");

        ISSTagTreeUnit ttu = new ISSTagTreeUnit(rootTree, jsonTagname);

        if (jsonType.equals(OBJECT_TYPE)) {
            JSONObject jsonContain = json.getJSONObject("CONTAIN");
            for (Iterator<String> iterator = jsonContain.keys(); iterator.hasNext(); ) {
                String key = iterator.next();
                ttu.append(transformToTTU(jsonContain.getJSONObject(key), ttu));
            }
        }
        else if (jsonType.equals(ARRAY_TYPE)) {
            JSONArray jsonContain = json.getJSONArray("CONTAIN");
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
                jsonContain.put(childttu.getTagName(), transformToJSON(childttu));
            }
            jsonResult.put("CONTAIN", jsonContain);
        }

        return jsonResult;
    }

    /** This method can only called by transformToJSON(ISSTagTreeUnit) */
    // private static JSONObject tftjson(ISSTagTreeUnit ttu) {
    //     if (ttu.isLeaf())
    //         return null;
    //     JSONObject jsonResult = new JSONObject();
    //     jsonResult.put("TAGNAME", ttu.getTagName());
        

    //     if (ttu.hasChild() ? ttu.getChild(0).isLeaf() : false) {
    //         jsonResult.put("TYPE", ARRAY_TYPE);
    //         JSONArray jsonContain = new JSONArray();

    //         for (Iterator<ISSTagTreeUnit> iterator = ttu.getChildrenList().iterator(); iterator.hasNext(); ) {
    //             jsonContain.put(iterator.next().getTagName());
    //         }
    //         jsonResult.put("CONTAIN", jsonContain);
    //     }
    //     else {
    //         jsonResult.put("TYPE", OBJECT_TYPE);
    //         JSONObject jsonContain = new JSONObject();

    //         for (Iterator<ISSTagTreeUnit> iterator = ttu.getChildrenList().iterator(); iterator.hasNext(); ) {
    //             ISSTagTreeUnit childttu = iterator.next();
    //             jsonContain.put(childttu.getTagName(), tftjson(childttu));
    //         }
    //         jsonResult.put("CONTAIN", jsonContain);
    //     }

    //     return jsonResult;
    // }


    public ISSTagSystem() {
        
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
