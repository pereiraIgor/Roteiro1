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
		int toReturn = -1;
		if (!isEmpty())
			toReturn = height(root);
		return toReturn;
	}

	private int height(BNode<T> node) {
		int toReturn = 0;
		if (!node.isLeaf()) {
			toReturn = 1 + height(node.children.getFirst());
		}

		return toReturn;
	}

	@Override
	public BNode<T>[] depthLeftOrder() {
		BNode<T>[] toReturn = new BNode[numberOfNodes(this.getRoot()) + 1];
		if (!this.isEmpty()) {
			depthLeftOrder(toReturn, 0, this.getRoot());
		}
		return toReturn;
	}

	private int depthLeftOrder(BNode<T>[] arrayToReturn, int index, BNode<T> node) {
		arrayToReturn[index] = node;
		index++;
		for (int i = 0; i < node.children.size(); i++) {
			index = depthLeftOrder(arrayToReturn, index, node.children.get(i));
		}
		return index;

	}

	private int numberOfNodes(BNode<T> node) {
		int nodes = node.children.size();
		for (int i = 0; i < node.children.size(); i++) {
			nodes += this.numberOfNodes(node.children.get(i));
		}
		return nodes;
	}

	@Override
	public int size() {
		return size(root);
	}

	private int size(BNode<T> node) {
		int toReturn = node.getElements().size();
		for (int i = 0; i < node.children.size(); i++) {
			toReturn += size(node.children.get(i));
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
		insert(root, element);

	}

	private void insert(BNode<T> node, T element) {
		int i = 0;
		while (i < node.elements.size() && element.compareTo(node.elements.get(i)) > 0) {
			i++;
		}
		if (node.isLeaf()) {
			node.addElement(element);
			if (node.size() > node.getMaxKeys()) {
				node.split();

				while (node.parent != null) {
					node = node.parent;
				}

				this.root = node;
			}
		} else {
			this.insert(node.children.get(i), element);
		}
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
