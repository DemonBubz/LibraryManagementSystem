package com.lms;

import java.util.UUID;

class Books{
    protected UUID bookId;
    protected int price,quantity;
    protected String bookName,writerName;
    Books(){

    }
    Books(UUID bookId, int price, int quantity, String bookName, String writerName){
        this.bookId=bookId;
        this.price=price;
        this.quantity=quantity;
        this.bookName=bookName;
        this.writerName=writerName;
    }
    public void setBookId(UUID bookId){
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
    public UUID getBookId(){
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
        System.out.println("1.Add com.lms.Books");
        System.out.println("2.Delete com.lms.Books");
        System.out.println("3.Display com.lms.Books");
    }
}