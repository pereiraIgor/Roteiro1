package adt.avltree;

import java.util.Arrays;

import adt.bst.BSTNode;
import adt.bt.Util;

public class AVLCountAndFillImpl<T extends Comparable<T>> extends AVLTreeImpl<T> implements AVLCountAndFill<T> {

	private int LLcounter;
	private int LRcounter;
	private int RRcounter;
	private int RLcounter;

	public AVLCountAndFillImpl() {
		super();
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		int balance = calculateBalance(node);
		BSTNode<T> testa = null;
		if (Math.abs(balance) > 1) {
			if (balance < -1) {
				int newBalance = calculateBalance((BSTNode<T>) node.getRight());
				if (newBalance <= 0) {
					RRcounter++;
					testa = Util.leftRotation(node);
				} else {
					RLcounter++;
					Util.rightRotation((BSTNode<T>) node.getRight());
					testa = Util.leftRotation(node);
				}
				if (node == root) {
					root = testa;
				}
			} else {
				int newBalance = calculateBalance((BSTNode<T>) node.getLeft());
				if (newBalance >= 0) {
					LLcounter++;
					testa = Util.rightRotation(node);
				} else {
					LRcounter++;
					Util.leftRotation((BSTNode<T>) node.getLeft());
					testa = Util.rightRotation(node);
				}
				if (node == root) {
					root = testa;
				}
			}
		}
	}

	@Override
	public int LLcount() {
		return LLcounter;
	}

	@Override
	public int LRcount() {
		return LRcounter;
	}

	@Override
	public int RRcount() {
		return RRcounter;
	}

	@Override
	public int RLcount() {
		return RLcounter;
	}

	@Override
	public void fillWithoutRebalance(T[] array) {
		Arrays.sort(array);
		T[] arrayAux = (T[]) new Comparable[array.length];
		int metade = array.length / 2;
		int addInArrayAux = 0;
		arrayAux[addInArrayAux] = array[(metade)];
		fillWithoutRebalanceArray(array, arrayAux, 0, metade - 1, ++addInArrayAux);
		fillWithoutRebalanceArray(array, arrayAux, metade + 1, array.length - 1, ++addInArrayAux);
		System.out.println(Arrays.toString(arrayAux));
		for (int i = 0; i < arrayAux.length; i++) {
			insert(arrayAux[i]);
		}
	}

	public void fillWithoutRebalanceArray(T[] array, T[] arrayAux, int left, int right, int addInArrayAux) {
		if (left <= right) {
			int middle = (left + right) / 2;
			arrayAux[addInArrayAux++] = array[middle];
			fillWithoutRebalanceArray(array, arrayAux, left, middle - 1, addInArrayAux);
			fillWithoutRebalanceArray(array, arrayAux, middle + 1, right, addInArrayAux);
		}

	}

}
