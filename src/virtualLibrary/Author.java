package virtualLibrary;

import java.util.ArrayList;

public class Author {
    private String name;
    private long numberReads;
    private ArrayList<String> books;
    private ArrayList<String> genres;

    public Author(String authorName) {
        name = authorName;
    }

    public ArrayList<Book> getBooks(LibraryDatabase libDB, Searcher searcher) {
        ArrayList<Book> allBooks = libDB.getBooks();
        int numAuthors = libDB.getAuthors().size();

        ArrayList<Book> authorBooks = new ArrayList<>();
        authorBooks.ensureCapacity(allBooks.size()/numAuthors);
        int startIndex = 0;
        do {
            int newIndex = searcher.findBookByField(allBooks, "author", this.name, startIndex);
            authorBooks.add(allBooks.get(newIndex));
            startIndex = newIndex + 1;
        } while (startIndex != -1);

        return authorBooks;
    }
}
