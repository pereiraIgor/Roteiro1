package adt.avltree;

import adt.bst.BSTNode;
import adt.bt.Util;

public class AVLCountAndFillImpl<T extends Comparable<T>> extends AVLTreeImpl<T> implements AVLCountAndFill<T> {

	private int LLcounter;
	private int LRcounter;
	private int RRcounter;
	private int RLcounter;

	public AVLCountAndFillImpl() {

	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		int balance = calculateBalance(node);
		if (Math.abs(balance) > 1) {
			String testa = calcOfRotacion(node);
			switch (testa) {
			case "RR":
				
				Util.leftRotation(node);
				break;
			case "LL":
//				LLcounter++;
				Util.rightRotation(node);
				break;
			case "RL":
//				RLcounter++;
				Util.rightRotation((BSTNode<T>) node.getRight());
				Util.leftRotation(node);
				break;
			case "LR":
				//LRcounter++;
				Util.leftRotation((BSTNode<T>) node.getLeft());
				Util.rightRotation(node);
				break;

			}
		}
	}

	@Override
	public int LLcount() {
		return LLcounter;
	}

	@Override
	public int LRcount() {
		return LRcounter;
	}

	@Override
	public int RRcount() {
		return RRcounter;
	}

	@Override
	public int RLcount() {
		return RLcounter;
	}

	@Override
	public void fillWithoutRebalance(T[] array) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

}
