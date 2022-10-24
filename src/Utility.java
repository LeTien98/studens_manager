/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.util.Scanner;

/**
 *
 * @author PHAM KHAC VINH
 */
public class Utility {
    public final String REGEX_YN = "[yYnN]";
    public final String REGEX_UD = "[uUdD]";
    public final String REGEX_STRING = "[a-zA-Z0-9 ]+";
    
    /**
     * This function use to get a string input
     * @param message: message required input
     * @param error: message error
     * @param regex: pattern use to check input
     * @return : user's input
     */
    public String getString(String message, String error, String regex ) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine();

            if (input.matches(regex)) {
                return input;
            } else {
                System.out.println(error);
            }
        }
    }

    /*
    lay ve 1 so dang integer
     */
    public int getInt(String message, String firstError, String secondError,
            String thirdError, int min, int max) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print(message);
                String input = scanner.nextLine();
                int number = Integer.parseInt(input);

                //check range
                if (number > max) {
                    System.out.println(firstError);
                } else if (number < min) {
                    System.out.println(secondError);
                } else {
                    return number;
                }

            } catch (Exception e) {
                System.out.println(thirdError);
            }
        }
    }

    public double getDouble(String message, String firstError, String secondError,
            String thirdError, double min, double max) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print(message);
                String input = scanner.nextLine();
                double number = Double.parseDouble(input);

                //check range
                if (number > max) {
                    System.out.println(firstError);
                } else if (number < min) {
                    System.out.println(secondError);
                } else {
                    return number;
                }
            } catch (Exception e) {
                System.out.println(thirdError);
            }
        }
    }

    public float getFloat(String message, String firstError, String secondError,
            String thirdError, float min, float max) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print(message);
                String input = scanner.nextLine();
                float number = Float.parseFloat(input);

                //check range
                if (number > max) {
                    System.out.println(firstError);
                } else if (number < min) {
                    System.out.println(secondError);
                } else {
                    return number;
                }
            } catch (Exception e) {
                System.out.println(thirdError);
            }
        }
    }

}
