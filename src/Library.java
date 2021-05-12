import java.util.Scanner;
import java.util.ArrayList;
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
    System.out.println("1.Add Books");
    System.out.println("2.Delete Books");
    System.out.println("3.Display Books");
}
}

class Mechanism{
     Scanner scanner=new Scanner(System.in);
     ArrayList<Books>booksArrayList=new ArrayList<>();
     Books myBooks=new Books();

     public boolean isPresent(String bookName){
         boolean isPresent=false;
         for(int i=0;i<booksArrayList.size();i++){
             Books tempInstance=booksArrayList.get(i);
             if(bookName.equals(tempInstance.bookName)) {
                 isPresent = true;
                break;
             }
         }
         return isPresent;
     }
     public boolean isPresent(String bookName,String writerName){    //Overloading in case user remembers both book name and writer name
         boolean isPresent=false;
         for(int i=0;i<booksArrayList.size();i++){
             Books tempInstance=booksArrayList.get(i);
             if(bookName.equals(tempInstance.bookName) && writerName.equals(tempInstance.writerName)) {
                 isPresent = true;
                break;
             }
         }
         return isPresent;
     }

     public  void addBookElements(){
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

        System.out.println("Enter Book Name");
        myBooks.setBookName(scanner.next());
        String currBookName= myBooks.getBookName();

        System.out.println("Enter Writer Name");
        myBooks.setWriterName(scanner.next());
        String  currWriterName= myBooks.getWriterName();
        booksArrayList.add(new Books(currBookId,currBookPrice,currBookQuantity,currBookName,currWriterName));
    }
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
                        System.out.println("Enter number of books needed");
                        Scanner sc=new Scanner(System.in);
                        int n=sc.nextInt();
                        while(n>0){
                            addBookElements();
                            n--;
                        }
                        System.out.println("Would you like to exit y/n");
                        if(scanner.next().equals("n"))
                            menuImplementation();
                    }
                    case 2->{
                        boolean menu_IsPresentBool;
                        String bookName,writerName;
                        System.out.println("Delete Query");//Trying to remove an element from book to check parameters->
                        System.out.println("Do you remember writer's name alongside book name y/n?");
                        if(scanner.next().equals("y")) {
                            bookName = scanner.next();
                            writerName = scanner.next();
                            menu_IsPresentBool=isPresent(bookName,writerName);
                        }else {
                            bookName =scanner.next();
                            menu_IsPresentBool = isPresent(bookName);
                        }
                        if(menu_IsPresentBool){
                        for(int i=0;i<booksArrayList.size();i++){
                            Books temp=booksArrayList.get(i);
                            if(temp.bookName.equals(bookName) && temp.getQuantity()==1){       //if quantity of book type is 0 remove it completely
                                booksArrayList.remove(temp);
                            }else if(temp.bookName.equals(bookName) && temp.getQuantity()>1){   //else if quantity is greater than 1 reduce it by 1
                                int quant=temp.getQuantity();
                                quant=quant-1;
                                temp.setQuantity(quant);
                            }
                        }
                        }
                        System.out.println("Would you like to exit y/n");
                        if(scanner.next().equals("n"))
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
