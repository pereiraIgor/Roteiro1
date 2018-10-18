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
		if (element != null) {
			int probe = 0;
			int index = ((HashFunctionOpenAddress<T>) hashFunction).hash(element, probe);
			while (table[index] != null && probe < table.length) {
				index = ((HashFunctionOpenAddress<T>) hashFunction).hash(element, ++probe);
				COLLISIONS++;
			}
			if (table[index] == null) {
				table[index] = element;
				elements++;
			}
		}
	}

	@Override
	public void remove(T element) {
		if (element != null) {
			int probe = 0;
			int index = ((HashFunctionOpenAddress<T>) hashFunction).hash(element, probe);
			while (table[index] != null && !table[index].equals(element) && probe < table.length) {
				index = ((HashFunctionOpenAddress<T>) hashFunction).hash(element, ++probe);
			}
			if (table[index] != null && table[index].equals(element) && probe < table.length) {
				table[index] = deletedElement;
				elements--;
			}
		}
	}

	@Override
	public T search(T element) {
		T retorno = null;
		if (element != null) {
			int probe = 0;
			int index = ((HashFunctionOpenAddress<T>) hashFunction).hash(element, probe);
			while (table[index] != null && !table[index].equals(element) && probe < table.length) {
				index = ((HashFunctionOpenAddress<T>) hashFunction).hash(element, ++probe);
			}
			if (table[index] != null && table[index].equals(element) && probe < table.length) {
				retorno = (T) table[index];
			}
		}
		return retorno;
	}

	@Override
	public int indexOf(T element) {
		int indice = -1;
		if (element != null) {
			int probe = 0;
			int index = ((HashFunctionOpenAddress<T>) hashFunction).hash(element, probe);
			while (table[index] != null && !table[index].equals(element) && probe < table.length) {
				index = ((HashFunctionOpenAddress<T>) hashFunction).hash(element, ++probe);
			}
			if (table[index] != null && table[index].equals(element) && probe < table.length) {
				indice = index;
			}
		}
		return indice;
	}
}
