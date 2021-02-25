package priv.kcl.iss.core;

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

    public String getTagName() {
        return tagName;
    }

    public ISSTagTreeUnit getChildTree(int index) {
        return childTree.get(index);
    }
    public ISSTagTreeUnit getChildTree(String tagname) {
        ISSTagTreeUnit child = null;
        for (Iterator<ISSTagTreeUnit> iterator = childTree.iterator(); iterator.hasNext(); ) {
            child = iterator.next().find(tagname);
            if (child != null)
                break;
        }
        return child;
    }

    public boolean isLeaf() {
        return childTree.size() == 0;
    }
}