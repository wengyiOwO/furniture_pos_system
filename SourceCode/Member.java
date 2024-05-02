package HamFamFurniture;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
public class Member extends Person{

    //private String memberId, memberName, memberPhoneNo, memberJoinDate;
    private int memberSpoint;

    public Member() {
    }

    public Member(String memberId, String memberName, String memberPhoneNo, String memberJoinDate,int memberSpoint) {
        super(memberId,memberName,memberPhoneNo,memberJoinDate);
        this.memberSpoint= memberSpoint;
    }

    public int getMemberSpoint(){
        return memberSpoint;
    }
    
    public void setMemberSpoint(int memberSpoint){
        this.memberSpoint=memberSpoint;
    }
    @Override
    public String toString() {
        return String.format("|%-9s | %-16s | %-15s | %-17s| %-15s |", super.getId(), super.getName(), super.getPhoneNo(), super.getJoinDate(),memberSpoint);
    }
    public static void MemberManage(ArrayList<Member> array) {
        Scanner sc = new Scanner(System.in);
        int line;
        do {
            System.out.println("-------------------------------------------------------");
            System.out.println("        Welcome to the Member Management System        ");
            System.out.println("-------------------------------------------------------");
            System.out.println("1. View All Member");
            System.out.println("2. Add Member");
            System.out.println("3. Modify Member");
            System.out.println("4. Delete Member");
            System.out.println("0. Exit");
            System.out.print("Please enter your choice:");
            line = sc.nextInt();
            switch (line) {
                case 1:
                    displayMember(array);
                    break;
                case 2:
                    addMember(array);
                    break;
                case 3:
                    updateMember(array);
                    break;
                case 4:
                    deleteMember(array);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid Option");
                    break;
            }
        } while (line != 0);

    }

    public static void loadMember(ArrayList<Member> array) {
        Member member = new Member();
        Member member1 = new Member("M001", "LEE WENG YI", "012-3456789", "21/05/18", 20);
        Member member2 = new Member("M002", "PUA JIA QIAN", "013-4567890", "27/08/20", 30);
        Member member3 = new Member("M003", "GOBIN", "014-5677890", "14/06/21", 40);
        array.add(member1);
        array.add(member2);
        array.add(member3);
    }

    public static void addMember(ArrayList<Member> array) {

        Scanner sc = new Scanner(System.in);

        String memberId;

        while (true) {

            System.out.print("Please enter the Member id:");
            memberId = sc.nextLine().toUpperCase();

            boolean flag = isUsed(array, memberId);
            if (flag) {

                System.out.println("    The member id you entered has been occupied , Please re-enter! ");
            } else {

                break;
            }
        }

        System.out.print("Please enter the member's name :");
        String memberName = sc.nextLine().toUpperCase();

        System.out.print("Please enter the phone number of the member :");
        String memberPhoneNO = sc.nextLine();

        System.out.print("Please enter the member's join date :");
        String memberJoinDate = sc.nextLine();

        System.out.print("Please enter the member's membership point :");
        int memberSpoint = sc.nextInt();

        Member m = new Member();
        m.setId(memberId);
        m.setName(memberName);
        m.setPhoneNo(memberPhoneNO);
        m.setJoinDate(memberJoinDate);
        m.setMemberSpoint(memberSpoint);

        array.add(m);

        System.out.println("    Add member successfully ");
    }

    public static boolean isUsed(ArrayList<Member> array, String memberId) {

        boolean flag = false;

        for (int i = 0; i < array.size(); i++) {

            Member m = array.get(i);
            if (m.getId().equals(memberId)) {

                flag = true;
                break;
            }
        }

        return flag;
    }

    public static void displayMember(ArrayList<Member> array) {

        if (array.isEmpty()) {

            System.out.println("    No information , Please add member before querying! ");
            return;
        }

        System.out.println("\t\t\t\t    Member List\t\t\t");
        System.out.println("======================================================================================");
        System.out.println("|Member ID | Member name      |\t Member Phone No | Member Join Date | Membership Point|");
        System.out.println("======================================================================================");

        for (Member member : array) {
            System.out.println(member.toString());

        }
        System.out.println("=======================================================================================");
    }

    public static void deleteMember(ArrayList<Member> array) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Please enter the Member ID to delete :");
        String memberId = sc.nextLine().toUpperCase();

        int index = -1;

        for (int i = 0; i < array.size(); i++) {

            Member m = array.get(i);
            if (m.getId().equals(memberId)) {

                index = i;
                break;
            }
        }

        if (index == -1) {

            System.out.println("    The member does not exist , Please re-enter! ");
        } else {

            array.remove(index);
            System.out.println("    Delete member success !");
        }
    }

    public static void updateMember(ArrayList<Member> array) {
        Scanner sc = new Scanner(System.in);
        String memberId;
        while (true) {
            System.out.print("Please enter the member ID to modify the information:");
            memberId = sc.nextLine().toUpperCase();
            if (!hasMemberId(array, memberId)) {
                System.err.println("    This member ID does not exist, please re-enter it!");
            } else {
                break;
            }
        }
        System.out.print("Enter the member's new name :");
        String memberName = sc.nextLine().toUpperCase();
        System.out.print("Enter the new phone number of the member :");
        String memberPhoneNo = sc.nextLine();
        System.out.print("Enter the student's new join date :");
        String memberJoinDate = sc.nextLine();
        System.out.print("Please enter the membership point :");
        int memberSpoint = sc.nextInt();

        Member m = new Member();
        m.setId(memberId);
        m.setName(memberName);
        m.setPhoneNo(memberPhoneNo);
        m.setJoinDate(memberJoinDate);
        m.setMemberSpoint(memberSpoint);

        for (int i = 0; i < array.size(); i++) {
            if (memberId.equals(array.get(i).getId())) {
                array.set(i, m);
            }
        }
        System.out.println("    Successful Member Information Modification!");
    }

    private static boolean hasMemberId(List<Member> array, String memberId) {
        for (int i = 0; i < array.size(); i++) {
            if (memberId.equals(array.get(i).getId())) {
                return true;
            }
        }
        return false;

    }

}
