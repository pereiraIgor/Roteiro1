package adt.bst;

import java.util.ArrayList;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		int altura = 0;
		return altura;
//		if (isEmpty()) {
//			altura = 0;
//		} else {
//
//		}
	}

	@Override
	public BSTNode<T> search(T element) {
		BSTNode<T> saida = null;
		if (element != null) {
			saida = search(root, element);
		}
		return saida;
	}

	private BSTNode<T> search(BSTNode<T> root, T element) {
		BSTNode<T> saida = null;
		if (!isEmpty() && !root.getData().equals(element)) {
			if (root.getData().compareTo(element) < 0) {
				saida = search((BSTNode<T>) root.getLeft(), element);
			} else {
				saida = search((BSTNode<T>) root.getRight(), element);
			}
		}
		return saida;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			insert(root, element);
		}
	}

	private void insert(BSTNode<T> node, T element) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode<>());
			node.setRight(new BSTNode<>());
			node.getRight().setParent(node);
			node.getLeft().setParent(node);
		} else {
			if (node.getData().compareTo(element) < 0) {
				insert((BSTNode<T>) node.getLeft(), element);
			} else {
				insert((BSTNode<T>) node.getRight(), element);
			}
		}
	}

	@Override
	public BSTNode<T> maximum() {
		return maximum(root);
	}

	private BSTNode<T> maximum(BSTNode<T> node) {

		if (node.isEmpty()) {
			return (BSTNode<T>) node.getParent();
		} else {
			return maximum((BSTNode<T>) node.getRight());
		}
	}

	@Override
	public BSTNode<T> minimum() {
		return minimum(root);
	}

	private BSTNode<T> minimum(BSTNode<T> node) {
		if (node.isLeaf()) {
			return (BSTNode<T>) node;
		} else {
			return minimum((BSTNode<T>) node.getLeft());
		}
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> aux = search(element);
		if (!aux.getRight().isEmpty()) {
			return minimum((BSTNode<T>) aux.getRight());
		}
		BSTNode<T> y = (BSTNode<T>) aux.getParent();
		while (!y.isEmpty() && aux.equals((BSTNode<T>) y.getRight())) {
			aux = y;
			y = (BSTNode<T>) y.getParent();
		}
		return y;

	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> aux = search(element);
		if (!aux.getLeft().isEmpty()) {
			return maximum((BSTNode<T>) aux.getLeft());
		}
		BSTNode<T> y = (BSTNode<T>) aux.getParent();
		while (!y.isEmpty() && aux.equals((BSTNode<T>) y.getLeft())) {
			aux = y;
			y = (BSTNode<T>) y.getParent();
		}
		return y;
	}

	@Override
	public void remove(T element) {
		BSTNode<T> node = search(element);
		if (!node.isEmpty()) {
			if (node.isLeaf()) {
				node.setData(null);
				node.setLeft(null);
				node.setRight(null);
			} else if (nodeHasOneChild(node) == 1 || nodeHasOneChild(node) == 2) {
				if (!node.equals(root)) {
					if (nodeHasOneChild((BSTNode<T>) node.getParent()) == 2) {
						if (!node.getLeft().isEmpty()) {
							node.getParent().setLeft(node.getLeft());
						} else {
							node.getParent().setLeft(node.getRight());
						}

						// node is tight
					} else {
						if (!node.getLeft().isEmpty()) {
							node.getParent().setRight(node.getLeft());
						} else {
							node.getParent().setRight(node.getRight());
						}
					}
				} else {
					if (nodeHasOneChild(node) == 1) {
						root = (BSTNode<T>) root.getRight();
					} else {
						root = (BSTNode<T>) root.getLeft();
					}

				}
			} else {
				BSTNode<T> successor = sucessor(node.getData());
				node.setData(successor.getData());
				remove(successor.getData());
			}
		}
	}
	private int nodeHasOneChild(BSTNode<T> node) {
		// se for 1 é o da direita e se for 2 é o da esquerda
		int valor = 0;
		if (node.getLeft().getData() != null && node.getRight().getData() == null) {
			valor = 2;
		} else if (node.getLeft().getData() == null && node.getRight().getData() != null) {
			valor = 1;
		} else if (node.getLeft().getData() != null && node.getRight().getData() != null) {
			valor = 0;
		} else {
			valor = 3;
		}
		return valor;
	}

	// root left right
	@Override
	public T[] preOrder() {
		ArrayList<Comparable> array = new ArrayList<>();
		preOrder(root,array);
		return (T[]) array.toArray();
	}

	private void preOrder(BSTNode<T> node, ArrayList<Comparable> array) {
		if (!node.isEmpty()) {
			array.add(visit(node));
			preOrder((BSTNode<T>) node.getLeft(),array);
			preOrder((BSTNode<T>) node.getRight(),array);
		}
	}

	private T visit(BSTNode<T> node) {
		return node.getData();
	}

	// left root right
	@Override
	public T[] order() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	// left right root
	@Override
	public T[] postOrder() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	/**
	 * This method is already implemented using recursion. You must understand how
	 * it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft()) + size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
