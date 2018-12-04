import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Item[] s;
    private int n_array = 1;
    private int n_item = 0;
    private int first_index = 0;
    private int last_index = 0;
    private int first_pointer = 0;
    private int last_pointer = 0;

    private void inc_first_pointer() {
        if (first_pointer == last_index) {
            first_pointer = first_index;
        } else {
            first_pointer = first_pointer + 1;
        }
    }

    private void inc_last_pointer() {
        if (last_pointer == last_index) {
            last_pointer = first_index;
        } else {
            first_pointer = first_pointer + 1;
        }
    }

    private void dec_first_pointer() {
        if (first_pointer == first_index) {
            first_pointer = last_index;
        } else {
            first_pointer = first_pointer - 1;
        }
    }

    private void dec_last_pointer() {
        if (last_pointer == first_index) {
            last_pointer = last_index;
        } else {
            last_pointer = last_pointer - 1;
        }
    }

    private void reset_first_pointer() {
        first_pointer = 0;
    }

    private void reset_last_pointer() {
        if (n_item == 0) {
            last_pointer = 0;
        } else {
            last_pointer = n_item - 1;
        }
    }

    private void upsize_array() {
        // TODO : resize should reset pointer
        n_array = n_array * 2;
        last_index = n_array - 1;
        Item[] copy = (Item[]) new Object[n_array];
        for (int i = 0; i < n_item; i = i + 1) {
            copy[i] = s[first_pointer];
            inc_first_pointer();
        }
        reset_first_pointer();
        reset_last_pointer();
        s = copy;
    }

    private void downsize_array() {
        // TODO : resize should reset pointer
        if (n_array == 1) return;
        else {
            n_array = n_array / 2;
            last_index = n_array - 1;
            Item[] copy = (Item[]) new Object[n_array];
            for (int i = 0; i < n_item; i = i + 1) {
                copy[i] = s[first_pointer];
                inc_first_pointer();
            }
            reset_first_pointer();
            reset_last_pointer();
            s = copy;
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
            return removeFirst();
        }
    }

    public Deque() {
        s = (Item[]) new Object[n_array];
    }                           // construct an empty deque

    public boolean isEmpty() {
        return n_array == 0;
    }                 // is the deque empty?

    public int size() {
        return n_array;
    }                        // return the number of items on the deque

    public void addFirst(Item item) {
        if (n_item == n_array) {
            upsize_array();
        }
        dec_first_pointer();
        s[first_pointer] = item;
        n_item = n_item + 1;
    }          // add the item to the front

    public void addLast(Item item) {
        if (n_item == n_array) {
            upsize_array();
        }
        inc_last_pointer();
        s[last_pointer] = item;
        n_item = n_item + 1;
    }   // add the item to the end

    public Item removeFirst() {
        Item value;
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        value = s[first_pointer];
        inc_first_pointer();
        n_item = n_item - 1;
        if (n_item == n_array / 4) {
            downsize_array();
        }
        return value;
    }               // remove and return the item from the front

    public Item removeLast() {
        Item value;
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        value = s[last_pointer];
        dec_last_pointer();
        n_item = n_item - 1;
        if (n_item == n_array / 4) {
            downsize_array();
        }
        return value;
    }                // remove and return the item from the end

    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }        // return an iterator over items in order from front to end

    public static void main(String[] args) {
        return;
    }   // unit testing (optional)
}
