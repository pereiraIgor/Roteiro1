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

	protected int height(BSTNode<T> node) {
		int saida = -1;
		if (!node.isEmpty()) {
			int leftHeight = height((BSTNode<T>) node.getLeft());
			int rightHeight = height((BSTNode<T>) node.getRight());
			saida = Math.max(leftHeight, rightHeight) + 1;
		}
		return saida;
	}

	@Override
	public BSTNode<T> search(T element) {
		return search(getRoot(), element);

	}

	protected BSTNode<T> search(BSTNode<T> node, T element) {
		BSTNode<T> saida = new BSTNode<T>();
		
		if (element != null) {
			if (!node.isEmpty() && !node.getData().equals(element)) {
				if (node.getData().compareTo(element) < 0) {
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

	@Override
	public void insert(T element) {
		if (element != null) {
			insert(root, element);
		}
	}

	protected void insert(BSTNode<T> node, T element) {
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

	protected BSTNode<T> maximum(BSTNode<T> node) {
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

	protected BSTNode<T> minimum(BSTNode<T> node) {
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
				while (parent != null && aux == parent.getRight()) {
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
				while (parent != null && aux == parent.getLeft()) {
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

	protected void remove(BSTNode<T> node) {
		if (!node.isEmpty()) {
			if (node.isLeaf()) {
				node.setData(null);
				node.setLeft(null);
				node.setRight(null);
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

				}
			} else {
				BSTNode<T> successor = sucessor(node.getData());
				node.setData(successor.getData());
				remove(successor);
			}
		}

	}

	protected boolean nodeHasOneChild(BSTNode<T> node) {
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

	protected void preOrder(BSTNode<T> node, ArrayList<Comparable> array) {
		if (!node.isEmpty()) {
			array.add(node.getData());
			preOrder((BSTNode<T>) node.getLeft(), array);
			preOrder((BSTNode<T>) node.getRight(), array);
		}
	}

	// left root right
	@Override
	public T[] order() {
		ArrayList<Comparable> array = new ArrayList<>();
		order(root, array);
		return (T[]) array.toArray(new Comparable[size()]);

	}

	protected void order(BSTNode<T> node, ArrayList<Comparable> array) {
		if (!node.isEmpty()) {
			order((BSTNode<T>) node.getLeft(), array);
			array.add(node.getData());
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

	protected void postOrder(BSTNode<T> node, ArrayList<Comparable> array) {
		if (!node.isEmpty()) {
			postOrder((BSTNode<T>) node.getLeft(), array);
			postOrder((BSTNode<T>) node.getRight(), array);
			array.add(node.getData());
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
