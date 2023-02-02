package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {

	}


	@Override
	public boolean isEmpty() {
		if(this.data == null) {return true;}
		else {return false;}
	}

	@Override
	public int size() {
		if(isEmpty()) {return 0;}
		else {return 1 + this.next.size();}
	}

	@Override
	public T search(T element) {
		if(isEmpty()) {return null;}
		else {
			if (this.data == element) {return this.data;}
			else {return this.next.search(element);}
		}
	}

	@Override
	public void insert(T element) {
		if (!(element == null)) {
			if(isEmpty()) {
				this.data = element;
				this.next = new RecursiveSingleLinkedListImpl<T>();
			} else {this.next.insert(element);}
		}
	}

	@Override
	public void remove(T element) {
		if(!(element == null)) {
			if(isEmpty()) {}
			else {
				if(this.data == element) {
					this.data = this.next.getData();
					this.next.setData(this.next.next.getData());
				} else {this.next.remove(element);}
			}
		}
	}

	@Override
	public T[] toArray() {
		java.util.LinkedList array = new java.util.LinkedList();
		to_Array(array, this);
		return (T[]) array.toArray();
	}

	private void to_Array(java.util.LinkedList array, RecursiveSingleLinkedListImpl<T> node) {
		if(!node.isEmpty()) {
			array.add(node.getData());
			to_Array(array, node.next);
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
