package learning;

public class Box <T> {
    private T object;

    public void set(T obj){
        this.object = obj;
    }

    public Object get() {
        return this.object;
    }
}
