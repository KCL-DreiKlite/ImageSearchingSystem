# Welcome
Hello! Welcome to my page of Image Searching System(ISS)!

The reason I start this project is to find a way to sort and classify my image library, since there's thousands of images in that folder.

This is my first time to start mid-project by my own. Hope this project can keep updating.

## Goal
The goal of Image Searching System(ISS) is to provides these features:

* A Core that provides some basic methods such as Folder Initialization, Create JSONs Files, Transform 
* A Tag System that you can edit and control the tags.
* A Image System that you can append or deleate tags onto each image.
* A well looking and useful GUI that served the features above properly.

# Previous work
The purpose of this sector is to let me know where I was working on of my project.
### TODO:
* See problem viewer - TODO and XXX

# Version Information

The following is the version information of ISS.

### Ver. 0.0.6
* Change the reurn data type of ISSCore.makeFileCollection(File[]) to Arraylist\<File\>
* Add a method in core.ISSCore to get the date string in system date format.
* Add a logger in core.ISSCore.
* Create ISSException to handle unique exceptions of ISS.
* Create core.ISSImageFileUnit.getFileRelativePath() to get the relative pathname of an IFU.
* Let core.ISSCore handle the working folder pathname of the whole program by create a new varibale called <code>workingFolder</code> so every class in ISS can get the working folder pathname without invoke any argument of any method.<p>The reason to make separate this variable is to convert the file system to relative pathname, so the <code>ISSImageSystem</code> or other else classes can easily find out where's the specific file if the root pathname is not the same every time the program started.<p>Also, all the pathname should started with ".\" or "\" but ended without "\".
* <b>From now on, the file pathname system will use relative pathname in every usual-use methods. Any method still using absolute pathname will be rewrite. And also, any method need to invoke the pathname of working folder should call <code>core.ISSCore.getWorkingFolderPathname()</code> instead of defining the pathname by itself.</b>


### Ver. 0.0.5
* Add some constructors into core.ISSImageFileUnit. One of them is to resolve all attributes from given source jsonobject directly (see core.ISSImageFileUnit.ISSImageFileUnit(JSONObject, ISSTagTreeUnit)).
* Start writing non-static functions to core.ISSTagSystem.
* Created core.ISSImageSystem.
* Add a function in core.ISSImageFileUnit to provide the identity of an image file (core.ISSImageFileUnit.getIdentity()).
* 我受不了了我要開始寫中文了。
* 將 .issinfos\Tags.json 中的 Keys 改成 core.ISSTagSystem 的公開靜態型別，如將同一個 class 中的 transformToTTU() 和 transformToJSON() 中需要存取 json key 的字串("CONTAIN","TYPE"等)分離出來。
* 

### Ver. 0.0.4
* Simplify the content of .gitignore.
* Un-ignored the source code of org.json(src\org\json\\*).
* Change Info folder's path from ".\info" to ".\issinfos".
* Fixed some bugs in core.ISSTagSystem.transformToJSON(ttu).
* Add time-stamp in core.ISSImageFileUnit which is used for storing the time when that image file was added into this system. Also create some constructors related to the time-stamp in the same class.


### Ver. 0.0.3
* Separate exception handle functions of the constructors in core.ISSImageFileUnit into a single method (core.ISSImageFileUnit.checkFile()).
* Add addTag() and removeTag() into core.ISSImageFileUnit
* Add comments and Javadoc in core.ISSImageFileUnit.
* Add core.ISSCore.formatFileLength(File) which done the same thing as formatFileLength(Long) but the input argument is File.


### Ver. 0.0.2
* Merging files. Not important.

### Ver. 0.0.1
* Start to write READ.md and push onto github.
