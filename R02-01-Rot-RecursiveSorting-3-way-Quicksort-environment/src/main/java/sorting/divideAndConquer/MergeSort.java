package sorting.divideAndConquer;

import sorting.AbstractSorting;

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
         int middle = (int) Math.ceil((leftIndex + rightIndex) / 2);
         sort(array, leftIndex, middle);
         sort(array, middle + 1, rightIndex);
         merge(array, leftIndex, rightIndex);
      }
   }

   private void merge(T[] array, int left, int right) {
      T[] lista = (T[]) new Comparable[right - left + 1];
      int middle = (int) Math.ceil((left + right) / 2);
      int inicio = left, fim = right;
      int aux = middle + 1;
      int indice = 0;
      while (left <= middle && aux <= right) {
         if (array[left].compareTo(array[aux]) >= 0) {
            lista[indice++] = array[aux++];
         } else if (array[left].compareTo(array[aux]) < 0) {
            lista[indice++] = array[left++];
         }
      }
      if (left > middle) {
         while (aux <= right) {
            lista[indice++] = array[aux++];
         }
      } else if (aux > right) {
         while (left <= middle) {
            lista[indice++] = array[left++];
         }
      }
      indice = 0;
      while (inicio <= fim) {
         array[inicio++] = lista[indice++];
      }
   }
}