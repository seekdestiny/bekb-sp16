public class LinkedListDeque<Item> {
    static class Node<Item> {
        Item item;
        Node<Item> prev;
        Node<Item> next;

        Node(Item item) {
            this.item = item;
        }
    }

    private Node<Item> head;
    private Node<Item> tail;
    private int size;

    public LinkedListDeque() {
        head = new Node<Item>(null);
        tail = new Node<Item>(null);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    public void addFirst(Item item) {
        Node<Item> newNode = new Node<Item>(item);
        newNode.next = head.next;
        head.next = newNode;
        newNode.next.prev = newNode;
        newNode.prev = head;
        size++;
    }

    public void addLast(Item item) {
        Node<Item> newNode = new Node<Item>(item);
        newNode.prev = tail.prev;
        tail.prev = newNode;
        newNode.prev.next = newNode;
        newNode.next = tail;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node<Item> track = head.next;
        while (track != tail) {
            System.out.println(track.item.toString());
            track = track.next;
        }
    }

    public Item removeFirst() {
        if (isEmpty()) {
            return null;
        }

        Node<Item> target = head.next;
        target.next.prev = head;
        head.next = target.next;
        target.prev = null;
        target.next = null;
        size--;
        return target.item;
    }

    public Item removeLast() {
        if (isEmpty()) {
            return null;
        }

        Node<Item> target = tail.prev;
        target.prev.next = tail;
        tail.prev = target.prev;
        target.prev = null;
        target.next = null;
        size--;
        return target.item;
    }

    public Item get(int index) {
        if (isEmpty()) {
            return null;
        }

        Node<Item> track = head.next;
        while (index-- > 0) {
            track = track.next;
        }

        return track.item;
    }

    public Item getRecursive(int index) {
        if (isEmpty() || index >= size) {
            return null;
        }

        return getHelper(head.next, index);
    }

    private Item getHelper(Node<Item> cur, int index) {
        if (index == 0) {
            return cur.item;
        }
        return getHelper(cur.next, index - 1);
    }
}
