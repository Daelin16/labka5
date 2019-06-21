import com.google.gson.JsonSyntaxException;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * <p>Класс для считывания и выполнения команд</p>
 *
 * @author Воробьёв Дмитрий
 * @version 1.0
 */
public class CommandHandler {
    /**
     * <p>Имя команды</p>
     */
    String command = "";
    /**
     * <p>Объект команды</p>
     */
    Mumla current_object = new Mumla();

    /**
     * <p>Считает количество заданных символов в строке</p>
     *
     * @param word Строка
     * @param s    Символ
     * @return Количество
     */
    public int charCounter(String word, char s) {
        int count = 0;
        for (char element : word.toCharArray()) {
            if (element == s) count += 1;
        }
        return count;
    }

    private int charCounter(String word, char s, char e) {
        int countS = 0;
        int countE = 0;
        for (char el : word.toCharArray()) {
            if (countE % 2 == 0) {
                if (el == s) countS += 1;
            }
            if (el == e) countE += 1;
        }
        return countS;
    }

    String filename = System.getenv().get("FILENAME");

    /**
     * <p>Считывает и запускает команды</p>
     */
    public void runCom() {
        System.out.println("Используйте команду help, чтобы получить информацию о командах");
        while (true) {
            String object = "";
            String line = "";
            Scanner scanner = new Scanner(System.in);
            int count;
            boolean command_end;
            boolean cur_command = false;

            while (!cur_command) {
                System.out.println("Введите команду ");
                try {
                    line = scanner.nextLine().trim();
                } catch (NoSuchElementException e) {
                    System.exit(0);
                }
                if (line.equals("show") || line.startsWith("remove {") || line.startsWith("add_if_min {") || line.startsWith("remove_lower {") || line.equals("info") || line.startsWith("remove_first {") || line.startsWith("add {") || line.equals("help") || line.equals("exit") || line.equals("save")) {
                    cur_command = true;
                    command_end = false;
                } else {
                    System.err.println("Неизвестная команда или неверный формат команды ");
                    command_end = true;
                }

                if (line.startsWith("show") || line.startsWith("info") || line.startsWith("help") || line.startsWith("save") || line.startsWith("exit")) {
                    command_end = true;
                    command = line;
                }

                while (!command_end) {
                    count = 0;
                    if (charCounter(line, '{', '\"') != charCounter(line, '}', '\"') || charCounter(line, '}', '\"') == 0 || charCounter(line, '{', '\"') == 0) {
                        line += scanner.nextLine();
                        count += charCounter(line, '{');
                        count -= charCounter(line, '}');
                    }
                    if (charCounter(line, '{', '\"') < charCounter(line, '}', '\"')) {
                        System.err.println("Ошибка в написании json строки, попробуйте заново");
                        cur_command = false;
                        line = "";
                        break;
                    } else {
                        if (count == 0) {
                            command_end = true;
                            try {
                                String a[] = line.split(" ", 2);
                                if (!(a[0].equals("remove") || a[0].equals("add_if_min") || a[0].equals("remove_lower") || a[0].equals("info") || a[0].equals("remove_first") || a[0].equals("add"))) {
                                    throw new Exception();
                                }
                                object = a[1];
                                command = a[0];
                            } catch (Exception e) {
                                System.err.println("Неизвестная команда или не верный формат команды");
                                line = "";
                                cur_command = false;
                            }
                        }
                    }
                }

            }
            if (!command.equals("import") && !command.equals("show") && !command.equals("help") && !command.equals("info") && !command.equals("exit") && !command.equals("save")) {
                try {
                    current_object = (Mumla) MumlaFactory.createMumla(object);
                } catch (NumberFormatException e) {
                    System.err.println("Неверный формат атрибута");
                    continue;
                } catch (ClassNotFoundException e) {
                    System.err.println("Атрибут класс не найден");
                    continue;
                } catch (JsonSyntaxException e) {
                    System.err.println("Ошибка синтаксиса json");
                    continue;
                }
            }


            switch (command) {
                case ("remove"): {
                    CollectionManager.remove(current_object);
                }
                break;
                case ("show"): {
                    CollectionManager.show();
                }
                break;
                case ("add_if_min"): {
                    if (CollectionManager.add_if_min(current_object))
                        System.out.println("Элемент добавлен в коллекцию");
                    else System.out.println("Элемент не добавлен в коллекцию");
                }
                break;
                case ("remove_lower"): {
                    CollectionManager.remove_lower(current_object);
                }
                break;
                case ("info"): {
                    CollectionManager.info();
                }
                break;
                case ("add"): {
                    if (CollectionManager.add(current_object)) System.out.println("Элемент добавлен в коллекцию");
                    else System.out.println("Элемент не добавлен в коллекцию");
                }
                break;
                case ("help"): {
                    CollectionManager.help();
                }
                break;
                case ("remove_first"): {
                    //object = object.replace("{", "").replace("}", "");
                    CollectionManager.remove_first(CollectionManager.MumlaStack);
                }
                break;
                case ("exit"): {
                    CollectionManager.exit();
                }
                break;
                case ("save"): {
                    try {
                        CollectionManager.save(CollectionManager.MumlaStack, filename);
                    } catch (IOException | NullPointerException e) {
                        System.err.println("Не удалось сохранить коллекцию");
                    }
                }
            }
        }
    }
}
