package com.lms;

import java.util.UUID;

class Books{
    boolean isIssued;
    static protected int bookIdGenerator=0;
    int bookId;
    protected int price,quantity;
    protected String bookName,writerName;
    Books(){

    }
    Books(int price, int quantity, String bookName, String writerName){
        bookIdGenerator=bookIdGenerator+1;
        setBookId(bookIdGenerator);
        this.price=price;
        this.quantity=quantity;
        this.bookName=bookName;
        this.writerName=writerName;
    }
    protected void setBookId(int bookId){
        this.bookId=bookId;
    }
    public void setPrice(int price){
        this.price=price;
    }
    public void setQuantity(int quantity){
        this.quantity=quantity;
    }
    public void setBookName(String bookName){
        this.bookName=bookName;
    }
    public void setWriterName(String writerName){
        this.writerName=writerName;
    }
    public int getBookId(){
        return bookId;
    }
    public int getPrice(){
        return price;
    }
    public int getQuantity(){
        return quantity;
    }
    public String getBookName(){
        return bookName;
    }
    public String getWriterName(){
        return writerName;
    }
    public void bookMenu(){
        System.out.println("1.Add Books");
        System.out.println("2.Delete Books");
        System.out.println("3.Display all Books");
        System.out.println("4.Display Specific Book");
    }
}