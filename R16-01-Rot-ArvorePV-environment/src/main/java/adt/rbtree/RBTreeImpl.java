package adt.rbtree;

import adt.bst.BSTImpl;
import adt.bt.Util;
import adt.rbtree.RBNode.Colour;

public class RBTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements RBTree<T> {

	public RBTreeImpl() {
		this.root = new RBNode<T>();
	}

	protected int blackHeight() {
		// TODO Implement your code here
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	protected boolean verifyProperties() {
		boolean resp = verifyNodesColour() && verifyNILNodeColour() && verifyRootColour() && verifyChildrenOfRedNodes()
				&& verifyBlackHeight();

		return resp;
	}

	/**
	 * The colour of each node of a RB tree is black or red. This is guaranteed by
	 * the type Colour.
	 */
	private boolean verifyNodesColour() {
		return true; // already implemented
	}

	/**
	 * The colour of the root must be black.
	 */
	private boolean verifyRootColour() {
		return ((RBNode<T>) root).getColour() == Colour.BLACK; // already
		// implemented
	}

	/**
	 * This is guaranteed by the constructor.
	 */
	private boolean verifyNILNodeColour() {
		return true; // already implemented
	}

	/**
	 * Verifies the property for all RED nodes: the children of a red node must be
	 * BLACK.
	 */
	private boolean verifyChildrenOfRedNodes() {
		// TODO Implement your code here
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	/**
	 * Verifies the black-height property from the root.
	 */
	private boolean verifyBlackHeight() {
		// TODO Implement your code here
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public void insert(T value) {
		// TODO Implement your code here
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public RBNode<T>[] rbPreOrder() {
		// TODO Implement your code here
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	// FIXUP methods
	protected void fixUpCase1(RBNode<T> node) {
		if (node == root) {
			node.setColour(Colour.BLACK);
		} else {
			fixUpCase2(node);
		}
	}

	protected void fixUpCase2(RBNode<T> node) {
		if (((RBNode<T>) node.getParent()).getColour() != Colour.BLACK) {
			fixUpCase3(node);
		}
	}

	protected void fixUpCase3(RBNode<T> node) {

		if (node.getParent() != getRoot() && node.getParent().getParent().getRight() != node.getParent()) {
			((RBNode<T>) node.getParent()).setColour(Colour.BLACK);
			((RBNode<T>) node.getParent().getParent().getRight()).setColour(Colour.BLACK);
			((RBNode<T>) node.getParent().getParent()).setColour(Colour.RED);
			fixUpCase1((RBNode<T>) node.getParent().getParent());
		} else if (node.getParent() != getRoot() && node.getParent().getParent().getLeft() != node.getParent()) {
			((RBNode<T>) node.getParent()).setColour(Colour.BLACK);
			((RBNode<T>) node.getParent().getParent().getLeft()).setColour(Colour.BLACK);
			((RBNode<T>) node.getParent().getParent()).setColour(Colour.RED);
		} else {
			fixUpCase4(node);
		}
	}

	protected void fixUpCase4(RBNode<T> node) {
		RBNode<T> next = node;
		RBNode<T> parent = (RBNode<T>) node.getParent();
		if (node.getParent().getRight() == node && parent.getParent().getLeft() == parent) {
			Util.leftRotation(parent);
			next = (RBNode<T>) node.getLeft();
		} else if (node.getParent().getLeft() == node && parent.getParent().getRight() == parent) {
			Util.rightRotation(parent);
		}
		fixUpCase5(node);
	}

	protected void fixUpCase5(RBNode<T> node) {
		// TODO Implement your code here
		throw new UnsupportedOperationException("Not implemented yet!");
	}
}
