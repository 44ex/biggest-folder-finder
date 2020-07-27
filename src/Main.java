import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        //String folderPath = "C:\\Users\\Dmitry\\IdeaProjects";
        //String folderPath = "C:\\Users\\Dmitry\\Desktop";
        String folderPath = "C:\\Downloads";
        File file = new File(folderPath);
        long start = System.currentTimeMillis();

        //System.out.println(getFolderSize(file));
        FolderSizeCalculator calculator = new FolderSizeCalculator(file);
        // класс, позволяющий запускать множество потоков
        ForkJoinPool pool = new ForkJoinPool();
        long size = pool.invoke(calculator);
        System.out.println(size);
        System.out.println(getHumanReadableSize(size));

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
//    // Вариант 1
//    public static String getHumanReadableSize(long size){
//        String[] sizeMarks = {"B","Kb","Mb","Gb","Tb"};
//        for(int i=0; i<sizeMarks.length; i++){
//            double value = ((double)size)/Math.pow(1024,i);
//            if(value < 1024){
//                return String.format("%.2f%s",value, sizeMarks[i]);
//            }
//        }
//        return "Размер от пентабайта и больше";
//    }
//-----------------------------------------------------------------------------------
////    // Вариант 2
//    public static String getHumanReadableSize(long size){
//        String[] sizeMarks = {"B","Kb","Mb","Gb","Tb"};
//        String strSize = Long.toString(size);
//        int i;
//        if(strSize.length() <= 3)i=0;
//        else
//        if(strSize.length() <= 6)i=1;
//        else
//        if(strSize.length() <= 9)i=2;
//        else
//        if(strSize.length() <=12)i=3;
//        else
//        if(strSize.length() <=15)i=4;
//        else
//            return "Размер от пентабайта и больше";
//        return String.format("%.2f%s",(double)size/Math.pow(1024,i), sizeMarks[i]);
//    }
//-----------------------------------------------------------------------------------
//    // Вариант 3
public static String getHumanReadableSize(long size){
    String[] sizeMarks = {"B","Kb","Mb","Gb","Tb"};
    String strSize = Long.toString(size);
    for(int i=0; i<5; i++) {
        if (strSize.length()/(3*(i+1)) == 0) {
            return String.format("%.2f%s",(double)size/Math.pow(1024,i), sizeMarks[i]);
        }
    }
    return "Размер от пентабайта и больше";
}
//-----------------------------------------------------------------------------------

}
