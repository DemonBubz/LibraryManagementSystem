package com.lms;

import java.util.Scanner;
import java.util.ArrayList;

class delBookException extends Exception{
    public String toString(){
        return "InputExceedsDBCapacity";
    }
}
class Mechanism implements DeleteMechanism,AddBooks{
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
        System.out.println("1.com.lms.Books");
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
                        System.out.println("Enter number of books needed");
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
                        if(checkBeforeDel(booksArrayList).equals("n"))
                        menuImplementation();
                    }
                    case 3->{
                        System.out.println("displaying elements");
                        displayBookList();
                        System.out.println("Would you like to exit y/n");
                        if(scanner.next().equals("n"))
                            menuImplementation();
                    }
                }

            }
        }
    }
}
public class Library {
    public static void main(String[] args) {
        Mechanism createConstruct =new Mechanism();
        createConstruct.menuImplementation();

    }
}
