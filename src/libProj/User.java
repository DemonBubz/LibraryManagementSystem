package libProj;

import java.util.ArrayList;
import java.util.Scanner;

public class User {
    ArrayList<User>userArrayList=new ArrayList<>();
    boolean haveIssued=false;
    Scanner scanner=new Scanner(System.in);
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
    public void createAdmin(){
        System.out.println("Enter name");
        String name=scanner.nextLine();
        System.out.println("Enter password");
        String pass=scanner.nextLine();
        userArrayList.add(new User(name,"Admin",pass));
    }
    public void addUser(){
        System.out.println("Generating Id for user..");

        System.out.println("Enter name");
        setName(scanner.nextLine());
        String currName=getName();

        System.out.println("Enter password");
        setPassword(scanner.nextLine());
        String currPass=getPassword();

        userArrayList.add(new User(currName,"User",currPass));
    }
    public void displayUsers(){
        for(int i=0;i<userArrayList.size();i++){
            User user=userArrayList.get(i);
            System.out.println(user.userId+" "+user.name+" "+user.role+" "+user.haveIssued);
        }
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
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getUserId() {
        return userId;
    }
    public boolean isAdminAndCheckPass(String name){
        boolean isPresent=false,AdminLogged=false;
        Scanner scanner=new Scanner(System.in);
        User Admin;
        for (int i=0;i<userArrayList.size();i++){
            Admin=userArrayList.get(i);
            if(name.equals(Admin.name))
                isPresent=true;
            if(isPresent && Admin.role.equalsIgnoreCase("Admin")){
                System.out.println("Enter password");
                String pass=scanner.nextLine();
                if(pass.equals(Admin.password)){
                    AdminLogged=true;
                }
            }
        }

        return AdminLogged;
    }
    public User returnIfFound(){
        System.out.println("Enter Name");
        String name=scanner.nextLine();
        User temp2=new User();
        boolean isPresentAdmin=isAdminAndCheckPass(name);
        boolean isPresentUser=isUserAndCheckPass(name);
        if(isPresentAdmin || isPresentUser){
            for(int i=0;i<userArrayList.size();i++){
                User temp= userArrayList.get(i);
                if(temp.getName().equalsIgnoreCase(name))
                    temp2=temp;
                return temp2;
            }
        }
        return temp2;
    }

    public boolean isUserAndCheckPass(String name){
        boolean isPresent=false,userLogged=false;
        Scanner scanner=new Scanner(System.in);
        User user;
        for (int i=0;i<userArrayList.size();i++){
            user=userArrayList.get(i);
            if(name.equalsIgnoreCase(user.name))
                isPresent=true;
            if(isPresent && user.role.equalsIgnoreCase("User")){
                System.out.println("Enter password");
                String pass=scanner.nextLine();
                if(pass.equals(user.password)){
                    userLogged=true;
                }
            }
        }

        return userLogged;
    }

}

