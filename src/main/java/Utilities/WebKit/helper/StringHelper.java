//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.test.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class StringHelper {
    public StringHelper() {
    }

    public static boolean isBlankOrNull(String string) {
        return string == null || string.trim().isEmpty();
    }

    public static boolean toBoolean(Boolean bool) {
        return bool == null ? false : bool;
    }

    public static String returnEmptyStringIfNull(Object realValue) {
        return realValue == null ? "" : realValue.toString();
    }

    public static Object returnZeroIfNull(Object value) {
        return value != null && !value.equals("") ? value : 0;
    }

    public static String removeNewLine(String str) {
        return str != null ? str.replace('\n', ' ') : null;
    }

    public static String removeNewLines(String str) {
        return str != null && str.contains("\n") ? str.replaceAll("\\n", "") : str;
    }

    public static String replaceNewLinesWithSpaces(String str) {
        return str != null && str.contains("\n") ? str.replaceAll("\\n", " ") : str;
    }

    public static String removeSpaces(String str) {
        return str != null ? str.replaceAll(" ", "") : null;
    }

    public static String removeFullStops(String str) {
        return str != null ? str.replaceAll("\\.", "") : null;
    }

    public static String removeCommas(String str) {
        return str != null ? str.replaceAll("\\,", "") : null;
    }

    public static String removeTabs(String str) {
        return str != null ? str.replaceAll("\\t", "") : null;
    }

    public static String removeAllSymbolsAndSpaces(String str) {
        return str != null ? str.replaceAll("\\W", "") : null;
    }

    public static String getFirstCharacter(String str) {
        return str != "" && str != null ? str.substring(0, 1) : null;
    }

    public static String removeAllSymbolsCharactersAndSpaces(String str) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < str.length(); ++i) {
            if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                sb.append(str.charAt(i));
            }
        }

        return sb.toString();
    }

    public static boolean notEmpty(String str) {
        String text = str.trim();
        return text != null && text.length() > 0;
    }

    public static String removeLeadingZero(String str) {
        return str != null && str.startsWith("0") ? str.replaceFirst("0", "") : str;
    }

    public static String[] splitTextStringOnNewLine(String text) {
        if (text.contains("\n")) {
            String[] scheduledDepartDateTimeArray = text.split("\n");
            return scheduledDepartDateTimeArray;
        } else {
            return null;
        }
    }

    public static String getStringAfterText(String completeString, String searchString) {
        if (completeString.contains(searchString)) {
            int lastIndexOf = completeString.lastIndexOf(searchString);
            return completeString.substring(lastIndexOf + searchString.length()).trim();
        } else {
            return "Unable to find " + searchString + " in " + completeString;
        }
    }

    public static String getStringBeforeText(String completeString, String searchString) {
        if (completeString.contains(searchString)) {
            int indexOf = completeString.indexOf(searchString);
            return completeString.substring(0, indexOf).trim();
        } else {
            return "Unable to find " + searchString + " in " + searchString;
        }
    }

    public static String extractNumberFromString(String subjectString) {
        String returnString = "";
        returnString = subjectString.replaceAll("([^0-9^.])", "");
        return returnString;
    }

    public static String removeNumberFromString(String subjectString) {
        String returnString = null;
        if (subjectString != null && !subjectString.equals("")) {
            returnString = subjectString.replaceAll("[0-9]", "");
        } else {
            returnString = "";
        }

        return returnString;
    }

    public static List<String> removeSpaceFromStringsOfList(List<String> paramList) {
        List<String> newStringList = new ArrayList();

        for(int cnt = 0; cnt < paramList.size(); ++cnt) {
            newStringList.add(removeSpaces(((String)paramList.get(cnt)).toString()));
        }

        return newStringList;
    }

    public static boolean isIntegerInString(String subjectString) {
        Pattern intsOnly = Pattern.compile("\\d+");
        Matcher makeMatch = intsOnly.matcher(subjectString);
        return makeMatch.find();
    }

    public static String getNamedFormatString(String subjectString) {
        String firstDeli = subjectString.substring(0, 1);
        String secondDeli = subjectString.substring(1, subjectString.length());
        return firstDeli.toUpperCase() + secondDeli;
    }

    public static String getLastnCharacters(String inputString, int subStringLength) {
        int length = inputString.length();
        if (length <= subStringLength) {
            return inputString;
        } else {
            int startIndex = length - subStringLength;
            return inputString.substring(startIndex);
        }
    }

    public static String getIntegerInString(String subjectString) {
        Pattern intsOnly = Pattern.compile("\\d+");
        Matcher makeMatch = intsOnly.matcher(subjectString);
        makeMatch.find();
        String inputInt = makeMatch.group();
        return inputInt;
    }

    public static String getStringBetweenChars(String completeString, String startChar, String endChar) {
        try {
            int startPoint = completeString.indexOf(startChar) + 1;
            int endPoint = completeString.indexOf(endChar, startPoint);
            return completeString.substring(startPoint, endPoint);
        } catch (Exception var6) {
            return "Error in getStringBetweenChars() " + startChar + " or " + endChar + " not found in " + completeString;
        }
    }

    public static boolean regExMatcher(String inputString, String reExPattern) {
        Matcher match = null;
        Pattern stringMatch = Pattern.compile(reExPattern);
        match = stringMatch.matcher(inputString);
        return match.find();
    }

    public static String getStringBetweenTexts(String completeString, String startText, String endText) {
        try {
            int startPoint = completeString.indexOf(startText) + startText.length();
            int endPoint = completeString.indexOf(endText);
            return completeString.substring(startPoint, endPoint);
        } catch (Exception var6) {
            return "Error in getStringBetweenChars() " + startText + " or " + endText + " not found in " + completeString;
        }
    }

    public static boolean isTitleValid(String title) {
        String[] validTitles = new String[]{"Mr", "Mrs", "Miss", "Mstr"};

        for(int x = 0; x < validTitles.length; ++x) {
            if (validTitles[x].toUpperCase().equals(title.toUpperCase())) {
                return true;
            }
        }

        return false;
    }

    public static boolean isStringEmptyOrNull(String str) {
        return "".equals(str) || str == null;
    }

    public static String toCamelCase(String string) {
        String[] parts = string.split("[_\\.]");
        String camelCaseString = "";
        String[] var3 = parts;
        int var4 = parts.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            String part = var3[var5];
            camelCaseString = camelCaseString + firstLetterUpperCase(part);
        }

        return firstLetterLowerCase(camelCaseString);
    }

    static String firstLetterUpperCase(String string) {
        return string.substring(0, 1).toUpperCase() + string.toLowerCase().substring(1);
    }

    static String firstLetterLowerCase(String string) {
        return string.substring(0, 1).toLowerCase() + string.substring(1);
    }

    public ArrayList<String> getReversedArray(ArrayList<String> original) {
        ArrayList<String> copy = new ArrayList(original);
        Collections.reverse(copy);
        return copy;
    }
}
