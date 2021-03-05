package priv.kcl.iss;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import org.json.JSONObject;

import priv.kcl.iss.core.ISSCore;
import priv.kcl.iss.core.ISSTagSystem;
import priv.kcl.iss.core.ISSTagTreeUnit;

public class Main {
    public static void main(String[] args) {
        System.err.println("Hello world!");

        // ArrayList<Integer> i = new ArrayList<Integer>();
        // i.add(0);
        // if (i.size() != 0? i.get(0) == 0: false) {
        //     System.err.println("true");
        // }
        // System.err.println("false");
        // InitializeFolder.findInformationFiles();

        // ISSTagTreeUnit ttu = new ISSTagTreeUnit(null, "RootTree");

        // ISSTagTreeUnit t1ttu = new ISSTagTreeUnit(ttu, "Some name");
        // t1ttu.append(new ISSTagTreeUnit(t1ttu, "Marven"));
        // t1ttu.append(new ISSTagTreeUnit(t1ttu, "Charlie"));

        // ISSTagTreeUnit t2ttu = new ISSTagTreeUnit(ttu, "Some words");
        // t2ttu.append(new ISSTagTreeUnit(t2ttu, "Damn bro"));
        // t2ttu.append(new ISSTagTreeUnit(t2ttu, "This is suck"));

        // ISSTagTreeUnit t3ttu = new ISSTagTreeUnit(ttu, "Marven");

        // // ISSTagTreeUnit t31ttu = new ISSTagTreeUnit(t3ttu, "Marven");

        // ttu.append(t1ttu);
        // ttu.append(t2ttu);
        // ttu.append(t3ttu);

        // System.err.println(ttu.getChildTree(0).getChildTree(0).getTagName());
        // t1ttu.remove("Marve");
        // System.err.println(ttu.getChildTree(0).getChildTree(0).getTagName());
        
        // File f = new File("..\\projectIdea\\bruh.json");
        // try {
        //     BufferedReader br = new BufferedReader(new FileReader(f));
        //     JSONObject hehe = new JSONObject(br.readLine());
        //     br.close();
        //     System.err.println(hehe.toString());
        // }
        // catch (Exception e) {

        // }

        // ISSTagSystem.transformToTTU(new JSONObject("source"));
        // try {
        //     BufferedReader br = new BufferedReader(new FileReader(new File("..\\projectIdea\\Tags.json")));
        //     JSONObject json = new JSONObject(br.readLine());
        //     br.close();

        //     System.err.println(json.toString());

        //     ISSTagTreeUnit ttu = ISSTagSystem.transformToTTU(json.getJSONObject("tagTree"));

        //     JSONObject newJson = ISSTagSystem.transformToJSON(ttu);

        //     System.err.println(newJson.toString());

        //     // System.err.println(newJson.toString().equals(json.getJSONObject("tagTree").toString()));
        // }
        // catch (Exception e) {
        //     System.err.println(e.toString());
        // }
        long hi = 1024*1024*1024*1024;
        System.err.println(hi);
        System.err.println(ISSCore.formatFileLength(hi));
    }
}