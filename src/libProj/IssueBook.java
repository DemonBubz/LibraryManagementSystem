package libProj;

import java.util.ArrayList;

public class IssueBook {
    int bookId;
    int userId;

    IssueBook() {

    }

    IssueBook(int bookId, int userId) {
        this.bookId = bookId;
        this.userId = userId;
    }

    public Books returnBookFromId(int id, ArrayList<Books> booklist) {
        Books ret = new Books();
        for (int i = 0; i < booklist.size(); i++) {
            Books local = booklist.get(i);
            if (local.getBookId() == id) {
                ret = local;
                return ret;
            }
        }
        return ret;
    }

    public User returnUserFromId(int id, ArrayList<User> userlist) {
        User ret = new User();
        for (int i = 0; i < userlist.size(); i++) {
            User local = userlist.get(i);
            if (local.getUserId() == id) {
                ret = local;
                return ret;
            }
        }
        return ret;
    }

    public void displayUsersWithBooks(ArrayList<IssueBook>list,ArrayList<Books>booklist,ArrayList<User>userlist){
        for(int i=0;i<list.size();i++){
            IssueBook tempInstance= list.get(i);
            Books bookInstance=returnBookFromId(tempInstance.bookId,booklist);
            User userInstance=returnUserFromId(tempInstance.userId,userlist);
            System.out.println(bookInstance.bookId + " " + bookInstance.price + " " + bookInstance.quantity + " " + bookInstance.bookName + " " + bookInstance.writerName);
            System.out.println(userInstance.userId+" "+userInstance.name+" "+userInstance.role+" "+userInstance.haveIssued);
        }
    }
}

