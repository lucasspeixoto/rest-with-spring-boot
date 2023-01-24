package com.api.restwithspringboot.utils;

import java.util.Arrays;
import java.util.List;

public class Mathematical {

    public static List<String> validOperations = Arrays.asList(
            "sum",
            "subtraction",
            "multiply",
            "division"
    );

    public static Double convertToDouble(String strNumber) {

        if (strNumber == null) return 0D;

        String number = strNumber.replaceAll(",", ".");

        if (isNumeric((number))) {
            return Double.parseDouble(number);
        }

        return 0D;
    }

    public static boolean isNumeric(String strNumber) {
        if (strNumber == null) return false;

        String number = strNumber.replaceAll(",", ".");

        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
}
