package libProj;
import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;


class Mechanism{
     Scanner scanner=new Scanner(System.in);
     ArrayList<IssueBook>issueBooks=new ArrayList<>();
     Books myBooksOb=new Books();
     User userOb=new User();

     public void userMenu() throws IOException,ClassNotFoundException {
        System.out.println("1.Add user");
        System.out.println("2.Display Existing users");
        System.out.println("3.Change password");
        System.out.println("4.Display specific user");
        System.out.println("5.Issue book");
        System.out.println("6.display users with books");
        System.out.println("7.return issued book");
         System.out.println("Enter choice");
         int u=scanner.nextInt();
         switch(u){
             case 1->{
                 System.out.println("Enter number of users to be added");
                 int n=scanner.nextInt();
                 while(n>0){
                     userOb.addUser();
                     n--;
                 }
                 System.out.println("Would you like to exit y/n");
                 if(scanner.next().equals("n"))
                     displayMainMenu();
             }
             case 2->{
                 userOb.displayUsers();
                 System.out.println("Would you like to exit y/n");
                 if(scanner.next().equals("n"))
                     displayMainMenu();
             }
             case 3->{
                 User user=userOb.returnIfFound();
                 scanner.nextLine();
                 System.out.println("Enter password");
                 user.setPassword(scanner.nextLine());
                 userOb.serializeUser();
                 System.out.println("New Password set");
                 System.out.println("Would you like to exit y/n");
                 if(scanner.next().equals("n"))
                     displayMainMenu();
             }
             case 4->{
                 User user=userOb.returnIfFound();
//                 scanner.nextLine();
                 System.out.println(user.userId+" "+user.name+" "+user.role+" "+user.haveIssued);
                 System.out.println("Would you like to exit y/n");
                 if(scanner.next().equals("n"))
                     displayMainMenu();
             }
             case 5->{
                 User user=userOb.returnIfFound();
                 if(user.haveIssued){
                     System.out.println("Return the issued book first");
                 }else {
                     Books book=myBooksOb.returnThisBook();
                     if(book.getQuantity()>0){
                         issueBooks.add(new IssueBook(book.getBookId(),user.getUserId()));
                         user.haveIssued=true;
                         book.issuedTo.add(user.name);
                         int quantity=book.getQuantity();
                         quantity--;
                         book.setQuantity(quantity);
                         serializeIssue();
                         userOb.serializeUser();
                         myBooksOb.serializeBook();
                         System.out.println(book.getBookName()+" has been issued to "+user.getName()+" on "+LocalDate.now());
                     }else System.out.println("Not Available");
                 } System.out.println("Would you like to exit y/n");
                 if(scanner.next().equals("n"))
                     displayMainMenu();
             }
             case 6-> {
                 new IssueBook().displayUsersWithBooks(issueBooks, myBooksOb.booksArrayList, userOb.userArrayList);
                 System.out.println("Would you like to exit y/n");
                 if (scanner.next().equals("n"))
                     displayMainMenu();
             }
            case 7->{
                System.out.println("Initiating procedure to return the book");
                 new IssueBook().returnIssuedBook(issueBooks,myBooksOb.booksArrayList,userOb.userArrayList);
                 myBooksOb.serializeBook();;
                 userOb.serializeUser();
                 serializeIssue();
                System.out.println("Would you like to exit y/n");
                if (scanner.next().equals("n"))
                    displayMainMenu();
            }
         }
    }
    public void bookMenu()  throws IOException,ClassNotFoundException {
        System.out.println("1.Add Books");
        System.out.println("2.Delete Books");
        System.out.println("3.Display all Books");
        System.out.println("4.Display Specific Book");
        int bookMenuChoice=scanner.nextInt();
        switch (bookMenuChoice){
            case 1->{
                System.out.print("Enter number of books needed: ");
                Scanner sc=new Scanner(System.in);
                int n=sc.nextInt();
                while(n>0){
                    myBooksOb.addBookElements();
                    n--;
                }
                System.out.println("Would you like to exit y/n");
                if(scanner.next().equals("n"))
                    displayMainMenu();
            }
            case 2->{
                if(myBooksOb.checkBeforeDel().equalsIgnoreCase("n"))
                    displayMainMenu();
            }
            case 3->{
                System.out.println("displaying elements");
                myBooksOb.displayBookList();
                System.out.println("Would you like to exit y/n");
                if(scanner.next().equals("n"))
                    displayMainMenu();
            }
            case 4->{
                System.out.println("Specific book information");
                scanner.nextLine();
                Books book=myBooksOb.returnThisBook();
                System.out.println(book.bookId + " " + book.price + " " + book.quantity + " " + book.bookName + " " + book.writerName);
                System.out.println("Do you want to exit the program y/n");
                if(scanner.next().equals("n"))
                    displayMainMenu();

            }
        }
    }
    public void displayMainMenu() throws IOException,ClassNotFoundException {

        System.out.println("Welcome to library management system");
        System.out.println("choose one of the given options by pressing respective numeric key");
        System.out.println("1.Books");
        System.out.println("2.Users");
        int c1=scanner.nextInt();
        switch(c1){
            case 1->{
                System.out.println("Books");
                bookMenu();
            }
            case 2->{
                System.out.println("user");
                userMenu();

            }
        }
    }

    public void serializeIssue() throws IOException{
            FileOutputStream file = new FileOutputStream("IssueBook.ser");
            ObjectOutputStream bookObject=new ObjectOutputStream(file);
                bookObject.writeObject(issueBooks);
            bookObject.close();
            file.close();
    }
    public void deserializeIssue() throws IOException,ClassNotFoundException {
            FileInputStream file = new FileInputStream("IssueBook.ser");
            ObjectInputStream bookObject=new ObjectInputStream(file);
            ArrayList<IssueBook> issueBookDetailsFile=(ArrayList<IssueBook>) bookObject.readObject();
            bookObject.close();
           issueBooks= issueBookDetailsFile;
    }
    /*
    Login session implementation

     */


    public  void loginSession() throws IOException,ClassNotFoundException {
        System.out.println("Welcome to Library Management System");
        System.out.println("1.Existing admin login");
        System.out.println("2.Create new admin");
        int c=scanner.nextInt();
        switch(c){
            case 1->{
                System.out.println("checking database for existing admins..");
                if(userOb.userArrayList.isEmpty()) {
                    System.out.println("No admins found....");
                    userOb.createAdmin();
                    loginSession();
                }else {
                    System.out.println("Login");
                    System.out.print("Enter Name: ");
                    scanner.nextLine();
                    boolean userLogged=userOb.isAdminAndCheckPass(scanner.nextLine());
                    if(userLogged)
                        {
                            displayMainMenu();

                        }
                }
            }

            case 2->{
                userOb.createAdmin();
                loginSession();
            }
        }

    }
    public void dataFetch() throws IOException,ClassNotFoundException {
       try {
           myBooksOb.deserializeBook();
           userOb.deserializeUser();
           deserializeIssue();
       }catch(Exception e){
           System.out.println(e);
       }
        loginSession();
    }


}
public class Library{
    public static void main(String[] args) {
        try {
            new Mechanism().dataFetch();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
