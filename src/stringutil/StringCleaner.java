package stringutil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCleaner {
    public String cleanNonLetters(String str) {
        String regex = "\^a-z";
        str.replaceAll(regex, "");
    }
}
