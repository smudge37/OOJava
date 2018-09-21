package genericsLearning;

public class PlayAround {
    public static void main(String[] args) {
        String o1 = "Hello there.";

        Box o2 = new Box();

        if (!o1.equals(o2)) {
            System.out.println("It worked!");
        }
    }
}
