package learning;


public class PlayAround {
    public static void main(String[] args) {
        Experiment ex = new Experiment();
        Box<Double> intBox = ex.experiment();
        double num = Math.random();
        intBox.set(num);
        System.out.println(intBox.get());
    }

}
