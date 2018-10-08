package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {

	}

	public RecursiveSingleLinkedListImpl(T data, RecursiveSingleLinkedListImpl<T> next) {
		this.data = data;
		this.next = next;
	}

	@Override
	public boolean isEmpty() {
		return getData() == null;
	}

	@Override
	public int size() {
		int length = 0;
		if (getData() == null) {
		} else {
			length = 1 + next.size();
		}
		return length;
	}

	@Override
	public T search(T element) {
		T result;
		if(isEmpty()) {
			result = null;
		}else {
			if(getData().equals(element)) {
				result = getData();
			}else {
				result = next.search(element);
			}
		}
		return result;
	}

	@Override
	public void insert(T element) {
		if (isEmpty()) {
			data = element;
			next = new RecursiveSingleLinkedListImpl<T>();
		} else {
			next.insert(element);
		}
	}

	@Override
	public void remove(T element) {
		if(!getData().equals(element)) {
			next.remove(element);
		}else {
			data = next.getData();
			next = next.next;
		}
	}

	@Override
	public T[] toArray() {
		T[] result = (T[]) new Object[size()];
		toArray(result, 0);
		return result;
	}
	
	public void toArray(T[] array, int i) {
		if(!isEmpty()) {
			array[i] = data;
			next.toArray(array,i+1);
		}
	}
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

}
