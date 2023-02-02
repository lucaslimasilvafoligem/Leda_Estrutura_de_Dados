package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class StackDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> top;
	protected int size;

	public StackDoubleLinkedListImpl(int size) {
		this.size = size;
		this.top = new DoubleLinkedListImpl<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if(!(element == null)) {
			if(isFull()) {throw new StackOverflowException();}
			else {this.top.insert(element);}
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		if(isEmpty()) {throw new StackUnderflowException();}
		else {
			T resultado = top();
			this.top.removeLast();
			return resultado;
		}
	}

	@Override
	public T top() {
		if(isEmpty()) {return null;}
		else {
			T[] array = this.top.toArray();
			return array[array.length - 1];
		}
	}

	@Override
	public boolean isEmpty() {
		return this.top.isEmpty();
	}

	@Override
	public boolean isFull() {
		return this.top.size() == this.size;
	}

}
