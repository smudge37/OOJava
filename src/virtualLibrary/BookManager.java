package virtualLibrary;

public interface BookManager {
    void borrowBook(Book book, Member member);
    void renewBook(Book book);
    void returnBook(Book book);
    void reserveBook(Book book);
//    void notifyBorrower(Book book);
}
