package com.lms;

import java.util.ArrayList;
import java.util.Scanner;

public class User {
    boolean haveIssued;
    static int userIdGenerator=0;
    int userId;
    String password,role,name;
    User(){}
    User(String name,String role,String password){
        System.out.println("generating user Id");
        userIdGenerator++;
        setUserId(userIdGenerator);
        this.role=role;
        this.password=password;
        this.name=name;
    }
    User(String name,String role) {//this overload is just for first initial admin creation
        System.out.println("generating user Id");
        userIdGenerator++;
        setUserId(userIdGenerator);
        this.role = role;
        this.name=name;
    }
    public boolean isPresentAndCheckPass(String name, ArrayList<User> userArrayList){
        boolean isPresent=false,userLogged=false;
        Scanner scanner=new Scanner(System.in);
        User user;
        for (int i=0;i<userArrayList.size();i++){
            user=userArrayList.get(i);
            if(name.equals(user.name))
                isPresent=true;
            if(isPresent && user.role.equals("Admin")){
                System.out.println("Enter password");
                String pass=scanner.nextLine();
                if(pass.equals(user.password)){
                    userLogged=true;
                }
            }
        }

        return userLogged;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }
    public void userMenu(){
        System.out.println("1.Add user");
        System.out.println("2.Display Existing users");
        System.out.println("3.Change password");
    }
    public void addUserMenu(){
        System.out.println("1.Add User");
        System.out.println("2.Add admin");
    }
}
