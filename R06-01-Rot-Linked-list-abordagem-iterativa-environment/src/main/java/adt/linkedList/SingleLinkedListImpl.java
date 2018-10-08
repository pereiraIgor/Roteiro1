package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		return head.isNIL();
	}

	@Override
	public int size() {
		int size = 0;
		SingleLinkedListNode<T> aux = head;
		while (!aux.isNIL()) {
			size += 1;
			aux = aux.getNext();
		}
		return size;
	}

	@Override
	public T search(T element) {
		SingleLinkedListNode<T> x = head;
		while (!x.isNIL() && x.getData() != element) {
			x = x.getNext();
		}
		return x.getData();
	}

	@Override
	public void insert(T element) {
		SingleLinkedListNode<T> auxHead = head;
		if (head.isNIL()) {
			SingleLinkedListNode<T> newHead = new SingleLinkedListNode<>(element, head);
			head = newHead;
		} else {
			while (!auxHead.getNext().isNIL()) {
				auxHead = auxHead.getNext();
			}
			SingleLinkedListNode<T> nil = new SingleLinkedListNode<>();
			SingleLinkedListNode<T> newNode = new SingleLinkedListNode<>(element, nil);
			auxHead.setNext(newNode);
		}
	}

	@Override
	public void remove(T element) {
		if(!isEmpty()) {
			SingleLinkedListNode<T> previus = new SingleLinkedListNode<>();
			if (head.getData() == element) {
				head = head.getNext();
			} else {
				SingleLinkedListNode<T> aux = head;
				while (!aux.isNIL() && aux.getData() != element) {
					previus = aux;
					aux = aux.next;
				}
				if (!aux.isNIL()) {
					previus.next = aux.next;
				}
			}
		}
		else {
			return;
		}
	}

	@Override
	public T[] toArray() {
		T[] result = (T[]) new Object[size()];
		SingleLinkedListNode<T> aux = head;
		int count = 0;
		while (!aux.isNIL()) {
			result[count] = aux.data;
			aux = aux.next;
			count++;
		}
		return result;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
