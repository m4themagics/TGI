package hw2_customListArray;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

import org.junit.jupiter.api.Test;

class CustomArrayListTest {

    @Test
    void testAdd() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        list.add(1);
        assertEquals(1, (int) list.get(0));
    }

    @Test
    void testAddAll() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        Collection<Integer> c = new ArrayList<>();
        c.add(1);
        c.add(2);
        list.addAll(c);
        assertEquals(1, (int) list.get(0));
        assertEquals(2, (int) list.get(1));
    }

    @Test
    void testGet() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        list.add(1);
        list.add(2);
        assertEquals(1, (int) list.get(0));
        assertEquals(2, (int) list.get(1));
    }

    @Test
    void testClear() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        list.add(1);
        list.add(2);
        list.clear();
        assertTrue(list.isEmpty());
    }

    @Test
    void testRemove() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        list.add(1);
        list.add(2);
        list.remove(0);
        assertEquals(2, (int) list.get(0));
    }

    @Test
    void testIndexOf() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        list.add(1);
        list.add(2);
        assertEquals(0, list.indexOf(1));
        assertEquals(-1, list.indexOf(3));
    }

    @Test
    void testRangeCheck() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
    }

    @Test
    void testValidateIndexForGetRemove() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        list.add(1);
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
        list.remove(0);
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
    }

    @Test
    void testQuicksort() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        list.add(3);
        list.add(1);
        list.add(4);
        list.add(1);
        list.add(5);
        list.add(9);
        list.add(2);
        list.add(6);
        list.add(5);
        list.add(3);
        list.sort(Comparator.comparingInt(a -> a));
        assertEquals("[1, 1, 2, 3, 3, 4, 5, 5, 6, 9]", list.toString());
    }

}