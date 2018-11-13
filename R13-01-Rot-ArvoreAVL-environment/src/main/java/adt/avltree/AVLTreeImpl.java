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

	protected void insert(BSTNode<T> node, T element) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode<T>());
			node.setRight(new BSTNode<T>());
			node.getLeft().setParent(node);
			node.getRight().setParent(node);
		} else {
			if (node.getData().compareTo(element) < 0) {
				insert((BSTNode<T>) node.getRight(), element);
			} else if (node.getData().compareTo(element) > 0) {
				insert((BSTNode<T>) node.getLeft(), element);
			}
			rebalance(node);
		}
	}

	protected void remove(BSTNode<T> node) {
		if (!node.isEmpty()) {
			if (node.isLeaf()) {
				node.setData(null);
				node.setLeft(null);
				node.setRight(null);
				rebalanceUp(node);
			} else if (nodeHasOneChild(node)) {
				if (node != root) {
					if (node.getParent().getLeft() == node) {
						if (!node.getLeft().isEmpty()) {
							node.getParent().setLeft(node.getLeft());
							node.getLeft().setParent(node.getParent());
						} else {
							node.getParent().setLeft(node.getRight());
							node.getRight().setParent(node.getParent());
						}
					} else {
						if (!node.getLeft().isEmpty()) {
							node.getParent().setRight(node.getLeft());
							node.getLeft().setParent(node.getParent());
						} else {
							node.getParent().setRight(node.getRight());
							node.getRight().setParent(node.getParent());
						}
					}
				} else {
					if (!node.getRight().isEmpty()) {
						root = (BSTNode<T>) root.getRight();

					} else {
						root = (BSTNode<T>) root.getLeft();
					}
					rebalanceUp(node);
				}
			} else {
				BSTNode<T> successor = sucessor(node.getData());
				node.setData(successor.getData());
				remove(successor);
			}
		}

	}

	// AUXILIARY

	// < 0 pesa para o lado direito
	// == 0 pesa pra porra nenhuma
	// > 0 pesa para o lçado esquerdo

	protected int calculateBalance(BSTNode<T> node) {
		int toReturn = 0;
		if (node != new BSTNode<>()) {
			toReturn = height((BSTNode<T>) node.getLeft()) - height((BSTNode<T>) node.getRight());
		}
		return toReturn;

	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		int balance = calculateBalance(node);
		BSTNode<T> testa = null;
		if (Math.abs(balance) > 1) {
			if (balance < -1) {
				int newBalance = calculateBalance((BSTNode<T>) node.getRight());
				if (newBalance <= 0) {
					testa = Util.leftRotation(node);
				} else {
					Util.rightRotation((BSTNode<T>) node.getRight());
					testa =Util.leftRotation(node);
				}
				if (node == root) {
					root = testa;
				}
			} else {
				int newBalance = calculateBalance((BSTNode<T>) node.getLeft());
				if (newBalance >= 0) {
					testa = Util.rightRotation(node);
				} else {
					Util.leftRotation((BSTNode<T>) node.getLeft());
					testa = Util.rightRotation(node);
				}
				if (node == root) {
					root = testa;
				}
			}
		}
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		BSTNode<T> parent = (BSTNode<T>) node.getParent();
		while (parent != null) {
			rebalance(parent);
			parent = (BSTNode<T>) parent.getParent();
		}
	}
}
