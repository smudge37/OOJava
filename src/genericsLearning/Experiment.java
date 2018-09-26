package genericsLearning;

public class Experiment<T> {
    public Box<T> experiment(){
        return new Box<>();
    }
}
