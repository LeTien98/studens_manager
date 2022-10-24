/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author PHAM KHAC VINH
 */
public class Main {

    public static void main(String[] args) {
        ManageStudent manage = new ManageStudent();
        Utility valid = new Utility();
        while (true) {
            displayMenu();

            int option = valid.getInt("Option: ", "Wrong", "Wrong", "Wrong", 1, 5);
            switch (option) {
                case 1:
                    //create 
                    manage.inputStudent();
                    break;
                case 2:
                    //find and sort
                    manage.findAndSort();
                    break;
                case 3:
                    //update or delte
                    manage.updateOrDelete();
                    break;
                case 4:
                    //report
                    manage.report();
                    break;
                case 5:
                    System.exit(0);
                    break;

            }

        }

    }

    private static void displayMenu() {
        System.out.println("WELCOME TO STUDENT MANAGEMENT\n"
                + "1.	Create\n"
                + "2.	Find and Sort\n"
                + "3.	Update/Delete\n"
                + "4.	Report\n"
                + "5.	Exit");
    }
}
