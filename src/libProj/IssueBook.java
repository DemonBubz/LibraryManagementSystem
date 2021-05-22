package libProj;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class IssueBook implements Serializable {
    UUID bookId;
    UUID userId;

    IssueBook() {

    }

    IssueBook(UUID bookId, UUID userId) {
        this.bookId = bookId;
        this.userId = userId;
    }

    public Books returnBookFromId(UUID id, ArrayList<Books> booklist) {
        Books ret = new Books();
        for (int i = 0; i < booklist.size(); i++) {
            Books local = booklist.get(i);
            if (id.equals(local.bookId)) {
                return local;
            }
        }
        return ret;
    }

    public User returnUserFromId(UUID id, ArrayList<User> userlist) {
        User ret = new User();
        for (int i = 0; i < userlist.size(); i++) {
            User local = userlist.get(i);
            if ((id.toString()).equals((local.userId).toString())) {
                System.out.println("Getting in for debug");
                return local;
            }
        }
        return ret;
    }

    public void displayUsersWithBooks(ArrayList<IssueBook> list, ArrayList<Books> booklist, ArrayList<User> userlist) {
        for (int i = 0; i < list.size(); i++) {
            IssueBook tempInstance = list.get(i);
            Books bookInstance = returnBookFromId(tempInstance.bookId, booklist);
            User userInstance = returnUserFromId(tempInstance.userId, userlist);
            System.out.println(bookInstance.bookId + " " + bookInstance.price + " " + bookInstance.quantity + " " + bookInstance.bookName + " " + bookInstance.writerName);
            System.out.println(userInstance.userId + " " + userInstance.name + " " + userInstance.role + " " + "Book issued");
        }
    }

    public void returnIssuedBook(ArrayList<IssueBook> list, ArrayList<Books> booklist, ArrayList<User> userlist) {
        boolean isPresent=false;
        User foundUser=new User();
        UUID userAssociatedBookId = null;
        System.out.println("Enter name ");//take name
        Scanner scanner=new Scanner(System.in);
        String name=scanner.nextLine();
        for(User ob:userlist){
            if(ob.getName().equalsIgnoreCase(name)){//find user object associated with name
                isPresent=true;
                foundUser=ob;                       //store user object if found
                break;
            }else System.out.println("User Not found");
        }
        if(isPresent){
            if(foundUser.haveIssued){                   //check if stored user object has issued book
                foundUser.haveIssued=false;             //return book
                for(IssueBook ob:list){
                    if(ob.userId.equals(foundUser.userId)){//find object in arraylist of issued books using stored user
                        userAssociatedBookId=ob.bookId;    // object to find associated book id in order to increase book quantity
                        list.remove(ob);                    //remove object in arraylist of issued books since book is not returned
                        break;
                    }
                }
                for(Books ob:booklist){                     //user found user associated book id to find book object in booklist
                    if(ob.bookId.equals(userAssociatedBookId)){
                        int quantity=ob.getQuantity();
                        quantity++;
                        ob.setQuantity(quantity);
                        break;
                    }
                }
                System.out.println("Book has been successfully returned you can issue new book now");
            }else System.out.println("User has not issued a book");
        }else System.out.println("User is not present");

    }
}
