package libProj;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class IssueBook implements Serializable {
    UUID bookId;
    UUID userId;
    LocalDate IssuedOn;
    IssueBook() {

    }

    IssueBook(UUID bookId, UUID userId) {
        this.bookId = bookId;
        this.userId = userId;
        this.IssuedOn=LocalDate.now();
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
            System.out.println("Book: "+bookInstance.bookId + "|" + bookInstance.bookName +" is issued By: "+userInstance.userId + "|" + userInstance.name+" was issued on: "+tempInstance.IssuedOn);
        }
    }

    public void returnIssuedBook(ArrayList<IssueBook> list, ArrayList<Books> booklist, ArrayList<User> userlist) {
        boolean isPresent=false;
        LocalDate dNow=null;
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
                         dNow=LocalDate.now();
                        int dateDiff=dNow.compareTo(ob.IssuedOn);
                        if(dateDiff>8){
                            System.out.println("Book is being returned later than 8 day mark.Fine is to be paid on basis 0.5Rs/day");
                            System.out.println(dateDiff*0.5f+"Rs");
                        }
                        list.remove(ob);                    //remove object in arraylist of issued books since book is not returned
                        break;
                    }
                }
                for(Books ob:booklist){                     //user found user associated book id to find book object in booklist
                    if(ob.bookId.equals(userAssociatedBookId)){
                        ob.issuedTo.remove(name);
                        int quantity=ob.getQuantity();
                        quantity++;
                        ob.setQuantity(quantity);
                        break;
                    }
                }
                System.out.println("Book has been successfully returned on "+dNow+" you can issue new book now");
            }else System.out.println("User has not issued a book");
        }else System.out.println("User is not present");

    }
}
