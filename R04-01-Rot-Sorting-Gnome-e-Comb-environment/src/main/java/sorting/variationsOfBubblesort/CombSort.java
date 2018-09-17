package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * The combsort algoritm.
 */
public class CombSort<T extends Comparable<T>> extends AbstractSorting<T> {
   @Override
   public void sort(T[] array, int leftIndex, int rightIndex) {
      double fator = 1.25;
      int tamanhoTotal = rightIndex - leftIndex + 1;
      int gap = (int) (tamanhoTotal / fator);

      while (gap > 1) {
         for (int i = leftIndex; i + gap <= rightIndex; i++) {
            if (array[i].compareTo(array[i + gap]) > 0) {
               Util.swap(array, i, i + gap);
            }
            gap /= fator;
         }
      }
      for (int j = leftIndex; j <= rightIndex; j++) {
         for (int i = j; i <= rightIndex; i++) {
            if (array[j].compareTo(array[i]) > 0) {
               Util.swap(array, j, i);
            }
         }
      }
   }
}
