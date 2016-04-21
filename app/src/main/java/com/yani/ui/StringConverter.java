package com.yani.ui;

import java.util.List;

public class StringConverter {

    private StringConverter() {
    }

    public static String makeStringFromList(List<String> list) {
        return list.toString().replace("[", "").replace("]", "");
    }

    public static String assignDeclination(int num, String word) {
        String numAsStr = Integer.toString(num);
        if (!exceptionsDeclination(num)) {
            if (numAsStr.endsWith("0") | numAsStr.endsWith("5") | numAsStr.endsWith("6") | numAsStr.endsWith("7")
                    | numAsStr.endsWith("8") | numAsStr.endsWith("9")) {
                return numAsStr + " " + word + "ов";
            }
            if (numAsStr.endsWith("1")) {
                return numAsStr + " " + word;
            }
            if (numAsStr.endsWith("2") | numAsStr.endsWith("3") | numAsStr.endsWith("4")) {
                return numAsStr + " " + word + "а";
            }
        }
        return numAsStr + " " + word + "ов";
    }

    private static Boolean exceptionsDeclination(int num) {
        return num > 10 & num < 15;
    }
}
