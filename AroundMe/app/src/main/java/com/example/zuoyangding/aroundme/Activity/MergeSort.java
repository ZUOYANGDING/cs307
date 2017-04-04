package com.example.zuoyangding.aroundme.Activity;

import android.location.Location;

import com.example.zuoyangding.aroundme.DataModels.GroupClass;

public class MergeSort {
    private GroupClass[] numbers;
    private GroupClass[] helper;

    private int number;

    public void sort(GroupClass[] values, Location mlocation) {
        this.numbers = values;
        number = values.length;
        this.helper = new GroupClass[number];
        mergesort(0, number - 1, mlocation);
    }

    private void mergesort(int low, int high, Location mlocation) {
        // check if low is smaller than high, if not then the array is sorted
        if (low < high) {
            // Get the index of the element which is in the middle
            int middle = low + (high - low) / 2;
            // Sort the left side of the array
            mergesort(low, middle, mlocation);
            // Sort the right side of the array
            mergesort(middle + 1, high, mlocation);
            // Combine them both
            merge(low, middle, high, mlocation);
        }
    }

    private void merge(int low, int middle, int high, Location mlocation) {

        // Copy both parts into the helper array
        for (int i = low; i <= high; i++) {
            helper[i] = numbers[i];
        }

        int i = low;
        int j = middle + 1;
        int k = low;
        // Copy the smallest values from either the left or the right side back
        // to the original array
        while (i <= middle && j <= high) {
            double l1 = helper[i].alt;
            double a1 = helper[i].lon;

            double l2 = helper[j].alt;
            double a2 = helper[j].lon;

            double l0 = mlocation.getLongitude();
            double a0 = mlocation.getAltitude();

            double distance1 = Math.pow((l1-l0), 2) + Math.pow((a1-a0),2);
            double distance2 = Math.pow((l2-l0), 2) + Math.pow((a2-a0),2);


            if (distance1 >= distance2) {
                numbers[k] = helper[i];
                i++;
            } else {
                numbers[k] = helper[j];
                j++;
            }
            k++;
        }
        // Copy the rest of the left side of the array into the target array
        while (i <= middle) {
            numbers[k] = helper[i];
            k++;
            i++;
        }

    }
}