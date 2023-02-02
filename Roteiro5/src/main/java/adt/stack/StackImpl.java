package adt.stack;

import java.util.Arrays;

public class StackImpl<T> implements Stack<T> {

	private T[] array;
	private int top;

	@SuppressWarnings("unchecked")
	public StackImpl(int size) {
		array = (T[]) new Object[size];
		top = -1;
	}

	@Override
	public T top() {
		if(isEmpty()) {return null;}
		else{return this.array[this.top];}
	}

	@Override
	public boolean isEmpty() {
		return this.top == -1;
	}

	@Override
	public boolean isFull() {
		return this.top == this.array.length - 1;	
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if(isFull()) {throw new StackOverflowException();}
		else {
			this.top += 1;
			this.array[this.top] = element;
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		if(isEmpty()) {throw new StackUnderflowException();}
		else {
			T result = this.array[this.top];
			this.top -= 1;
			return result;
		}
	}

}
