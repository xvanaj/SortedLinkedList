package org.example;

import java.util.LinkedList;

public class SortedLinkedList<T extends Comparable<T>> extends LinkedList<T> {

    @Override
    public boolean add(T item) {
        //raise exception if null
        if (item == null) {
            throw new NullPointerException("Item to add to LinkedList must not be null");
        }

        //raise exception if not valid object - currently only String or Integer is allowed
        if (!(item instanceof Integer || item instanceof String)) {
            throw new ClassCastException("Item must be instance of Integer of String");
        }

        int low = 0;
        int high = size() - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            int compareResult = item.compareTo(get(mid));
            if (compareResult < 0) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        super.add(low, item);

        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean contains(Object value) {
        if (!(value instanceof Comparable)) {
            return false;
        }

        T comparableValue = (T) value;

        int low = 0;
        int high = size() - 1;
        int count = 0;
        while (low <= high) {
            count ++;
            int mid = (low + high) / 2;
            T midValue = get(mid);
            int compareResult = midValue.compareTo(comparableValue);
            if (compareResult == 0) {
                return true;
            } else if (compareResult < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean remove(Object value) {
        if (!(value instanceof Comparable)) {
            return false;
        }

        T comparableValue = (T) value;

        int low = 0;
        int high = size() - 1;
        int count = 0;

        while (low <= high) {
            count ++;
            int mid = (low + high) >>> 1;
            int compareResult = comparableValue.compareTo(get(mid));
            if (compareResult == 0) {
                super.remove(mid);
                return true;
            } else if (compareResult < 0) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return false;
    }

}
