package arraysOfSimilar;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Реализован абстрактный класс ArrayOfSimilar для дальнейшего наследования от него классов, которые будут хранить
 * филтрованные данные. Так же для удобства вызова методов у всех классов через цикл foreach
 * Методы addStatistic и showStatistic реализованы абстрактными, т.к. у них отличаются итоговые реализации для
 * работы со строками и числами
 */
public abstract class ArrayOfSimilar {

    protected ArrayList<String> list = new ArrayList<>();
    protected String outputPath;
    protected boolean writeMode;
    protected int statisticMode;
    protected int count;
    // Метод добавления входящей строки в общий список и дополнение статистики данными
    public void add(String str) {
        list.add(str);
        addStatistic(str);
    }

    // Метод дополнения переменных отвечающих за подсчет статистических данных
    public abstract void addStatistic(String str);

    // Метод вывода статистики
    public abstract void showStatistic();

    // Метод вывода полученных данных в файл
    public void toFile() {
        if(list.isEmpty()) {
            System.out.println("Данные для записи в файл отсутствуют.");
            return;
        }
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputPath, writeMode))) {
            for (String elem : list) {
                bufferedWriter.write(elem + "\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Путь не найден " + outputPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}