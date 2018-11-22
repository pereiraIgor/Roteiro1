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
		BSTNode<T> pivot = (BSTNode<T>) node.getRight();
		pivot.setParent((BSTNode<T>) node.getParent());
		if (node.getParent() != null) {
			if (node.getParent().getLeft() == node) {
				node.getParent().setLeft(pivot);
			} else {
				node.getParent().setRight(pivot);
			}
		}

		node.setRight((BSTNode<T>) pivot.getLeft());
		pivot.getLeft().setParent((BSTNode<T>) node);
		pivot.setLeft((BSTNode<T>) node);
		node.setParent((BSTNode<T>) pivot);
		return pivot;
	}

	/**
	 * A rotacao a direita em node deve subir e retornar seu filho a esquerda
	 * 
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> rightRotation(BSTNode<T> node) {
		BSTNode<T> pivot = (BSTNode<T>) node.getLeft();
		pivot.setParent((BSTNode<T>) node.getParent());
		if (node.getParent() != null) {
			if (node.getParent().getLeft() == node) {
				node.getParent().setLeft(pivot);
			} else {
				node.getParent().setRight(pivot);
			}
		}
		node.setLeft((BSTNode<T>) pivot.getRight());
		pivot.getRight().setParent((BSTNode<T>) node);
		pivot.setRight((BSTNode<T>) node);
		node.setParent((BSTNode<T>) pivot);
		node = pivot;
		return pivot;
	}

	public static <T extends Comparable<T>> T[] makeArrayOfComparable(int size) {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[size];
		return array;
	}
}
