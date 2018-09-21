package virtualLibrary;

import java.util.ArrayList;

public class Searcher {
    public int findBookByField(ArrayList<Book> bookList, String field, String searchItem, int startIndex) {
        for (int i = startIndex; i < bookList.size(); i++) {
            if (searchItem == bookList.get(i).getProperty(field)) {
                return i;
            }
        }

        return -1;
    }

    public int findInArrayList(ArrayList<?> aList, <?> searchItem, startIndex i ){

    }
}
