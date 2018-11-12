package adt.bt;

import adt.bst.BSTNode;

public class Util {

	/**
	 * A rotacao a esquerda em node deve subir e retornar seu filho a direita
	 * 
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> leftRotation(BSTNode<T> node) {
		BSTNode<T> pivot = null;

		pivot = (BSTNode<T>) node.getRight();

		node.setRight(pivot.getLeft());
		pivot.getLeft().setParent((BSTNode<T>) node);

		pivot.setLeft(node);
		node.setParent(pivot);

		pivot.setParent(node.getParent());

		node = pivot;
		return pivot;
	}

	/**
	 * A rotacao a direita em node deve subir e retornar seu filho a esquerda
	 * 
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> rightRotation(BSTNode<T> node) {
		BSTNode<T> pivot = null;
		pivot = (BSTNode<T>) node.getLeft();

		node.setLeft(pivot.getRight());
		pivot.getRight().setParent(node);

		pivot.setRight(node);
		node.setParent(pivot);

		pivot.setParent(node.getParent());

		node = pivot;
		return pivot;
	}

	public static <T extends Comparable<T>> T[] makeArrayOfComparable(int size) {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[size];
		return array;
	}
}