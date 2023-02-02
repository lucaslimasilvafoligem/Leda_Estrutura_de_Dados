package adt.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import util.Util;

/**
 * O comportamento de qualquer heap é definido pelo heapify. Neste caso o
 * heapify dessa heap deve comparar os elementos e colocar o menor sempre no
 * topo. Ou seja, admitindo um comparador normal (responde corretamente 2 < 3),
 * essa heap deixa os elementos menores no topo. Essa comparação não é feita
 * diretamente com os elementos armazenados, mas sim usando um comparator. 
 * Dessa forma, dependendo do comparator, a heap pode funcionar como uma max-heap 
 * ou min-heap.
 */
public class HeapImpl<T extends Comparable<T>> implements Heap<T> {

	protected T[] heap;
	protected int index = -1;
	/**
	 * O comparador é utilizado para fazer as comparações da heap. O ideal é
	 * mudar apenas o comparator e mandar reordenar a heap usando esse
	 * comparator. Assim os metodos da heap não precisam saber se vai funcionar
	 * como max-heap ou min-heap.
	 */
	protected Comparator<T> comparator;

	private static final int INITIAL_SIZE = 20;
	private static final int INCREASING_FACTOR = 10;

	/**
	 * Construtor da classe. Note que de inicio a heap funciona como uma
	 * min-heap. OU seja, voce deve considerar que a heap usa o comparator
	 * interno e se o comparator responde compare(x,y) < 0 entao o x eh menor
	 * e sobe na heap.
	 */
	@SuppressWarnings("unchecked")
	public HeapImpl(Comparator<T> comparator) {
		this.heap = (T[]) (new Comparable[INITIAL_SIZE]);
		this.comparator = comparator;
	}

	// /////////////////// METODOS IMPLEMENTADOS
	private int parent(int i) {
		return (i - 1) / 2;
	}

	/**
	 * Deve retornar o indice que representa o filho a esquerda do elemento
	 * indexado pela posicao i no vetor
	 */
	private int left(int i) {
		return (i * 2 + 1);
	}

	/**
	 * Deve retornar o indice que representa o filho a direita do elemento
	 * indexado pela posicao i no vetor
	 */
	private int right(int i) {
		return (i * 2 + 1) + 1;
	}

	@Override
	public boolean isEmpty() {
		return (index == -1);
	}

	@Override
	public T[] toArray() {
		ArrayList<T> resp = new ArrayList<T>();
		for (int i = 0; i <= this.index; i++) {
			resp.add(this.heap[i]);
		}
		return (T[])resp.toArray(new Comparable[0]);
	}

	// ///////////// METODOS A IMPLEMENTAR
	/**
	 * Valida o invariante de uma heap a partir de determinada posicao, que pode
	 * ser a raiz da heap ou de uma sub-heap. O heapify deve usar o comparator
	 * para subir os elementos na heap.
	 */
	private void heapify(int position) {
		if (isLeaf(position) || !isValidIndex(position)) {return;}
		int indexMaxOrMin = indexMaxOrMin(position, left(position), right(position));
		if (indexMaxOrMin != position) {
			swap(position, indexMaxOrMin);
			heapify(indexMaxOrMin);
		}
	}
	
	private int indexMaxOrMin(int position, int left, int right) {
		if(!isValidIndex(left)) {return position;}
		if (this.comparator.compare(this.heap[position], this.heap[left]) > 0) {
			if (isValidIndex(right)) {
				if (this.comparator.compare(this.heap[position], this.heap[right]) < 0) {return right;}
			}
			return position;
		} else {
			if (isValidIndex(right)) {
				if (this.comparator.compare(this.heap[left], this.heap[right]) < 0) {return right;}
			}
			return left;
		}
	}
	
	private boolean isValidIndex(int position) {
		return position >= 0 && position <= this.index;
	}

	private boolean isLeaf(int position) {
		return position > parent(this.index) && position <= this.index;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			// ESSE CODIGO E PARA A HEAP CRESCER SE FOR PRECISO. NAO MODIFIQUE
			if (this.index == this.heap.length - 1) {
				this.heap = Arrays.copyOf(heap, heap.length + INCREASING_FACTOR);
			}
			this.index += 1;
			this.heap[this.index] = element;
			int i = this.index;
			while (i > 0 && this.comparator.compare(this.heap[parent(i)], this.heap[i]) < 0) {
				swap(i, parent(i));
				i = parent(i);
			}
		}
	}

	@Override
	public void buildHeap(T[] array) {
		this.heap = array;
		this.index = this.heap.length - 1;
		if(this.index > 1) {
			for (int i = parent(this.index); i >= 0; i--) {heapify(i);}
		}
	}

	@Override
	public T extractRootElement() {
		if(isEmpty()) {return null;}
		T resposta = this.heap[0];
		this.heap[0] = this.heap[this.index];
		this.index -= 1;
		this.heapify(0);
		return resposta;
	}

	@Override
	public T rootElement() {
		if(isEmpty()) {return null;}
		return this.heap[0];
	}

	@Override
	public T[] heapsort(T[] array) {
		if (array.length <= 1) {return array;}
		buildHeap(array);
		T[] arrayResp = (T[]) new Comparable[size()];
		if(this.heap[0].compareTo(this.heap[1]) > 0) {
			for (int i = this.index; i > -1; i--) {arrayResp[i] = extractRootElement();}
		} else {
			for (int i = 0; i < arrayResp.length; i++) {arrayResp[i] = extractRootElement();}
		}
		return arrayResp;
	}

	@Override
	public int size() {
		return this.index + 1;
	}
	
	private void swap(int index1, int index2) {
		T aux = this.heap[index1];
		this.heap[index1] = this.heap[index2];
		this.heap[index2] = aux;
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	public T[] getHeap() {
		return heap;
	}

}
