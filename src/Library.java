import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

class Books{
     protected int bookId,price,quantity;
     protected String bookName,writerName;
     Books(){
         System.out.println("Book Object created");
     }
    Books(int bookId, int price, int quantity, String bookName, String writerName){
        this.bookId=bookId;
        this.price=price;
        this.quantity=quantity;
        this.bookName=bookName;
        this.writerName=writerName;
    }
public void setBookId(int bookId){
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
}
public class Library {
    static Scanner scanner=new Scanner(System.in);
    static ArrayList<Books>booksArrayList=new ArrayList<>();
    static Books myBooks=new Books();
    public static void addBookElements(){
        System.out.println("Enter Book Id");
        myBooks.setBookId(scanner.nextInt());
        int currBookId= myBooks.getBookId();

        System.out.println("Enter Quantity");
        myBooks.setQuantity(scanner.nextInt());
        int currBookQuantity= myBooks.getQuantity();

        System.out.println("Enter Price");
        myBooks.setPrice(scanner.nextInt());
        int currBookPrice= myBooks.getPrice();

        System.out.println("Enter Book Name");
        myBooks.setBookName(scanner.next());
        String currBookName= myBooks.getBookName();

        System.out.println("Enter Writer Name");
        myBooks.setWriterName(scanner.next());
        String  currWriterName= myBooks.getWriterName();
        booksArrayList.add(new Books(currBookId,currBookPrice,currBookQuantity,currBookName,currWriterName));
    }
    public static void displayBookList() {
        for (int i = 0; i < booksArrayList.size(); i++) {
            Books book = booksArrayList.get(i);
            System.out.println(book.bookId + " " + book.price + " " + book.quantity + " " + book.bookName + " " + book.writerName);
        }

    }
    public static void main(String[] args) {
        System.out.println("Enter number of books needed");
      Scanner sc=new Scanner(System.in);
      int n=sc.nextInt();
      while(n>0){
          addBookElements();
          n--;
      }
        System.out.println("displaying elements");
        {
            displayBookList();
        }
    }
}
