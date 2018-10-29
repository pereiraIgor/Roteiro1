package adt.bst;

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
		if (isEmpty()) {
			altura = 0;
		} else {

		}
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
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public void remove(T element) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	// root left right
	@Override
	public T[] preOrder() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
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
