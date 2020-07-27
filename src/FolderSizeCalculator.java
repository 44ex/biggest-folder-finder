import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FolderSizeCalculator extends RecursiveTask<Long>{
    private File folder;

    public FolderSizeCalculator(File folder){
        this.folder = folder;
    }

    @Override
    protected Long compute(){ // у всех классов RecursiveTask есть метод compute()
        if(folder.isFile()){
            return folder.length();
        }

        long sum = 0;
        List<FolderSizeCalculator> subTasks = new LinkedList<>();

        File[] files = folder.listFiles();
        for(File file : files){
            FolderSizeCalculator task = new FolderSizeCalculator(file);
            task.fork(); // запускаем асинхронно, т.е. выделяем в отдельный поток
            subTasks.add(task);
        }

        for(FolderSizeCalculator task : subTasks){
            sum += task.join(); // дождемся выполнения задачи и прибавим результат
        }

        return sum;
    }
}
