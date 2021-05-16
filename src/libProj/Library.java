package libProj;
import java.util.Scanner;
import java.util.ArrayList;


class Mechanism{
     Scanner scanner=new Scanner(System.in);
     ArrayList<IssueBook>issueBooks=new ArrayList<>();
     Books myBooksOb=new Books();
     User userOb=new User();
     public void userMenu(){
        System.out.println("1.Add user");
        System.out.println("2.Display Existing users");
        System.out.println("3.Change password");
        System.out.println("4.Display specific user");
        System.out.println("5.Issue book");
        System.out.println("6.display users with books");
    }
    public void bookMenu(){
        System.out.println("1.Add Books");
        System.out.println("2.Delete Books");
        System.out.println("3.Display all Books");
        System.out.println("4.Display Specific Book");
    }
    public void displayMainMenu(){
        System.out.println("Welcome to library management system");
        System.out.println("choose one of the given options by pressing respective numeric key");
        System.out.println("1.Books");
        System.out.println("2.Issuers");
    }


    /*
    Login session implementation

     */


    public  void loginSession(){
        System.out.println("Welcome to Library Management System");
        System.out.println("1.Existing admin login");
        System.out.println("2.Create new admin");
        int c=scanner.nextInt();
        switch(c){
            case 1->{
                System.out.println("checking database for existing admins..");
                if(userOb.userArrayList.isEmpty()) {
                    System.out.println("No admins found");
                    userOb.createAdmin();
                    loginSession();
                }else {
                    System.out.println("Login");
                    System.out.println("Enter Name: ");
                    scanner.nextLine();
                    boolean userLogged=userOb.isAdminAndCheckPass(scanner.nextLine());
                    if(userLogged)
                        {
                            displayMainMenu();
                            int c1=scanner.nextInt();
                            switch(c1){
                                case 1->{
                                    System.out.println("Books");
                                    bookMenu();
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
                                                loginSession();
                                        }
                                        case 2->{
                                            if(myBooksOb.checkBeforeDel().equalsIgnoreCase("n"))
                                                loginSession();
                                        }
                                        case 3->{
                                            System.out.println("displaying elements");
                                            myBooksOb.displayBookList();
                                            System.out.println("Would you like to exit y/n");
                                            if(scanner.next().equals("n"))
                                                loginSession();
                                        }
                                        case 4->{
                                            System.out.println("Specific book information");
                                            Books book=myBooksOb.returnThisBook();
                                            System.out.println(book.bookId + " " + book.price + " " + book.quantity + " " + book.bookName + " " + book.writerName);
                                            System.out.println("Do you want to exit the program y/n");
                                            if(scanner.next().equals("n"))
                                                loginSession();
                                        }
                                    }

                                }
                                case 2->{
                                    System.out.println("user");
                                    userMenu();
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
                                                loginSession();
                                        }
                                        case 2->{
                                            userOb.displayUsers();
                                            System.out.println("Would you like to exit y/n");
                                            if(scanner.next().equals("n"))
                                                loginSession();
                                        }
                                        case 3->{
                                            User user=userOb.returnIfFound();
                                            scanner.nextLine();
                                            System.out.println("Enter password");
                                            user.setPassword(scanner.nextLine());
                                            System.out.println("New Password set");
                                            System.out.println("Would you like to exit y/n");
                                            if(scanner.next().equals("n"))
                                                loginSession();
                                        }
                                        case 4->{
                                            User user=userOb.returnIfFound();
                                            scanner.nextLine();
                                            System.out.println(user.userId+" "+user.name+" "+user.role+" "+user.haveIssued);
                                            System.out.println("Would you like to exit y/n");
                                            if(scanner.next().equals("n"))
                                                loginSession();
                                        }
                                        case 5->{
                                            User user=userOb.returnIfFound();
                                            if(user.haveIssued){
                                                System.out.println("Return the issued book first");
                                            }else {
                                                Books book=myBooksOb.returnThisBook();
                                                if(book.getQuantity()>0){
                                                    issueBooks.add(new IssueBook(user.getUserId(),book.getBookId()));
                                                    user.haveIssued=true;
                                                    int quantity=book.getQuantity();
                                                    quantity--;
                                                    book.setQuantity(quantity);
                                                }else System.out.println("Not Available");
                                            } System.out.println("Would you like to exit y/n");
                                            if(scanner.next().equals("n"))
                                                loginSession();
                                        }
                                        case 6-> {
                                            new IssueBook().displayUsersWithBooks(issueBooks, myBooksOb.booksArrayList, userOb.userArrayList);
                                            System.out.println("Would you like to exit y/n");
                                            if (scanner.next().equals("n"))
                                                loginSession();
                                        }

                                    }
                                }
                            }
                        }

                }
            }

            case 2->{
                userOb.createAdmin();
                loginSession();
            }
        }

    }

}
public class Library{

    public static void main(String[] args) {

    new Mechanism().loginSession();
    }
}
