/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package methodsforhelp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author Nasos
 */
public class Utils {

    private static final String DATE_FORMAT = "d-M-yyyy";
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

    static LocalDate dateTime = null;

    private static Scanner scanner = new Scanner(System.in);

    /**
     * We check if the integers are integers or the user did a mistake.
     * With a massage of wrong input and ask him to try again.
     * @return integer
     */
    public static int checkingIntegers() {

        int number;

        while (true) {
            try {
                number = Integer.parseInt(scanner.next());

                break;
            } catch (NumberFormatException nfe) {
                System.out.println(colour.TEXT_RED+"Please give only number."+colour.TEXT_RESET);
            }
        }
        return number;
    }
    
    /**
     * We check if the integers are integers or the user did a mistake.
     * With 2 parameters of min and max.
     * @param min 
     * @param max
     * @return integer
     */
    public static int checkingIntegers(int min, int max) {

        int result = Utils.checkingIntegers();
        boolean quit = false;
        while (!quit) {
            if (result < min || result > max) {
                System.out.println(colour.TEXT_RED+"Please give the right number"+colour.TEXT_RESET);
                result = Utils.checkingIntegers();
            } else {
                quit = true;
            }
        }
        return result;
    }

    
    /**
     * We check the string to contains only letters.
     * @return string
     */
    public static String checkingStringToBeOnlyLetters() {
        String string = " ";
        boolean isValid = false;
        while (isValid == false) {
            string = scanner.next();
            scanner.nextLine();
            if (Pattern.matches("^[ A-Za-z]+$", string) == true) {                 //checks if the input contains only letters
                isValid = true;
            } else {
                System.out.println(colour.TEXT_RED+"Please give only latin characters."+colour.TEXT_RESET);
            }
        }
        return string;
    }


    /*Επιστρεφει true εαν το input ειναι 1!
    Επιστρεφει false εαν το input ειναι 2
    Εαν γραφτει κατι αλλο γινετε παλι*/
    
    /**
     * This method gives only 2 options to user.
     * @return boolean
     */
    public static boolean optionsTrueOrFalse() {
        boolean isItYes = false;
        boolean inputIsValid = false;
        while (inputIsValid == false) {
            String input = scanner.next();
            scanner.nextLine();
            switch (input) {
                case "1":
                    isItYes = true;
                    inputIsValid = true;
                    break;
                case "2":
                    isItYes = false;
                    inputIsValid = true;
                    break;
                default:

                    System.out.println(colour.TEXT_RED+"Please coose from option '1' or '2'!"+colour.TEXT_RESET);

            }
        }
        return isItYes;
    }

   
    public static DateTimeFormatter getFormatter() {
        return formatter;
    }
    
     /**
     * Validation for local date.
     * @return 
     */
    public static LocalDate validationLocalDate() {

        boolean flag1;
        do {
            try {
                String inp = scanner.next();
                scanner.nextLine();
                dateTime = LocalDate.parse(inp, formatter);
                flag1 = false;
            } catch (Exception e) {
                System.out.println(colour.TEXT_RED+"Invalid date value. "+colour.TEXT_RESET);
                System.out.println(colour.TEXT_YELLOW+"Please enter a new one: "+colour.TEXT_RESET);
                flag1 = true;
            }

        } while (flag1);
        return dateTime;
    }

}
