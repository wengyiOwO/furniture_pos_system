/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HamFamFurniture;

/**
 *
 * @author yongy
 */
public abstract class Person {
    protected String id;
    protected String name;
    protected String phoneNo;
    protected String joinDate;
    
    public Person(){
        this("","","","");
    }
    public Person(String id, String name, String phoneNo, String joinDate){
        this.id = id;
        this.name = name;
        this.phoneNo = phoneNo;
        this.joinDate = joinDate;
    }
    public String getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getPhoneNo(){
        return phoneNo;
    }
    public String getJoinDate(){
        return joinDate;
    }
    public void setId(String id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setPhoneNo(String phoneNo){
        this.phoneNo = phoneNo;
    }
    public void setJoinDate(String joinDate){
        this.joinDate = joinDate;
    }
    public String toString(){
        return String.format("|%-9s | %-16s | %-15s | %-17s|", id,name,phoneNo,joinDate);
    }
}
