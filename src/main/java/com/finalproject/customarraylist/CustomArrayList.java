package com.finalproject.customarraylist;


import java.util.*;
import java.util.function.UnaryOperator;

public class CustomArrayList<T> implements List<T> {
    private T[] array;
    private static final int DEFAULT_CAPACITY = 16;
    private static final int MULTIPLIER = 2;
    private int lastPosition = 0;

    public CustomArrayList() {
        this.array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public CustomArrayList(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity is wrong: " + capacity);
        }
        this.array = (T[]) new Object[capacity];
    }

    // Исправлен конструктор: убран внутренний <T>, чтобы использовать обобщенный тип класса
    public CustomArrayList(List<T> list) {
        this(list.size());
        for (T element : list) {
            add(element);
        }
    }

    @Override
    public boolean add(T element) {
        if (lastPosition >= array.length) {
            growArray();
        }
        array[lastPosition++] = element;
        return true; // Возвращаем true при успешном добавлении
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object element : c) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean changed = false;
        for (T element : c) {
            add(element);
            changed = true;
        }
        return changed;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        checkBounds(index);
        int addSize = c.size();
        if (lastPosition + addSize > array.length) {
            growArray(lastPosition + addSize);
        }
        System.arraycopy(array, index, array, index + addSize, lastPosition - index);
        int i = 0;
        for (T element : c) {
            array[index + i++] = element;
        }
        lastPosition += addSize;
        return !c.isEmpty();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean changed = false;
        for (int i = lastPosition - 1; i >= 0; i--) {
            if (c.contains(array[i])) {
                remove(i);
                changed = true;
            }
        }
        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean changed = false;
        for (int i = lastPosition - 1; i >= 0; i--) {
            if (!c.contains(array[i])) {
                remove(i);
                changed = true;
            }
        }
        return changed;
    }

    @Override
    public void replaceAll(UnaryOperator<T> operator) {
        for (int i = 0; i < lastPosition; i++) {
            array[i] = operator.apply(array[i]);
        }
    }

    public void insert(int index, T element) {
        if (index == lastPosition) {
            add(element);
            return;
        }
        checkBounds(index);
        if (lastPosition + 1 > array.length) {
            growArray();
        }
        System.arraycopy(array, index, array, index + 1, lastPosition - index);
        array[index] = element;
        lastPosition++;
    }

    public T set(int index, T element) {
        checkBounds(index);
        T oldElement = array[index];
        array[index] = element;
        return oldElement;
    }

    @Override
    public void add(int index, T element) {
        insert(index, element);
    }

    @Override
    public T remove(int index) {
        checkBounds(index);
        T element = array[index];
        System.arraycopy(array, index + 1, array, index, lastPosition - index - 1);
        array[lastPosition--] = null;
        return element;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < lastPosition; i++) {
            if ((o == null && array[i] == null) || o.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = lastPosition - 1; i >= 0; i--) {
            if ((o == null && array[i] == null) || o.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new CustomListIterator(0);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return new CustomListIterator(index);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > lastPosition || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException();
        }
        CustomArrayList<T> subList = new CustomArrayList<>();
        System.arraycopy(array, fromIndex, subList.array, 0, toIndex - fromIndex);
        subList.lastPosition = toIndex - fromIndex;
        return subList;
    }

    @Override
    public Spliterator<T> spliterator() {
        return Spliterators.spliterator(array, 0, lastPosition, Spliterator.ORDERED);
    }

    public boolean remove(Object removedElement) {
        boolean result = false;
        for (int i = 0; i < lastPosition; i++) {
            if ((removedElement == null && array[i] == null) || removedElement.equals(array[i])) {
                remove(i);
                result = true;
                break;
            }
        }
        return result;
    }

    public T get(int index) {
        checkBounds(index);
        return array[index];
    }

    public void clear() {
        Arrays.fill(array, null);
        lastPosition = 0;
    }

    public int size() {
        return lastPosition;
    }

    @Override
    public boolean isEmpty() {
        return lastPosition == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public Iterator<T> iterator() {
        return listIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(array, lastPosition);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < lastPosition) {
            return (T1[]) Arrays.copyOf(array, lastPosition, a.getClass());
        }
        System.arraycopy(array, 0, a, 0, lastPosition);
        if (a.length > lastPosition) {
            a[lastPosition] = null;
        }
        return a;
    }

    public void sort(Comparator<? super T> comparator) {
        if (comparator == null) {
            comparator = (o1, o2) -> ((Comparable<T>) o1).compareTo(o2);
        }
        quickSort(0, lastPosition - 1, comparator);
    }

    private void quickSort(int low, int high, Comparator<? super T> comparator) {
        if (low >= high) {
            return;
        }
        int middle = low + (high - low) / 2;
        T pivot = array[middle];

        int left = low;
        int right = high;

        while (left <= right) {
            while (comparator.compare(array[left], pivot) < 0) left++;
            while (comparator.compare(array[right], pivot) > 0) right--;
            if (left <= right) {
                T temp = array[left];
                array[left] = array[right];
                array[right] = temp;
                left++;
                right--;
            }
        }
        quickSort(low, right, comparator);
        quickSort(left, high, comparator);
    }

    private void growArray() {
        growArray(array.length * MULTIPLIER);
    }

    private void growArray(int newCapacity) {
        if (newCapacity > array.length) {
            array = Arrays.copyOf(array, newCapacity);
        }
    }

    private void checkBounds(int index) {
        if (index < 0 || index >= lastPosition) {
            throw new IndexOutOfBoundsException(
                    String.format("Index: %d, Size: %d", index, lastPosition));
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(array, lastPosition));
    }

    // Внутренний класс итератора
    private class CustomListIterator implements ListIterator<T> {
        private int cursor;
        private int lastRet = -1;

        public CustomListIterator(int index) {
            checkBounds(index);
            cursor = index;
        }

        @Override
        public boolean hasNext() {
            return cursor != lastPosition;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T element = array[cursor];
            lastRet = cursor++;
            return element;
        }

        @Override
        public boolean hasPrevious() {
            return cursor > 0;
        }

        @Override
        public T previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            cursor--;
            lastRet = cursor;
            return array[cursor];
        }

        @Override
        public int nextIndex() {
            return cursor;
        }

        @Override
        public int previousIndex() {
            return cursor - 1;
        }

        @Override
        public void remove() {
            if (lastRet == -1) {
                throw new IllegalStateException();
            }
            CustomArrayList.this.remove(lastRet);
            cursor = lastRet;
            lastRet = -1;
        }

        @Override
        public void set(T e) {
            if (lastRet == -1) {
                throw new IllegalStateException();
            }
            array[lastRet] = e;
        }

        @Override
        public void add(T e) {
            CustomArrayList.this.add(cursor, e);
            cursor++;
            lastRet = -1;
        }
    }
}