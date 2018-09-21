package virtualLibrary;

public interface BookManager {
    void borrowBook(Book book);
    void renewBook(Book book);
    void returnBook(Book book);
    void notifyBorrower(Book book);
    void reserveBook(Book book);
}
