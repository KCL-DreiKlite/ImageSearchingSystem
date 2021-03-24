package priv.kcl.iss.core;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * A tree data type for tags.
 */
public class ISSTagTreeUnit {
    private ISSTagTreeUnit rootTree;
    private String tagName;
    private ArrayList<ISSTagTreeUnit> childTree = new ArrayList<ISSTagTreeUnit>();

    /**
     * Create a leaf.
     * 
     * @param rootTree the father of this leaf
     * @param tagname the name of this leaf
     */
    public ISSTagTreeUnit(ISSTagTreeUnit rootTree, String tagname) {
        this.rootTree = rootTree;
        this.tagName = tagname;
        
    }
    /**
     * Create a subtree.
     * 
     * @param rootTree the father of this tree
     * @param tagname the name of this root leaf
     * @param childTree the children of this tree
     */
    public ISSTagTreeUnit(ISSTagTreeUnit rootTree, String tagname, ArrayList<ISSTagTreeUnit> childTree) {
        this.rootTree = rootTree;
        this.tagName = tagname;
        this.childTree = childTree;
    }

    /**
     * Append child leaf/tree into tree.
     * 
     * @param child the leaf/tree you want to add for
     */
    public void append(ISSTagTreeUnit child) {
        childTree.add(child);
    }
    /**
     * Append child leaf into tree.
     * 
     * @param tag the tagname you want to append
     */
    public void append(String tag) {
        childTree.add(new ISSTagTreeUnit(this, tag));
    }
    /**
     * Append child leaf into tree.
     * 
     * @param tag the tagname you want to append
     * @param root specific the root of the child leaf
     */
    public void append(String tag, ISSTagTreeUnit root) {
        childTree.add(new ISSTagTreeUnit(root, tag));
    }

    /**
     * Remove child leaf/tree from tree.
     * 
     * @param child the leaf/tree you want to remove
     * @return {@code true} if there exist the specific child leaf/tree. Otherwise return {@code false}.
     */
    public boolean remove(ISSTagTreeUnit child) {
        return childTree.remove(child);
    }
    /**
     * Remove child leaf/tree from tree.
     * 
     * @param childTagname the leaf/tree root name you want to remove
     * @return {@code true} if there exist the specific child leaf/tree. Otherwise return {@code false}.
     */
    public boolean remove(String childTagname) {
        int currentIndex = -1;
        if (childTree.get(++currentIndex).getTagName().equals(childTagname)) {
            childTree.remove(currentIndex);
            return true;
        }
        return false;
    }

    /**
     * Find leaf by given TagName. This method will search every child and every child of their child, and
     * so on.
     * 
     * @param tagname the name you're looking for
     * @return the closest result of searching one by one. If the name no found, return {@code null}
     */
    public ISSTagTreeUnit find(String tagname) {
        if (tagName.equals(tagname))
            return this;
        else {
            ISSTagTreeUnit child = null;
            for (Iterator<ISSTagTreeUnit> iterator = childTree.iterator(); iterator.hasNext(); ) {
                child = iterator.next().find(tagname);
                if (child != null)
                    break;
            }
            return child;
        }
    }


    public ISSTagTreeUnit getRootTree() {
        return rootTree;
    }

    /**
     * Get the tag name.
     * @return
     */
    public String getTagName() {
        return tagName;
    }

    /**
     * Get a String for display.
     * <p>
     * The structure will be "{@code rootTree.tagName}/{@code tagName}". For example, if there's a tagtree
     * as the structure below:
     * <p>
     * <code>Hair -> HairColor -> Blue</code>
     * <p>
     * , and we want to get the display name of the lowest tree (which is {@code Blue}), then it'll return
     * {@code "HairColor/Blue"}. But if we want to get the display name of top root, then it'll return
     * {@code "null/Hair"} since the root element of {@code Hair} equals to {@code null}.
     * @return
     */
    public String getDisplayName() {
        if (rootTree == null)
            return "null/"+tagName;
        else
            return rootTree.tagName+"/"+tagName;
    }

    /**
     * Get a path which shows where this tag is. The path is started from top-root tree, and the destnation
     * is this tagtree.
     * <p>
     * For example, if there exist a tagtree constructed by
     * <p>
     * <code>Hair -> HairColor -> Blue</code>
     * <p>
     * , then the returned string should be {@code "Hair/HairColor/Blue"}.
     * @return
     */
    public String getAllParentPath() {
        ISSTagTreeUnit ttu = rootTree;
        String result = tagName;
        while (ttu != null) {
            result = ttu.tagName+"/"+result;
            ttu = ttu.rootTree;
        }
        return result;
    }

    /**
     * Get child by specific arraylist index.
     * 
     * @param index the arraylist index
     * @return the corresponding TTU. If index out of range, return {@code null}
     */
    public ISSTagTreeUnit getChild(int index) {
        if (0 > index || index >= childTree.size())
            return null;
        return childTree.get(index);
    }
    /**
     * Get child by specific arraylist index.
     * 
     * @param tagname the tagname which we want to find out
     * @return the corresponding TTU. If the specific ttu doesn't exist, return {@code null}
     */
    public ISSTagTreeUnit getChild(String tagname) {
        ISSTagTreeUnit child = null;
        for (Iterator<ISSTagTreeUnit> iterator = childTree.iterator(); iterator.hasNext(); ) {
            child = iterator.next().find(tagname);
            if (child != null)
                break;
        }
        return child;
    }

    /***
     * Get the Arraylist of my children.
     * @return
     */
    public ArrayList<ISSTagTreeUnit> getChildrenList() {
        return childTree;
    }

    /**
     * Find out if this tree is a leaf.
     * @return {@code true} if I don't have any children. Otherwise return {@code false}
     */
    public boolean isLeaf() {
        return childTree.size() == 0;
    }
    /**
     * Find out if I'm the top of the whole tree.
     * @return {@code true} if my root is null. Otherwise return {@code false}
     */
    public boolean isTopRoot() {
        return rootTree == null;
    }

    /**
     * Find out if I have any child.
     * @return {@code true} if I got at least one child tree. Otherwise return {@code false}
     */
    public boolean hasChild() {
        return childTree.size() != 0;
    }

    public String toString() {
        String result = "";
        result += "[Root: "+(isTopRoot() ? null : rootTree.tagName)+", Tag: "+tagName+", children: {";
        if (!isLeaf())
            result += "\""+childTree.get(0).tagName+"\"";
        for (int index = 1; index < childTree.size(); index++) {
            result += ",\""+childTree.get(index).tagName+"\"";
        }
        result += "}]";
        return result;
    }

    public void printTreeToConsole() {

    }
    private void pttc(ISSTagTreeUnit ttu) {
        
    }
}