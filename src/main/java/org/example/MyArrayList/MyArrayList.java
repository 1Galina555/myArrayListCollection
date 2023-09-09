package org.example.MyArrayList;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

/**
 * Реализация  ArrayList.
 * @Author Paramoshina Galina
 */
public class MyArrayList <T>{
    private static final Object[] EMPTY_ARRAY = {};
    private Object[] items;
    private int size;

    /**
     * Создается пустой список.
     */
    public MyArrayList() {
        this.items = EMPTY_ARRAY;
        this.size = 0;
    }

    /**
     * @param capacity- емкость внутреннего массива
     * Создается пустой список с  емкостью ,равной  capacity.
     */
    public MyArrayList(int capacity) {
        if (capacity > 0) {
            this.items = new Object[capacity];
        } else if (capacity == 0) {
            this.items = EMPTY_ARRAY;
        } else {
            throw new IllegalArgumentException("Capacity<0");
        }
        this.size = 0;
    }

    /**
     * @param necessaryCapacity -задает  емкость
     * Обеспечивает  емкость внутреннего массива
     *  В метод Arrays.copyOf() передаем массив-items и длину нового массива-necessaryCapacity,
     *  в который копируем данные.
     */
    private void capacityProvision(int necessaryCapacity) {
        if(necessaryCapacity > items.length) {
            if (items == EMPTY_ARRAY) {
                items= Arrays.copyOf(items, necessaryCapacity);
            } else {
                int oldCapacity = items.length;
                int newCapacity = (oldCapacity == 1)//используем тернарный оператор
                        ? 2
                        : oldCapacity + (oldCapacity >> 1);/*иначе сдвиг вправо на 1 -уменьшает число до его половины.
                         Означает деление на 2 в степени 1.
                         Делим oldCapacity на 2 и прибавляем oldCapacity*/
                items = Arrays.copyOf(items, newCapacity);
            }
        }
    }

    /**
     *  @param item - объект для добавления
     *  Добавляется объект в конец списка.
     */
    public void add(T item) {
        capacityProvision(size + 1);
        items[size++] = item;
    }

    /**
     * @param index -индекс для добавления
     * Добавить элемент по индексу(не заменить)
     */
    public void add(int index, T item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index < 0 или index > размера массива");
        }
        capacityProvision(size + 1);
        System.arraycopy(items, index,
                items, index + 1,
                size - index);
        items[index] = item;
        size++;
    }

    /**
     * @param index индекс элемента
     * @return - возвращает элемент по индексу
     * Получить элемент по  индексу.
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index < 0 или index > размера массива");
        }
        return (T) items[index];
    }

    /**
     * Удалить элемент  по индексу.
     * @param index индекс элемента для удаления
     * @return - возвращает удаленный элемент-deleteItem
     */
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index < 0 или index > размера массива");
        }
        T deleteItem= (T) items[index];
        System.arraycopy(items, index + 1, items, index, size - index - 1);
        items[--size] = null;
        return deleteItem;
    }

    /**
     * Очистить всю коллекцию-присваивая объектам null в цикле и в конце обнуляя размер списка
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            items[i] = null;
        }
        size = 0;
    }

    /**
     * Заменить элемент по индексу.
     * @param index индекс элемента для изменения
     * @return - элемент, ранее находившийся в указанной позиции-oldValue
     */
    public T set(int index, T item) {
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException("index < 0 или index > размера массива");
        }

        T oldValue = (T) items[index];
        items[index] = item;
        return oldValue;
    }

    /**
     * @param j позиция второго элемента для обмена
     * @param i позиция первого элемента для обмена
     * Меняет местами элементы на позициях i и j в массиве.
     */
    private void change(int i, int j) {
        Object temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }

    /**
     * Разделяет массив  вокруг опорного элемента.
     * @param low        нижняя граница диапазона
     * @param high       верхняя граница диапазона
     * @param comparator компаратор для сравнения элементов
     * @return - возвращает индекс опорного элемента
     */
    private int separation(int low, int high, Comparator<? super T> comparator) {
        T pivot = (T) items[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (comparator.compare((T) items[j], pivot) <= 0) {
                i++;
                change(i, j);
            }
        }
        change(i + 1, high);
        return i + 1;
    }

    /**
     * Рекурсивно сортирует подмассивы внутри заданного диапазона.
     * @param comparator компаратор для сравнения элементов
     * @param low        нижняя граница диапазона
     * @param high       верхняя граница диапазона
     */
    private void quickSort(int low, int high, Comparator<? super T> comparator) {
        if (low < high) {
            int pivotIndex = separation(low, high, comparator);
            quickSort(low, pivotIndex - 1, comparator);
            quickSort(pivotIndex + 1, high, comparator);
        }
    }

    /**
     * Сортирует список с использованием алгоритма QuickSort.
     * @param comparator компаратор для сравнения элементов
     */
    public void quickSort(Comparator<? super T> comparator) {
        quickSort(0, size - 1, comparator);
    }

    @Override
    public String toString() {
        return Arrays.toString(items);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyArrayList<?> that = (MyArrayList<?>) o;
        return size == that.size && Arrays.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(items);
        return result;
    }

    /**
     * Возвращает количество элементов в списке.
     * @return количество элементов
     */
    public int getSize() {
        return size;
    }
}
