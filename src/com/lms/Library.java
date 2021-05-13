package com.lms;

import java.util.Scanner;
import java.util.ArrayList;

class delBookException extends Exception{
    public String toString(){
        return "InputExceedsDBCapacity";
    }
}
class Mechanism implements DeleteMechanism,AddBooks,DisplaySpecificBookMechanism{
     Scanner scanner=new Scanner(System.in);
     ArrayList<Books>booksArrayList=new ArrayList<>();
     Books myBooks=new Books();
     public void displayBookList() {
        for (int i = 0; i < booksArrayList.size(); i++) {
            Books book = booksArrayList.get(i);
            System.out.println(book.bookId + " " + book.price + " " + book.quantity + " " + book.bookName + " " + book.writerName);
        }
    }
    public void displayMainMenu(){
        System.out.println("Welcome to library management system");
        System.out.println("choose one of the given options by pressing respective numeric key");
        System.out.println("1.Books");
        System.out.println("2.Issuers");
    }
    public void menuImplementation(){
         displayMainMenu();
        int c=scanner.nextInt();
        switch(c){
            case 1->{
                System.out.println("Books");
                myBooks.bookMenu();
                int bookMenuChoice=scanner.nextInt();

                switch (bookMenuChoice){
                    case 1->{
                        System.out.print("Enter number of books needed: ");
                        Scanner sc=new Scanner(System.in);
                        int n=sc.nextInt();
                        while(n>0){
                            addBookElements(myBooks,booksArrayList);
                            n--;
                        }
                        System.out.println("Would you like to exit y/n");
                        if(scanner.next().equals("n"))
                            menuImplementation();
                    }
                    case 2->{
                        if(checkBeforeDel(booksArrayList).equalsIgnoreCase("n"))
                        menuImplementation();
                    }
                    case 3->{
                        System.out.println("displaying elements");
                        displayBookList();
                        System.out.println("Would you like to exit y/n");
                        if(scanner.next().equals("n"))
                            menuImplementation();
                    }
                    case 4->{
                        System.out.println("Specific book information");
                        if(checkBeforeDisplay(booksArrayList).equalsIgnoreCase("n"))
                            menuImplementation();
                    }
                }

            }
            case 2->{
                System.out.println("user");

            }
        }
    }
}
public class Library {
   static Scanner scanner=new Scanner(System.in);
    static ArrayList<User>userArrayList=new ArrayList<>();
    static User myUser=new User();

    public static void createAdmin(){
        System.out.println("Enter name");
        scanner.nextLine();
        String name=scanner.nextLine();
        System.out.println("Enter password");
        String pass=scanner.nextLine();
        userArrayList.add(new User(name,"Admin",pass));
    }
    public static void loginSession(){
        System.out.println("Welcome to Library Management System");
        System.out.println("1.Existing admin login");
        System.out.println("2.Create new admin");
        int c=scanner.nextInt();
        switch(c){
            case 1->{
                System.out.println("checking database for existing admins..");
                if(userArrayList.isEmpty()) {
                    System.out.println("No admins found");
                    createAdmin();
                    loginSession();
                }else {
                    System.out.println("Login");
                    System.out.println("Enter Name");
                    scanner.nextLine();
                    boolean userLogged=myUser.isPresentAndCheckPass(scanner.nextLine(),userArrayList);
                    if(userLogged){
                        Mechanism createConstruct =new Mechanism();
                        createConstruct.menuImplementation();
                    }
                }
            }

            case 2->{
                createAdmin();
            }
        }

    }
    public static void main(String[] args) {
    loginSession();
    }
}
