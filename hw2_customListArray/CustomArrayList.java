package hw2_customListArray;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;

public class CustomArrayList<E> implements CustomList<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size = 0;

    public CustomArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    public CustomArrayList(int initialCapacity) {
        elements = new Object[initialCapacity];
    }

    private void increaseCapacity() {
        long longCapacity = (long) elements.length * 3 / 2 + 1;

        if (longCapacity > Integer.MAX_VALUE) {
            throw new OutOfMemoryError("Required array size too large");
        }

        int newCapacity = (int) longCapacity;
        elements = Arrays.copyOf(elements, newCapacity);
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            increaseCapacity();
        }
    }

    @Override
    public void add(E element) {
        ensureCapacity();
        elements[size] = element;
        size++;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        } else if (index == size) {
            add(element);
        } else {
            ensureCapacity();
            System.arraycopy(elements, index, elements, index + 1, size - index);
            elements[index] = element;
            size++;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public E get(int index) {
        validateIndexForGetRemove(index);
        return (E) elements[index];
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E e : c) {
            add(e);
        }
        return true;
    }

    @Override
    public void clear() {
        if (size > 0) {
            Arrays.fill(elements, 0, size, null);
            size = 0;
        }
    }

    @Override
    public void remove(int index) {
        rangeCheck(index);

        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[--size] = null;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index != -1) {
            remove(index);
            return true;
        }
        return false;
    }

    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elements[i], o)) {
                return i;
            }
        }
        return -1;
    }

    private void rangeCheck(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
    }

    private void validateIndexForGetRemove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
    }

    private void quicksort(int low, int high, Comparator<? super E> c) {
        if (low < high) {
            int pivotIndex = partition(low, high, c);
            quicksort(low, pivotIndex - 1, c);
            quicksort(pivotIndex + 1, high, c);
        }
    }

    @SuppressWarnings("unchecked")
    private int partition(int low, int high, Comparator<? super E> c) {
        E pivot = (E) elements[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (c.compare((E)elements[j], pivot) <= 0) {
                i++;
                E temp = (E) elements[i];
                elements[i] = elements[j];
                elements[j] = temp;
            }
        }
        E temp = (E) elements[i + 1];
        elements[i + 1] = elements[high];
        elements[high] = temp;

        return i + 1;
    }

    @Override
    public void sort(Comparator<? super E> c) {
        quicksort(0, size - 1, c);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]);
            if (i != size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

}