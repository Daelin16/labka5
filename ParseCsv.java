import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ParseCsv {


    public ParseCsv()  {
    }

    /**
     * <p>Считывает данные с файла</p>
     *
     * @param filename Имя считываемого файла
     * @return Строка с данными из файла
     * @throws FileNotFoundException При считывании с несуществующего файла
     */
     static String readFile(String filename) throws FileNotFoundException, NullPointerException {
        FileReader reader = new FileReader(filename);
        Scanner scanner = new Scanner(reader);
        String filestrings = "";
        while (scanner.hasNextLine()) {
            filestrings += scanner.nextLine() + "\n";
        }
        try {
            reader.close();
        } catch (IOException e) {
            System.err.println("Ошибка");
        }
        return filestrings;
    }


    public static String readStrings(String path) throws FileNotFoundException {
        String in = "";

        File file = new File(path);
        if (file.exists()) {
            try (BufferedInputStream fileInput = new BufferedInputStream(new FileInputStream(file))) {
                String currentLine = "";
                int i;
                while ((i = fileInput.read()) != -1) {
                    char c = (char) i;
                    in += c;
                }
            } catch (FileNotFoundException e) {
                throw new FileNotFoundException();
            } catch (IOException e) {
                System.err.println("Ой-ой-ой");
            }
        } else {
            throw new FileNotFoundException();
        }

        return in.trim();
    }
    public static void upload(String filestrings) {
        String[] line = filestrings.split("\n");
        try {
            for (int i = 0; i < line.length; i++) {
                String[] parts = line[i].replace("\"", "").split(",");
                int age = Integer.parseInt(parts[0]);
                String name = parts[1];
                Place area = Place.valueOf(parts[2]);
                int height = Integer.parseInt(parts[3]);
                int width = Integer.parseInt(parts[4]);
                int weight = Integer.parseInt(parts[5]);




                CollectionManager.add(new Mumla( name, height, width, weight, area));

            }
            System.out.println("File uploaded");
        } catch (Exception e) {
            System.out.println("File error");
        }
    }

    public static void parseCsv(Stack<Mumla> mumlaStack){

        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader("storage.csv"), ',');
        } catch (FileNotFoundException e) {
            System.err.println("Я пидорас");
        }
        ColumnPositionMappingStrategy<Mumla> beanStrategy = new ColumnPositionMappingStrategy<Mumla>();
        beanStrategy.setType(Mumla.class);
        beanStrategy.setColumnMapping(new String[] {"name","height","width","weight"});

        CsvToBean<Mumla> csvToBean = new CsvToBean<Mumla>();

       List<Mumla> mumlas = csvToBean.parse(beanStrategy, reader);
        for(int i=0; i < mumlas.size(); i++) {
            try {
                CollectionManager.MumlaStack.add(MumlaFactory.createMumla(readFile("storage.csv"))) ;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
        //CollectionManager.MumlaStack = new Stack<Mumla>();
        CollectionManager.MumlaStack.addAll(mumlas);
        ArrayList arrayList = new ArrayList(CollectionManager.MumlaStack);
        for(Object element : arrayList) {
            CollectionManager.add((Mumla) element);
        }

    }

}