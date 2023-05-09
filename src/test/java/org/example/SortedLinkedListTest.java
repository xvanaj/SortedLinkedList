package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SortedLinkedListTest {

    @Test
    void testAdd_listIsSorted() {
        // GIVEN
        SortedLinkedList<String> list = new SortedLinkedList<>();

        // WHEN
        list.add("d");
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("e");

        // THEN
        assertArrayEquals(new String[]{"a", "b", "c", "d", "e"}, list.toArray(new String[0]));
    }

    @Test
    void testAdd_allowDuplicates() {
        // GIVEN
        SortedLinkedList<Integer> list = new SortedLinkedList<>();

        // WHEN
        list.add(1);
        list.add(2);
        list.add(4);
        list.add(3);
        list.add(5);
        list.add(2);

        // THEN
        assertArrayEquals(new Integer[]{1,2,2,3,4,5}, list.toArray(new Integer[0]));
    }

    @Test
    public void testContains_returnTrue() {
        // GIVEN
        SortedLinkedList<String> list = new SortedLinkedList<>();

        // WHEN
        list.add("adsa");
        list.add("sdfg");
        list.add("gdfrt");
        list.add("ergdf");
        boolean contains = list.contains("gdfrt");

        //THEN
        assertTrue(contains);
    }

    @Test
    public void testContains_returnFalse() {
        // GIVEN
        SortedLinkedList<String> list = new SortedLinkedList<>();

        // WHEN
        list.add("adsa");
        list.add("sdfg");
        list.add("gdfrt");
        list.add("ergdf");
        boolean contains = list.contains("somethingElse");

        //THEN
        assertFalse(contains);
    }

    @Test
    public void testRemove_succesfullyRemovesItem() {
        // GIVEN
        SortedLinkedList<Integer> list = new SortedLinkedList<>();

        //WHEN
        list.add(2);
        list.add(5);
        list.add(6);
        list.add(3);
        list.add(1);
        boolean removed = list.remove(Integer.valueOf(3));

        //THEN
        assertTrue(removed);
        assertArrayEquals(new Integer[]{1, 2, 5, 6}, list.toArray(new Integer[0]));
    }

    @Test
    public void testRemove_removeReturnsFalse() {
        // GIVEN
        SortedLinkedList<Integer> list = new SortedLinkedList<>();

        //WHEN
        list.add(2);
        list.add(4);
        list.add(1);
        boolean removed = list.remove(Integer.valueOf(3));

        //THEN
        assertFalse(removed);
        assertArrayEquals(new Integer[]{1, 2, 4}, list.toArray(new Integer[0]));
    }

    @Test
    public void testSortedLinkedList_addNull_throwsNPE() {
        // GIVEN
        SortedLinkedList<Integer> list = new SortedLinkedList<>();

        // WHEN + THEN
        assertThrows(NullPointerException.class, () -> list.add(null));
    }
}