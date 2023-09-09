import org.example.MyArrayList.MyArrayList;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

public class TeatMyArrayList {
    @Test
    public void testAddGet() {
        MyArrayList<Integer> array = new MyArrayList<>();
        array.add(17);
        array.add(1009);
        array.add(777);
        array.add(66);
        assertEquals(17, (int) array.get(0));
        assertEquals(1009, (int) array.get(1));
        assertEquals(777, (int) array.get(2));
        assertEquals(4, array.getSize());
    }

    @Test
    public void testAddIndex() {
        MyArrayList<String> array = new MyArrayList<>();
        array.add("coffe");
        array.add("system");
        array.add("tv");
        array.add("mouse");
        array.add("phone");
        array.add(4, "company");
        array.add(3, "company");
        assertEquals("coffe", array.get(0));
        assertEquals("system", array.get(1));
        assertEquals("tv", array.get(2));
        assertEquals("company", array.get(3));
        assertEquals(7, array.getSize());
    }

    @Test
    public void testAddAtIndexWithInvalidIndex() {
        MyArrayList<Integer> array = new MyArrayList<>();
        array.add(12);
        assertThrows(IndexOutOfBoundsException.class, () -> {
            array.add(2, 70);
        });
        assertEquals(1, array.getSize());
    }

    @Test
    public void testRemove() {
        MyArrayList<String> array = new MyArrayList<>();
        array.add("yandex");
        array.add("google");
        array.add("mail");
        String removed = array.remove(2);
        assertEquals("mail", removed);
        assertEquals(2, array.getSize());
        assertEquals("yandex", array.get(0));
        assertEquals("google", array.get(1));
    }

    @Test
    public void testRemoveWithInvalidIndex() {
        MyArrayList<Integer> array = new MyArrayList<>();
        array.add(4);
        assertThrows(IndexOutOfBoundsException.class, () -> {
            array.remove(1);
        });
        assertEquals(1, array.getSize());
    }

    @Test
    public void testClear() {
        MyArrayList<String> array = new MyArrayList<>();
        array.add("yandex");
        array.add("google");
        array.add("mail");
        array.clear();
        assertEquals(0, array.getSize());
    }

    @Test
    public void testQuickSort() {
        MyArrayList<Integer> array = new MyArrayList<>();
        array.add(100);
        array.add(18);
        array.add(22);
        array.add(90);
        array.add(2);
        array.add(109);
        array.add(180);
        array.add(202);
        array.add(920);
        array.add(12);
        array.quickSort(Comparator.naturalOrder());
        assertEquals(2, (int) array.get(0));
        assertEquals(12, (int) array.get(1));
        assertEquals(18, (int) array.get(2));
        assertEquals(22, (int) array.get(3));
        assertEquals(90, (int) array.get(4));
        assertEquals(100, (int) array.get(5));
        assertEquals(109, (int) array.get(6));
        assertEquals(180, (int) array.get(7));
        assertEquals(202, (int) array.get(8));
        assertEquals(920, (int) array.get(9));
    }

}
