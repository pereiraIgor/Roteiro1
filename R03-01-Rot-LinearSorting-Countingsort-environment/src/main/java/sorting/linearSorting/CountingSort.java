package sorting.linearSorting;

import java.util.Arrays;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o máximo inteiro presente no array a ser ordenado.
 * 
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		Integer bigger = 0;
		for (int i = leftIndex; i <= rightIndex; i++) {
			if (array[i] > bigger) {
				bigger = array[i];
			}
		}
		Integer[] array_C = new Integer[bigger + 1];
		for (int i = 0; i < array_C.length; i++) {
			array_C[i] = 0;
		}
		//System.out.println(Arrays.toString(array_C));
		for (int i = leftIndex; i <= rightIndex; i++) {
			array_C[array[i]]++;
		}

		// soma dos prefixos
		for (int i = 1; i < array_C.length; i++) {
			array_C[i] += array_C[i - 1];
		}
		
		Integer[] array_B = new Integer[array.length];
		for (int i = rightIndex; i >= leftIndex; i--) {
			array_C[array[i]]--;
			array_B[array_C[array[i]]] = array[i];
		}
		for (int i = leftIndex; i <= rightIndex; i++) {
			array[i] = array_B[i];
		}
	}
}
