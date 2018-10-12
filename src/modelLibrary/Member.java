package modelLibrary;

import java.util.ArrayList;

public class Member {
    private String name;
    private int refNum;
    private ArrayList<Book> booksHeld;
    private ArrayList<Book> booksReserved;

    public Member(String name, int refNum) {
        this.name = name;
        this.refNum = refNum;
        this.booksHeld = new ArrayList<>();
        this.booksReserved = new ArrayList<>();
    }

    public Object getProperty(String property) {
        try {
            return this.getClass().getField(property).get(this);
        } catch (NoSuchFieldException e){
            System.out.println("No such property.");
            return null;
        } catch (IllegalAccessException e) {
            return null;
        }
    }

    public int getRefNum() {
        return this.refNum;
    }

    public ArrayList<Book> getBooksReserved() {
        return this.booksReserved;
    }

    public void reserveBook(Book book) {
        this.booksReserved.add(book);
    }

    public void borrowBook(Book book) {
        this.booksReserved.remove(book);
        this.booksHeld.add(book);
    }

    public void returnBook(Book book) {
        this.booksHeld.remove(book);
    }
}
