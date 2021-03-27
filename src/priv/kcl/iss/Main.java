package priv.kcl.iss;


import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Logger;

import priv.kcl.iss.core.ISSCore;
import priv.kcl.iss.core.ISSException;
import priv.kcl.iss.core.ISSImageFileUnit;

public class Main {
    public static void main(String[] args) {
        System.err.println("Hello world!");

        ISSCore.deleteLogFolder();
        ISSCore.startUpISS(new File(".\\test_folder"));

        ISSCore.logger.info("Working_folder="+ISSCore.getWorkingFolderPathname());

        ArrayList<File> fileList = ISSCore.makeFileCollection(ISSCore.getWorkingFolder().listFiles());
        ArrayList<ISSImageFileUnit> ifuList = new ArrayList<ISSImageFileUnit>();

        for (Iterator<File> iterator = fileList.iterator(); iterator.hasNext(); ) {
            File tmpFile = iterator.next();
            try {
                ISSImageFileUnit ifu = new ISSImageFileUnit(tmpFile);
                ifuList.add(ifu);
                // ISSCore.logger.fine("Filename="+ifu.getFilename()+"\tFile_ext="+ifu.getFileExtension()+"\tFile_without_ext="+ifu.getFilenameWithoutExtension());
                ISSCore.logger.fine("absPath=\""+ifu.getFileAbsolutePath()+"\"\trltPath=\""+ifu.getFileRelativePath()+"\"");
            }
            catch (Exception e) {
                ISSCore.logger.severe(e.getMessage());
            }
        }

    }
}