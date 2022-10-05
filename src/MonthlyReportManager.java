import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MonthlyReportManager {


    ArrayList<MonthlyReport> monthlyReports;

    MonthlyReportManager() {
        monthlyReports = new ArrayList<>();
    }

    void readMonthlyReport(String nameFile) {
        String content = readFileContentsOrNull(nameFile);
        String[] lines = content.split("\r?\n");
        ArrayList<MonthlyReportRecord> records = new ArrayList<>();
        for (int i = 1; i < lines.length; i++) {
            MonthlyReportRecord record = makeRecordFromLine(lines[i]);
            records.add(record);
        }
        MonthlyReport monthlyReport = new MonthlyReport(records);
        monthlyReports.add(monthlyReport);
    }


    MonthlyReportRecord makeRecordFromLine(String line) {
        String[] tokens = line.split(",");
        return new MonthlyReportRecord(tokens[0],
                Boolean.parseBoolean(tokens[1]),
                Integer.parseInt(tokens[2]),
                Double.parseDouble(tokens[3]));
    }

    String readFileContentsOrNull(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом." +
                    " Возможно, файл не находится в нужной директории.");
            return null;
        }
    }

    void printMonthlyReport() {
        if (monthlyReports.isEmpty()) {
            System.out.println("Список небыл загружен");
        } else {
            System.out.println("Статистика по месяцам");

            int monthName = 0;
            for (MonthlyReport report : monthlyReports) {
                monthName = monthName + 1;
                double maxSum = 0;
                double minSum = 0;
                String nameMax = "";
                String nameMin = "";
                double trueSum;
                double falseSum;

                for (MonthlyReportRecord record : report.records) {


                    falseSum = record.quantity * record.sum_of_one;
                    trueSum = record.quantity * record.sum_of_one;

                    if (record.is_expense == false && maxSum < falseSum) {
                        maxSum = falseSum;
                        nameMax = record.item_name;
                    }
                    if (record.is_expense == true && minSum < trueSum) {
                        minSum = trueSum;
                        nameMin = record.item_name;
                    }
                }
                System.out.println("Результаты за месяц " + monthName + ":");
                System.out.println("Самый прибыльный товар в этом месяце. " + nameMax + " " + maxSum );
                System.out.println("Самая большая трата в этом месяце. " + nameMin + " " + minSum);
            }


        }

    }
    void printYearlyReportCheck() {
        int monthName = 0;
        for (MonthlyReport report : monthlyReports){
            monthName = monthName + 1;
            double sumIncomes = 0;
            double sumExpenses = 0;
            for (MonthlyReportRecord record : report.records) {
                if (record.is_expense == false) {
                    sumIncomes += record.quantity * record.sum_of_one;
                } else {
                    sumExpenses += record.quantity * record.sum_of_one;
                }
            }

        }
    }

}
