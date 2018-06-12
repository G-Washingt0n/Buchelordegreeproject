package com.pgmail.martsulg.bachelordegreeproject.extras;

/**
 * Created by g_washingt0n on 02.05.2018.
 */

public enum WeekdaysEnum {
    Monday(1),
    Tuesday(2),
    Wednesday(3),
    Thursday(4),
    Friday(5),
    Saturday(6),
    Sunday(7),
    Mon("Monday"),
    Tue("Tuesday"),
    Wed("Wednesday"),
    Thu("Thursday"),
    Fri("Friday"),
    Sat("Saturday"),
    Sun("Sunday");

    private int number;
    private String day;

    WeekdaysEnum(int number) {
        this.number = number;
    }

    WeekdaysEnum(String day) {
        this.day = day;
    }

    public int getDayAsInt() {
        return number;
    }

    public String getDayAsString() {
        return day;
    }


    public static String convertIntToDay(int num) {
        for (WeekdaysEnum day : WeekdaysEnum.values()) {
            if (day.getDayAsInt() == num) {
                return day.toString();
            }
        }
        return null;
    }

//    public static Color convertStringToColor(String inputColor) {
//        for (Color color : Color.values()) {
//            if (color.getColorAsString().equals(inputColor)) {
//                return color;
//            }
//        }
//        return null;
//    }

    public static int convertDayToInt(String inputDay) {
        for (WeekdaysEnum day : WeekdaysEnum.values()) {
            if (day.toString().equals(inputDay)) {
                return day.getDayAsInt();
            }
        }
        return -1;
    }

    public static String convertIntToShortDay(int num) {
        String fullDay = convertIntToDay(num);
        assert fullDay != null;
        switch (fullDay) {
            case "Monday":
                return "Mon";
            case "Tuesday":
                return "Tue";
            case "Wednesday":
                return "Wed";
            case "Thursday":
                return "Thu";
            case "Friday":
                return "Fri";
            case "Saturday":
                return "Sat";
            case "Sunday":
                return "Sun";
            default:
                return null;
        }
//        TODO replace switch with correct enum functionality
//        for (WeekdaysEnum day : WeekdaysEnum.values()) {
//            if(day.getDayAsString().equals(fullDay)){
//                return day.toString();
//            }
//        }
    }

//    public static String convertColorToString(Color inputColor) {
//        for (Color color : Color.values()) {
//            if (color.getColorAsInt() == inputColor.getColorAsInt()) {
//                return color.getColorAsString();
//            }
//        }
//        return null;
//    }

//    Resources res = getResources();
//    ... = res.getStringArray(R.array.weekdays);
//    private final String[] weekday = Resources.getSystem().getStringArray(R.array.weekdays);


}
