package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		return this.head.isNIL();
	}

	@Override
	public int size() {
		int size = 0;
		SingleLinkedListNode<T> auxHead = this.head;
		while (!auxHead.isNIL()) {
			size += 1;
			auxHead = auxHead.getNext();
		}
		return size;
	}

	@Override
	public T search(T element) {
		SingleLinkedListNode<T> auxHead = this.head;
		while (!auxHead.isNIL() && auxHead.getData() != element) {auxHead = auxHead.getNext();}
		return auxHead.getData();
	}

	@Override
	public void insert(T element) {
		if(!(element == null)) {
			SingleLinkedListNode<T> auxHead = this.head;
			if(isEmpty()) {
				SingleLinkedListNode<T> newHead = new SingleLinkedListNode<T>(element, this.head);
				this.head = newHead;
			} else {
				while (!auxHead.isNIL()) {auxHead = auxHead.getNext();}
				auxHead.setData(element);
				auxHead.setNext(new SingleLinkedListNode<>());
			}
		}
	}

	@Override
	public void remove(T element) {
		if(!(element == null)) {
			if(this.head.getData() == element) {this.head = this.head.getNext();}
			else {
				SingleLinkedListNode<T> auxHead = this.head;
				while (!auxHead.isNIL() && auxHead.getData() != element) {auxHead = auxHead.getNext();}
				if(!auxHead.isNIL()) {
					auxHead.setData(auxHead.getNext().getData());
					auxHead.setData(auxHead.getNext().getNext().getData());
				}
			}
		}
	}

	@Override
	public T[] toArray() {
		SingleLinkedListNode<T> auxHead = this.head;
		int tamanho = size();
		T[] array = (T[]) new Comparable[tamanho];
		for (int i = 0; i < tamanho; i++) {
			array[i] = auxHead.getData();
			auxHead = auxHead.getNext();
		}
		return array;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}
}
