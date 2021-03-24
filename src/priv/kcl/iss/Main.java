package priv.kcl.iss;


import java.util.logging.Logger;

import priv.kcl.iss.core.ISSCore;

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

        // System.err.println(ttu.getChild(0).getChild(0).getTagName());
        // t1ttu.remove("Marve");
        // System.err.println(ttu.getChild(0).getChild(0).getTagName());
        
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

        //     System.err.println(json.getJSONObject("tagTree").toString());

        //     ISSTagTreeUnit ttu = ISSTagSystem.transformToTTU(json.getJSONObject("tagTree"));

        //     JSONObject newJson = ISSTagSystem.transformToJSON(ttu);

        //     System.err.println(newJson.toString());

        //     // System.err.println(newJson.toString().equals(json.getJSONObject("tagTree").toString()));
        // }
        // catch (Exception e) {
        //     System.err.println(e.toString());
        // }
        // System.err.println(ISSCore.checkInfoFolderExist());

        // ISSCore.deleteInfoFolder();
        // SimpleDateFormat sdf = new SimpleDateFormat();
        // Date date = new Date();
        
        // sdf.applyPattern("yyyy/MM/dd hh:mm:ss");
        // System.err.println(sdf.format(date));
        // ISSCore.initialize(".\\test_folder", true);
        
        // try {
        //     ArrayList<ISSImageFileUnit> files = ISSCore.makeFileCollection(new File(".\\test_folder").listFiles());
        //     files.stream().forEach(file -> System.out.println( file.getFilePath()+"/"+file.getIdentity()));
            
        // }
        // catch (Exception e) {
        //     System.err.println(e.toString());
        // }

        // System.out.println("ii".hashCode());

        // ISSTagTreeUnit ttu1 = new ISSTagTreeUnit(null, "Hair");
        // ISSTagTreeUnit ttu2 = new ISSTagTreeUnit(ttu1, "HairColor");
        // ISSTagTreeUnit ttu3 = new ISSTagTreeUnit(ttu2, "Blue");
        // System.out.println(ttu3.getDisplayName());
        // System.out.println(ttu1.getAllParentPath());

        // ArrayList<String> hi = new ArrayList<String>();
        // hi.add("bruh");
        // hi.add("hehe");
        // hi.add("yee");

        // hi.stream().forEach(moo -> {
        //     System.out.println(moo.getClass() == Integer.class);
        // });

        // ISSCore.logger.info("Hello there");
        // ISSCore.logger.warning("Test warning");

        // FileHandler fh = new FileHandler(ISSCore.systemDate.getTime()+"log");
        // new File(".\\.log").mkdir();
        // File logFile = new File(".\\.log\\"+ISSCore.getDateInFormat(ISSCore.PATTERN_FOR_FILE)+".log");
        // try {
        //     FileHandler fh = new FileHandler(logFile.getAbsolutePath());
        //     fh.setFormatter(new Formatter() {
        //         @Override
        //         public String format(LogRecord record) {
        //             String bruh = "["+ISSCore.getDateInFormat(ISSCore.PATTERN_FOR_STRING)+"] "+
        //                           record.getLevel()+" / "+
        //                           "\""+record.getMessage()+"\""+
        //                           "\n";
        //             return bruh;
        //         }
        //     });
        //     fh.setLevel(Level.FINEST);
        //     ISSCore.logger.setLevel(Level.ALL);
        //     ISSCore.logger.addHandler(fh);
        //     ISSCore.logger.severe("This is a severe message");
        //     ISSCore.logger.config("This is a config message");
        //     ISSCore.logger.info("Why hello again");
        //     ISSCore.logger.info("Test info");
        //     ISSCore.logger.warning("Testing warning again");
        //     ISSCore.logger.fine("this is a fine msg");
        //     ISSCore.logger.finer("This msg is even finer");
        //     ISSCore.logger.finest("This is the finest message");
        // }
        // catch (Exception e) {
        //     System.err.println(e.toString());
        // }
        // ISSCore.logger.severe("This is a SEVERE message");
        // ISSCore.logger.config("This is a CONFIG message");
        // ISSCore.logger.warning("This is a WARNING message");
        // ISSCore.logger.info("This is an INFO message");
        // ISSCore.logger.fine("This is a FINE message");
        // ISSCore.logger.finer("This is a FINER message");
        // ISSCore.logger.finest("This is a FINNEST message");
        // ISSCore.startUpISS();
        // ISSCore.logger.severe("This is a SEVERE message");
        // ISSCore.logger.config("This is a CONFI\nG message");
        // ISSCore.logger.warning("This is a WARNI\nNG message");
        // ISSCore.logger.info("This is an INFO message");
        // ISSCore.logger.fine("This is a FINE message");
        // ISSCore.logger.finer("This is a FINER message");
        // ISSCore.logger.finest("This is a FINNEST\n message");
        // new Thread(() -> {
        //     ISSCore.logger.info("Testing");
        // }).start();
    }
}