package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

/**
 * 
 * Performs consistency validations within a AVL Tree instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements AVLTree<T> {

	// TODO Do not forget: you must override the methods insert and remove
	// conveniently.

	@Override
	public void remove(T element) {
		removeAux(search(element));
	}

	public void removeAux(BSTNode<T> node) {
		if (!node.isEmpty()) {
			if (node.isLeaf()) {
				node.setData(null);
				node.setLeft(null);
				node.setRight(null);
				rebalanceUp(node);
			} else if (haveOneChild(node)) {
				if (node != root) {
					if (node.getParent().getLeft() == node) {
						if (node.getLeft().isEmpty()) {
							node.getParent().setLeft(node.getRight());
							node.getRight().setParent(node.getParent());
						} else {
							node.getParent().setLeft(node.getLeft());
							node.getLeft().setParent(node.getParent());
						}
					} else {
						if (node.getLeft().isEmpty()) {
							node.getParent().setRight(node.getRight());
							node.getRight().setParent(node.getParent());
						} else {
							node.getParent().setRight(node.getLeft());
							node.getLeft().setParent(node.getParent());
						}
					}
				} else {
					if (root.getLeft().isEmpty())
						root = (BSTNode<T>) root.getRight();
					else
						root = (BSTNode<T>) root.getLeft();
				}
				rebalanceUp(node);
			} else {
				BSTNode<T> sucessor = sucessor(node.getData());
				node.setData(sucessor.getData());
				removeAux(sucessor);
			}
		}
	}

	@Override
	public void insert(T element) {
		insertAux(root, element);

	}

	public void insertAux(BSTNode<T> no, T element) {
		if (no.isEmpty()) {
			no.setData(element);

			no.setLeft(new BSTNode<T>());
			no.getLeft().setParent(no);

			no.setRight(new BSTNode<T>());
			no.getRight().setParent(no);
		} else {
			if (no.getData().compareTo(element) < 0)
				insertAux((BSTNode<T>) no.getRight(), element);
			else
				insertAux((BSTNode<T>) no.getLeft(), element);
			rebalance(no);
		}
	}

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		return heigthAux(-1,(BSTNode<T>) node.getLeft()) - heigthAux(-1,(BSTNode<T>) node.getRight());
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		int pesoPai = calculateBalance(node);
		if(pesoPai > 1) {//pende para o lado esquerdo, rotação para a direita
			int pesoFilhoEsquerdo = calculateBalance((BSTNode<T>) node.getLeft());
			
			if(pesoFilhoEsquerdo < 0) // caso LR
				Util.leftRotation((BSTNode<T>) node.getLeft());// caso LR
			
			
			BSTNode<T> saida = Util.rightRotation(node);//LL
			if(node == root) 
				root = saida;
			
		}else if(pesoPai < -1){// pende para a direita, rotação para a esquerda
			int pesoFilhoDireito = calculateBalance((BSTNode<T>) node.getRight());
			
			if(pesoFilhoDireito > 0) // caso RL
				Util.rightRotation((BSTNode<T>) node.getRight());
			
			
			BSTNode<T> saida = Util.leftRotation(node);//RR
			if(node == root) 
				root = saida;
		}
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		node = (BSTNode<T>) node.getParent();
		while(node != null) {
			rebalance(node);
			node = (BSTNode<T>) node.getParent();
		}
	}
}