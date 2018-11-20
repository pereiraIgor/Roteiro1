package adt.skipList;

public class SkipListImpl<T> implements SkipList<T> {

	protected SkipListNode<T> root;
	protected SkipListNode<T> NIL;

	protected int maxHeight;

	protected double PROBABILITY = 0.5;

	public SkipListImpl(int maxHeight) {
		this.maxHeight = maxHeight;
		root = new SkipListNode(Integer.MIN_VALUE, maxHeight, null);
		NIL = new SkipListNode(Integer.MAX_VALUE, maxHeight, null);
		connectRootToNil();
	}

	/**
	 * Faz a ligacao inicial entre os apontadores forward do ROOT e o NIL Caso
	 * esteja-se usando o level do ROOT igual ao maxLevel esse metodo deve conectar
	 * todos os forward. Senao o ROOT eh inicializado com level=1 e o metodo deve
	 * conectar apenas o forward[0].
	 */
	private void connectRootToNil() {
		for (int i = 0; i < maxHeight; i++) {
			root.forward[i] = NIL;
		}
	}

	@Override
	public void insert(int key, T newValue, int height) {
		SkipListNode<T>[] update = root.getForward();

		SkipListNode<T> x = root.getForward(maxHeight - 1);
		for (int i = maxHeight - 1; i >= 0; i--) {
			while (x.forward[i].key < key) {
				x = x.forward[i];
			}
			update[i] = x;
		}
		x = x.forward[0];

		if (x.key == key) {
			x.setValue(newValue);
		} else {
			SkipListNode<T> newNode = new SkipListNode<T>(key, height, newValue);
			for (int i = 0; i < height; i++) {
				newNode.forward[i] = update[i].forward[i];
				update[i].forward[i] = x;
			}
		}

	}

	@Override
	public void remove(int key) {
		SkipListNode<T>[] update = root.getForward();
		SkipListNode<T> x = root.getForward(maxHeight - 1);
		for (int i = maxHeight - 1; i >= 0; i--) {
			while (x.forward[i].key < key) {
				x = x.forward[i];
			}
			update[i] = x;
		}
		x = x.forward[0];

		if (x.getKey() == key) {
			for (int i = 0; i < x.height(); i++) {
				if (update[i].forward[i] != x) {
					break;
				}
				update[i].forward[i] = x.getForward(i);
			}
		}
	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public SkipListNode<T> search(int key) {
		SkipListNode<T> x = root.getForward(maxHeight - 1);
		for (int i = maxHeight - 1; i >= 0; i--) {
			while (x.forward[i].key < key) {
				x = x.forward[i];
			}
		}
		// pega o proximo do x, pois ele sempre vai chegar no 1
		x = x.getForward(0);
		SkipListNode<T> toReturn = null;
		if (x.getKey() == key) {
			toReturn = x;
		}
		return toReturn;

	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public SkipListNode<T>[] toArray() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

}
