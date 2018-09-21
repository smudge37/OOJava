package virtualLibrary;

import java.util.ArrayList;

public class LibraryDatabase {
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Author> authors = new ArrayList<>();
    private ArrayList<String> genres = new ArrayList<>();

    private void addGenres(Searcher searcher, String[] newGenres) {
        for (int i = 0; i < newGenres.length; i++) {
            newGenres[i] = newGenres[i].toLowerCase().replaceAll("[^a-z]","");
            if ( -1 == searcher.findInArrayList(genres, newGenres[i], 0) ) {
                genres.add(newGenres[i]);
            };
        }
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }

    public Book[] getBookArray(){
        Book[] bookArray = new Book[books.size()];
        for (int i = 0; i < books.size(); i++ ){
            bookArray[i] = books.get(i);
        }

        return bookArray;
    }

    public Book getBook(Searcher searcher, String title, String author) {

        int index = 0;
        Book book;
        do {
            index = searcher.findBookByField(books, "title", title, index);
            if (index == -1) {
                System.out.println("Unfortunately the book you are looking for is not in the library.");
                return null;
            }
            book = books.get(index);
        } while (!book.getProperty("author").equals(author));

        return book;

//        if (book.isBorrowed()) {
//            System.out.println("Unfortunately this book is currently being borrowed. " +
//                    "Would you like to reserve it? You will be notified when it is returned.");
//        }
    }
}
