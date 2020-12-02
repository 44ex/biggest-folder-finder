import java.io.File;

public class ParametersBag {
    private long limit;
    private String path;

    public ParametersBag(String args[]){
        if(args.length != 4 ){
            throw new IllegalArgumentException("Specify two parameters: -l (size limit) and -d (path to folder)");
        }
        limit = 0;
        path = "";
        for(int i = 0; i < 4; i = i + 2){
            if(args[i].equals("-l"))
                limit = SizeCalculator.getSizeFromHumanReadable(args[i+1]);
            else
                if(args[i].equals("-d"))path = args[i+1];
        }

        if(limit <= 0) {
            throw new IllegalArgumentException("Limit not specified or specified incorrectly");
        }
        File folder = new File(path);
        if(!folder.exists() || !folder.isDirectory()) {
            throw new IllegalArgumentException("Folder path not specified or specified incorrectly");
        }
    }

    public long getLimit(){
        return limit;
    }

    public String getPath(){
        return path;
    }

}