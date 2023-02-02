package adt.queue;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class QueueDoubleLinkedListImpl<T> implements Queue<T> {

	protected DoubleLinkedList<T> list;
	protected int size;

	public QueueDoubleLinkedListImpl(int size) {
		this.size = size;
		this.list = new DoubleLinkedListImpl<T>();
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if(!(element == null)) {
			if(isFull()) {throw new QueueOverflowException();}
			else {this.list.insert(element);}
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if(isEmpty()) {throw new QueueUnderflowException();}
		else{
			T resposta = head();
			this.list.removeFirst();
			return resposta;
		}
	}

	@Override
	public T head() {
		if(isEmpty()) {return null;}
		T[] array = list.toArray();
		return array[0];
	}

	@Override
	public boolean isEmpty() {
		return this.list.isEmpty();
	}

	@Override
	public boolean isFull() {
		return this.list.size() == this.size;
	}

}
