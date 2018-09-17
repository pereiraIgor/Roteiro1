package sorting.linearSorting;

import java.util.Arrays;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos: - Alocar o tamanho minimo
 * possivel para o array de contadores (C) - Ser capaz de ordenar arrays
 * contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if(leftIndex < rightIndex) {
		Integer maximunValue = array[leftIndex];
		Integer minimumValue = array[leftIndex];
		
		for (int i = leftIndex; i <= rightIndex; i++) {
			if (array[i] > maximunValue) {
				maximunValue = array[i];
			}
			if (array[i] < minimumValue) {
				minimumValue = array[i];
			}
		}

		int pivot = -1 * minimumValue;
		int array_length = maximunValue - minimumValue + 1;

		Integer[] array_C = new Integer[array_length];
		for (int i = 0; i < array_C.length; i++) {
			array_C[i] = 0;
		}
		
		for (int i = leftIndex; i <= rightIndex; i++) {
			array_C[array[i] + pivot]++;
		}
		for (int i = 1; i < array_C.length; i++) {
			array_C[i] += array_C[i - 1];
		}
		
		Integer[] array_B = new Integer[array.length];
		for (int i = rightIndex; i >= leftIndex; i--) {
			array_C[array[i] + pivot]--;
			array_B[array_C[array[i] + pivot]] = array[i];
		}
		for (int i = leftIndex; i <= rightIndex; i++) {
			array[i] = array_B[i];
		}
		System.out.println(Arrays.toString(array));
		}
	}

}
