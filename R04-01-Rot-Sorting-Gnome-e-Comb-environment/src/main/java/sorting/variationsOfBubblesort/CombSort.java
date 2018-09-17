package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * The combsort algoritm.
 */
public class CombSort<T extends Comparable<T>> extends AbstractSorting<T> {
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (Util.verificaLimites(array, leftIndex, rightIndex)) {
			double fator = 1.25;
			int tamanhoTotal = rightIndex - leftIndex + 1;
			int gap = (int) (tamanhoTotal / fator);
			boolean ordenado = false;
			while (gap >= 1 && !ordenado) {
				int variavel = 0;
				for (int i = leftIndex; i + gap <= rightIndex; i++) {
					if (array[i].compareTo(array[i + gap]) > 0) {
						Util.swap(array, i, i + gap);
						variavel++;
					}
					if (gap == 1) {
						gap = 1;
					} else {
						gap /= fator;
					}
				}
				if (variavel == 0) {
					ordenado = true;
				}
			}
		}
	}
}
