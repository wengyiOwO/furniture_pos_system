/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HamFamFurniture;

import java.util.Scanner;

/**
 *
 * @author Pua Jia Qian
 */
public class Staff extends Person {


    private String staffEmail;
    private String position;

    public Staff() {

    }

    public Staff(String staffId, String staffName, String contactNo, String staffEmail, String joinDate, String position) {
        super(staffId, staffName, contactNo, joinDate);
        this.staffEmail = staffEmail;
        this.position = position;
    }
    public void setStaffEmail(String staffEmail) {
        this.staffEmail = staffEmail;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getStaffEmail() {
        return staffEmail;
    }

    public String getPosition() {
        return position;
    }

    public String toString() {
        return String.format("|%-4s | %-15s | %-10s | %-15s |%-10s | %-15s |", super.getId(), super.getName(), super.getPhoneNo(), staffEmail, super.getJoinDate(), position);
    }
    public static boolean loginStaff() {

        String Username;
        String Password;

        Password = "abc123";
        Username = "test";

        Scanner input1 = new Scanner(System.in);
        System.out.print("Enter Username : ");
        String username = input1.next();

        Scanner input2 = new Scanner(System.in);
        System.out.print("Enter Password : ");
        String password = input2.next();

        if (username.equals(Username) && password.equals(Password)) {

            System.out.println("Access Granted! Welcome!");
        } else if (username.equals(Username)) {
            System.out.println("Invalid Password!");
        } else if (password.equals(Password)) {
            System.out.println("Invalid Username!");
        } else {
            System.out.println("Invalid Username & Password!");
        }
        return username.equals(Username) && password.equals(Password);

    }
    public static void loadStaff(StaffRecord sr){
        Staff staff1 = new Staff("1001", "Coco Lee", "0123456789", "coco@gmail.com", "12/12/2013", "accountant");
        Staff staff2 = new Staff("1002", "Lily Tan", "0123123123", "lily@gmail.com", "03/05/2014", "HR manager");
        Staff staff3 = new Staff("1003", "Lim Jia Yi", "012315553", "ljyi@gmail.com", "10/07/2014", "cashier");

        sr.add(staff1);
        sr.add(staff2);
        sr.add(staff3);
    }
    
    public static void StaffRecordLinkedList(StaffRecord sr) {
        Staff staff = new Staff();

        Scanner input = new Scanner(System.in);
        String selection = "";

        do {
            staffMenu();

            selection = input.next();

            switch (selection) {

                //add staff
                case "1":
                    System.out.print("Enter Staff ID: ");
                    String staffId = input.next();
                    input.nextLine();
                    System.out.print("Enter Staff name: ");
                    String staffName = input.nextLine();

                    System.out.print("Enter Staff contact no: ");
                    String contactNo = input.next();
                    input.nextLine();
                    System.out.print("Enter Staff email: ");
                    String email = input.next();
                    input.nextLine();
                    System.out.print("Enter Staff join date(dd//mm/yyyy): ");
                    String joinDate = input.next();
                    input.nextLine();
                    System.out.print("Enter Staff working position: ");
                    String position = input.nextLine();
                    staff = new Staff(staffId, staffName, contactNo, email, joinDate, position);

                    sr.add(staff);
                    System.out.println("Staff record is successfully added");

                    break;

                //delete staff
                case "2":
                    System.out.print("\nEnter Staff ID: ");
                    String sId = input.next();
                    input.nextLine();
                    sr.delete(sId);

                    break;

                //update staff
                case "3":
                    System.out.print("\nEnter Staff ID: ");
                    String sIdNo = input.next();
                    input.nextLine();

                    sr.update(sIdNo, input);

                    break;

                //display staff
                case "4":
                    sr.display();
                    break;
                case "5":
                    break;
                default:
                    System.out.println("\nInvalid input");
                    break;
            }
        } while (!selection.equalsIgnoreCase("5"));

    }

    public static void staffMenu() {
        System.out.println("\n--- STAFF MENU ---");
        System.out.println("1. Add Staff");
        System.out.println("2. Delete Staff");
        System.out.println("3. Update Staff");
        System.out.println("4. Display Staff");
        System.out.println("5. Back to Main Menu");
        System.out.print("Enter your selection: ");

    }
}
