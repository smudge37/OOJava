package virtualLibrary;


public class Book {
    private int price;
    private String title;
    private String author;
    private String genre;
    private long numberReads;
    private int dateReleased;
    private boolean isBorrowed;

    public void setProperty(String property, String value) {
        property = property.toLowerCase();
        try {
            if (property.equals("price")) {
                this.getClass().getField(property).set(this, Integer.parseInt(value));
            } else {
                this.getClass().getField(property).set(this, value);
            }
        } catch (NoSuchFieldException e) {
            System.out.println("No such property.");
        } catch (IllegalAccessException e) {
            System.out.println("Problem accessing property.");
        }
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

    public boolean isBorrowed() {
        return this.isBorrowed;
    }
}
