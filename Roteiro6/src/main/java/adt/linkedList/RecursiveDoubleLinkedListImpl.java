package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends
		RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {

	}

	@Override
	public void insertFirst(T element) {
		if(!(element == null)) {
			if(isEmpty()) {
				super.insert(element);
				this.previous = new RecursiveDoubleLinkedListImpl<>();
			}
			else {
				RecursiveDoubleLinkedListImpl newElemento = new RecursiveDoubleLinkedListImpl();
				newElemento.setData(super.data);
				newElemento.setNext(super.next);
				super.data = element;
				this.previous = new RecursiveDoubleLinkedListImpl<>();
				super.next = newElemento;
			}
		}
	}

	@Override
	public void removeFirst() {
		if(!super.isEmpty()) {
			this.data = this.next.getData();
			this.next = this.next.next;
		}
	}

	@Override
	public void removeLast() {
		if(!super.isEmpty()) {
			if(size() <= 1) {removeFirst();}
			else {auxRemoveLast(this.next);}
		}
	}

	private void auxRemoveLast(RecursiveSingleLinkedListImpl<T> next) {
		if(!next.next.isEmpty()) {auxRemoveLast(next.next);}
		else {
			next.setData(null);
			next.next = null;
		}
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}

}
