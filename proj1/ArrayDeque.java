/**
 * Created by jifeiqian on 12/3/16.
 */
public class ArrayDeque<Item> {
    private Item[] items;
    private int size;
    private int front;
    private int end;

    public ArrayDeque() {
        items = (Item[]) new Object[8];
        size = 0;
        front = 0;
        end = 0;
    }

    public void addFirst(Item item) {
        items[front] = item;
        front = front == 0 ? items.length - 1 : front - 1;
        size++;
        resize();
    }

    public void addLast(Item item) {
        end = end + 1 == items.length ? 0 : end + 1;
        items[end] = item;
        size++;
        resize();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            int index = (front + 1 + i) % items.length;
            System.out.println(items[index].toString());
        }
    }

    public Item removeFirst() {
        if (isEmpty()) {
            return null;
        }
        front = front + 1 == items.length ? 0 : front + 1;
        Item res = items[front];
        items[front] = null;
        size--;
        resize();
        return res;
    }

    public Item removeLast() {
        if (isEmpty()) {
            return null;
        }
        Item res = items[end];
        items[end] = null;
        end = end == 0 ? items.length - 1 : end - 1;
        size--;
        resize();
        return res;
    }

    public Item get(int index) {
        if (isEmpty() || index >= size) {
            return null;
        }
        int idx = (front + 1 + index) % items.length;
        return items[idx];
    }

    private void resize() {
        if (size == items.length) {
            Item[] a = (Item[]) new Object[items.length * 2];
            for (int i = 0; i < size; i++) {
                int index = (front + 1 + i) % items.length;
                a[front + 1 + i] = items[index];
            }
            items = a;
            end = front + size;
        } else if (size < items.length / 4 && items.length >= 16) {
            Item[] a = (Item[]) new Object[items.length / 2];
            for (int i = 0; i < size; i++) {
                int index = (front + 1 + i) % a.length;
                a[index] = items[front + 1 + i];
            }
            items = a;
            end = (front + size) % a.length;
        }
    }
}

