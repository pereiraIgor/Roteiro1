package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {
	}

	public RecursiveDoubleLinkedListImpl(T data, RecursiveSingleLinkedListImpl<T> next,
			RecursiveDoubleLinkedListImpl<T> previous) {
		super(data, next);
		this.previous = previous;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			if (isEmpty()) {
				data = element;
				setNext(new RecursiveDoubleLinkedListImpl<>(null, null, this));
				if (previous == null) {
					setPrevious(new RecursiveDoubleLinkedListImpl<T>(null, this, null));
				}
			} else {
				next.insert(element);
			}
		}
	}

	@Override
	public void remove(T element) {
		if (element != null) {
			if (isEmpty()) {
				// do nothing
			} else {
				if (getData().equals(element)) {
					if (previous.isEmpty() && next.isEmpty()) {
						setData(null);
						setNext(null);
						setPrevious(null);
					} else {
						data = next.getData();
						next = next.next;
						if (next != null) {
							((RecursiveDoubleLinkedListImpl<T>) next).previous = this;
						} else {
							setNext(new RecursiveDoubleLinkedListImpl<>());
						}
					}
				} else {
					next.remove(element);

				}
			}

		}
	}

	@Override
	public void insertFirst(T element) {
		if (element != null) {
			if (isEmpty()) {
				insert(element);
			} else {
				T elem = data;
				setData(element);
				RecursiveDoubleLinkedListImpl<T> node = new RecursiveDoubleLinkedListImpl<>(elem, next, this);
				node.previous.setNext(node);
				((RecursiveDoubleLinkedListImpl<T>) next).setPrevious(node);

			}
		}
	}

	@Override
	public void removeFirst() {
		if (!isEmpty()) {
			if (previous.isEmpty() && next.isEmpty()) {
				data = null;
				next = null;
				previous = null;
			} else {
				data = next.data;
				next = next.next;
				if (!next.isEmpty()) {
					((RecursiveDoubleLinkedListImpl<T>) next).previous = this;
				}
			}
		}
	}

	@Override
	public void removeLast() {
		if (isEmpty()) {
			previous.data = null;
			previous.next = null;
		} else {
			((RecursiveDoubleLinkedListImpl<T>) next).removeLast();
		}
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}

}
