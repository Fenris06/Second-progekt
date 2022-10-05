import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class YearlyReportManager {
    YearlyReport yearlyReport;

    void readYearlyReport(String nameFile) {
        String content = readFileContentsOrNull(nameFile);
        String[] line = content.split("\r?\n");

        ArrayList<YearlyReportRecord> yearRecords = new ArrayList<>();
        for (int i = 1; i < line.length; i++) {
            YearlyReportRecord yearRecord = makeYearRecordLine(line[i]);
            yearRecords.add(yearRecord);
        }
        yearlyReport = new YearlyReport(yearRecords);
        System.out.println("Список был успешно считан");

    }

    YearlyReportRecord makeYearRecordLine(String lines) {
        String[] tokens = lines.split(",");
        return new YearlyReportRecord(Integer.parseInt(tokens[0]),
                Double.parseDouble(tokens[1]),
                Boolean.parseBoolean(tokens[2]));

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


    void yearlyStatisticMouth() {
        if (yearlyReport == null) {
            System.out.println("Фаил годового отчета не был загружен. Загрузите фаил.");
        } else {
            System.out.println("2021 год");
            HashMap<Integer, Double> incom = new HashMap<>();
            HashMap<Integer, Double> midlExpense = new HashMap<>();
            HashMap<Integer, Double> midlIncome = new HashMap<>();
            double incomes = 0;
            double expense = 0;
            double sumExpense = 0;
            double sumIncomes = 0;
            double midlSumExpense = 0;
            double midlSumIncomes = 0;

            for (YearlyReportRecord record : yearlyReport.yearRecords) {
                if (!record.is_expense) {
                    incomes = record.amount;
                } else {
                    expense = record.amount;
                }
                incom.put(record.mouth, (incomes - expense));

            }

            for (Integer month : incom.keySet()) {
                Double income = incom.get(month);
                System.out.println("Прибыль за " + month + " месяц составила :" + income);

            }
            for (YearlyReportRecord record : yearlyReport.yearRecords) {
                if (!record.is_expense) {
                    sumIncomes += record.amount;

                } else {
                    sumExpense += record.amount; //1611050.0

                }
                midlIncome.put(record.mouth, sumIncomes);
                midlExpense.put(record.mouth, sumExpense);
            }
            for (Integer month : midlIncome.keySet()) {
                Double income = midlIncome.get(month);
                midlSumIncomes = income / month;
            }
            for (Integer month : midlExpense.keySet()) {
                Double expenses = midlExpense.get(month);
                midlSumExpense = expenses / month;
            }
            System.out.println("Средний доход за год составил: " + midlSumIncomes);
            System.out.println("Средний расход за год составил:" + midlSumExpense);
        }
    }
    ArrayList<YearlyReportRecord> getYearlyReport() {
        return yearlyReport.yearRecords;
    }
}