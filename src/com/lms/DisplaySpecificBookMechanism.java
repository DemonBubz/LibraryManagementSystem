package com.lms;
import java.util.ArrayList;
import java.util.Scanner;
public interface DisplaySpecificBookMechanism extends CheckIfPresent {
    Scanner scanner=new Scanner(System.in);
    default String checkBeforeDisplay(ArrayList<Books>booksArrayList){
        String bookName,writerName;
        System.out.println("Search by book name or writer name b/w");
        if(scanner.next().equals("b")){
            System.out.println("Enter Book Name");
            scanner.nextLine();
            bookName=scanner.nextLine();
            boolean found=isPresentBook(bookName,booksArrayList);
            display(bookName,found,booksArrayList);
        }else {
            scanner.nextLine();
            System.out.print("Enter Writer Name");
            writerName = scanner.nextLine();
            boolean found=isPresentWriter(writerName, booksArrayList);
            display(writerName,found,booksArrayList);
        }
        System.out.println("Do you want to exit the program y/n");
            return scanner.next();
    }
    default void display(String bow,boolean found,ArrayList<Books>booksArrayList){
        if(found){
            for(int i=0;i<booksArrayList.size();i++){
                Books tempInstance=booksArrayList.get(i);
                if(tempInstance.writerName.equalsIgnoreCase(bow)||tempInstance.bookName.equalsIgnoreCase(bow)){
                    System.out.println(tempInstance.bookId + " " + tempInstance.price + " " + tempInstance.quantity + " " + tempInstance.bookName + " " + tempInstance.writerName);
                }
            }
        }else System.out.println("Entry does not exist");

    }
}
