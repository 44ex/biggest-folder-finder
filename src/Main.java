import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        //String folderPath = "C:\\Users\\Dmitry\\IdeaProjects";
        String folderPath = "C:\\Users\\Dmitry\\Desktop";
        File file = new File(folderPath);
        long start = System.currentTimeMillis();

        //System.out.println(getFolderSize(file));
        FolderSizeCalculator calculator = new FolderSizeCalculator(file);
        // класс, позволяющий запускать множество потоков
        ForkJoinPool pool = new ForkJoinPool();
        long size = pool.invoke(calculator);
        System.out.println(size);

        long duration = System.currentTimeMillis() - start;
        System.out.println(duration + " ms");

//        System.out.println(file.length());
//        System.out.println(getFolderSize(file));

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
