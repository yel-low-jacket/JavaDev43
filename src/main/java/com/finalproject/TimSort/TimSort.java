package com.finalproject.TimSort;
import com.finalproject.customarraylist.CustomArrayList;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.List;

public class TimSort {


    // Минимальный размер подмассива (run)
    private static final int MIN_MERGE = 32;

    // Основная функция сортировки
    public static <T> void timSort(List<T> arr, Comparator<T> comparator) {
        int n = arr.size();
        if (n < 2) return;

        // Вычисляем минимальный размер подмассива
        int minRun = calcMinRun(n);

        // Сортировка подмассивов
        for (int i = 0; i < n; i += minRun) {
            insertionSort(arr, i, Math.min(i + minRun, n), comparator);
        }

        // Слияние подмассивов
        for (int size = minRun; size < n; size = 2 * size) {
            for (int left = 0; left < n; left += 2 * size) {
                int mid = left + size - 1;
                int right = Math.min(left + 2 * size - 1, n - 1);
                if (mid < right) {
                    merge(arr, left, mid, right, comparator);
                }
            }
        }
    }

    // Вычисление минимального размера подмассива
    private static int calcMinRun(int n) {
        int r = 0;
        while (n >= MIN_MERGE) {
            r |= (n & 1);
            n >>= 1;
        }
        return n + r;
    }

    // Сортировка вставками
    private static <T> void insertionSort(List<T> arr, int left, int right, Comparator<T> comparator) {
        for (int i = left + 1; i < right; i++) {
            T key = arr.get(i);
            int j = i - 1;
            while (j >= left && comparator.compare(arr.get(j), key) > 0) {
                arr.set(j + 1, arr.get(j));
                j--;
            }
            arr.set(j + 1, key);
        }
    }

    // Слияние двух подмассивов
    private static <T> void merge(List<T> arr, int left, int mid, int right, Comparator<T> comparator) {
        int len1 = mid - left + 1;
        int len2 = right - mid;

        // Временные массивы
        CustomArrayList<T> leftArr = new CustomArrayList<>(arr.subList(left, mid + 1));
        CustomArrayList<T> rightArr = new CustomArrayList<>(arr.subList(mid + 1, right + 1));

        int i = 0, j = 0, k = left;

        // Слияние
        while (i < len1 && j < len2) {
            if (comparator.compare(leftArr.get(i), rightArr.get(j)) <= 0) {
                arr.set(k++, leftArr.get(i++));
            } else {
                arr.set(k++, rightArr.get(j++));
            }
        }

        // Копирование оставшихся элементов
        while (i < len1) {
            arr.set(k++, leftArr.get(i++));
        }
        while (j < len2) {
            arr.set(k++, rightArr.get(j++));
        }
    }
}