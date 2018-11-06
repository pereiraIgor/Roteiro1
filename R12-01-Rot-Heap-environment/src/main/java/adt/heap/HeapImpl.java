package adt.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import util.Util;

/**
 * O comportamento de qualquer heap é definido pelo heapify. Neste caso o
 * heapify dessa heap deve comparar os elementos e colocar o maior sempre no
 * topo. Ou seja, admitindo um comparador normal (responde corretamente 3 > 2),
 * essa heap deixa os elementos maiores no topo. Essa comparação não é feita
 * diretamente com os elementos armazenados, mas sim usando um comparator. Dessa
 * forma, dependendo do comparator, a heap pode funcionar como uma max-heap ou
 * min-heap.
 */
public class HeapImpl<T extends Comparable<T>> implements Heap<T> {

	protected T[] heap;
	protected int index = -1;
	/**
	 * O comparador é utilizado para fazer as comparações da heap. O ideal é mudar
	 * apenas o comparator e mandar reordenar a heap usando esse comparator. Assim
	 * os metodos da heap não precisam saber se vai funcionar como max-heap ou
	 * min-heap.
	 */
	protected Comparator<T> comparator;

	private static final int INITIAL_SIZE = 20;
	private static final int INCREASING_FACTOR = 10;

	/**
	 * Construtor da classe. Note que de inicio a heap funciona como uma min-heap.
	 */
	@SuppressWarnings("unchecked")
	public HeapImpl(Comparator<T> comparator) {
		this.heap = (T[]) (new Comparable[INITIAL_SIZE]);
		this.comparator = comparator;
	}

	// /////////////////// METODOS IMPLEMENTADOS
	private int parent(int i) {
		return (i - 1) / 2;
	}

	/**
	 * Deve retornar o indice que representa o filho a esquerda do elemento indexado
	 * pela posicao i no vetor
	 */
	private int left(int i) {
		return (i * 2 + 1);
	}

	/**
	 * Deve retornar o indice que representa o filho a direita do elemento indexado
	 * pela posicao i no vetor
	 */
	private int right(int i) {
		return (i * 2 + 1) + 1;
	}

	@Override
	public boolean isEmpty() {
		return (index == -1);
	}

	@Override
	public T[] toArray() {
		ArrayList<T> resp = new ArrayList<T>();
		for (T elem : this.heap) {
			if (elem != null) {
				resp.add(elem);
			}
		}
		return (T[]) resp.toArray(new Comparable[0]);
	}

	// ///////////// METODOS A IMPLEMENTAR
	/**
	 * Valida o invariante de uma heap a partir de determinada posicao, que pode ser
	 * a raiz da heap ou de uma sub-heap. O heapify deve colocar os maiores
	 * (comparados usando o comparator) elementos na parte de cima da heap.
	 */
	private void heapify(int position) {
		int left = left(position);
		int right = right(position);
		int largest = position;
		if (left < size() && comparator.compare(heap[left], heap[position]) > 0) {
			largest = left;
		}
		if (right < size() && comparator.compare(heap[right], heap[largest]) > 0) {
			largest = right;
		}
		if (largest != position) {
			Util.swap(heap, position, largest);
			heapify(largest);
		}

	}

	@Override
	public void insert(T element) {
		// ESSE CODIGO E PARA A HEAP CRESCER SE FOR PRECISO. NAO MODIFIQUE
		if (index == heap.length - 1) {
			heap = Arrays.copyOf(heap, heap.length + INCREASING_FACTOR);
		}
		index++;
		int auxIndex = index;
		while (auxIndex > 0 && parent(auxIndex) >= 0 && comparator.compare(heap[parent(auxIndex)], element) < 0) {
			heap[auxIndex] = heap[parent(auxIndex)];
			auxIndex = parent(auxIndex);
		}
		heap[auxIndex] = element;
	}

	@Override
	public void buildHeap(T[] array) {
		heap = array;
		index = array.length - 1;
		int middle = (int) Math.floor(heap.length / 2);
		for (int i = middle; i >= 0; i--) {
			heapify(i);
		}
	}

	@Override
	public T extractRootElement() {
		if (isEmpty()) {
			return null;
		}
		T elementToReturn = heap[0];
		heap[0] = heap[index];
		heap[index--] = null;
		heapify(0);
		return elementToReturn;
	}

	@Override
	public T rootElement() {
		if (index >= 0) {
			return heap[0];
		} else {
			return null;
		}

	}

	@Override
	public T[] heapsort(T[] array) {
		if (size() > 1) {
			boolean isMax = heap[0].compareTo(heap[1]) > 0;
			System.out.println("inicio foi");
			if (isMax) {
				System.out.println("aqui");
				heap = array;
				index = array.length - 1;
				for (int i = array.length - 1; i > 0; i--) {
					
					Util.swap(array, i, 0);
					index--;
					heapify(0);
				}
			} else {
				System.out.println("TNCCCCC");
				while (!isEmpty()) {
					extractRootElement();
				}
				for (int i = 0; i < array.length; i++) {
					insertMin(array[i]);
				}
			}
		}
		return heap;
	}

	private void insertMin(T element) {
		// ESSE CODIGO E PARA A HEAP CRESCER SE FOR PRECISO. NAO MODIFIQUE
		if (index == heap.length - 1) {
			heap = Arrays.copyOf(heap, heap.length + INCREASING_FACTOR);
		}
		index++;
		int auxIndex = index;
		while (auxIndex > 0 && parent(auxIndex) >= 0 && heap[parent(auxIndex)].compareTo(element) < 0) {
			heap[auxIndex] = heap[parent(auxIndex)];
			auxIndex = parent(auxIndex);
		}
		heap[auxIndex] = element;
	}

	@Override
	public int size() {
		return index + 1;
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	public T[] getHeap() {
		return heap;
	}

}
