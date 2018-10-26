package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;
import adt.hashtable.hashfunction.HashFunctionOpenAddress;

public class HashtableOpenAddressLinearProbingImpl<T extends Storable> extends AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressLinearProbingImpl(int size, HashFunctionClosedAddressMethod method) {
		super(size);
		hashFunction = new HashFunctionLinearProbing<T>(size, method);
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
			if (table[index] == null && probe < table.length) {
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
			if (table[index] != null && table[index].equals(element)) {
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
			if (table[index] != null && table[index].equals(element)) {
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
			while (!table[index].equals(element) && probe < table.length && table[index] != null) {
				index = ((HashFunctionOpenAddress<T>) hashFunction).hash(element, ++probe);
			}
			if (table[index].equals(element)) {
				indice = index;
			}

		}
		return indice;

	}

}
