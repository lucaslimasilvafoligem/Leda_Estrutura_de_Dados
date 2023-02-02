package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;

public class HashtableOpenAddressLinearProbingImpl<T extends Storable> extends
		AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressLinearProbingImpl(int size,
			HashFunctionClosedAddressMethod method) {
		super(size);
		hashFunction = new HashFunctionLinearProbing<T>(size, method);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		if(!(element == null) && !isFull()) {
			for (int i = 0; i < this.table.length; i++) {
				int key = ((HashFunctionLinearProbing<T>) this.hashFunction).hash(element, i);
				if(((DELETED)this.table[key]).toString().equals("D") || this.table[key] == null) {this.table[key] = element;}
			}
		}	
	}

	@Override
	public void remove(T element) {
		if(!(element == null) && !isEmpty()) {
			for (int i = 0; i < this.table.length; i++) {
				int key = ((HashFunctionLinearProbing) this.hashFunction).hash(element, i);
				if((this.table[key]).equals(element)) {this.table[key] = new DELETED();}
			}
		}
	}

	@Override
	public T search(T element) {
		if(!(element == null) && !isEmpty()) {
			for (int i = 0; i < this.table.length; i++) {
				int key = ((HashFunctionLinearProbing) this.hashFunction).hash(element, i);
				if((this.table[key]).equals(element)) {return element;}
			}
			return null;
		} else {return null;}
	}

	@Override
	public int indexOf(T element) {
		if(!(element == null) && !isEmpty()) {
			for (int i = 0; i < this.table.length; i++) {
				int key = ((HashFunctionLinearProbing) this.hashFunction).hash(element, i);
				if((this.table[key]).equals(element)) {return key;}
			}
		}
		return -1;
	}

}
