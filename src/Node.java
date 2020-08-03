import java.io.File;
import java.util.ArrayList;

public class Node {
    private File folder;
    private ArrayList<Node> children;
    private long size;
    private int level;
    private long limit;

//    public Node(File folder)
//    {
//        this(folder);
//        this.limit = limit;
//    }

    public Node(File folder)
    {
        this.folder = folder;
        children = new ArrayList<>();
    }

    private long setLimit(long limit)
    {
        return limit;
    }

    public File getFolder()
    {
        return folder;
    }

    public void addChild(Node node)
    {
        //node.setLevel(level + 1);
        node.setLimit(limit);
        children.add(node);
    }

    public ArrayList<Node> getChildren()
    {
        return children;
    }

    public long getSize()
    {
        return size;
    }

    public void setSize(long size)
    {
        this.size = size;
    }

    public String toString(){
        return "";
    }

}
