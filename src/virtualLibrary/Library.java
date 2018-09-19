package virtualLibrary;

import java.util.ArrayList;

public class Library {
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Author> authors = new ArrayList<>();
    private ArrayList<String> genres = new ArrayList<>();

    public String[] getBooks(){
        String[] bookArray = new String[books.size()];
        for (int i = 0; i < books.size(); i++ ){
            bookArray[i] = books.get(i).toString();
        }
        return bookArray;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    private void updateGenres(String[] newGenres) {
        for (int i = 0; i < newGenres.length; i++) {
            newGenres[i] = newGenres[i].toLowerCase().trimx
        }
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }
}
