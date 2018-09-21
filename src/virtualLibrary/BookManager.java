package virtualLibrary;

public interface BookManager {
    void borrowBook();
    void renewBook();
    void returnBook();
    void notifyBorrower();
}
