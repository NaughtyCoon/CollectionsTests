package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Comparator;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class MyArrayListTest {

    private MyArrayList<Gal> list;
    private static final Gal JENNY = new Gal("Jenny", 18);
    private static final Gal JANE = new Gal("Jane", 21);
    private static final Gal JADE = new Gal("Jade", 25);

    @BeforeEach
    void setUp() {
        list = new MyArrayList<>();
        list.add(JENNY);
        list.add(JANE);
        list.add(JADE);
    }

    @AfterEach
     void tearDown() {
        list.destroy();
        assertEquals(0, list.size());
    }

    @Test
    void testInitialState() {
        assertNotNull(list);
        assertEquals(3, list.size());
        assertTrue(list.capacity() >= list.size());
    }

    @Test
    void testAdd() {
        Gal newGal = new Gal("Julia", 34);
        list.add(newGal);
        assertEquals(4, list.size());
        assertEquals(newGal, list.get(3));
    }

    @Test
    void testGet() {
        assertEquals(JENNY, list.get(0));
        assertEquals(JANE, list.get(1));
        assertEquals(JADE, list.get(2));
    }

    @Test
    void testGetOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(3));
    }

    @Test
    void testRemove() {
        Gal removed = list.remove(1);
        assertEquals(JANE, removed);
        assertEquals(2, list.size());
        assertEquals(JADE, list.get(1));
    }

    @Test
    void testRemoveOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(3));
    }

    @Test
    void testCapacityIncrease() {
        int initialCapacity = list.capacity();
        // Fill the list to trigger capacity increase
        for (int i = list.size(); i <= initialCapacity; i++) {
            list.add(new Gal("Gal" + i, 20 + i));
        }
        assertTrue(list.capacity() > initialCapacity);
    }

    @Test
    void testDestroy() {
        list.destroy();
        assertEquals(0, list.size());
        assertEquals(10, list.capacity()); // DEFAULT_CAPACITY
    }

    @Test
    void testSortByName() {
        list.sort(Comparator.comparing(Gal::name));
        assertEquals(JADE, list.get(0));
        assertEquals(JANE, list.get(1));
        assertEquals(JENNY, list.get(2));
    }

    @Test
    void testSortByAge() {
        list.sort(Comparator.comparingInt(Gal::age));
        assertEquals(JENNY, list.get(0));
        assertEquals(JANE, list.get(1));
        assertEquals(JADE, list.get(2));
    }

    @Test
    void insertShouldThrowWhenIndexInvalid() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.insert(new Gal("X", 0), -1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.insert(new Gal("X", 0), 100));
    }

    @ParameterizedTest
    @MethodSource("provideInsertTestData")
    void testInsert(Gal element, int index, Gal expectedElement, int expectedSize) {
        Gal inserted = list.insert(element, index);
        assertEquals(expectedElement, inserted);
        assertEquals(expectedSize, list.size());
        assertEquals(element, list.get(index));
    }

    private static Stream<Arguments> provideInsertTestData() {
        Gal JASMINE = new Gal("Jasmine", 19);
        return Stream.of(
                Arguments.of(JASMINE, 0, JASMINE, 4),
                Arguments.of(JASMINE, 1, JASMINE, 4),
                Arguments.of(JASMINE, 2, JASMINE, 4),
                Arguments.of(JASMINE, 3, JASMINE, 4)
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidIndices")
    void testInsertOutOfBounds(int index) {
        assertThrows(IndexOutOfBoundsException.class, () -> list.insert(new Gal("Julia", 34), index));
    }

    private static Stream<Arguments> provideInvalidIndices() {
        return Stream.of(
                Arguments.of(-1),
                Arguments.of(4)
        );
    }
}