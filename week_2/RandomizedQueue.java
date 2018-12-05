import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.Object;
import java.lang.IllegalArgumentException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] s;
    private int n_array = 1;
    private int n_item = 0;

    private void upsize_array() {
        // TODO : Follow deque method
        n_array = n_array * 2;
        Item[] copy = (Item[]) new Object[n_array];
        for (int i = 0; i < n_item; i = i + 1){
            copy[i] = s[i];
        }
        s = copy;
    }

    private void downsize_array(){
        if (n_array == 1) return;
        else {
            n_array = n_array / 2;
            Item[] copy = (Item[]) new Object[n_array];
            for (int i = 0; i < n_item; i = i + 1) {
                copy[i] = s[i];
            }
            s = copy;
        }
    }

    private int get_last_pointer() {
        if (n_item == 0){
            return 0;
        } else {
            return n_item - 1;
        }
    }

    private class ArrayIterator implements Iterator<Item> {
        public boolean hasNext() {
            return isEmpty();
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()){
                throw new NoSuchElementException();
            } else {
                return dequeue();
            }
        }
    }

    public RandomizedQueue() {
        s = (Item[]) new Object[n_array];
    }                 // construct an empty randomized queue
    public boolean isEmpty() {
        return n_item == 0;
    }                // is the randomized queue empty?
    public int size() {
        return n_item;
    }                        // return the number of items on the randomized queue
    public void enqueue(Item item) {
        int last_pointer;

        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (n_item == n_array) {
            upsize_array();
        }
        n_item = n_item + 1;
        last_pointer = get_last_pointer();
        s[last_pointer] = item;
    }           // add the itemf
    public Item dequeue() {
        Item value;
        int random_pointer, last_pointer;

        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        last_pointer = get_last_pointer();
        if (last_pointer == 0) {
            random_pointer = 0;
        } else {
            random_pointer = StdRandom.uniform(last_pointer);
        }
        value = s[random_pointer];
        s[random_pointer] = s[last_pointer];
        s[last_pointer] = value;
        n_item = n_item - 1;
        if (n_item == n_array / 4) {
            downsize_array();
        }
        return value;
    }                   // remove and return a random item
    public Item sample() {
        Item value;
        int last_pointer, random_pointer;

        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        last_pointer = get_last_pointer();
        random_pointer = StdRandom.uniform(last_pointer);
        value = s[random_pointer];
        return value;
    }                     // return a random item (but do not remove it)
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }        // return an independent iterator over items in random order

    public static void main(String[] args) {
        return;
    }  // unit testing (optional)
}