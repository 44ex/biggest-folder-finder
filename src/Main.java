import java.io.File;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        String folderPath = "C:\\Users\\Dmitry\\IdeaProjects";
        File file = new File(folderPath);
//        System.out.println(file.length());
        System.out.println(getFolderSize(file));

//        Set keys = System.getProperties().keySet();
//        for(Object key : keys){
//            System.out.println(key);
//        }
//        System.out.println(System.getProperties().get("user.dir"));
    }
    public static long getFolderSize(File folder){
        if(folder.isFile()){
            return folder.length();
        }

        long sum = 0;
        File[] files = folder.listFiles();
        for(File file : files){
            sum += getFolderSize(file);
        }
        return sum;
    }
}
