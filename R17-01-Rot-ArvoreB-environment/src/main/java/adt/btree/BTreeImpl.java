package adt.btree;

public class BTreeImpl<T extends Comparable<T>> implements BTree<T> {

	protected BNode<T> root;
	protected int order;

	public BTreeImpl(int order) {
		this.order = order;
		this.root = new BNode<T>(order);
	}

	@Override
	public BNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return this.root.isEmpty();
	}

	@Override
	public int height() {
		return height(this.root);
	}

	private int height(BNode<T> node) {
		int toReturn = 0;
		if (node.isLeaf()) {
			toReturn = 1;
		} else {
			toReturn = 1 + height(node.children.getFirst());
		}
		return toReturn;
	}

	@Override
	public BNode<T>[] depthLeftOrder() {
		BNode<T>[] arrayToReturn = new BNode[size()];
		depthLeftOrder(arrayToReturn, 0, root);
		return arrayToReturn;
	}

	private void depthLeftOrder(BNode<T>[] arrayToReturn, int index, BNode<T> node) {
		if (node.isLeaf()) {
		} else {
			for (BNode<T> no : node.children) {
				toReturn += size(no);
			}
		}
		return toReturn;
	}

	@Override
	public int size() {
		return size(root);
	}

	private int size(BNode<T> node) {
		int toReturn = 0;
		if (node.isLeaf()) {
		} else {
			for (BNode<T> no : node.children) {
				toReturn += size(no);
			}
		}
		return toReturn;
	}

	@Override
	public BNodePosition<T> search(T element) {
		return search(root, element);
	}

	private BNodePosition<T> search(BNode<T> x, T t) {
		int i = 0;
		BNodePosition<T> toReturn = null;
		while (i < x.size() && t.compareTo(x.getElementAt(i)) > 0) {
			i++;
		}
		if (i < x.size() && t.compareTo(x.getElementAt(i)) == 0) {
			toReturn = new BNodePosition<>(x, i);
		}
		if (x.isLeaf()) {
			toReturn = new BNodePosition<>(null, -1);
		}
		return toReturn;
	}

	@Override
	public void insert(T element) {
		// TODO Implement your code here
		throw new UnsupportedOperationException("Not Implemented yet!");

	}

	private void split(BNode<T> node) {
		// TODO Implement your code here
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	private void promote(BNode<T> node) {
		// TODO Implement your code here
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	// NAO PRECISA IMPLEMENTAR OS METODOS ABAIXO
	@Override
	public BNode<T> maximum(BNode<T> node) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	@Override
	public BNode<T> minimum(BNode<T> node) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	@Override
	public void remove(T element) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

}
