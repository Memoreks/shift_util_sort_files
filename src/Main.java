import arraysOfSimilar.ArrayOfFloats;
import arraysOfSimilar.ArrayOfIntegers;
import arraysOfSimilar.ArrayOfSimilar;
import arraysOfSimilar.ArrayOfStrings;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Основной класс программы для фильтрации входящих данных на строки, целые числа и вещественные числа
 */
public class Main {

    /**
     * @param args - входные параметры для запуска программы
     *             -s или -f - режим вывода статистики короткая или полная
     *             -a - включает режим дозаписи в итоговые файлы, по умолчанию - создание новых файлов
     *             -o /path - задает дополнительные директории в исходной директории для сохранения файлов
     *             -p sample1- - задает префикс в названии итоговых файлов
     *             *.txt - задается название исходных файлов
     */
    public static void main(String[] args) {
        //Массив для хранения путей к исходным файлам
        ArrayList<String> inputFilesName = new ArrayList<>();

        //-а режим дополнения выходных файлов
        boolean writeMode = false;
        /* -s краткая статистика или -f полная статистика
         * реалозовано с помощью int, а не с помощью boolean, т.к.
         * в будущем возможны дополнительные режимы вывода статистики
         */
        int statisticMode = 0;
        //-o указание директорий к путю итоговых файлов
        String output = "";
        //-p префикс к названию итоговых файлов
        String prefix = "";

        boolean isOutput = false;
        boolean isPrefix = false;
        //Разбор входных параметров
        for (String str : args) {
            //Проверяем что получен правильный параметр префикса или дополнительного пути
            if(!str.equals("-s") && !str.equals("-f") && !str.equals("-o") && !str.equals("-a") &&
                    !str.equals("-p") && !(str.contains(".txt"))) {
                if (isOutput) {
                    if (str.contains("/")) {
                        output = str;
                    }
                    isOutput = false;
                    continue;
                }
                if (isPrefix) {
                    prefix = str;
                    isPrefix = false;
                    continue;
                }
            }
            if (str.equals("-s")) {
                statisticMode = 0;
            } else if (str.equals("-f")) {
                statisticMode = 1;
            } else if (str.equals("-a")) {
                writeMode = true;
            } else if (str.contains(".txt")) {
                inputFilesName.add(str);
            } else if (str.equals("-o")) {
                isOutput = true;
            } else if (str.equals("-p")) {
                isPrefix = true;
            } else {
                System.out.println("Ошибка неизвестная команда " + str);
            }
        }

        //Директория запуска программы
        String currentDir = System.getProperty("user.dir");
        //Проверка наличия директории и при ее отсутствии создание нужных директории
        String[] directories = output.split("/");
        String outputPath = currentDir;
        for (int i = 1; i < directories.length; i++) {
            outputPath = outputPath + "/" + directories[i];
            File directory = new File(outputPath);
            if (!directory.exists()) {
                directory.mkdir();
            }
        }
        File directory = new File(outputPath);
        if (!directory.exists()) {
            System.out.println("Невозможно найти указанный путь сохранения файлов. Файлы не будут сохранены.");
        }

        //Массивы для фильтрованных данных
        ArrayOfStrings arrayOfStrings = new ArrayOfStrings(writeMode, statisticMode, prefix, outputPath);
        ArrayOfIntegers arrayOfIntegers = new ArrayOfIntegers(writeMode, statisticMode, prefix, outputPath);
        ArrayOfFloats arrayOfFloats = new ArrayOfFloats(writeMode, statisticMode, prefix, outputPath);

        List<ArrayOfSimilar> arrayOfSimilars = new ArrayList<>();
        arrayOfSimilars.add(arrayOfStrings);
        arrayOfSimilars.add(arrayOfIntegers);
        arrayOfSimilars.add(arrayOfFloats);

        //Построчное чтение файлов поочереди
        for (int i = 0; i < inputFilesName.size(); i++) {
            try {
                String str;
                BufferedReader bufferedReader = new BufferedReader(
                        new FileReader(inputFilesName.get(i), StandardCharsets.UTF_8));
                while (bufferedReader.ready()) {
                    str = bufferedReader.readLine();
                    switch (typeInString(str)) {
                        case "str": {
                            arrayOfStrings.add(str);
                            break;
                        }
                        case "int": {
                            arrayOfIntegers.add(str);
                            break;
                        }
                        case "float": {
                            arrayOfFloats.add(str);
                            break;
                        }
                        //Пустые строки пропускаем
                        case "null": {
                            break;
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("Возникла ошибка при чтении файла " + inputFilesName.get(i));
            } catch (IOException e) {
                System.out.println("Возникла ошибка: " + e.getMessage());
            }
        }

        for (ArrayOfSimilar array : arrayOfSimilars) {
            //Вывод отфильтрованных данных в файл
            array.toFile();
            //Вывод статистики в консоль
            array.showStatistic();
        }
    }

    //Определение содержимого в строке
    public static String typeInString(String str) {
        if (str.isEmpty()) {
            return "null";
        }
        if (str.matches("^-?[0-9]*$")) {
            return "int";
        } else if (str.matches("^-?[0-9]{1,}.[0-9]{0,}[e]?[E]?-?[0-9]{1,}?$")) {
            return "float";
        } else {
            return "str";
        }
    }
}