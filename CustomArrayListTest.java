import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

public class CustomArrayListTest {
    @Test
    public void testAddAndGet() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        list.add(5);
        list.add(10);
        list.add(15);

        assertEquals(5, list.get(0));
        assertEquals(10, list.get(1));
        assertEquals(15, list.get(2));
    }

    @Test
    public void testSize() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        assertEquals(0, list.size());

        list.add(5);
        assertEquals(1, list.size());

        list.add(10);
        assertEquals(2, list.size());
    }

    @Test
    public void testIsEmpty() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        assertTrue(list.isEmpty());

        list.add(5);
        assertFalse(list.isEmpty());
    }

    @Test
    public void testRemove() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        list.add(5);
        list.add(6);
        list.add(7);

        list.remove(1);
        assertEquals(2, list.size());
        assertEquals(7, list.get(1));
    }

    @Test
    public void testOutOfBounds() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        list.add(5);
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
    }

    @Test
    public void testSort() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        list.add(15);
        list.add(5);
        list.add(10);
        list.sort(Comparator.naturalOrder());
        assertEquals(5, list.get(0));
        assertEquals(10, list.get(1));
        assertEquals(15, list.get(2));
    }
}