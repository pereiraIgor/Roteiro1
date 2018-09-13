package sorting.divideAndConquer;

import sorting.AbstractSorting;
import util.Util;

/**
 * Quicksort is based on the divide-and-conquer paradigm. The algorithm chooses
 * a pivot element and rearranges the elements of the interval in such a way
 * that all elements lesser than the pivot go to the left part of the array and
 * all elements greater than the pivot, go to the right part of the array. Then
 * it recursively sorts the left and the right parts. Notice that if the list
 * has length == 1, it is already sorted.
 */
public class QuickSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex >= rightIndex) {
			return;
		} else {
			int m = partition(array, leftIndex, rightIndex);
			sort(array, leftIndex, m - 1);
			sort(array, m + 1, rightIndex);
		}
	}

	private int partition(T[] array, int leftIndex, int rightIndex) {
		T pivot = array[leftIndex];
		int i = leftIndex, j = leftIndex + 1;
		while (j <= rightIndex) {
			if (array[j].compareTo(pivot) < 0) {
				Util.swap(array, ++i, j);
			}
			j++;
		}
		Util.swap(array, i, leftIndex);
		return i;
	}
}
