package org.example;
import java.util.Comparator;

/**
 * @author Антон Городсков
 */
public class MyArrayList<T> implements MyList<T> {        // Обобщённый тип T, т.к. заранее не знаем, объекты какого класса будут
    // храниться в конкретной реализации списка MyList.
    private T[] elements;  // "Классический" массив для хранения элементов
    private int size;           // Текущее количество элементов
    private static final int DEFAULT_CAPACITY = 10;  // Начальный размер массива

    public MyArrayList() {

        this.elements = (T[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;

    }

    @Override
    public void add(T element) {

        if (size == elements.length) {
            increaseCapacity();
        }

        elements[size++] = element;

    }

    private void increaseCapacity() {

        int newCapacity = (int) (elements.length * 1.5 + 1);
        T[] newArray = (T[]) new Object[newCapacity];
        System.arraycopy(elements, 0, newArray, 0, size);
        elements = newArray;

    }

    @Override
    public T get(int index) {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        return (T) elements[index];  // Приведение типа

    }

    @Override
    public T remove(int index) {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        T removedElement = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[--size] = null;

        return removedElement;      // Пусть знают, кого лишились!

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int capacity() {
        return elements.length;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder("[");

        for (int i = 0; i < size; i++) {
            sb.append(elements[i]);
            if (i < size - 1) sb.append(", ");
        }

        sb.append("]");

        return sb.toString();

    }

    @Override
    public void destroy(){

        elements = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;

    }

    @Override
    public void sort(Comparator<T> comparator) {

        for (int i = 0; i < size - 1; i++) {

            for (int j = 0; j < size - 1 - i; j++) {

                T current = (T) elements[j];
                T next = (T) elements[j + 1];

                if (comparator.compare(current, next) > 0) {
                    T temp = elements[j];
                    elements[j] = elements[j + 1];
                    elements[j + 1] = (T) temp;
                }

            }

        }

    }

    @Override
    public T insert(T element, int index) {

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        if (size == elements.length) {
            increaseCapacity();
        }

        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;

        return (T) elements[index];

    }

}
