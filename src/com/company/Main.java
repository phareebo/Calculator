package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        String oper, a, b, result = "";

        while(true){
            try {

                Scanner sc = new Scanner(System.in); //считывает вводимую информацию
                String expression = sc.nextLine();
                sc.close();

                String[] splitExpression = expression.split(" ");

                if(splitExpression.length != 3){
                    throw new Exception();
                }

                a = splitExpression[0].toUpperCase();
                b = splitExpression[2].toUpperCase();
                oper = splitExpression[1];

                break;
            }
            catch (Exception e){
                throw e;
            }
        }

        ArrayList<String> correctRomanNumbersList = new ArrayList<String >(Arrays.asList("I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"));
        ArrayList<String> correctArabicNumbersList = new ArrayList<String >(Arrays.asList("1", "2", "3", "4", "5","6", "7", "8", "9", "10"));
        if(correctRomanNumbersList.contains(a) && correctRomanNumbersList.contains(b)){
            result = resolveRomanExpression(a,b,oper);
        }
        else if(correctArabicNumbersList.contains(a) && correctArabicNumbersList.contains(b)){
            result  = String.valueOf(resolveArabicExpression(Integer.valueOf(a), Integer.valueOf(b), oper));
        }
        else{
            throw new Exception();
        }

        System.out.println(result);
    }

    static int resolveArabicExpression(int a, int b, String oper) throws Exception{
        int result = 0;
        if ((a > 0 && a <= 10) && (b > 0 && b <= 10)) {
            switch (oper) {
                case "+":
                    result = a + b;
                    break;
                case "-":
                    result = a - b;
                    break;
                case "*":
                    result = a * b;
                    break;
                case "/":
                    result = a / b;
                    break;
                default:
                    throw new Exception();
            }
        }
        else{
            throw new Exception();
        }
        return result;
    }

    static String resolveRomanExpression(String a, String b, String oper) throws Exception{
        int arabA = convertRomanToArabic(a);
        int arabB = convertRomanToArabic(b);
        int arabicResult = resolveArabicExpression(arabA, arabB, oper);
        String result = convertArabicToRoman(arabicResult);
        return result;
    }

    static String convertArabicToRoman (int arabianNumber) {
        String romanNumber = Constants.romanNumbers[arabianNumber - 1];
        return romanNumber;
    }

    static int convertRomanToArabic(String romanNumber) {
        int decimal = 0;
        int lastNumber = 0;
        String romanNumeral = romanNumber.toUpperCase();
        for (int x = romanNumeral.length() - 1; x >= 0 ; x--) {
            char convertToDecimal = romanNumeral.charAt(x);

            switch (convertToDecimal) {
                case 'C':
                    decimal = processDecimal(100, lastNumber, decimal);
                    lastNumber = 100;
                    break;

                case 'L':
                    decimal = processDecimal(50, lastNumber, decimal);
                    lastNumber = 50;
                    break;

                case 'X':
                    decimal = processDecimal(10, lastNumber, decimal);
                    lastNumber = 10;
                    break;

                case 'V':
                    decimal = processDecimal(5, lastNumber, decimal);
                    lastNumber = 5;
                    break;

                case 'I':
                    decimal = processDecimal(1, lastNumber, decimal);
                    lastNumber = 1;
                    break;
            }
        }
        return decimal;
    }

    static int processDecimal(int decimal, int lastNumber, int lastDecimal) {
        if (lastNumber > decimal) {
            return lastDecimal - decimal;
        } else {
            return lastDecimal + decimal;
        }
    }
}
