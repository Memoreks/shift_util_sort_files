package arraysOfSimilar;

public class ArrayOfFloats extends ArrayOfSimilar {
    private Float min;
    private Float max;
    private Float sum = (float) 0;
    private Float average;

    public ArrayOfFloats(boolean writeMode, int statisticMode, String prefix, String output) {
        this.writeMode = writeMode;
        this.statisticMode = statisticMode;
        this.outputPath = output + "/" + prefix + "floats.txt";
    }

    @Override
    public void addStatistic(String str) {
        count++;
        if (min == null || min > Float.valueOf(str)) {
            min = Float.valueOf(str);
        } if (max == null || max < Float.valueOf(str)) {
            max = Float.valueOf(str);
        }
        sum = sum + Float.valueOf(str);
        average = sum / count;
    }

    @Override
    public void showStatistic() {
        System.out.println("------ Статистика по обработанным вещественных числам ------");
        if (count == 0) {
            System.out.println("В исходном наборе данных вещественных чисел не обнаружено.");
            System.out.println(" ");
            return;
        }
        System.out.println("Всего вещественных чисел: " + count);
        if (statisticMode != 0) {
            System.out.println("Максимальное вещественное число: " + max);
            System.out.println("Минимальное вещественное число: " + min);
            System.out.println("Среднее вещественное число: " + average);
            System.out.println("Сумма вещественных чисел: " + sum);
        }
        System.out.println(" ");
    }
}
