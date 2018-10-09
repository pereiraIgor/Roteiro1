package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.RecursiveDoubleLinkedListImpl;

public class StackRecursiveDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> top;
	protected int size;

	public StackRecursiveDoubleLinkedListImpl(int size) {
		this.size = size;
		this.top = new RecursiveDoubleLinkedListImpl<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (!isFull()) {
			top.insertFirst(element);
		} else {
			throw new StackOverflowException();
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		
		if (!isEmpty()) {
			T saida = ((RecursiveDoubleLinkedListImpl<T>) top).getData();
			top.removeFirst();
			return saida;
		} else {
			throw new StackUnderflowException();
		}
		

	}

	@Override
	public T top() {
		return ((RecursiveDoubleLinkedListImpl<T>) top).getData();
	}

	@Override
	public boolean isEmpty() {
		return top.size() == 0;
	}

	@Override
	public boolean isFull() {
		return top.size() == size;
	}

}
