package virtualLibrary;

import org.jetbrains.annotations.NotNull;
import virtualLibrary.LibraryExceptions.DoubleReservationException;

import java.util.ArrayList;
import java.util.Date;


public class LibraryDatabase {
    // Only LibraryDatabase or higher level classes can print to console. ?

    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Author> authors = new ArrayList<>();
    private ArrayList<String> genres = new ArrayList<>();
    private ArrayList<Integer> availableRefNums = new ArrayList<>();
    private int reserveLimit;

    private void addGenres(Searcher searcher, @NotNull String[] newGenres) {
        for (int i = 0; i < newGenres.length; i++) {
            newGenres[i] = newGenres[i].toLowerCase().replaceAll("[^a-z]","");
            if ( -1 == searcher.findInArrayList(genres, newGenres[i], 0) ) {
                genres.add(newGenres[i]);
            }
        }
    }

    public void addBook(String title, String author, String genre) {

        int refNumber;

        if (availableRefNums.size() > 0) {
            refNumber = availableRefNums.get(0);
            availableRefNums.remove(0);
        } else {
            refNumber = books.size() + 1;
        }

        Book book = new Book(title, author, genre, refNumber);

        books.add(book);
    }

    public void removeBook(Searcher searcher, int refNumber) {
        availableRefNums.add(refNumber);
        books.remove(searcher.findBookByField(books, "refNumber", Integer.toString(refNumber), 0));
    }

    public void addAuthor(String name) {
        authors.add(new Author(name));
    }

    public Book[] getBookArray(){
        Book[] bookArray = new Book[books.size()];
        for (int i = 0; i < books.size(); i++ ){
            bookArray[i] = books.get(i);
        }

        return bookArray;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public ArrayList<Author> getAuthors() {
        return authors;
    }

    public Book getBook(Searcher searcher, String title, String author) {

        int startIndex = 0;
        Book book;
        do {
            int index = searcher.findBookByField(books, "title", title, startIndex);
            if (index == -1) {
                System.out.println("Unfortunately the book you are looking for is not in the library.");
                return null;
            }
            book = books.get(index);
            startIndex = index + 1;
        } while (!book.getProperty("author").equals(author));

        return book;

//        if (book.isBorrowed()) {
//            System.out.println("Unfortunately this book is currently being borrowed. " +
//                    "Would you like to reserve it? You will be notified when it is returned.");
//        }
    }

    /** BookManager methods **/

    void borrowBook(Book book, Member member) {
        if (book.isBorrowed()) {
            System.out.println("Unfortunately this book is currently borrowed. Would you like to reserve it?");
            // Allow reservation of book
        } else{
            if (book.isReserved() && !book.getHolder().equals(member)) {
                System.out.println("Unfortunately this book has been reserved by someone else.");
            } else {
                book.borrow(member, new Date());
                member.borrowBook(book);
            }
        }
    }

    void renewBook(Book book){
        if (book.isBorrowed()) {
            if (book.isReserved()) {
                System.out.println("Unfortunately for you, this book has been reserved by someone else. You may not renew it.");
            } else {
                book.renew(new Date());
            }

        } else {
            System.out.println("It appears that this book has not been borrowed. Would you like to borrow it?");
            // Allow borrowing of book.
        }
    }

    void returnBook(Book book) {
        Member member = book.getHolder();
        book.replace();
        member.returnBook(book);
    }

    void reserveBook(Book book, Member member) {
        if (member.getBooksReserved().size() >= reserveLimit) {
            System.out.println("Sorry, you have reserved " + reserveLimit + " books. You cannot reserve any more.");
        } else {
            try {
                book.reserve(member);
            } catch(DoubleReservationException message) {
                System.out.println(message);
            }
        }
    }
}
