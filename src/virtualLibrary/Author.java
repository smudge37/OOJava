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

}
