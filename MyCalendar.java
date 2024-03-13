public class MyCalendar {


    public static void printMonth(int year, int month) {
        printMonthTitle(year, month);
        printMonthBody(year, month);
    }

    public static void printMonthTitle(int year, int month) {
        System.out.println("          " + getMonthName(month) + " " + year);

        System.out.println("   =========================");
        System.out.println("   P   W   S   C   P   S   N ");
    }

    public static void printMonthBody(int year, int month) {
        int startDay = getStartDay(year, month);
        int numberOfDays = getNumberOfDaysInMonth(month, year);

        for (int i = 0; i < startDay; i++) {
            System.out.print("    ");
        }

        for (int i = 1; i <= numberOfDays; i++) {
            System.out.printf("%4d", i);
            if((i + startDay) % 7 == 0){
                System.out.println();
            }
        }
    }

    public static String getMonthName(int month) {

        String monthName = "";
        switch (month) {
            case 1: monthName = "Styczeń"; break;
            case 2: monthName = "Luty"; break;
            case 3: monthName = "Marzec"; break;
            case 4: monthName = "Kwiecień"; break;
            case 5: monthName = "Maj"; break;
            case 6: monthName = "Czerwiec"; break;
            case 7: monthName = "Lipiec"; break;
            case 8: monthName = "Śerpień"; break;
            case 9: monthName = "Wrzesień"; break;
            case 10: monthName = "Październik"; break;
            case 11: monthName = "Listopad"; break;
            case 12: monthName = "Grudzień"; break;
            default: monthName = "Błendny wpis";
        }
        return monthName;
    }

    public static int getStartDay(int year, int month) {
        int wensday_3_06_1800 = 2;

        int totalNumberOfDays = getTotalNumberOfDays(year, month);

        return (totalNumberOfDays + wensday_3_06_1800) % 7;
    }

    public static int getTotalNumberOfDays(int year, int month) {
        int numberOfDays = 0;

        for (int i = 1800; i < year; i++) {
            if (isLeapYear(i)){
                numberOfDays += 366;
            } else {
                numberOfDays += 365;
            }
        }

        for (int i = 1; i < month; i++) {
            numberOfDays += getNumberOfDaysInMonth(i, year);
        }

        return numberOfDays;

    }

    public static int getNumberOfDaysInMonth(int month, int year) {
        if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12){
            return 31;
        }
        if(month == 4 || month == 6 || month == 9 || month == 11){
            return 30;
        }
        if(month == 2){
            return isLeapYear(year) ? 29 : 28;
        } else{
            return 0;
        }

    }

    public static boolean isLeapYear(int year) {
        return(year % 400 == 0 || year % 4 == 0 && year % 100 != 0);
    }
}
