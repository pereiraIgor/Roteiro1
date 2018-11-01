package adt.bst.extended;

import java.util.ArrayList;
import java.util.Comparator;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * Implementacao de SortComparatorBST, uma BST que usa um comparator interno em
 * suas funcionalidades e possui um metodo de ordenar um array dado como
 * parametro, retornando o resultado do percurso desejado que produz o array
 * ordenado.
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class SortComparatorBSTImpl<T extends Comparable<T>> extends BSTImpl<T> implements SortComparatorBST<T> {

	private Comparator<T> comparator;

	public SortComparatorBSTImpl(Comparator<T> comparator) {
		super();
		this.comparator = comparator;
	}

	@Override
	public T[] sort(T[] array) {
		while (!root.isEmpty()) {
			remove(root.getData());
		}
		for (T i : array) {
			insert(i);
		}
		return order();

	}

	@Override
	public T[] reverseOrder() {
		ArrayList<Comparable> array = new ArrayList<>();
		reverseOrder(root, array);
		return (T[]) array.toArray(new Comparable[size()]);
	}

	private void reverseOrder(BSTNode<T> node, ArrayList<Comparable> array) {
		if (!node.isEmpty()) {
			reverseOrder((BSTNode<T>) node.getRight(), array);
			array.add(node.getData());
			reverseOrder((BSTNode<T>) node.getLeft(), array);
		}
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			insert(getRoot(), element);
		}
	}

	private void insert(BSTNode<T> node, T element) {
		if (node.isEmpty()) {
			node.setData(element);

			node.setLeft(new BSTNode<T>());
			node.getLeft().setParent(node);

			node.setRight(new BSTNode<T>());
			node.getRight().setParent(node);

		} else {

			if (comparator.compare(node.getData(), element) < 0) {
				insert((BSTNode<T>) node.getRight(), element);
			} else if (node.getData().compareTo(element) > 0) {
				insert((BSTNode<T>) node.getLeft(), element);
			}
		}
	}

	@Override
	public BSTNode<T> search(T element) {
		return search(getRoot(), element);
	}

	private BSTNode<T> search(BSTNode<T> node, T element) {
		BSTNode<T> saida = new BSTNode<T>();
		if (element != null) {
			if (!node.isEmpty() && !node.getData().equals(element)) {
				if (comparator.compare(node.getData(), element) < 0) {
					saida = search((BSTNode<T>) node.getRight(), element);
				} else {
					saida = search((BSTNode<T>) node.getLeft(), element);
				}
			} else {
				saida = node;
			}
		}
		return saida;
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}

}
