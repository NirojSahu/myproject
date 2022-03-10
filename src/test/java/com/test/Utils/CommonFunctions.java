package com.test.Utils;

import com.google.inject.Inject;
import org.apache.commons.lang.RandomStringUtils;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class CommonFunctions {


    SimpleDateFormat sdf;
    Calendar cal;
    String strDate;

    @Inject
    private CommonVariables cmnVrbls;

    public String getCurrentDate() {
        cal = Calendar.getInstance();
        sdf = new SimpleDateFormat("ddMMYYYY_HHmmss");
        strDate = sdf.format(cal.getTime());
        System.out.println("Current date in String Format: " + strDate);
        return strDate;
    }

    public Date getCurrentDate_formatted(String Pattern) {
        Calendar cal = Calendar.getInstance();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date();
        try {
            d = sdf.parse(sdf.format(cal.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //System.out.println("Current date in String Format: " + strDate);
        return d;
    }

    public Date getDate_formatted(String value,String Pattern) {
        //cal = Calendar.getInstance();
        DateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        Date d = null;
        try {
            d = sdf1.parse(value);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //System.out.println("Current date in String Format: " + strDate);
        return d;
    }

    public String getCurrentDatewithouttime() {
        Date date = new Date();
        sdf = new SimpleDateFormat("dd/MM/YYYY");
        strDate = sdf.format(date);
        System.out.println("Current date in String Format: " + strDate);
        return strDate;
    }


    public String getCurrentDate(String expectedDateFormat) {
        cal = Calendar.getInstance();
        sdf = new SimpleDateFormat(expectedDateFormat);
        strDate = sdf.format(cal.getTime());
        System.out.println("Current date in String Format: " + strDate);
        return strDate;
    }

    public String getFutureDate(String expectedDateFormat) {
        cal = Calendar.getInstance();
        sdf = new SimpleDateFormat(expectedDateFormat);
        cal.setTime(cal.getTime());
        cal.add(Calendar.DATE, 1);
        Date futureDate = cal.getTime();
        strDate = sdf.format(futureDate);
        System.out.println("Current date in String Format: " + strDate);
        return strDate;
    }

    public String getMonthOldPastDate(String expectedDateFormat) {
        cal = Calendar.getInstance();
        sdf = new SimpleDateFormat(expectedDateFormat);
        cal.setTime(cal.getTime());
        cal.add(Calendar.MONTH, -1);
        Date pastDate = cal.getTime();
        strDate = sdf.format(pastDate);
        System.out.println("Current date in String Format: " + strDate);
        return strDate;
    }


    public int getCurrentDayPlusOneDay() {
        return( Integer.parseInt(getFutureDate("dd")));
    }

    public int getCurrentHour() {
        return( Integer.parseInt(getCurrentDate("HH")));
    }

    public int getCurrentMin() {
        return( Integer.parseInt(getCurrentDate("mm")));
    }

    public int getCurrentMonthPlusOneDay() {
        return( Integer.parseInt(getFutureDate("MM")));
    }

    public int getCurrentYearPlusOneDay() {
        return( Integer.parseInt(getFutureDate("YYYY")));
    }

    public String getTodaysDay() {

        return (getCurrentDate("dd"));
    }

    public String getTodaysMonth() {

        return (getCurrentDate("MM"));
    }

    public String getTodaysYear() {

        return (getCurrentDate("YYYY"));
    }

    public String getMonthOldDay() {

        return (getMonthOldPastDate("dd"));
    }

    public String getMonthOldMonth() {

        return (getMonthOldPastDate("MM"));
    }

    public String getMonthOldYear() {

        return (getMonthOldPastDate("YYYY"));
    }

    public int getDigitFromString(String str) {
        return Integer.parseInt(str.replaceAll("\\D+", ""));
    }

    public static String getRandomString(int length) {
        return RandomStringUtils.randomAlphanumeric(length).toUpperCase();

    }

    public static String getRandomNumber(int length) {
        return RandomStringUtils.randomNumeric(length);

    }

    public String getScenarioNumber(String scenarioName) {

        return (scenarioName.split("-",2)[0]);
    }

    public String splitStringOnFirstOccurenceOfAnotherPattern(String string, String pattern) {

        return (string.split(pattern,2)[0]);
    }

    public String splitStringOnFirstOccurenceOfAnotherPattern(String string, String pattern, int integer) {

        return (string.split(pattern,2)[integer]);
    }


    public void test() {
        System.out.println(getScenarioNumber("1-Scenario is"));
    }


    public String replaceString(String orignalString, String templateStringToReplace, String toBeReplaceWithString ) {
        return orignalString.replace(templateStringToReplace,toBeReplaceWithString);

    }
    public String replaceTemplateString(String orignalString, String toBeReplaceWithString ) {
        return orignalString.replace("TEMPLATE",toBeReplaceWithString);

    }

    public String convertString(Integer intVrbl ) {
        return String.valueOf(intVrbl);

    }

    public int convertInt(String stringVrbl ) {
        return Integer.parseInt(stringVrbl);

    }
    public String getSubString(String stringVrbl, int startIndex, int endindex ) {
        return stringVrbl.substring(startIndex, endindex);

    }

    public int getRowIndex(ArrayList<Map<String, String>> arrayList, String stringToMatch ) {
        int index= 0;
        for(int i=0;i<arrayList.size();i++) {
            if(arrayList.get(i).get("ScenarioNumber").equals(stringToMatch)) {
                index = i;
                break;
            }
        }
        return index;
    }



    public String IdentifyIndexOfGivenValue(String string, String IndexToFind) {
        int index =getDigitFromString(IndexToFind);

        return String.valueOf(string.charAt(index-1));

    }

    public boolean isStringNotEqualTo(String stringOne, String stringTwo ) {
        return !stringOne.equals(stringTwo);
    }

    public String getSubString(String stringVrbl, char startCharacter, char endCharacter){
//        return string between the start and end given character
        boolean flag=false;
        String subString="";
        for(int i=0;i<stringVrbl.length();i++){
            if(stringVrbl.charAt(i)==startCharacter){
                flag=true;
                continue;
            }
            if(stringVrbl.charAt(i)==endCharacter)
                break;
            if(flag)
                subString=subString+stringVrbl.charAt(i);

        }
        return subString;
    }
    public void setMessageToNewFile(String msg, String path, String fileName){
        try {
            //create a temporary file

            File logFile=new File(path+fileName);
            BufferedWriter writer = new BufferedWriter(new FileWriter(logFile));
            writer.write (msg);

            //Close writer
            writer.close();
        } catch(Exception e) {
            System.out.println("Failed");
            e.printStackTrace();
        }
    }
    public String getMessageFromFile(String path, String fileName){
        String name = path+fileName;
        String line=null;
        File file = new File(name);

        try{
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            line = br.readLine();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return line;
    }

    public String convertedformat (String dateStr) throws ParseException {
        DateFormat srcDf = new SimpleDateFormat("yyyy-MM-dd");

        // parse the date string into Date object
        Date date = srcDf.parse(dateStr);

        DateFormat destDf = new SimpleDateFormat("dd/MM/yyyy");

        // format the date into another format
        dateStr = destDf.format(date);

        System.out.println("Converted date is : " + dateStr);
        return dateStr;
    }
    public String convertedformat1 (String dateStr) throws ParseException {
        DateFormat srcDf = new SimpleDateFormat("yyyy-MM-dd");

        // parse the date string into Date object
        Date date = srcDf.parse(dateStr);

        DateFormat destDf = new SimpleDateFormat("dd-MM-yyyy");

        // format the date into another format
        dateStr = destDf.format(date);

        System.out.println("Converted date is : " + dateStr);
        return dateStr;
    }
    public String convertedformat2 (String dateStr)  {
        DateFormat srcDf = new SimpleDateFormat("dd/MM/yyyy");

        // parse the date string into Date object
        Date date = null;
        try {
            date = srcDf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateFormat destDf = new SimpleDateFormat("dd-MM-yyyy");

        // format the date into another format
        dateStr = destDf.format(date);
        System.out.println("Converted date is : " + dateStr);
        return dateStr;
    }
    public String convertedformat3 (String dateStr)  {
        DateFormat srcDf = new SimpleDateFormat("dd/MM/yyyy");

        // parse the date string into Date object
        Date date = null;
        try {
            date = srcDf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateFormat destDf = new SimpleDateFormat("yyyy-MM-dd");

        // format the date into another format
        dateStr = destDf.format(date);
        System.out.println("Converted date is : " + dateStr);
        return dateStr;
    }
    public LocalDate next6months(String Date)
    {
        LocalDate date3 = LocalDate.parse(Date);
        System.out.println("Original Date: " + date3);
        date3 = date3.plusDays(180);
        return date3;
    }

    public LocalDate previous6months(String Date)
    {
        LocalDate date3 = LocalDate.parse(Date);
        System.out.println("Original Date: " + date3);
        date3 = date3.minusDays(180);
        return date3;
    }

    public String takepreviousyearwithcurrentdate() throws ParseException {
        Calendar c= Calendar.getInstance();

        c.setTime(new Date());
        sdf = new SimpleDateFormat("dd/MM/YYYY");
        strDate = sdf.format(c.getTime());
        c.add(Calendar.YEAR,-1);
        c.add(Calendar.DAY_OF_MONTH,1);
        strDate = sdf.format(c.getTime());
        System.out.println("Current date in String Format: " + strDate);
        return convertedformat2(strDate);

        /*int year;
        String date = convertedformat2(getCurrentDatewithouttime());
        year = Integer.parseInt(date.split("-")[2]) - 1;
        return date.replace(date.split("-")[2],String.valueOf(year));*/
    }
    public static String convertCurrencySymbolToCurrencyType(String symbol){
        String currencyType;
        switch (symbol){
            case "£":
                currencyType="GBP";
                break;
            case "$":
                currencyType="USD";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + symbol);
        }
        return currencyType;
    }

}
