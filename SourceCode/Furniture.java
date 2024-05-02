/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HamFamFurniture;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class Furniture {
    private String furnitureID;
    private String furnitureName;
    private double price;
    private int quantity;
    public Furniture(){
        
    }
    public Furniture(String furnitureID, String furnitureName, double price, int quantity){
        this.furnitureID = furnitureID;
        this.furnitureName = furnitureName;
        this.price = price;
        this.quantity = quantity;
    }
    public String getFurnitureID(){
        return furnitureID;
    }
    public String getFurnitureName(){
        return furnitureName;
    }
    public double getPrice(){
        return price;
    }
    public int getQuantity(){
        return quantity;
    }
    public void setFurnitureID(String furnitureID){
        this.furnitureID = furnitureID;
    }
    public void setFurnitureName(String furnitureName){
        this.furnitureName = furnitureName;
    }
    public void setPrice(double price){
        this.price = price;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    @Override
    public String toString(){
        return String.format("| %-6s | %-20s | %10.2f | %8d |", 
                furnitureID, furnitureName, price, quantity);
    }
    public static void FurnitureManage(ArrayList<Furniture> furnitureList) {
        Furniture furniture = new Furniture();
        Scanner scanner = new Scanner(System.in);

        int option = 0;
        do {
            System.out.println("\n\n----Manage Furniture Menu----");
            System.out.println("1. Add Furniture");
            System.out.println("2. Delete Furniture");
            System.out.println("3. Modify Furniture");
            System.out.println("4. Display Furniture");
            System.out.println("0. Exit");
            System.out.print("Enter option: ");
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    addFurniture(furnitureList);
                    break;
                case 2:
                    deleteFurniture(furnitureList);
                    break;
                case 3:
                    modifyFurniture(furnitureList);
                    break;
                case 4:
                    displayFurniture(furnitureList);
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

    public static void loadFurniture(ArrayList<Furniture> furnitureList) {
        Furniture furniture = new Furniture();
        Furniture furniture1 = new Furniture("F001", "Sofa", 500.0, 70);
        Furniture furniture2 = new Furniture("F002", "Folding Chair", 100.0, 30);
        Furniture furniture3 = new Furniture("F003", "Wooden Chair", 250, 50);
        Furniture furniture4 = new Furniture("F004", "Marble Chair", 750.0, 110);
        Furniture furniture5 = new Furniture("F005", "Marble Table", 1050.0, 100);
        Furniture furniture6 = new Furniture("F006", "Office Table", 450.0, 75);
        Furniture furniture7 = new Furniture("F007", "Coffea Table", 200.0, 150);
        Furniture furniture8 = new Furniture("F008", "Wooden Table", 150.0, 200);
        Furniture furniture9 = new Furniture("F009", "Kitchen Cabinet", 850.0, 50);
        Furniture furniture10 = new Furniture("F010", "Floor Lamp", 110.0, 350);

        furnitureList.add(furniture1);
        furnitureList.add(furniture2);
        furnitureList.add(furniture3);
        furnitureList.add(furniture4);
        furnitureList.add(furniture5);
        furnitureList.add(furniture6);
        furnitureList.add(furniture7);
        furnitureList.add(furniture8);
        furnitureList.add(furniture9);
        furnitureList.add(furniture10);
    }

    public static void addFurniture(ArrayList<Furniture> furnitureList) {

        // Enter the data required by the student object with the keyboard , Display prompt message , Prompt what information to enter 
        Scanner scan = new Scanner(System.in);
        Furniture furniture = new Furniture();
        String newID;
        while (true) {
            System.out.print("Enter Furniture ID: ");
            newID = scan.next();
            scan.nextLine();

            boolean flag = isUsed(furnitureList, newID);
            if (flag) {
                System.out.println(newID + " entered is duplicated.");
            } else {
                break;
            }
        }

        System.out.print("Enter Furniture Name: ");
        String newName = scan.nextLine();
        System.out.print("Enter Price: ");
        double newPrice = scan.nextDouble();
        System.out.print("Enter Quantity: ");
        int newQuantity = scan.nextInt();

        furniture = new Furniture(newID, newName, newPrice, newQuantity);
        furnitureList.add(furniture);

        System.out.println(newID + " added successfully. ");
    }

    public static boolean isUsed(ArrayList<Furniture> furnitureList, String furnitureID) {

        // If it is the same as a student number in the set , return true; If it's not the same , return false
        boolean flag = false;

        for (int i = 0; i < furnitureList.size(); i++) {

            Furniture furniture = furnitureList.get(i);
            if (furniture.getFurnitureID().equals(furnitureID)) {

                flag = true;
                break;
            }
        }

        return flag;
    }

    //display Furniture
    public static void displayFurniture(ArrayList<Furniture> furnitureList) {

        //if empty data
        if (furnitureList.isEmpty()) {
            System.out.println("There is no record\n");
        } else {
            System.out.println("\t\t\tFurniture List");
            System.out.println("=========================================================");
            System.out.printf("| %-6s | %-20s | %10s | %8s |\n", "ID", "Furniture Name", "Price(RM)", "Quantity");
            System.out.println("=========================================================");
            for (Furniture furniture : furnitureList) {
                System.out.println(furniture.toString());
            }
            System.out.println("=========================================================");
        }
    }

    // Delete furniture
    public static void deleteFurniture(ArrayList<Furniture> furnitureList) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter delete Furniture ID: ");
        String deleteID = scan.nextLine().toUpperCase();
        int index = -1;

        for (int i = 0; i < furnitureList.size(); i++) {

            Furniture furniture = furnitureList.get(i);
            if (furniture.getFurnitureID().equals(deleteID)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println(deleteID + " does not exist. ");
        } else {
            furnitureList.remove(index);
            System.out.println(deleteID + " delete success !");
        }
    }

    public static void modifyFurniture(ArrayList<Furniture> furnitureList) {
        Scanner scan = new Scanner(System.in);
        Furniture furniture = new Furniture();
        String furnitureID;
        System.out.print("Enter modify Furniture ID: ");
        furnitureID = scan.next();
        scan.nextLine();
        int index = -1;
        for (int i = 0; i < furnitureList.size(); i++) {
            furniture = furnitureList.get(i);
            if (furniture.getFurnitureID().equals(furnitureID)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            System.out.println(furnitureID + " does not exist. ");
        } else {
            System.out.print("Enter modify furniture name: ");
            String modifyName = scan.nextLine();
            furniture.setFurnitureName(modifyName);

            System.out.print("Enter modify price: ");
            double modifyPrice = scan.nextDouble();
            furniture.setPrice(modifyPrice);

            System.out.print("Enter modify quantity: ");
            int modifyQuantity = scan.nextInt();
            furniture.setQuantity(modifyQuantity);

            System.out.println(furnitureID + " modified success !");
        }
           
    }

}
