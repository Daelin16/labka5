import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>Класс, управляющий элементами коллекции</p>
 *
 * @author Воробьев Дмитрий
 * @version 1.0
 */
public class CollectionManager {
    static boolean save = true;

    /**
     * <p>Коллекция мюмл</p>
     */
    static Stack<Mumla> MumlaStack = new Stack<>();

    /**
     * <p>Объект класса Date, используется для вывода даты инициализации коллекции</p>
     */
    static Date date = new Date();

    /**
     * <p>Добавляет элемент в коллекцию</p>
     *
     * @param mumla Элемент типа Mumla
     * @return Результат добавления, true или false
     */
    public static boolean add(Mumla mumla) {
        boolean result;
        result = MumlaStack.add(mumla);
        Vector<Mumla> stack = new Vector<>(MumlaStack);
        //Collections.sort(stack);
        MumlaStack = new Stack<>();
        MumlaStack.addAll(stack);
        return result;
    }

    /**
     * <p>Выводит на экран все элементы коллекции</p>
     */
    public static void show() {
        for (Mumla current : MumlaStack) {
            System.out.println(current.toString());
        }
        if (MumlaStack.isEmpty()) System.out.println("Коллекция не содержит объектов");
    }

    /**
     * <p>Удаляет элемент из коллекции по имени</p>
     *
     * @param mumla Элемент типа Mumla
     */
    public static void remove(Mumla mumla) {
        Iterator<Mumla> iterator = MumlaStack.iterator();
        while (iterator.hasNext()) {
            for (Mumla a : MumlaStack) {
                iterator.next();
                if (a.equals(mumla)) {
                    iterator.remove();
                    break;
                }
            }
            break;
        }
    }

    /**
     * <p>Выводит информацию о коллекции</p>
     */
    public static void info() {
        System.out.println("Тип коллекции: Stack");
        System.out.println("Дата инициалтзации: " + date.toString());
        System.out.println("Количество элементов коллекции: " + MumlaStack.size());
    }

    /**
     * <p>Удаляет все элементы коллекции, меньше заданного</p>
     *
     * @param mumla Элемент типа Mumla
     */
    public static void remove_lower(Mumla mumla) {
        int size = MumlaStack.size();
        int count = 0;
        for (Iterator<Mumla> iterator = MumlaStack.iterator(); iterator.hasNext(); ) {
            if (iterator.next().compareTo(mumla) < 0) {
                iterator.remove();
                count += 1;
            }

        }
        if (size != MumlaStack.size()) System.out.println("Удалено элементов: " + count);
        else
            System.out.println("Нет элементов с меньшим значением, чем элемент c именем: "+ "\"" + mumla.getName() + "\"");
    }

    /**
     * <p>Добавляет элемент в коллeкцию, если его значение меньше, чем у минимального элемента</p>
     *
     * @param mumla Элемент типа Mumla
     * @return Результат добавления, true или false
     */
    public static boolean add_if_min(Mumla mumla) {
        boolean result = false;
        for (Mumla current : MumlaStack) {
            if (mumla.compareTo(current) < 0) {
                result = add(mumla);
            }
            break;
        }
        return result;
    }
    /**
     * <p>Закрывает программу</p>
     */
    public static void exit() {
        save = false;
        System.exit(0);
    }
    /**
     * <p>Выводит информацию о всех командах</p>
     */
    public static void help() {
        System.out.println("remove {element}: удалить элемент из коллекции по его значению\n" +
                "show: вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                "add_if_min {element}: добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции\n" +
                "remove_lower {element}: удалить из коллекции все элементы, меньше чем заданный\n" +
                "info: вывести в стандартный поток вывода информацию о коллекции \n" +
                "add {element}: добавить новый элемент в коллекцию\n" +
                "save: сохраняет элементы коллекции в файл\n" +
                "exit: выход без сохранения\n" +
                //"remove_first: удаляет первый элемент коллекции\n" +
                "Пример задания объекта: add {\"name\":\"myml\", \"height\":90, \"width\":50, \"weigth\":50, \"area\":JAIL \"troll\":{\"name\":\"myml\", \"height\":90, \"width\":50, \"weigth\":50, \"area\":JAIL}}");
    }


    public static void remove_first(Stack<Mumla> MumlaStack) {
        if (!MumlaStack.isEmpty()) {
            ArrayList<String> al = new ArrayList(MumlaStack);
            al.remove(0);

        }

    }

    public static void save(Stack<Mumla> MumlaStack, String filename) throws IOException {
        String fileName = "src/storage.csv";
        Path myPath = Paths.get(fileName);
        ArrayList list = new ArrayList(MumlaStack);
        //CSVWriter csvWriter = new CSVWriter(writer, '#', '\'');
        try (BufferedWriter writer = Files.newBufferedWriter(myPath,
                StandardCharsets.UTF_8)) {

            StatefulBeanToCsv<Mumla> beanToCsv = new StatefulBeanToCsvBuilder(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();
        beanToCsv.write(list);
        System.out.println("Коллекция сохранена");
        } catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException |
                IOException ex) {
           // Logger.getLogger(OpenCSVWriteBeansEx.class.getName()).log(
           //         Level.SEVERE, ex.getMessage(), ex);
      /*  List<Mumla> emps = OpenCSVParseToBeanExample.parseCSVWithHeader();

        List<String[]> data = toStringArray(emps);

        csvWriter.writeAll(data);

        csvWriter.close();

        System.out.println(writer);*/


    }
}}
