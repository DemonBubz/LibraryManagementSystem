package libProj;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class User implements Serializable{
    ArrayList<User>userArrayList=new ArrayList<>();
    boolean haveIssued=false;
    UUID userId;
    String password,role,name;
    User(){}
    User(String name,String role,String password){
        System.out.println("generating user Id");
        this.userId=UUID.randomUUID();
        this.role=role;
        this.password=password;
        this.name=name;
    }
    public void createAdmin() throws IOException{
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter name: ");
        String name=scanner.nextLine();
        System.out.print("Enter password: ");
        String pass=scanner.nextLine();
        userArrayList.add(new User(name,"Admin",pass));
        serializeUser();
    }
    public void addUser() throws IOException {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Generating Id for user..");

        System.out.print("Enter name: ");
        setName(scanner.nextLine());
        String currName=getName();

        System.out.print("Enter password: ");
        setPassword(scanner.nextLine());
        String currPass=getPassword();

        userArrayList.add(new User(currName,"User",currPass));
        serializeUser();
    }
    public void displayUsers(){
        for(int i=0;i<userArrayList.size();i++){
            User user=userArrayList.get(i);
            if(user.haveIssued) {
                System.out.println(user.userId + " " + user.name + " " + user.role + " " + "Book Issued");
            }else System.out.println(user.userId+" "+user.name+" "+user.role+" "+"Book not Issued");

        }
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
    public UUID getUserId() {
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
            if(isPresent ){
                if(Admin.role.equals("Admin")) {
                    System.out.print("Enter password: ");
                    String pass = scanner.nextLine();
                    System.out.println();
                    if (pass.equals(Admin.password)) {
                        AdminLogged = true;
                        break;
                    }
                }
            }
        }

        return AdminLogged;
    }
    public User returnIfFound(){
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter Name: ");
        String name=scanner.nextLine();
        User temp2=new User();
        boolean isPresentUser=isPresentAndCheckPass(name);
        if(isPresentUser){
            for(int i=0;i<userArrayList.size();i++){
                User temp= userArrayList.get(i);
                if(temp.getName().equalsIgnoreCase(name)) {
                    temp2 = temp;
                    return temp2;
                }
            }
        }
        return temp2;
    }

    public boolean isPresentAndCheckPass(String name){
        boolean isPresent=false,userLogged=false;
        Scanner scanner=new Scanner(System.in);
        User user;
        for (int i=0;i<userArrayList.size();i++){
            user=userArrayList.get(i);
            if(name.equalsIgnoreCase(user.name))
                isPresent=true;
            if(isPresent ){
                    System.out.print("Enter password: ");
                    String pass = scanner.nextLine();
                    if (pass.equals(user.password)) {
                        userLogged = true;
                        break;
                    }
            }
        }

        return userLogged;
    }
    public void serializeUser() throws IOException{
            FileOutputStream file = new FileOutputStream("user.ser");
            ObjectOutputStream userObject=new ObjectOutputStream(file);
            userObject.writeObject(userArrayList);
            userObject.close();
            file.close();
    }
    public void deserializeUser() throws IOException,ClassNotFoundException {
            FileInputStream file = new FileInputStream("user.ser");
            ObjectInputStream bookObject=new ObjectInputStream(file);
            ArrayList<User>userListFromFile=(ArrayList<User>)bookObject.readObject();
            bookObject.close();
            userArrayList=userListFromFile;
    }
}

