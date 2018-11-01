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
		return height(root);

	}

	private int height(BSTNode<T> node) {
		int saida = -1;
		if (!node.isEmpty()) {
			saida = Math.max(height((BSTNode<T>) node.getLeft()), height((BSTNode<T>) node.getRight())) + 1;
		}
		return saida;
	}

	@Override
	public BSTNode<T> search(T element) {
		return search(getRoot(), element);

	}

	private BSTNode<T> search(BSTNode<T> node, T element) {
		BSTNode<T> saida = new BSTNode<T>();
		if (element != null) {
			if (!node.isEmpty() && !node.getData().equals(element)) {
				if (node.getData().compareTo(element) < 0) {
					saida = search((BSTNode<T>) node.getRight(), element);
				} else {
					saida = search((BSTNode<T>) node.getLeft(), element);
				}
			} else if (!node.isEmpty() && node.getData().equals(element)) {
				saida = node;
			}
		}
		return saida;
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
			if (node.getData().compareTo(element) < 0) {
				insert((BSTNode<T>) node.getRight(), element);
			} else if (node.getData().compareTo(element) > 0) {
				insert((BSTNode<T>) node.getLeft(), element);
			}
		}
	}

	@Override
	public BSTNode<T> maximum() {
		return maximum(root);
	}

	private BSTNode<T> maximum(BSTNode<T> node) {
		BSTNode<T> nodeAux = node;
		if (!nodeAux.isEmpty()) {
			while (!nodeAux.getRight().isEmpty()) {
				nodeAux = (BSTNode<T>) nodeAux.getRight();
			}
		} else {
			nodeAux = null;
		}
		return nodeAux;
	}

	@Override
	public BSTNode<T> minimum() {
		return minimum(root);
	}

	private BSTNode<T> minimum(BSTNode<T> node) {
		BSTNode<T> nodeAux = node;
		if (!nodeAux.isEmpty()) {
			while (!nodeAux.getLeft().isEmpty()) {
				nodeAux = (BSTNode<T>) nodeAux.getLeft();
			}
		} else {
			nodeAux = null;
		}
		return nodeAux;
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> aux = search(element);
		if (!aux.isEmpty()) {
			if (!aux.getRight().isEmpty()) {
				aux = minimum((BSTNode<T>) aux.getRight());
			} else {
				BSTNode<T> parent = (BSTNode<T>) aux.getParent();
				while (parent != null && aux.equals((BSTNode<T>) parent.getRight())) {
					aux = parent;
					parent = (BSTNode<T>) parent.getParent();
				}
				aux = parent;
			}

		} else {
			aux = null;
		}
		return aux;
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> aux = search(element);
		if (!aux.isEmpty()) {
			if (!aux.getLeft().isEmpty()) {
				aux = maximum((BSTNode<T>) aux.getLeft());
			} else {
				BSTNode<T> parent = (BSTNode<T>) aux.getParent();
				while (parent != null && aux.equals((BSTNode<T>) parent.getLeft())) {
					aux = parent;
					parent = (BSTNode<T>) parent.getParent();
				}
				aux = parent;
			}
		} else {
			aux = null;
		}

		return aux;
	}

	@Override
	public void remove(T element) {
		remove(search(element));

	}

	private void remove(BSTNode<T> node) {
		if (!node.isEmpty()) {
			if (node.isLeaf()) {
				node.setData(null);
				node.setLeft(null);
				node.setRight(null);
			} else if (nodeHasOneChild(node)) {
				if (!node.equals(getRoot())) {
					if (node.getParent().getLeft().equals(node)) {
						if (!node.getLeft().isEmpty()) {
							node.getParent().setLeft(node.getLeft());
							node.getLeft().setParent(node.getParent());
						} else {
							node.getParent().setLeft(node.getRight());
							node.getRight().setParent(node.getParent());
						}
					} else if (node.getParent().getRight().equals(node)) {
						if (!node.getLeft().isEmpty()) {
							node.getParent().setRight(node.getLeft());
							node.getLeft().setParent(node.getParent());
						} else {
							node.getParent().setRight(node.getRight());
							node.getParent().setParent(node.getParent());
						}
					}
				} else {
					if (!node.getRight().isEmpty()) {
						root = (BSTNode<T>) root.getRight();
					} else {
						root = (BSTNode<T>) root.getLeft();
					}

				}
			} else {
				BSTNode<T> successor = sucessor(node.getData());
				remove(successor);
				node.setData(successor.getData());
			}
		}

	}

	private boolean nodeHasOneChild(BSTNode<T> node) {
		boolean returno = false;
		if (!node.getLeft().isEmpty() && node.getRight().isEmpty()) {
			returno = true;
		} else if (node.getLeft().isEmpty() && !node.getRight().isEmpty()) {
			returno = true;
		}

		return returno;
	}

	// root left right
	@Override
	public T[] preOrder() {
		ArrayList<Comparable> array = new ArrayList<>();
		preOrder(root, array);
		return (T[]) array.toArray(new Comparable[size()]);
	}

	private void preOrder(BSTNode<T> node, ArrayList<Comparable> array) {
		if (!node.isEmpty()) {
			array.add(visit(node));
			preOrder((BSTNode<T>) node.getLeft(), array);
			preOrder((BSTNode<T>) node.getRight(), array);
		}
	}

	private T visit(BSTNode<T> node) {
		return node.getData();
	}

	// left root right
	@Override
	public T[] order() {
		ArrayList<Comparable> array = new ArrayList<>();
		order(root, array);
		return (T[]) array.toArray(new Comparable[size()]);

	}

	private void order(BSTNode<T> node, ArrayList<Comparable> array) {
		if (!node.isEmpty()) {
			order((BSTNode<T>) node.getLeft(), array);
			array.add(visit(node));
			order((BSTNode<T>) node.getRight(), array);
		}
	}

	// left right root
	@Override
	public T[] postOrder() {
		ArrayList<Comparable> array = new ArrayList<>();
		postOrder(root, array);
		return (T[]) array.toArray(new Comparable[size()]);
	}

	public void postOrder(BSTNode<T> node, ArrayList<Comparable> array) {
		if (!node.isEmpty()) {
			order((BSTNode<T>) node.getLeft(), array);
			order((BSTNode<T>) node.getRight(), array);
			array.add(visit(node));
		}

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
