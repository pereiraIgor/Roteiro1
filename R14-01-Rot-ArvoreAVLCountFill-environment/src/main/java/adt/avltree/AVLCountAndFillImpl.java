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

	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		int pesoPai = calculateBalance(node);
		if (pesoPai > 1) {// pende para o lado esquerdo, rotação para a direita
			int pesoFilhoEsquerdo = calculateBalance((BSTNode<T>) node.getLeft());
			if (pesoFilhoEsquerdo < 0) {
				Util.leftRotation((BSTNode<T>) node.getLeft());// caso LR
				LRcounter++;
			} else {
				LLcounter++;
			}
			BSTNode<T> saida = Util.rightRotation(node);// LL
			if (node == root)
				root = saida;

		} else if (pesoPai < -1) {// pende para a direita, rotação para a esquerda
			int pesoFilhoDireito = calculateBalance((BSTNode<T>) node.getRight());

			if (pesoFilhoDireito > 0) { // caso RL
				Util.rightRotation((BSTNode<T>) node.getRight());
				RLcounter++;
			} else {
				RRcounter++;
			}
			BSTNode<T> saida = Util.leftRotation(node);// RR
			if (node == root)
				root = saida;
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
		int fator = array.length / 2;
		while (fator > 0) {
			
			while (fator < array.length) {
				insert(array[fator]);
				fator += fator;
			}
			
			fator = fator / 2;
		}

	}

}
