package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionOpenAddress;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable> extends AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressQuadraticProbingImpl(int size, HashFunctionClosedAddressMethod method, int c1, int c2) {
		super(size);
		hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		int probe = 0;
		int index = ((HashFunctionOpenAddress<T>) hashFunction).hash(element, probe);
		while (table[index] != null && probe < table.length) {
			index = ((HashFunctionOpenAddress<T>) hashFunction).hash(element, ++probe);
			COLLISIONS++;
		}
		if (probe < table.length) {
			table[index] = element;
			elements++;
		}
	}

	@Override
	public void remove(T element) {
		int probe = 0;
		int index = ((HashFunctionOpenAddress<T>) hashFunction).hash(element, probe);
		while(table[index] != null && !table[index].equals(element) && probe < table.length) {
			index = ((HashFunctionOpenAddress<T>) hashFunction).hash(element, ++probe);
		}
	}

	@Override
	public T search(T element) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public int indexOf(T element) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}
}
