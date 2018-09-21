package stringutil;


public class StringCleaner {
    public String cleanNonLetters(String str) {
        String regex = "[^a-z]";
        str.replaceAll(regex, "");
    }
}
