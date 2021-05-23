package libProj;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;
/*
INTERFACE TO CHECK IF A ENTERED BOOK IS PRESENT IN THE ARRAYLIST
CONTAINS TWO FUNCTIONS
1.FUNCTION CHECKS FOR PRESENCE ON THE BASIS OF BOOK NAME
2.FUNCTION CHECKS FOR PRESENCE ON THE BASIS OF WRITER'S NAME
 */

interface CheckIfPresent{
    default boolean isPresentBook(String bookName, ArrayList<Books> booksArrayList){
        boolean isPresent=false;
        for(int i=0;i<booksArrayList.size();i++){
            Books tempInstance=booksArrayList.get(i);
            if(bookName.equalsIgnoreCase(tempInstance.bookName)) {
                isPresent = true;
                break;
            }
        }
        return isPresent;
    }
    default boolean isPresentWriter(String writerName,ArrayList<Books>booksArrayList){    //Overloading in case user remembers both book name and writer name
        boolean isPresent=false;
        for(int i=0;i<booksArrayList.size();i++){
            Books tempInstance=booksArrayList.get(i);
            if( writerName.equalsIgnoreCase(tempInstance.writerName)) {
                isPresent = true;
                break;
            }
        }
        return isPresent;
    }

}
/*
EXTENSION OF EXCEPTION CLASS RAISES EXCEPTION IN DELETE FUNCTION
IF ENTERED AMOUNT TO BE DELETED IS GREATER THAN AVAILABLE QUANTITY
 */
class delBookException extends Exception{
    public String toString(){
        return "InputExceedsDBCapacity";
    }
}
/*
INTERFACE TO DEFINE ABSTRACT FUNCTION RETURN THIS BOOK WHICH IS IMPLEMENTED
IN MAIN BOOK CLASS SEARCHES FOR A BOOK ON THE BASIS OF CHECK IF PRESENT
INTERFACE AND RETURNS BOOK OBJECT IF FOUND
 */


interface SpecificBookMechanism extends CheckIfPresent {
    Scanner scanner=new Scanner(System.in);
    Books returnThisBook();

}
/*
MAIN BOOK CLASS IMPLEMENTS CHECK IF PRESENT INTERFACE AND SPECIFIC BOOK MECHANISM
CONTAINS ARRAY LIST OF BOOKS AND OTHER DETAILS RELATED TO BOOKS
 */

class Books implements CheckIfPresent,SpecificBookMechanism,Serializable{
    ArrayList<String> issuedTo=new ArrayList<>();
    ArrayList<Books>booksArrayList=new ArrayList<>();
      UUID bookId;
    protected int price,quantity;
    protected String bookName,writerName;

    /*
    ABSTRACT METHOD OF SPECIFIC BOOK MECHANISM IS DEFINED HENCEFORTH
     */
    public Books returnThisBook(){
        boolean found;
        Books temp=new Books();
        String bookName,writerName;
        System.out.println("Search by book name or writer name b/w");
        if(scanner.next().equals("b")){
            System.out.print("Enter Book Name: ");
            scanner.nextLine();
            bookName=scanner.nextLine();
            found=isPresentBook(bookName,booksArrayList);   //CALLS IF PRESENT ON BASIS OF BOOK NAME
            if(found){
                System.out.println("found");
                for(int i=0;i<booksArrayList.size();i++){
                    Books book=booksArrayList.get(i);
                    if(book.bookName.equalsIgnoreCase(bookName)) {
                        return book;
                    }
                }
            }else System.out.println("Not found the book");
        }else {
            scanner.nextLine();
            System.out.print("Enter Writer Name: ");
            writerName = scanner.nextLine();
            found=isPresentWriter(writerName, booksArrayList);  //CALLS IF PRESENT ON THE BASIS OF WRITER NAME
            if(found){
                for(int i=0;i<booksArrayList.size();i++){
                    Books book=booksArrayList.get(i);
                    if(book.writerName.equalsIgnoreCase(writerName)) {
                        temp = book;
                        return temp;
                    }
                }
            }
        }
        return temp;
    }
    Books(){ }  //CONSTRUCTOR WITH NO PARAMETERS TO INITIATE OBJECT IN MAIN

    Books(int price, int quantity, String bookName, String writerName){     //CONSTRUCTOR AIMED AT ADDING NEW ELEMENTS
        this.bookId=UUID.randomUUID();
        this.price=price;
        this.quantity=quantity;
        this.bookName=bookName;
        this.writerName=writerName;
    }
        public void addBookElements() throws IOException{
        System.out.println("Generating ID for book");

        System.out.print("Enter Quantity: ");
        setQuantity(scanner.nextInt());
        int currBookQuantity= getQuantity();

        System.out.print("Enter Price: ");
        setPrice(scanner.nextInt());
        int currBookPrice= getPrice();

        scanner.nextLine();
        System.out.print("Enter Book Name: ");
        setBookName(scanner.nextLine());
        String currBookName= getBookName();

        System.out.print("Enter Writer Name: ");
        setWriterName(scanner.nextLine());
        String currWriterName= getWriterName();
        booksArrayList.add(new Books(currBookPrice,currBookQuantity,currBookName,currWriterName));
        System.out.println();
        serializeBook();
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

    public void displayBookList() {
        for (int i = 0; i < booksArrayList.size(); i++) {
            Books book = booksArrayList.get(i);
            System.out.println("Id: "+book.bookId + " price: " + book.price + " quantity: " + book.quantity + " name: " + book.bookName + " writer: " + book.writerName+" Issued To: "+book.issuedTo);
        }
    }

        public void delBooks(String wOb, boolean menu_IsPresentBool) throws IOException {
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
                    serializeBook();
                    break;
                }
            }
        }else System.out.println("Book whose details are entered is not present");
    }

    //Check for delete function
    public String checkBeforeDel() throws IOException {
        boolean menu_IsPresentBool;
        String bookName,writerName;
        System.out.println("Delete Query");//Trying to remove an element from book to check parameters->
        System.out.println("Do you remember writer's name or book name w/b?");
        if(scanner.next().equalsIgnoreCase("w")) {
            scanner.nextLine();
            System.out.print("Enter Writer Name: ");
            writerName = scanner.nextLine();
            menu_IsPresentBool=isPresentWriter(writerName,booksArrayList);
            delBooks(writerName,menu_IsPresentBool);
        }else {
            System.out.print("Enter Book name: ");
            scanner.nextLine();
            bookName =scanner.nextLine();
            menu_IsPresentBool = isPresentBook(bookName,booksArrayList);
            delBooks(bookName,menu_IsPresentBool);
        }

        System.out.println("Would you like to exit y/n");
        return scanner.next();

    }
    public void serializeBook() throws IOException{
            File file1=new File("book.ser");
               FileOutputStream file = new FileOutputStream(file1);
               ObjectOutputStream bookObject = new ObjectOutputStream(file);
               bookObject.writeObject(booksArrayList);
               bookObject.close();
               file.close();

    }
    public void deserializeBook() throws IOException,ClassNotFoundException {
            FileInputStream file = new FileInputStream("book.ser");
            ObjectInputStream bookObject=new ObjectInputStream(file);
            ArrayList<Books> bookListFromFile=(ArrayList<Books>) bookObject.readObject();
            bookObject.close();
           booksArrayList= bookListFromFile;
    }
}