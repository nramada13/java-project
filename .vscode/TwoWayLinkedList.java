import java.util.ListIterator;
import java.util.NoSuchElementException;

public class TwoWayLinkedList<E> {
    private Node<E> head, tail;
    private int size = 0;

    /** Inner class: Doubly Linked List Node */
    private static class Node<E> {
        E element;
        Node<E> next;
        Node<E> previous;

        public Node(E e) {
            element = e;
            next = previous = null;
        }
    }

    /** Return the size of the list */
    public int size() {
        return size;
    }

    /** Add an element at the end of the list */
    public void add(E e) {
        Node<E> newNode = new Node<>(e);
        if (tail == null) { // Empty list
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
        }
        size++;
    }

    /** Get an element at a specified index */
    public E get(int index) {
        checkIndex(index);
        Node<E> current = getNode(index);
        return current.element;
    }

    /** Remove an element at a specified index */
    public E remove(int index) {
        checkIndex(index);
        Node<E> current = getNode(index);
        
        if (current == head) {
            head = head.next;
            if (head != null) head.previous = null;
        } else if (current == tail) {
            tail = tail.previous;
            tail.next = null;
        } else {
            current.previous.next = current.next;
            current.next.previous = current.previous;
        }

        size--;
        return current.element;
    }

    /** Get node at a given index */
    private Node<E> getNode(int index) {
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    /** Ensure index is valid */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range: " + index);
        }
    }

    /** Return a list iterator starting at index 0 */
    public ListIterator<E> listIterator() {
        return new TwoWayIterator(0);
    }

    /** Return a list iterator starting at a given index */
    public ListIterator<E> listIterator(int index) {
        return new TwoWayIterator(index);
    }

    /** Inner class: ListIterator implementation */
    private class TwoWayIterator implements ListIterator<E> {
        private Node<E> current;
        private int index;

        public TwoWayIterator(int index) {
            checkIndex(index);
            this.index = index;
            current = (index == size) ? null : getNode(index);
        }

        public boolean hasNext() {
            return current != null;
        }

        public E next() {
            if (!hasNext()) throw new NoSuchElementException();
            E data = current.element;
            current = current.next;
            index++;
            return data;
        }

        public boolean hasPrevious() {
            return current != null && current.previous != null;
        }

        public E previous() {
            if (!hasPrevious()) throw new NoSuchElementException();
            current = current.previous;
            index--;
            return current.element;
        }

        public int nextIndex() {
            return index;
        }

        public int previousIndex() {
            return index - 1;
        }

        public void remove() {
            throw new UnsupportedOperationException("remove() not supported.");
        }

        public void add(E e) {
            throw new UnsupportedOperationException("add() not supported.");
        }

        public void set(E e) {
            if (current == null) throw new IllegalStateException();
            current.element = e;
        }
    }

    /** Display the list elements */
    public void printList() {
        Node<E> current = head;
        while (current != null) {
            System.out.print(current.element + " ");
            current = current.next;
        }
        System.out.println();
    }

    /** Main method to test the TwoWayLinkedList */
    public static void main(String[] args) {
        TwoWayLinkedList<Integer> list = new TwoWayLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        System.out.println("Original List:");
        list.printList();

        System.out.println("\nUsing ListIterator:");
        ListIterator<Integer> iter = list.listIterator();
        while (iter.hasNext()) {
            System.out.print(iter.next() + " ");
        }

        System.out.println("\nReversing Using ListIterator:");
        while (iter.hasPrevious()) {
            System.out.print(iter.previous() + " ");
        }
    }
}

C:\Users\noorr>cd C:\book\

C:\Book> javac TwoWayLinkedList.java

C:\Book> java TwoWayLinkedList
Original List:
1 2 3 4 5

Using ListIterator:
1 2 3 4 5
Reversing Using ListIterator:
5 4 3 2 1
