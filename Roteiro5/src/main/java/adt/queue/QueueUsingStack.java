package adt.queue;

import adt.stack.Stack;
import adt.stack.StackImpl;
import adt.stack.StackOverflowException;
import adt.stack.StackUnderflowException;

public class QueueUsingStack<T> implements Queue<T> {

	private Stack<T> stack1;
	private Stack<T> stack2;

	public QueueUsingStack(int size) {
		stack1 = new StackImpl<T>(size);
		stack2 = new StackImpl<T>(size);
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if(this.stack1.isFull()) {throw new QueueOverflowException();}
		try {this.stack1.push(element);} 
		catch (StackOverflowException e) {e.printStackTrace();}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if(this.stack1.isEmpty()) {throw new QueueUnderflowException();}
		T result = null;
		movePilhas(this.stack1, this.stack2);
		try { result = this.stack2.pop();} 
		catch (StackUnderflowException e) {e.printStackTrace();}
		movePilhas(this.stack2, this.stack1);
		return result;
	}

	@Override
	public T head() {
		if(this.stack1.isEmpty()) {return null;}
		T result = null;
		movePilhas(this.stack1, this.stack2);
		result = this.stack2.top();
		movePilhas(this.stack2, this.stack1);
		return result;	
	}

	@Override
	public boolean isEmpty() {
		return this.stack1.isEmpty();
	}

	@Override
	public boolean isFull() {
		return stack1.isFull();
	}

	private void movePilhas(Stack<T> pilha1, Stack<T> pilha2) {
		while (!pilha1.isEmpty()) {
			try {pilha2.push(pilha1.pop());}
			catch (StackOverflowException | StackUnderflowException e) {e.printStackTrace();}
		}
		this.stack1 = pilha1;
		this.stack2 = pilha2;
	}
}
