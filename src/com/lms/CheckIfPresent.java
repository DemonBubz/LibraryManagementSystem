package com.lms;

import java.util.ArrayList;

interface CheckIfPresent{
    default boolean isPresentBook(String bookName, ArrayList<Books> booksArrayList){
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
    default boolean isPresentWriter(String writerName,ArrayList<Books>booksArrayList){    //Overloading in case user remembers both book name and writer name
        boolean isPresent=false;
        for(int i=0;i<booksArrayList.size();i++){
            Books tempInstance=booksArrayList.get(i);
            if( writerName.equals(tempInstance.writerName)) {
                isPresent = true;
                break;
            }
        }
        return isPresent;
    }

}