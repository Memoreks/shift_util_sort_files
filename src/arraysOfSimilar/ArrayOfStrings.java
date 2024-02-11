package arraysOfSimilar;

public class ArrayOfStrings extends ArrayOfSimilar {
    private Integer min;
    private Integer max;

    public ArrayOfStrings(boolean writeMode, int statisticMode, String prefix, String output) {
        this.writeMode = writeMode;
        this.statisticMode = statisticMode;
        this.outputPath = output + "/" + prefix + "strings.txt";
    }

    @Override
    public void addStatistic(String str) {
        count++;
        if (min == null || min > str.length()) {
            min = str.length();
        } if (max == null || max < str.length()) {
            max = str.length();
        }
    }

    @Override
    public void showStatistic() {
        System.out.println("------ Статистика по обработанным строкам ------");
        if (count == 0) {
            System.out.println("В исходном наборе данных строк не обнаружено.");
            System.out.println(" ");
            return;
        }
        System.out.println("Всего строк: " + count);
        if (statisticMode != 0) {
            System.out.println("Максимальная длина строки: " + max);
            System.out.println("Минимальная длина строки: " + min);
        }
        System.out.println(" ");
    }
}