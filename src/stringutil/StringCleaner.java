package stringutil;


public class StringCleaner {
    public String cleanNonLetters(String str) {
        String regex = "[^a-z]";
        return str.replaceAll(regex, "");
    }
}