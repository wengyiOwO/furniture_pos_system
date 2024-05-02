/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HamFamFurniture;

import static HamFamFurniture.Staff.StaffRecordLinkedList;
import java.lang.String;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

/**
 *
 * @author User
 */
public class MainProgress {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Member> memberList = new ArrayList<Member>();
        ArrayList<Furniture> furnitureList = new ArrayList<Furniture>();
        ArrayList<Order> orderList = new ArrayList<Order>();
        LinkedList<Staff> staffList = new LinkedList<Staff>();
        //get stored data
        StaffRecord sr = new StaffRecord();
        Member.loadMember(memberList);
        Furniture.loadFurniture(furnitureList);
        Staff.loadStaff(sr);
        boolean validateLogin = false;
        
        while(validateLogin == false){
            System.out.println("=====================================\n");
            System.out.println("   Welcome To HamFam Furniture Shop");
            System.out.println("\n=====================================\n");

            System.out.println("       -----------------------");
            System.out.println("      |        LOG IN         | ");
            System.out.println("       -----------------------\n");
            validateLogin = Staff.loginStaff();
        }
        
        int option;
        if (validateLogin == true) {
            do {
                mainMenu();
                option = scan.nextInt();
                switch (option) {
                    case 1:
                        Order.OrderManage(furnitureList, orderList, memberList);
                        break;
                    case 2:
                        Furniture.FurnitureManage(furnitureList);
                        break;
                    case 3:
                        Member.MemberManage(memberList);
                        break;
                    case 4:
                        Staff.StaffRecordLinkedList(sr);
                        break;
                    case 0:
                        System.out.println("Exit...");
                        break;
                    default:
                        System.out.println("Invalid Option");
                        break;
                }
            } while (option != 0);

        }

    }

    public static void mainMenu() {
        System.out.println("\n\n\n-------Main Menu--------");
        System.out.println("1. Manage Order");
        System.out.println("2. Manage Furniture");
        System.out.println("3. Manage Member");
        System.out.println("4. Manage Staff");
        System.out.println("0. Exit");
        System.out.print("Enter your option: ");
    }

   
}
