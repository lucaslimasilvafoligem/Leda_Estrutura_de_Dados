package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;

	public DoubleLinkedListImpl() {
		this.last = (new DoubleLinkedListNode<>());
		this.head = (new DoubleLinkedListNode<>());
	}

	@Override
	public void insertFirst(T element) {
		DoubleLinkedListNode<T> newHead = new DoubleLinkedListNode<>(element, (new DoubleLinkedListNode<>()), (new DoubleLinkedListNode<>()));
		newHead.setNext(this.head);
		this.head = new DoubleLinkedListNode<>(this.head.getData(), ((DoubleLinkedListNode<T>) this.head.next), newHead);
		if(this.head.isNIL()) {this.last = newHead;}
		this.head = newHead;
	}

	@Override
	public void removeFirst() {
		if(!this.head.isNIL()) {
			this.head = this.head.getNext();
			if(this.head.isNIL()) {this.last.setData(this.head.getData());}
			else {((DoubleLinkedListNode<T>)this.head).setPrevious((new DoubleLinkedListNode<>()));}
		}
	}

	@Override
	public void removeLast() {
		if(!this.last.isNIL()) {
			this.last = this.last.getPrevious();
			if(this.last.isNIL()) {this.head = this.last;}
			else {this.last.setNext(new DoubleLinkedListNode<>());}
		}
	}

	@Override
	public void insert(T element) {
		if(!(element == null)) {
			DoubleLinkedListNode<T> newN = new DoubleLinkedListNode<>(element, (new DoubleLinkedListNode<>()), this.last);
			this.last.setNext(newN);
			if(this.head.isNIL()) {this.head = newN;}
			this.last = newN;
		}
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

}
