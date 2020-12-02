import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FolderSizeCalculator extends RecursiveTask<Long>{
    private Node node;

    public FolderSizeCalculator(Node node){
        this.node   = node;
    }

    @Override
    protected Long compute(){ // у всех классов RecursiveTask есть метод compute()
        // начиная с корневой ноды, проверяем, является ли текущая нода файлом
        // если нет, получаем список всех файлов и папок внутри текущей
        // создаем для всех из них новые ноды, и добавляем в нашу исходную ноду
        // и по каждой отдельной ноде запускаем задачу, которая рекурсивно делает то же самое
        File folder = node.getFolder();
        if(folder.isFile()){
            long length = folder.length();
            node.setSize(length);
            return length;
        }

        long sum = 0;
        List<FolderSizeCalculator> subTasks = new LinkedList<>();

        File[] files = folder.listFiles();
        for(File file : files){
            Node child = new Node(file, node.getLimit());
            FolderSizeCalculator task = new FolderSizeCalculator(child);
            task.fork(); // запускаем асинхронно, т.е. выделяем в отдельный поток
            subTasks.add(task);
            node.addChild(child);
        }

        for(FolderSizeCalculator task : subTasks){
            sum += task.join(); // дождемся выполнения задач всех потоков и прибавим результат
        }

        node.setSize(sum);
        return sum; // сумма размеров (длин) всех файлов
    }
}
