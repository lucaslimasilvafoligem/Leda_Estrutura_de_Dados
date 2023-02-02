package adt.heap.extended;

import java.util.Comparator;

import adt.heap.ComparatorMaxHeap;
import adt.heap.ComparatorMinHeap;
import adt.heap.HeapImpl;

public class FloorCeilHeapImpl extends HeapImpl<Integer> implements FloorCeilHeap {

	public FloorCeilHeapImpl(Comparator<Integer> comparator) {
		super(comparator);
	}

	@Override
	public Integer floor(Integer[] array, double numero) {
		if (array.length < 1) {return null;}
		HeapImpl<Integer> heap = new HeapImpl<>(new ComparatorMinHeap<Integer>());
		for (int j = 0; j < array.length; j++) {heap.insert(array[j]);}
		Integer floor = null;
		int i = 0;
		int tamanho = heap.size();
		while (tamanho > i && numero >= heap.rootElement()) {
			i += 1;
			floor = heap.extractRootElement();
		}
		return floor;
	}

	@Override
	public Integer ceil(Integer[] array, double numero) {
		if (array.length < 1) {return null;}
		HeapImpl<Integer> heap = new HeapImpl<>(new ComparatorMaxHeap<Integer>());
		for (int j = 0; j < array.length; j++) {heap.insert(array[j]);}
		Integer ceil = null;
		int i = 0;
		int tamanho = heap.size();
		while (tamanho > i && numero <= heap.rootElement()) {
			i += 1;
			ceil = heap.extractRootElement();
		}
		return ceil;
	}

}
