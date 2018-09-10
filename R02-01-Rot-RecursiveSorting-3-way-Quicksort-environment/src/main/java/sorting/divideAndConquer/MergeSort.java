package sorting.divideAndConquer;

import java.awt.List;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import sorting.AbstractSorting;
import util.Util;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex >= rightIndex) {
			return;
		} else {
			sort(array, leftIndex, (int) Math.ceil((leftIndex + rightIndex) / 2));
			sort(array, (int) Math.ceil((leftIndex + rightIndex) / 2) + 1, rightIndex);
			merge(array, leftIndex,(int) Math.ceil((leftIndex + rightIndex) / 2),rightIndex);
		}
	}

	public void merge(T[] array, int left, int middle, int right) {
		//primeira lista = left -> middle
		//segunda lista = middle +1  -> right
		int variavel_aux=  middle+1;
		while (left < middle && variavel_aux < right) {
			if(array[left].compareTo(array[variavel_aux]) > 0) {
				Util.swap(array, i, j);
			}
		}
	}
}