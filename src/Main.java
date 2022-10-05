import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Поехали!
        Scanner scanner = new Scanner(System.in);

        //YearlyReport yReport = new YearlyReport("resources/y.2021.csv");
        MonthlyReportManager monthlyReportManager = new MonthlyReportManager();
        YearlyReportManager yearlyReportManager = new YearlyReportManager();



        while (true) {
            printMenu();
            int command = scanner.nextInt();
            if (command == 1){

                monthlyReportManager.readMonthlyReport("resources/m.202101.csv");
                monthlyReportManager.readMonthlyReport("resources/m.202102.csv");
                monthlyReportManager.readMonthlyReport("resources/m.202103.csv");
//


            } else if (command == 2) {

                yearlyReportManager.readYearlyReport( "resources/y.2021.csv");
//           System.out.println("Список был успешно считан");

            } else if (command == 3) {

            } else if (command == 4) {
                monthlyReportManager.printYearlyReportCheck(yearlyReportManager.getYearlyReport());

            } else if (command == 5) {

                yearlyReportManager.yearlyStatisticMouth();

            } else if (command == 0) {
                System.out.println("Выполнение программы завершено");
                return;
            } else {
                System.out.println("Такая команда не поддерживается. Введите новую команду");
            }


        }
    }
    public static void printMenu(){
        System.out.println("Выберити номер команды:");
        System.out.println("1 - Считать все месячные очеты:");
        System.out.println("2 - Считать годовой отчет:");
        System.out.println("3 - Сверить отчеты:");
        System.out.println("4 - Вывести информацию о всех месячных отчетах:");
        System.out.println("5 - Вывести информацию о годовом отчете:");
        System.out.println("0 - Завершение программмы:");
    }
}
