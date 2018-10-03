package learning;

class ArrayCopyOfDemo {
    public static void main(String[] args) {

        char[] copyFrom = {'d', 'e', 'c', 'a', 'f', 'f', 'e',
                'i', 'n', 'a', 't', 'e', 'd'};

        char[] copyTo = java.util.Arrays.copyOf(copyFrom, 9);

        copyFrom[3] = 'd';

        System.out.println(new String(copyTo));
        System.out.println(new String(copyFrom));
    }
}