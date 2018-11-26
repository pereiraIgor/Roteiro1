package adt.rbtree;

import java.util.ArrayList;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;
import adt.rbtree.RBNode.Colour;

public class RBTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements RBTree<T> {

	public RBTreeImpl() {
		this.root = new RBNode<T>();
	}

	protected int blackHeight() {
		return blackHeight((RBNode<T>) root);
	}

	protected int blackHeight(RBNode<T> node) {
		int toReturn = 0;
		if (!node.isEmpty()) {
			if (node.getColour() == Colour.BLACK) {
				toReturn += 1;
			}
			int leftHeight = blackHeight((RBNode<T>) node.getLeft());
			int rightHeight = blackHeight((RBNode<T>) node.getRight());
			toReturn = Math.max(leftHeight, rightHeight);
		}
		return toReturn;
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
		return verifyChildrenOfRedNodes((RBNode<T>) root);
	}

	private boolean verifyChildrenOfRedNodes(RBNode<T> node) {
		boolean toReturn = true;
		if (!node.isEmpty()) {
			RBNode<T> leftSon = (RBNode<T>) node.getLeft();
			RBNode<T> rightSon = (RBNode<T>) node.getRight();
			if (node.getColour() == Colour.RED) {
				if (leftSon.getColour() == Colour.RED || rightSon.getColour() == Colour.RED) {
					toReturn = false;
				}
			} else {

				boolean leftVerify = verifyChildrenOfRedNodes(leftSon);
				boolean rightVerify = verifyChildrenOfRedNodes(rightSon);
				if (!toReturn) {
					toReturn = leftVerify && rightVerify;
				}
			}
		}
		return toReturn;
	}

	/**
	 * Verifies the black-height property from the root.
	 */
	private boolean verifyBlackHeight() {
		boolean toReturn = true;
		if (blackHeight((RBNode<T>) root.getLeft()) == blackHeight((RBNode<T>) root.getRight())) {
			toReturn = true;
		} else {
			toReturn = false;
		}
		return toReturn;
	}

	@Override
	public void insert(T value) {
		insert(root, value);
	}

	protected void insert(RBNode<T> node, T element) {
		if (node.isEmpty()) {
			node.setData(element);

			node.setLeft(new BSTNode<T>());
			node.getLeft().setParent(node);

			node.setRight(new BSTNode<T>());
			node.getRight().setParent(node);
			node.setColour(Colour.RED);
			fixUpCase1(node);
		} else {
			if (node.getData().compareTo(element) < 0) {
				insert((RBNode<T>) node.getRight(), element);
			} else if (node.getData().compareTo(element) > 0) {
				insert((RBNode<T>) node.getLeft(), element);
			}
		}
	}

	@Override
	public RBNode<T>[] rbPreOrder() {
		RBNode<T>[] array = new RBNode[size()];
		rbPreOrder((RBNode<T>)root, array,0);
		return array;
	}

	protected void rbPreOrder(RBNode<T> node, RBNode<T>[] array, int auxIndex) {
		if (!node.isEmpty()) {
			array[auxIndex] = node;
			rbPreOrder((RBNode<T>) node.getLeft(), array,2 *auxIndex +1);
			rbPreOrder((RBNode<T>) node.getRight(), array,2 *auxIndex +2);
		}
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
			next = (RBNode<T>) node.getRight();
		}
		fixUpCase5(node);
	}

	protected void fixUpCase5(RBNode<T> node) {
		((RBNode<T>) node.getParent()).setColour(Colour.BLACK);
		((RBNode<T>) node.getParent().getParent()).setColour(Colour.RED);
		if (node.getParent().getLeft() == node) {
			Util.rightRotation((RBNode<T>) node.getParent().getParent());
		} else {
			Util.leftRotation((RBNode<T>) node.getParent().getParent());
		}
	}
}
