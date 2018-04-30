package com.example.administrator.utils;

/**
 * Created by Administrator on 2016/10/19.
 */
public class SortUtils {
    public static int biSearch(int[] array, int a) {
        int lo = 0;
        int hi = array.length - 1;
        int mid;
        while (lo <= hi) {
            mid = (lo + hi) / 2;
            if (array[mid] == a) {
                return mid + 1;
            } else if (array[mid] < a) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 查找最接近目标值的数，并返回
     *
     * @param array     有序数组
     * @param targetNum
     * @return
     */
    public static Integer binarysearchKey(int[] array, int targetNum) {

        int targetindex = 0;
        int left = 0, right = 0;
        for (right = array.length - 1; left != right; ) {
            int midIndex = (right + left) / 2;
            int mid = (right - left);
            int midValue = array[midIndex];
            if (targetNum == midValue) {
                return midIndex;
            }

            if (targetNum > midValue) {
                left = midIndex;
            } else {
                right = midIndex;
            }

            if (mid <= 2) {
                break;
            }
        }
        return ((array[right] - array[left]) / 2 > targetNum ? right
                : left);
    }
}
