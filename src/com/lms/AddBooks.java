package com.lms;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;


public interface AddBooks {
    Scanner scanner=new Scanner(System.in);
    default  void addBookElements(Books myBooks, ArrayList<Books>booksArrayList){
        System.out.println("Generating ID for book");
        UUID uid= UUID.randomUUID();
        myBooks.setBookId(uid);
        UUID currBookId= myBooks.getBookId();

        System.out.println("Enter Quantity");
        myBooks.setQuantity(scanner.nextInt());
        int currBookQuantity= myBooks.getQuantity();

        System.out.println("Enter Price");
        myBooks.setPrice(scanner.nextInt());
        int currBookPrice= myBooks.getPrice();

        scanner.nextLine();
        System.out.println("Enter Book Name");
        myBooks.setBookName(scanner.nextLine());
        String currBookName= myBooks.getBookName();

        System.out.println("Enter Writer Name");
        myBooks.setWriterName(scanner.nextLine());
        String  currWriterName= myBooks.getWriterName();
        booksArrayList.add(new Books(currBookId,currBookPrice,currBookQuantity,currBookName,currWriterName));
    }
}
