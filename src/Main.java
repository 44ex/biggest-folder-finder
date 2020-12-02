import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
// как создать jar-файл, на отметке 4:58:00
// https://live.skillbox.ru/webinars/code/razrabotka-konsolnogo-prilozheniya/?key=study&utm_source=expertsender&utm_medium=email&utm_content=2020-07-24&utm_campaign=all_all_expertsender_email_calendar_students_all_code_skillbox&utm_term=calendar
public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            System.out.println(i + " => " + args[i]);
        }
        ParametersBag bag = new ParametersBag(args);

        //System.exit(0);

        String folderPath = "C:\\Users\\Dmitry\\IdeaProjects";
//        folderPath = "C:\\Users\\Dmitry\\Desktop";
//        folderPath = "C:\\Downloads";
//        folderPath = "C:\\Users\\Dmitry\\Desktop\\PROGR";
        folderPath = bag.getPath();

        long sizeLimit = 50 * 1024 * 1024;
        sizeLimit = bag.getLimit();

        File file = new File(folderPath);
        Node root = new Node(file, sizeLimit); // содержит дерево всех папок,
                                               // на которые указывает folderPath
        long start = System.currentTimeMillis();

        FolderSizeCalculator calculator = new FolderSizeCalculator(root);
        // класс, позволяющий запускать множество потоков
        ForkJoinPool pool = new ForkJoinPool();
//        long size = pool.invoke(calculator);
        pool.invoke(calculator);

        System.out.println("Path  " + folderPath);
        System.out.println("Bytes total: \t"   + root.getSize());
        System.out.println("Human readable:\t" + SizeCalculator.getHumanReadableSize(root.getSize()));
        System.out.println("Human to Bytes:\t" + SizeCalculator.getSizeFromHumanReadable(SizeCalculator.getHumanReadableSize(root.getSize())));

        System.out.println( root);

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
}
// для этого проекта создал репозиторий на GitHub
// github.com => New => biggest-folder-finder
// public - не будет требовать авторизации
// нажать HTTPS => скопировать путь к репозиторию =>
// https://github.com/44ex/biggest-folder-finder.git
// в идее выбрать строку с названием проекта =>
// VCS => Enable Version Control Integration => Git =>
// В этот момент инициализировался локальный депозиторий проекта
// VCS => Git => +Add
// VCS => Git => Commit Directory => [написать комментарий] =>
// нажать кнопку Commit = в этот момент текущ версия кода
// сохранилась в локальном репозитории проекта
// Чтобы отправить закомиченный код на GitHub
// VCS => Git => Remotes => В табличке Git Remotes
// выбрать репозиторий (а в первый раз - вставить путь к нему, взятый на GitHub)
// VCS => Git => Push
//

