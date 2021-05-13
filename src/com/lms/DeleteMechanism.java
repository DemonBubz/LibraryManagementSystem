package com.lms;

import java.util.ArrayList;
import java.util.Scanner;

public interface DeleteMechanism extends CheckIfPresent{
    Scanner scanner=new Scanner(System.in);
    default void delBooks(String wOb, boolean menu_IsPresentBool, ArrayList<Books> booksArrayList){
        if(menu_IsPresentBool){
            System.out.println("Enter the quantity of books to be removed");
            int delQuantity=scanner.nextInt();
            for(int i=0;i<booksArrayList.size();i++){
                Books temp=booksArrayList.get(i);
                if(temp.bookName.equalsIgnoreCase(wOb)||temp.writerName.equalsIgnoreCase(wOb) && temp.getQuantity()>=1){
                    if(delQuantity>temp.getQuantity())
                    {
                        try {throw new delBookException();}
                        catch (delBookException e){
                            e.printStackTrace();
                        }
                    }else if(delQuantity==temp.getQuantity())
                    {
                        booksArrayList.remove(temp);
                    }else {
                        int quant = temp.getQuantity();
                        quant = quant - delQuantity;
                        temp.setQuantity(quant);
                    }
                }
            }
        }else System.out.println("Book whose details are entered is not present");
    }

    //Check for delete function
    default String checkBeforeDel(ArrayList<Books>booksArrayList){
        boolean menu_IsPresentBool;
        String bookName,writerName;
        System.out.println("Delete Query");//Trying to remove an element from book to check parameters->
        System.out.println("Do you remember writer's name or book name w/b?");
        if(scanner.next().equals("w")) {
            scanner.nextLine();
            System.out.print("Enter Writer Name");
            writerName = scanner.nextLine();
            menu_IsPresentBool=isPresentWriter(writerName,booksArrayList);
            delBooks(writerName,menu_IsPresentBool,booksArrayList);
        }else {
            System.out.print("Enter Book name");
            scanner.nextLine();
            bookName =scanner.nextLine();
            menu_IsPresentBool = isPresentBook(bookName,booksArrayList);
            delBooks(bookName,menu_IsPresentBool,booksArrayList);
        }

        System.out.println("Would you like to exit y/n");
        return scanner.next();

    }
}

