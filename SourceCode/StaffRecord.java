/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HamFamFurniture;

/**
 *
 * @author Acer
 */
import java.util.LinkedList;
import java.util.Scanner;

public class StaffRecord {
    
    LinkedList<Staff> list;
    
    public StaffRecord(){
        
        list = new LinkedList<>();
    }
    
     public void add(Staff staff)
    {
 
        
        if (!find(staff.getId())) {
            list.add(staff);
        }
        else {
 
            System.out.println(
                "This staff record already exists in the Staff Record list");
        }
    }
     public boolean find(String sId)
    {
 

        for (Staff l : list) {
 
            if (l.getId().equals(sId)) {
                
                System.out.println(l);
                return true;
            }
        }
        return false;
    }
     public void delete(String idNo){
         Staff staffDel = null;
         
         for(Staff ll:list){
             if(ll.getId().equals(idNo)){
             staffDel = ll;
           }
         }
         
         if(staffDel == null){
             System.out.println("Invalid record Staff ID");
         }else{
             list.remove(staffDel);
             System.out.println("Successfully removed staff record from the list");
         }
         
             }
     public Staff findStaff(String idNumber){
         for(Staff l:list){
             
             if(l.getId().equals(idNumber)){
                 return l;
             }
         }
         return  null;
     }
      public void update(String id,Scanner input){
             if(find(id)){
                 Staff s = findStaff(id);
                 
                 System.out.print("Enter new Staff ID: ");
                 String newIdNo = input.nextLine();
                 
                 System.out.print("Enter new Staff Name: ");
                 String newName = input.next();
                 input.nextLine();

                  System.out.print("Enter new Staff Contact No: ");
                 String newCNo = input.next();
                 input.nextLine();
                 System.out.print("Enter new Staff Email: ");
                 String newEmail = input.next();
                 input.nextLine();                 
                  System.out.print("Enter new Staff join date: ");
                 String newJDate = input.next();
                 input.nextLine();
                 System.out.print("Enter new Staff working position: ");
                 String newPosition = input.next();
                 input.nextLine();
                 s.setId(newIdNo);
                 s.setName(newName);
                 s.setPhoneNo(newCNo);
                 s.setStaffEmail(newEmail);
                 s.setJoinDate(newJDate);
                 s.setPosition(newPosition);
             }
         }
      
      public void display(){
          if(list.isEmpty()){
              System.out.println("\nThe list has no staff records");
          }
          System.out.println("\t\t\t\t    Staff List\t\t\t");
        System.out.println("======================================================================================");
        System.out.println("| ID  | Name  \t \t | Contact No |  Email \t  |Joined Date| Working Position|");
        System.out.println("======================================================================================");    
          for(Staff staff:list){
             System.out.println(staff.toString());          
          }
           System.out.println("======================================================================================");

      }
      
}