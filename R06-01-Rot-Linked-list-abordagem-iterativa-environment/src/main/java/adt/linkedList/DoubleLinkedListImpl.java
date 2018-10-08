package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;

	public DoubleLinkedListImpl() {
		head = new DoubleLinkedListNode<>();
		last = (DoubleLinkedListNode<T>) head;
	}

	@Override
	public T search(T element) {
		DoubleLinkedListNode<T> auxHead = (DoubleLinkedListNode<T>) head;
		DoubleLinkedListNode<T> auxLast = last;
		while (auxHead != auxLast && auxHead.getNext() != auxLast && !auxHead.getData().equals(element)
				&& !auxLast.getData().equals(element)) {
			auxHead = (DoubleLinkedListNode<T>) auxHead.getNext();
			auxLast = auxLast.getPrevious();
		}
		T saida = null;
		if (auxHead.getData().equals(element)) {
			saida = auxHead.getData();
		}
		if (auxLast.getData().equals(element)) {
			saida = auxLast.getData();
		}
		return saida;
	}

	@Override
	public void insert(T element) {
		DoubleLinkedListNode<T> newLast = new DoubleLinkedListNode<>();
		newLast.setData(element);
		newLast.setPrevious(last);
		newLast.setNext(new DoubleLinkedListNode<>());
		last.setNext(newLast);

		if (last.isNIL()) {
			head = newLast;
		}
		last = newLast;

	}

	@Override
	public void remove(T element) {
		if (!isEmpty()) {
			if (((DoubleLinkedListNode<T>) head).getData().equals(element)) {
				removeFirst();
			} else {
				DoubleLinkedListNode<T> aux = (DoubleLinkedListNode<T>) head;
				while (!aux.isNIL() && !aux.getData().equals(element)) {
					aux = (DoubleLinkedListNode<T>) aux.getNext();
				}
				if (!aux.isNIL()) {
					aux.getPrevious().setNext(aux.getNext());
					((DoubleLinkedListNode<T>) aux.getNext()).setPrevious(aux.getPrevious());
				}
			}
		} else {
			return;
		}

	}

	@Override
	public void insertFirst(T element) {
		DoubleLinkedListNode<T> newHead = new DoubleLinkedListNode<>();
		newHead.setData(element);
		newHead.setNext((DoubleLinkedListNode<T>) head);
		newHead.setPrevious(new DoubleLinkedListNode<>());
		newHead.getPrevious().setNext(head);
		((DoubleLinkedListNode<T>) head).setPrevious(newHead);

		if (((DoubleLinkedListNode<T>) head).isNIL()) {
			last = newHead;
		}
		head = newHead;

	}

	@Override
	public void removeFirst() {
		if (!isEmpty()) {
			if (!((DoubleLinkedListNode<T>) head).isNIL()) {
				head = ((DoubleLinkedListNode<T>) head).getNext();
			}
			if (((DoubleLinkedListNode<T>) head).isNIL()) {
				last = (DoubleLinkedListNode<T>) head;
			}
			((DoubleLinkedListNode<T>) head).setPrevious(new DoubleLinkedListNode<>());
			((DoubleLinkedListNode<T>) head).getPrevious().setNext(head);
		}else {
			return;
		}

	}

	@Override
	public void removeLast() {
		if (!isEmpty()) {

			if (!last.isNIL()) {
				last = last.getPrevious();
			}
			if (last.isNIL()) {
				head = last;
			}
			last.next = new DoubleLinkedListNode<>();
			((DoubleLinkedListNode<T>) last.getNext()).setPrevious(last);
		}else {
			return;
		}
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

}
