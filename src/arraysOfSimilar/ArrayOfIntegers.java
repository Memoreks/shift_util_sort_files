package arraysOfSimilar;

public class ArrayOfIntegers extends ArrayOfSimilar {

    private Long min;
    private Long max;
    private Long sum = 0L;
    private Long average;

    public ArrayOfIntegers(boolean writeMode, int statisticMode, String prefix, String output) {
        this.writeMode = writeMode;
        this.statisticMode = statisticMode;
        this.outputPath = output + "/" + prefix + "integers.txt";
    }

    @Override
    public void addStatistic(String str) {
        count++;
        if (min == null || min > Long.valueOf(str)) {
            min = Long.valueOf(str);
        } if (max == null || max < Long.valueOf(str)) {
            max = Long.valueOf(str);
        }
        sum = sum + Long.valueOf(str);
        average = sum / count;
    }

    @Override
    public void showStatistic() {
        System.out.println("------ Статистика по обработанным целым числам ------");
        if (count == 0) {
            System.out.println("В исходном наборе данных целых чисел не обнаружено.");
            System.out.println(" ");
            return;
        }
        System.out.println("Всего целых чисел: " + count);
        if (statisticMode != 0) {
            System.out.println("Максимальное целое число: " + max);
            System.out.println("Минимальное целое число: " + min);
            System.out.println("Среднее целое число: " + average);
            System.out.println("Сумма целых чисел: " + sum);
        }
        System.out.println(" ");
    }
}