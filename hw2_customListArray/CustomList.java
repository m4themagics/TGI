package hw2_customListArray;

import java.util.Collection;
import java.util.Comparator;

public interface CustomList<E> {
    void add(E element);

    void add(int index, E element);

    E get(int index);

    boolean addAll(Collection<? extends E> c);

    void clear();

    void remove(int index);

    boolean remove(Object o);

    int indexOf(Object o);

    void sort(Comparator<? super E> c);

    int size();

    boolean isEmpty();

    boolean equals(Object o);

    int hashCode();

    String toString();
}