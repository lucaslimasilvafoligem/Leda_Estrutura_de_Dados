package adt.queue;

public class CircularQueue<T> implements Queue<T> {

	private T[] array;
	private int tail;
	private int head;
	private int elements;

	public CircularQueue(int size) {
		array = (T[]) new Object[size];
		head = -1;
		tail = -1;
		elements = 0;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if(isFull()) {throw new QueueOverflowException();}
		else {
			this.tail = (this.tail + 1) % (this.array.length -1);
			this.array[tail] = element;
			this.elements += 1;
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if(isEmpty()) {throw new QueueUnderflowException();}
		else {
			this.head = (this.head + 1) % (this.array.length -1);
			T result = this.array[this.head];
			this.elements -= 1;
			return result;
		}
	}

	@Override
	public T head() {
		if(isEmpty()) {return null;}
		else {
			T result = this.array[this.head + 1];
			return result;
		}
	}

	@Override
	public boolean isEmpty() {
		return this.elements == 0;
	}

	@Override
	public boolean isFull() {
		return this.elements == this.array.length;
	}
}
