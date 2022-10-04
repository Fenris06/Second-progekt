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

//    void printMonthlyReport() {
//        if (monthlyReports == null) {
//            System.out.println("Список небыл загружен");
//        } else {
//
//
//            for (MonthlyReport report : monthlyReports) {
//                for (MonthlyReportRecord record : report.records)
//                    System.out.println( record.is_expense +
//                            " " + record.item_name +
//                            " " + record.quantity +
//                            " " + record.sum_of_one);
//            }
//        }
//    }

}
