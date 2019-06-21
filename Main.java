
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Stack;

public class Main {

    public static void main(String args[]) {
        String filename = System.getenv().get("FILENAME");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (CollectionManager.save) {
                try {
                    CollectionManager.save(CollectionManager.MumlaStack, filename);

                } catch (IOException | NullPointerException e) {
                    System.err.println("Не удалось сохранить коллекцию");
                }
            } else System.out.println("Изменения не сохранены");
        }));


        //ParseCsv.parseCsv(CollectionManager.MumlaStack);
        ParseCsv parser = new ParseCsv();
        boolean error = true;
        try {
            String filestring = parser.readFile(filename);
            error = !filestring.isEmpty();
            parser.parseCsv(CollectionManager.MumlaStack);
        } catch (FileNotFoundException | NullPointerException e) {
            System.err.println("Файл не найден или к нему нет доступа");
            CollectionManager.exit();
        } catch (IOException  e) {
            if (error) {
                System.err.println("Ошибка xml файла");
                CollectionManager.exit();
            }
        }
        CommandHandler readeer = new CommandHandler();
        readeer.runCom();





        Homsa Homs = new Homsa("Homs", 50, 50, 90, Place.GRASSLAND);
        Mumla muml = new Mumla("princessa", 90, 79, 40, Place.ROOF);
        Mumla myml = new Mumla("myml", 90, 79, 40, Place.WATER);
        Mumla maml = new Mumla("maml", 90, 79, 40, Place.JAIL);
        Mumla moml = new Mumla("moml", 90, 79, 40, Place.GARDEN);

        Birds ptica = new Birds("ptica", 110, 20, 10, Place.ROOF);
        Birds ptaha = new Birds("ptaha", 110, 20, 10, Place.SHORE);
        Hemul hemul = new Hemul("hemul", 50, 50, 90, Place.GRASSLAND);
        Ads afisha = new Ads(Place.JAIL, 10);
        Ads afishka = new Ads(Place.SHORE, 7);

        try {
            hemul.read();
        } catch (AdNotFoundException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            {
            }
            class H extends Homsa {
                public void drow() {
                    System.out.println("Я художник я так вижу");
                }
            }
        }
    }
}