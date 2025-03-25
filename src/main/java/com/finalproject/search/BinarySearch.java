package com.finalproject.search;

import com.finalproject.customarraylist.CustomArrayList;
import java.util.Comparator;

public class BinarySearch {

    public static <T> int binarySearch(CustomArrayList<T> list, T valueToFind, Comparator<T> comparator) {
        int left = 0, right = list.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cmp = comparator.compare(list.get(mid), valueToFind);

            if (cmp == 0) {
                return mid;
            }
            if (cmp < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }
}