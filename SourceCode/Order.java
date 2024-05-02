/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HamFamFurniture;

import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class Order extends Furniture {

    private String orderIdCode = "ORD";
    private int orderIdNum;
    private String staffInCharge;
    private String memberID;
    private static int nextOrderIdNum = 1001;
    private double total;
    private double subtotal = 0;
    private double tax = 0.05;
    //private double totalPayment;

    public Order() {

    }

    public Order(String orderIdCode, int orderIdNum, String furnitureID,
            String furnitureName, double price, int quantity,
            String staffInCharge, String memberID) {
        super(furnitureID, furnitureName, price, quantity);
        this.orderIdCode = orderIdCode;
        this.orderIdNum = orderIdNum;
        this.staffInCharge = staffInCharge;
        this.memberID = memberID;
    }

    public String getOrderIdCode() {
        return orderIdCode;
    }

    public int getOrderIdNum() {
        return orderIdNum;
    }

    public String getStaffInCharge() {
        return staffInCharge;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setOrderIdCode(String orderIdCode) {
        this.orderIdCode = orderIdCode;
    }

    public void setOrderIdNum(int orderIdNum) {
        this.orderIdNum = orderIdNum;
    }

    public void setStaffInCharge(String staffInCharge) {
        this.staffInCharge = staffInCharge;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public static int getNextOrderIdNum() {
        return nextOrderIdNum;
    }

    public static void addNextOrderIdNum() {
        nextOrderIdNum++;
    }

    public void setTotal(double price, int quantity) {
        total = price * quantity;
    }

    public double calcTotal(double price, int quantity) {
        total = price * quantity;
        return total;
    }

    public String toString() {
        return String.format("%-20s   %10.2f   %3d   %10.2f",
                super.getFurnitureName(), super.getPrice(),
                super.getQuantity(), total);
    }

    public static void OrderManage(ArrayList<Furniture> furnitureList, 
            ArrayList<Order> orderList, ArrayList<Member> memberList) {
        Order order = new Order();
        Scanner scanner = new Scanner(System.in);

        int option = 0;
        do {
            System.out.println("\n\n-----Order Menu-----");
            System.out.println("1. Make Order");
            System.out.println("0. Exit");
            System.out.print("Enter option: ");
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    addOrder(furnitureList, orderList, memberList);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option.\n");
                    break;
            }
        } while (option != 0);
    }

    public static void addOrder(ArrayList<Furniture> furnitureList,
            ArrayList<Order> orderList, ArrayList<Member> memberList) {
        Scanner scan = new Scanner(System.in);
        Order order = new Order();
        Furniture furniture = new Furniture();
        String nextCustomer;
        do {

            String orderIdCode = order.getOrderIdCode();
            int orderIdNum = Order.getNextOrderIdNum();
            String staffInCharge, memberID;
            System.out.print("Enter Staff Name: ");
            order.setStaffInCharge(staffInCharge = scan.nextLine());
            int index = -1;
            do {

                System.out.print("Enter Member ID: ");
                memberID = scan.next();
                scan.nextLine();
                for (int i = 0; i < memberList.size(); i++) {
                    if (memberID.equals(memberList.get(i).getId())) {
                        index = i;
                        break;
                    }
                }
                if (index != -1) {
                    order.setMemberID(memberID);
                } else {
                    System.out.println(memberID + " not found.");
                }
            } while (index == -1);

            String continueOrder;
            do {

                String furnitureName;
                double furniturePrice;
                int furnitureQty;
                int orderQty;

                //print Menu
                Furniture.displayFurniture(furnitureList);
                //get Order
                index = -1;
                System.out.print("Enter Order Furniture ID: ");
                String orderFurnitureID = scan.nextLine().toUpperCase();
                for (int i = 0; i < furnitureList.size(); i++) {
                    if (orderFurnitureID.equals(furnitureList.get(i).getFurnitureID())) {
                        index = i;
                        break;
                    }
                }
                if (index != -1) {
                    furnitureName = furnitureList.get(index).getFurnitureName();
                    furniturePrice = furnitureList.get(index).getPrice();
                    furnitureQty = furnitureList.get(index).getQuantity();
                    //check orderQuantityAvailability;
                    do {
                        System.out.print("Enter Order(" + furnitureName + ") Quantity: ");
                        orderQty = scan.nextInt();
                        if (!(orderQty > 0 && orderQty <= furnitureQty)) {
                            System.out.println("Quantity not available.");
                        }
                    } while (!(orderQty > 0 && orderQty <= furnitureQty));

                    order = new Order(orderIdCode, orderIdNum, orderFurnitureID, furnitureName, furniturePrice, orderQty, staffInCharge, memberID);
                    orderList.add(order);
                    order.calcTotal(furniturePrice, orderQty);
                    System.out.println(orderFurnitureID + " added successfully. ");
                } else {
                    System.out.println(orderFurnitureID + " not found.");
                }
                System.out.print("Continue add order? (Y/N): ");
                continueOrder = scan.next().toUpperCase();
                scan.nextLine();
            } while (continueOrder.equals("Y"));
            displayOrder(orderList, orderIdCode, orderIdNum);
            int option;
            do {
                System.out.println("1. Display Selected Order");
                System.out.println("2. Modify Selected Order");
                System.out.println("3. Delete Selected Order");
                System.out.println("4. Done Order -> Payment");
                System.out.print("Select option: ");
                option = scan.nextInt();
                switch (option) {
                    case 1:
                        displayOrder(orderList, orderIdCode, orderIdNum);
                        break;
                    case 2:
                        modifyOrder(orderList, orderIdCode, orderIdNum);
                        break;
                    case 3:
                        deleteOrder(orderList, orderIdCode, orderIdNum);
                        break;
                    case 4:
                        doneOrder(orderList, orderIdCode, orderIdNum);
                        break;
                }
            } while (option != 4);

            System.out.print("Next Customer? (Y/N): ");
            nextCustomer = scan.next().toUpperCase();
            scan.nextLine();

            Order.addNextOrderIdNum();
            decInventory(furnitureList, orderList, orderIdCode, orderIdNum);
            addMemberPoint(memberList, orderList, orderIdCode, orderIdNum);
        } while (nextCustomer.equals("Y"));
    }

    //display Order
    public static void displayOrder(ArrayList<Order> orderList, String orderIdCode, int orderIdNum) {
        System.out.println("---------------- Order No. " + orderIdCode + orderIdNum + " -----------------");
        System.out.printf("%-20s   %10s   %3s   %10s\n", "Furniture", "Price(RM)", "Qty", "Amount(RM)");
        for (Order list : orderList) {
            if (list.getOrderIdNum() == orderIdNum) {
                System.out.println(list);
            }
        }
        System.out.println("----------------------------------------------------");

    }

    // Delete order
    public static void deleteOrder(ArrayList<Order> orderList, String orderIdCode, int orderIdNum) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter delete Furniture ID: ");
        String deleteID = scan.nextLine().toUpperCase();

        int index = -1;

        for (int i = 0; i < orderList.size(); i++) {
            Order order = orderList.get(i);
            if (order.getOrderIdNum() == orderIdNum && order.getFurnitureID().equals(deleteID)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println(deleteID + " does not exist. ");
        } else {
            orderList.remove(index);
            System.out.println(deleteID + " delete success !");
            displayOrder(orderList, orderIdCode, orderIdNum);
        }
    }

    public static void modifyOrder(ArrayList<Order> orderList, String orderIdCode, int orderIdNum) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter modify Furniture ID: ");
        String deleteID = scan.nextLine().toUpperCase();
        
        int index = -1;

        for (int i = 0; i < orderList.size(); i++) {
            Order order = orderList.get(i);
            if (order.getOrderIdNum() == orderIdNum && order.getFurnitureID().equals(deleteID)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println(deleteID + " does not exist. ");
        } else {
            System.out.print("Enter modify quantity: ");
            int modifyQuantity = scan.nextInt();
            orderList.get(index).setQuantity(modifyQuantity);

            System.out.println(orderIdCode + orderIdNum + " modified success !");
            orderList.get(index).setTotal(orderList.get(index).getPrice(), modifyQuantity);
            displayOrder(orderList, orderIdCode, orderIdNum);
        }
    }

    public static void doneOrder(ArrayList<Order> orderList, String orderIdCode, int orderIdNum) {
        Scanner scan = new Scanner(System.in);
        displayOrder(orderList, orderIdCode, orderIdNum);
        double total, subtotal = 0;

        for (Order list : orderList) {
            if (list.getOrderIdNum() == orderIdNum) {
                subtotal += list.calcTotal(list.getPrice(), list.getQuantity());
            }
        }
        double tax = subtotal * 0.05;
        double payment = subtotal + tax;
        System.out.printf("%42s%10.2f\n", "Subtotal", subtotal);
        System.out.printf("%42s%10.2f\n", "TAX(5%)", tax);
        System.out.printf("%42s%10.2f\n", "Total", payment);

        System.out.println("\n\n-----Payment Method-----");
        System.out.println("1. Cash");
        System.out.println("2. Credit/Debit Card");
        System.out.print("Select payment method: ");
        int selectMethod = scan.nextInt();
        double amountReceive = 0, amountReturn = 0;
        switch (selectMethod) {
            case 1:
                System.out.print("Enter cash receive: ");
                amountReceive = scan.nextDouble();
                amountReturn = amountReceive - payment;
                break;
            case 2:
                String cardNum;
                String exampleCardNum = "0205210517";
                do {
                    System.out.print("Enter Card Number: ");
                    cardNum = scan.next();
                    scan.nextLine();
                    if (!cardNum.equals(exampleCardNum)) {
                        System.out.println("Invalid credit/debit card number. Try again");
                    } else {
                        amountReceive = payment;
                        amountReturn = 0;
                    }
                } while (!cardNum.equals(exampleCardNum));
                break;
        }
        displayInvoice(orderList, orderIdCode, orderIdNum, subtotal, tax, payment, amountReceive, amountReturn);

    }

    public static void displayInvoice(ArrayList<Order> orderList, String orderIdCode, int orderIdNum, double subtotal, double tax, double payment, double amountReceive, double amountReturn) {
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime currentDateTime = LocalDateTime.now();
        System.out.println("\n\n\n                  HamFam Furniture");
        System.out.println("                      Invoice          ");
        System.out.println("Date: " + dateTimeFormat.format(currentDateTime) + "\n");
        displayOrder(orderList, orderIdCode, orderIdNum);
        System.out.printf("%42s%10.2f\n", "Subtotal", subtotal);
        System.out.printf("%42s%10.2f\n", "Tax(5%)", tax);
        System.out.printf("%42s%10.2f\n", "Total", payment);
        System.out.println("----------------------------------------------------");
        System.out.printf("%42s%10.2f\n", "Cash", amountReceive);
        System.out.printf("%42s%10.2f\n", "Change", amountReturn);
        System.out.println("\n\t\tThank You, Have a Nice Day\n\n");

    }

    public static void decInventory(ArrayList<Furniture> furnitureList, ArrayList<Order> orderList, String orderIdCode, int orderIdNum) {
        for (int i = 0; i < orderList.size(); i++) {
            for (int j = 0; j < furnitureList.size(); j++) {
                if (orderList.get(i).getOrderIdNum() == orderIdNum && orderList.get(i).getFurnitureID().equals(furnitureList.get(j).getFurnitureID())) {
                    furnitureList.get(j).setQuantity(furnitureList.get(j).getQuantity() - orderList.get(i).getQuantity());
                    break;
                }
            }
        }
    }

    public static void addMemberPoint(ArrayList<Member> memberList, ArrayList<Order> orderList, String orderIdCode, int orderIdNum) {
        for (int i = 0; i < orderList.size(); i++) {
            for (int j = 0; j < memberList.size(); j++) {
                if (orderList.get(i).getOrderIdNum() == orderIdNum && orderList.get(i).getMemberID().equals(memberList.get(j).getId())) {
                    memberList.get(j).setMemberSpoint(memberList.get(j).getMemberSpoint() + 5);
                    break;
                }
            }
        }
    }
}
