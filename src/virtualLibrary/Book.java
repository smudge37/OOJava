package virtualLibrary;


public class Book {
    private int price;
    private String title;
    private String author;
    private String genre;
    private long numberReads;
    private int dateReleased;
    private boolean isBorrowed;

    public Object getProperty(String field) {
        try {
            return this.getClass().getField(field).get(this);
        } catch (NoSuchFieldException e){
            System.out.println("No such field");
            return null;
        } catch (IllegalAccessException e) {
            return null;
        }
    }

    public boolean isBorrowed() {
        return this.isBorrowed;
    }
}
