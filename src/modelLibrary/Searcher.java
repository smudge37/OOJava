package modelLibrary;

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

    public int findInArrayList(ArrayList aList, Object searchItem, int startIndex ){
            for (int i = startIndex; i < aList.size(); i++) {
                if (aList.get(i).equals(searchItem)) {
                    return i;
                }
            }
        return -1;
    }
}
