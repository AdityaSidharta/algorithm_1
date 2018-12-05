import java.util.Arrays;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        String tokens, value;
        int k = Integer.parseInt(args[0]);
        int len = 0;
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()){
            tokens = StdIn.readString();
            queue.enqueue(tokens);
            len = len + 1;
        }
        if (k > len) {
            k = len;
        }
        for (int i = 0; i < k; i = i + 1) {
            value = queue.dequeue();
            StdOut.println(value);
        }
        return;
    }
}
